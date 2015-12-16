package com.kalgarn.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kalgarn.game.FlappyGDX;

/**
 * Created by Jerome on 16/12/2015.
 */
public class PlayState extends State {
    //private Bird bird;
    private Texture bg;
    private Texture bird;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        // bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyGDX.WIDTH / 2, FlappyGDX.HEIGHT / 2);
        bg = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
