package views.SceneElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import turtle.Turtle;
import views.Observer;
import views.SlogoView;

// pen up or down
// pen thickness
// ID, position, heading

public class CurrentState extends SceneElement implements Observable {
	private VBox vbox;
	private Text id;
	private TextArea info;
//	private List<Text> info;
	private List<Observer> observers;
    private ScrollPane pane;
    private List<Text> states;
    private Label stateLabel;
	private Turtle turtle;
	
	public CurrentState(Turtle turtle) {
		this.turtle = turtle;
		vbox = new VBox();
		states = new ArrayList<>();
		observers = new ArrayList<>();
		stateLabel = getLabel();
		vbox.getChildren().add(stateLabel);
		info = getTurtleInfo();
		pane = getPane(info);

		vbox = getVbox();
		vbox.getChildren().add(pane);
	}
	
	private Label getLabel() {
		Label label = new Label("Current States:");
        label.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-width: 1;");
		label.setPrefWidth(SlogoView.STATEWIDTH);;
		label.maxWidth(SlogoView.STATEWIDTH);
		label.setAlignment(Pos.CENTER);;
		return label;
	}
	
	private ScrollPane getPane(TextArea text) {	
		pane = new ScrollPane(text);
		pane.setLayoutX(SlogoView.STATEX);
		pane.setLayoutY(SlogoView.STATEY);
		pane.setPrefWidth(SlogoView.STATEWIDTH);
        pane.setPrefHeight(SlogoView.STATEHEIGHT);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
       
        return pane;
	}
	
	private VBox getVbox() {
		vbox.setLayoutX(SlogoView.STATEX);
		vbox.setLayoutY(SlogoView.STATEY);
		vbox.setPrefWidth(SlogoView.STATEWIDTH);
        vbox.setMinHeight(SlogoView.STATEHEIGHT);
        vbox.setPrefHeight(SlogoView.STATEHEIGHT);
        return vbox;
	}

	
	public TextArea getTurtleInfo() {
		Text turtleID = new Text("Turtle ID: " + turtle.getId());
		Text turtleHeading = new Text("Turtle Heading: " + turtle.getHeading());
		Text turtleX = new Text("Turtle X Position: " + turtle.getLocation().getX());
		Text turtleY = new Text("Turtle Y Position: " + turtle.getLocation().getY());
//		Text turtleShape = new Text("Turtle Shape: " + turtle.turtleShape());
		Text penSize = new Text("Pen Size: " + turtle.getPenSize());
		Text penUP = new Text("Pen Up: " + turtle.isPenUp());
		Text penColor = new Text("Pen Color: " + turtle.penColor());

		info = new TextArea();
        info.appendText(turtleID.getText()+"\n");
        info.appendText(turtleHeading.getText()+"\n");
//        info.appendText(turtleShape.getText() + "\n");
        info.appendText(turtleX.getText()+"\n");
        info.appendText(turtleY.getText()+"\n");
        info.appendText(penSize.getText()+"\n");
        info.appendText(penUP.getText()+"\n");
        info.appendText(penColor.getText()+"\n");
        
        states.add(turtleID);
        states.add(turtleHeading);
        states.add(turtleX);
        states.add(turtleY);
        states.add(penSize);
        states.add(penUP);
        
		return info;
	}
	

	
	public void updateStateView() {
		updateState();
	}

	public void updateState() {
		reset();
	}
	
	
    public void updateObservers(){
        for (Observer o : observers){
        	System.out.println(o.toString());
//            o.update(new Object());
            o.update(info);
        }
    }
    public void addObserver(Observer o){
	    observers.add(o);
    }
	
	@Override
	public VBox getField() {
		return vbox;
	}

	public void reset() {
		vbox.getChildren().clear();
		vbox.getChildren().add(stateLabel);
		info = getTurtleInfo();
		pane = getPane(info);
		vbox = getVbox();
		vbox.getChildren().add(pane);
	}
	
}
