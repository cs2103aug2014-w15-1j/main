package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * a customized swing container that extends <code>JPanel</code>. It is a simple
 * JPanel to display the information for one attribute in the list.
 * 
 * <p>
 * Layout manager used in <code>InfoPanel</code> is <code>BorderLayout</code>
 * info panel is divided into two parts: title panel and content panel.
 * 
 * @author A0119391A
 */
public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -6143438348516086903L;
	private JLabel titleF;
	private JTextPane contentF;
	private Font titleFont = new Font("calibri", Font.BOLD | Font.ITALIC, 15);
	private Font contentFont = new Font("calibri", Font.BOLD, 25);
	
	private static final Color COLOR_WHITE_210 = new Color(255, 255, 255, 210);
	private static final Color COLOR_CYAN_220 = new Color(55, 177, 241, 220);

	public InfoPanel(Color background, String title, String content) {
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
		titleF.setBackground(Color.WHITE);
		titleF.setForeground(COLOR_CYAN_220);
		
		titleF.setFont(titleFont);
		titleF.setText(title + ":");
	}

	private void initializeContentF(String content) {

		contentF = new JTextPane();
		contentF.setFont(contentFont);
		contentF.setEditable(false);
		contentF.setOpaque(true);
		contentF.setBackground(COLOR_WHITE_210);
		contentF.setForeground(COLOR_CYAN_220);
		contentF.setText(content);
		contentF.selectAll();
		contentF.setHighlighter(null);

		// align text to center
		StyledDocument doc = contentF.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}

}
