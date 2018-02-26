package views.SceneElements;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class History extends SceneElement implements Observable{
	private VBox vbox;
	private Text text;
	private Stack<String> record;
	private Stack<String> reverserecord;
	private List<Observer> observers;
	private ScrollPane scrollPane;
	private String current;
	public static final double WRAPBUFFER = 30;
	private List<String> commands;
	private int pos;
	public History(){
		vbox = new VBox();
		vbox.setLayoutX(SlogoView.CMDHISTORYX);
		vbox.setLayoutY(SlogoView.CMDHISTORYY);
		vbox.setPrefWidth(SlogoView.CMDHISTORYWIDTH);
		vbox.setPrefHeight(SlogoView.CMDHISTORYHEIGHT);
		vbox.setPadding(new Insets(2,2,2,5));
		text = new Text("Command History: \n");
		text.setWrappingWidth(SlogoView.CMDHISTORYWIDTH - WRAPBUFFER);
		vbox.getChildren().add(text);
		current = "";
		record = new Stack<>();
		reverserecord = new Stack();
		commands = new ArrayList<>();
		observers = new ArrayList<>();
		scrollPane = new ScrollPane(vbox);
		scrollPane.setLayoutX(SlogoView.CMDHISTORYX);
        scrollPane.setLayoutY(SlogoView.CMDHISTORYY);
        scrollPane.setPrefWidth(SlogoView.CMDHISTORYWIDTH);
        scrollPane.setPrefHeight(SlogoView.CMDHISTORYHEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	}
	@Override
    public ScrollPane getField(){
	    return scrollPane;
    }
	public void addCommand(String command){
        text.setText(text.getText() + "\n" + "[" + ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS)
                + "]\n" + command);
        record.add(command);
        commands.add(command);
        pos = commands.size() - 1;
//        vbox.getChildren().remove(text);
//        vbox.getChildren().add(text);
        updateObservers();
    }
    public String getLastCommand() throws EmptyStackException{
        String s = commands.get(pos);
        pos--;
        if (pos < 0){
            pos = 0;
        }
        return s;
    }
    public String restoreCommand() throws EmptyStackException{
        pos++;
        if (pos >= commands.size()){
            pos--;
            return "";
        }
        return commands.get(pos);
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
