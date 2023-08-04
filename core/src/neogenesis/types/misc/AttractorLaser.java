package neogenesis.types.misc;

import arc.graphics.Color;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.util.Time;
import mindustry.content.StatusEffects;
import mindustry.type.StatusEffect;
import neogenesis.utils.AttractUtils;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.Lightning;
import mindustry.entities.Units;
import mindustry.entities.bullet.ContinuousLaserBulletType;
import mindustry.gen.Bullet;

public class AttractorLaser extends ContinuousLaserBulletType {

    protected Rect rect = new Rect();
    protected Vec2 tr = new Vec2();
    protected Vec2 tr2 = new Vec2();

    public float dragRadius = 11 * 8;
    public float dragPower = 200F;


    /**
     * This is unnessarary.
     * Because we already have.
     * public float lightningSpacing;
    public float lightningDelay;
    public float lightningAngleRand;
    public Color lightningColor;**/
    public StatusEffect attractedStatus = StatusEffects.melting;

    public AttractorLaser() {

        // Replaces 'ContinousLaserBulletType'.

    }

    protected void rotate(float ox, float oy, float nx, float ny, float rotation) {
        this.tr2.set(nx, ny).sub(ox, oy).rotate(-rotation);
    }

    protected float dragPowerPercent(float dst, float radius) {
        return Interp.sineOut.apply(1 - dst / radius);
    }

    @Override
    public void update(Bullet b) {
        var length = Damage.findLaserLength(b, this.length);
        if (b.timer.get(1, 2)) {
            // Drag them
            // calculate them
            this.tr.trns(b.rotation(), length);
            this.rect.setPosition(b.x, b.y).setSize(this.tr.x, this.tr.y);

            if (this.rect.width < 0) {
                this.rect.x += this.rect.width;
                this.rect.width *= -1;
            }

            if (this.rect.height < 0) {
                this.rect.y += this.rect.height;
                this.rect.height *= -1;
            }

            var expand = this.dragRadius * 1.4;

            this.rect.y -= expand;
            this.rect.x -= expand;
            this.rect.width += expand * 2;
            this.rect.height += expand * 2;

            Units.nearbyEnemies(b.team, this.rect, u -> {
                final float dst =
                        AttractUtils.attractUnitToLine(u, b.x, b.y, b.rotation(), length, this.dragRadius, this.dragPower);
                if (dst < 3 && this.dragPower > AttractUtils.enginePower(u)) {
                    u.apply(attractedStatus, 6);
                }
            });
        }

        if (b.timer.get(2, 5)) {
            Damage.collideLine(b, b.team, this.hitEffect, b.x, b.y, b.rotation(), this.length, this.largeHit);
        }

        /** if (this.lightningSpacing > 0 && b.timer.get(3, 59)) {
            var idx = 0;
            var rot = b.rotation();
            // lightningSound.at(b.x + Angles.trnsx(rot, length / 2), b.y + Angles.trnsy(rot, length / 2));
            for (var i = (this.lightningSpacing + Mathf.random(this.lightningSpacing)) / 2;
                 i <= length; i += this.lightningSpacing) {
                var cx = b.x + Angles.trnsx(rot, i);
                var cy = b.y + Angles.trnsy(rot, i);

                var f = idx++;

                for (var s : Mathf.signs) {
                    Time.run(f * this.lightningDelay, (() -> {
                        if (b.isAdded() && b.type == this) {
                            Lightning.create(b, this.lightningColor, this.lightningDamage,
                                    cx, cy, rot + 90 * s + Mathf.range(this.lightningAngleRand),
                                    (int) (this.lightningLength + Mathf.random(this.lightningLengthRand)));
                        }
                    }));
                }
            }
        }**/

        if (this.shake > 0) {
            Effect.shake(this.shake, this.shake, b);
        }
    }
}