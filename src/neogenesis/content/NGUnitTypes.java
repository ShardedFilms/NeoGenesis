package neogenesis.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class NGUnitTypes{
    //region standard

    //mech
    public static UnitType decade;
    //mech, legacy

    //endregion

    public static void load(
            /* Used to be my personality as my own use. awe.
            * With The power of code and intelligence to perform creation.
            * However... One step goes wrong. because we founded
            * the Missing Constructor.
            *
            * Remember!, Every Units must have Constructor to make a type of unit! */
    ){

        decade = new UnitType("5-a-01-decade"){{
            speed = 0.4f;
            hitSize = 10f;
            health = 250;
            constructor = MechUnit::create;
            canDrown = true;
            mechStepParticles = false;
            stepShake = 0;
            mechFrontSway = 0.2f;
            mechSideSway = 0.1f;
            singleTarget = false;
            rotateSpeed = 4f;
            ammoType = new PowerAmmoType(800);
            parts.add(new ShapePart(){{
                sides = 4;
                progress = PartProgress.warmup;
                radius = 0;
                radiusTo = 2;
                layer= Layer.bullet;
                color=NGColor.purtuxe2;
                y =-6;
            }});
            weapons.add(new Weapon("purp"){{
                reload = 36f;
                x = 0f;
                y = 0f;
                top = false;
                mirror=false;
                shootSound= (Sounds.blaster);
                shoot= new ShootHelix(){{
                    scl =2f;
                    mag=1.5f;
                    offset = Mathf.PI * 1.25f;
                }};
                bullet = new BasicBulletType(8f, 18){{
                    width = 7f;
                    height = 9f;
                    lifetime = 20f;
                    shootEffect = NGFx.decade1;
                    smokeEffect= NGFx.decade1sm;
                    hitColor = backColor = trailColor = NGColor.purtuxe2;
                    trailLength = 6;
                    trailWidth = 1f;
                    despawnEffect = Fx.hitBulletColor;
                    hitEffect = Fx.hitSquaresColor;
                }};
            }});
        }};

    }
}
