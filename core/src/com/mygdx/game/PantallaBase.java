package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * Created by jose on 18/02/2017.
 */

public abstract class PantallaBase implements Screen {
    MainGame game;
    public PantallaBase(MainGame game){
        this.game=game;
    }
}
