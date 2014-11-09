package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

/**
 * <code>TransColorTextField</code> is a customized <code>JTextField</code>
 * which allows background color switch when new text is set.
 * 
 * @author A0119391A
 * 
 */
public class TransColorTextField extends JTextField {

	private static final long serialVersionUID = 2017126697448899239L;
	
	private static final Color COLOR_A = new Color(255, 255, 255, 100);
	private static final Color COLOR_B = new Color(128, 138, 135, 100);
	private Color currentColor;
	
	private static final Font FONT_TEXT = new Font("DIALOG", Font.ITALIC, 40);
	
	private static final int PREFEFERRED_WIDTH = 200;
	private static final int PREFEFERRED_HEIGHT = 40;
	/*********************************************
	 *************** Constructor *****************
	 ********************************************/
	public TransColorTextField() {
		super();
		this.setPreferredSize(new Dimension(PREFEFERRED_WIDTH, PREFEFERRED_HEIGHT));
		this.setFont(FONT_TEXT);
		currentColor = COLOR_A;

	}
	/*********************************************
	 ************* Public Method ****************
	 ********************************************/
	public void setTextTransColor(String txt) {
		setBackground(currentColor);
		setText(txt);
		switchColor();

	}
	/*********************************************
	 ************* Private Method ****************
	 ********************************************/
	private void switchColor() {
		if (currentColor.equals(COLOR_A)) {
			currentColor = COLOR_B;
		} else {
			currentColor = COLOR_A;
		}
	}
	}

