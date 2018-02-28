package views.SceneElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;

public class VariableView extends SceneElement implements Observable{
    private Rectangle rectangle;
    private List<Observer> observers;
    public VariableView() {
        rectangle = new Rectangle(SlogoView.VARIABLEVIEWX, SlogoView.VARIABLEVIEWY, SlogoView.VARIABLEVIEWWIDTH,
                SlogoView.VARIABLEVIEWHEIGHT);
        rectangle.setFill(Color.CYAN);
        observers = new ArrayList<>();
    }
    @Override
    public Rectangle getField() {
        return rectangle;
    }
    public void updateObservers(){
        for (Observer o : observers){
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
}
