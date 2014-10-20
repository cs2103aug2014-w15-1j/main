package gui;

import java.util.logging.Logger;

import logic.DisplayInfo;
import logic.RunLogic;

public class GuiController {
	private static BasicGui gui;
	private static Logger logger = Logger.getLogger("GuiController"); 
	
	private static GuiInfoTranslator translate(DisplayInfo info){
		return new GuiInfoTranslator(info);
	}
	
	private static void display(GuiInfoTranslator info) {
		if (gui == null) {
			run();
		}
		gui.setTitleText(info.getTitleString());
		gui.setFeedbackText(info.getFeedbackString());
		gui.setMainText(info.getTaskString());
	}
	
	private static void display(DisplayInfo info) {
		display(translate(info));
	}
	
	static void display(String command){
		DisplayInfo info= RunLogic.logic(command);
		logger.info("user enters command: " + command);
		display(info);
		logger.info("user receives feedback");
	}

	public static void run(){
		gui = BasicGui.getInstance();
		
		logger.info("Gui instance gotten");
		assert gui!=null:"GuiController cannot get instance of Gui";
		DisplayInfo confg= RunLogic.initialize();
		display(confg);
		logger.info("MagiCal initialization completed");
		gui.showLayered();
	}
	public static void main(String[] args){
		run();
	}

}
