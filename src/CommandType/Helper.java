package CommandType;

import logic.Default;
import logic.DisplayInfo;
import logic.RunLogic;
import logic.VIEW_MODE;

public class Helper implements Command{
	private static String feedback;
	private static String title;
	
	public Helper(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
	}
	
	@Override
	public DisplayInfo execute() {
		RunLogic.getGuiStatus().changeViewMode(VIEW_MODE.HELP);
		return new DisplayInfo(RunLogic.getGuiStatus().getMode(), Default.helpInfo, feedback, title);
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportUndo() {
		return false;
	}

}
