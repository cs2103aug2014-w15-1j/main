package CommandType;

import logic.DisplayInfo;

/**
 * 
 * @author a0119456Y
 *
 */
public interface Command {
	public DisplayInfo execute();	
	public DisplayInfo undo();
	public boolean supportUndo();
}
