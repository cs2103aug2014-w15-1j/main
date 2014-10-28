package CommandType;

import gui.VIEW_MODE;

import logic.*;

public class Back implements Command{
	private static String feedback;
	private static String title;
	
	//local memory
	private static GUIStatus GUI;
	
	public Back(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		initialize();
	}

	@Override
	public DisplayInfo execute() {
		GUI.changeCurretnTask(GUI.getTaskIndex() - GUI.getTaskIndex() % Default.MAX_DISPLAY_LINE);
	
		if(GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)){
			ViewTrashBin view = new ViewTrashBin(GUI.getTaskIndex(), feedback, title);
			return view.execute();
		} else {
			ViewTaskList view = new ViewTaskList(GUI.getTaskIndex(), feedback, title);
			return view.execute();
		}
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	//-----------helper functions-----------------

	private static void initialize(){
		GUI = RunLogic.getGuiStatus();
	}
}
