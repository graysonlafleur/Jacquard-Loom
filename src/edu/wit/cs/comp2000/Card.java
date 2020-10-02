package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Holds information about a single Jacquard loom card
 * 
 */
public class Card {
	private List<Hole> holes;
	private int size;

	/**
	 * @return list of holes punched on the card
	 */
	public List<Hole> getHoles() {
		return holes;
	}

	/**
	 * @return number of holes, punched or not, on the card
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Creates an ArrayList of Holes based on the string input, and
	 * sets the holes and size data fields.
	 * 
	 * @param textHoles A string representation of the holes to punch on a card
	 * @throws IllegalArgumentException if the text has unexpected characters
	 */
	public void punch(String textHoles) {
		ArrayList<Hole> arr = new ArrayList<Hole>();
		for(int i = 0; i<textHoles.length(); i++)
		{
			if(textHoles.charAt(i) == 'x')
			{
				arr.add(new Hole(true)); 
			}
			else if(textHoles.charAt(i) == ' ')
			{
				arr.add(new Hole(false));
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		
		holes = arr;
		size = textHoles.length();
	}

	// Test Card methods
	public static void main(String[] args) {

		Card c = new Card();

		c.punch("xx  ");

		if (c.getSize() != 4) {
			System.err.println("Card doesn't have the correct number of holes.");
			System.exit(1);
		}
		
		if (!c.getHoles().get(0).isPunched()) {
			System.err.println("Position 0 should be punched.");
			System.exit(1);			
		}

		if (c.getHoles().get(2).isPunched()) {
			System.err.println("Position 2 should not be punched.");
			System.exit(1);			
		}

		System.out.println("All methods working correctly.");
	}
}
