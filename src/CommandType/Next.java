package CommandType;

import gui.VIEW_MODE;
import logic.*;

public class Next implements Command{
	private static GUIStatus GUI;
	public Next(){
		GUI = RunLogic.getGuiStatus();
	}

	@Override
	public DisplayInfo execute() {
		int currentFirstIndex = GUI.getTaskIndex();
		VIEW_MODE mode = GUI.getMode();
		if(mode.equals(VIEW_MODE.TASK_LIST)){
			ViewTaskList view = new ViewTaskList(currentFirstIndex + Default.MAX_DISPLAY_LINE);
			return view.execute();
		}
		if(mode.equals(VIEW_MODE.BIN)){
			ViewTrashBin view = new ViewTrashBin(currentFirstIndex + Default.MAX_DISPLAY_LINE);
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

}
