/**package neogenesis.types.misc;

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

    public static void absorb(Team team, float x, float y, float radius, float damage, boolean complete, boolean air, boolean ground, boolean scaled, @Nullable Bullet source) {
        Cons<Unit> cons = unit -> {
            if (unit.team == team || !unit.checkTarget(air, ground) || !unit.hittable() || !unit.within(x, y, radius + (scaled ? unit.hitSize / 2f : 0f))) {

                return;
                boolean dead = unit.dead;

                float amount = calculateDamage(scaled ? Math.max(0, unit.dst(x, y) - unit.type.hitSize/2) : unit.dst(x, y), radius, damage);
                unit.damage(amount);

                if(source != null){
                    unit.controller().hit(source);
                    Call.unitDestroy(unit.id);

                    if(!dead && unit.dead){
                        Events.fire(new UnitBulletDestroyEvent(unit, source));
                    }
                }
            }
        };
    }
    private static float calculateDamage(float dist, float radius, float damage){
        float falloff = 0.4f;
        float scaled = Mathf.lerp(1f - dist / radius, 1f, falloff);
        return damage * scaled;
    }}**/