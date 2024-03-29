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
import neogenesis.types.misc.AccelBulletType;
import neogenesis.types.misc.ShootVertical;
import neogenesis.types.template.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class NGUnitTypes{
    //region standard

    //mech
    public static UnitType
    //mech, legacy

    andrius ,matther,
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

        andrius = new UnitType("b2-a-01-andrius"){{

            accel = 0.15f;
            drag = 0.07f;
            speed = 5f;
            hitSize = 14f;
            health = 800;
            armor = 20;
            constructor = UnitEntity::create;
            singleTarget = false;
            rotateSpeed = 20f;
            ammoType = new PowerAmmoType(2000);

            flying = true;
            engineSize = 3;
            engineOffset = 7;
            trailLength = 6;
            outlineColor = Pal.darkOutline;
            weapons.add(new Weapon("blaster"){{
                reload = 9f;
                alternate = true;
                x = 4f;
                y = 3f;
                top = true;
                mirror=true;
                shootSound= (Sounds.blaster);
                shootStatus = StatusEffects.slow;
                shootStatusDuration= 12;
                bullet = new BasicBulletType(10f, 27){{
                    width = 7f;
                    height = 9f;
                    lifetime = 16f;
                    shootEffect = NGFx.andrius1;
                    smokeEffect= NGFx.andrius2;
                    hitColor = backColor = trailColor = Pal.accent;
                    frontColor = Color.white;
                    trailLength = 6;
                    trailWidth = 1f;
                    despawnEffect = Fx.hitBulletColor;
                    hitEffect = Fx.hitSquaresColor;
                }};
            }});
        }};
        matther = new UnitType("b2-a-02-matther"){{

            accel = 0.12f;
            drag = 0.09f;
            speed = 3f;
            hitSize = 22f;
            health = 2400;
            armor = 36;
            constructor = UnitEntity::create;
            singleTarget = false;
            rotateSpeed = 6f;
            ammoType = new PowerAmmoType(2000);

            flying = true;
            lowAltitude = false;
            engineSize = 4;
            engineOffset = 10;
            trailLength = 10;
            outlineColor = Pal.darkOutline;
            weapons.add(new Weapon(name + "-launcher"){{
                reload = 90f;
                x=0f;
                y = 5f;
                layerOffset = -0.01f;
                top = false;
                mirror = alternate =false;
                recoil = 3f;
                shootSound= (Sounds.missileSmall);
                shoot = new ShootMulti(
                        new ShootBarrel(){{
                            barrels = new float[]{
                                    -1.5f, 0f, -10f,
                                    0f, 0f, 0f,
                                    1.5f, 0f, 10f,
                            };
                            shots=3;
                            shotDelay = 1;
                        }},new ShootPattern(){{
                            shots = 3;
                            shotDelay = 18;
                }}

                );
                bullet = new MissileBulletType(6f, 36){{
                    width = 10f;
                    height = 15f;
                    shrinkY = 0f;
                    homingRange = 80f;
                    keepVelocity = false;
                    splashDamageRadius = 30f;
                    shootEffect = Fx.shootSmokeSquareBig;
                    smokeEffect = Fx.shootTitan;
                    splashDamage = 28f;
                    lifetime = 45f;
                    trailLength = 9;
                    trailWidth = 1f;
                    trailColor = Pal.accent;
                    backColor = Pal.accent;
                    hitColor = Pal.accent;
                    frontColor = Color.white;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;
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
                damage *=100f; // Hard Shells = Increased Hit Damage
                hitColor = backColor = trailColor = Liquids.cryofluid.color;
                despawnEffect = NGFx.hlaserxplosion;
                hitEffect= Fx.none;
                sprite = "missile-large";
                despawnHit=true;
                splashDamage=2625*12;
                splashDamageRadius=120;
                trailWidth=6;
                trailLength=6;
                homingRange=2000;
                homingDelay=3;
                homingPower=0.2f;
                shrinkY=0;
            }
            /**        hitEntity(Bullet ex , Unit, Healthc) {
                    if (other && other.kill) {
                        Call.unitDestroy(u.id)
                    }
                },
                    hitTile(b, tile, x, y, health, direct)  {
                    this.super$hitTile(b, tile, x, y, health, direct) ;
                    if (tile) {
                        tile.killed()
                    }
                }
                );*/
/**
            @Override
            public void hitEntity(Bullet b, Unit other, float initialHealth) {
                if (unit != null && kill)}**/
            };

            envDisabled = Env.none;
            createScorch = false;
            fogRadius *=1000;
            fallSpeed /=2;

            speed = 10f;
            hitSize = 24f;
            accel = 0.1f;
            drag = 0.05f;
            health =1f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f*16f; // high
            lifetime = 3600;
            outlineRadius=0;
            flying = true;
            engineSize=0;
            singleTarget = false;
            rotateSpeed = 20f;
            loopSound = Sounds.flux;
            deathExplosionEffect= NGFx.massiveShockwave;
            fallEffect = fallEngineEffect = new MultiEffect(NGFx.deathCharge,NGFx.deathCharge2,NGFx.deathCharge3);
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
                bullet = new BasicBulletType(16f, 2000){{
                    width = 14f;
                    height = 36f;
                    lifetime = 70f;
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
                            lightningType = new ExplosionBulletType(10*2625f, 80f){{
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
                            damage = 17150f;
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
                                    Lightning.create(b.team, Pal.redLight, damage*3, b.x, b.y, b.rotation() + (6 - Mathf.range(12)), (int)(length/10));};
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
                    splashDamage = 2625f*1000000f;
                    splashDamageRadius= 400;
                    collides=false;
                    absorbable=false;
                    hittable=false;
                    lifetime=240;
                    hitColor = Liquids.cryofluid.color;
                    bulletInterval=3;
                    intervalBullets =6;
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

                    weapons.add(new TurretLarge("nge-personal-mount"){{

                        aimDst = 99999;
                        x= -100f;
                        y= 0f;
                                    mirror = true;
                                    reload/=2;

                    }},
                            new TurretLarge("nge-personal-mount"){{

                                aimDst = 99999;
                                x= -60f;
                                y= -50f;
                                mirror = true;
                                reload/=2;

                            }},new TurretLarge("nge-personal-mount"){{

                                aimDst = 99999;
                                x= -0f;
                                y= -80f;

                            }},
                            new Weapon("orbitbullet"){{
                                reload = 300f;
                                x = 0f;
                                y = 160f;
                                top = false;
                                mirror=false;
                                shootSound= (Sounds.bolt);
                                aimDst=99999;
                                shoot = new ShootMulti
                                        (
                                                new ShootSpread(){{
                                                    shots = 72;
                                                    shotDelay =0.2f;
                                                    spread = 5/2f;
                                                }},
                                                new ShootVertical(){{
                                                    shots = 10;
                                                    shotDelay =9;
                                                    barrels = 10;
                                                    height = 36;
                                                }}
                                        );
                                bullet = new AccelBulletType(0f, 2625){{
                                    velocityBegin = 0.01f;
                                    velocityIncrease = 10f;
                                    accelerateBegin = 0.25f;
                                    accelerateEnd = 0.5f;

                                    despawnHit = true;

                                    keepVelocity = false;
                                    width = 40f;
                                    height = 40f;
                                    lifetime = 240f;
                                    shootEffect = NGFx.orbitbullet;
                                    smokeEffect= NGFx.end;
                                    hitColor = backColor = trailColor = Liquids.cryofluid.color;
                                    frontColor=Color.white;
                                    trailWidth = 1f;
                                    hitEffect = despawnEffect = Fx.bigShockwave;
                                    hitEffect = Fx.hitSquaresColor;
                                    sprite = "large-orb";
                                    shrinkY=0;
                                    accelInterp = Interp.sineIn;
                                    spin = 10f;
                                    hittable=false;
                                    collides = false;

                                    homingPower = 99f;
                                    homingDelay = 237f;
                                    homingRange = 99999;

                                    fragRandomSpread = 0f;
                                    fragBullets =1;
                                    fragVelocityMin = 1;
                                    hitSound = Sounds.railgun;
                                    fragBullet = new RailBulletType(){{
                                        length = 4000f;
                                        damage = 900f*33f;
                                        hitColor = Color.valueOf("feb380");
                                        hitEffect = endEffect = Fx.hitBulletColor;
                                        pierceDamageFactor = 0.8f;

                                        smokeEffect = Fx.colorSpark;

                                        endEffect = new Effect(14f, e -> {
                                            color(Liquids.cryofluid.color);
                                            Drawf.tri(e.x, e.y, e.fout() * 3f, 20f, e.rotation);
                                        });

                                        lineEffect = new Effect(20f, e -> {
                                            if(!(e.data instanceof Vec2 v)) return;

                                            color(Liquids.cryofluid.color);
                                            stroke(e.fout() * 8f);

                                            Fx.rand.setSeed(e.id);
                                            for(int i = 0; i < 14; i++){
                                                Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                                                Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                                            }

                                            e.scaled(14f, b -> {
                                                stroke(b.fout() * 16f);
                                                color(Liquids.cryofluid.color);
                                                Lines.line(e.x, e.y, v.x, v.y);
                                            });
                                        });
                                    }};
                                }};
                            }}


                    );
                    constructor = TimedKillUnit::create;
            abilities.add(new RegenAbility() {{
                percentAmount = 6000;

            }}, new Ability() {

                              @Override
                              public String localized() {
                                  return "Ability";
                              }

                              @Override
                              public void update(Unit unit) {
                                  Groups.bullet.intersect(unit.x - 200, unit.y - 200, 200 * 2, 200 * 2, b -> {
                                      if (b.team != unit.team && unit.within(b, 200)){
                                          @Nullable Unit owner = null;
                                          if(b.owner instanceof Unit) owner = (Unit)b.owner;
                                          if(b.type.damage > unit.maxHealth/2f || b.type.splashDamage > unit.maxHealth/2f){
                                              if(owner != null) owner.kill();
                                              b.remove();
                                          }
                                          if(owner != null && (owner.maxHealth > unit.maxHealth * 2 || owner.type.armor >= unit.type.armor * 2)) owner.kill();

                                          Building building = null;
                                          if(b.owner instanceof Building) building = (Building) b.owner;
                                          if(b.type.damage > unit.maxHealth/2f || b.type.splashDamage > unit.maxHealth/2f){
                                              if(building != null) building.kill();
                                              b.remove();
                                          }
                                          if(building != null && building.health > unit.maxHealth * 2) building.kill();
                                      }
                                  });
                              }
                          }
            );
        }};

    }
}
