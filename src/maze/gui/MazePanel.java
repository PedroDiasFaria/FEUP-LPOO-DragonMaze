package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import maze.logic.Maze;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Class responsible for the display and for the procedures of the panel that displays the maze structure in a graphical interface.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class MazePanel extends JPanel {

	static final ImageIcon exit  				= new ImageIcon("Imagens/exit.png");
	private static final ImageIcon wall1  		= new ImageIcon("Imagens/wall1.png");
	private static final ImageIcon wall2  		= new ImageIcon("Imagens/wall2.png");
	private static final ImageIcon dragon 		= new ImageIcon("Imagens/dragon.png");
	private static final ImageIcon dragonSleep 	= new ImageIcon("Imagens/dragonsleep.png");
	private static final ImageIcon hero   		= new ImageIcon("Imagens/hero.png");
	private static final ImageIcon heroWSword   = new ImageIcon("Imagens/herowsword.png");
	private static final ImageIcon sword  		= new ImageIcon("Imagens/sword.png");
	private static final ImageIcon eagle  		= new ImageIcon("Imagens/eagle.png");
	private static final ImageIcon floor  		= new ImageIcon("Imagens/floor.png");
	private static final ImageIcon eaglewsword	= new ImageIcon("Imagens/eaglewsword.png");

	private int mazeSize = GUI.getMazeSize();
	private int cellHeight;
	private int cellWidth;

	private boolean endGame=false;

	/**
	 * Constructor of this panel.
	 */
	public MazePanel() {

		setLayout(null);
		this.setFocusable(true);
		//this.setBounds(0, 0, 570, 300);
		GUI.getFrame().setResizable(true);
		this.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(GUI.getGameFlag()){
					if(arg0.getKeyCode() == GUI.getUpKey() || arg0.getKeyCode() == KeyEvent.VK_UP){
						GUI.getMaze().dragonTurn();
						GUI.getMaze().heroTurn('w');
					}else
						if(arg0.getKeyCode() == GUI.getLeftKey() || arg0.getKeyCode() == KeyEvent.VK_LEFT){
							GUI.getMaze().dragonTurn();
							GUI.getMaze().heroTurn('a');
						}else
							if(arg0.getKeyCode() == GUI.getDownKey() || arg0.getKeyCode() == KeyEvent.VK_DOWN){
								GUI.getMaze().dragonTurn();
								GUI.getMaze().heroTurn('s');
							}else
								if(arg0.getKeyCode() == GUI.getRightKey() || arg0.getKeyCode() == KeyEvent.VK_RIGHT){
									GUI.getMaze().dragonTurn();
									GUI.getMaze().heroTurn('d');
								}else
									if(arg0.getKeyCode() == GUI.getEagleKey()){
										GUI.getMaze().heroTurn('1');
									}
				}

				validate();
				repaint();

				if(GUI.getMaze().getEndOfGame() && GUI.getMaze().getPlayerWon()){
					repaint();
					JOptionPane.showMessageDialog(null, "You win", "Information", 1);
					GUI.setGameFlag(false);
					endGame = true;
				}else
					if(GUI.getMaze().getEndOfGame() && GUI.getMaze().getPlayerLost()){
						repaint();
						JOptionPane.showMessageDialog(null, "You lose","Information",1);
						GUI.setGameFlag(false);
						endGame=true;
					}

						if(endGame){
							int option = JOptionPane.showConfirmDialog(null, "Want to play again?", "New Game", JOptionPane.YES_NO_OPTION,1);
							if(option == JOptionPane.YES_OPTION){
								GUI.getFrame().getContentPane().removeAll();
								GUI.getFrame().resize(570, 350);
								GUI.getFrame().setResizable(false);
								GUI.getFrame().setLocationRelativeTo(null);
								GUI.getFrame().getContentPane().add(new InitialGamePanel(), BorderLayout.CENTER);
								GUI.getFrame().setVisible(true);
							}else
							{
								System.exit(0);
							}
						}
					}
			});

		}

		/**
		 * Function responsible for overriding the repaint function of this panel.
		 */
		public void paint(Graphics g) 
		{
			cellHeight = getHeight() / mazeSize;
			cellWidth  = getWidth()  / mazeSize;

			super.paint(g);
			for(int i=0; i<mazeSize;i++) 							
				for(int j=0;j<mazeSize;j++)							
					g.drawImage(floor.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);


			for(int i=0; i<mazeSize;i++) 							
				for(int j=0;j<mazeSize;j++){					
					if(i==0 || i==mazeSize-1 || j==0 || j==mazeSize-1)
						if(GUI.getMaze().getMaze()[i][j] == 'S')
							g.drawImage(exit.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						else
							g.drawImage(wall2.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);



				}


			for(int i=1;i<mazeSize-1;i++)
				for(int j=1;j<mazeSize-1;j++){
					switch(GUI.getMaze().getMaze()[i][j]){
					case 'X':
						g.drawImage(wall1.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					case 'H':
						g.drawImage(hero.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);

						if(GUI.getMaze().getHero().getHasEagle() == true)
							g.drawImage(eagle.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);

						break;
					case 'A':
						g.drawImage(heroWSword.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					case 'D':
						g.drawImage(dragon.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					case 'd':
						g.drawImage(dragonSleep.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					case 'E':
						g.drawImage(sword.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					case 'G':	
						if(GUI.getMaze().getEagle().getOldChar() == 'X')
							g.drawImage(wall1.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						if(GUI.getMaze().getEagle().getOldChar() == 'D' || GUI.getMaze().getEagle().getOldChar() == 'd' )
							g.drawImage(dragon.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						g.drawImage(eaglewsword.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					case 'g':
						if(GUI.getMaze().getEagle().getOldChar() == 'X')
							g.drawImage(wall1.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						if(GUI.getMaze().getEagle().getOldChar() == 'D' || GUI.getMaze().getEagle().getOldChar() == 'd')
							g.drawImage(dragon.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						g.drawImage(eagle.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					case 'F':
						g.drawImage(sword.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						g.drawImage(dragon.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
						break;
					}
				}

		}

	}
