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
	private Text turtleInfo;
	private Text text;
	private Text id;
	private Text turtleID;
	private Text turtleHeading;
	private Text turtleX;
	private Text turtleY;
	private Text penSize;
	private Text penUP;
	private Text penColor;
//	private List<Text> info;
	private List<Observer> observers;
	public static final double WRAPBUFFER = 30;
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
		text = getText();
		pane = getPane(text);
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
	
	private ScrollPane getPane(Text text) {	
		ScrollPane pane = new ScrollPane(text);
		pane.setLayoutX(SlogoView.STATEX);
		pane.setLayoutY(SlogoView.STATEY);
		pane.setPrefWidth(SlogoView.STATEWIDTH);
        pane.setPrefHeight(SlogoView.STATEHEIGHT);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return pane;
	}
	
	 private Text getText() {
	        Text tex = new Text();
	        tex.setWrappingWidth(SlogoView.CMDHISTORYWIDTH - WRAPBUFFER);
	        tex.setStyle("-fx-background-color: grey;");
	        return tex;
	    }
	 
	private VBox getVbox() {
		vbox.setLayoutX(SlogoView.STATEX);
		vbox.setLayoutY(SlogoView.STATEY);
		vbox.setPrefWidth(SlogoView.STATEWIDTH);
        vbox.setMinHeight(SlogoView.STATEHEIGHT);
        vbox.setPrefHeight(SlogoView.STATEHEIGHT);
        return vbox;
	}

	
	public void getTurtleInfo(Turtle turtle) {
		turtleInfo.setText("Turtle ID: " + turtle.getId() + "\n" + "Turtle Heading: " + 
				turtle.getHeading()+ "Turtle X Position: " + turtle.getLocation().getX() + 
				"Turtle Y Position: " + turtle.getLocation().getY() + "Pen Size: " + turtle.getPenSize() +
				"Pen Up: " + turtle.isPenUp() + "Pen Color: " + turtle.penColor());
		states.add(turtleInfo);
//		turtleID.setText("Turtle ID: " + turtle.getId());
//		turtleHeading.setText("Turtle Heading: " + turtle.getHeading());
//		turtleX.setText("Turtle X Position: " + turtle.getLocation().getX());
//		turtleY.setText("Turtle Y Position: " + turtle.getLocation().getY());
//		penSize.setText("Pen Size: " + turtle.getPenSize());
//		penUP.setText("Pen Up: " + turtle.isPenUp());
//		penColor.setText("Pen Color: " + turtle.penColor());	
//        states.add(turtleID);
//        states.add(turtleHeading);
//        states.add(turtleX);
//        states.add(turtleY);
//        states.add(penSize);
//        states.add(penUP);   
		updateObservers();
	}
	

//	
//	public void updateStateView() {
//		updateState();
//	}

//	public void updateState() {
//		reset();
//	}
	
	
    public void updateObservers(){
        for (Observer o : observers){
        	System.out.println(o.toString());
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
//
//	public void reset() {
//		vbox.getChildren().clear();
//		vbox.getChildren().add(stateLabel);
//		info = getTurtleInfo();
//		pane = getPane(info);
//		vbox = getVbox();
//		vbox.getChildren().add(pane);
//	}
	
}
