package views.SceneElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import views.SceneElements.SceneElement;
import views.SlogoView;

public class VariableView extends SceneElement {
    private Rectangle rectangle;
    public VariableView() {
        rectangle = new Rectangle(SlogoView.VARIABLEVIEWX, SlogoView.VARIABLEVIEWY, SlogoView.VARIABLEVIEWWIDTH,
                SlogoView.VARIABLEVIEWHEIGHT);
        rectangle.setFill(Color.CYAN);
    }
    @Override
    public Rectangle getField() {
        return rectangle;
    }
}
