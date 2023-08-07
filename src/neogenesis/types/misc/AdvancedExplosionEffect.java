package neogenesis.types.misc;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.entities.*;
import mindustry.graphics.*;

import neogenesis.graphics.g2d.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;

public class AdvancedExplosionEffect extends Effect{
    public Color waveColor = Pal.missileYellow, smokeColor = Color.gray, sparkColor = Pal.missileYellowBack;
    public float waveLife = 6f, waveStroke = 3f, waveRad = 15f, waveRadBase = 2f, sparkAlpha = 0.7f, sparkStroke = 1f, sparkRad = 23f, sparkMinRad = 1f, sparkLen = 3f, smokeSize = 4f, smokeSizeBase = 0.5f, smokeMinRad = 2f, smokeRad = 23f;
    public int smokes = 5, sparks = 4;
    String region = "circle";

    public AdvancedExplosionEffect(){
        clip = 100f;
        lifetime = 22;

        renderer = e -> {
            color(waveColor);

            e.scaled(waveLife, i -> {
                stroke(waveStroke * i.fout());
                Lines.circle(e.x, e.y, waveRadBase + i.fin() * waveRad);
            });

            color(smokeColor);

            if(smokeSize > 0){
                randLenVectors(e.id, smokes, smokeMinRad + smokeRad * e.finpow(), (x, y) -> {
                    AdFill.circle(e.x + x, e.y + y, e.fout() * smokeSize + smokeSizeBase,region);
                });
            }

            color(sparkColor);
            stroke(e.fout() * sparkStroke);

            randLenVectors(e.id + 1, sparks, sparkMinRad , 0+sparkRad * e.finpow(), (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * sparkLen);
                Drawf.light(e.x + x, e.y + y, e.fout() * sparkLen * 4f, sparkColor, sparkAlpha);
            });
        };
    }
}
