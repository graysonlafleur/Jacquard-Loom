package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.FileDialog;
import java.io.File;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Studio holds a Weaver and Jacquard loom,
 * and handles options for a tapestry
 *
 */
public class Studio extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Color warpColor = Color.white;
	private Weaver weaver;
	private Loom loom;
	private TapestryFrame frame;

	public static void main(String[] args) {

		Studio t = new Studio();

		String[] opts = {" Load new pattern file ",
				" Pick weft color ",
				" Weave tapestry ",
		" Quit " };
		int messageType = JOptionPane.QUESTION_MESSAGE;  // type of dialog box
		int choice;  // user choice
		do { // keep asking until user wants to quit

			choice = JOptionPane.showOptionDialog (null, "Choose", "Tapestry tester", 0, messageType, null, opts, opts[0]);
			switch (choice) {
			case 0: 	t.loadCardFile();
			break;
			case 1:     t.pickWeft();
			break;
			case 2:     t.makeNewTapestry();
			break;
			}
		} while (choice != 3);
	}


	/**
	 * lets user select a pattern file and sets the weaver's card deck
	 */
	private void loadCardFile() {
		FileDialog fd = new FileDialog(this, "Select pattern file", FileDialog.LOAD);
		fd.setVisible(true);
		File[] fs = fd.getFiles();
		if (fs.length == 0)	// no file selected
			return;

		weaver.setCards(weaver.buildCards(fs[0].toString()));
	}

	/**
	 * Constructor for objects of class Studio
	 */
	public Studio() {
		loom = new Loom(warpColor);
		weaver = new Weaver();
	}

	/**
	 * display a tapestry from the weaver's cards
	 */
	public void makeNewTapestry() {

		if (weaver.getCards() == null) {
			System.err.println("No card deck has been loaded!");
			return;
		}

		if (loom.getWeft() == null) {
			System.err.println("No weft thread has been loaded!");
			return;
		}

		// weave until the weaver is out of cards
		while(weaver.weaveRow(loom)) {}

		List<List<Color>> result = loom.getTapestry();

		if(frame!=null) {
			frame.setVisible(false);
			frame.dispose();
		}

		List<List<Color>> tapestry = new ArrayList<List<Color>>();

		frame = new TapestryFrame(tapestry,
				Math.max(result.size(), result.get(0).size()));

		for (List<Color> r: result) {
			tapestry.add(r);
			frame.refreshTapestry();
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
			}
		}
	}

	/**
	 *  Show the palette for weft color choice
	 *
	 */
	private void pickWeft() {
		Color c = JColorChooser.showDialog(this,"Select a weft color", Color.black);
		loom.setWeft(c);
	}

}
