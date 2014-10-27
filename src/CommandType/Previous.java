package CommandType;

import gui.VIEW_MODE;
import logic.*;

public class Previous implements Command{
	private static GUIStatus GUI;
	public Previous(){
		GUI = RunLogic.getGuiStatus();
	}

	@Override
	public DisplayInfo execute() {
		Invalid invalid = new Invalid(String.format(Default.CANNOT_FORMAT, "Previous",GUI.getMode().toString()));

		int currentFirstIndex = GUI.getTaskIndex();
		VIEW_MODE mode = GUI.getMode();
		
		if(!GUI.hasPrevious()){
			return invalid.execute();
		}
		if(mode.equals(VIEW_MODE.TASK_LIST)){
			ViewTaskList view = new ViewTaskList(currentFirstIndex - Default.MAX_DISPLAY_LINE);
			return view.execute();
		}
		if(mode.equals(VIEW_MODE.BIN)){
			ViewTrashBin view = new ViewTrashBin(currentFirstIndex - Default.MAX_DISPLAY_LINE);
			return view.execute();
		}
		return invalid.execute();
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}
}
