package views.SceneElements;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import views.Observer;
import views.SlogoView;

public class CurrentState extends SceneElement implements Observable {
	private VBox vbox;
	private Text text;
	private List<Observer> observers;
    private ScrollPane pane;
    private List<Label> states;
    private Label stateLabel;
	
	
	public CurrentState() {
		vbox = new VBox();
		states = new ArrayList<>();
		observers = new ArrayList<>();
		stateLabel = getLabel();
		text = getText();

		vbox.getChildren().add(stateLabel);
		pane = getPane();


		
	}
	
	private Label getLabel() {
		Label label = new Label("Current States:");
		label.setStyle("-fx-border-color: white; -fx-border-width: 3;" +
                "-fx-background-color: black; -fx-text-fill: white");
		label.setPrefWidth(SlogoView.STATEWIDTH);;
		label.maxWidth(SlogoView.STATEWIDTH);
		label.setAlignment(Pos.CENTER);;
		return label;
	}
	
	private ScrollPane getPane() {
		pane = new ScrollPane(vbox);
		pane.setLayoutX(SlogoView.STATEX);
		pane.setLayoutY(SlogoView.STATEY);
		pane.setPrefWidth(SlogoView.STATEWIDTH);
        pane.setPrefHeight(SlogoView.STATEHEIGHT);
        return pane;
	}
	
	private Text getText() {
		Text tex = new Text();
		tex.setWrappingWidth(SlogoView.CMDHISTORYWIDTH );
        tex.setStyle("-fx-background-color: grey;");
        return tex;
	}
	
	public void updateStateView(Map<String, String> variables) {
		updateState(variables);
	}

	public void updateState(Map<String, String> variables) {
		vbox.getChildren().removeAll(states);
		states.clear();
		for (String key : variables.keySet()) {
			Label l = new Label(key + " : " + variables.get(key));
//			l.setPadding(new Insets(1, 1, 1, 5));
			states.add(l);
		}
		vbox.getChildren().addAll(states);
	}
	
//	public Text getStatus() {
//		
//	}
	
	
	
	
	
    public void updateObservers(){
        for (Observer o : observers){
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
	    observers.add(o);
    }
	
	@Override
	public VBox getField() {
		return vbox;
	}
}
