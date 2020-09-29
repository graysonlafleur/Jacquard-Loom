package edu.wit.cs.comp2000;

import java.awt.*;
import java.util.List;

import javax.swing.*;

/**
 * Draws a window to display a tapestry
 *
 */
public class TapestryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int PANEL_HEIGHT = 600; // the height of the frame
	private static final int PANEL_WIDTH = 600; // the width of the frame;
	private int stitchSize;

	private List<List<Color>> tapestry;
	private TapestryPanel panel; // the panel we'll draw the tapestry on

	public TapestryFrame(List<List<Color>> tapestry, int size) {

		this.tapestry = tapestry; // set the tapestry
		stitchSize = PANEL_WIDTH/size; // calculate stitch size

		panel = new TapestryPanel();
		panel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		panel.setBackground(Color.WHITE);
		this.getContentPane().add(panel);

		this.setTitle("Tapestry");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * A private inner class that contains the panel that we're drawing on.
	 * We've overwritten paintComponent to specify our own painting.
	 */
	private class TapestryPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {

			//this will "white out" the background of the Panel
			g.setColor(Color.WHITE);
			g.clearRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT); //erase what has come before

			int row = 0;
			int col = 0;
			for (List<Color> line: tapestry) {
				for (Color c: line) {
					drawStitch(g, c, row, col);
					col += stitchSize;
				}
				row += stitchSize;
				col = 0;
			}
		}

		/**
		 * draw a "stitch" - a rectangle - in a certain location
		 * 
		 * @param g the graphic to draw the rectangle on
		 * @param c the rectangle color
		 * @param row the x-coord
		 * @param col the y-coord
		 */
		private void drawStitch(Graphics g, Color c, int row, int col) {
			g.setColor(c);
			g.fillRect(col, row, stitchSize, stitchSize);
		}
	}

	/**
	 * A method that "redraws" the frame to update the shown necklace.
	 */
	public void refreshTapestry() {
		panel.repaint();
	}
}
