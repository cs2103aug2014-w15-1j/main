package CommandType;

import logic.DisplayInfo;

public class Undo implements Command {
	
	
	
	
	
	
	
	@Override
	public DisplayInfo execute() {
		return null;
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
