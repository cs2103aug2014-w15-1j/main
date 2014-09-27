package logic;

public class LogicToStore {
	private String[] TaskList;
	
	LogicToStore(){
		this.TaskList = null;
	}
	
	LogicToStore(String[] taskList){
		this.TaskList = taskList;
	}
	
	public String[] getList(){
		return this.TaskList;
	}
}
