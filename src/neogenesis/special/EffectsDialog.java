package neogenesis.special;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.scene.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;

import java.lang.reflect.*;

public class EffectsDialog extends BaseDialog{
    static BoundsBatch bounds = new BoundsBatch();

    public EffectsDialog(){
        super("Effects");

        closeOnBack();
        onResize(this::setup);

        setup();
    }

    void setup(){
        float size = 300f;
        int cols = (int)Math.max(1, Core.graphics.getWidth() / Scl.scl(size + 12f));

        Field[] fields = Fx.class.getFields();

        cont.clear();
        cont.pane(t -> {
            int i = 0;
            for(Field f : fields){
                if(f.getType() != Effect.class) continue;

                Effect e = Reflect.get(f);
                float bounds = calculateSize(e);

                if(bounds <= 0) continue;

                t.stack(new EffectCell(e), new Table(af -> af.add(f.getName()).grow().labelAlign(Align.bottomLeft).style(Styles.outlineLabel).bottom().left())).size(size);

                if(++i % cols == 0) t.row();
            }
        }).grow().scrollX(false);
    }

    static float calculateSize(Effect effect){
        try{
            effect.init();
            Batch prev = Core.batch;
            bounds.reset();
            Core.batch = bounds;

            float lifetime = effect.lifetime;
            float rot = 1f;
            int steps = 60;
            int seeds = 4;
            for(int s = 0; s < seeds; s++){
                for(int i = 0; i <= steps; i++){
                    effect.render(1, Color.white, i / (float)steps * lifetime, lifetime, rot, 0f, 0f, null);
                }
            }

            Core.batch = prev;

            return bounds.max * 2f;
        }catch(Exception e){
            //might crash with invalid data
            return -1f;
        }
    }

    static class BoundsBatch extends Batch{
        float max;

        void reset(){
            max = 0f;
        }

        void max(float... xs){
            for(float f : xs){
                if(Float.isNaN(f)) continue;
                max = Math.max(max, Math.abs(f));
            }
        }

        @Override
        protected void draw(Texture texture, float[] spriteVertices, int offset, int count){
            for(int i = offset; i < count; i += SpriteBatch.VERTEX_SIZE){
                max(spriteVertices[i], spriteVertices[i + 1]);
            }
        }

        @Override
        protected void draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float rotation){
            float worldOriginX = x + originX;
            float worldOriginY = y + originY;
            float fx = -originX;
            float fy = -originY;
            float fx2 = width - originX;
            float fy2 = height - originY;
            float cos = Mathf.cosDeg(rotation);
            float sin = Mathf.sinDeg(rotation);
            float x1 = cos * fx - sin * fy + worldOriginX;
            float y1 = sin * fx + cos * fy + worldOriginY;
            float x2 = cos * fx - sin * fy2 + worldOriginX;
            float y2 = sin * fx + cos * fy2 + worldOriginY;
            float x3 = cos * fx2 - sin * fy2 + worldOriginX;
            float y3 = sin * fx2 + cos * fy2 + worldOriginY;

            max(x1, y1, x2, y2, x3, y3, x1 + (x3 - x2), y3 - (y2 - y1));
        }

        @Override
        protected void flush(){}
    }

    static class EffectCell extends Element{
        Effect effect;
        float size = -1f;

        int id = 1;
        float time = 0f;
        float lifetime;
        float rotation = 1f;
        Object data = null;

        public EffectCell(Effect effect){
            this.effect = effect;
            this.lifetime = effect.lifetime;
        }

        @Override
        public void draw(){
            if(size < 0){
                size = calculateSize(effect) + 1f;
            }

            color.fromHsv((Time.globalTime) % 360f, 1f, 1f);

            if(clipBegin(x, y, width, height)){
                Draw.colorl(0.5f);
                Tex.alphaBg.draw(x, y, width, height);
                Draw.reset();
                Draw.flush();

                float scale = width / size;
                Tmp.m1.set(Draw.trans());
                Draw.trans().translate(x + width/2f, y + height/2f).scale(scale, scale);
                Draw.flush();
                this.lifetime = effect.render(id, color, time, lifetime, rotation, 0f, 0f, data);

                Draw.flush();
                Draw.trans().set(Tmp.m1);
                clipEnd();
            }

            Lines.stroke(Scl.scl(3f), Color.black);
            Lines.rect(x, y, width, height);
            Draw.reset();
        }

        @Override
        public void act(float delta){
            super.act(delta);

            time += Time.delta;
            if(time >= lifetime){
                id ++;
            }
            time %= lifetime;
        }
    }
}
