package CommandType;

import logic.DisplayInfo;

/**
 * 
 * @author a0119456Y
 *
 */
public class Exit implements Command{

	public Exit(){
		
	}
	
	@Override
	public DisplayInfo execute() {
		System.exit(0);
		return null;
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

}
