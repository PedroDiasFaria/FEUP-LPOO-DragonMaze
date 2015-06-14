package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import maze.logic.Maze;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class responsible for the display and for the procedures of the dialog of the custom maze size that appears on the beginning of the configuration of the custom maze.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class CustomMazeSizeDialog extends JDialog {

	//general panel
	private final JPanel contentPanel = new JPanel();
	private JTextField mazeSize;

	/**
	 * Launch this dialog.
	 */
	public static void main(String[] args) {
		try {
			CustomMazeSizeDialog dialog = new CustomMazeSizeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor of this dialog.
	 */
	public CustomMazeSizeDialog() {
		setBounds(100, 100, 300, 180);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		mazeSize = new JTextField();
		mazeSize.setBounds(99, 60, 86, 20);
		contentPanel.add(mazeSize);
		mazeSize.setColumns(10);

		JLabel lblInsertTheSize = new JLabel("Custom maze size: [10-30]");
		lblInsertTheSize.setBounds(70, 35, 214, 14);
		contentPanel.add(lblInsertTheSize);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean ok = false;
						if(mazeSize.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Please insert a value", "Error", 0);
						}else
							if(!mazeSize.getText().matches("[0-9]+")){
								JOptionPane.showMessageDialog(null, "Please insert a numerical value", "Error", 0);
							}
							else
								if(Integer.parseInt(mazeSize.getText()) < 10 || Integer.parseInt(mazeSize.getText()) > 30){
									JOptionPane.showMessageDialog(null, "The size must be between 10 and 30", "Error", 0);
								}else{
									ok = true;
								}

						if(ok){
							
							GUI.setMazeSize(Integer.parseInt(mazeSize.getText()));
							dispose();
							CustomMazeDialog custom = new CustomMazeDialog();
							custom.setVisible(true);
						}
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
