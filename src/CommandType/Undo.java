package CommandType;

import logic.RunLogic;
import logic.DisplayInfo;

/**
 * 
 * @author A0119493X
 *
 */
public class Undo implements Command {
	
	
	private static String NO_UNDO = "No available undo!";	
	
	
	@Override
	public DisplayInfo execute() {
		if(!RunLogic.hasPastCommands()) {
			return new Invalid(NO_UNDO).execute();
		} else {
			return RunLogic.undo();
		}
	}

	@Override
	public DisplayInfo undo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean supportUndo() {
		return false;
	}

}
