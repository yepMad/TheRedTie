package com.sundaysunday.theredtie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sundaysunday.theredtie.Worlds.Level;
import com.sundaysunday.theredtie.Worlds.World;

public class TheRedTie extends ApplicationAdapter
{
	public static final int WIDTH = 192;
	public static final int HEIGHT = 324;
	public static final int SCALE = 2;

	private static final int PLAY_BUTTON_WIDTH = 75;
	private static final int PLAY_BUTTON_HEIGHT = 19;

	private static final int PLAY_BUTTON_X = ((WIDTH) * 2 - PLAY_BUTTON_WIDTH) / 2;
	private static final int PLAY_BUTTON_Y_OFFSET = 60;
	private static final int PLAY_BUTTON_Y = ((HEIGHT - PLAY_BUTTON_Y_OFFSET) * 2 - PLAY_BUTTON_HEIGHT) / 2;

	private static final int CREDITS_BUTTON_WIDTH = 111;
	private static final int CREDITS_BUTTON_HEIGHT = 24;

	private static final int CREDITS_BUTTON_X = ((WIDTH) * 2 - CREDITS_BUTTON_WIDTH) / 2;
	private static final int CREDITS_BUTTON_Y_OFFSET = 90;
	private static final int CREDITS_BUTTON_Y = ((HEIGHT - CREDITS_BUTTON_Y_OFFSET) * 2 - CREDITS_BUTTON_HEIGHT) / 2;

	private static final int CREDITS_TEAM_X = ((WIDTH) * 2 - 143) / 2;
	private static final int CREDITS_TEAM_Y = ((HEIGHT - 147) * 2) / 2;

	private static final int BACK_BUTTON_WIDTH = 67;
	private static final int BACK_BUTTON_HEIGHT = 13;

	private static final int BACK_BUTTON_X = 115;
	private static final int BACK_BUTTON_Y = HEIGHT - 310;

	private SpriteBatch batch;
	private World world;
	private OrthographicCamera camera;
	private Texture playButtonNotActive, playButtonActive, creditsButtonNotActive, creditsButtonActive, backButtonNotActive, backButtonActive;

	private boolean isPlayButtonClicked = false;
	private boolean isCreditsScreenOpen = false;

	@Override
	public void create ()
	{
		playButtonNotActive = new Texture("play-button.png");
		playButtonActive = new Texture("play-button-trigger.png");

		creditsButtonNotActive = new Texture("credits-button.png");
		creditsButtonActive = new Texture("credits-button-trigger.png");

		backButtonNotActive = new Texture("back_button.png");
		backButtonActive = new Texture("back_button_active.png");

		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		world = new Level(camera);

		camera.position.x = WIDTH * 0.5f;
		camera.position.y = HEIGHT * 0.5f;

		Gdx.input.setInputProcessor(world);
	}

	@Override
	public void render ()
	{
		camera.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(!isCreditsScreenOpen)
		{
		batch.begin();

		DrawMenuBackground();

		PlayButton();
		CreditsButton();

		batch.end();
		}

		if(isCreditsScreenOpen)
		{
			batch.begin();

			DrawMenuBackgroundWithoutLogo();
			batch.draw(Assets.creditsTextImage, CREDITS_TEAM_X, CREDITS_TEAM_Y, 0, 0, 71, 147, 2, 2, 0);
			BackButton();

			batch.end();
		}

		if(isPlayButtonClicked)
		{
			batch.begin();
			batch.draw(Assets.background, 0, 0, 0, 0, WIDTH, HEIGHT, 1, 1, 0);
			batch.end();
			batch.setProjectionMatrix(camera.combined);
			world.Update(Gdx.graphics.getDeltaTime());
			world.Draw(batch);
			world.DrawHitbox();
			world.UpdateList();
		}

		if(world.resetFlag)
		{
			world = new Level(camera);
		}
	}

	public void PlayButton()
	{
		if(Gdx.input.getX() > PLAY_BUTTON_X && Gdx.input.getX() <= PLAY_BUTTON_X + PLAY_BUTTON_WIDTH && Gdx.input.getY() > PLAY_BUTTON_Y + (PLAY_BUTTON_Y_OFFSET * 2) && Gdx.input.getY() <= PLAY_BUTTON_Y + (PLAY_BUTTON_Y_OFFSET * 2) + PLAY_BUTTON_HEIGHT)
		{
			batch.draw(playButtonActive, PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched())
			{
				isPlayButtonClicked = true;
			}
		}
		else
		{
			batch.draw(playButtonNotActive, PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}
	}

	public void CreditsButton()
	{
		if(Gdx.input.getX() > CREDITS_BUTTON_X && Gdx.input.getX() <= CREDITS_BUTTON_X + CREDITS_BUTTON_WIDTH && Gdx.input.getY() > CREDITS_BUTTON_Y + (CREDITS_BUTTON_Y_OFFSET * 2) && Gdx.input.getY() <= CREDITS_BUTTON_Y + (CREDITS_BUTTON_Y_OFFSET * 2) + CREDITS_BUTTON_HEIGHT)
		{
			batch.draw(creditsButtonActive, CREDITS_BUTTON_X, CREDITS_BUTTON_Y, CREDITS_BUTTON_WIDTH, CREDITS_BUTTON_HEIGHT);
			if(Gdx.input.isTouched())
			{
				isCreditsScreenOpen = true;
			}
		}
		else
		{
			batch.draw(creditsButtonNotActive, CREDITS_BUTTON_X, CREDITS_BUTTON_Y, CREDITS_BUTTON_WIDTH, CREDITS_BUTTON_HEIGHT);
		}
	}

	public void BackButton()
	{
		if(Gdx.input.getX() > BACK_BUTTON_X && Gdx.input.getX() < BACK_BUTTON_X + BACK_BUTTON_WIDTH && Gdx.input.getY() < (HEIGHT * 2) - BACK_BUTTON_HEIGHT && Gdx.input.getY() > (HEIGHT * 2) - (BACK_BUTTON_HEIGHT * 2))
		{
			batch.draw(backButtonActive, BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
			if(Gdx.input.isTouched())
			{
				isCreditsScreenOpen = false;
			}
		}
		else
		{
			batch.draw(backButtonNotActive, BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
		}
	}

	public void DrawMenuBackground()
	{
		batch.draw(Assets.backgroundMenu, 0, 0, 0, 0, WIDTH, HEIGHT, 2, 2, 0);
	}

	public void DrawMenuBackgroundWithoutLogo()
	{
		batch.draw(Assets.backgroundMenuWithoutLogo, 0, 0, 0, 0, WIDTH, HEIGHT, 2, 2, 0);
	}
}
