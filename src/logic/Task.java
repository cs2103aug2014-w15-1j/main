package logic;

import java.util.Date;

public class Task {
	private String name;
	private String description;
	private String repeatTimes;
	private String repeatDays;
	private Date startDate;
	private Date endDate;
	private final String DEVIDESYMBOL = "-";
	
	public Task(){
		// empty constructor
	}
	
	public Task(String name){
		this.name = name;
		this.description = null;
		this.repeatTimes = null;
		this.repeatDays = null;
		this.startDate = null;
		this.endDate = null;
	}
	
	public Task(String name, String description){
		this.name = name;
		this.description = description;
		this.repeatTimes = null;
		this.repeatDays = null;
		this.startDate = null;
		this.endDate = null;
	}
	
	public Task(String name, String description, String repeatTimes, String repeatDays){
		this.name = name;
		this.description = description;
		this.repeatTimes = repeatTimes;
		this.repeatDays = repeatDays;
		this.startDate = null;
		this.endDate = null;
	}
	
	public Task(String name, String description, String repeatTimes, String repeatDays, Date startDate, Date endDate){
		this.name = name;
		this.description = description;
		this.repeatTimes = repeatTimes;
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
	
	public String getRepeatTimes(){
		return this.repeatTimes;
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
	
	public void repeat(String newRepeatTimes, String newRepeatDays){
		this.repeatTimes = newRepeatTimes;
		this.repeatDays = newRepeatDays;
	}
	
	public String toString() {
	    return this.name + DEVIDESYMBOL + this.description + DEVIDESYMBOL + this.repeatTimes +
	           this.repeatDays + DEVIDESYMBOL + this.startDate.toString() + DEVIDESYMBOL + this.endDate.toString();
	}
}
