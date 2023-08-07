package neogenesis.types.misc;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.Vec2;
import arc.math.geom.Vec3;
import arc.util.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
public class VectorBulletType extends BasicBulletType{

    public float fx1=0;
    public float fy1=0;
    public float fx2=4;
    public float fy2=-4;
    public float fx3=0;
    public float fy3=-8;
    public float bx1=0f;
    public float by1=-2f;
    public float bx2=8;
    public float by2=-8;
    public float bx3=0;
    public float by3=16;

    public Vec2 aa = new Vec2(1,1);
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
            Fill.tri((Tmp.v1.x + bx1 )+ b.x, (Tmp.v1.y + by1 )+ b.y, (Tmp.v1.x + bx2 )+ b.x, (Tmp.v1.y + by2 )+ b.y,(Tmp.v1.x + bx3 )+ b.x, (Tmp.v1.y + by3 )+ b.y);
            Draw.color(frontColor);
            Fill.tri((Tmp.v1.x + fx1 )+ b.x, (Tmp.v1.y + fy1 )+ b.y, (Tmp.v1.x + fx2 )+ b.x, (Tmp.v1.y + fy2 )+ b.y,(Tmp.v1.x + fx3 )+ b.x, (Tmp.v1.y + fy3 )+ b.y);
        }
    }
}
