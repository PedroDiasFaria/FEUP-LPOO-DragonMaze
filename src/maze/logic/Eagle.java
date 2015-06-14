package maze.logic;

/**
 * Class responsible for the creation and function manipulation of the eagle element parameters in the maze
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class Eagle extends MazeElement {
	
	//Variables responsible for recording the initial position of th eagle when its used
	private int oldX;
	private int oldY;
	//Variables responsible for controlling the eagle 
	private boolean isAlive=true;
	private boolean hasSword=false;
	private boolean wasUsed=false;
	private boolean isFlying=false;
	private char oldChar = 'L';
	
	/**
	 * Default constructor of this class - It creates a eagle on the position (x,y)
	 */
	public Eagle(){
		this.setX(1);
		this.setY(1);
	}

	/**
	 * Gets the old y variable
	 * @return oldY
	 */
	public int getOldY() {
		return oldY;
	}

	/**
	 * Sets the old y variable
	 * @param oldY 
	 */
	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	/**
	 * Gets the old x variable
	 * @return oldX
	 */
	public int getOldX() {
		return oldX;
	}

	/**
	 * Sets the old x variable
	 * @param oldX
	 */
	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	/**
	 * Gets the oldChar variable
	 * @return oldChar 
	 */
	public char getOldChar() {
		return oldChar;
	}

	/**
	 * Sets the oldChar variable
	 * @param oldChar
	 */
	public void setOldChar(char oldChar) {
		this.oldChar = oldChar;
	}

	/**
	 * Gets the isAlive variable
	 * @return true if Alive, false if Dead
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Sets the isAlive variable
	 * @param true if Alive, false if Dead
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * Gets the hasSword variable
	 * @return true if has the Sword, false if it hasn't
	 */
	public boolean HasSword() {
		return hasSword;
	}

	/**
	 * Sets the hasSword variable
	 * @param true if has the Sword, false if it hasn't
	 */
	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}

	/**
	 * Gets the wasUsed variable
	 * @return true if eagle has been used, false if it hasn't
	 */
	public boolean wasUsed() {
		return wasUsed;
	}

	/**
	 * Sets the wasUsed variable
	 * @param true if eagle has been used, false if it hasn't
	 */
	public void setWasUsed(boolean wasUsed) {
		this.wasUsed = wasUsed;
	}

	/**
	 * Gets the isFlying variable
	 * @return true if eagle is Flying, false if it isn't
	 */
	public boolean isFlying() {
		return isFlying;
	}

	/**
	 * Sets the isFlying variable
	 * @param true if eagle is Flying, false if it isn't
	 */
	public void setFlying(boolean isFlying) {
		this.isFlying = isFlying;
	}
	
	
}
