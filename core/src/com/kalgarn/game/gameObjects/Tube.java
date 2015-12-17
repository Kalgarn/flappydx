package com.kalgarn.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Jerome on 17/12/2015.
 */
public class Tube {
    private static final int FLUCTUATION = 120; //0>120
    private static final int TUBE_GAP = 100; // espace entre les 2 tubes
    private static final int LOWEST_OPENING = 100; // taille min tube du bas

    private Texture topTube;
    private Texture bottomTube;
    private Vector2 positionTopTube;
    private Vector2 positionBotTube;
    private Random rand;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        positionTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBotTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

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
