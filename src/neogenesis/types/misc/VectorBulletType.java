package neogenesis.types.misc;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
public class VectorBulletType extends BasicBulletType{

    float fx1=0;
    float fy1=0;
    float fx2=1;
    float fy2=-1;
    float fx3=0;
    float fy3=-2;
    float bx1=0;
    float by1=-0.5f;
    float bx2=2;
    float by2=-2;
    float bx3=0;
    float by3=4;
    public VectorBulletType(float speed, float damage){
        super(speed, damage);
    }

    @Override
    public void draw(Bullet b){
        drawTrail(b);
        Tmp.v1.trns(b.rotation(), height / 2f);
        for(int s : Mathf.signs){
            Tmp.v2.trns(b.rotation() - 90f, width * s, -height);
            Draw.color(backColor);
            Fill.tri(bx1 + b.x, by1 + b.y, bx2 + b.x, by2 + b.y, bx3 + b.x, by3 + b.y);
            Draw.color(frontColor);
            Fill.tri(fx1 + b.x, fy1 + b.y, fx2 + b.x, fy2 + b.y, fx3 + b.x, fy3 + b.y);
        }
    }
}
