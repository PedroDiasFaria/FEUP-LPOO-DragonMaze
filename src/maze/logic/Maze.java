package maze.logic;
import java.io.Serializable;
import java.util.Random;  //import made to use the random utility to generate a random dragon movement 
import java.util.Stack;

/**
 * Class responsible for the creation and manipulation of the maze used in the game.
 * It contains all the functions responsible for the movement of the maze elements in the maze and the constructor and initializers of that structure.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 * @see Serializable
 */

public class Maze implements Serializable{

	//Maze Structure
	private char maze [][];

	//chars initialization
	//Number of dragons
	private int nDragons = 1; //number of dragons
	
	private int nHero = 0;
	private int nSword = 0;
	private int nExit =0;
	
	//Initializes the hero character
	private Hero hero = new Hero();
	//Initializes the eagle character
	private Eagle eagle = new Eagle();
	//Initializes the exit character
	private Exit exit = new Exit();
	//Initializes the sword character
	private Sword sword = new Sword();
	//Initializes the array responsible for multiple dragons
	private Dragon[] dragons = new Dragon[5];

	{
		for (int i=0;i<5;i++){
			getDragons()[i] = new Dragon();
		}
	}

	//maze size
	private int numberRows = 10;
	private int numberColumns = 10;

	//variable responsible by controlling the end of the game
	private boolean endOfGame = false;

	//variables to check if the player won or lost
	private boolean playerWon = false;
	private boolean playerLost = false;

	//variable responsible for randomly calculating the direction of the dragon move
	private int dragonDirection = 0; 

	//variable responsible for checking if the dragon move is possible
	private boolean dragonMoveNotValid = true;

	//variable responsible for checking if the dragon and the sword were on the same square on the previous turn
	private boolean dragonWasAboveSword = false;

	//variable responsible for setting the dragon to sleep or to be awake
	private int dragonPill = 0;

	//variable responsible for the dragon mode in the game
	//(1-Dragon stopped || 2-Dragon with random movements || 3- Dragon with random movements and with the dragon pill)
	private int dragonMode;

	//variable responsible for controlling the eagle turn
	private boolean isEagleTurn = false;

	//random variable to calculate random values
	private Random randomGenerator = new Random();
	
	/**
	 * Constructor of the random maze which size is specified by the user
	 * @param n Size of the maze
	 */
	public Maze(int n){
		int row,column;

		//creates a maze with NxN of size
		maze = new char [n][n];
		int currX = 0;
		int currY = 0;

		//fills the new maze with 'X's
		for(row=0;row<n;row++)
			for(column=0;column<n;column++){
				maze[row][column]='X';
			}

		for(row=1; row<n; row+=2)
			for(column=1; column<n; column+=2){
				maze[row][column]=' ';
			}
		//creates a random number that is used to define the direction of the exit
		Random randomGenerator = new Random();
		int exitDirection = randomGenerator.nextInt(4)+1;

		boolean impar = false;
		int imparn = 0;

		while(impar == false){
			imparn = randomGenerator.nextInt((n-2)+1);
			if(imparn % 2 == 1)
				impar = true;
		}
		/******/


		switch(exitDirection){
		case 1: 
			exit.setX(imparn);
			exit.setY(0);
			maze[exit.getY()][exit.getX()] = exit.getLetter();
			currX = exit.getX();
			currY = exit.getY()+1;
			maze[currY][currX] = 'V';
			RandomMaze(currX, currY, n);
			break;

		case 2: 
			exit.setX(imparn);
			exit.setY(n-1);
			maze[exit.getY()][exit.getX()] = exit.getLetter();
			currX = exit.getX();
			currY = exit.getY()-1;
			maze[currY][currX] = 'V';
			RandomMaze(currX, currY, n);
			break;

		case 3: 
			exit.setY(imparn);
			exit.setX(0);
			maze[exit.getY()][exit.getX()] = exit.getLetter();
			currX = exit.getX()+1;
			currY = exit.getY();
			maze[currY][currX] = 'V';
			RandomMaze(currX, currY, n);
			break;

		case 4: 
			exit.setY(imparn);
			exit.setX(n-1);
			maze[exit.getY()][exit.getX()] = exit.getLetter();
			currX = exit.getX()-1;
			currY = exit.getY();
			maze[currY][currX] = 'V';
			RandomMaze(currX, currY, n);
			break;

		}

		for(row=0;row<n;row++)
			for(column=0;column<n;column++){
				if(maze[row][column]== 'V')
					maze[row][column]=' ';
			}

	}

	/**
	 * Function responsible for the algorithm of creating the random maze
	 * @param x Current x position of the maze
	 * @param y Current y position of the maze
	 * @param size Size of the maze
	 */
	public void RandomMaze(int x, int y, int size){

		Stack<Integer> auxstack = new Stack<Integer>();

		Random randomGenerator = new Random();
		int direction =0;
		boolean tocreate = true;
		boolean unvisited = true;
		int i = 0;
		int j = 0;

		while(unvisited){

			//NW			
			if(x == 1 && y ==1){
				if((this.maze[y+2][x] == ' ') || (this.maze[y][x+2] == ' ')){

					direction = randomGenerator.nextInt(2);

					while(tocreate){						

						switch(direction){
						case 0: 
							if(this.maze[y][x+2] == ' '){
								this.maze[y][x+1] = 'V';
								this.maze[y][x+2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x+=2;
								tocreate = false;
							}
							else
								direction = 1;
							break;

						case 1:
							if(this.maze[y+2][x] == ' '){
								this.maze[y+1][x] = 'V';
								this.maze[y+2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y+=2;
								tocreate = false;
							}
							else
								direction = 0;
							break;

						}
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}

			//SW
			else if(x == 1 && y == size-2){
				if((this.maze[y-2][x] == ' ') || (this.maze[y][x+2] == ' ')){
					direction = randomGenerator.nextInt(2);

					while(tocreate){


						switch(direction){
						case 0:
							if(this.maze[y][x+2] == ' '){
								this.maze[y][x+1] = 'V';
								this.maze[y][x+2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x+=2;
								tocreate = false;
							}
							else
								direction = 1;
							break;
						case 1:
							if(this.maze[y-2][x] == ' '){
								this.maze[y-1][x] = 'V';
								this.maze[y-2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y-=2;
								tocreate = false;
							}
							else
								direction = 0;
							break;
						}
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}

			//W
			else if(x==1){
				if((this.maze[y-2][x] == ' ')|| (this.maze[y][x+2] == ' ') || (this.maze[y+2][x] == ' ')){
					while(tocreate){
						direction = randomGenerator.nextInt(3);


						switch(direction){
						case 0:
							if(this.maze[y][x+2] == ' '){
								this.maze[y][x+1] = 'V';
								this.maze[y][x+2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x+=2;
								tocreate = false;
							}
							break;
						case 1:
							if(this.maze[y-2][x] == ' '){
								this.maze[y-1][x] = 'V';
								this.maze[y-2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y-=2;
								tocreate = false;
							}
							break;
						case 2:
							if(this.maze[y+2][x] == ' '){
								this.maze[y+1][x] = 'V';
								this.maze[y+2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y+=2;
								tocreate = false;
							}
							break;
						}					
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}

			//NE
			else if(y == 1 && x == size-2){
				if((this.maze[y+2][x] == ' ') || (this.maze[y][x-2] == ' ')){
					direction = randomGenerator.nextInt(2);
					while(tocreate){


						switch(direction){
						case 0:
							if(this.maze[y][x-2] == ' '){
								this.maze[y][x-1] = 'V';
								this.maze[y][x-2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x-=2;
								tocreate = false;
							}
							else
								direction = 1;
							break;
						case 1:
							if(this.maze[y+2][x] == ' '){
								this.maze[y+1][x] = 'V';
								this.maze[y+2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y+=2;
								tocreate = false;
							}
							else 
								direction = 0;
							break;
						}
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}

			//SE
			else if(x == size-2 && y == size-2){
				if((this.maze[y-2][x] == ' ')|| (this.maze[y][x-2] == ' ')){
					direction = randomGenerator.nextInt(2);
					while(tocreate){


						switch(direction){
						case 0:
							if(this.maze[y][x-2] == ' '){
								this.maze[y][x-1] = 'V';
								this.maze[y][x-2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x-=2;
								tocreate = false;
							}
							else
								direction = 1;
							break;
						case 1:
							if(this.maze[y-2][x] == ' '){
								this.maze[y-1][x] = 'V';
								this.maze[y-2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y-=2;
								tocreate = false;
							}
							else
								direction = 0;
							break;
						}
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}

			//E
			else if(x==size-2){
				if((this.maze[y-2][x] == ' ')|| (this.maze[y][x-2] == ' ') || (this.maze[y+2][x] == ' ')){
					while(tocreate){
						direction = randomGenerator.nextInt(3);

						switch(direction){
						case 0:
							if(this.maze[y][x-2] == ' '){
								this.maze[y][x-1] = 'V';
								this.maze[y][x-2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x-=2;
								tocreate = false;
							}
							break;
						case 1:
							if(this.maze[y-2][x] == ' '){
								this.maze[y-1][x] = 'V';
								this.maze[y-2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y-=2;
								tocreate = false;
							}
							break;
						case 2:
							if(this.maze[y+2][x] == ' '){
								this.maze[y+1][x] = 'V';
								this.maze[y+2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y+=2;
								tocreate = false;
							}
							break;
						}					
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}

			//N
			else if(y==1){
				if((this.maze[y+2][x] == ' ')|| (this.maze[y][x-2] == ' ') || (this.maze[y][x+2] == ' ')){
					while(tocreate){
						direction = randomGenerator.nextInt(3);

						switch(direction){
						case 0:
							if(this.maze[y][x-2] == ' '){
								this.maze[y][x-1] = 'V';
								this.maze[y][x-2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x=x-2;
								tocreate = false;
							}
							break;
						case 1:
							if(this.maze[y][x+2] == ' '){
								this.maze[y][x+1] = 'V';
								this.maze[y][x+2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x=x+2;
								tocreate = false;
							}
							break;
						case 2:
							if(this.maze[y+2][x] == ' '){
								this.maze[y+1][x] = 'V';
								this.maze[y+2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y=y+2;
								tocreate = false;
							}
							break;
						}					
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}

			//S
			else if(y==size-2){
				if((this.maze[y-2][x] == ' ')|| (this.maze[y][x-2] == ' ') || (this.maze[y][x+2] == ' ')){
					while(tocreate){
						direction = randomGenerator.nextInt(3);

						switch(direction){
						case 0:
							if(this.maze[y][x-2] == ' '){
								this.maze[y][x-1] = 'V';
								this.maze[y][x-2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x-=2;
								tocreate = false;
							}
							break;
						case 1:
							if(this.maze[y-2][x] == ' '){
								this.maze[y-1][x] = 'V';
								this.maze[y-2][x] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								y-=2;
								tocreate = false;
							}
							break;
						case 2:
							if(this.maze[y][x+2] == ' '){
								this.maze[y][x+1] = 'V';
								this.maze[y][x+2] = 'V';
								auxstack.push(x);
								auxstack.push(y);
								x+=2;
								tocreate = false;
							}
							break;
						}					
					}
				}
				else if(auxstack.isEmpty() == false){
					y=auxstack.pop();
					x=auxstack.pop();
				}

				else if(auxstack.isEmpty() == true){
					//Check for unvisited
					for(i=0; i<size; i++){
						for(j=0; j<size; j++){
							if(this.maze[j][i]==' '){
								x=i;
								y=j;
								this.maze[y][x] = 'V';
								break;
							}
						}
						if(j!= size)
							break;
					}
					if((i==size) && (j==size))
						unvisited = false;
					//********************
				}
			}
			//Qualquer outro caso
			else if((this.maze[y-2][x] == ' ')|| (this.maze[y][x-2] == ' ') || (this.maze[y][x+2] == ' ') || (this.maze[y+2][x] == ' ')){

				while(tocreate){
					direction = randomGenerator.nextInt(4);

					switch(direction){
					case 0:
						if(this.maze[y][x-2] == ' '){
							this.maze[y][x-1] = 'V';
							this.maze[y][x-2] = 'V';
							auxstack.push(x);
							auxstack.push(y);
							x-=2;
							tocreate = false;
						}
						break;
					case 1:
						if(this.maze[y-2][x] == ' '){
							this.maze[y-1][x] = 'V';
							this.maze[y-2][x] = 'V';
							auxstack.push(x);
							auxstack.push(y);
							y-=2;
							tocreate = false;
						}
						break;
					case 2:
						if(this.maze[y][x+2] == ' '){
							this.maze[y][x+1] = 'V';
							this.maze[y][x+2] = 'V';
							auxstack.push(x);
							auxstack.push(y);
							x+=2;
							tocreate = false;
						}
						break;
					case 3:
						if(this.maze[y+2][x] == ' '){
							this.maze[y+1][x] = 'V';
							this.maze[y+2][x] = 'V';
							auxstack.push(x);
							auxstack.push(y);
							y+=2;
							tocreate = false;
						}
						break;
					}					
				}
			}

			else if(auxstack.isEmpty() == false){
				y=auxstack.pop();
				x=auxstack.pop();
			}

			else if(auxstack.isEmpty() == true){
				//Check for unvisited
				for(i=0; i<size; i++){
					for(j=0; j<size; j++){
						if(this.maze[j][i]==' '){
							x=i;
							y=j;
							this.maze[y][x] = 'V';
							break;
						}
					}
					if(j!= size)
						break;
				}
				if((i==size) && (j==size))
					unvisited = false;
				//********************
			}

			tocreate = true;
		}
	}

	/**
	 * Constructor of the standard maze
	 */
	public Maze()
	{
		maze = new char [][]{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}	};
	}

	/**
	 * Constructor of a maze with or without walls inside
	 */
	public Maze(int n, char c){
		int row,column;
		
		maze = new char[n][n];
		
		for(row=0;row<n;row++)
			for(column=0;column<n;column++){
				maze[row][column]='X';
			}

		//if empty
		if(c == 'e')
		for(row=1; row<n-1; row++)
			for(column=1; column<n-1; column++){
				maze[row][column]=' ';
			}
		
		//if full
		else
			if(c == 'f'){
					}
		this.setNumberColumns(n);
		this.setNumberRows(n);
		
	}

	public char[][] getMaze() {
		return maze;
	}

	public void setMaze(char[][] maze) {
		this.maze = maze;
	}

	/**
	 * Function responsible for initializing the standard maze.
	 * It initializes all the chars on pre-calculated coordinates of the maze.
	 */
	public void initializeMaze()
	{
		maze[hero.getY()][hero.getX()]=hero.getLetter();
		maze[sword.getY()][sword.getX()]=sword.getLetter();
		maze[getDragons()[0].getY()][getDragons()[0].getX()]=getDragons()[0].getLetter();
		maze[exit.getY()][exit.getX()]=exit.getLetter();
		this.setNumberOfDragons(1);
	}

	/**
	 * Function responsible for initializing the random maze with the size m x m.
	 * It initializes all the chars randomly on the maze.
	 * @param m Size of the random maze
	 */
	public void initializeMaze(int m)
	{
		//refreshes the row and columns size
		numberRows = m;
		numberColumns = m;

		//Generates random positions to the maze characters
		Random randomGenerator = new Random();
		int heroX = randomGenerator.nextInt(m);
		int heroY = randomGenerator.nextInt(m);
		int dragonX = randomGenerator.nextInt(m);
		int dragonY = randomGenerator.nextInt(m);
		int swordX = randomGenerator.nextInt(m);
		int swordY = randomGenerator.nextInt(m);

		while(maze[heroY][heroX]!=' ')
		{
			heroX = randomGenerator.nextInt(m);
			heroY = randomGenerator.nextInt(m);
		}

		//set the new coordinates
		hero.setX(heroX);hero.setY(heroY);
		eagle.setX(heroX);eagle.setY(heroY);
		//draws the hero on the maze
		maze[hero.getY()][hero.getX()] = hero.getLetter();

		for(int i=0;i<nDragons;i++)
		{
			dragonX = randomGenerator.nextInt(m);
			dragonY = randomGenerator.nextInt(m);

			while(maze[dragonY][dragonX] != ' ')
			{
				dragonX = randomGenerator.nextInt(m);
				dragonY = randomGenerator.nextInt(m);
			}

			//set the new coordinates
			getDragons()[i].setX(dragonX);getDragons()[i].setY(dragonY);
			//draws the dragon on the maze
			maze[getDragons()[i].getY()][getDragons()[i].getX()] = getDragons()[i].getLetter();
		}

		while(maze[swordY][swordX] != ' ')
		{
			swordX = randomGenerator.nextInt(m);
			swordY = randomGenerator.nextInt(m);
		}

		//set the new coordinates
		sword.setX(swordX);sword.setY(swordY);
		//draws the sword on the maze
		maze[sword.getY()][sword.getX()] = sword.getLetter();
	}

	/**
	 * Function responsible for the hero movements on the maze
	 * @param direction Key pressed by the user
	 */ 
	public void heroTurn(char direction)
	{	
		switch(direction)
		{
		//hero goes up
		case 'w':	if(maze[hero.getY()-1][hero.getX()] != 'X' && maze[hero.getY()-1][hero.getX()] != 'g') //checks if there is a obstacle in the hero path
		{
			//it erases the old position of the hero
			maze[hero.getY()][hero.getX()] = ' ';

			//checks if there is a sword in the hero path
			if(maze[hero.getY()-1][hero.getX()] == 'E' || maze[hero.getY()-1][hero.getX()] == 'G'){
				if(maze[hero.getY()-1][hero.getX()] == 'G'  && !eagle.isFlying()){
					hero.setLetter('A');
					hero.setHasEagle(true);
					sword.setLetter(' ');
					hero.setArmed(true);
				}else
					if(maze[hero.getY()-1][hero.getX()] == 'E'){
						hero.setLetter('A');
						sword.setLetter(' ');
						hero.setArmed(true);
					}
			}

			//puts the hero in the new position
			maze[hero.getY()-1][hero.getX()] = hero.getLetter();
			hero.setY(hero.getY()-1);

			if(hero.getHasEagle())
				eagle.setY(eagle.getY()-1);
		}
		break;
		//hero goes left
		case 'a':	if(maze[hero.getY()][hero.getX()-1] != 'X' && maze[hero.getY()][hero.getX()-1] != 'g') //checks if there is a obstacle in the hero path
		{
			//it erases the old position of the hero
			maze[hero.getY()][hero.getX()] = ' ';

			//checks if there is a sword in the hero path
			if(maze[hero.getY()][hero.getX()-1] == 'E' || maze[hero.getY()][hero.getX()-1] == 'G') {
				if(maze[hero.getY()][hero.getX()-1] == 'G'  && !eagle.isFlying()){
					hero.setLetter('A');
					hero.setHasEagle(true);
					sword.setLetter(' ');
					hero.setArmed(true);
				}else
					if(maze[hero.getY()][hero.getX()-1] == 'E'){
						hero.setLetter('A');
						sword.setLetter(' ');
						hero.setArmed(true);
					}
			}

			//puts the hero in the new position
			maze[hero.getY()][hero.getX()-1] = hero.getLetter();
			hero.setX(hero.getX()-1);

			if(hero.getHasEagle())
				eagle.setX(eagle.getX()-1);
		}
		break;
		//hero goes down 
		case 's':	if(maze[hero.getY()+1][hero.getX()] != 'X' && maze[hero.getY()+1][hero.getX()] != 'g') //checks if there is a obstacle in the hero path
		{
			//it erases the old position of the hero
			maze[hero.getY()][hero.getX()] = ' ';

			//checks if there is a sword in the hero path
			if(maze[hero.getY()+1][hero.getX()] == 'E' || maze[hero.getY()+1][hero.getX()] == 'G') {
				if(maze[hero.getY()+1][hero.getX()] == 'G'  && !eagle.isFlying()){
					hero.setLetter('A');
					hero.setHasEagle(true);
					sword.setLetter(' ');
					hero.setArmed(true);
				}else
					if(maze[hero.getY()+1][hero.getX()] == 'E'){
						hero.setLetter('A');
						sword.setLetter(' ');
						hero.setArmed(true);
					}
			}

			//puts the hero in the new position
			maze[hero.getY()+1][hero.getX()] = hero.getLetter();
			hero.setY(hero.getY()+1);

			if(hero.getHasEagle())
				eagle.setY(eagle.getY()+1);
		}
		break;
		//hero goes right
		case 'd':	if(maze[hero.getY()][hero.getX()+1] != 'X' && maze[hero.getY()][hero.getX()+1] != 'g') //checks if there is a obstacle in the hero path
		{
			//it erases the old position of the hero
			maze[hero.getY()][hero.getX()] = ' ';

			//checks if there is a sword in the hero path
			if(maze[hero.getY()][hero.getX()+1] == 'E' || maze[hero.getY()][hero.getX()+1] == 'G'){
				if(maze[hero.getY()][hero.getX()+1] == 'G'  && !eagle.isFlying()){
					hero.setLetter('A');
					hero.setHasEagle(true);
					sword.setLetter(' ');
					hero.setArmed(true);
				}else
					if(maze[hero.getY()][hero.getX()+1] == 'E'){
						hero.setLetter('A');
						sword.setLetter(' ');
						hero.setArmed(true);
					}

			}

			//puts the hero in the new position
			maze[hero.getY()][hero.getX()+1] = hero.getLetter();
			hero.setX(hero.getX()+1);

			if(hero.getHasEagle())
				eagle.setX(eagle.getX()+1);
		}
		break;
		//eagle activated
		case '1':	if(hero.getHasEagle() && !eagle.wasUsed() && !hero.isArmed())
			eagleTurn();
		break;



		}


		//checks if the hero is at the exit of the dungeon
		if(hero.getX() == exit.getX() && hero.getY() == exit.getY() && hero.isArmed() && checkIfDragonsAreDead(0))
		{
			maze[exit.getY()][hero.getX()] = 'A';
			endOfGame = true;
			this.setPlayerWon(true);

		}
		else 
			if((hero.getX() == exit.getX() && hero.getY() == exit.getY()) && !checkIfDragonsAreDead(0))
			{

				if(hero.getX()==numberColumns-1)
				{
					maze[exit.getY()][exit.getX()] = exit.getLetter();
					maze[hero.getY()][hero.getX()-1] = hero.getLetter();
					hero.setX(hero.getX()-1);
					if(hero.getHasEagle())
						eagle.setX(eagle.getX()-1);
				}
				else
					if(hero.getX()==0)
					{
						maze[exit.getY()][exit.getX()] = exit.getLetter();
						maze[hero.getY()][hero.getX()+1] = hero.getLetter();
						hero.setX(hero.getX()+1);
						if(hero.getHasEagle())
							eagle.setX(eagle.getX()+1);
					}
					else
						if(hero.getY()==numberRows-1)
						{
							maze[exit.getY()][exit.getX()] = exit.getLetter();
							maze[hero.getY()-1][hero.getX()] = hero.getLetter();
							hero.setY(hero.getY()-1);
							if(hero.getHasEagle())
								eagle.setY(eagle.getX()-1);
						}
						else
							if(hero.getY()==0)
							{
								maze[exit.getY()][exit.getX()] = exit.getLetter();
								maze[hero.getY()+1][hero.getX()] = hero.getLetter();
								hero.setY(hero.getY()+1);
								if(hero.getHasEagle())
									eagle.setY(eagle.getX()+1);
							}
			}

		for(int i=0;i<nDragons;i++)
		{
			//checks if the hero is in a square near the dragons[n]
			if((hero.getX()-1 == getDragons()[i].getX() && hero.getY() == getDragons()[i].getY()) || (hero.getY()-1 == getDragons()[i].getY() && hero.getX() == getDragons()[i].getX()) || (hero.getX()+1 == getDragons()[i].getX() && hero.getY() == getDragons()[i].getY()) || (hero.getY()+1 == getDragons()[i].getY() && hero.getX() == getDragons()[i].getX()))
			{
				//checks it the hero is in a square near the dragons[i] and is armed
				if(hero.getLetter() == 'A')
				{
					maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					getDragons()[i].setAlive(false);
					//maze.printMaze();
				}else//checks if the hero is in a square near the dragons[i] and is unarmed
					if(!getDragons()[i].isAsleep()) //checks if the dragons[i] is asleep				
					{
						this.setPlayerLost(true);
						endOfGame = true;
					}
			}
		}

		if(!hero.getHasEagle() && eagle.isAlive() && isEagleTurn)
			eagleTurn();
	}

	/**
	 * Function responsible for checking if all the dragons in the maze are dead
	 * @param i position to check in the dragons array
	 * @return false if some dragon is alive or true if all dragons are dead.
	 */
	public boolean checkIfDragonsAreDead(int i){

		if(getDragons()[i].isAlive()){
			return false;
		}
		else
			if(i==nDragons-1){
				if(getDragons()[i].isAlive()){
					return false;
				}
				else
					return true;
			}

		return checkIfDragonsAreDead(i+1);
	}

	/**
	 * Function responsible for the dragon(s) movement(s)
	 */
	public void dragonTurn()
	{
		for(int i=0;i<nDragons;i++)
		{
			if(getDragons()[i].isAlive()){
				//resets the dragon move checker
				dragonMoveNotValid = true;

				//creates the random dragon pill to make the dragon go to sleep (1-awake || 0-asleep)
				dragonPill = randomGenerator.nextInt(2);

				//sees the value of the dragon pill
				if(dragonMode == 3)
				{
					if(dragonPill == 0)
					{
						getDragons()[i].setAsleep(true);
						getDragons()[i].setLetter('d');
						maze[getDragons()[i].getY()][getDragons()[i].getX()] = getDragons()[i].getLetter();
					}else
					{
						getDragons()[i].setAsleep(false);
						getDragons()[i].setLetter('D');
					}
				}

				//It Moves the dragon on a random direction if he is alive and awake
				if(getDragons()[i].isAlive() && !getDragons()[i].isAsleep() && dragonMode!=1)
				{
					//loop to guarantee that the dragon movement dont overlay the 'X' marks on the maze
					while(dragonMoveNotValid == true)
					{
						//randomly calculates the next movement of the dragon
						dragonDirection = randomGenerator.nextInt(5); // || 0-do not move || 1-up || 2-down || 3-left || 4-right

						switch(dragonDirection)
						{
						//do not move
						case 0 : dragonMoveNotValid = false;
						break;
						//up
						case 1 : if(maze[getDragons()[i].getY()-1][getDragons()[i].getX()] == ' ' || maze[getDragons()[i].getY()-1][getDragons()[i].getX()] == sword.getLetter())
							dragonMoveNotValid = false;
						break;
						//down
						case 2: if(maze[getDragons()[i].getY()+1][getDragons()[i].getX()] == ' ' || maze[getDragons()[i].getY()+1][getDragons()[i].getX()] == sword.getLetter())
							dragonMoveNotValid = false;
						break;
						//left
						case 3: if(maze[getDragons()[i].getY()][getDragons()[i].getX()-1] == ' ' || maze[getDragons()[i].getY()][getDragons()[i].getX()-1] == sword.getLetter()) 
							dragonMoveNotValid = false;
						break;
						//right
						case 4: if(maze[getDragons()[i].getY()][getDragons()[i].getX()+1] == ' ' || maze[getDragons()[i].getY()][getDragons()[i].getX()+1] == sword.getLetter())
							dragonMoveNotValid = false;
						break;
						}

					}

					//cycle to move the dragon
					switch(dragonDirection)
					{
					//do not move
					case 0 : break;
					//up
					case 1 : maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()-1][getDragons()[i].getX()] = 'D';
					getDragons()[i].setY(getDragons()[i].getY()-1);
					break;
					//down
					case 2: maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()+1][getDragons()[i].getX()] = 'D';
					getDragons()[i].setY(getDragons()[i].getY()+1);
					break;
					//left
					case 3: maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()][getDragons()[i].getX()-1] = 'D';
					getDragons()[i].setX(getDragons()[i].getX()-1);
					break;
					//right
					case 4: maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()][getDragons()[i].getX()+1] = 'D';
					getDragons()[i].setX(getDragons()[i].getX()+1);
					break;
					}
				}

				//checks if the dragon was above the sword in the previous turn
				if(dragonWasAboveSword == true)
				{
					maze[sword.getY()][sword.getX()] = sword.getLetter();
					dragonWasAboveSword = false;
				}

				//checks if the dragon and the sword are in the same square
				if(getDragons()[i].getX() == sword.getX() && getDragons()[i].getY() == sword.getY())
				{
					maze[getDragons()[i].getY()][getDragons()[i].getX()] = 'F';
					dragonWasAboveSword = true;
				}

				//checks if there is a eagle in an adjacent square
				if(((eagle.getX()== getDragons()[i].getX()-1  && eagle.getY() == getDragons()[i].getY()) || (eagle.getY() == getDragons()[i].getY()-1 && eagle.getX() == getDragons()[i].getX()) || (eagle.getX() == getDragons()[i].getX()+1 && hero.getY() == getDragons()[i].getY()) || (eagle.getY() == getDragons()[i].getY()+1 && eagle.getX() == getDragons()[i].getX())) && !eagle.isFlying() && !hero.isArmed())
				{
					eagle.setAlive(false);
					maze[eagle.getOldY()][eagle.getOldX()] = 'E';
				}
			}
		}
	}

	/**
	 * Same function as dragonTurn() but made to test the dragon conditions in Junit
	 * @param dragonDirection Direction of the movement of the dragon
	 * @param isAsleep false if the dragon is to be sleeping or true if the dragon is to be awake
	 */
	public void dragonTurn(int dragonDirection,boolean isAsleep){
		for(int i=0;i<nDragons;i++)
		{
			//resets the dragon move checker
			dragonMoveNotValid = true;

			//creates the random dragon pill to make the dragon go to sleep (1-awake || 0-asleep)
			dragonPill = randomGenerator.nextInt(2);

			//puts the dragon to sleep is isAsleep is true
			if(isAsleep)
			{
				getDragons()[i].setAsleep(true);
				getDragons()[i].setLetter('d');
				maze[getDragons()[i].getY()][getDragons()[i].getX()] = getDragons()[i].getLetter();
			}else
			{
				getDragons()[i].setAsleep(false);
				getDragons()[i].setLetter('D');
			}

			//It Moves the dragon on a random direction if he is alive and awake
			if(getDragons()[i].isAlive() && !getDragons()[i].isAsleep())
			{
				//loop to guarantee that the dragon movement dont overlay the 'X' marks on the maze
				switch(dragonDirection)
				{
				//do not move
				case 0 : dragonMoveNotValid = false;
				break;
				//up
				case 1 : if(maze[getDragons()[i].getY()-1][getDragons()[i].getX()] == ' ' || maze[getDragons()[i].getY()-1][getDragons()[i].getX()] == sword.getLetter())
					dragonMoveNotValid = false;
				break;
				//down
				case 2: if(maze[getDragons()[i].getY()+1][getDragons()[i].getX()] == ' ' || maze[getDragons()[i].getY()+1][getDragons()[i].getX()] == sword.getLetter())
					dragonMoveNotValid = false;
				break;
				//left
				case 3: if(maze[getDragons()[i].getY()][getDragons()[i].getX()-1] == ' ' || maze[getDragons()[i].getY()][getDragons()[i].getX()-1] == sword.getLetter()) 
					dragonMoveNotValid = false;
				break;
				//right
				case 4: if(maze[getDragons()[i].getY()][getDragons()[i].getX()+1] == ' ' || maze[getDragons()[i].getY()][getDragons()[i].getX()+1] == sword.getLetter())
					dragonMoveNotValid = false;
				break;
				}

				if(!dragonMoveNotValid){
					//cycle to move the dragon
					switch(dragonDirection)
					{
					//do not move
					case 0 : break;
					//up
					case 1 : maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()-1][getDragons()[i].getX()] = 'D';
					getDragons()[i].setY(getDragons()[i].getY()-1);
					break;
					//down
					case 2: maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()+1][getDragons()[i].getX()] = 'D';
					getDragons()[i].setY(getDragons()[i].getY()+1);
					break;
					//left
					case 3: maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()][getDragons()[i].getX()-1] = 'D';
					getDragons()[i].setX(getDragons()[i].getX()-1);
					break;
					//right
					case 4: maze[getDragons()[i].getY()][getDragons()[i].getX()] = ' ';
					maze[getDragons()[i].getY()][getDragons()[i].getX()+1] = 'D';
					getDragons()[i].setX(getDragons()[i].getX()+1);
					break;
					}
				}
			}

			//checks if the dragon was above the sword in the previous turn
			if(dragonWasAboveSword == true)
			{
				maze[sword.getY()][sword.getX()] = sword.getLetter();
				dragonWasAboveSword = false;
			}

			//checks if the dragon and the sword are in the same square
			if(getDragons()[i].getX() == sword.getX() && getDragons()[i].getY() == sword.getY())
			{
				maze[getDragons()[i].getY()][getDragons()[i].getX()] = 'F';
				dragonWasAboveSword = true;
			}

			//checks if there is a eagle in an adjacent square
			if(((eagle.getX()== getDragons()[i].getX()-1  && eagle.getY() == getDragons()[i].getY()) || (eagle.getY() == getDragons()[i].getY()-1 && eagle.getX() == getDragons()[i].getX()) || (eagle.getX() == getDragons()[i].getX()+1 && hero.getY() == getDragons()[i].getY()) || (eagle.getY() == getDragons()[i].getY()+1 && eagle.getX() == getDragons()[i].getX())) && !eagle.isFlying())
			{
				eagle.setAlive(false);
				maze[eagle.getY()][eagle.getX()] = 'E';
			}
		}
	}

	/**
	 * Function responsible for the eagle movements
	 */
	public void eagleTurn()
	{
		//if the user just pressed 1 for the first time
		if(isEagleTurn == false)
		{
			eagle.setOldX(eagle.getX());
			eagle.setOldY(eagle.getY());
			hero.setHasEagle(false);
			eagle.setWasUsed(true);
			eagle.setFlying(true);
			isEagleTurn = true;
			return;
		}

		for(int i=0;i<nDragons;i++)
		{
			if(eagle.getOldChar()=='D' && dragonMode != 1)
			{
				if(getDragons()[i].getX()!=eagle.getX() || getDragons()[i].getY()!=eagle.getY())
					maze[eagle.getY()][eagle.getX()]=' ';
				else
					maze[eagle.getY()][eagle.getX()]='D';
			}
			else
				if(eagle.getOldChar()!='L')//&& eagle.getOldChar()!='D')
					maze[eagle.getY()][eagle.getX()]=eagle.getOldChar();
		}

		//if the eagle is still on the move
		if(isEagleTurn && !eagle.HasSword())
		{	
			if(sword.getY() < eagle.getY())
			{
				eagle.setOldChar(maze[eagle.getY()-1][eagle.getX()]);
				eagle.setY(eagle.getY()-1);
				maze[eagle.getY()][eagle.getX()] = 'g';
			}else
				if(sword.getY() > eagle.getY())
				{
					eagle.setOldChar(maze[eagle.getY()+1][eagle.getX()]);
					eagle.setY(eagle.getY()+1);
					maze[eagle.getY()][eagle.getX()] = 'g';
				}else
					if(sword.getX() < eagle.getX())
					{
						eagle.setOldChar(maze[eagle.getY()][eagle.getX()-1]);
						eagle.setX(eagle.getX()-1);
						maze[eagle.getY()][eagle.getX()] = 'g';
					}else
						if(sword.getX() > eagle.getX())
						{
							eagle.setOldChar(maze[eagle.getY()][eagle.getX()+1]);
							eagle.setX(eagle.getX()+1);
							maze[eagle.getY()][eagle.getX()] = 'g';
						}

			if(eagle.getX()==sword.getX() && eagle.getY()==sword.getY())
			{
				eagle.setHasSword(true);
				eagle.setOldChar(' ');
				maze[eagle.getY()][eagle.getX()] = 'G';
				eagle.setFlying(false);
				return;
			}
		}

		if(isEagleTurn && eagle.HasSword())
		{
			//maze[eagle.getY()][eagle.getX()]=eagle.getOldChar();

			if(!eagle.isFlying())
				eagle.setFlying(true);

			//move the eagle to the old position of the hero 			
			if(eagle.getOldY() < eagle.getY())
			{
				eagle.setOldChar(maze[eagle.getY()-1][eagle.getX()]);
				eagle.setY(eagle.getY()-1);
				maze[eagle.getY()][eagle.getX()] = 'G';
			}else
				if(eagle.getOldY() > eagle.getY())
				{
					eagle.setOldChar(maze[eagle.getY()+1][eagle.getX()]);
					eagle.setY(eagle.getY()+1);
					maze[eagle.getY()][eagle.getX()] = 'G';
				}else
					if(eagle.getOldX() < eagle.getX())
					{
						eagle.setOldChar(maze[eagle.getY()][eagle.getX()-1]);
						eagle.setX(eagle.getX()-1);
						maze[eagle.getY()][eagle.getX()] = 'G';
					}else
						if(eagle.getOldX() > eagle.getX())
						{
							eagle.setOldChar(maze[eagle.getY()][eagle.getX()+1]);
							eagle.setX(eagle.getX()+1);
							maze[eagle.getY()][eagle.getX()] = 'G';
						}

			if(eagle.getX() == eagle.getOldX() && eagle.getY() == eagle.getOldY())
			{
				isEagleTurn = false;
				eagle.setFlying(false);
				maze[eagle.getY()][eagle.getX()] = 'G';
				sword.setX(eagle.getX());sword.setY(eagle.getY());
			}
		}
	}

	/**
	 * Gets the endOfGame variable
	 * @return true if the game has ended or false if the game is still active
	 */
	public boolean getEndOfGame() {
		return endOfGame;
	}

	/**
	 * Sets the endOfGame variable
	 * @param endOfGame true if the game has ended or false if the game is still active
	 */
	public void setEndOfGame(boolean endOfGame) {
		this.endOfGame = endOfGame;
	}

	/**
	 * Gets the playerWon variable
	 * @return true if the player has won or false if it hasn't
	 */
	public boolean getPlayerWon() {
		return playerWon;
	}

	/**
	 * Set playerWon variable
	 * @param playerWon true if the player has won or false if it hasn't
	 */
	public void setPlayerWon(boolean playerWon) {
		this.playerWon = playerWon;
	}

	/**
	 * Gets the playerLost variable
	 * @return true if the player has lost or false if it hasn't
	 */
	public boolean getPlayerLost() {
		return playerLost;
	}

	/**
	 * Sets the playerLost variable
	 * @param playerLost true if the player has lost or false if it hasn't
	 */
	public void setPlayerLost(boolean playerLost) {
		this.playerLost = playerLost;
	}

	/**
	 * Gets the dragonMode variable
	 * @return 1 if Dragon stopped || 2 if Dragon with random movements || 3 if Dragon with random movements and with the dragon pill
	 */
	public int getDragonMode() {
		return dragonMode;
	}

	/**
	 * Sets the dragonMode variable
	 * @param dragonMode 1 if Dragon stopped || 2 if Dragon with random movements || 3 if Dragon with random movements and with the dragon pill
	 */
	public void setDragonMode(int dragonMode) {
		this.dragonMode = dragonMode;
	}

	/**
	 * Gets the eagleStat variable
	 * @return true if hero has eagle or false if it hasn't
	 */
	public boolean getEagleStat(){
		return this.hero.getHasEagle();}

	/**
	 * Gets the eagleCondition variable
	 * @return true if the eagle is alive or false if it isn't
	 */
	public boolean getEagleCondition(){
		return this.eagle.isAlive();
	}

	/**
	 * Gets the dragonPill variable
	 * @return 0 is dragon is to be awake or 1 if dragon is to be asleep
	 */
	public int getDragonPill() {
		return dragonPill;
	}

	/**
	 * Sets the dragonPill variable
	 * @param dragonPill
	 */
	public void setDragonPill(int dragonPill) {
		this.dragonPill = dragonPill;
	}

	/**
	 * Gets the dragonWasAboveSword variable
	 * @return true if dragon was above sword or false it it wasn't
	 */
	public boolean getDragonWasAboveSword() {
		return dragonWasAboveSword;
	}

	/**
	 * Set dragonWasAboveSword variable
	 * @param dragonWasAboveSword true if dragon was above sword or false it it wasn't
	 */
	public void setDragonWasAboveSword(boolean dragonWasAboveSword) {
		this.dragonWasAboveSword = dragonWasAboveSword;
	}

	/**
	 * Gets the hero variable
	 * @return the hero object
	 */
	public Hero getHero(){
		return hero;
	}

	/**
	 * Gets the first element of the dragon array
	 * @return the first element of the dragon array
	 */
	public Dragon getDragon(){
		return getDragons()[0];
	}
	
	/**
	 * Gets the exit variable
	 * @return the exit object
	 */
	public Exit getExit(){
		return exit;
	}
	
	/**
	 * Gets the sword variable
	 * @return the sword object
	 */
	public Sword getSword(){
		return sword;
	}

	/**
	 * Gets the eagle variable
	 * @return the eagle object
	 */
	public Eagle getEagle(){
		return eagle;
	}

	/**
	 * Gets the numberRows variable
	 * @return number of rows
	 */
	public int getNumberRows() {
		return numberRows;
	}

	/**
	 * Set the numberRows variable
	 * @param numberRows new number of rows of the maze
	 */
	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}

	/**
	 * Gets the numberColumns variable
	 * @return the number of columns of the maze
	 */
	public int getNumberColumns() {
		return numberColumns;
	}

	/**
	 * Set the numberColums variable
	 * @param numberColumns new number od columns of the maze
	 */
	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}

	/**
	 * Get the numberOfDragons variable
	 * @return the number of dragons in the game
	 */
	public int getNumberOfDragons(){
		return nDragons;
	}

	/**
	 * Set the numberOfDragons variable
	 * @param nDragons new number of dragons in the game
	 */
	public void setNumberOfDragons(int nDragons) {
		this.nDragons = nDragons;
	}

	/**
	 * Gets the number of heroes in the CharPanel in the MazeDialog
	 * @return the number of heroes
	 */
	public int getnHero() {
		return nHero;
	}

	/**
	 * Set the number of heroes in the CharPanel in the MazeDialog
	 * @param nHero new number of heroes
	 */
	public void setnHero(int nHero) {
		this.nHero = nHero;
	}

	/**
	 * Get the number of swords in the CharPanel in the MazeDialog
	 * @return the number of swords
	 */
	public int getnSword() {
		return nSword;
	}

	/**
	 * Set the number of swords in the CharPanel in the MazeDialog
	 * @param nSword the new number of swords
	 */
	public void setnSword(int nSword) {
		this.nSword = nSword;
	}

	/**
	 * Get the number of exit in the CharPanel in the MazeDialog
	 * @return the number of exits
	 */
	public int getnExit() {
		return nExit;
	}

	/**
	 * Set the number of exit in the CharPanel in the MazeDialog
	 * @param nExit the new number of exits
	 */
	public void setnExit(int nExit) {
		this.nExit = nExit;
	}

	/**
	 * Get the array of dragons of the CharPanel in the MazeDialog
	 * @return the array of dragons
	 */
	public Dragon[] getDragons() {
		return dragons;
	}

	/**
	 * Set the array of dragons of the CharPanel in the MazeDialog
	 * @param dragons the new array of dragons
	 */
	public void setDragons(Dragon[] dragons) {
		this.dragons = dragons;
	}
}
