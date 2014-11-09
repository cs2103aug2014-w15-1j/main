package CommandType;

import logic.*;

/**
 * 
 * @author a0119456Y
 *
 */
public class Invalid implements Command{
	private static String feedback;
	
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
