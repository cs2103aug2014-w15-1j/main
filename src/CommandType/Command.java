package CommandType;

import logic.DisplayInfo;

public interface Command {
	DisplayInfo execute();	
	DisplayInfo undo();
}
