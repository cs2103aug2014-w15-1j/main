package gui;

public class DisplayController {
	static BasicGUI gui;

	public static void display(DisplayConfiguration confg) {
		if (gui == null) {
			start();
		}
		gui.setTitleText(confg.getTitleString());
		gui.setFeedbackText(confg.getFeedbackString());
		gui.setMainText(confg.getTaskString());
	}

	public static void start(){
		gui = new BasicGUI();
		gui.setVisible(true);
		DisplayConfiguration confg= gui.initializeLogic();
		display(confg);
	}
	public static void main(String[] args){
		start();
	}

}
