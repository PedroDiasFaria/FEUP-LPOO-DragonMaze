package maze.test;
import maze.logic.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class responsible for testing all the simple features of the interaction between maze elements.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */

public class SimpleTest {

	Maze maze = new Maze(); //initialize the maze structure
	
	/**
	 * Test responsible for checking the hero movement to a empty space.
	 */
	@Test
	public void testMoveHero() {
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		//hero movements definition
		char[] movements = {'s','w','d','a'};
		
		//down movement
		maze.heroTurn(movements[0]);
		assertEquals(1,maze.getHero().getX());
		assertEquals(2,maze.getHero().getY());
		
		//up movement
		maze.heroTurn(movements[1]);
		assertEquals(1,maze.getHero().getX());
		assertEquals(1,maze.getHero().getY());
		
		//right movement
		maze.heroTurn(movements[2]);
		assertEquals(2,maze.getHero().getX());
		assertEquals(1,maze.getHero().getY());
		
		//left movement
		maze.heroTurn(movements[3]);
		assertEquals(1,maze.getHero().getX());
		assertEquals(1,maze.getHero().getY());
	}
	
	/**
	 * Test responsible for checking the hero movement against a wall.
	 */
	@Test
	public void testMoveHeroAgainstAWall(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		//hero movements definition
		char[] movements = {'d','s','a','w','a','s','d'};
		
		//down movement against Wall
		maze.heroTurn(movements[0]);
		maze.heroTurn(movements[1]);
		assertEquals(2,maze.getHero().getX());
		assertEquals(1,maze.getHero().getY());
		
		//up movement against Wall
		maze.heroTurn(movements[2]);
		maze.heroTurn(movements[3]);
		assertEquals(1,maze.getHero().getX());
		assertEquals(1,maze.getHero().getY());
		
		//left movement against Wall
		maze.heroTurn(movements[4]);
		assertEquals(1,maze.getHero().getX());
		assertEquals(1,maze.getHero().getY());
		
		//right movement against Wall
		maze.heroTurn(movements[5]);
		maze.heroTurn(movements[6]);
		assertEquals(1,maze.getHero().getX());
		assertEquals(2,maze.getHero().getY());
		
	}
	
	/**
	 * Test responsible for checking the situation where the hero catches the sword.
	 */
	@Test
	public void testHeroGetSword(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		assertEquals('H',maze.getHero().getLetter());
		char[] movements = {'d','d','d','s','s','s','s','a','a','a','s','s','s'};
		
		//moves the hero till the sword position
		for(int i=0;i<13;i++){
			maze.heroTurn(movements[i]);
		}
		
		assertEquals('A',maze.getHero().getLetter());
	}
	
	/**
	 * Test responsible for checking the situation where the dragon kills the hero.
	 */
	@Test
	public void testDragonKillHero(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		assertFalse(maze.getEndOfGame());
		char movement = 's';
		
		//move the hero into the adjacent square of the dragon
		maze.heroTurn(movement);
		assertTrue(maze.getEndOfGame());
	}

	/**
	 * Test responsible for checking the situation where the hero kills the dragon.
	 */
	@Test
	public void testHeroKillDragon(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		assertTrue(maze.getDragon().isAlive());
		char[] movements = {'d','d','d','s','s','s','s','a','a','a','s','s','s','w','w','w','w'};
		
		for(int i=0;i<17;i++){
			maze.heroTurn(movements[i]);
		}
		
		assertFalse(maze.getDragon().isAlive());
	}
	
	/**
	 * Test responsible for checking the situation where the hero tries to exit the maze after catching the sword and killing the dragon.
	 */
	@Test
	public void testExitWithSwordAndDragonKilled(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		char[] movements = {'d','d','d','s','s','s','s','a','a','a','s','s','s','w','w','w','w','w','w','w','d','d','d','d','d','d','d','s','s','s','s','d'};
		
		for(int i=0;i<32;i++){
			maze.heroTurn(movements[i]);
		}

		assertEquals(9,maze.getHero().getX());
		assertEquals(5,maze.getHero().getY());
		assertFalse(maze.getDragon().isAlive());
		assertEquals('A',maze.getHero().getLetter());
		assertTrue(maze.getEndOfGame());
	}
	
	/**
	 * Test responsible for checking the situation where the hero tries to exit the maze without catching the sword and killing the dragon.
	 */
	@Test
	public void testExitWithoutSwordAndDragonAlive(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		char[] movements = {'d','d','d','d','d','d','d','s','s','s','s','d'};
		
		for(int i=0;i<12;i++){
			maze.heroTurn(movements[i]);
		}
		
		assertEquals(8,maze.getHero().getX());
		assertEquals(5,maze.getHero().getY());
		assertFalse(maze.getEndOfGame());
	}
}
