package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.Dragon;

/**
 * Class responsible for the display and for the procedures of the dialog of the custom maze option.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class CustomMazeDialog extends JDialog {

	//general panel for the dialog
	private final JPanel contentPanel = new JPanel();
	CustomMazeDialogCharPanel charpanel = null;

	/**
	 * Launches this dialog
	 */
	public static void main(String[] args) {
		try {
			CustomMazeDialog dialog = new CustomMazeDialog();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor of this dialog.
	 */
	public CustomMazeDialog() {

		setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setSize(screenSize.width, screenSize.height - 50);
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		{ //Maze Panel in the dialog
			CustomMazeDialogMazePanel mazepanel = new CustomMazeDialogMazePanel();
			contentPanel.add(mazepanel, BorderLayout.CENTER);
		}
		{ //Icons Panel in the dialog
			charpanel = new CustomMazeDialogCharPanel();
			contentPanel.add(charpanel, BorderLayout.NORTH);


		}
		{
			//Play and Cancel buttons
			JPanel btns = new JPanel();
			btns.setLayout(new FlowLayout());

			JButton btnPlay = new JButton("Play");
			btnPlay.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(GUI.getMaze().getNumberOfDragons() == 0){
						JOptionPane.showMessageDialog(null, "Please put at least 1 dragon", "Error", 0);
					}
					else
						if(GUI.getMaze().getNumberOfDragons() > 5){
							JOptionPane.showMessageDialog(null, "Maximum dragons exceeded", "Error", 0);
						}
						else
							if(GUI.getMaze().getnSword() == 0){
								JOptionPane.showMessageDialog(null, "Please put a sword", "Error", 0);
							}
							else
								if(GUI.getMaze().getnHero() == 0){
									JOptionPane.showMessageDialog(null, "Please put a hero", "Error", 0);
								}
								else
									if(GUI.getMaze().getnExit() == 0){
										JOptionPane.showMessageDialog(null, "Please put an exit", "Error", 0);

									}

									else{
										//initializes the custom game
										dispose();			
										int i;
										int x, y;
										Dragon[] dragons = new Dragon[GUI.getMaze().getNumberOfDragons()];
										for(i=0;i<GUI.getMaze().getNumberOfDragons();i++){
											dragons[i] = new Dragon();
										}

										for(i=0; i< GUI.getMaze().getNumberOfDragons(); i++){
											for(y=1; y< GUI.getMazeSize()-1; y++){
												for(x=1; x<GUI.getMazeSize()-1; x++){

													if(GUI.getMaze().getMaze()[y][x] == 'D'){
														dragons[i].setX(x);
														dragons[i].setY(y);
														i++;
													}
												}
										}
										}
										
										for(i=0;i<GUI.getMaze().getNumberOfDragons();i++){
											GUI.getMaze().getDragons()[i] = dragons[i];
										}
										GUI.getMaze().setDragonMode(charpanel.getComboBoxSelectedItem()+1);
										GUI.setGameFlag(true);
										MazePanel game = new MazePanel();
										setVisible(false);
										GUI.getFrame().getContentPane().removeAll();
										GUI.getFrame().getContentPane().add(game);
										game.setVisible(true);
										game.setFocusable(true);
										game.requestFocusInWindow();
									}

				}
			});
			btns.add(btnPlay);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					close();
				}
			});
			btns.add(btnCancel);

			contentPanel.add(btns, BorderLayout.SOUTH);

		}
		{
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					close();
				}
			});
		}
	}
	
	/**
	 * Function responsible for handling the exit event of this dialog
	 */
	public void close(){
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit Custom Maze Creation?", "Exit!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(option == JOptionPane.YES_OPTION){
			GUI.getMaze().setNumberOfDragons(1);
			dispose();}
	}


}
