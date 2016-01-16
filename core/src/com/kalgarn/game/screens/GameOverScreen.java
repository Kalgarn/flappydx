package com.kalgarn.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.kalgarn.game.FlappyGDX;


/**
 * Created by Jerome on 16/01/2016.
 */
public class GameOverScreen implements Screen {
    private Texture gameOver;
    private Texture background;
    private FlappyGDX game;
    private OrthographicCamera cam = new OrthographicCamera();
    public GameOverScreen(FlappyGDX game) {
this.game = game;
        cam.setToOrtho(false, FlappyGDX.WIDTH/2,FlappyGDX.HEIGHT/2);
        this.gameOver = new Texture("gameover.png");
        this.background = new Texture("bg.png");
    }
    public void handleInput() {
        if(Gdx.input.justTouched()) {

            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.draw(gameOver, cam.position.x - gameOver.getWidth() / 2, cam.position.y);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
        System.out.println("gameover quitter");
    }
}
