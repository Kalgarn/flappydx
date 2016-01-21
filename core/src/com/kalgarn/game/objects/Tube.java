package com.kalgarn.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Jerome on 17/12/2015.
 */
public class Tube {
    private static final int FLUCTUATION = 130; //0>120
    private static final int TUBE_GAP = 115; // espace entre les 2 tubes en y
    private static final int LOWEST_OPENING = 100; // taille min tube du bas
    public static final int TUBE_WIDTH = 50;

    private Texture topTube;
    private Texture bottomTube;
    private Vector2 positionTopTube;
    private Vector2 positionBotTube;
    private Random rand;

    private Rectangle boundstopTube;
    private Rectangle boundsbotTube;


    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        positionTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBotTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundstopTube = new Rectangle(positionTopTube.x, positionTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundsbotTube = new Rectangle(positionBotTube.x, positionBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public void reposition(float x) {
        positionTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBotTube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundstopTube.setPosition(positionTopTube.x, positionTopTube.y);
        boundsbotTube.setPosition(positionBotTube.x, positionBotTube.y);


    }
    // verifie si les rectangle se chevauche, si le joueur touche les tubes
    public boolean collides(Rectangle player){
        return player.overlaps(boundstopTube) || player.overlaps(boundsbotTube);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }

    public Vector2 getPositionBotTube() {
        return positionBotTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }
}
