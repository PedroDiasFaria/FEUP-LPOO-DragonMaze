package maze.gui;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import maze.logic.Maze;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class responsible for the display and for the procedures of the custom maze elements on the top of the custom maze dialog.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 *
 */
public class CustomMazeDialogCharPanel extends JPanel {
	
	//Images and icons definition
	private static final ImageIcon exit  		= new ImageIcon("Imagens/exit.png");
	private static final ImageIcon wall1  		= new ImageIcon("Imagens/wall1.png");
	private static final ImageIcon dragon 		= new ImageIcon("Imagens/dragon.png");
	private static final ImageIcon hero   		= new ImageIcon("Imagens/hero.png");
	private static final ImageIcon sword  		= new ImageIcon("Imagens/sword.png");
	private static final ImageIcon floor  		= new ImageIcon("Imagens/floor.png");

	private static final ImageIcon exitIcon = new ImageIcon((exit.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	private static final ImageIcon wallIcon = new ImageIcon((wall1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	private static final ImageIcon dragonIcon = new ImageIcon((dragon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	private static final ImageIcon heroIcon = new ImageIcon((hero.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	private static final ImageIcon swordIcon = new ImageIcon((sword.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	private static final ImageIcon floorIcon = new ImageIcon((floor.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

	//button group for the various buttons
	private ButtonGroup btnGroup = new ButtonGroup();
	//combo box for the dragon mode
	JComboBox<String> comboBox = null;

	/**
	 * Constructor of this panel.
	 */
	public CustomMazeDialogCharPanel() {

		setLayout(new FlowLayout());

		//character buttons (hero,wall,sword,dragon,exit)
		JToggleButton btnHero = new JToggleButton();
		btnHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				GUI.setButtonPressedFlag(true);
				GUI.setCharSelected('H');
			}
		});
		btnHero.setIcon(heroIcon);
		add(btnHero);
		btnGroup.add(btnHero);

		JToggleButton btnSword = new JToggleButton();
		btnSword.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUI.setButtonPressedFlag(true);
				GUI.setCharSelected('E');
			}
		});
		btnSword.setIcon(swordIcon);
		add(btnSword);
		btnGroup.add(btnSword);

		JToggleButton btnDragon = new JToggleButton();
		btnDragon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUI.setButtonPressedFlag(true);
				GUI.setCharSelected('D');
			}
		});
		btnDragon.setIcon(dragonIcon);
		add(btnDragon);
		btnGroup.add(btnDragon);

		JToggleButton btnExit = new JToggleButton();
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUI.setButtonPressedFlag(true);
				GUI.setCharSelected('S');
			}
		});
		btnExit.setIcon(exitIcon);
		add(btnExit);
		btnGroup.add(btnExit);

		JToggleButton btnWall = new JToggleButton();
		btnWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUI.setButtonPressedFlag(true);
				GUI.setCharSelected('X');
			}
		});
		btnWall.setIcon(wallIcon);
		add(btnWall);
		btnGroup.add(btnWall);

		JToggleButton btnFloor = new JToggleButton();
		btnFloor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUI.setButtonPressedFlag(true);
				GUI.setCharSelected(' ');
			}
		});
		btnFloor.setIcon(floorIcon);
		add(btnFloor);
		btnGroup.add(btnFloor);

		comboBox = new JComboBox<String>();
		add(comboBox);
		comboBox.addItem("Dragons Without Movement");
		comboBox.addItem("Dragons with Random Movement");
		comboBox.addItem("Sleeping Dragons with Random Movement");
		
		GUI.getMaze().setDragonMode(comboBox.getSelectedIndex()+1);


		System.out.println(GUI.getCharSelected());
		btnGroup.clearSelection();
		this.setVisible(true);
}
	/**
	 * Function responsible for returning the combo box selected item of this panel
	 * @return the int value of the combo box selected index
	 */
	public int getComboBoxSelectedItem(){
		return comboBox.getSelectedIndex();
	}
}
