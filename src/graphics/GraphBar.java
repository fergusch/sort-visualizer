package graphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A visual representation of a number
 * in the sort visualizer.
 * @author Christian Ferguson (@fergusch)
 */
public class GraphBar {
	
	private int index;
	private int value;
	private Color color;
	
	/**
	 * Creates a GraphBar object with the given values.
	 * @param index - the index of the GraphBar.
	 * @param value - the value of the GraphBar.
	 */
	public GraphBar(int index, int value, Color color) {
		this.index = index;
		this.value = value;
		this.color = color;
	}
	
	/**
	 * Draw the GraphBar.
	 * @param g - Graphics object used for drawing
	 * @param width - bar width
	 */
	public void draw(Graphics g, int width) {
		g.setColor(this.color);
		g.fillRect(this.index * (width + 1), 720 - value, width, value);
	}
	
}
