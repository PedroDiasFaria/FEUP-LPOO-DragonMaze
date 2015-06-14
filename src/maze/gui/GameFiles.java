package maze.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import maze.logic.Maze;

/**
 * Class responsible for the save and load algorithms.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */

public class GameFiles {
	
	/**
	 * Function responsible for the save option.
	 * @param fileName Name of the file that the user wants to save.
	 * @param gameFile Maze object that is being used in the GUI for the game.
	 */
	public static void saveFile(String fileName, Maze gameFile){
		
		File file = new File(fileName);
		
		try {
			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(fileName));
			try {
				save.writeObject(gameFile);
				save.flush();

			} finally {
				save.close();
			}

		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", 0);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", 0);

		}
	}

	/**
	 * Function responsible for the load option.
	 * @param fileName Name of the file the user wants to load.
	 * @return a maze structure loaded with the configurations saved on the fileName.
	 * @throws ClassNotFoundException when it doens't find a file with the fileName string.
	 */
	public static Maze loadFile(String fileName) throws ClassNotFoundException {
		Maze gameFile = null;
		
		try {
			ObjectInputStream load = new ObjectInputStream(new FileInputStream(fileName));
			try {
				gameFile = (Maze) load.readObject();
			} finally {
				load.close();
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", 0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", 0);
		}
		
		return gameFile;
	}

	
	
}
