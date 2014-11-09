package CommandType;

import logic.*;

/**
 * 
 * @author a0119456Y
 *
 */
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
		int highlightLine = determineHighlight();
		updateGUI();
		return constructDisplay(highlightLine);
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

	
	//-----------helper functions-----------------

	private static void initialize(){
		GUI = RunLogic.getGuiStatus();
	}
	
	private DisplayInfo constructDisplay(int highlightLine) {
		Command view;
		if(GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)){
			view = new ViewTrashBin(GUI.getTaskIndex(), feedback, title);
		} else {
			view = new ViewTaskList(GUI.getTaskIndex(), feedback, title);
		}
		DisplayInfo dis = view.execute();
		dis.setHighlight(Default.HIGHLIGHT_LINE);
		dis.setHighlightLine(highlightLine);
		return dis;
	}

	private void updateGUI() {
		GUI.changeCurretnTask(GUI.getTaskIndex() - GUI.getTaskIndex() % Default.MAX_DISPLAY_LINE);
	}

	private int determineHighlight() {
		return GUI.getTaskIndex() % Default.MAX_DISPLAY_LINE;
	}

}
