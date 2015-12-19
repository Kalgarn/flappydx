package com.kalgarn.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kalgarn.game.FlappyGDX;

/**
 * Created by Jerome on 19/12/2015.
 */
public class GameOver extends State {
    private Texture gameOver;
    private Texture background;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyGDX.WIDTH/2,FlappyGDX.HEIGHT/2);
        this.gameOver = new Texture("gameover.png");
        this.background = new Texture("bg.png");

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(gameOver, cam.position.x - gameOver.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
        System.out.println("gameover quitter");
    }
}
