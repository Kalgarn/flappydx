package com.kalgarn.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.kalgarn.game.FlappyGDX;
import com.kalgarn.game.HighScore;
import com.kalgarn.game.objects.Bird;
import com.kalgarn.game.objects.Tube;

/**
 * Created by Jerome on 16/01/2016.
 */
public class PlayScreen implements Screen {

    private static final int TUBE_SPACING = 125; // espace entre 2 tubes en x
    private static final int TUBE_COUNT = 4; // nb de tube afficher
    private static final int GROUND_Y_OFFSET = -55; // decalage du sol, trop haut de base

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    private Texture ground;
    private Vector2 groundPosition1;
    private Vector2 groundPosition2;

    private int score;
    private BitmapFont font;

    private FlappyGDX game;
    private OrthographicCamera cam;

    private Stage stage;
    private Label scoreLabel;

    public PlayScreen(FlappyGDX menuScreen) {
        this.game = menuScreen;
        cam = new OrthographicCamera();
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyGDX.WIDTH / 2, FlappyGDX.HEIGHT / 2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        score = 0;

        font = new BitmapFont(Gdx.files.internal("font/font.fnt"),Gdx.files.internal("font/font.png"), false);

        stage = new Stage();
        Table table = new Table();
        table.bottom().padBottom(15);
        table.setFillParent(true);
        scoreLabel = new Label(String.valueOf(score),new Label.LabelStyle(font,Color.WHITE));
        table.add(scoreLabel).expandX();
        stage.addActor(table);
    }

    public void handleInput() {
        if (Gdx.input.justTouched())
            bird.jump();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bird.jump();
        }
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPosition1.x + ground.getWidth()) {
            groundPosition1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPosition2.x + ground.getWidth()) {
            groundPosition2.add(ground.getWidth() * 2, 0);
        }
    }

    public void update(float dt) {
        handleInput();
        updateGround(); // genere sol
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80; //centre la vue
        //  tubes
        for (Tube tube : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                score++;
                scoreLabel.setText(String.valueOf(score));
                HighScore.getInstance().setHighscore(score);
            }
            // reinitialise la game si collision
            if (tube.collides(bird.getPlayer())) {
                bird.die();
                game.setScreen(new GameOverScreen(game));
            }
            // -- si touche le sol
            if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
                bird.falling();
                game.setScreen(new MenuScreen(game));
            }
        }
        cam.update();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        game.batch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            game.batch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            game.batch.draw(tube.getBottomTube(), tube.getPositionBotTube().x, tube.getPositionBotTube().y);
        }
        game.batch.draw(ground, groundPosition1.x, groundPosition1.y);
        game.batch.draw(ground, groundPosition2.x, groundPosition2.y);
        game.batch.end();
        stage.act();
        stage.draw();

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
        bg.dispose();
        ground.dispose();
        System.out.println("fin partie");
    }
}
