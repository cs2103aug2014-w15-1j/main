package gui;

import java.util.logging.Logger;

import logic.DisplayInfo;
import logic.RunLogic;

/**
 * <code>GuiController</code> is the ultimate driver of
 * <Strong>MagiCal</Strong>. It establish a connection between
 * <Strong>GUI</Strong> and <Strong>Logic</Strong>.It is also the start point of
 * the program.
 * 
 * <p>
 * At startup, it receive the configuration from <Strong>Logic</Strong> and
 * initialize <Strong>GUI</Strong>. When a user command is input,
 * <code>BasicGui</code> is aware of it and notify the
 * <code>EnterKeyListener</code>. Then the <em>actionPerformed</em> method
 * inside <code>EnterKeyListener</code> is invoked. Input command will be passed
 * to <em>display</em> method inside <code>GuiController</code>. So
 * <code>GuiController</code> can call <Strong>Logic</Strong> to process this
 * input and get returned display information. <code>GuiInfoTranslator</code>
 * will translate this display information. Finally <code>GuiController</code>
 * will call <em>setText(setTitleText,
 * setFeedbackText, setMainText)</em> to update new information of
 * <code>BasicGui</code> to show the feedback to user.
 * </p>
 * 
 * @author A0119391A
 * 
 */
public class GuiController {
	private static BasicGui gui;
	private static Logger logger = Logger.getLogger("GuiController");

	/*********************************************
	 ************** Public Method ****************
	 ********************************************/

	public static void main(String[] args) {
		run();
	}
	/**
	 * <em>run()</em>is the start point of <strong>MagiCal</strong>. 
	 * <code>BasicGui</code> instance is obtained and <strong>Gui</strong> initialization is completed here. 
	 */
	public static void run() {
		gui = BasicGui.getInstance();
		logger.info("Gui instance gotten");
		assert gui != null : "GuiController cannot get instance of Gui.";

		DisplayInfo info = RunLogic.initialize();
		display(info);
		logger.info("MagiCal initialization completed.");

		// gui.showLayered();
	}

	/*********************************************
	 ************* Private Method ****************
	 ********************************************/

	/**
	 * call <code>GuiInfoTranslator</code> to translate <code>DisplayInfo</code>
	 * .
	 * 
	 * @param info
	 *            DisplayInfo sent by GUI
	 * @return GuiInfoTranslator
	 */
	private static GuiInfoTranslator translate(DisplayInfo info) {
		return new GuiInfoTranslator(info);
	}
	/**
	 * manage and update <strong>GUI</strong> by given <code>GuiInfoTranslator</code>
	 * @param info
	 */
	private static void display(GuiInfoTranslator info) {
		if (gui == null) {
			run();
		}
		if(info.changeTitle()) {
			gui.setTitleText(info.getTitleString());
		}
		
		gui.setFeedbackText(info.getFeedbackString());

		if (info.changeTaskList()) {
			switch (info.getViewMode()) {
			case TASK_DETAIL:
				gui.ShowDetailed(info.getFirstCol(), info.getSecondCol());
				break;
			case BIN_DETAIL:
				gui.ShowDetailed(info.getFirstCol(), info.getSecondCol());
				break;
			case MONTH:
				throw new UnsupportedOperationException(
						"view in Month is not supported yet");
			default:
				gui.showListed(info.getFirstCol(), info.getSecondCol(), info.getThirdCol());
			}
		}

	}

	/**
	 * show updated <strong>GUI</strong> by given <code>DisplayInfo</code>
	 * <strong>Note><strong>: a shortcut method combine <em>display(GuiInfoTranslator)</em> and
	 * <em>translate(DisplayInfo)</em>.
	 * 
	 * @see #display(GuiInfoTranslator)
	 * @see #translate(DisplayInfo)
	 * @param info
	 */
	private static void display(DisplayInfo info) {
		display(translate(info));
	}
	/**
	 * pass the command to <strong>Logic</strong> and update <strong>GUI</strong> accordingly
	 * @param command
	 */
	static void passToLogic(String command) {
		DisplayInfo info = RunLogic.logic(command);
		logger.info("user enters command: " + command);
		display(info);
		logger.info("user receives feedback");
	}
}
