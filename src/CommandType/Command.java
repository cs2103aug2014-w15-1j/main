package CommandType;

import logic.DisplayInfo;

public interface Command {
	public DisplayInfo execute();	
	public DisplayInfo undo();
}
