package views.SceneElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import treenode.SlogoNode;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VariableView extends SceneElement implements Observable{
    private Rectangle rectangle;
    private VBox vbox;
    private List<Observer> observers;
    private Text text;
    private ScrollPane pane;
    public static final double WRAPBUFFER = 30;
    public VariableView() {
        observers = new ArrayList<>();
        vbox = new VBox();
        vbox.getChildren().add(getLabel());
        //vbox.getChildren().add(getText());
        vbox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: goldenrod;");
        pane = getPane();
    }

    private Node getLabel() {
        Label l = new Label("Active Variables:");
        l.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-width: 1;");
        l.setPrefWidth(SlogoView.VARIABLEVIEWWIDTH);
        l.setAlignment(Pos.CENTER);
        return l;
    }
    private ScrollPane getPane(){
        pane = new ScrollPane(vbox);
        pane.setLayoutX(SlogoView.VARIABLEVIEWX);
        pane.setLayoutY(SlogoView.VARIABLEVIEWY);
        pane.setPrefWidth(SlogoView.VARIABLEVIEWWIDTH);
        pane.setPrefHeight(SlogoView.VARIABLEVIEWHEIGHT);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        return pane;
    }
    private Node getText(){
        text = new Text("There are no active variables at the moment :(");
        text.setWrappingWidth(SlogoView.VARIABLEVIEWWIDTH - WRAPBUFFER);

        //text.setStyle("-fx-background-color: gold;");
        return text;
    }
    @Override
    public ScrollPane getField() {
        return pane;
    }
    public void updateObservers(){
        for (Observer o : observers){
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }

    public void updateVarView(Map<String, Double> variables) {
        updateText(variables);
    }

    private void updateText(Map<String, Double> variables) {

        for (String key : variables.keySet()){
            //text.setText(text.getText() + "\n " + key + " : " + variables.get(key));
            Label l = new Label(key + " : " + variables.get(key));
            l.setPadding(new Insets(1,1,1,5));
            vbox.getChildren().add(l);
        }
    }
}
