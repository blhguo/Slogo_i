package views.SceneElements;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
<<<<<<< HEAD
import javafx.stage.FileChooser;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;

import java.io.File;
=======
import javafx.scene.control.Hyperlink;
import views.Observer;
import views.SceneElements.SceneElement;
import views.SlogoView;
>>>>>>> d008fb8ec795702b39fb3fecd78021b0bfc0951c
import java.util.ArrayList;
import java.util.List;


public class Toolbar extends SceneElement implements Observable{
	private ToolBar toolbar;
	private List<Observer> observers;
	private ColorPicker picker;
	private boolean done;
	private String url = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
	public Toolbar() {
		//The Tool Bar is on the top, so no need to set X and Y values
		Hyperlink link = new Hyperlink("Help");
		link.setOnAction(e -> getLink());
		Button open = new Button("open");
		open.setOnAction(e -> OpenButton());
		toolbar = new ToolBar(
				new Button("New"),
				open,
				link,
				new Separator(),
				new Label("Choose Background Color:"),
				getColorPicker(),
				new Button("Run"),
				new Separator(),
				new Button("Debug"),
				new Button("Profile"),
				new Separator(),
				new Label("Hi Everyone!")
				);
		toolbar.setMinSize(SlogoView.TOOLBARWIDTH, SlogoView.TOOLBARHEIGHT);
		observers = new ArrayList<>();
	}

	public void OpenButton() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open New File");
	}

	private void getLink() {
		done = true;
		updateObservers();
	}

	private ColorPicker getColorPicker() {
		picker = new ColorPicker();
		picker.setOnAction(e -> updateObservers());
		picker.setPromptText("Choose Color");
		return picker;
	}

	@Override
	public Node getField(){
		return toolbar;
	}
	public void updateObservers(){
		for (Observer o : observers){
			o.update(picker.getValue());
		}
		if (done) {
			for (Observer o : observers) {
				o.update("https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php");
			}
			done = false;
		}
	}
	public void addObserver(Observer o){
		observers.add(o);
	}
}
