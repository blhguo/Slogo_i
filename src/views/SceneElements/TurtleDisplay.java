package views.SceneElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;

public class TurtleDisplay extends SceneElement implements Observable {
    private Rectangle rectangle;
    private List<Observer> observers;
    public TurtleDisplay(){
        rectangle = new Rectangle(SlogoView.TURTLEVIEWX, SlogoView.TURTLEVIEWY, SlogoView.TURTLEVIEWWIDTH,
                SlogoView.TURTLEVIEWHEIGHT);
        rectangle.setFill(Color.ORANGE);
        observers = new ArrayList<>();
    }
    @Override
    public Rectangle getField(){
        return rectangle;
    }
    public void updateObservers(){
        for (Observer o : observers){
            o.update();
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
}
