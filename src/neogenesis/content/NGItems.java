package neogenesis.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class NGItems{
    public static Item
    astrolite , scalar;

//    public static final Seq<Item> serpuloItems = new Seq<>(), erekirItems = new Seq<>(), erekirOnlyItems = new Seq<>();

    public static void load(){
        astrolite = new Item("1-i-0-astrolite", NGColor.genesux3){{
            hardness = 3;
            cost = 1f;
            charge = 0.1f;
        }};
        scalar = new Item("y-i-0-scalar", Color.valueOf("00e676")){{
            hardness = 20;
            cost = 10f;
        }};

    }
}