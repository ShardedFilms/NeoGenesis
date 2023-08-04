package neogenesis.types.misc;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.ContinuousBulletType;
import mindustry.gen.*;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;

public class ContinuousBeamBulletType extends ContinuousBulletType {
    public float lightStroke = 40f;
    public float width = 3.7f, oscScl = 1.2f, oscMag = 0.02f;
    public int divisions = 25;
    public float fadeTime = 16f;
    public float strokeFrom = 2f, strokeTo = 0.5f, pointyScaling = 0.75f;
    public float backLength = 7f, frontLength = 35f;
    public Interp lengthInterp = Interp.slope;


    public Color[] colors = {Color.valueOf("eb7abe").a(0.55f), Color.valueOf("e189f5").a(0.7f), Color.valueOf("907ef7").a(0.8f), Color.valueOf("91a4ff"), Color.white.cpy()};

    public ContinuousBeamBulletType(float damage){
        this.damage = damage;
    }

    public ContinuousBeamBulletType(){
    }

    {
        optimalLifeFract = 0.5f;
        length = 120f;
        hitEffect = Fx.hitFlameBeam;
        hitSize = 4;
        drawSize = 420f;
        lifetime = 16f;
        hitColor = colors[1].cpy().a(1f);
        lightColor = hitColor;
        laserAbsorb = false;
        ammoMultiplier = 1f;
        pierceArmor = true;
    }

    @Override
    public void draw(Bullet b){
        float realLength = Damage.findLaserLength(b, length);
        float fout = Mathf.clamp(b.time > b.lifetime - fadeTime ? 1f - (b.time - (lifetime - fadeTime)) / fadeTime : 1f);
        float baseLen = realLength * fout;
        float rot = b.rotation();

        for(int i = 0; i < colors.length; i++){
            Draw.color(Tmp.c1.set(colors[i]).mul(1f + Mathf.absin(Time.time, 1f, 0.1f)));

            float colorFin = i / (float)(colors.length - 1);
            float baseStroke = Mathf.lerp(strokeFrom, strokeTo, colorFin);
            float stroke = (width + Mathf.absin(Time.time, oscScl, oscMag)) * fout * baseStroke;
            float ellipseLenScl = Mathf.lerp(1 - i / (float)(colors.length), 1f, pointyScaling);

            Lines.stroke(stroke);
            Lines.lineAngle(b.x, b.y, rot, baseLen - frontLength, false);

            //back ellipse
            Drawf.flameFront(b.x, b.y, divisions, rot + 180f, backLength, stroke / 2f);

            //front ellipse
            Tmp.v1.trnsExact(rot, baseLen - frontLength);
            Drawf.flameFront(b.x + Tmp.v1.x, b.y + Tmp.v1.y, divisions, rot, frontLength * ellipseLenScl, stroke / 2f);
        }

        Tmp.v1.trns(b.rotation(), baseLen * 1.1f);

        Drawf.light(b.x, b.y, b.x + Tmp.v1.x, b.y + Tmp.v1.y, lightStroke, lightColor, 0.7f);
        Draw.reset();
    }

    @Override
    public float currentLength(Bullet b){
        return length * b.fin(lengthInterp);
    }

    @Override
    public void drawLight(Bullet b){
        //no light drawn here
    }

}