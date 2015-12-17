package com.kalgarn.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kalgarn.game.FlappyGDX;
import com.kalgarn.game.gameObjects.Bird;

/**
 * Created by Jerome on 16/12/2015.
 */
public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    //private Texture bird;

    public PlayState(GameStateManager gsm) {
        super(gsm);
       // bird = new Texture("bird.png");
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyGDX.WIDTH / 2, FlappyGDX.HEIGHT / 2);
        bg = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(bird, 50, 50);
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
