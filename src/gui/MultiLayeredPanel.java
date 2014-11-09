package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class MultiLayeredPanel extends JLayeredPane {

	private String[] layerStrings = { "bin (0)", "done (1)", "today (2)",
			"tomorrow (3)", "tasklist (4)" };
	private int alphaValue = 240;
	private Color[] layerColors = { new Color(251,172,27,alphaValue),  new Color(52,167,224,alphaValue),  new Color(246,40,52,alphaValue),
			 new Color(248,113,0,alphaValue),  new Color(141,196,0,alphaValue) };

	/**
	 * Create the panel.
	 */
	public MultiLayeredPanel() {
		super();
		// This is the origin of the first label added.
		Point origin = new Point(10, 20);
		// This is the offset for computing the origin for the next label.
		int offset = 35;

		// Add several overlapping, colored labels to the layered pane
		// using absolute positioning/sizing.
		for (int i = 0; i < layerStrings.length; i++) {
			JLabel label = createColoredLabel(layerStrings[i], layerColors[i],
					origin);
			this.add(label, new Integer(i));
			origin.x += offset;
			origin.y += offset;
		}

	}

	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color, Point origin) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setBounds(origin.x, origin.y, 140, 140);
		return label;
	}
	private static void test() {
		MultiLayeredPanel a = new MultiLayeredPanel();
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
