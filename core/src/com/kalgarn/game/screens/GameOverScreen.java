package com.kalgarn.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kalgarn.game.FlappyGDX;
import com.kalgarn.game.HighScore;


/**
 * Created by Jerome on 16/01/2016.
 */
public class GameOverScreen implements Screen {
    private Texture gameOver;
    private Texture background;
    private FlappyGDX game;
    private OrthographicCamera cam = new OrthographicCamera();

    private Stage stage;
    private Label scoreLabel;
    private Label highScoreLabel;

    private BitmapFont font;

    public GameOverScreen(FlappyGDX game) {
        this.game = game;
        cam.setToOrtho(false, FlappyGDX.WIDTH / 2, FlappyGDX.HEIGHT / 2);
        this.gameOver = new Texture("gameover.png");
        this.background = new Texture("bg.png");

        font = new BitmapFont(Gdx.files.internal("font/font.fnt"),Gdx.files.internal("font/font.png"), false);
        stage = new Stage();
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        scoreLabel = new Label("score " + String.valueOf(HighScore.getGamescore()),new Label.LabelStyle(font, Color.WHITE));
        highScoreLabel = new Label("best score " + String.valueOf(HighScore.getHighscore()), new Label.LabelStyle(font, Color.BROWN));
        table.add(scoreLabel).expandX();
        table.row();
        table.add(highScoreLabel).expandX();
        stage.addActor(table);
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
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
        game.batch.draw(background, 0, 0);
        game.batch.draw(gameOver, cam.position.x - gameOver.getWidth() / 2, cam.position.y);
        stage.draw();
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
