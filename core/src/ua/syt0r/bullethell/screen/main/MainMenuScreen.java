package ua.syt0r.bullethell.screen.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ua.syt0r.bullethell.BaseScreen;
import ua.syt0r.bullethell.GameManager;

public class MainMenuScreen extends BaseScreen {

    private TextureAtlas uiAtlas;
    private String[] mainMenuButtonLabels = new String[] {
            "Game start","Endless mode","Customize","Achievements","Settings","About","Exit"
    };

    private enum MenuOption {
        MAIN_MENU,
        LEVEL_SELECT,
        ENDLESS_MODE,
        SETTINGS,
        EXIT
    }

    private Table contentTable;

    @Override
    public void loadResources() {
        GameManager.AssetManager.load("ui.atlas", TextureAtlas.class);
    }

    @Override
    public void disposeResources() {
        GameManager.AssetManager.unload("ui.atlas");
    }

    @Override
    public void onLoadingDone() {

        uiAtlas = GameManager.AssetManager.get("ui.atlas", TextureAtlas.class);

        Skin skin = new Skin(uiAtlas);
        contentTable = new Table(skin);
        contentTable.setFillParent(true);
        getUiStage().addActor(contentTable);

        loadMenu(MenuOption.MAIN_MENU);

    }

    private void loadMenu(final MenuOption menuOption) {
        getUiStage().addAction(Actions.sequence(
                Actions.fadeOut(0.5f),
                new Action() {
                    @Override
                    public boolean act(float delta) {
                        contentTable.clearChildren();
                        switch (menuOption){
                            case MAIN_MENU:
                                showMainMenu();
                                break;
                            case LEVEL_SELECT:
                                showLevelSelectMenu();
                                break;
                            default:
                            case EXIT:
                                Gdx.app.exit();
                        }

                        return true;
                    }
                },
                Actions.fadeIn(0.5f)
        ));
    }

    private void showMainMenu() {

        TextureAtlas loadingTextureAtlas = GameManager.AssetManager.get("loading.atlas", TextureAtlas.class);

        //

        SolidColorActor leftLineActor = new SolidColorActor(loadingTextureAtlas.findRegion("color"),
                getUiStage().getWidth() * 8 / 1280, getUiStage().getHeight());

        contentTable.add(leftLineActor);

        // Menu Buttons

        ButtonsTable buttonsTable = new ButtonsTable(getUiStage(), contentTable.getSkin(),
                loadingTextureAtlas, mainMenuButtonLabels);
        buttonsTable.setButtonGroupOnClickListener(new ButtonsTable.ButtonGroupOnClickListener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0: loadMenu(MenuOption.LEVEL_SELECT); break;
                    default:
                    case 6: loadMenu(MenuOption.EXIT); break;
                }
            }
        });

        contentTable.add(buttonsTable)
                .width(getUiStage().getWidth()/3*1)
                .expandY().top()
                .padTop(getUiStage().getWidth() * 200f /1280);

        //

        SolidColorActor rightLineActor = new SolidColorActor(loadingTextureAtlas.findRegion("color"),
                getUiStage().getWidth() * 8 / 1280, getUiStage().getHeight());
        contentTable.add(rightLineActor);

    }

    private void showLevelSelectMenu() {



        //contentTable.add(leftLineActor).left().padLeft( getUiStage().getWidth() * 150 / 1280);

        TextureAtlas loadingTextureAtlas = GameManager.AssetManager.get("loading.atlas", TextureAtlas.class);
        ButtonsTable buttonsTable = new ButtonsTable(getUiStage(), contentTable.getSkin(), loadingTextureAtlas,
                mainMenuButtonLabels);
        buttonsTable.getButtonGroup().setMinCheckCount(1);

        TextButton returnButton = new TextButtonWithPadding(getUiStage(), "return",
                buttonsTable.getTextButtonStyle());

        returnButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadMenu(MenuOption.MAIN_MENU);
            }
        });

        buttonsTable.add(returnButton)
                .width(getUiStage().getWidth() * 160 / 1280)
                .expandY()
                .bottom();

        contentTable.add(buttonsTable)
                .width(getUiStage().getWidth() * 200 / 1280)
                .growY().left()
                .padTop((getUiStage().getWidth() * 130 / 1280))
                .padBottom((getUiStage().getWidth() * 100 / 1280));



    }

}
