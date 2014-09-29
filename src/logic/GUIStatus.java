package logic;

import gui.VIEW_MODE;

public class GUIStatus {
	private VIEW_MODE mode ;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private int currentTaskIndex;
	
	GUIStatus(VIEW_MODE mode, boolean hasNextPage, boolean hasPrevious, int curretnTask){
		this.mode = mode;
		this.hasNextPage = hasNextPage;
		this.hasPreviousPage = hasPreviousPage;
		this.currentTaskIndex = curretnTask;
	}
	
	public VIEW_MODE getMode(){
		return this.mode ;
	}
	
	public boolean hasNextPage(){
		return this.hasNextPage;
	}
	
	public boolean hasPreviousPage(){
		return this.hasPreviousPage;
	}
	
	public int getTask(){
		return this.currentTaskIndex;
	}
	public void changeViewMode(int mode){
		this.mode = mode;
	}
	
	public void FlipHasNextPage(){
		this.hasNextPage = !this.hasNextPage;
	}
	
	public void changeHasPreviousPage(){
		this.hasPreviousPage = !this.hasPreviousPage;
	}
	
	public void changeCurretnTask(int task){
		this.currentTaskIndex = task;
	}
}
