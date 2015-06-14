package maze.gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class responsible for the display and for the procedures of the initial menu that appearers in the beginning of the application.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class MenuPanel extends JPanel {

	private static final ImageIcon background  = new ImageIcon("Imagens/menuImage.png");
	private static final ImageIcon logo = new ImageIcon("Imagens/logo.png");
	Options options;
	
	/**
	 * Constructor of this panel.
	 */
	public MenuPanel() {
		setLayout(null);
		this.setFocusable(true);
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				GUI.getFrame().getContentPane().removeAll();
				InitialGamePanel panel = new InitialGamePanel();
				panel.setBounds(0, 0, 570, 300);
				GUI.setExistGame(true);
				GUI.getFrame().getContentPane().add(panel, BorderLayout.CENTER);
				GUI.getFrame().setResizable(false);
				panel.setFocusable(true);
			}
		});
		btnNewGame.setBounds(230, 118, 106, 23);
		add(btnNewGame);
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUI.loadGame();
			}
		});
		btnLoadGame.setBounds(230, 152, 106, 23);
		add(btnLoadGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GUI.myExit();
			}
		});
		btnExit.setBounds(230, 220, 106, 23);
		add(btnExit);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				options = new Options();
				options.setLocationRelativeTo(null);
				options.setVisible(true);
			}
		});
		btnOptions.setBounds(230, 186, 106, 23);
		add(btnOptions);

	}
	
	/**
	 * Function responsible for overriding the repaint function of this panel.
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(background.getImage(),0,0, null);
		g.drawImage(logo.getImage(),170,30,null);
		super.paintChildren(g);
		
	}
}
