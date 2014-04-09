package ifs;

import java.awt.Graphics;

/**
 * The Point class can be used to create a point that can be used for various
 * reasons. For more precise calculations the coordinates of a Point-Object are
 * represented as doubles.
 * 
 * @author Pommesfee
 * @version 1.1
 * @since 1.0
 */
public class IFS_Point {

	/**
	 * A double representing the X-coordinate of a Point.
	 */
	private double x;
	/**
	 * A double representing the Y-coordinate of a Point.
	 */
	private double y;

	/**
	 * This value can be used to scale the x and y values of a Point.
	 */
	private int scale = 15;

	/**
	 * The X-offset is used to translate the X-position of A Point.
	 */
	private int offsetX = getWidth() / 2;
	/**
	 * The Y-offset is used to translate the Y-position of A Point.
	 */
	private int offsetY =  (getHeight() / 2);

	/**
	 * Canvas height used for offset calculation.
	 */
	private int height = 0;

	/**
	 * Canvas width used for offset calculation.
	 */
	private int width = 0;

	/**
	 * The standart constructor for a point-object. Creates a new point. The
	 * coordinates a freely selectable.
	 * 
	 * @param x
	 *            X-coordinate of the point you want to create.
	 * @param y
	 *            Y-coordinate of the Point you want to create.
	 */
	public IFS_Point(double x, double y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		calculateOffsets();
	}

	/**
	 * An alternate constructor for a point-object. Creates a new Point at
	 * x=0/y=0.
	 */
	public IFS_Point(int width, int height) {
		this(0, 0, width, height);
	}

	/**
	 * Method used to calculate the x-offset.
	 */
	public void calculateOffsetX() {
		setOffsetX(getWidth() / 2);
	}

	/**
	 * Method used to calculate the y-offset.
	 */
	public void calculateOffsetY() {
		setOffsetY(getHeight() / 10);
	}

	/**
	 * Method used to calculate the x-offset and y-offset.
	 */
	public void calculateOffsets() {
		setOffsetX(getWidth() / 2);
		setOffsetY(getHeight() / 10);
	}

	/**
	 * Method used to recalculate the x-offset and y-offset.
	 */
	public void reCalculateOffsets(int width, int height) {
		setWidth(width);
		setHeight(height);
		setOffsetX(getWidth() / 2);
		setOffsetY(getHeight() / 10);
	}

	/**
	 * The getter for the point's x-coordinate.
	 * 
	 * @return returns the X-coordinate of a Point.
	 */
	public double getX() {
		return x;
	}

	/**
	 * The setter for the point's x-coordinate.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * The getter for the point's y-coordinate.
	 * 
	 * @return returns the Y-coordinate of a Point.
	 */
	public double getY() {
		return y;
	}

	/**
	 * The setter for the point's y-coordinate.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * The getter for the point's scale.
	 * 
	 * @return returns the scale of a Point.
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * The setter for the point's scale.
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}

	/**
	 * The getter for the point's x-offset.
	 * 
	 * @return returns the X-offset of a Point.
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * The setter for the point's x-offset.
	 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * The getter for the point's y-offset.
	 * 
	 * @return returns the Y-offset of a Point.
	 */
	public int getOffsetY() {
		return offsetY;
	}

	/**
	 * The getter for the point's x-offset.
	 */
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	/**
	 * Getter for height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setter for height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Getter for width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Setter for width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * The draw method of a Point. Used to draw a point to the screen. A point
	 * is represented by a filled 1*1 rectangle.
	 */
	public void draw(Graphics g) {
		g.drawRect((int) (((getX() * -1) * getScale()) + getOffsetX()),
				(int) (((getY() * -1) * getScale()) + getOffsetY()), 1, 1);
	}

}