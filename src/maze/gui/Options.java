package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *  Class responsible for the display and for the procedures of the dialog that presents the options menu for the key configuration.
 * @author Rui Figueira - ei11021
 * @author Pedro Faria - ei11167
 */
public class Options extends JDialog {

	//panel used into the the dialog
	private final JPanel contentPanel = new JPanel();
	//array of the key codes
	int[] keys = new int[5];
	{
		keys[0] = GUI.getUpKey();
		keys[1] = GUI.getDownKey();
		keys[2] = GUI.getRightKey();
		keys[3] = GUI.getLeftKey();
		keys[4] = GUI.getEagleKey();

	}
	//buttonGroup for the various buttons
	private ButtonGroup btnGroup = new ButtonGroup();
	
	/**
	 * Launch this dialog.
	 */
	public static void main(String[] args) {
		try {
			Options options = new Options();
			options.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			options.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor of this dialog.
	 */
	public Options() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Options");
		setResizable(false);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			final JToggleButton btnUp = new JToggleButton(KeyEvent.getKeyText(keys[0]));
			btnUp.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					keys[0] = e.getKeyCode();
					btnUp.setText(KeyEvent.getKeyText(keys[0]));
					btnUp.validate();}
			});
			btnUp.setBounds(21, 76, 70, 91);
			contentPanel.add(btnUp);
			btnGroup.add(btnUp);
		}
		{
			final JToggleButton btnDown = new JToggleButton(KeyEvent.getKeyText(keys[1]));
			btnDown.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					keys[1] = e.getKeyCode();
					btnDown.setText(KeyEvent.getKeyText(keys[1]));
					btnDown.validate();
				}
			});
			btnDown.setBounds(101, 76, 70, 91);
			contentPanel.add(btnDown);
			btnGroup.add(btnDown);
		}
		{
			final JToggleButton btnRight = new JToggleButton(KeyEvent.getKeyText(keys[2]));
			btnRight.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					keys[2] = e.getKeyCode();
					btnRight.setText(KeyEvent.getKeyText(keys[2]));
					btnRight.validate();
				}
			});
			btnRight.setBounds(181, 76, 70, 91);
			contentPanel.add(btnRight);
			btnGroup.add(btnRight);
		}
		{
			final JToggleButton btnLeft = new JToggleButton(KeyEvent.getKeyText(keys[3]));
			btnLeft.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					keys[3] = e.getKeyCode();
					btnLeft.setText(KeyEvent.getKeyText(keys[3]));
					btnLeft.validate();
				}
			});
			btnLeft.setBounds(261, 76, 70, 91);
			contentPanel.add(btnLeft);
			btnGroup.add(btnLeft);
		}
		{
			final JToggleButton btnEagle = new JToggleButton(KeyEvent.getKeyText(keys[4]));
			btnEagle.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					keys[4] = e.getKeyCode();
					btnEagle.setText(KeyEvent.getKeyText(keys[4]));
					btnEagle.validate();
				}
			});
			btnEagle.setBounds(341, 76, 70, 91);
			contentPanel.add(btnEagle);
			btnGroup.add(btnEagle);
		}

		btnGroup.clearSelection();
		
		this.validate();
		JLabel lblUp = new JLabel("Up",JLabel.CENTER);
		lblUp.setBounds(31, 178, 46, 14);
		contentPanel.add(lblUp);
		{
			JLabel label = new JLabel("Down", SwingConstants.CENTER);
			label.setBounds(111, 178, 46, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Right", SwingConstants.CENTER);
			label.setBounds(191, 178, 46, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Left", SwingConstants.CENTER);
			label.setBounds(271, 178, 46, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Eagle", SwingConstants.CENTER);
			label.setBounds(352, 178, 46, 14);
			contentPanel.add(label);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Apply");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean validConfiguration=true;
						for(int i=0;i<5;i++)
							for(int j=i+1;j<5;j++){
								if(i!=j){
									if(keys[i] == keys[j]){
										validConfiguration=false;
										JOptionPane.showMessageDialog(null, "Invalid Configuration", "Error", 0);
									}
								}
							}

						if(validConfiguration){
							GUI.setUpKey(keys[0]);
							GUI.setDownKey(keys[1]);
							GUI.setRightKey(keys[2]);
							GUI.setLeftKey(keys[3]);
							GUI.setEagleKey(keys[4]);
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel?","Cancel", JOptionPane.YES_NO_OPTION,1);
						if(option == JOptionPane.YES_OPTION){
							dispose();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
