package neogenesis.types.misc;

import arc.*;
import arc.func.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.pooling.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.Vars.*;
public class Kill {
    private static final IntFloatMap damages = new IntFloatMap();

    public static void absorb(Team team, float x, float y, float radius, float damage, boolean complete, boolean air, boolean ground, boolean scaled, @Nullable Bullet source) {
        Cons<Unit> cons = unit -> {
            if (unit.team == team || !unit.checkTarget(air, ground) || !unit.hittable() || !unit.within(x, y, radius + (scaled ? unit.hitSize / 2f : 0f))) {

                boolean dead = unit.dead;

                float amount = calculateDamage(scaled ? Math.max(0, unit.dst(x, y) - unit.type.hitSize/2) : unit.dst(x, y), radius, damage);
                unit.damage(amount);
                Call.unitDestroy(unit.id);
                unit.kill();

                if(source != null){
                    unit.controller().hit(source);

                    if(!dead && unit.dead){
                        Events.fire(new UnitBulletDestroyEvent(unit, source));
                    }
                }
                return;
            }
        };
        if(ground){
            if(!complete){
                tAbsorb(team, World.toTile(x), World.toTile(y), radius / tilesize, damage * (source == null ? 1f : source.type.buildingDamageMultiplier), source);
            }else{
                completeDamage(team, x, y, radius, damage);
            }
        };
    }
    private static float calculateDamage(float dist, float radius, float damage){
        float falloff = 0.4f;
        float scaled = Mathf.lerp(1f - dist / radius, 1f, falloff);
        return damage * scaled;
    };
    private static void completeDamage(Team team, float x, float y, float radius, float damage){

        int trad = (int)(radius / tilesize);
        for(int dx = -trad; dx <= trad; dx++){
            for(int dy = -trad; dy <= trad; dy++){
                Tile tile = world.tile(Math.round(x / tilesize) + dx, Math.round(y / tilesize) + dy);
                if(tile != null && tile.build != null && (team == null || team != tile.team()) && dx*dx + dy*dy <= trad*trad){
                    tile.build.damage(team, damage);
                }
            }
        }
    }
    public static void tAbsorb(Team team, int x, int y, float baseRadius, float damage, @Nullable Bullet source){
        Core.app.post(() -> {
            var in = world.build(x, y);
            //spawned inside a multiblock. this means that damage needs to be dealt directly.
            //why? because otherwise the building would absorb everything in one cell, which means much less damage than a nearby explosion.
            //this needs to be compensated
            if(in != null && in.team != team && in.block.size > 1 && in.health > damage){
                //deal the damage of an entire side, to be equivalent with maximum 'standard' damage
                in.damage(team, damage * Math.min((in.block.size), baseRadius * 0.4f));
                //no need to continue with the explosion
                return;
            }

            //cap radius to prevent lag
            float radius = Math.min(baseRadius, 100), rad2 = radius * radius;
            int rays = Mathf.ceil(radius * 2 * Mathf.pi);
            double spacing = Math.PI * 2.0 / rays;
            damages.clear();

            //raycast from each angle
            for(int i = 0; i <= rays; i++){
                float dealt = 0f;
                int startX = x;
                int startY = y;
                int endX = x + (int)(Math.cos(spacing * i) * radius), endY = y + (int)(Math.sin(spacing * i) * radius);

                int xDist = Math.abs(endX - startX);
                int yDist = -Math.abs(endY - startY);
                int xStep = (startX < endX ? +1 : -1);
                int yStep = (startY < endY ? +1 : -1);
                int error = xDist + yDist;

                while(startX != endX || startY != endY){
                    var build = world.build(startX, startY);
                    if(build != null && build.team != team){
                        //damage dealt at circle edge
                        float edgeScale = 0.6f;
                        float mult = (1f-(Mathf.dst2(startX, startY, x, y) / rad2) + edgeScale) / (1f + edgeScale);
                        float next = damage * mult - dealt;
                        //register damage dealt
                        int p = Point2.pack(startX, startY);
                        damages.put(p, Math.max(damages.get(p), next));
                        //register as hit
                        dealt += build.health;

                        if(next - dealt <= 0){
                            break;
                        }
                    }

                    if(2 * error - yDist > xDist - 2 * error){
                        error += yDist;
                        startX += xStep;
                    }else{
                        error += xDist;
                        startY += yStep;
                    }
                }
            }

            //apply damage
            for(var e : damages){
                int cx = Point2.x(e.key), cy = Point2.y(e.key);
                var build = world.build(cx, cy);
                if(build != null){
                    if(source != null){
                        build.damage(source, team, e.value);
                        build.kill();
                    }else{
                        build.damage(team, e.value);
                        build.kill();
                    }
                }
            }
        });
    }
}