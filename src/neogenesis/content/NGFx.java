package  neogenesis.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.Liquids;
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
            Drawf.tri(e.x, e.y, 2f * e.fout(), 16f * e.fout(), i*90);
        }

        color(NGColor.graphite1);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 1f * e.fout(), 8f * e.fout(), i*90);
        }
        color(NGColor.graphite2);

        e.scaled(6, i -> {
            stroke(2f * i.fout());
            Lines.circle(e.x, e.y, 3f + i.fin() * 20f);
        });
    }),



    astral2 = new Effect(40f, 100f, e -> {
        color(NGColor.genesux3);
        stroke(e.fout() * 4f);

        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 4f * e.fout(), 24f * e.fout(), i*90);
        }

        color(NGColor.genesux2);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2f * e.fout(), 12f * e.fout(), i*90);
        }

        color(Color.white, NGColor.genesux3, e.fin());

        e.scaled(30f, s -> {
            stroke(2f + s.fout());
            Lines.circle(e.x, e.y, 4f + s.fin() * 24f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 7, e.fin() * 20f, (x, y) -> {
            Fill.square(e.x + x, e.y + y, e.fout() * 3.2f, 45);
        });

        Drawf.light(e.x, e.y, 30f, e.color, 0.6f * e.fout());
    }),

    cosmosBlast = new Effect(90f, 60f, b -> {
        float intensity = 1.5f;

        color(NGColor.genesux3, 0.7f);
        for(int i = 0; i < 12; i++){
            rand.setSeed(b.id*2 + i);
            float lenScl = rand.random(0.5f, 1f);
            int fi = i;
            b.scaled(b.lifetime * lenScl, e -> {
                randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(0.9f * intensity), 24f * intensity, (x, y, in, out) -> {
                    float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                    float rad = fout * ((1f + intensity) * 2.35f);

                    Fill.circle(e.x + x, e.y + y, rad);
                    Drawf.light(e.x + x, e.y + y, rad * 2.5f, b.color, 0.5f);

                    
                });
            });
        }
    }),

    cosmosSpark = new Effect(35f, e -> {
        rand.setSeed(e.id);
        for(int i = 0; i < 12; i++){
            float a = e.rotation + rand.range(40f);
            v.trns(a, rand.random(e.finpow() * 50f));
            e.scaled(e.lifetime * rand.random(0.3f, 1f), b -> {
                color(NGColor.genesux3);
                Lines.stroke(b.fout() * 3f + 0.5f);
                Lines.lineAngle(e.x + v.x, e.y + v.y, a, b.fout() * 8f + 0.4f);
            });
        }
    }),
    blister1 = new Effect(44f, 120f, e -> {
        color(Pal.lightPyraFlame, Pal.darkPyraFlame, Color.gray, e.fin());

        randLenVectors(e.id, 28, e.finpow() * 128f, e.rotation, 20f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.0f + e.fout() * 2.4f);
        });
    }),
    blister2 = new Effect(44f, 120f, e -> {
        color(Liquids.cryofluid.gasColor, Liquids.cryofluid.color, Color.gray, e.fin());

        randLenVectors(e.id, 28, e.finpow() * 128f, e.rotation, 20f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.0f + e.fout() * 2.4f);
        });
    }),
    blister2h = new Effect(14, e -> {
        color(Liquids.cryofluid.gasColor, Liquids.cryofluid.color, e.fin());
        stroke(0.5f + e.fout());

        randLenVectors(e.id, 2, 1f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });
    }),
            decade1 = new Effect(10, e -> {
                color(NGColor.purtuxe2, NGColor.purtuxe1, e.fin());
                float w = 1f + 5 * e.fout();
                Drawf.tri(e.x, e.y, w, 16f * e.fout(), e.rotation);
                Drawf.tri(e.x, e.y, w, 4f * e.fout(), e.rotation + 180f);
            }),

    decade1sm = new Effect(25f, e -> {
        color(NGColor.purtuxe2, NGColor.purtuxe1, Color.gray, e.fin());

        randLenVectors(e.id, 7, e.finpow() * 10f, e.rotation, 45f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 1.75f);
        });
    }),



    hlaserxplosion = new Effect(30f, 160f, e -> {
        color(e.color);
        stroke(e.fout() * 5f);
        float circleRad = 6f + e.finpow() * 120f;
        Lines.circle(e.x, e.y, circleRad);

        color(e.color, e.foutpow());
        Fill.circle(e.x, e.y, circleRad);

        rand.setSeed(e.id);
        for(int i = 0; i < 32; i++){
            float angle = rand.random(360f);
            float lenRand = rand.random(0.5f, 3f);
            Tmp.v1.trns(angle, circleRad);

            for(int s : Mathf.signs){
                Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 40f, e.fout() * 30f * lenRand + 6f, angle + 90f + s * 90f);
            }
        }
    }).layer(Layer.bullet + 2f),
            massiveSmoke = new Effect(300f, 300f, b -> {
                float intensity = 3f;

                color(b.color, 0.7f);
                for(int i = 0; i < 8; i++){
                    rand.setSeed(b.id*2 + i);
                    float lenScl = rand.random(0.5f, 1f);
                    int fi = i;
                    b.scaled(b.lifetime * lenScl, e -> {
                        randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 80f * intensity, (x, y, in, out) -> {
                            float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                            float rad = fout * ((1f + intensity) * 2.35f);

                            Fill.circle(e.x + x, e.y + y, rad);
                            Drawf.light(e.x + x, e.y + y, rad * 2.5f, b.color, 0.5f);
                        });
                    });
                }
            }),
            massiveShockwave = new Effect(30f, 80f, e -> {
                for(int i = 0; i < 10; i++){
                    float radi = i*20;
                    color(Color.white, Color.lightGray, e.fin());
                    stroke(e.fout() * 9f);
                    Lines.circle(e.x, e.y, e.fin() * 500f+radi);
                }
            }),
    end = new Effect(0, 0f, e -> {});


	public static void load() {
//
//            Particle is only needed Region.
//
	}

}