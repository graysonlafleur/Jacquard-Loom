package edu.wit.cs.comp2000;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Jacquard loom operator
 *
 */
public class Weaver {

	private List<Card> cardDeck;
	private Iterator<Card> cardIter;

	/**
	 * Weave a row onto the loom based on the next Card in the cardDeck
	 * 
	 * @param loom The loom to add a row of Color to
	 * @return whether a row was added
	 */
	public boolean weaveRow(Loom loom) {
		if (!cardIter.hasNext()) {
			cardIter = cardDeck.iterator();
			return false;
		}

		loom.run(cardIter.next());
		return true;
	}

	/**
	 * reads in a pattern from a text file and converts it to a List of cards.
	 * also verifies a valid pattern
	 * 
	 * @param patternFile full file path to a pattern file
	 * @return List of punched cards
	 */
	public List<Card> buildCards(String patternFile) {

		List<Card> cardDeck = new ArrayList<>();

		// TODO open patternFile and convert lines of file to cards

		verifyDeck(cardDeck);

		return cardDeck;
	}

	/**
	 * Gives the Weaver a deck of cards and starts the card iterator
	 * 
	 * @param c pattern cards
	 */
	public void setCards(List<Card> c) {
		//TODO implement this method
	}

	/**
	 * @return deck of cards that weaver is holding
	 */
	public List<Card> getCards() {
		//TODO implement this method
		return null;
	}

	/**
	 * @param cd Deck of cards to check for existence and regularity
	 */
	private void verifyDeck(List<Card> cd) {

		if (cd.isEmpty()) {
			System.err.println("No cards were read");
			System.exit(1);
		}

		int size = cd.get(0).getSize();

		for (Card c: cd) {
			if (c.getSize() != size) {
				System.err.println("Cards do not have a consistent size");
				System.exit(1);
			}
		}

	}


	// Test Weaver methods, assuming that Card and Loom already works
	public static void main(String[] args) {

		Weaver w = new Weaver();
		Loom l = new Loom(Color.white);
		l.setWeft(Color.black);

		List<Card> cards = w.buildCards("patterns/simple.txt");

		if ((cards == null) || (cards.size() != 3)) {
			System.err.println("Card deck wasn't built correctly.");
			System.exit(1);
		}


		w.setCards(cards);

		List<Card> cardResult = w.getCards();

		if ((cardResult == null) || (cardResult.size() != 3)) {
			System.err.println("Card deck wasn't stored correctly.");
			System.exit(1);
		}

		// This looks weird. It's weaving two rows and checking if either failed
		if ((!w.weaveRow(l)) || (!w.weaveRow(l))) {
			System.err.println("A row wasn't woven successfully.");
			System.exit(1);
		}

		List<List<Color>> t = l.getTapestry();

		if (t.size() != 2) {
			System.err.println("Tapestry should have two rows.");
			System.exit(1);
		}

		if ((t.get(0) == null) || (!t.get(0).get(0).equals(Color.white))) {
			System.err.println("Wrong row was woven.");
			System.exit(1);
		}

		if ((t.get(1) == null) || (!t.get(1).get(0).equals(Color.black))) {
			System.err.println("Wrong row was woven.");
			System.exit(1);
		}

		System.out.println("All methods working correctly.");
	}

}
