package maze.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import maze.logic.Maze;

public class initialMenuPanel extends JPanel {

	//private GUIlauncher launcher;
	private JTextField mazeSize;
	private JLabel lblNumberOfDragons;
	private JTextField numberDragons;
	
	//Game Global Variables
	int mazeOption=1; //Option to decide which maze type to present
	int dragonMode; //Option to decide in which mode the dragon is going to operate
	int numberOfDragons; //number of dragons in the game
	
	public initialMenuPanel() {
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
		chckbxStandartMaze.setBounds(10, 87, 121, 23);
		add(chckbxStandartMaze);
		
		//creates a text field for the maze size
		mazeSize = new JTextField();
		mazeSize.setBounds(137, 114, 86, 20);
		add(mazeSize);
		mazeSize.setColumns(10);
		mazeSize.setEditable(false);
		
		//creates a label for the maze size
		JLabel lblSize = new JLabel("Maze Size:");
		lblSize.setBounds(10, 117, 68, 14);
		add(lblSize);
		
		lblNumberOfDragons = new JLabel("Number Of Dragons:");
		lblNumberOfDragons.setBounds(10, 146, 121, 14);
		add(lblNumberOfDragons);
		
		//creates a text field for the number of dragons
		numberDragons = new JTextField();
		numberDragons.setBounds(137, 143, 86, 20);
		add(numberDragons);
		numberDragons.setColumns(10);
		numberDragons.setEditable(false);
		
		//creates a combo Box for the dragon movements options
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(233, 142, 191, 20);
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
				if(mazeOption==1){
					GUIlauncher.setMaze(new Maze());
					GUIlauncher.getMaze().initializeMaze();
					numberOfDragons = 1;
				}
				else
					if(mazeOption==2){
						if(mazeSize.getText()== null || numberDragons.getText() == null){
							JOptionPane.showMessageDialog(null, "Fill all the fields", "Error", 0);
						}else
							if(Integer.parseInt(numberDragons.getText()) > 5){
								JOptionPane.showMessageDialog(null, "The number of dragons must be less than 5", "Error", 0);
							}else
								if(Integer.parseInt(mazeSize.getText())%2==0){
									JOptionPane.showMessageDialog(null, "The size of the maze must be odd", "Error", 0);
								}else
								{
									GUIlauncher.setMazeSize(Integer.parseInt(mazeSize.getText()));
									numberOfDragons = Integer.parseInt(numberDragons.getText());
									GUIlauncher.setMaze(new Maze(GUIlauncher.getMazeSize()));
									GUIlauncher.getMaze().initializeMaze(GUIlauncher.getMazeSize());
								}
					}

				GUIlauncher.getMaze().setDragonMode(comboBox.getSelectedIndex());
				GUIlauncher.setGameFlag(true);
				GUIlauncher.launchGamePanel();
			}
		});
	
		btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPlay.setBounds(175, 228, 89, 23);
		add(btnPlay);
		
		//creates a label for the title of the game
		JLabel lblDragonMaze = DefaultComponentFactory.getInstance().createTitle("Dragon Maze");
		lblDragonMaze.setBounds(175, 21, 103, 37);
		add(lblDragonMaze);
		
	}

}
