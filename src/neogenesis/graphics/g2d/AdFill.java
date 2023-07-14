package neogenesis.graphics.g2d;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;

import static arc.Core.atlas;

public class AdFill{
    private static TextureRegion circle;
    public static void circle(float x, float y, float radius, String sprite){



        if(circle == null || circle.texture.isDisposed()){
            circle = atlas.find(sprite);
        }

        Draw.rect(circle, x, y, radius * 2, radius * 2);
    }
}