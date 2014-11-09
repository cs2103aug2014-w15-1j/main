package logic;


/**
 * 
 * @author a0119456Y
 *
 */
public class GUIStatus {
	private VIEW_MODE viewMode;
	private boolean hasNext;
	private boolean hasPrevious;
	private int currentTask;
	private JDate date;

	public GUIStatus(VIEW_MODE viewMode, boolean hasNext, boolean hasPrevious, int currentTask, JDate date){
		this.viewMode = viewMode;
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;
		this.currentTask = currentTask;
		this.date = date;
	}
	
	public VIEW_MODE getMode(){
		return this.viewMode ;
	}
	
	public boolean hasNext(){
		return this.hasNext;
	}
	
	public boolean hasPrevious(){
		return this.hasPrevious;
	}
	
	public int getTaskIndex(){
		return this.currentTask;
	}
	
	public JDate getDate(){
		return this.date;
	}
	
	public void changeViewMode(VIEW_MODE mode){
		this.viewMode = mode;
	}
	
	public void changeHasNext(boolean hasNext){
		this.hasNext = hasNext;
	}
	
	public void changeHasPrevious(boolean hasPrevious){
		this.hasPrevious = hasPrevious;
	}
	
	public void changeCurretnTask(int task){
		this.currentTask = task;
	}
	
	public void changeDate(JDate date){
		this.date = date;
	}
}
