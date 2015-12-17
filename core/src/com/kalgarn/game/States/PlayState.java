package com.kalgarn.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.kalgarn.game.FlappyGDX;
import com.kalgarn.game.gameObjects.Bird;
import com.kalgarn.game.gameObjects.Tube;

/**
 * Created by Jerome on 16/12/2015.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 125; // espace entre 2 tubes en x
    private static final int TUBE_COUNT = 4; // nb de tube afficher

    private Bird bird;
    private Texture bg;
    //private Texture bird;
   // private Tube tube;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
       // bird = new Texture("bird.png");
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyGDX.WIDTH / 2, FlappyGDX.HEIGHT / 2);
        bg = new Texture("bg.png");
     //   tube = new Tube(120); // 1er tube a 120px
        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80; //centre la vue
        for (Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x  + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(bird, 50, 50);
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPositionBotTube().x, tube.getPositionBotTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
