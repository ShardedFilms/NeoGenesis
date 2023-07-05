package neogenesis.types.misc;

import mindustry.entities.pattern.*;
import arc.util.*;

public class ShootVertical extends ShootPattern{
    /** number of barrels used for shooting. */
    public int barrels = 2;
    /** spread between barrels, in world units - not degrees. */
    public float height = 5f;
    /** offset of barrel to start on */
    public int barrelOffset = 0;

    public ShootVertical(float spread){
        this.height = spread;
    }

    public ShootVertical(){
    }

    @Override
    public void shoot(int totalShots, BulletHandler handler, @Nullable Runnable barrelIncrementer){
        for(int i = 0; i < shots; i++){
            float index = ((totalShots + i + barrelOffset) % barrels) - (barrels-1)/2f;
            handler.shoot(0, index * height, 0f, firstShotDelay + shotDelay * i);
            if(barrelIncrementer != null) barrelIncrementer.run();
        }
    }
}