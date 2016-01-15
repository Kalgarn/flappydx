package com.kalgarn.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Jerome on 17/12/2015.
 */
public class Bird {
    private static final int GRAVITY = -10; // vitesse de chute
    private static final int MOVEMENT = 100; // vitesse de deplacement

    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;

    private Rectangle player;

    private Animation birdAnimation;
    //private Texture texture;

    private Sound wingsflap;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

       // bird = new Texture("bird.png");
        bird = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(bird), 3, 0.6f);
        //player = new Rectangle(x,y,bird.getWidth(),bird.getHeight());  // sans animation
        player = new Rectangle(x,y,bird.getWidth() /3 ,bird.getHeight());
        wingsflap = Gdx.audio.newSound(Gdx.files.internal("audio/fx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);

        //position.add(0, velocity.y, 0);
        position.add(MOVEMENT * dt, velocity.y, 0); // fait avancer
        velocity.scl(1/dt);
        //empeche de tomber
        if(position.y < 0) {
            position.y = 0;
        }
        // ?
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        player.setPosition(position.x,position.y);

    }
        // vitesse de saut
    public void jump(){
        velocity.y = 250;
        wingsflap.play(0.1f);

    }
        // annimation de mort
    public void die(){
        wingsflap.play();
    }

    public void dispose(){
        bird.dispose();
        wingsflap.dispose();
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

    //public Texture getBird() { return bird;}  } // sans animation

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public void setBird(Texture bird) {
        this.bird = bird;
    }
}
