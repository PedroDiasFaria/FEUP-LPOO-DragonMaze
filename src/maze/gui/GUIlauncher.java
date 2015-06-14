package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import maze.logic.Maze;

public class GUIlauncher {

	private static Maze maze = null;
	private static JFrame frame;
	static boolean gameFlag;
	static int mazeSize = 10;

	/**
	 * Launch the application.
	 */

	public void show(){
		launchMenuPanel();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private static void launchMenuPanel() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Dragon Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(new initialMenuPanel(),BorderLayout.CENTER);	
		frame.setVisible(true);
		
	}
	
	public static void launchGamePanel(){
		frame.getContentPane().add(new MazePanel(),BorderLayout.CENTER);
	}
	
	public static Maze getMaze() {
		return maze;
	}
	public static void setMaze(Maze maze) {
		GUIlauncher.maze = maze;
	}
	public static boolean getGameFlag() {
		return gameFlag;
	}
	public static void setGameFlag(boolean gameFlag) {
		GUIlauncher.gameFlag = gameFlag;
	}
	public static JFrame getFrame() {
		return frame;
	}
	public static void setFrame(JFrame frame) {
		GUIlauncher.frame = frame;
	}
	public static int getMazeSize() {
		return mazeSize;
	}
	public static void setMazeSize(int mazeSize) {
		GUIlauncher.mazeSize = mazeSize;
	}

	
}
