package  neogenesis.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.units.UnitAssembler.*;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class NGFx{
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

    blank = new Effect(0, 0f, e -> {}),

    astral1 = new Effect(10f, 100f, e -> {
        color(NGColor.graphite2);
        stroke(e.fout() * 4f);

        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2f, 16f * e.fout(), i*90);
        }

        color(NGColor.graphite1);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 1f, 8f * e.fout(), i*90);
        }
        color(NGColor.graphite2);

        e.scaled(6, i -> {
            stroke(2f * i.fout());
            Lines.circle(e.x, e.y, 3f + i.fin() * 20f);
        });
    }),

    astral2 = new Effect(20f, 100f, e -> {
        color(NGColor.genesux3);
        stroke(e.fout() * 4f);

        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2f * e.fout(), 24f * e.fout(), i*90);
        }

        color(NGColor.genesux2);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 1f * e.fout(), 12f * e.fout(), i*90);
        }

        color(Color.white, NGColor.genesux3, e.fin());

        e.scaled(7f, s -> {
            stroke(0.5f + s.fout());
            Lines.circle(e.x, e.y, s.fin() * 5f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 7, e.fin() * 20f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            Fill.square(e.x + x, e.y + y, e.fout() * 3.2f, ang);
        });

        Drawf.light(e.x, e.y, 30f, e.color, 0.6f * e.fout());
    }),

    cosmosBlast = new Effect(300f, 300f, b -> {
        float intensity = 3f;

        color(b.color, 0.7f);
        for(int i = 0; i < 4; i++){
            rand.setSeed(b.id*2 + i);
            float lenScl = rand.random(0.5f, 1f);
            int fi = i;
            b.scaled(b.lifetime * lenScl, e -> {
                randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 22f * intensity, (x, y, in, out) -> {
                    float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                    float rad = fout * ((2f + intensity) * 2.35f);

                    Fill.circle(e.x + x, e.y + y, rad);
                    Drawf.light(e.x + x, e.y + y, rad * 2.5f, b.color, 0.5f);

                    
                });
            });
        }
    }),

    cosmosSpark = new Effect(70f, e -> {
        rand.setSeed(e.id);
        for(int i = 0; i < 13; i++){
            float a = e.rotation + rand.range(30f);
            v.trns(a, rand.random(e.finpow() * 50f));
            e.scaled(e.lifetime * rand.random(0.3f, 1f), b -> {
                color(e.color);
                Lines.stroke(b.fout() * 3f + 0.5f);
                Lines.lineAngle(e.x + v.x, e.y + v.y, a, b.fout() * 8f + 0.4f);
            });
        }
    });


	public static void load() {
	}

}