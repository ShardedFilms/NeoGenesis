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
        color(NGColor.graphite);
        stroke(e.fout() * 4f);
        Lines.circle(e.x, e.y, 4f + e.finpow() * 20f);

        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2f, 16f * e.fout(), i*90);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 1f, 8f * e.fout(), i*90);
        }
        color(NGColor.graphite);

        e.scaled(6, i -> {
            stroke(2f * i.fout());
            Lines.circle(e.x, e.y, 3f + i.fin() * 20f);
        });
    }),

    astral2 = new Effect(20f, 100f, e -> {
        color(NGColor.genesux1);
        stroke(e.fout() * 4f);
        Lines.circle(e.x, e.y, 4f + e.finpow() * 20f);

        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2f, 24f * e.fout(), i*90);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 1f, 12f * e.fout(), i*90);
        }

        color(NGColor.genesux1);
        e.scaled(10, i -> {
            stroke(2f * i.fout());
            Lines.circle(e.x, e.y, 3f + i.fin() * 32f);
        });
    });


	public static void load() {
	}

}