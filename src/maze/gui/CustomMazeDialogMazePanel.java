package maze.gui;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Maze;

/**
 * Class responsible for the display and for the procedures of the maze panel of the custom maze dialog,where the maze elements are inserted.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class CustomMazeDialogMazePanel extends JPanel implements MouseListener {

	//Images definition
	private static final ImageIcon exit  		= new ImageIcon("Imagens/exit.png");
	private static final ImageIcon wall1  		= new ImageIcon("Imagens/wall1.png");
	private static final ImageIcon wall2  		= new ImageIcon("Imagens/wall2.png");
	private static final ImageIcon dragon 		= new ImageIcon("Imagens/dragon.png");
	private static final ImageIcon hero   		= new ImageIcon("Imagens/hero.png");
	private static final ImageIcon sword  		= new ImageIcon("Imagens/sword.png");
	private static final ImageIcon floor  		= new ImageIcon("Imagens/floor.png");

	//counters for the characters 
	int heroNumber = 0;
	int swordNumber = 0;
	int dragonNumber = 0;
	int exitNumber = 0;

	/**
	 * Constructor of this panel.
	 */
	public CustomMazeDialogMazePanel() {
		addMouseListener(this);

		Maze maze = new Maze(GUI.getMazeSize(), 'e');
		GUI.setMaze(maze);

		this.setVisible(true);
	}

	/**
	 * Function responsible for overriding the repaint function of this panel.
	 */
	public void paint(Graphics g) 
	{
		//calculates the size of the cell based on the panel size
		int cellHeight = (getHeight()) / GUI.getMazeSize();
		int cellWidth  = (getWidth()) / GUI.getMazeSize();

		super.paint(g);

		//draw the maze with images
		for(int i=0; i< GUI.getMazeSize();i++) 							
			for(int j=0;j< GUI.getMazeSize();j++)							
				g.drawImage(floor.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);


		for(int i=0; i<GUI.getMazeSize();i++) 							
			for(int j=0;j<GUI.getMazeSize();j++){					
				if(i==0 || i==GUI.getMazeSize()-1 || j==0 || j==GUI.getMazeSize()-1)
					if(GUI.getMaze().getMaze()[i][j] == 'S')
						g.drawImage(exit.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
					else
						g.drawImage(wall2.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
			}
		for(int i=1;i<GUI.getMazeSize()-1;i++)
			for(int j=1;j<GUI.getMazeSize()-1;j++){
				switch(GUI.getMaze().getMaze()[i][j]){
				case 'X':
					g.drawImage(wall1.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
					break;
				case 'H':
					g.drawImage(hero.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
					break;
				case 'D':
					g.drawImage(dragon.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
					break;
				case 'E':
					g.drawImage(sword.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
					break;
				case 'F':
					g.drawImage(sword.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
					g.drawImage(dragon.getImage(), j*cellWidth, i*cellHeight, cellWidth, cellHeight, null);
					break;
				}
			}
	}

	/**
	 * Function responsible for handling the mouse clicked event in this panel.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int cellHeight = (getHeight()) / GUI.getMazeSize();
		int cellWidth  = (getWidth()) / GUI.getMazeSize();

		int i = e.getY() / cellHeight;
		int j = e.getX() / cellWidth;
		GUI.getMaze().setNumberOfDragons(dragonNumber);
		//System.out.println(i + " " + j + GUI.getCharSelected());

		if(GUI.getCharSelected()=='H'){
			if(!(i==0 || i==GUI.getMazeSize()-1 || j==0 || j==GUI.getMazeSize()-1)){
				if(heroNumber == 0){
					removeItem(i, j)	;	
					GUI.getMaze().getHero().setX(j);
					GUI.getMaze().getHero().setY(i);
					GUI.getMaze().getEagle().setX(j);
					GUI.getMaze().getEagle().setY(i);
					GUI.getMaze().getMaze()[i][j]= 'H';					
					heroNumber = 1;
					GUI.getMaze().setnHero(heroNumber);
				}
				else{
					removeItem(i, j);
					GUI.getMaze().getMaze()[GUI.getMaze().getHero().getY()][GUI.getMaze().getHero().getX()]= ' ';
					GUI.getMaze().getHero().setX(j);
					GUI.getMaze().getHero().setY(i);
					GUI.getMaze().getEagle().setX(j);
					GUI.getMaze().getEagle().setY(i);
					GUI.getMaze().getMaze()[i][j]= 'H';
				}

			}
		}else
			if(GUI.getCharSelected()=='X'){
				if(!(i==0 || i==GUI.getMazeSize()-1 || j==0 || j==GUI.getMazeSize()-1)){
					removeItem(i, j);
					GUI.getMaze().getMaze()[i][j]= 'X';
				}
			}else
				if(GUI.getCharSelected()=='S'){
					if((i==0 || i==GUI.getMazeSize()-1 || j==0 || j==GUI.getMazeSize()-1) && 
							!((i == 0 && j == 0) || (i == 0 && j == GUI.getMazeSize()-1) || 
									(i == GUI.getMazeSize()-1 && j == 0) || (i == GUI.getMazeSize()-1 && j == GUI.getMazeSize()-1))){
						if(exitNumber == 0)
						{
							GUI.getMaze().getExit().setX(j);
							GUI.getMaze().getExit().setY(i);
							GUI.getMaze().getMaze()[i][j]= 'S';
							exitNumber = 1;
							GUI.getMaze().setnExit(exitNumber);
						}
						else{
							GUI.getMaze().getMaze()[GUI.getMaze().getExit().getY()][GUI.getMaze().getExit().getX()]= 'X';
							GUI.getMaze().getExit().setX(j);
							GUI.getMaze().getExit().setY(i);
							GUI.getMaze().getMaze()[i][j]= 'S';

						}
					}
				}else
					if(GUI.getCharSelected()==' '){
						if(!(i==0 || i==GUI.getMazeSize()-1 || j==0 || j==GUI.getMazeSize()-1)){
							removeItem(i, j);
							GUI.getMaze().getMaze()[i][j]= ' ';
						}
					}else
						if(GUI.getCharSelected()=='E'){
							if(!(i==0 || i==GUI.getMazeSize()-1 || j==0 || j==GUI.getMazeSize()-1)){
								if(swordNumber == 0){
									removeItem(i, j)	;	
									GUI.getMaze().getSword().setX(j);
									GUI.getMaze().getSword().setY(i);
									GUI.getMaze().getMaze()[i][j]= 'E';
									swordNumber = 1;
									GUI.getMaze().setnSword(swordNumber);
								}
								else{
									removeItem(i, j);
									GUI.getMaze().getMaze()[GUI.getMaze().getSword().getY()][GUI.getMaze().getSword().getX()]= ' ';
									GUI.getMaze().getSword().setX(j);
									GUI.getMaze().getSword().setY(i);
									GUI.getMaze().getMaze()[i][j]= 'E';
								}

							}
						}else
							if(GUI.getCharSelected()=='D'){
								if(!(i==0 || i==GUI.getMazeSize()-1 || j==0 || j==GUI.getMazeSize()-1)){
									removeItem(i, j);
									GUI.getMaze().getMaze()[i][j]= 'D';
									dragonNumber++;
									GUI.getMaze().setNumberOfDragons(dragonNumber);
								}
							}
		this.repaint();
		this.setVisible(true);
	}

	/**
	 * Function responsible for removing a element in the maze panel and reseting the respective value.
	 * @param i x position of the element.
	 * @param j y position of the element.
	 */
	private void removeItem(int i, int j) {
		char Item = GUI.getMaze().getMaze()[i][j];

		switch(Item){
		case 'H':
			heroNumber = 0;
			GUI.getMaze().setnHero(heroNumber);
			break;
		case 'D':
			dragonNumber --;
			GUI.getMaze().setNumberOfDragons(dragonNumber);
			break;
		case 'E':
			swordNumber = 0;
			GUI.getMaze().setnSword(swordNumber);
			break;
		default:
			break;

		}


	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}

