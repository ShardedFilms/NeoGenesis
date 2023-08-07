package neogenesis.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class NGItems{
    public static Item
    
    
    
    
    
    scalar, vector, matrix,tensor;

//    public static final Seq<Item> serpuloItems = new Seq<>(), erekirItems = new Seq<>(), erekirOnlyItems = new Seq<>();

    public static void load(){
/**        astrolite = new Item("1-i-0-astrolite", NGColor.genesux3){{
            hardness = 3;
            cost = 1f;
            charge = 0.1f;
        }};**/
        scalar = new Item("y-i-0-scalar", Color.valueOf("00e676")){{
            cost = 2f;
        }};
        vector = new Item("y-i-1-vector", Color.valueOf("3d5afe")){{
            cost = 3f;
        }};
        matrix = new Item("y-i-2-matrix", Color.valueOf("8c4dff")){{
            cost = 4f;
        }};
        tensor = new Item("y-i-3-tensor", Color.valueOf("ffc400")){{
            cost = 5f;
        }};

    }
}