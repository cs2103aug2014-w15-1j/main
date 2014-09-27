package logic;

public class GUIStatus {
	private int ViewMode;
	private boolean hasNext;
	private boolean hasPrevious;
	
	GUIStatus(){
		this.ViewMode = 0;
		this.hasNext = false;
		this.hasPrevious = false;
	}
	
	GUIStatus(int ViewMode, boolean hasNext, boolean hasPrevious){
		this.ViewMode = ViewMode;
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;
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
}
