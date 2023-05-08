package neogenesis.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class NGBlocks{
	//list of blocks and environment
	public static Block largeRadar ,

	//genesux
	astral, 
	
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
		        astral = new ItemTurret("1-t-01-astral"){{
            requirements(Category.turret, with(Items.copper,30, Items.lead,50));
			ammo(
                Items.graphite,  new BasicBulletType(8f, 10){{
                    width = 9f;
                    height = 9f;
                    lifetime = 13f;
					absorbable = true;
                    ammoMultiplier = 2;
					reloadMultiplier= 1.5f;
					despawnHit=true;
					trailParam= 2f;
					trailLength= 8;
					trailWidth= 2f;
					trailColor= backColor = hitColor = (NGColor.graphite1);
					frontColor= (Color.white);
					hitEffect=(NGFx.astral1);
					shrinkY = shrinkX =0;
					despawnEffect = (Fx.none);
					shootEffect= (Fx.shootSmallColor);
                }},
				NGItems.astrolite,  new BasicBulletType(8f, 10){{
                    width = 9f;
                    height = 9f;
                    lifetime = 13f;
					absorbable = true;
                    ammoMultiplier = 2;
					reloadMultiplier= 1.5f;
					despawnHit=true;
					trailParam= 2f;
					trailLength= 8;
					trailWidth= 2f;
					trailColor= backColor = hitColor = (NGColor.graphite1);
					frontColor= (Color.white);
					hitEffect=(NGFx.astral2);
					shrinkY = shrinkX =0;
					despawnEffect = (Fx.none);
					shootEffect= (Fx.shootSmallColor);
                }}
            );
            shootY = 3f;
            reload = 40f;
            range = 100;
			maxAmmo=10;
            shootCone = 15f;
            ammoUseEffect = Fx.none;
            inaccuracy = 0f;
            rotateSpeed = 10f;
            coolant = consumeCoolant(0.2f);
			coolantMultiplier *= 0.5f;
            researchCostMultiplier = 0.1f;
			recoil = 0.7f;
			shootSound = (Sounds.bang);
			scaledHealth=200;
			size=1;
        }};


	        test = new ItemTurret("z-z-z-test"){{
            requirements(Category.turret, with( NGItems.scalar, 120 ,Items.copper, 120));
			ammo(
                Items.copper,  new ArtilleryBulletType(3f, 20){{
                    knockback = 0.8f;
                    lifetime = 80f;
                    width = height = 11f;
                    collidesTiles = false;
                    splashDamageRadius = 25f * 0.75f;
                    splashDamage = 33f;
                }}
            );

            shoot = new ShootAlternate(3.5f);

            shootY = 3f;
            reload = 20f;
            range = 110;
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
	
		
