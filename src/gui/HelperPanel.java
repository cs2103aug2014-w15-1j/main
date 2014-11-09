package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class HelperPanel extends JPanel implements CustomizedJPanel {
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	private JTextPane contentPane;
	private Color background = new Color(255, 255, 255, 230); 
	private static final Font FONT_TEXT = new Font("DIALOG", Font.PLAIN, 15);
	
	public HelperPanel(String text){
		this.text = text;
		this.setOpaque(false);
		this.setBackground(background);
		this.setLayout(new BorderLayout());
		
	}
	
	public void construct() {
		initializeContentPane(text);
		this.add(contentPane);
	}
	private void initializeContentPane(String content) {
		contentPane = new JTextPane();
		contentPane.setEditable(false);
		contentPane.setOpaque(false);
		contentPane.setBackground(background);
		contentPane.setContentType("text/html");
		contentPane.setText(content);
		contentPane.setHighlighter(null);
		contentPane.setAutoscrolls(true);
		
		
		
		
	}

}
