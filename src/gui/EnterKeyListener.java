package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 * class enterKeyListener
 * @author JJ
 *
 */
class EnterKeyListener implements ActionListener {
	private JTextField input;
	private final static String EMPTY_STRING = "";
	
	public EnterKeyListener(JTextField input) {
		this.input = input;
	}

	public void actionPerformed(ActionEvent e) {
		
		String inputCommand =input.getText().trim(); 
		input.setText(EMPTY_STRING);
		GuiController.display(inputCommand);

	}
}