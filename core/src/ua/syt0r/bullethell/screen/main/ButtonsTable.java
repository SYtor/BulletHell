package ua.syt0r.bullethell.screen.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import ua.syt0r.bullethell.Utils;

public class ButtonsTable extends Table {

    private ButtonGroupOnClickListener buttonGroupOnClickListener;
    private ButtonGroup<TextButton> buttonGroup;

    private TextButton.TextButtonStyle textButtonStyle;

    public ButtonsTable(Stage stage, Skin skin, TextureAtlas loadingTextureAtlas, String[] labels) {
        super(skin);

        NinePatchDrawable defaultButtonPatch = new NinePatchDrawable(new NinePatch(loadingTextureAtlas
                .findRegion("color"), Color.valueOf("00000000")));
        NinePatchDrawable pressedButtonPatch = new NinePatchDrawable(new NinePatch(loadingTextureAtlas
                .findRegion("color"),Color.valueOf("5e5e5e")));
        BitmapFont bitmapFont = Utils.generateFont("MunroSmall.ttf",(int)(stage.getWidth() * 46 / 1280));
        textButtonStyle =
                new TextButton.TextButtonStyle(defaultButtonPatch,pressedButtonPatch,pressedButtonPatch,bitmapFont);
        textButtonStyle.fontColor = Color.valueOf("5e5e5e");
        textButtonStyle.downFontColor = Color.valueOf("e0e0e0");
        textButtonStyle.checkedFontColor = textButtonStyle.downFontColor;

        buttonGroup = new ButtonGroup<TextButton>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);

        ClickListener clickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (buttonGroupOnClickListener != null)
                    buttonGroupOnClickListener.onClick(buttonGroup.getCheckedIndex());
            }
        };

        for (String title : labels) {
            TextButton textButton = new TextButtonWithPadding(stage, title, textButtonStyle);
            buttonGroup.add(textButton);
            this.add(textButton).width(stage.getWidth()/32*9).padBottom(10f).padLeft(0).padRight(0).row();
            textButton.getLabel().setAlignment(Align.left);
            textButton.addListener(clickListener);
        }

    }

    public void setButtonGroupOnClickListener(ButtonGroupOnClickListener buttonGroupOnClickListener) {
        this.buttonGroupOnClickListener = buttonGroupOnClickListener;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public TextButton.TextButtonStyle getTextButtonStyle() {
        return textButtonStyle;
    }

    public interface ButtonGroupOnClickListener{
        void onClick(int position);
    }
}
