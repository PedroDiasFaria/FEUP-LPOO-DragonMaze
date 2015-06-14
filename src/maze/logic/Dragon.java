package maze.logic;

/**
 * Class responsible for the creation and function manipulation of the dragon element(s) parameters in the maze
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class Dragon extends MazeElement {

	private boolean alive = true;
	private boolean asleep = false;
	
	/**
	 * Constructor of this class - It creates a dragon on the position (x,y) and sets its character letter
	 */
	public Dragon()
	{
		this.setX(1);
		this.setY(3);
		this.setLetter('D');
	}

	/**
	 * Gets the alive variable
	 * @return true if alive, false if dead
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Sets the alive variable
	 * @param true if alive, false if dead
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Gets the asleep variable
	 * @return true if asleep, false if awake
	 */
	public boolean isAsleep() {
		return asleep;
	}

	/**
	 * Sets the asleep variable
	 * @param true if asleep, false if awake
	 */
	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}
	
	
}
