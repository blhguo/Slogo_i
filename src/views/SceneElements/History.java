package views.SceneElements;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class History extends SceneElement implements Observable{
	private VBox vbox;
	private Text text;
	private Stack<String> record;
	private List<Observer> observers;
	public History(){
		vbox = new VBox();
		vbox.setLayoutX(SlogoView.CMDHISTORYX);
		vbox.setLayoutY(SlogoView.CMDHISTORYY);
		vbox.setPrefWidth(SlogoView.CMDHISTORYWIDTH);
		vbox.setPrefHeight(SlogoView.CMDHISTORYHEIGHT);
		vbox.setPadding(new Insets(2,2,2,5));
		text = new Text("Command History: \n");
		vbox.getChildren().add(text);
		record = new Stack<>();
		observers = new ArrayList<>();
	}
	@Override
    public VBox getField(){
	    return vbox;
    }
	public void addCommand(String command){
        text.setText(text.getText() + "\n" + command);
        record.add(command);
        vbox.getChildren().remove(text);
        vbox.getChildren().add(text);
        updateObservers();
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
