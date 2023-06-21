package neogenesis.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

import neogenesis.content.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class NGUnitTypes {
        // 1

        // 2

        // 3

        // 4

        // 5
    public static UnitType decade ;
        // 6
        public static void load(){

            decade = new UnitType("5-a-01-decade"){{
                speed = 5f*8f/60;
                hitSize = 10f;
                health = 240;
                weapons.add(new Weapon("weapon"){{
                    reload = 45f;
                    top = false;
                    alternate=false;
                    mirror=false;
//                    ejectEffect = Fx.casing1;
                    shootSound = Sounds.blaster;
                    bullet = new BasicBulletType(6f, 15){{
                        width = 7f;
                        height = 9f;
                        frontColor = Color.white;
                        backColor = NGColor.purtuxe1;
                        lifetime = 20f;
                        shootEffect=new MultiEffect (NGFx.decade1, NGFx.decade1sm);
                    }};
                }});
            }};
        }
}
