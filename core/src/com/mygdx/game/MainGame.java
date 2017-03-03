package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jose on 18/02/2017.
 */

public class MainGame extends Game {
    SpriteBatch batch;
    PantallaJuego pantallaJuego;
    @Override
    public void create() {
        batch=new SpriteBatch();
        pantallaJuego=new PantallaJuego(this,"player");
        setScreen(pantallaJuego);
    }
    public SpriteBatch getSpriteBatch(){
        return batch;
    }
}
