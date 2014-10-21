package CommandType;

import gui.VIEW_MODE;

import logic.*;

public class Back implements Command{
	//local memory
	private static GUIStatus GUI;
	
	public Back(){
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		GUI.changeCurretnTask(GUI.getTaskIndex() - GUI.getTaskIndex() % Default.MAX_DISPLAY_LINE);
	
		if(GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)){
			ViewTrashBin view = new ViewTrashBin(GUI.getTaskIndex());
			return view.execute();
		} else if (GUI.getMode().equals(VIEW_MODE.TASK_DETAIL)){
			ViewTaskList view = new ViewTaskList(GUI.getTaskIndex());
			return view.execute();
		}
		Invalid invalid = new Invalid(Default.INVALID_FEEDBACK);
		return invalid.execute();
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
