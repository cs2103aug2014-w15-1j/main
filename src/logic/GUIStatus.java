package logic;

public class GUIStatus {
	private int ViewMode;
	private boolean hasNext;
	private boolean hasPrevious;
	private int currentTask;
	
	GUIStatus(){
		this.ViewMode = 0;
		this.hasNext = false;
		this.hasPrevious = false;
		this.currentTask = 0;
	}
	
	GUIStatus(int ViewMode, boolean hasNext, boolean hasPrevious, int curretnTask){
		this.ViewMode = ViewMode;
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;
		this.currentTask = curretnTask;
	}
	
	public int getMode(){
		return this.ViewMode ;
	}
	
	public boolean hasNext(){
		return this.hasNext;
	}
	
	public boolean hasPrevious(){
		return this.hasPrevious;
	}
	
	public int getTask(){
		return this.currentTask;
	}
	public void changeViewMode(int mode){
		this.ViewMode = mode;
	}
	
	public void changeHasNext(){
		this.hasNext = !this.hasNext;
	}
	
	public void changeHasPrevious(){
		this.hasPrevious = !this.hasPrevious;
	}
	
	public void changeCurretnTask(int task){
		this.currentTask = task;
	}
}
