package maze.logic;

import java.io.Serializable;

/**
 * Class responsible for the creation and function manipulation of the dragon element parameters in the maze
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */

public class Exit extends MazeElement {

	private int x;
	private int y;
	private char letter;
	
	/**
	 * Default constructor of this class - It creates a exit on the position (x,y) and sets its character letter
	 */
	public Exit()
	{
		this.setX(9);
		this.setY(5);
		this.setLetter('S');
	}

	/**
	 * Gets the x variable
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x variable
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y variable
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y variable
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the letter variable
	 */
	public char getLetter() {
		return letter;
	}

	/**
	 * Sets the letter variable
	 */
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	
}
