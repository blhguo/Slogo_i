package views.SceneElements;


import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import views.Observer;
import views.SlogoView;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Console extends SceneElement implements Observable{
    private VBox vbox;
    private String currentString = "";



    private String[] passValue;
    private History myHistory;
    private TextArea field;
    private List<Observer> observers;
    private TextField littlefield;
    public static final double MINITOOLBARHEIGHT = .5 * SlogoView.TOOLBARHEIGHT;
    public static final double VBOXBUFFER = 5;
    public Console(){
        vbox = new VBox();
        vbox.getChildren().addAll(getTextArea(), getToolBar());
        vbox.setSpacing(0);
        vbox.setPadding(new Insets(1,1,3,1));;
        vbox.setLayoutX(SlogoView.CONSOLEX);
        vbox.setLayoutY(SlogoView.CONSOLEY);
        vbox.setMaxWidth(SlogoView.CONSOLEWIDTH);
        vbox.setPrefHeight(SlogoView.CONSOLEHEIGHT);
        vbox.setStyle("-fx-border-color: black; -fx-border-width: 2");
        myHistory = new History();
        myHistory.setMyConsole(this);
        observers = new ArrayList<>();
       // littlefield = new TextField();
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
        toolbar.setMinSize(SlogoView.CONSOLEWIDTH - VBOXBUFFER, MINITOOLBARHEIGHT);
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
        field.setOnKeyPressed(event -> handleText(event.getCode()));
        field.setPadding(new Insets(1,1,1,5));
        field.setMaxWidth(SlogoView.CONSOLEWIDTH - VBOXBUFFER);
        return field;
    }
    private void handleText(KeyCode code){
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

    public void getCommand(String text) {
        field.setText(text);
        sendText();
    }
}
