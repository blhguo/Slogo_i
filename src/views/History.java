package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class History{
	private Rectangle rectangle;
	public History(){
		rectangle = new Rectangle(SlogoView.CMDHISTORYX, SlogoView.CMDHISTORYY, 
				SlogoView.CMDHISTORYWIDTH, SlogoView.CMDHISTORYHEIGHT);
		rectangle.setFill(Color.BLUE);
	}
	public Rectangle getField(){
		return rectangle;
	}
}
