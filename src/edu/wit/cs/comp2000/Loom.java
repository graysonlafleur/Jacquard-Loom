package edu.wit.cs.comp2000;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *	Holds thread colors for a Jacquard loom
 */
public class Loom {

	private Color weft;
	private final Color warp;	// only set in constructor
	private List<List<Color>> tapestry = new ArrayList<List<Color>>();

	public Loom(Color warpColor) {
		warp = warpColor;
	}

	public void setWeft(Color c) {
		weft = c;
	}

	public Color getWeft() {
		return weft;
	}

	/**
	 * Builds one row of tapestry and adds it to list of rows
	 * 
	 * @param card Card to run through the loom
	 */
	public void run(Card card) {
		List<Color> tempTapestry = new ArrayList<Color>();
		for(int i = 0; i<card.getSize(); i++) 
		{
			if(card.getHoles().get(i).isPunched())
			{
				tempTapestry.add(warp);
			}
			else
			{
				tempTapestry.add(weft);
			}
		}
		
		tapestry.add(tempTapestry);
		//TODO Implement this method
	}

	/**
	 * Returns and resets the current tapestry
	 * 
	 * @return 
	 */
	public List<List<Color>> getTapestry() {
		List<List<Color>> newTapestry = new ArrayList<List<Color>>();
		newTapestry = tapestry;
		tapestry = new ArrayList<List<Color>>();
		return newTapestry;
	}


	// Test Loom methods assuming Card works
	public static void main(String[] args) {

		Loom l = new Loom(Color.white);

		l.setWeft(Color.blue);

		if ((l.getWeft() == null) ||
				(!l.getWeft().equals(Color.blue))) {
			System.err.println("Loom doesn't save the weft color correctly.");
			System.exit(1);
		}

		Card c1 = new Card();
		c1.punch("xxxx");
		Card c2 = new Card();
		c2.punch("  xx");

		l.run(c1);
		l.run(c2);

		List<List<Color>> result = l.getTapestry();

		if (result == null) {
			System.err.println("Loom doesn't give the tapestry as a list of list of colors.");
			System.exit(1);
		}

		if ( (result.get(0) == null) || 	// no first row
				(result.get(0).get(0) == null) ||	// no Color in first row
				(!result.get(0).get(0).equals(Color.white))) {	// wrong color
			System.err.println("Unexpected tapestry color.");
			System.exit(1);
		}

		if ( (result.get(1) == null) || 	// no second row
				(result.get(1).get(1) == null) ||	// no Color in second row
				(!result.get(1).get(1).equals(Color.blue))) {	// wrong color
			System.err.println("Unexpected tapestry color.");
			System.exit(1);
		}

		System.out.println("All methods working correctly.");
	}
}
