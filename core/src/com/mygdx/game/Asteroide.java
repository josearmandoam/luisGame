package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jose on 17/02/2017.
 */

public class Asteroide {
    private Batch batch;
    private float origenX,origenY;
    private Texture imagen;
    private int xMax,yMax;
    Rectangle bbAsteroiod;
    private int velocidad;
    public Vector2 tex;
    public Asteroide(Batch batch, int origenX, int origenY, Texture imagen, int xMax, int yMax) {
        this.batch = batch;
        this.origenX = origenX;
        this.origenY = origenY;
        this.imagen = imagen;
        this.xMax = xMax;
        this.yMax = yMax;
        this.bbAsteroiod=new Rectangle(origenX,origenY,imagen.getWidth()-20,imagen.getHeight()-40);
        velocidad=50;
        tex=new Vector2(origenX,origenY);
    }
    public void setVelocidad(int n){
        this.velocidad=n;
    }
    public void dibujaAsteroiode(){
        //Animation an=new Animation(0.1f)
        if(batch.isDrawing()) {
            batch.draw(imagen, tex.x, tex.y);
        }
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public float getOrigenX() {
        return origenX;
    }

    public void setOrigenX(int origenX) {
        this.origenX = origenX;
    }

    public float getOrigenY() {
        return origenY;
    }

    public void setOrigenY(int origenY) {
        this.origenY = origenY;
    }

    public Texture getImagen() {
        return imagen;
    }

    public void setImagen(Texture imagen) {
        this.imagen = imagen;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }
    public Vector2 getVector(){return tex;}

    public void actuliza() {
        tex.x--;
    }

    public void setOrigenX(float origenX) {
        this.origenX = origenX;
    }

    public void setOrigenY(float origenY) {
        this.origenY = origenY;
    }

    public Rectangle getBbAsteroiod() {
        return bbAsteroiod;
    }

    public void setBbAsteroiod(Rectangle bbAsteroiod) {
        this.bbAsteroiod = bbAsteroiod;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Vector2 getTex() {
        return tex;
    }

    public void setTex(Vector2 tex) {
        this.tex = tex;
    }
    public void actualializaRectangulo(float x,float y){
        bbAsteroiod.setX(x);
        bbAsteroiod.setY(y);
    }
}
