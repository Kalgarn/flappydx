package com.kalgarn.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
    private static final int GROUND_Y_OFFSET = -55; // decalage du sol, trop haut de base

    private Bird bird;
    private Texture bg;
    //private Texture bird;
   // private Tube tube;
    private Array<Tube> tubes;

    private Texture ground;
    private Vector2 groundPosition1;
    private Vector2 groundPosition2;

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

        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(cam.position.x - cam.viewportWidth /2 ,GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(),GROUND_Y_OFFSET);
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
        updateGround(); // genere sol
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80; //centre la vue
        //  tubes
        for (Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x  + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
                // reinitialise la game si collision
            if(tube.collides(bird.getPlayer())){
                gsm.set(new MenuState(gsm));
            }
                // -- si touche le sol
            if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
                gsm.set(new MenuState(gsm));
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
        sb.draw(ground, groundPosition1.x,groundPosition1.y);
        sb.draw(ground, groundPosition2.x,groundPosition2.y);
        sb.end();
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPosition1.x + ground.getWidth())
            groundPosition1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPosition2.x + ground.getWidth())
            groundPosition2.add(ground.getWidth() * 2, 0);
    }

    @Override
    public void dispose() {
        bg.dispose();
        ground.dispose();
        System.out.println("fin partie");
    }
}
