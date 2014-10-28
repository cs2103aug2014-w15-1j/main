package CommandType;

import gui.VIEW_MODE;
import logic.*;

public class Previous implements Command{
	private static String feedback;
	private static String title;
	
	private static GUIStatus GUI;
	public Previous(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		GUI = RunLogic.getGuiStatus();
	}
	
	@Override
	public DisplayInfo execute() {
		int currentFirstIndex = GUI.getTaskIndex();
		VIEW_MODE mode = GUI.getMode();

		if(mode.equals(VIEW_MODE.TASK_LIST)){
			ViewTaskList view = new ViewTaskList(currentFirstIndex - Default.MAX_DISPLAY_LINE, feedback, title);
			return view.execute();
		} else {
			ViewTrashBin view = new ViewTrashBin(currentFirstIndex - Default.MAX_DISPLAY_LINE, feedback, title);
			return view.execute();
		}
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}
}
