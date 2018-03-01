package views;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final String TITLE = "SLogo";
	
	public static void main(String[] args) {
		launch(args);
		}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		SlogoView simulation = new SlogoView();
		//mainStage=simulation.initializeStartScene(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(simulation.initializeStartScene());
		primaryStage.show();
	}
	
	


}
