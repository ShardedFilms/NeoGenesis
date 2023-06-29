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
import neogenesis.types.template.*;

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
            * Remember!, Every Unit must have Constructor to make a type of unit! */
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
            BulletType ex = new BasicBulletType(32f, 2625){{
                width = 21f;
                height = 72f;
                lifetime = 100;
                shootEffect = Fx.mineImpactWave;
                smokeEffect= Fx.mineImpactWave;
                frontColor=Color.white;
                damage = 2625*3f;
                hitColor = backColor = trailColor = Liquids.cryofluid.color;
                despawnEffect = NGFx.hlaserxplosion;
                hitEffect= Fx.none;
                sprite = "missile-large";
                despawnHit=true;
                splashDamage=2625;
                splashDamageRadius=120;
                trailWidth=6;
                trailLength=6;
                homingRange=1600;
                homingDelay=3;
                homingPower=0.2f;
                shrinkY=0;
            }};

            envDisabled = Env.none;
            createScorch = false;
            fogRadius *=1000;
            fallSpeed /=2;

            speed = 10f;
            hitSize = 24f;
            accel = 0.1f;
            drag = 0.05f;
            health = 2147483647;
            lifetime = 3600;
            constructor = TimedKillUnit::create;
            outlineRadius=0;
            flying = true;
            engineSize=0;
            singleTarget = false;
            rotateSpeed = 20f;
            deathExplosionEffect= NGFx.massiveShockwave;
            ammoType = new PowerAmmoType(800);
            for(int j = 0; j < 3; j++){
                int i = j;
                parts.add(new ShapePart(){{
                    hollow=true;
                    x = 0f;
                    rotateSpeed = -4-i*0.9f;
                    color = Liquids.cryofluid.color.cpy().a(0.4f);
                    strokeTo = stroke = 5;
                    radius=radiusTo=36;
                    layer = 110;
                            circle=false;
                    sides=6;
                }});
            };
            for(int j2 = 0; j2 < 2; j2++){
                int i2 = j2;
                parts.add(new ShapePart(){{
                    hollow=true;
                    x = 0f;
                    rotateSpeed = -3-i2;
                    color = Liquids.ozone.color.cpy().a(0.5f);
                    rotation=30;
                    strokeTo = stroke = 6;
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
                aimDst=99999;
                shoot = new ShootMulti
                        (
                                new ShootPattern(){{
                                    shots = 10;
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
                    width = 14f;
                    height = 36f;
                    lifetime = 50f;
                    shootEffect = NGFx.end;
                    smokeEffect= NGFx.end;
                    hitColor = backColor = trailColor = Liquids.cryofluid.color;
                    frontColor=Color.white;
                    trailWidth = 1f;
                    despawnEffect = Fx.hitBulletColor;
                    hitEffect = Fx.hitSquaresColor;
                    sprite = "missile-large";
                    shrinkY=0;
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
                                            spread= 22.5f;
                                        }},new ShootPattern()
                                );
                        bullet = new LaserBulletType(){{
                            length = 1000f;
                            damage = 2625f;
                            width = 180f;
                            layer = 109;

                            lifetime = 70f;

                            lightningSpacing = 12f;
                            lightningLength = 2;
                            lightningDelay = 0.1f;
                            lightningLengthRand = 0;
                            lightningDamage = 33;
                            lightningType = new ExplosionBulletType(4*2625f, 80f){{
                                collidesAir = true;
                                shootEffect = Fx.blastExplosion;
                            }};
                            lightningAngleRand = 0f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.redLight;
                            splashDamage = 2625;
                            splashDamageRadius=80;
                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.redLight.cpy().a(0.4f), Pal.redLight, Color.white};
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
                                            spread= 22.5f;
                                        }},new ShootPattern()
                                );
                        bullet = new ContinuousLaserBulletType(){{
                            damage = 675f;
                            damageInterval =1;
                            width = 36;
                            length = 1000f;
                            layer = 110;
                            hitEffect = Fx.scatheSlash;
                            drawSize = 420f;
                            lifetime = 240f;
                            fadeTime = 20f;
                            shake = 4f;
                            despawnEffect = Fx.none;
                            smokeEffect = Fx.none;

                            chargeEffect = Fx.none;
                            hitColor = Pal.redLight;

                            incendChance = 0f;
                            incendSpread = 0f;
                            incendAmount = 0;
                            colors = new Color[]{Pal.redLight.cpy().a(0.4f),Pal.redLight.cpy().a(0.8f), Pal.redLight, Color.white};
                            oscScl=0.6f;
                            oscMag=3f;


                        }
                            // Creates Lightning during its fire.
                            @Override
                            public void update(Bullet b) {
                                super.update(b);
                                if(b.timer.get(1)){
                                    Lightning.create(b.team, Pal.redLight, damage*2, b.x, b.y, b.rotation() + (6 - Mathf.range(12)), (int)(length/10));};
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
                                    spread= 22.5f;
                                }},new ShootPattern()
                        );
                bullet = new BombBulletType(0f, 0f){{
                    width = 10f;
                    height = 14f;
                    speed = 0.4f;
                    hitEffect = Fx.mineImpactWave;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    splashDamage = 2625*1000000;
                    splashDamageRadius= 400;
                    collides=false;
                    absorbable=false;
                    hittable=false;
                    lifetime=240;
                    hitColor = Liquids.cryofluid.color;
                    bulletInterval=3;
                    intervalBullets =3;
                    intervalRandomSpread=360;
                    collidesAir=collidesGround=collidesTiles=collideFloor=false;
                    intervalBullet = ex;
                    fragVelocityMin=1.2f;
                    fragVelocityMax=1.2f;
                    fragLifeMax = fragLifeMin = 2f;
                    fragBullet=ex;
                    fragBullets=72;
                    fragSpread=5;
                    fragRandomSpread=0;
                    despawnEffect = NGFx.massiveSmoke;
                }};
            }});
            // Secondary with ,for, loop

                    weapons.add(new TurretLarge("personal-mount"){{

                        aimDst = 99999;
                        x= -100f;
                        y= 0f;
                                    mirror = true;
                                    reload/=2;

                    }},
                            new TurretLarge("personal-mount"){{

                                aimDst = 99999;
                                x= -60f;
                                y= -50f;
                                mirror = true;
                                reload/=2;

                            }},new TurretLarge("personal-mount"){{

                                aimDst = 99999;
                                x= -0f;
                                y= -80f;

                            }}


                    );

            abilities.add(new RegenAbility(){{
                percentAmount=6000;

            }});
        }};

    }
}
