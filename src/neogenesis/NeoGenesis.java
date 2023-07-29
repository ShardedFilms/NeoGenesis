package neogenesis;

import arc.*;
import arc.assets.*;
import arc.assets.loaders.*;
import arc.audio.*;
import arc.files.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.core.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.maps.*;
import mindustry.mod.*;
import mindustry.net.*;
import mindustry.ui.*;
import neogenesis.content.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class NeoGenesis extends Mod{
	
	public NeoGenesis(

        /** Base Category.
         * Blocks
         *
         *         // Base Category.
         *         // 0 = Development
         *         //
         *         // 1 = Genesux
         *         // 2 = Solran
         *         // 3 = Quantra
         *         // 4 = Elecian
         *         // 5 = Purtuxe
         *         // 6 = impirion
         *         // 7 = vyrtian
         *         // 8 = Nebulon
         *         // 9 = Thethia
         *         // 10 = Zetaium
         *         // 11 = Omega
         *         // 12 = Devine
         *         // a = Serpulo(Special)
         *         // b = Gemini
         *         // c = lyra
         *         // d = taurus
         *         // e = Electra + aqua
         *         // f = Erekir(Special)
         *         // g = Assertion
         *         // h = Pink
         *         // i = Silver
         *         // j = orange
         *         // k = Lightblue
         *         // l = accent
         *         //
         *         // y = Sandbox and Persona
         *         // z = Undeeded
         *         //
         *         // Base > Category > index > Content
         *         //
         *         // All content is ordered alphabeticial
         *         // You must sort it yourself.
         *
         *Units
         *0 = Development
         *
         * c1 = Genesux
         * c2 = Solran
         * c3 = Quantra
         * c4 = Elecian
         * c5 = Purtuxe
         * c6 = impirion
         * c7 = vyrtian
         * c8 = Nebulon
         * c9 = Thethia
         * c10 = Zetaium
         * c11 = Omega
         * c12 = Devine
         * a1 = Serpulo
         * a2 = Erekir
         * b1 = customized Normal
         * b2 = customized Advanced
         * b3 = customized High
         * b4 = customized Ultimate
         *
         *
         *
         *
         *
         *
         *
         * y = Sandbox and Persona
         * z = Undeeded
         *
         * Base > Category > index > Content
         *
         * All content is ordered alphabeticial
         * You must sort it yourself. **/
    ){
        Log.info("Loads Constructor.");

    }

    @Override
    public void loadContent(
    ){
        Log.info("Activating Plugin.");

        // First
        NGItems.load();
        NGColor.load();
        NGFx.load();



        // Second
    	NGBlocks.load();
        NGPlanets.load();
        NGUnitTypes.load();


    }
    
}

// Concept Terms Is Complex!
//
// Firstly- ?
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
// At the end you will make "Everything" accessible.