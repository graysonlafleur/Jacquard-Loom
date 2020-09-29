package edu.wit.cs.comp2000;

/**
 *
 *	represents a single hole on a Jacquard loom card
 *
 */
public class Hole {
	private boolean punched;

	public Hole(boolean p) {
		punched = p;
	}

	public boolean isPunched() {
		return punched;
	}
}
