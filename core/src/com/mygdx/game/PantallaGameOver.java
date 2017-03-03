package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

/**
 * Created by jose on 18/02/2017.
 */

public class PantallaGameOver extends PantallaBase {
    private int puntuacion;
    private String jugador;
    MainGame game;
    SpriteBatch batch;
    private Texture tgameOver;
    private BitmapFont fpuntuacion;
    private Music gameSound;
    private TextButton startButton;
    private Skin skin;
    private Stage stage;
    private BitmapFont fDatos;
    private String titulo;

    public PantallaGameOver(MainGame game, String jugador, int puntuacion) {
        super(game);
        this.game=game;
        this.batch=game.getSpriteBatch();
        this.jugador=jugador;
        this.puntuacion=puntuacion;
        titulo="Top  5  Puntuaciones";
    }

    @Override
    public void show() {
        tgameOver=new Texture("gameover.jpg");
        fpuntuacion=new BitmapFont(Gdx.files.internal("puntuacion.fnt"));
        fDatos=new BitmapFont(Gdx.files.internal("titulo.fnt"));


        gameSound=Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3"));
        gameSound.setLooping(true);
        gameSound.play();

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        startButton = new TextButton("Try Again",skin);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                abreJuego();
                event.stop();
            }
        });
        startButton.setPosition((float) (Gdx.graphics.getWidth()-350),50);
        startButton.setSize(300,100);

        stage.addActor(startButton);
        InputMultiplexer im = new InputMultiplexer(stage);
        Gdx.input.setInputProcessor(im);
    }

    private void abreJuego() {
        gameSound.stop();
        game.setScreen(new PantallaJuego(game,jugador.toString()));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            batch.draw(tgameOver, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

            fpuntuacion.draw(batch,"Puntuacion",Gdx.graphics.getWidth()/5, (float) (Gdx.graphics.getHeight()/1.8));
            fpuntuacion.draw(batch,""+jugador, (float) (Gdx.graphics.getWidth()/7), (float) (Gdx.graphics.getHeight()/1.8-200));
            fpuntuacion.draw(batch,""+puntuacion, (float) (Gdx.graphics.getWidth()/3), (float) (Gdx.graphics.getHeight()/1.8-200));

            //fDatos.draw(batch,titulo,Gdx.graphics.getWidth()-titulo.length()*33,Gdx.graphics.getHeight()-titulo.length()*15);


        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        gameSound.pause();
    }

    @Override
    public void resume() {
        gameSound.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameSound.dispose();
        tgameOver.dispose();
        fpuntuacion.dispose();
        stage.dispose();
        fDatos.dispose();
    }
}
