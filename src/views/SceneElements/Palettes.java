package views.SceneElements;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import views.Observer;
import views.SlogoView;

public class Palettes extends SceneElement implements Observable{

	private VBox vbox;
	private Text text;
	private List<Observer> observers;
    private ScrollPane pane;
    private List<String> states;
    private Label stateLabel;
	
	
	public Palettes() {
		vbox = new VBox();
		states = new ArrayList<>();
		observers = new ArrayList<>();
		stateLabel = getLabel();
		text = getText();
		pane = getPane();

		vbox.getChildren().add(stateLabel);

		

		
	}
	
	private Label getLabel() {
		Label label = new Label("My Palette");
		label.setStyle("-fx-border-color: white; -fx-border-width: 3;" +
                "-fx-background-color: black; -fx-text-fill: white");
		label.setPrefWidth(SlogoView.PALETTEWIDTH);;
		label.maxWidth(SlogoView.PALETTEWIDTH);
		label.setAlignment(Pos.CENTER);;
		return label;
	}
	
	private ScrollPane getPane() {
		pane = new ScrollPane(vbox);
		pane.setLayoutX(SlogoView.PALETTEX);
		pane.setLayoutY(SlogoView.PALETTEY);
		pane.setPrefWidth(SlogoView.PALETTEWIDTH);
        pane.setPrefHeight(SlogoView.PALETTEHEIGHT);
        return pane;
	}
	
	private Text getText() {
		Text tex = new Text();
		tex.setWrappingWidth(SlogoView.CMDHISTORYWIDTH );
        tex.setStyle("-fx-background-color: grey;");
        return tex;
	}

	public void updateState() {
		
	}
	
	
	
	
	
	
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
