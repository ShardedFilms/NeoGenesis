package alphaplus.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
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

public class AlphaplusBlocks{
	//list of blocks and environment
	public static Block oven, waterTurbine;
	
	public static void load() {
		oven = new GenericCrafter("oven"){{
			requirements(Category.crafting, with(Items.lead, 45, Items.graphite, 30,Items.silicon,20));
            craftEffect = Fx.fireSmoke;
            outputItem = new ItemStack(Items.graphite, 1);
            craftTime = 90f;
            size = 2;
            hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));

            consumeItems(with(Items.coal, 2));
		}};
		waterTurbine = new ConsumeGenerator("water turbine"){{
			requirements(Category.power, with(Items.lead, 45, Items.graphite, 30,Items.silicon,20));
			liquidCapacity = 5f;
			powerProduction = 5f;
			hasLiquids = true;
			size = 2;
			
			consumeLiquid(Liquids.water,0.2f);
		}};
	}
}