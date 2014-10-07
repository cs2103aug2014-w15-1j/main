package logic;

import java.util.Date;
import data_store.SystemInfo;

public class Task {
	private String name;
	private String description;
	private String repeatDays;
	private Date startDate;
	private Date endDate;
	
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
	
	public Task(String name, String description, String repeatDays, Date startDate, Date endDate){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = startDate;
		this.endDate = endDate;
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
	
	public Date getStartDate(){
		return this.startDate;
	}
	
	public Date getEndDate(){
		return this.endDate;
	}
	
	public void rename(String newName){
		this.name = newName;
	}
	
	public void reschedule(Date newStartDate, Date newEndDate){
		this.startDate = newStartDate;
		this.endDate = newEndDate;
	}
	
	public void describe(String newDescription){
		this.description = newDescription;
	}
	
	public void repeat(String newRepeatDays){
		this.repeatDays = newRepeatDays;
	}
	
	@SuppressWarnings("deprecation")
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
