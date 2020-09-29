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
		//TODO Implement this method
		return null;
	}

	/**
	 * @return number of holes, punched or not, on the card
	 */
	public int getSize() {
		//TODO Implement this method
		return -1;
	}

	/**
	 * Creates an ArrayList of Holes based on the string input, and
	 * sets the holes and size data fields.
	 * 
	 * @param textHoles A string representation of the holes to punch on a card
	 * @throws IllegalArgumentException if the text has unexpected characters
	 */
	public void punch(String textHoles) {
		//TODO Implement this method
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
