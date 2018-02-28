package turtle;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.SlogoView;

/**
*
* Turtle holds knowledge about themselves that can be
* used for display.
* 
*/
public class Turtle {
	private Point2D location;
	private double speed;
	private double heading;
	private ImageView turtleview;
	private final double TURTLESIZE = 50;
    private double BASEX = SlogoView.TURTLEVIEWX + 1.0 / 2 * SlogoView.TURTLEVIEWWIDTH
            - .5 * TURTLESIZE;
    private double BASEY = SlogoView.TURTLEVIEWY + 1.0 / 2 * SlogoView.TURTLEVIEWHEIGHT
            - .5 * TURTLESIZE;
	/**
	 * Variety of getters and setters used to access Information in actors, for
	 * update and display
	 **/
	public Turtle() {
		this(new Point2D(SlogoView.TURTLEVIEWX + 1.0 / 2 * SlogoView.TURTLEVIEWWIDTH
                - 25,SlogoView.TURTLEVIEWY + 1.0 / 2 * SlogoView.TURTLEVIEWHEIGHT
                - 25), 0);
	}


	public Turtle(Point2D location, double speed)
	{
		this.location = location;
		this.speed = speed;
		Image turtle = new Image("turtle.png", 50,50,true,true);
		turtleview = new ImageView(turtle);
		turtleview.setLayoutX(this.location.getX());
		turtleview.setLayoutY(this.location.getY());
	}

	/**
	 * Point representing the current location of the actor
	 * 
	 * @return Point location
	 */
	public Point2D getLocation()
	{
		return location;
	}
	public ImageView getImage(){return turtleview;}

	public void setLocation(Point2D p)
	{
		location = p;
		turtleview.setLayoutX(this.location.getX());
		turtleview.setLayoutY(this.location.getY());
	}

	/**
	 * An int representing the speed of the actor as it updates on the screen
	 * 
	 */
	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double s)
	{
		speed = s;
	}

	public double getHeading()
	{
		return heading;
	}

	public void setHeading(double heading)
	{
		this.heading = Math.floorMod((int) heading, 360);
	}
}
