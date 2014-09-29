package logic;

public class Task {
	private String name;
	private String description;
	private String repeatTimes;
	private String repeatDays;
	private String startDate;
	private String endDate;
	
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
	
	public Task(String name, String description, String repeatTimes, String repeatDays, String startDate, String endDate){
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
	
	public String getStartDate(){
		return this.startDate;
	}
	
	public String getEndDate(){
		return this.endDate;
	}
	
	public void rename(String newName){
		this.name = newName;
	}
	
	public void reschedule(String newStartDate, String newEndDate){
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
}
