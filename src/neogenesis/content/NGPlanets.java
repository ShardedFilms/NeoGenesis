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
    siravax,siratla;

    public static void load(

    // Planetor's Section
    // We will make Newtron Star...
    // ???
    // ???
    // Planets.

    ){

        siravax = new Planet("1-pt-0-siravax", Planets.sun, 3f){{
            bloom = true;
            accessible = true;

            meshLoader = () -> new SunMesh(
                this, 6,
                5, 0.3, 0.1, 1.2, 1,
                1.1f,
                NGColor.genesux1,
                NGColor.genesux1.lerp(NGColor.genesux2, 0.5f),
                NGColor.genesux2,
                NGColor.genesux2.lerp(NGColor.genesux3, 0.5f),
                NGColor.genesux3
            );
        }};
        siratla = new Planet("z-pt-z", siravax , 1f, 2){{
            generator = new SiratlaPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
//            cloudMeshLoader = () -> new MultiMesh(
//                new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Pal.spore).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
//                new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Pal.spore, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
//            );

            launchCapacityMultiplier = 0.5f;
            sectorSeed = 1;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            //doesn't play well with configs
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.blue;
                r.placeRangeCheck = false;
                r.showSpawns = true;
            };
            iconColor = NGColor.genesux2;
            atmosphereColor = NGColor.genesux3;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 2;
            alwaysUnlocked = true;
            landCloudColor = NGColor.genesux3.cpy().a(0.5f);
        }};

 

    }


    
}
