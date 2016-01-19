package com.kalgarn.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kalgarn.game.screens.MenuScreen;


public class FlappyGDX extends Game {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "FlappyBird";

    public SpriteBatch batch;


    private Music music;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.setScreen(new MenuScreen(this));
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        HighScore.getInstance().load();
    }

    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
        batch.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
