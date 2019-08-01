package ua.syt0r.bullethell.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ua.syt0r.bullethell.GameManager;

public class LoadingActor extends Actor {

    private OnLoadingDoneListener onLoadingDoneListener;

    private NinePatch ninePatch;

    public LoadingActor(OnLoadingDoneListener onLoadingDoneListener) {
        this.onLoadingDoneListener = onLoadingDoneListener;

        ninePatch = new NinePatch(GameManager.AssetManager.get("loading.atlas", TextureAtlas.class)
                .findRegion("color"));

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameManager.AssetManager.update()) {
            onLoadingDoneListener.onLoadingDone();
            remove();
        }
        else
            System.out.println("Loading...");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        ninePatch.draw(batch,0,0,getStage().getWidth(),getStage().getHeight());
    }

    public interface OnLoadingDoneListener {
        void onLoadingDone();
    }

}
