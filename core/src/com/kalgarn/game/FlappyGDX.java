package com.kalgarn.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kalgarn.game.States.GameStateManager;
import com.kalgarn.game.States.MenuState;

public class FlappyGDX extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "FlappyBird";

	private GameStateManager gsm;
	private SpriteBatch batch;
	private OrthographicCamera camera = new OrthographicCamera();

	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.gsm = new GameStateManager();
		//camera.setToOrtho(false, WIDTH, HEIGHT);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		this.gsm.push(new MenuState(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//camera.update();
		gsm.update(Gdx.graphics.getDeltaTime());
		this.gsm.render(batch);

	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
