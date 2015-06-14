package maze.cli;
import maze.logic.*;

import java.util.Scanner; //import made to use the scanner to read the key strokes 

/**
 * Class responsible for the command line interface of the game.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 *
 */
public class Main {

	//Maze structure
	static Maze maze = null;
	//Size of the maze
	static int n = 10;

	/**
	 * Main function that launches the game.
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Qual o tipo de labirinto que deseja?");
		System.out.println("1-Labirinto Original");
		System.out.println("2-Labirinto Aleatório");

		//reads the key pressed by the player using the scanner for the maze option
		Scanner scanner = new Scanner (System.in);
		int mazeOption = scanner.nextInt();

		if(mazeOption == 1)
		{
			maze = new Maze();
			maze.initializeMaze();
		}
		else
			if(mazeOption == 2)
			{
				System.out.println("Introduza o tamanho do labirinto pretendido:");
				scanner = new Scanner (System.in);
				n = scanner.nextInt();

				maze = new Maze(n);
				maze.initializeMaze(n);
			}

		System.out.println("Qual o tipo de dragão que pretende?");
		System.out.println("1-Dragão Parado");
		System.out.println("2-Dragão Com Movimentação Aleatória");
		System.out.println("3-Dragão Com Movimentação Aleatória Intercalada Com Dormir");

		//reads the key pressed by the player using the scanner for the dragon mode option
		int dragonMode = scanner.nextInt();

		if(dragonMode==1)
			maze.setDragonMode(1);
		else
			if(dragonMode==2)
				maze.setDragonMode(2);
			else
				if(dragonMode==3)
					maze.setDragonMode(3);

		printMaze();

		while(maze.getEndOfGame() != true)
		{
			//reads the key pressed by the user
			Scanner sc = new Scanner (System.in);
			String input = sc.next();
			char[] keyPressed;
			keyPressed = input.toCharArray();

			//dragon Turn
			maze.dragonTurn();

			//hero turn
			maze.heroTurn(keyPressed[0]);

			printMaze();

			if(maze.getEagleCondition())
			{
				if(maze.getEagleStat()){
					System.out.println("Eagle mode off");}
				else{	
					System.out.println("Eagle mode on");}
			}
		}

		if(maze.getPlayerWon() == true)
			System.out.println("You win!");
		else
			System.out.println("You lose!");
	}

	/**
	 * Function responsible for printing the maze in the command line interface.
	 */
	public static void printMaze(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(maze.getMaze()[i][j]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}
}
