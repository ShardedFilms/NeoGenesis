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

public class NGBullets {
    public static BulletType

    astral1, astral2,
    // ended up ???
    remove;

    public static void load(){

        astral1 =new BasicBulletType(8f, 10){{
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
        }};

        astral2 = new BasicBulletType(8f, 10){{
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
        }};

        remove = new BulletType(0, 0);

    }

}
