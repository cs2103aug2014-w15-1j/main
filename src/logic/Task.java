package logic;

import java.util.Date;

import data_store.SystemInfo;

public class Task {
	private String name;
	private String description;
	private String repeatDays;
	private JDate startDate;
	private JDate endDate;
	
	public Task(){
		// empty constructor
	}
	
	public Task(String name){
		this.name = name;
		this.description = null;
		this.repeatDays = null;
		this.startDate = null;
		this.endDate = null;
	}
	
	public Task(String name, String description){
		this.name = name;
		this.description = description;
		this.repeatDays = null;
		this.startDate = null;
		this.endDate = null;
	}
	
	public Task(String name, String description, String repeatDays){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = null;
		this.endDate = null;
	}
	
	public Task(String name, String description, String repeatDays, JDate startDate2, JDate endDate2){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = startDate2;
		this.endDate = endDate2;
	}
	
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
