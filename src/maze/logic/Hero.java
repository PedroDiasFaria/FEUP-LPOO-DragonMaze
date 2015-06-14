package maze.logic;

public class Hero extends MazeElement{

	/**
	 * Class responsible for the creation and function manipulation of the hero element parameters in the maze
	 * @author Rui Figueira - ei11021
	 * @author Pedro Faria - ei11167
	 */
	
	private boolean isArmed;
	private boolean hasEagle;
	
	/**
	 * Constructor of this class - It creates a hero on the position (x,y) and sets its character letter.It also initializes the value of armed and hasEagle
	 */
	public Hero()
	{
		this.setX(1);
		this.setY(1);
		this.setLetter('H');
		this.setArmed(false);
		this.setHasEagle(true);
	}

	/**
	 * Gets the isArmed variable
	 * @return true if Armed, false if Unarmed
	 */
	public boolean isArmed() {
		return isArmed;
	}


	/**
	 * Sets the isArmed variable
	 * @param true if Armed, false if Unarmed
	 */
	public void setArmed(boolean isArmed) {
		this.isArmed = isArmed;
	}

	/**
	 * Gets the hasEagle variable
	 * @return true if has Eagle, false if hasn't
	 */
	public boolean getHasEagle() {
		return hasEagle;
	}

	/**
	 * Sets the hasEagle variable
	 * @param true if has Eagle, false if hasn't
	 */
	public void setHasEagle(boolean hasEagle) {
		this.hasEagle = hasEagle;
	}
	
	
}
