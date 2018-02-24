package views.SceneElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import views.SceneElements.SceneElement;
import views.SlogoView;

public class TurtleDisplay extends SceneElement {
    private Rectangle rectangle;
    public TurtleDisplay(){
        rectangle = new Rectangle(SlogoView.TURTLEVIEWX, SlogoView.TURTLEVIEWY, SlogoView.TURTLEVIEWWIDTH,
                SlogoView.TURTLEVIEWHEIGHT);
        rectangle.setFill(Color.ORANGE);
    }
    @Override
    public Rectangle getField(){
        return rectangle;
    }
}
