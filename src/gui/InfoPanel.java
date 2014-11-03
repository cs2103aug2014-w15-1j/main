package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicArrowButton;

public class InfoPanel extends JPanel{

	private static final long serialVersionUID = -6143438348516086903L;
	JTextField titleF;
	JTextPane contentF;
	
	
	
	public InfoPanel(Color background, String title, String content){
		super();
		this.setBackground(background);
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		
		initializeTitleF(title);
		initializeContentF(content);
		this.add(titleF, BorderLayout.NORTH);
		this.add(contentF, BorderLayout.CENTER);
		
	}
	private void initializeTitleF(String title) {
		titleF = new JTextField();
		titleF.setOpaque(false);
		titleF.setBackground(new Color(255,255,255, 190));
		titleF.setText(title);
	}
	private void  initializeContentF(String content) {
		contentF = new JTextPane();
		contentF.setOpaque(false);
		//contentF.setBackground(new Color(255,255,255, 190));
		contentF.setText(content);
	}
	
	
	
	
	
	
}
