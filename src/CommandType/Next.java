package CommandType;

import gui.VIEW_MODE;
import logic.*;

public class Next implements Command{
	private static String feedback;
	private static String title;
	
	private static GUIStatus GUI;
	public Next(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		GUI = RunLogic.getGuiStatus();
	}
	
	@Override
	public DisplayInfo execute() {
		int FirstIndex = GUI.getTaskIndex() + Default.MAX_DISPLAY_LINE;
		VIEW_MODE mode = GUI.getMode();
		if(mode.equals(VIEW_MODE.TASK_LIST)){
			ViewTaskList view = new ViewTaskList(FirstIndex, feedback, title);
			return view.execute();
		} else {
			ViewTrashBin view = new ViewTrashBin(FirstIndex, feedback, title);
			return view.execute();
		}
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
