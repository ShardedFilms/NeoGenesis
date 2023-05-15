package neogenesis.content;

import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.Planets;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;

public class NGPlanets {

    public static Planet
    // one
    siravax;

    public static void load(

    // Planetor's Section
    // We gonna make Newtron Star...
    // ???
    // ???
    // Planets.

    ){

        siravax = new Planet("1-pt-0-siravax", Planets.sun, 3f){{
            bloom = true;
            accessible = true;

            meshLoader = () -> new SunMesh(
                this, 8,
                5, 0.3, 1.7, 1.2, 1,
                1.1f,
                NGColor.genesux1,
                NGColor.genesux1.lerp(NGColor.genesux2, 0.5f),
                NGColor.genesux2,
                NGColor.genesux2.lerp(NGColor.genesux2, 0.5f),
                NGColor.genesux3
            );
        }};
 

    }


    
}
