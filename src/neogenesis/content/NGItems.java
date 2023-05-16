package neogenesis.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class NGItems{
    public static Item
    astrolite , scalar, vector,matrix;

//    public static final Seq<Item> serpuloItems = new Seq<>(), erekirItems = new Seq<>(), erekirOnlyItems = new Seq<>();

    public static void load(){
        astrolite = new Item("1-i-0-astrolite", NGColor.genesux3){{
            hardness = 3;
            cost = 1f;
            charge = 0.1f;
        }};
        scalar = new Item("y-i-0-scalar", Color.valueOf("00e676")){{
            hardness = 45;
            cost = 1f;
        }};
        vector = new Item("y-i-1-vector", Color.valueOf("3d5afe")){{
            hardness = 60;
            cost = 2f;
        }};
        matrix = new Item("y-i-2-matrix", Color.valueOf("8c4dff")){{
            hardness = 75;
            cost = 3f;
        }};

    }
}