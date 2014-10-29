package CommandType;

import logic.*;

public class Invalid implements Command{
	private static String feedback;
	private static String title;
	
	public Invalid(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
	}
	
	@Override
	public DisplayInfo execute() {
		// TODO Auto-generated method stub
		
		return new DisplayInfo(RunLogic.getGuiStatus(), feedback, false, title);
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

}
