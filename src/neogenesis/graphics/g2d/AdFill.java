package neogenesis.graphics.g2d;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;

import static arc.Core.atlas;

public class AdFill{
    private static TextureRegion circleRegion;
    public static void circle(float x, float y, float radius , TextureRegion circle){



//        if(circleRegion == null || circleRegion.texture.isDisposed()){
//            circleRegion = atlas.find(circle);
//        }

        Draw.rect(circle, x, y, radius * 2, radius * 2);
    }
}