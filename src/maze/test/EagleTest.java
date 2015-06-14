package maze.test;

import static org.junit.Assert.*;

import maze.logic.Maze;

import org.junit.Test;

/**
 * Test class responsible for testing all the eagle features.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */

public class EagleTest {
	
	Maze maze = new Maze();

	/**
	 * Test responsible for checking if the hero keeps the eagle while moving.
	 */
	@Test
	public void testHeroHasEagle(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		char[] movements = {'d','d','d','s','w','a'};
		
		assertTrue(maze.getHero().getHasEagle());
		
		for(int i=0;i<6;i++){
			maze.heroTurn(movements[i]);
		}
		
		assertTrue(maze.getHero().getHasEagle());
	}
	
	/**
	 * Test responsible for checking if the eagle records when its used by the hero.
	 */
	@Test
	public void testHeroUsedEagle(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		assertTrue(maze.getHero().getHasEagle());
		maze.heroTurn('1');
		assertTrue(maze.getEagle().isFlying());
		assertFalse(maze.getHero().getHasEagle());
		assertTrue(maze.getEagle().wasUsed());
	}
	
	/**
	 * Test responsible for checking the eagle movement.
	 */
	@Test
	public void testEagleMovement() {
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		char[] movements = {'1','d','d','d','s','w','d','d','d','a','a','a','a','a'};
		
		maze.heroTurn(movements[0]);
		assertEquals(1,maze.getEagle().getX());
		assertEquals(2,maze.getEagle().getY());
		
		for(int i=1;i<7;i++){
			maze.heroTurn(movements[i]);
		}
		assertEquals(1,maze.getEagle().getX());
		assertEquals(8,maze.getEagle().getY());
		
		for(int i=7;i<14;i++){
			maze.heroTurn(movements[i]);
		}
		
		assertEquals(maze.getEagle().getOldX(),maze.getEagle().getX());
		assertEquals(maze.getEagle().getOldY(),maze.getEagle().getY());
		
	}
	
	/**
	 * Test responsible for checking if the eagle catches the sword.
	 */
	@Test
	public void testEagleHasSword(){
		//initializing the maze components
		maze.initializeMaze(); //original Maze
		maze.setDragonMode(1); //dragon without movement
		
		assertFalse(maze.getEagle().HasSword());
		char[] movements = {'1','d','d','d','d','d','d','d'};
		
		for(int i=0;i<8;i++){
			maze.heroTurn(movements[i]);
		}
		
		assertTrue(maze.getEagle().HasSword());
	}
	

}
