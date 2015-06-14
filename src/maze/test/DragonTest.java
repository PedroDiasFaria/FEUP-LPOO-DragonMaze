package maze.test;

import static org.junit.Assert.*;

import maze.logic.Maze;

import org.junit.Test;

/**
 * Test class responsible for testing all the dragon features.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class DragonTest {
	
	Maze maze = new Maze();

	/**
	 * Test responsible for checking the movement of the dragon to a empty space.
	 */
	@Test
	public void testMoveDragon() {
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		//dragon movements definition
		int[] movements = {2,1,4,3};
		
		//down
		maze.dragonTurn(movements[0],false);
		assertEquals(1,maze.getDragon().getX());
		assertEquals(4,maze.getDragon().getY());
		//up
		maze.dragonTurn(movements[1],false);
		assertEquals(1,maze.getDragon().getX());
		assertEquals(3,maze.getDragon().getY());
		
		maze.dragonTurn(movements[0],false);
		maze.dragonTurn(movements[0],false);
		
		//right
		maze.dragonTurn(movements[2],false);
		assertEquals(2,maze.getDragon().getX());
		assertEquals(5,maze.getDragon().getY());
		//left
		maze.dragonTurn(movements[3],false);
		assertEquals(1,maze.getDragon().getX());
		assertEquals(5,maze.getDragon().getY());
		
		
	}
	
	/**
	 * Test responsible for checking the dragon movement against a wall.
	 */
	@Test
	public void testMoveDragonAgainstAWall(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		//right against Wall
		maze.dragonTurn(4,false);
		assertEquals(1,maze.getDragon().getX());
		assertEquals(3,maze.getDragon().getY());
		//left against Wall
		maze.dragonTurn(3,false);
		assertEquals(1,maze.getDragon().getX());
		assertEquals(3,maze.getDragon().getY());
		
		//positioning the dragon for the other tests
		maze.dragonTurn(2,false);
		maze.dragonTurn(2,false);
		maze.dragonTurn(4,false);
		
		//up against Wall
		maze.dragonTurn(1,false);
		assertEquals(2,maze.getDragon().getX());
		assertEquals(5,maze.getDragon().getY());
		//down against Wall
		maze.dragonTurn(2,false);
		assertEquals(2,maze.getDragon().getX());
		assertEquals(5,maze.getDragon().getY());
		
	}
	
	/**
	 * Test responsible for checking if the dragon falls asleep.
	 */
	@Test
	public void testDragonIsASleep(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		assertFalse(maze.getDragon().isAsleep());
		maze.dragonTurn(0, true);
		assertTrue(maze.getDragon().isAsleep());
		assertEquals(1,maze.getDragon().getX());
		assertEquals(3,maze.getDragon().getY());
	}
	
	/**
	 * Test responsible for checking the situation where a dragon and a sword are in the same spot.
	 */
	@Test
	public void testDragonIsAboveSword(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		maze.getDragon().setY(7);
		assertFalse(maze.getDragonWasAboveSword());
		maze.dragonTurn(2,false);
		assertTrue(maze.getDragonWasAboveSword());
		
		
	}
	
	/**
	 * Test responsible for checking if the dragon kills the eagle.
	 */
	@Test
	public void testDragonKillEagle(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		char[] movements = {'d','d','d','d','d','d','d','a','a','a','a','a','a'};
		maze.heroTurn('1');
		
		//moves the hero until the eagle is back to its original place
		for(int i=0;i<13;i++){
			maze.heroTurn(movements[i]);
		}
		
		maze.dragonTurn(1,false);
		assertFalse(maze.getEagle().isAlive());
		
	}
	
	/**
	 * Test responsible for checking the existence of multiple dragons.
	 */
	@Test 
	public void testMultipleDragons(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		maze.setNumberOfDragons(3);
	}

}
