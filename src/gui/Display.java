package gui;

public class Display {
	static BasicGUI gui;

	public static void display(DisplayConfiguration confg) {
		if (gui == null) {
			lauch();
		}
		gui.setTitleText(confg.getTitleString());
		gui.setFeedbackText(confg.getFeedbackString());
		gui.setMainText(confg.getTaskString());
	}

	public static void lauch() {
		gui = new BasicGUI();
		gui.setVisible(true);

	}

}
