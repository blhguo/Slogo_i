package views.SceneElements;

import javafx.scene.Node;
import javafx.scene.control.*;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;

public class Toolbar extends SceneElement implements Observable{
    private ToolBar toolbar;
    private List<Observer> observers;
    private ColorPicker picker;
    public Toolbar() {
        //The Tool Bar is on the top, so no need to set X and Y values
        Hyperlink link = new Hyperlink("https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php");
        link.setText("Help");
        toolbar = new ToolBar(
                new Button("New"),
                new Button("Open"),
                link,
                new Separator(),
                new Button("Clean"),
                getColorPicker(),
                new Button("Run"),
                new Separator(),
                new Button("Debug"),
                new Button("Profile"),
                new Separator(),
                new Label("Hi Everyone!")
        );
        toolbar.setMinSize(SlogoView.TOOLBARWIDTH, SlogoView.TOOLBARHEIGHT);
        observers = new ArrayList<>();
    }

    private ColorPicker getColorPicker() {
        picker = new ColorPicker();
        picker.setOnAction(e -> updateObservers());
        picker.setPromptText("Choose Color");
        return picker;
    }

    @Override
    public Node getField(){
        return toolbar;
    }
    public void updateObservers(){
        for (Observer o : observers){
            o.update(picker.getValue());
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
}
