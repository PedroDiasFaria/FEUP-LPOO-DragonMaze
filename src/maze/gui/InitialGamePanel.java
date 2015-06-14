package maze.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import maze.logic.Maze;
/**
 * Class responsible for the display and for the procedures of the panel that appears to configure a new game.
 * @author Rui Figueira - e11021
 * @author Pedro Faria - ei11167
 *
 */
public class InitialGamePanel extends JPanel {

	//private GUI launcher;
	private JTextField mazeSize;
	private JLabel lblNumberOfDragons;
	private JTextField numberDragons;

	//Game Global Variables
	int mazeOption=1; //Option to decide which maze type to present
	int dragonMode; //Option to decide in which mode the dragon is going to operate
	//int numberOfDragons=1; //number of dragons in the game

	/**
	 * Constructor of this panel
	 */
	public InitialGamePanel() {
		//sets the grid layout for free movement of the content elements
		setLayout(null);
		//creates a set box for the standart Maze option
		final JCheckBox chckbxStandartMaze = new JCheckBox("Standart Maze");
		//sets the initial state of the box as set
		chckbxStandartMaze.setSelected(true);
		//adds a listener for the mouse click
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
		chckbxStandartMaze.setBounds(34, 87, 125, 23);
		add(chckbxStandartMaze);

		//creates a text field for the maze size
		mazeSize = new JTextField();
		mazeSize.setBounds(156, 114, 160, 20);
		add(mazeSize);
		mazeSize.setColumns(10);
		mazeSize.setEditable(false);

		//creates a label for the maze size
		JLabel lblSize = new JLabel("Maze Size:");
		lblSize.setBounds(38, 117, 68, 14);
		add(lblSize);

		lblNumberOfDragons = new JLabel("Number Of Dragons:");
		lblNumberOfDragons.setBounds(38, 148, 121, 14);
		add(lblNumberOfDragons);

		//creates a text field for the number of dragons
		numberDragons = new JTextField();
		numberDragons.setBounds(156, 145, 160, 20);
		add(numberDragons);
		numberDragons.setColumns(10);
		numberDragons.setEditable(false);

		//creates a combo Box for the dragon movements options
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(326, 143, 191, 20);
		add(comboBox);
		comboBox.addItem("Dragons Without Movement");
		comboBox.addItem("Dragons with Random Movement");
		comboBox.addItem("Sleeping Dragons with Random Movement");

		//creates a play button to initiate the game
		JButton btnPlay = new JButton("Play");
		//creates a listener for the mouse press event 
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				boolean validGame = false;
				if(mazeOption==1){
					GUI.setMaze(new Maze());
					GUI.getMaze().initializeMaze();
					GUI.getMaze().setNumberOfDragons(1);
					GUI.setMazeSize(10);	
					validGame = true;
				}
				else
					if(mazeOption==2){
						if(mazeSize.getText().isEmpty() || numberDragons.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Fill all the fields", "Error", 0);

						}else
							if(Integer.parseInt(numberDragons.getText()) > 5){
								JOptionPane.showMessageDialog(null, "The number of dragons must be less than 5", "Error", 0);
							}else
								if(Integer.parseInt(mazeSize.getText())%2==0){
									JOptionPane.showMessageDialog(null, "The size of the maze must be odd", "Error", 0);
								}else
									if(Integer.parseInt(mazeSize.getText())<11 || Integer.parseInt(mazeSize.getText())>31){
										JOptionPane.showMessageDialog(null,"The size of the maze must be between 11-31","Error",0);
									}else
									{
										GUI.setMazeSize(Integer.parseInt(mazeSize.getText()));
										GUI.setMaze(new Maze(GUI.getMazeSize()));
										GUI.getMaze().setNumberOfDragons(Integer.parseInt(numberDragons.getText()));
										GUI.getMaze().initializeMaze(GUI.getMazeSize());
										validGame = true;
									}
					}
				if(validGame){
					GUI.getMaze().setDragonMode(comboBox.getSelectedIndex()+1);
					GUI.setGameFlag(true);
					MazePanel game = new MazePanel();
					setVisible(false);
					GUI.getFrame().getContentPane().add(game);
					game.setVisible(true);
					game.setFocusable(true);
					game.requestFocusInWindow();
				}
			}
		});

		btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPlay.setBounds(428, 236, 89, 23);
		add(btnPlay);

		//creates a label for the title of the game
		JLabel lblDragonMaze = DefaultComponentFactory.getInstance().createTitle("Dragon Maze");
		lblDragonMaze.setBounds(226, 31, 103, 37);
		add(lblDragonMaze);

		JButton btnCustom = new JButton("Custom");
		btnCustom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CustomMazeSizeDialog dialog = new CustomMazeSizeDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});
		btnCustom.setBounds(240, 236, 89, 23);
		add(btnCustom);

		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				GUI.getFrame().getContentPane().removeAll();
				MenuPanel menupanel = new MenuPanel();
				GUI.getFrame().getContentPane().add(menupanel ,BorderLayout.CENTER);
				GUI.getFrame().setVisible(true);

			}
		});
		btnReturn.setBounds(42, 236, 89, 23);
		add(btnReturn);

	}

}
