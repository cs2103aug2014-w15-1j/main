package gui;

import logic.DisplayInfo;
import logic.RunLogic;

public class GuiController {
	static BasicGui gui;
	
	private static InfoConverter convert(DisplayInfo info){
		return new InfoConverter(info);
	}
	
	public static void display(InfoConverter info) {
		if (gui == null) {
			run();
		}
		gui.setTitleText(info.getTitleString());
		gui.setFeedbackText(info.getFeedbackString());
		gui.setMainText(info.getTaskString());
	}
	
	public static void display(DisplayInfo info) {
		display(convert(info));
	}
	
	public static void display(String command){
		DisplayInfo info= RunLogic.logic(command);
		display(info);
	}

	private static void run(){
		gui = BasicGui.getInstance();
		DisplayInfo confg= RunLogic.initialize();
		display(confg);
	}
	public static void main(String[] args){
		run();
	}

}
