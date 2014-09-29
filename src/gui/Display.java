package gui;

import java.awt.EventQueue;

public class Display {
	static BasicGUI frame; 
	public static void display(DisplayConfiguration confg){
		frame.setTitleText(confg.getTitle());
		frame.setFeedbackText(confg.getFeedback());

	}
	public static void lauch(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicGUI frame = new BasicGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args){
		lauch();
	}
}
