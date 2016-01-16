package com.kalgarn.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.kalgarn.game.FlappyGDX;

/**
 * Created by Jerome on 16/01/2016.
 */
public class MenuScreen implements Screen {

    private Texture background;
    private Texture playBtn;

    private OrthographicCamera cam;

    private FlappyGDX fgame;


    public MenuScreen(FlappyGDX flappyGDX) {
        this.fgame = flappyGDX;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, FlappyGDX.WIDTH / 2, FlappyGDX.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    public void handleInput(){
        if (Gdx.input.justTouched()){
            fgame.setScreen(new PlayScreen(fgame));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // verifie action en 1er
        handleInput();

        fgame.batch.setProjectionMatrix(cam.combined);
        fgame.batch.begin();
        fgame.batch.draw(background, 0, 0);
        fgame.batch.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        fgame.batch.end();
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
        playBtn.dispose();
        System.out.println("Menu quitter");
    }
}
