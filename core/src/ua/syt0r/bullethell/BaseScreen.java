package ua.syt0r.bullethell;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ua.syt0r.bullethell.actor.LoadingActor;

public abstract class BaseScreen implements Screen, LoadingActor.OnLoadingDoneListener {

    private Stage uiStage;

    @Override
    public void show() {

        uiStage = new Stage(new ScreenViewport());

        loadResources();

        LoadingActor loadingActor = new LoadingActor(this);
        uiStage.addActor(loadingActor);

        Gdx.input.setInputProcessor(uiStage);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.878f , 0.878f , 0.878f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        uiStage.act(delta);
        uiStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        disposeResources();
    }

    abstract public void loadResources();
    abstract public void disposeResources();

    @Override
    abstract public void onLoadingDone();

    public Stage getUiStage() {
        return uiStage;
    }

}
