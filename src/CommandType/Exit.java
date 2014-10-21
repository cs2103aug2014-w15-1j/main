package CommandType;

import logic.DisplayInfo;

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
	
}
