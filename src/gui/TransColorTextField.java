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
	private Color colorA = new Color(255, 255, 255, 100);
	private Color colorB = new Color(128, 138, 135, 100);
	private Color currentColor;
	/*********************************************
	 *************** Constructor *****************
	 ********************************************/
	public TransColorTextField() {
		super();
		this.setPreferredSize(new Dimension(200,40));
		Font font = new Font("DIALOG", Font.ITALIC, 40);
		this.setFont(font);
		currentColor = colorA;

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
		if (currentColor.equals(colorA)) {
			currentColor = colorB;
		} else {
			currentColor = colorA;
		}
	}

}
