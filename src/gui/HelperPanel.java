package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class HelperPanel extends JPanel implements CustomizedJPanel {
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	private JTextPane contentPane;
	private Color background = new Color(255, 255, 255, 200); 
	
	public HelperPanel(String text){
		this.text = text;
		this.setBackground(background);
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
	}
	
	public void construct() {
		initializeContentPane(text);
		this.add(contentPane);
	}
	private void initializeContentPane(String content) {
		contentPane = new JTextPane();
		contentPane.setEditable(false);
		contentPane.setOpaque(true);
		contentPane.setText(content);
		contentPane.selectAll();
		contentPane.setHighlighter(null);
	}

}
