package gui;

import java.util.logging.Logger;

import logic.DisplayInfo;
import logic.RunLogic;

public class GuiController {
	private static BasicGui gui;
	private static Logger logger = Logger.getLogger("GuiController");

	private static GuiInfoTranslator translate(DisplayInfo info) {
		return new GuiInfoTranslator(info);
	}

	private static void display(GuiInfoTranslator info) {
		if (gui == null) {
			run();
		}
		gui.setTitleText(info.getTitleString());
		gui.setFeedbackText(info.getFeedbackString());

		if (info.changeTaskList()) {
			switch (info.getViewMode()) {
			case TASK_DETAIL:
				if(info.processTaskInfo()) {
					gui.ShowDetailed(info.getFirstCol(), info.getSecondCol());
				}
				break;
			case BIN_DETAIL:
				if(info.processTaskInfo()) {
					gui.ShowDetailed(info.getFirstCol(), info.getSecondCol());
				}
				break;
			case MONTH:
				throw new UnsupportedOperationException(
						"view in Month is not supported yet");
			default:
				if(info.processTaskInfo()) {
					gui.showListed(info.getFirstCol(), info.getSecondCol());
				}
			}
		}

	}

	private static void display(DisplayInfo info) {
		display(translate(info));
	}

	static void display(String command) {
		DisplayInfo info = RunLogic.logic(command);
		logger.info("user enters command: " + command);
		display(info);
		logger.info("user receives feedback");
	}

	public static void run() {
		gui = BasicGui.getInstance();
		//logger.info("Gui instance gotten");
		assert gui != null : "GuiController cannot get instance of Gui";
		DisplayInfo info = RunLogic.initialize();
		display(info);
		logger.info("MagiCal initialization completed sdaflass");
		//gui.showLayered();
	}

	public static void main(String[] args) {
		run();
	}
}
