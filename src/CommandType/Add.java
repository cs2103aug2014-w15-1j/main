package CommandType;

import logic.*;

public class Add implements Command{	
	private static Task task;
	
	public Add(Task newTask){
		task = newTask;
	}

	@Override
	public DisplayInfo execute() {
		RunLogic.getTaskList().add(task);
		
		return null;
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

}
