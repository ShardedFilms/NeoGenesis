/** import java.util.function.Consumer;

import arc.math.Intersector;
import arc.math.Mathf;
import arc.struct.Groups;
import arc.util.Time;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.traits.ForceFieldAbility;
import mindustry.entities.type.Bullet;
import mindustry.entities.type.Unit;
import mindustry.graphics.Fx;

import static arc.Core.*;

public class InvincibleForceFieldAbility extends ForceFieldAbility {
    private float realRad;
    private Unit paramUnit;
    private InvincibleForceFieldAbility paramField;
    private Consumer<Bullet> shieldConsumer = trait -> {
        if (trait.team != paramUnit.team
                && trait.type.absorbable
                && Intersector.isInsideHexagon(paramUnit.x, paramUnit.y, realRad * 2, trait.x, trait.y)
                && paramUnit.shield > 0) {
            trait.absorb();
            Fx.absorb.at(trait);
            paramField.alpha = 1;
        }
    };

    public InvincibleForceFieldAbility(float radius, float regen, float max, float cooldown) {
        super(radius, regen, max, cooldown);
    }

    @Override
    public void update(Unit unit) {
        unit.shield = Float.POSITIVE_INFINITY;
        this.radiusScale = Mathf.lerpDelta(this.radiusScale, 1, 0.06f);
        realRad = this.radiusScale * this.radius;
        paramUnit = unit;
        paramField = this;
        Groups.bullet.intersect(unit.x - realRad, unit.y - realRad, realRad * 2, realRad * 2, shieldConsumer);
        this.alpha = Math.max(this.alpha - Time.delta / 10, 0);
    }

    @Override
    public ForceFieldAbility copy() {
        return new InvincibleForceFieldAbility(radius, regen, max, cooldown);
    }

    @Override
    public void draw(Unit unit) {
        super.draw(unit);
    }
}

    private BasicBulletType invincibleBulletType = new BasicBulletType() {
        @Override
        public void hitEntity(Bullet b, Unit other, float initialHealth) {
            if (other != null && other.kill)}

    **/
