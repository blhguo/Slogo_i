package views.SceneElements;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import views.SceneElements.SceneElement;
import views.SlogoView;

public class Toolbar extends SceneElement {
    private ToolBar toolbar;
    public Toolbar() {
        //The Tool Bar is on the top, so no need to set X and Y values
        toolbar = new ToolBar(
                new Button("New"),
                new Button("Open"),
                new Button("Save"),
                new Separator(),
                new Button("Clean"),
                new Button("Compile"),
                new Button("Run"),
                new Separator(),
                new Button("Debug"),
                new Button("Profile"),
                new Separator(),
                new Label("Hi Everyone!")
        );
        toolbar.setMinSize(SlogoView.TOOLBARWIDTH, SlogoView.TOOLBARHEIGHT);
    }
    @Override
    public Node getField(){
        return toolbar;
    }
}
