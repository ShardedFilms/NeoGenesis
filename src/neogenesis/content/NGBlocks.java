package neogenesis.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
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
			recoil = 10f;
			drawer = new DrawTurret(){{
				parts.add(new RegionPart("-barrel"){{
					progress = PartProgress.recoil; //Since recoil is 1-0, cut from the start instead of the end.
//				under = false;
					turretHeatLayer = Layer.turret + 0.0001f;
					moveY = -1.5f;
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
					NGItems.tensor,  new BasicBulletType(16f, 20){{
						knockback = 0.8f;
						lifetime = 20f;
						width = 10f;
						height = 15f;
						splashDamageRadius = 3 * 8;
						splashDamage = 66f;
						hitEffect= Fx.blastExplosion;
					}}
			);

			shoot = new ShootAlternate(3.5f);

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


		
