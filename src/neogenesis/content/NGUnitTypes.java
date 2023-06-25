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
    public static UnitType decade,
    //mech, legacy

    //endregion
    personal;
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
        personal = new UnitType("y-a-01-personal"){{
            speed = 4f;
            hitSize = 10f;
            accel = 0.05f;
            drag = 0.1f;
            health = 10*310;
            lifetime = 3600;
            constructor = TimedKillUnit::create;
            flying = true;
            singleTarget = false;
            rotateSpeed = 20f;
            ammoType = new PowerAmmoType(800);
            for(int j = 0; j < 3; j++){
                int i = j*2;
                parts.add(new ShapePart(){{
                    hollow=true;
                    x = 0f;
                    rotateSpeed = 3+i;
                    color = Liquids.cryofluid.color.cpy().a(0.5f);
                    strokeTo = stroke = 5;
                    radius=radiusTo=36;
                    layer = 110;
                            circle=false;
                    sides=6;
                }});
            }
            weapons.add(new Weapon("type-laser"){{
                reload = 9f;
                x = 0f;
                y = 0f;
                top = false;
                mirror=false;
                shootSound= (Sounds.none);
                shoot = new ShootMulti
                        (
                                new ShootPattern(){{
                                    shots = 9;
                                    shotDelay =1;
                                }},new ShootBarrel(){{
                    barrels = new float[]{
                            0f, 16f, 0f,
                            8f, 12f, 0f,
                            -8f, 12f, 0f,
                    };
                    shots=3;
                }}
                );
                bullet = new BasicBulletType(16f, 1000){{
                    width = 7f;
                    height = 9f;
                    lifetime = 50f;
                    shootEffect = NGFx.decade1;
                    smokeEffect= NGFx.decade1sm;
                    hitColor = backColor = trailColor = Liquids.cryofluid.color;
                    trailLength = 6;
                    trailWidth = 1f;
                    despawnEffect = Fx.hitBulletColor;
                    hitEffect = Fx.hitSquaresColor;
                    sprite = "large-missile";
                    shrinkY=1;
                }};
            }},
                    new Weapon("shootdeath"){{
                        reload = 100f;
                        x = 0f;
                        y = 0f;
                        top = false;
                        mirror=false;
                        controllable=false;
                        shootOnDeath=true;
                        shootCone=360;
                        shootSound= (Sounds.laserbig);
                        shoot = new ShootMulti
                                (
                                        new ShootSpread(){{
                                            shots = 8;
                                            spread= 45f;
                                        }},new ShootBarrel(){{
                                    barrels = new float[]{
                                            0f, 0f, 22.5f,
                                    };
                                }}
                                );
                        bullet = new LaserBulletType(){{
                            length = 600f;
                            damage = 2675f;
                            width = 80f;

                            lifetime = 50f;

                            lightningSpacing = 24f;
                            lightningLength = 2;
                            lightningDelay = 0.5f;
                            lightningLengthRand = 0;
                            lightningDamage = 33;
                            lightningType = new ExplosionBulletType(675f, 40f){{
                                collidesAir = true;
                                shootEffect = Fx.blastExplosion;
                            }};
                            lightningAngleRand = 0f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.redLight;
                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.redLight.cpy().a(0.4f), Pal.redLight, Color.white};
                        }};
                    }});
        }};

    }
}
