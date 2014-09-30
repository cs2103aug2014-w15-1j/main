package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import logic.Task;

public class Display {
	static BasicGUI gFrame;

	public static void display(DisplayConfiguration confg) {
		if (gFrame == null) {
			lauch();
		}
		gFrame.setTitleText(confg.getTitle());
		gFrame.setFeedbackText(confg.getFeedback());
		gFrame.setMainText(confg.getContentString());

	}


	public static void lauch() {
		gFrame = new BasicGUI();
		gFrame.setVisible(true);

	}

}
