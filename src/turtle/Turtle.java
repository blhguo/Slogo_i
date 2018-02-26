package turtle;

import javafx.geometry.Point2D;
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

	/**
	 * Variety of getters and setters used to access Information in actors, for
	 * update and display
	 **/
	public Turtle() {
		this(new Point2D(0, 0), 0);
	}


	public Turtle(Point2D location, double speed)
	{
		this.location = location;
		this.speed = speed;
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

	public void setLocation(Point2D p)
	{
		location = p;
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
