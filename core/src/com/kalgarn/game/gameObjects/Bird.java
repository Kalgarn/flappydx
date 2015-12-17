package com.kalgarn.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Jerome on 17/12/2015.
 */
public class Bird {
    private static final int GRAVITY = -5; // vitesse de chute
    private static final int MOVEMENT = 100; // vitesse de deplacement

    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;

    private Rectangle player;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        player = new Rectangle(x,y,bird.getWidth(),bird.getHeight());
    }

    public void update(float dt){
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);

        //position.add(0, velocity.y, 0);
        position.add(MOVEMENT * dt, velocity.y, 0); // fait avancer
        velocity.scl(1/dt);
        //empeche de tomber
        if(position.y < 0) {
            position.y = 0;
        }
        // inutile?
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        player.setPosition(position.x,position.y);

    }
        // vitesse de saut
    public void jump(){
        velocity.y = 250;
           }

    public Rectangle getPlayer(){
        return player;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    //get Texture bird

    public Texture getBird() {
        return bird;
    }

    public void setBird(Texture bird) {
        this.bird = bird;
    }
}
