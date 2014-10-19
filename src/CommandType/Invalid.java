package CommandType;

import logic.*;

public class Invalid implements Command{
	String feedback;
	
	public Invalid(String feedback){
		this.feedback = feedback;
	}
	
	@Override
	public DisplayInfo execute() {
		// TODO Auto-generated method stub
		
		return new DisplayInfo(RunLogic.getGuiStatus(), feedback, false);
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

}
