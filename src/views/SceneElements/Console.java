package views.SceneElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import views.SlogoView;

import java.util.ArrayList;

public class Console extends SceneElement {
    private VBox vbox;
    private String currentString = "";
    private History myHistory;
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
    }
    @Override
    public Node getField(){
        return vbox;
    }
    public ToolBar getToolBar(){
        ToolBar toolbar = new ToolBar(
                new Label("Console"),
                new Separator(),
                new Button("Execute")
        );
        toolbar.setMinSize(SlogoView.TOOLBARWIDTH, MINITOOLBARHEIGHT);
        return toolbar;
    }
    public History getHistory(){
        return myHistory;
    }
    private Label getConsoleLabel(){
        Label consolelabel = new Label("Console");
        return consolelabel;
    }
    private TextArea getTextArea(){
        TextArea field = new TextArea();
        field.setPromptText("Enter a SLogo command");
        field.setFocusTraversable(false);
        field.setCursor(Cursor.TEXT);
        field.setOnKeyPressed(event -> {
            String topass;
            if(event.getCode() == KeyCode.ENTER){
                topass = field.getText();
                topass = cleanText(topass);
                currentString = topass;
                field.setText("");
                field.positionCaret(0);
            }
        });
        return field;
    }
    private String cleanText(String text){
        text = text.toLowerCase();
        text = text.trim();
        text = text.replaceAll("\\s+"," ");
        text = text.replaceFirst("\\n", "");
        return text;
    }
    public String getCurrentString(){
        return currentString;
    }
}
