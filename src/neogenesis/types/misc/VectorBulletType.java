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
            Fill.tri(Tmp.v1.x + b.x, Tmp.v1.y + b.y, -Tmp.v1.x + b.x, -Tmp.v1.y + b.y, Tmp.v2.x + b.x, Tmp.v2.y + b.y);
            Draw.color(frontColor);
            Fill.tri(Tmp.v1.x / 2f + b.x, Tmp.v1.y / 2f + b.y, -Tmp.v1.x / 2f + b.x, -Tmp.v1.y / 2f + b.y, Tmp.v2.x / 2f + b.x, Tmp.v2.y / 2f + b.y);
        }
    }
}
