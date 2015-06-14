package maze.logic;

import java.io.Serializable;
/**
 * Super Class of the maze elements.It contains the standard variables of all the characters.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 * @see Serializable
 */ 
public class MazeElement implements Serializable{


	private int x;
	private int y;
	private char letter;

	/**
	 * Gets the letter variable
	 * @return letter
	 */
	public char getLetter() {
		return letter;
	}
	/**
	 * Sets the letter variable
	 * @param letter
	 */
	public void setLetter(char letter) {
		this.letter = letter;
	}
	/**
	 * Gets the x variable
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Sets the x variable
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Gets the y variable
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets the y variable
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
