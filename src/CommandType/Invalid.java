package CommandType;

import logic.*;

public class Invalid implements Command{
	private static String feedback;
	private static String title;
	
	public Invalid(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
	}
	public Invalid(String myFeedback) {
		feedback = myFeedback;
	}
	
	@Override
	public DisplayInfo execute() {
		return new DisplayInfo(RunLogic.getGuiStatus(), feedback, false, false);
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
