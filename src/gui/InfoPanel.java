package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class InfoPanel extends JPanel{

	private static final long serialVersionUID = -6143438348516086903L;
	JLabel titleF;
	JTextPane contentF;
	private static Color lightCyan220 = new Color(55, 177, 241, 220);
	
	
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
		titleF = new JLabel();
		titleF.setOpaque(false);
		//titleF.setHorizontalAlignment(JTextField.CENTER);
		titleF.setBackground(new Color(255,255,255, 150));
		titleF.setForeground(lightCyan220);
		titleF.setFont(new Font("calibri", Font.BOLD | Font.ITALIC, 15));
		titleF.setText(title+":");
	}
	private void  initializeContentF(String content) {
		
		contentF = new JTextPane();
		contentF.setFont(new Font("calibri",  Font.BOLD, 25));
		contentF.setEditable(false);
		contentF.setOpaque(true);
		contentF.setBackground(new Color(255,255,255));
		contentF.setForeground(lightCyan220);
		contentF.setText(content);
		contentF.selectAll();
		contentF.setHighlighter(null);
		contentF.setMaximumSize(new Dimension(this.getWidth(), 100));
		contentF.setPreferredSize(new Dimension(200, 200));
		contentF.setBounds(0,0,200,200);
		// align text to center
		StyledDocument doc = contentF.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}
	
	
	
	
	
	
}
