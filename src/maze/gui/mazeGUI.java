package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import maze.logic.*;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class mazeGUI implements KeyListener {

	private JFrame frame ;
	private JTextField mazeSize;
	private JLabel lblNumberOfDragons;
	private JTextField numberDragons;
	JPanel gamePanel ;
	
	//Imagens
	//ImageIcon wall = new ImageIcon(getClass().getResource("Imagens/wall.png"));
	//ImageIcon dragon = new ImageIcon(getClass().getResource("Imagens/dragon.png"));
	//ImageIcon hero = new ImageIcon(getClass().getResource("Imagens/hero.png"));
	//ImageIcon sword = new ImageIcon(getClass().getResource("Imagens/sword.png"));
	//ImageIcon eagle = new ImageIcon(getClass().getResource("Imagens/eagle.png"));
	ImageIcon exit = new ImageIcon("Imagens/exit.png");

	//Game Global Variables
	Maze maze = null;
	int mazeOption=1; //Option to decide which maze type to present
	int dragonMode; //Option to decide in which mode the dragon is going to operate
	int numberOfDragons; //number of dragons in the game
	int n;          //size of the maze
	boolean gameFlag = false;


	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	void initialMenu() {

		frame = new JFrame("Dragon Maze");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		final JCheckBox chckbxStandartMaze = new JCheckBox("Standart Maze");
		chckbxStandartMaze.setSelected(true);
		chckbxStandartMaze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxStandartMaze.isSelected()){
					mazeOption = 1;
				}
				else{
					mazeOption = 2;
				}

				if(mazeOption == 1){
					mazeSize.setEditable(false);
					numberDragons.setEditable(false);
				}else{
					mazeSize.setEditable(true);
					numberDragons.setEditable(true);
				}

				numberDragons.validate();
				mazeSize.validate();
				numberDragons.repaint();
				mazeSize.repaint();
			}
		});

		chckbxStandartMaze.setAlignmentY(Component.TOP_ALIGNMENT);
		chckbxStandartMaze.setBounds(10, 87, 121, 23);
		frame.getContentPane().add(chckbxStandartMaze);

		mazeSize = new JTextField();
		mazeSize.setBounds(137, 114, 86, 20);
		frame.getContentPane().add(mazeSize);
		mazeSize.setColumns(10);
		mazeSize.setEditable(false);

		JLabel lblSize = new JLabel("Maze Size:");
		lblSize.setBounds(10, 117, 68, 14);
		frame.getContentPane().add(lblSize);

		lblNumberOfDragons = new JLabel("Number Of Dragons:");
		lblNumberOfDragons.setBounds(10, 146, 121, 14);
		frame.getContentPane().add(lblNumberOfDragons);

		numberDragons = new JTextField();
		numberDragons.setBounds(137, 143, 86, 20);
		frame.getContentPane().add(numberDragons);
		numberDragons.setColumns(10);
		numberDragons.setEditable(false);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(233, 142, 191, 20);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Dragons Without Movement");
		comboBox.addItem("Dragons with Random Movement");
		comboBox.addItem("Sleeping Dragons with Random Movement");

		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(mazeOption==1){
					maze = new Maze();
					maze.initializeMaze();
					numberOfDragons = 1;
				}
				else
					if(mazeOption==2){
						if(mazeSize.getText()== null || numberDragons.getText() == null){
							JOptionPane.showMessageDialog(frame, "Fill all the fields", "Error", 0);
						}else
							if(Integer.parseInt(numberDragons.getText()) > 5){
								JOptionPane.showMessageDialog(frame, "The number of dragons must be less than 5", "Error", 0);
							}else
								if(Integer.parseInt(mazeSize.getText())%2==0){
									JOptionPane.showMessageDialog(frame, "The size of the maze must be odd", "Error", 0);
								}else
								{
									n = Integer.parseInt(mazeSize.getText());
									numberOfDragons = Integer.parseInt(numberDragons.getText());
									maze = new Maze(n);
									maze.initializeMaze(n);
								}
					}

				maze.setDragonMode(comboBox.getSelectedIndex());
				gameFlag = true;
				frame.getContentPane().setLayout(new GridLayout(1,1));
				frame.setResizable(true);
				//drawGamePanel();
			}
		});

		btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPlay.setBounds(175, 228, 89, 23);
		frame.getContentPane().add(btnPlay);

		JLabel lblDragonMaze = DefaultComponentFactory.getInstance().createTitle("Dragon Maze");
		lblDragonMaze.setBounds(175, 21, 103, 37);
		frame.getContentPane().add(lblDragonMaze);

		frame.setVisible(true);
	}

	public void drawGamePanel(Graphics g){
		if(gameFlag){
			frame.getContentPane().removeAll();
			frame.validate();	
			gamePanel = new JPanel(new GridLayout(maze.getNumberColumns(),maze.getNumberRows()));
			frame.addKeyListener(this);	
			gamePanel.setBorder(null);
			gamePanel.setBackground(Color.WHITE);
			int width = frame.getWidth()/n;
			int height = frame.getHeight()/n;
			for(int i=0;i<maze.getNumberRows();i++)
				for(int j=0;j<maze.getNumberColumns();j++){
					g.drawImage(exit.getImage(), i*width, j*height, null);	
				}

			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.validate();
			refreshGamePanel();
		}
	}

	public void refreshGamePanel(){
		//this.repaint();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(gameFlag){
			if(arg0.getKeyCode() == KeyEvent.VK_W || arg0.getKeyCode() == KeyEvent.VK_UP){
				maze.dragonTurn();
				maze.heroTurn('w');
			}else
				if(arg0.getKeyCode() == KeyEvent.VK_A || arg0.getKeyCode() == KeyEvent.VK_LEFT){
					maze.dragonTurn();
					maze.heroTurn('a');
				}else
					if(arg0.getKeyCode() == KeyEvent.VK_S || arg0.getKeyCode() == KeyEvent.VK_DOWN){
						maze.dragonTurn();
						maze.heroTurn('s');
					}else
						if(arg0.getKeyCode() == KeyEvent.VK_D || arg0.getKeyCode() == KeyEvent.VK_RIGHT){
							maze.dragonTurn();
							maze.heroTurn('d');
						}else
							if(arg0.getKeyCode() == KeyEvent.VK_1){
								maze.heroTurn('1');
							}
			refreshGamePanel();
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
