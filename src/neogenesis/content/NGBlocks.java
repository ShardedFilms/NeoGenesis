package neogenesis.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.Tmp;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import neogenesis.types.misc.Kill;
import neogenesis.types.misc.VectorBulletType;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class NGBlocks{
	//list of blocks and environment
	public static Block largeRadar ,

	//genesux
//	astral,

	blister,
	// do not sort
	test;


	public static void load() {
		largeRadar = new Radar("0-d-1-large-radar"){{
			requirements(Category.effect, BuildVisibility.fogOnly, with(Items.silicon, 80, Items.graphite, 100, Items.tungsten, 40, Items.oxide , 20));
			outlineColor = Pal.darkOutline;
			fogRadius = 50;
			scaledHealth = 60;
			discoveryTime = 60f * 12f;
			size = 2;
			researchCost = with(Items.silicon, 320, Items.tungsten, 120,Items.beryllium, 150);
			consumePower(1f);
		}};
		blister = new ItemTurret("a-t-04-01-blister"){{
			requirements(Category.turret, with(Items.copper, 80, Items.graphite, 60,Items.silicon,40,Items.titanium,40));
			ammo(
					Items.pyratite, new BulletType(8f, 100f){{
						ammoMultiplier = 4f;
						hitSize = 7f;
						lifetime = 15f;
						pierce = true;
						collidesAir = true;
						statusDuration = 60f * 13;
						shootEffect = NGFx.blister1;
						hitEffect = Fx.hitFlameSmall;
						splashDamageRadius= 24;
						splashDamage= damage/5f;
						despawnHit = false;
						despawnEffect = Fx.none;
						status = StatusEffects.burning;
						hittable = false;
					}},
					NGItems.vector, new BulletType(8f, 120f){{
						ammoMultiplier = 4f;
						hitSize = 7f;
						lifetime = 15f;
						pierce = true;
						collidesAir = true;
						statusDuration = 60f * 13;
						shootEffect = NGFx.blister2;
						hitEffect = NGFx.blister2h;
						splashDamageRadius= 24;
						splashDamage= damage/5f;
						despawnHit = false;
						despawnEffect = Fx.none;
						status = StatusEffects.freezing;
						hittable = false;
					}}
			);
			recoil = 2f;
			drawer = new DrawTurret(){{
				parts.add(new RegionPart("-barrel"){{
					progress = PartProgress.recoil; //Since recoil is 1-0, cut from the start instead of the end.
//				under = false;
					turretHeatLayer = Layer.turret + 0.0001f;
					moveY = -2f;
				}});
			}};
			reload = 5f;
			maxAmmo*=1.5f;
			range = 120f;
			shootCone = 50f;
			targetAir = true;
			ammoUseEffect = Fx.none;
			scaledHealth = 180;
			size =3;
			shootSound = Sounds.flame;
			rotateSpeed*= 0.7f;
		}};

		test = new ItemTurret("z-z-z-test"){{
			requirements(Category.turret, with( NGItems.scalar, 140 ,NGItems.vector, 120));
			ammo(
					Items.copper,  new ArtilleryBulletType(3f, 20){{
						knockback = 0.8f;
						lifetime = 80f;
						width = height = 11f;
						collidesTiles = false;
						splashDamageRadius = 25f * 0.75f;
						splashDamage = 44f;
						hitEffect= new MultiEffect(NGFx.cosmosBlast, NGFx.cosmosSpark);
					}},
					NGItems.tensor,  new ArtilleryBulletType(6f, 800, "shell"){{
						rangeChange = 280;
						hitEffect = Fx.blastExplosion;
						knockback = 0.8f;
						lifetime = 80f;
						width = height = 21f;
						collidesTiles = false;
						ammoMultiplier = 4f;
						splashDamageRadius = 10f*8f;
						splashDamagePierce = true;
						splashDamage = 55f;
						backColor = Pal.missileYellowBack;
						frontColor = Pal.missileYellow;

						status = StatusEffects.blasted;
						reloadMultiplier =2f;
					}
						public void createSplashDamage(Bullet b, float x, float y){
							super.createSplashDamage(b,x,y);
							if(splashDamageRadius > 0 && !b.absorbed){
								Kill.absorb(b.team, x, y, splashDamageRadius, splashDamage * b.damageMultiplier(), splashDamagePierce, collidesAir, collidesGround, scaledSplashDamage, b);


							}
						}
						public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){

							super.hitTile(b,build,x,y,initialHealth,direct);
							if(makeFire && build.team != b.team){
								Fires.create(build.tile);
								build.kill();
							}}
					},
					NGItems.matrix,  new BasicBulletType(10f, 200){{
						rangeChange = 360;
						hitEffect = Fx.shootSmokeTitan;
						knockback = 0f;
						lifetime = 40f;
						width = height = 8f;
						collidesTiles = true;
						ammoMultiplier = 4f;
						splashDamageRadius = 10f*8f;
						splashDamagePierce = true;
						splashDamage = 55f;
						backColor = Pal.sapBullet;
						frontColor = Color.white;
						hitColor = Pal.sapBullet;
						trailLength = 15;
						trailWidth =2f;
						reloadMultiplier =4f;
					}
						@Override
						public void draw(Bullet b){
							drawTrail(b);
							Tmp.v1.trns(b.rotation(), height / 2f);
							for(int s : Mathf.signs){
								Tmp.v2.trns(b.rotation() - 90f, width * s, -height);
								Draw.color(backColor);
								Fill.tri(Tmp.v1.x + b.x, Tmp.v1.y + b.y, -Tmp.v1.x + b.x, -Tmp.v1.y + b.y, Tmp.v2.x + b.x, Tmp.v2.y + b.y);
								Draw.color(frontColor);
								Fill.tri(Tmp.v1.x / 2f + b.x, Tmp.v1.y / 2f + b.y, -Tmp.v1.x / 2f + b.x, -Tmp.v1.y * 2f + b.y, Tmp.v2.x / 2f + b.x,  Tmp.v2.y / 2f + b.y);
							}
						}
					}
			);

			shootY = 3f;
			reload = 20f;
			range = 80 * 3.15f;
			shootCone = 15f;
			ammoUseEffect = Fx.none;
			health = 250;
			inaccuracy = 2f;
			rotateSpeed = 10f;
			coolant = consumeCoolant(0.1f);
			researchCostMultiplier = 0.05f;
			scaledHealth=160;
			size=2;

			buildVisibility = (BuildVisibility.sandboxOnly);
		}};

	}
}


		
