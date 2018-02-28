package views.SceneElements;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import turtle.Turtle;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;

public class TurtleDisplay extends SceneElement implements Observable {
    private Rectangle rectangle;
    private List<Observer> observers;
    private ArrayList<Turtle> turtles;
    public TurtleDisplay(Turtle turtle){
        rectangle = new Rectangle(SlogoView.TURTLEVIEWX, SlogoView.TURTLEVIEWY, SlogoView.TURTLEVIEWWIDTH,
                SlogoView.TURTLEVIEWHEIGHT);
        rectangle.setFill(Color.ORANGE);
        observers = new ArrayList<>();
        turtles = new ArrayList<>();
        turtles.add(turtle);
    }
    @Override
    public Group getField(){
        Group retgroup = new Group();
        retgroup.getChildren().add(rectangle);
        rectangle.toBack();
        retgroup.getChildren().add(turtles.get(0).getImage());
        return retgroup;
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
