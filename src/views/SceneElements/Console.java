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

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Console extends SceneElement implements Observable{
    private VBox vbox;
    private String currentString = "";



    private String[] passValue;
    private History myHistory;
    private TextArea field;
    private List<Observer> observers;
    private TextField littlefield;
    private CurrentState myState;
    private Palettes myPalette;
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
        vbox.setStyle("-fx-border-color: black; -fx-border-width: 2");
        myHistory = new History();
        observers = new ArrayList<>();
       // littlefield = new TextField();
        myState = new CurrentState();
        myPalette = new Palettes();
    }
    @Override
    public Node getField(){
        return vbox;
    }
    public String[] getPassValue() {
        return passValue;
    }
    public ToolBar getToolBar(){
        Button button = new Button("Execute");
        Button clearbutton = new Button("Clear");
        clearbutton.setOnAction(e -> clearHistory());
        button.setOnAction(e -> sendText());
        littlefield = new TextField();
        ToolBar toolbar = new ToolBar(
                new Label("Console"),
                new Separator(),
                button,
                new Separator(),
                clearbutton,
                littlefield
        );
        toolbar.setMinSize(SlogoView.TOOLBARWIDTH, MINITOOLBARHEIGHT);
        return toolbar;
    }

    private void clearHistory() {
        field.setText("");
        myHistory.clear();
    }
    public void setLittleField(String s){
        littlefield.setText("Return: " + s);
    }
    private void sendText(){
        try {
            //Refactor this to be "if invalid, do something"
            if (field.getText().equals("")) {
                return;
            }
            field.setStyle("-fx-text-fill: black;");
            currentString = field.getText();
            currentString = cleanText(currentString);
            passValue = currentString.split(" ");
            //StringBuilder temp = new StringBuilder(currentString);
            //System.out.println(temp.toString());
            myHistory.addCommand(currentString);
            //System.out.println(currentString);
            field.setText("");
            //passValue = currentString.split(" ");
        } catch (InvalidParameterException e) {
            myHistory.removeLastCommand();
            field.setText("Sorry, that's not a valid command");
            field.selectAll();
            field.setStyle("-fx-background-color: red;");

        }
    }
    public History getHistory(){
        return myHistory;
    }
    public CurrentState getState() {
    	return myState;
    }
    public Palettes getPalette() {
    	return myPalette;
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
        field.setPadding(new Insets(1,1,1,5));
        return field;
    }
    private void clearText(KeyCode code){
//        if(code == KeyCode.ENTER) {
//            sendText();
//        }
        if (code == KeyCode.ESCAPE){
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
        else{
            field.setStyle("-fx-text-fill: black;");
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
            o.update(new Object());
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
}
