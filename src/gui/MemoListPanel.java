package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MemoListPanel extends JPanel {

	private String[] layerStrings = { "bin (0)", "done (1)", "today (2)",
			"tomorrow (3)", "tasklist (4)" };
	private int alphaValue = 240;
	private Color[] layerColors = { new Color(251, 172, 27, alphaValue),
			new Color(52, 167, 224, alphaValue),
			new Color(246, 40, 52, alphaValue),
			new Color(248, 113, 0, alphaValue),
			new Color(141, 196, 0, alphaValue) };

	MemoListPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (int i = 0; i < layerStrings.length; i++) {
			if(i%2 == 0) {
				System.out.println(i);
				JLabel label = createColoredLabel(layerStrings[i], layerColors[i],
						0);
				this.add(label);
			} else {
				JLabel label = createColoredLabel(layerStrings[i], layerColors[i],
						1);
				this.add(label);
			}
			this.add(Box.createVerticalGlue());
		}
	}

	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color, float d) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		//label.setVerticalAlignment(JLabel.TOP);
		label.setAlignmentX(d);
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
