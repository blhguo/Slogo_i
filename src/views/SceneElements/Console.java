package views.SceneElements;


import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import views.Observer;
import views.SlogoView;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Console extends SceneElement implements Observable{
    private VBox vbox;
    private String currentString = "";
    private History myHistory;
    private TextArea field;
    private List<Observer> observers;
    public static final double MINITOOLBARHEIGHT = .5 * SlogoView.TOOLBARHEIGHT;
    public Console(){
        vbox = new VBox();
        vbox.getChildren().addAll(getTextArea(), getToolBar());
        vbox.setSpacing(0);
        vbox.setPadding(new Insets(1,1,3,1));
        vbox.setLayoutX(SlogoView.CONSOLEX);
        vbox.setLayoutY(SlogoView.CONSOLEY);
        vbox.setPrefWidth(SlogoView.CONSOLEWIDTH);
        vbox.setPrefHeight(SlogoView.CONSOLEHEIGHT);
        myHistory = new History();
        observers = new ArrayList<>();
    }
    @Override
    public Node getField(){
        return vbox;
    }
    public ToolBar getToolBar(){
        Button button = new Button("Execute");
        //button.setOnKeyPressed(e -> sendText());
        ToolBar toolbar = new ToolBar(
                new Label("Console"),
                new Separator(),
                button
        );
        toolbar.setMinSize(SlogoView.TOOLBARWIDTH, MINITOOLBARHEIGHT);
        return toolbar;
    }
    private void sendText(){
        currentString = field.getText();
        currentString = cleanText(currentString);
        //System.out.println(currentString);
        myHistory.addCommand(currentString);
        field.setText("");
    }
    public History getHistory(){
        return myHistory;
    }
    private Label getConsoleLabel(){
        Label consolelabel = new Label("Console");
        return consolelabel;
    }
    private TextArea getTextArea(){
        field = new TextArea();
        field.setPromptText("Enter a SLogo command");
        field.setFocusTraversable(false);
        field.setCursor(Cursor.TEXT);
        field.clipProperty();
        field.setOnKeyPressed(event -> clearText(event.getCode()));
        return field;
    }
    private void clearText(KeyCode code){
        if(code == KeyCode.ENTER) {
            sendText();
        }
        else if (code == KeyCode.UP){
            field.setText(myHistory.getLastCommand());
            field.positionCaret(field.getText().length());
        }
        else if (code == KeyCode.DOWN){
            field.setText(myHistory.restoreCommand());
            field.positionCaret(field.getText().length());

        }
    }

    private String cleanText(String text){
        text = text.toLowerCase();
        text = text.trim();
        text = text.replaceAll("\\s+"," ");
        //text = text.replaceFirst("\\n", "");
        return text;
    }
    public String getCurrentString(){
        return currentString;
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