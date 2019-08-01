package ua.syt0r.bullethell;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ua.syt0r.bullethell.screen.main.MainMenuScreen;

public class GameManager extends Game {

	public static AssetManager AssetManager = new AssetManager();

    private FPSLogger fpsLogger;

	@Override
	public void create () {

		AssetManager.load("loading.atlas", TextureAtlas.class);
		AssetManager.finishLoading();

        fpsLogger = new FPSLogger();

		loadScreen(new MainMenuScreen());

	}

	@Override
	public void render () {
        super.render();
        fpsLogger.log();
	}
	
	@Override
	public void dispose () {
	    super.dispose();
        AssetManager.dispose();
	}

	public void loadScreen(Screen screen) {
		setScreen(screen);
	}

}
