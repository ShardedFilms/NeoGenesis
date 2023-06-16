package arc.mock;

import arc.*;

/**
 * The headless backend does its best to mock elements. This is intended to make code-sharing between
 * server and client as simple as possible.
 */
public class MockInput extends Input{
    @Override
    public int mouseX(){
        return 0;
    }

    @Override
    public int mouseX(int pointer){
        return 0;
    }

    @Override
    public int deltaX(){
        return 0;
    }

    @Override
    public int deltaX(int pointer){
        return 0;
    }

    @Override
    public int mouseY(){
        return 0;
    }

    @Override
    public int mouseY(int pointer){
        return 0;
    }

    @Override
    public int deltaY(){
        return 0;
    }

    @Override
    public int deltaY(int pointer){
        return 0;
    }

    @Override
    public boolean isTouched(){
        return false;
    }

    @Override
    public boolean justTouched(){
        return false;
    }

    @Override
    public boolean isTouched(int pointer){
        return false;
    }

    @Override
    public long getCurrentEventTime(){
        return 0;
    }
}
