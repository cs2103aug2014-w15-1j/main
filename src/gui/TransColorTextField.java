package gui;

import java.awt.Color;
import javax.swing.JTextField;

public class TransColorTextField extends JTextField{

	private static final long serialVersionUID = 2017126697448899239L;
	private Color colorA = new Color(255,255,255,100);
	private Color colorB = new Color(128,138,135,50);
	private Color currentColor;
	
	public TransColorTextField() {
		super();
		currentColor = colorA;
		
	}
	public void setTextTransColor(String txt) {
		setBackground(currentColor);
		setText(txt);
		switchColor();
		
	}
	private void switchColor() {
		if(currentColor.equals(colorA) ) {
			currentColor = colorB;
		} else {
			currentColor = colorA;
		}
	}

}
