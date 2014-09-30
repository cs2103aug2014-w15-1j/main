package gui;

public class Display {
	static BasicGUI gFrame;

	public static void display(DisplayConfiguration confg) {
		if (gFrame == null) {
			lauch();
		}
		gFrame.setTitleText(confg.getTitleString());
		gFrame.setFeedbackText(confg.getFeedbackString());
		gFrame.setMainText(confg.getTaskString());
	}

	public static void lauch() {
		gFrame = new BasicGUI();
		gFrame.setVisible(true);

	}

}
