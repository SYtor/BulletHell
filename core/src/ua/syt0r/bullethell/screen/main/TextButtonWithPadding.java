package ua.syt0r.bullethell.screen.main;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TextButtonWithPadding extends TextButton {
    public TextButtonWithPadding(Stage stage, String text, TextButtonStyle style) {
        super(text, style);
        getLabelCell().padLeft(stage.getWidth() * 16 / 1280).padRight(stage.getWidth() * 16 / 1280);
    }
}
