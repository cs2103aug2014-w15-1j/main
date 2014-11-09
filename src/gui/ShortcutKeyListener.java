package gui;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

public class ShortcutKeyListener extends AbstractAction {
	
	HashMap<String, String> map = new HashMap<String, String>();
	private JTextField input;
	private String inputString;
	private final static String EMPTY_STRING = "";
	
	
	private void initializeMap() {
		map.put("F1", "view today");
		map.put("F2", "view tomorrow");
		map.put("F3", "view undone");
		map.put("F4", "view tasklist");
		map.put("F5", "view bin");
		map.put("F6", "help");
	}
	// add input JTextField into list of observation
	public ShortcutKeyListener(String actionKey, JTextField input) {
		this.input = input;
		initializeMap();
		inputString = map.get(actionKey);
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		input.setText(EMPTY_STRING);
		GuiController.processCommand(inputString);
		
	}

}
