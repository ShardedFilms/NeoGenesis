package neogenesis.types.template;

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

import neogenesis.content.*;

/** Large Sandbox turret. Already Modified. */
public class TurretLarge extends Weapon{

    public TurretLarge(){

    }

    public TurretLarge(String name){
        super(name);
    }

    {
        reload = 9f;
        shootY = 20;
        rotateSpeed = 0.2f;
        rotationLimit = 20f;
        rotate = false;
        recoil = 0;
        top = true;
        mirror=false;
        alternate=false;
        shootSound= (Sounds.none);
        shoot = new ShootMulti
                (
                        new ShootPattern(){{
                            shots = 10;
                            shotDelay =1;
                        }},new ShootBarrel(){{
                    barrels = new float[]{
                            0f, 16f, 0f,
                            6f, 12f, 0f,
                            -6f, 12f, 0f,
                    };
                    shots=3;
                }}
                );
        for(int j2 = 0; j2 < 2; j2++){
            int i2 = j2*30;
            parts.add(new ShapePart(){{
                hollow=true;
                x = 0f;
                color = Liquids.cryofluid.color.cpy().a(0.5f);
                rotation=i2;
                strokeTo = stroke = 6;
                radius=radiusTo=36;
                layer = 110;
                circle=false;
                sides=6;
            }});
        }
        bullet = new BasicBulletType(16f, 1000){{
            width = 18f;
            height = 28f;
            lifetime = 70f;
            shootEffect = NGFx.end;
            smokeEffect= NGFx.end;
            hitColor = backColor = trailColor = Liquids.cryofluid.color;
            frontColor= Color.white;
            trailWidth = 1f;
            despawnEffect = Fx.hitBulletColor;
            hitEffect = Fx.hitSquaresColor;
            sprite = "bullet";
            shrinkY=0;
        }};

    }
}
