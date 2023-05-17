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
		requirements(Category.turret, with(Items.copper, 25, Items.graphite, 22,Items.silicon,10));
		ammo(
			Items.pyratite, new BulletType(8f, 80f){{
				ammoMultiplier = 4f;
				hitSize = 7f;
				lifetime = 18f;
				pierce = true;
				collidesAir = false;
				statusDuration = 60f * 10;
				shootEffect = NGFx.blister1;
				hitEffect = Fx.hitFlameSmall;
				despawnEffect = Fx.none;
				status = StatusEffects.burning;
				hittable = false;
			}}
		);
		recoil = 1f;
		drawer = new DrawTurret(){{
			parts.add(new RegionPart("-barrel"){{
				progress = PartProgress.recoil.delay(0.6f); //Since recoil is 1-0, cut from the start instead of the end.
				under = true;
				turretHeatLayer = Layer.turret + 0.0001f;
				moveY = -1.5f;
			}});
		}};
		reload = 6f;
		maxAmmo*=1.5f;
		coolantMultiplier = 1.5f;
		range = 120f;
		shootCone = 50f;
		targetAir = false;
		ammoUseEffect = Fx.none;
		scaledHealth = 150;
		size =3;
		shootSound = Sounds.flame;
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
                }}
            );

            shoot = new ShootAlternate(3.5f);

            shootY = 3f;
            reload = 20f;
            range = 80 * 3.15f;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
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
	
		
