package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MemoListPanel extends JPanel {

	private String[] title = { "bin (0)", "done (1)sdfafasfdsdfasfdasdfasdfasdfad", "today (2)",
			"tomorrow (3)", "tasklist (4)" };
	private String[] date = { "120130318", "221100408", "320100308",
			"420100328", "520100308" };

	private int alphaValue = 240;
	private Color[] layerColors = { new Color(251, 172, 27, alphaValue),
			new Color(52, 167, 224, alphaValue),
			new Color(246, 40, 52, alphaValue),
			new Color(248, 113, 0, alphaValue),
			new Color(141, 196, 0, alphaValue) };

	MemoListPanel() {
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.anchor = GridBagConstraints.FIRST_LINE_START;
		c1.weightx = 0.5;
		c1.fill = GridBagConstraints.BOTH;
		c1.ipady = 10;  
		c1.gridx = 0;
		c1.gridy = 0;
		c1.insets = new Insets(2,10,1,1);
		c1.weightx = 0.5;
		for(int i=0; i<title.length; i++) {
			c1.gridy = i;
			this.add(createColoredLabel(title[i], layerColors[i]), c1);
		}
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 1;
		c2.weightx = 0.2;
		c2.insets = new Insets(2,10,1,1);
		c2.fill = GridBagConstraints.BOTH;
		for(int i=0; i<date.length; i++) {
			c2.gridy = i;
			this.add(createColoredLabel(date[i], layerColors[i]), c2);
		}
		
	}
	
	public void setDisplayInfo() {
		
	}

	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		// label.setVerticalAlignment(JLabel.TOP);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		return label;
	}

	private static void test() {
		MemoListPanel a = new MemoListPanel();
		JFrame frame = new JFrame("LayeredPaneDemo");
		frame.setPreferredSize(new Dimension(300, 310));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(a);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		test();
	}
}
