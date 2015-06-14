package maze.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

import maze.logic.Maze;
import maze.gui.GameFiles;
/**
 * Class responsible for creating the frame in which all the graphic components are created and also the Menu bar on the top of the frame.
 * It contains the maze structure for the game,and all the variables responsible for allowing the games to begin and being created.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class GUI {

	//maze structure for the game
	private static Maze maze = null;
	//primary frame
	private static JFrame frame=null;
	//flag to see if a game is on
	static boolean gameFlag;
	//variable responsible for passing the maze size to the maze structure
	static int mazeSize = 10;
	//flag to see if there is a game currently on
	private static boolean existGame = false;
	//jDialog for the options menu
	static Options options;
	//boolean to see if a button was pressed on the charPanel in the CustomMazeDialog 
	private static boolean buttonPressedFlag = false;
	//char variable to see what maze char was selected in the charPanel in the CustomMazeDialog
	private static char charSelected;	
	//variable responsible for passing the number of dragons to the maze structure
	static int numberDragons = 0;

	//key variables
	private static int upKey = KeyEvent.VK_W;
	private static int downKey = KeyEvent.VK_S;
	private static int rightKey = KeyEvent.VK_D;
	private static int leftKey = KeyEvent.VK_A;
	private static int eagleKey = KeyEvent.VK_1;

	/**
	 * Function responsible for launching the launchMenuPanel() private function.
	 */
	public void show(){
		launchMenuPanel();
	}
	/**
	 * Function responsible for initializing the contents of this frame with a menu bar.
	 */
	private static void launchMenuPanel() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				myExit();
			}
		});
		frame.setBounds(100, 100, 570, 350);
		frame.setTitle("Dragon Maze");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new MenuPanel(),BorderLayout.CENTER);	
		frame.setVisible(true);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);


		//menu Game

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
		mnGame.add(mntmNew);

		JMenuItem mntmSave= new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
		mnGame.add(mntmSave);

		JMenuItem mntmLoad= new JMenuItem("Load");
		mntmLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0));
		mnGame.add(mntmLoad);

		//menu Options

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmOpt= new JMenuItem("Options Menu");

		mntmOpt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0));
		mnOptions.add(mntmOpt);

		//menu Exit

		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);

		JMenuItem mntmExit = new JMenuItem("Exit Game");

		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0));
		mnExit.add(mntmExit);

		//newgame

		mntmNew.addMenuKeyListener(new MenuKeyListener() {

			@Override
			public void menuKeyPressed(MenuKeyEvent kc) {
				if(kc.getKeyCode() == MenuKeyEvent.VK_N)
					newGame();
			}

			@Override
			public void menuKeyReleased(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuKeyTyped(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		mntmNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				newGame();
			}
		});

		//savegame

		mntmSave.addMenuKeyListener(new MenuKeyListener() {

			@Override
			public void menuKeyPressed(MenuKeyEvent kc) {
				if(kc.getKeyCode() == MenuKeyEvent.VK_S)
					save();
			}

			@Override
			public void menuKeyReleased(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuKeyTyped(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		mntmSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				save();
			}
		});


		//Load game

		mntmLoad.addMenuKeyListener(new MenuKeyListener() {

			@Override
			public void menuKeyPressed(MenuKeyEvent kc) {
				if(kc.getKeyCode() == MenuKeyEvent.VK_L)
					loadGame();
			}

			@Override
			public void menuKeyReleased(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuKeyTyped(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		mntmLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				loadGame();
			}
		});

		//exit 

		mntmExit.addMenuKeyListener(new MenuKeyListener() {

			@Override
			public void menuKeyPressed(MenuKeyEvent kc) {
				if(kc.getKeyCode() == MenuKeyEvent.VK_E)
					myExit();
			}

			@Override
			public void menuKeyReleased(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuKeyTyped(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				myExit();
			}
		});

		//options

		mntmOpt.addMenuKeyListener(new MenuKeyListener() {

			@Override
			public void menuKeyPressed(MenuKeyEvent kc) {
				if(kc.getKeyCode() == MenuKeyEvent.VK_O){
					options = new Options();
					options.setLocationRelativeTo(null);
					options.setVisible(true);
				}
			}

			@Override
			public void menuKeyReleased(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuKeyTyped(MenuKeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		mntmOpt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				options = new Options();
				options.setLocationRelativeTo(null);
				options.setVisible(true);
			}
		});

	}

	/**
	 * Gets the maze variable
	 * @return a maze structure with the maze status of the game
	 */
	public static Maze getMaze() {
		return maze;
	}

	/**
	 * Sets the maze variable 
	 * @param maze new maze structure
	 */
	public static void setMaze(Maze maze) {
		GUI.maze = maze;
	}

	/**
	 * Gets the gameFlag variable
	 * @return true if there is a game active or false if there isn't
	 */
	public static boolean getGameFlag() {
		return gameFlag;
	}

	/**
	 * Sets the gameFlag variable
	 * @param true if there is a game active or false if there isn't
	 */
	public static void setGameFlag(boolean gameFlag) {
		GUI.gameFlag = gameFlag;
	}

	/**
	 * Gets the frame of the game
	 * @return the frame of the game
	 */
	public static JFrame getFrame() {
		return frame;
	}

	/**
	 * Sets the frame of this game
	 * @param frame new frame
	 */
	public static void setFrame(JFrame frame) {
		GUI.frame = frame;
	}

	/**
	 * Gets the mazeSize variable
	 * @return the size of the maze
	 */
	public static int getMazeSize() {
		return mazeSize;
	}

	/**
	 * Sets the mazeSize variable
	 * @param mazeSize new maze size
	 */
	public static void setMazeSize(int mazeSize) {
		GUI.mazeSize = mazeSize;
	}

	/**
	 * Gets the numberDragons variable
	 * @return the number of dragons in the game
	 */
	public static int getNumberDragons(){
		return numberDragons;
	}

	/**
	 * Sets the numberDragons variable
	 * @param numberDragons the number of dragons active in the game
	 */
	public static void setNumberDragons(int numberDragons){
		GUI.numberDragons = numberDragons;
	}

	/**
	 * Gets the upKey variable
	 * @return the key number that is assigned to the upKey variable
	 */
	public static int getUpKey() {
		return upKey;
	}

	/**
	 * Sets the upKey variable
	 * @param upKey the key number that is going to be assigned to the upKey variable
	 */
	public static void setUpKey(int upKey) {
		GUI.upKey = upKey;
	}

	/**
	 * Gets the downKey variable
	 * @return the key number that is assigned to the downKey variable
	 */
	public static int getDownKey() {
		return downKey;
	}

	/**
	 * Sets the downKey variable
	 * @param downKey the key number that is going to be assigned to the downKey variable
	 */
	public static void setDownKey(int downKey) {
		GUI.downKey = downKey;
	}

	/**
	 * Gets the rightKey variable
	 * @return the key number that is assigned to the rightKey variable
	 */
	public static int getRightKey() {
		return rightKey;
	}

	/**
	 * Sets the rightKey variable
	 * @param rightKey the key number that is going to be assigned to the rightKey variable
	 */
	public static void setRightKey(int rightKey) {
		GUI.rightKey = rightKey;
	}

	/**
	 * Gets the leftKey variable
	 * @return the key number that is assigned to the leftKey variable
	 */
	public static int getLeftKey() {
		return leftKey;
	}

	/**
	 * Sets the leftKey variable
	 * @param leftKey the key number that is going to be assigned to the leftKey variable
	 */
	public static void setLeftKey(int leftKey) {
		GUI.leftKey = leftKey;
	}

	/**
	 * Sets the eagleKey variable
	 * @param eagleKey the key number that is going to be assigned to the eagleKey variable
	 */
	public static int getEagleKey() {
		return eagleKey;
	}

	/**
	 * Gets the eagleKey variable
	 * @return the key number that is assigned to the eagleKey variable
	 */
	public static void setEagleKey(int eagleKey) {
		GUI.eagleKey = eagleKey;
	}

	/**
	 * Get the existGame variable
	 * @return true if there is a game or false if there isn't
	 */
	public static boolean isExistGame() {
		return existGame;
	}

	/**
	 * Sets the existGame variable
	 * @param existGame true if here is a game on or false if there isn't
	 */
	public static void setExistGame(boolean existGame) {
		GUI.existGame = existGame;
	}

	/**
	 * Gets the bruttonPressedFlag
	 * @return true if a mouse was pressed in the CustomMazeDialog,false if it wasn't
	 */
	public static boolean getButtonPressedFlag() {
		return buttonPressedFlag;
	}

	/**
	 * Sets the bruttonPressedFlag
	 * @return true if a mouse was pressed in the CustomMazeDialog,false if it wasn't
	 */
	public static void setButtonPressedFlag(boolean buttonPressedFlag) {
		GUI.buttonPressedFlag = buttonPressedFlag;
	}

	/**
	 * Gets the charSelected variable
	 * @return the maze char that was selected in the charPanel in the CustomMazeDialog
	 */
	public static char getCharSelected() {
		return charSelected;
	}

	/**
	 * Sets the charSelected variable
	 * @param charSelected
	 */
	public static void setCharSelected(char charSelected) {
		GUI.charSelected = charSelected;
	}

	/**
	 * Function responsible for launching a confirm dialog to confirm a new game.
	 */
	private static void newGame() {
		GUI.getFrame().setResizable(false);
		int resultado = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?", "New Game!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if(resultado == JOptionPane.YES_OPTION){
			newGameMenu();
		}
	}

	/**
	 * Function responsible for creating a new game.
	 */
	@SuppressWarnings("deprecation")
	private static void newGameMenu(){
		GUI.getFrame().resize(570, 350);
		GUI.getFrame().setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new InitialGamePanel(),BorderLayout.CENTER);		
		frame.setVisible(true);
	}

	/**
	 * Function responsible for launching a confirm dialog conforming the exit of the game.
	 */
	public static void myExit(){

		int resultado = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

		if(resultado == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	/**
	 * Function responsible for launching a dialog to receive the name of the file the user wants to save and for saving the actual state of the game.
	 */
	public static void save(){
		if(existGame){
			String fileName = JOptionPane.showInputDialog(null, "File name you wish to save:");
			if(fileName == null){

			}else
				if(fileName.isEmpty()){
					while(fileName.isEmpty()){
						JOptionPane.showMessageDialog(null,"Please insert a file name","Error",0);
						fileName = JOptionPane.showInputDialog(null, "File name you wish to save:");
					}
				}else{
					File file = new File(fileName);
					GameFiles.saveFile(fileName,maze);
				}
		}
		else{
			int resultado = JOptionPane.showConfirmDialog(null, "There is no active game,do you want to start a new one?", "No game running!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(resultado == JOptionPane.YES_OPTION){
				existGame = true;
				newGameMenu();			
			}
		}
	}

	/**
	 * Function responsible launching a dialog to receive the name of the file the user wants to load.
	 * @return 
	 */
	public static char load(){
		String fileName = JOptionPane.showInputDialog(null, "File name you wish to load:");
		if(fileName == null){

		}else
			if(fileName.isEmpty()){
				while(fileName.isEmpty()){
					JOptionPane.showMessageDialog(null,"Please insert a file name","Error",0);
					fileName = JOptionPane.showInputDialog(null, "File name you wish to load:");
				}
			}else{
				try {
					if(GameFiles.loadFile(fileName) == null)
						return 'n';
					else{
						maze = GameFiles.loadFile(fileName);
						mazeSize = maze.getNumberColumns();
					}
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null,"Please insert a valid name","Error",0);
				}
			}
		return 'y';
	}

	/**
	 * Function responsible for loading a game and initializing the game with those saved parameteres.
	 */
	@SuppressWarnings("deprecation")
	public static void loadGame(){
		if(GUI.load() == 'y'){
			GUI.getFrame().getContentPane().removeAll();
			GUI.getFrame().resize(570, 350);
			GUI.getFrame().setResizable(false);
			frame.setLocationRelativeTo(null);
			MazePanel newGame = new MazePanel();
			GUI.setExistGame(true);
			GUI.getFrame().getContentPane().add(newGame, BorderLayout.CENTER);
			newGame.setFocusable(true);	
			GUI.setGameFlag(true);
		}
	}
}
