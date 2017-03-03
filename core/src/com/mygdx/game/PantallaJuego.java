package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class PantallaJuego extends PantallaBase {
	private SpriteBatch batch;
	private Texture tfondo;
	private Texture tnave;
	private Texture trocket,trocket2,trocket3;
	private Texture tvida;
	private float renderX;
	private float renderY;
	private Asteroide asteroide;
	private Random n;
	private BitmapFont fuente;
	private int y;
	private Vector2 ve;
	private ArrayList<Asteroide> listAsteroids;
	private ArrayList<Texture> vidas;
	private Texture tgameOver;
	private Rectangle bbNave;
	private int nivel;
	int points;
	private String player;
	private int cntVidas;
	private Texture texplosion;
	private boolean naveViva;

	private Texture tfondo1;
	private Texture tfondo6;
	private Texture tfondo5;
	private Texture tfondo2;
	private Texture tfondo3;
	private Texture tfondo4;
	private int numAsteroids;
	private Music gameSound;
	private BitmapFont fplayer;
	private Music collitionSound;
	private Texture tfondofinal;

	public PantallaJuego(MainGame game,String player) {
		super(game);
		this.game=game;
		this.batch=game.getSpriteBatch();
		this.player=player;
	}


	@Override
	public void show() {
		tfondo=new Texture("pantalla.jpg");
		tnave=new Texture("rocket.png");

		tvida=new Texture("heart.png");
		tgameOver=new Texture("gameover.png");
		texplosion=new Texture("explosion.png");

		trocket=new Texture("asteroid.png");
		trocket2=new Texture("asteroid.png");
		trocket3=new Texture("asteroid.png");
		naveViva=true;
		fuente=new BitmapFont(Gdx.files.internal("mifuente.fnt"));
		fplayer=new BitmapFont(Gdx.files.internal("titulo.fnt"));

		tfondo1=new Texture("pantalla.jpg");
		tfondo2=new Texture("pantalla.jpg");
		tfondo3=new Texture("pantalla.jpg");
		tfondo4=new Texture("pantalla.jpg");
		tfondo5=new Texture("pantalla.jpg");
		tfondo6=new Texture("pantalla.jpg");
		tfondofinal=new Texture("pantalla.jpg");

		n=new Random();
		y=n.nextInt(n.nextInt(Gdx.graphics.getHeight()));
		listAsteroids=new ArrayList<Asteroide>();
		vidas=new ArrayList();
		nivel=1;
		points=0;
		cntVidas=3;
		numAsteroids=15;
		bbNave=new Rectangle(renderX,renderY,tnave.getWidth(),tnave.getHeight()-56);
		insertarAsteroides(numAsteroids);

		gameSound=Gdx.audio.newMusic(Gdx.files.internal("gamesound.mp3"));
		collitionSound=Gdx.audio.newMusic(Gdx.files.internal("explosion.mp3"));
		collitionSound.setLooping(false);
		gameSound.setLooping(true);
		gameSound.play();

		//Vidas(cntVidas);
		/*asteroide=new Asteroide(batch,Gdx.graphics.getWidth()-trocket.getWidth(),y,trocket,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		ve=new Vector2(asteroide.getOrigenX(),asteroide.getOrigenY());*/
	}

	private void Vidas(int n) {
		vidas.clear();
		for(int i=0;i<n;i++){
			vidas.add(tvida);
		}
	}


	private void insertarAsteroides(int m) {
		for(int i=0;i<m;i++) {
			switch(n.nextInt(3)){
				case 0:
					listAsteroids.add(new Asteroide(batch, Gdx.graphics.getWidth() + (i *trocket.getWidth() ), (n.nextInt(Gdx.graphics.getHeight())),trocket,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
					break;
				case 1:
					listAsteroids.add(new Asteroide(batch, Gdx.graphics.getWidth()+ (i * trocket2.getWidth()), (n.nextInt(Gdx.graphics.getHeight())),trocket2,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
					break;
				case 2:
					listAsteroids.add(new Asteroide(batch, Gdx.graphics.getWidth() + (i * trocket3.getWidth()), (n.nextInt(Gdx.graphics.getHeight())),trocket3,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
					break;
			}
		}

	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//int vida=cntVidas;
		if(naveViva) {

			if (renderX + Gdx.input.getAccelerometerX() <= 0 && renderX + Gdx.input.getAccelerometerX() >= -(Gdx.graphics.getHeight() - tnave.getHeight())) {
				renderX += Gdx.input.getAccelerometerX();
				bbNave.setY(-1 * renderX);
			}
			if (renderY + Gdx.input.getAccelerometerY() >= 0 && renderY + Gdx.input.getAccelerometerY() <= (Gdx.graphics.getWidth() - tnave.getWidth())) {
				renderY += Gdx.input.getAccelerometerY();
				bbNave.setX(renderY);
			}
		}

		batch.begin();
		switch (nivel){
			case 1:	batch.draw(tfondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;

			case 6:	batch.draw(tfondo1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;

			case 4:	batch.draw(tfondo2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;

			case 3:	batch.draw(tfondo3, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;

			case 5:	batch.draw(tfondo4, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;

			case 7:	batch.draw(tfondo5, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;

			case 2:	batch.draw(tfondo6, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;
			default:batch.draw(tfondofinal, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				break;
		}
		//nave.draw(batch)
		//muestro los primeros asteroides
		for (int i = 0; i < listAsteroids.size(); i++) {
			listAsteroids.get(i).dibujaAsteroiode();
			if(naveViva) {
				listAsteroids.get(i).tex.x -= nivel*2;
				listAsteroids.get(i).setOrigenX((int) listAsteroids.get(i).tex.x);
				listAsteroids.get(i).actualializaRectangulo(listAsteroids.get(i).getOrigenX(), listAsteroids.get(i).getOrigenY());
			}
			//System.out.println("X: "+bbNave.getX() + " Y: "+bbNave.getY());
			//System.out.println("X: "+listAsteroids.get(i).bbAsteroiod.getX()+ " Y: "+listAsteroids.get(i).bbAsteroiod.getY());
			if (listAsteroids.get(i).getBbAsteroiod().overlaps(bbNave)) {
				naveViva=false;
			}
		}
		//dibujo la nave
		batch.draw(tnave, renderY, -renderX);
		//en caso de que se muestren todos lo asteroides que habian, que se generen otros
		if (listAsteroids.get(listAsteroids.size() - 1).tex.x <= Gdx.graphics.getWidth() / 2) {
			//insertarAsteroides(15);
			subeNivel();
		}

			/*asteroide.dibujaAsteroiode();
			asteroide.tex.x--;*/
		fuente.draw(batch, "FPS   " + Math.round(Gdx.graphics.getDeltaTime() * 1000), Gdx.graphics.getWidth()-tnave.getWidth(), 40);
		fuente.draw(batch, "LEVEL   " + nivel, Gdx.graphics.getWidth() - tnave.getWidth(), Gdx.graphics.getHeight() - 10);
		fuente.draw(batch, "POINTS   " + points, 10, Gdx.graphics.getHeight() - 10);
		fplayer.draw(batch, "" + player, (float) (Gdx.graphics.getWidth() / 2 )-player.length()*10, Gdx.graphics.getHeight() - 10);
		if (!naveViva){
			gameOver(batch);
		}else{
			points++;
		}
		if(!collitionSound.isPlaying()){
			collitionSound.stop();
		}
		batch.end();

	}

	private void subeNivel() {
		nivel++;
		numAsteroids+=5;
		insertarAsteroides(numAsteroids);
	}

	private void gameOver(SpriteBatch batch) {
		gameSound.stop();
		collitionSound.play();
		batch.draw(texplosion,renderY,-1*renderX);
		batch.draw(tgameOver, (float) (Gdx.graphics.getWidth()/1.3-tgameOver.getWidth()), (float) (Gdx.graphics.getHeight()/1.5-tgameOver.getHeight()));
		if(Gdx.input.isTouched()){
			collitionSound.stop();
			game.setScreen(new PantallaGameOver(game,player,points));
			gameSound.stop();
		}
		gameSound.stop();


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
	public void dispose () {
		batch.dispose();
		tfondo.dispose();
		tnave.dispose();
		trocket.dispose();
		trocket2.dispose();
		trocket3.dispose();
		tvida.dispose();
		texplosion.dispose();
		tgameOver.dispose();
		tfondo1.dispose();
		tfondo2.dispose();
		tfondo3.dispose();
		tfondo4.dispose();
		tfondo5.dispose();
		tfondo6.dispose();
		gameSound.dispose();
		collitionSound.dispose();
		tfondofinal.dispose();
	}
}
