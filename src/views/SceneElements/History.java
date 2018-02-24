package views.SceneElements;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import views.SceneElements.SceneElement;
import views.SlogoView;

public class History extends SceneElement {
	private VBox vbox;
	private Text text;
	public History(){
		vbox = new VBox();
		text = new Text("Command History: \n");
		vbox.getChildren().add(text);
	}
    public Rectangle getField(){
	    return new Rectangle();
    }
	public void addCommand(String command){
        text.setText(text.getText() + "\n" + command);
    }
}
