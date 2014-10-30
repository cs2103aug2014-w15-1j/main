package logic;

import logic.JDate;

import data_store.SystemInfo;

public class Task {
	private String name;
	private String description;
	private String repeatDays;
	private JDate startDate;
	private JDate endDate; 
	private boolean done;
	
	//added by Zhang Ji
	private long pointer;
	public void setPointer(long pointer) {
		this.pointer = pointer;
	}
	public long getPointer() {
		return pointer;
	}
	
	// constructor
	public Task(){
		// empty constructor
	}
	
	public Task(String name){
		this.name = name;
		this.description = null;
		this.repeatDays = null;
		this.startDate = null;
		this.endDate = null;
		this.done = false;
	}
	
	public Task(String name, String description){
		this.name = name;
		this.description = description;
		this.repeatDays = null;
		this.startDate = null;
		this.endDate = null;
		this.done = false;
	}
	
	public Task(String name, String description, String repeatDays){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = null;
		this.endDate = null;
		this.done = false;
	}
	
	public Task(String name, String description, String repeatDays, JDate startDate2, JDate endDate2){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = startDate2;
		this.endDate = endDate2;
		this.done = false;
	}
	
	
	// API for get info
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getRepeatDays(){
		return this.repeatDays;
	}
	
	public JDate getStartDate(){
		return this.startDate;
	}
	
	public JDate getEndDate(){
		return this.endDate;
	}
	
	public boolean getDone(){
		return this.done;
	}
	
	// API for modify
	public void rename(String newName){
		this.name = newName;
	}
	
	public void reschedule(JDate newStartDate, JDate newEndDate){
		this.startDate = newStartDate;
		this.endDate = newEndDate;
	}
	
	public void describe(String newDescription){
		this.description = newDescription;
	}
	
	public void repeat(String newRepeatDays){
		this.repeatDays = newRepeatDays;
	}
	
	public void setDone(){
		this.done = true;
	}
	
	public void setUndone(){
		this.done = false;
	}
	
	// API for compare
	public boolean equals(Task task){
		if(!this.name.equals(task.name)){
			return false;
		}
		if(!this.description.equals(task.description)){
			return false;
		}
		if(!this.startDate.equals(task.startDate)){
			return false;
		}
		if(!this.endDate.equals(task.endDate)){
			return false;
		}
		if(!this.repeatDays.equals(task.repeatDays)){
			return false;
		}
		if((this.done || task.done) && !(this.done && task.done)){
			return false;
		}
		return true;
	}
	
	public String toPersonalString() {
	    String startDateStr;
	    String endDateStr;
	    if (this.startDate == null) {
	        startDateStr = SystemInfo.EMPTYDATE;
	    } else {
	        startDateStr = "" + this.startDate.getYear() + SystemInfo.SPLIT_DATE_SYMBOL + 
	                            this.startDate.getMonth() + SystemInfo.SPLIT_DATE_SYMBOL + 
	                            this.startDate.getDay();
	    }
	    if (this.endDate == null) {
	        endDateStr = SystemInfo.EMPTYDATE;
	    } else {
	        endDateStr =   "" + this.endDate.getYear()  + SystemInfo.SPLIT_DATE_SYMBOL + 
	                            this.endDate.getMonth()  + SystemInfo.SPLIT_DATE_SYMBOL + 
	                            this.endDate.getDay();
	    }

	    return this.name + SystemInfo.SEPERATESIMBOL + this.description + 
	           SystemInfo.SEPERATESIMBOL + this.repeatDays + 
	           SystemInfo.SEPERATESIMBOL + startDateStr + 
	           SystemInfo.SEPERATESIMBOL + endDateStr;
	}
}
