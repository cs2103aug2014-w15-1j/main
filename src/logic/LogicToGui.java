package logic;

public class LogicToGui {
	private GUIStatus GUI;
	private String[] TaskList;
	
	LogicToGui(){
		this.GUI = new GUIStatus();
		this.TaskList = null;
	}
	
	LogicToGui(GUIStatus GUI, String[] taskList){
		this.GUI = GUI;
		this.TaskList = taskList;
	}
	
	public GUIStatus getGUIstatus(){
		return this.GUI;
	}
	
	public String[] getList(){
		return this.TaskList;
	}
}
