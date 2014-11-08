package logic;

import logic.JDate;

import data_store.SystemInfo;

public class Task implements Comparable<Task> {
	private String name;
	private String description;
	private String repeatDays;
	private JDate startDate;
	private JDate endDate; 
	private boolean done;
	
	//added by Zhang Ji
	private int pointer;
	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
	public int getPointer() {
		return pointer;
	}
	public boolean matchPointer(int ptr){
		return this.pointer == ptr;
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
	
	/*
	 * @para 2 fields
	 */
	public Task(String name, String repeatDays){
		this.name = name;
		this.description = null;
		this.repeatDays = repeatDays;
		this.startDate = null;
		this.endDate = null;
		this.done = false;
	}
	
	/*
	 * @para 3 fields
	 * Input has No Description,No Start Date
	 */
	public Task(String name, String repeatDays, JDate endDate){
		this.name = name;
		this.description = null;
		this.repeatDays = repeatDays;
		this.startDate = null;
		this.endDate = endDate;
		this.done = false;
	}
	
	/*
	 * @para 3 fields
	 * Input has No Description, No End Date
	 */
	public Task(String name, JDate startDate, String repeatDays){
		this.name = name;
		this.description = null;
		this.repeatDays = repeatDays;
		this.startDate = startDate;
		this.endDate = null;
		this.done = false;
	}
	
	/*
	 * @para 3 fields
	 * Input has No Start Date, No End Date
	 */
	public Task(String name, String description, String repeatDays){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = null;
		this.endDate = null;
		this.done = false;
	}
	
	/*
	 * @para 4 fields
	 * Input has no Description
	 */
	public Task(String name, String repeatDays, JDate startDate, JDate endDate){
		this.name = name;
		this.description = null;
		this.repeatDays = repeatDays;
		this.startDate = startDate;
		this.endDate = endDate;
		this.done = false;
	}

	
	/*
	 * @para 4 fields
	 * Input has no Start Date
	 */
	public Task(String name, String description, String repeatDays, JDate endDate){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = null;
		this.endDate = endDate;
		this.done = false;
	}

	/*
	 * @para 4 fields
	 * Input has no End Date
	 */
	public Task(String name, String description, JDate startDate, String repeatDays){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = startDate;
		this.endDate = null;
		this.done = false;
	}
	
	/*
	 * Full 5 parts
	 */
	public Task(String name, String description, String repeatDays, JDate startDate2, JDate endDate2){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = startDate2;
		this.endDate = endDate2;
		this.done = false;
	}
	
	
	
	/*public Task(String name, String description, String repeatDays, JDate startDate2, null){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = startDate2;
		this.endDate = endDate2;
		this.done = false;
	}
	
	
	public Task(String name, String description, String repeatDays, null, JDate endDate2){
		this.name = name;
		this.description = description;
		this.repeatDays = repeatDays;
		this.startDate = startDate2;
		this.endDate = endDate2;
		this.done = false;
	}
	*/
	
	
	
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
		
		//no description compare
		if(this.description==null && task.description!=null){
			return false;
		}
		if(description==null && task.description==null){
			return true;
		}
		
		
		if(!this.description.equals(task.description)){
			return false;
		}
		
		//no start date compare
		if(this.startDate==null && task.startDate!=null){
			return false;
		}
		if(this.startDate==null && task.startDate==null){
			return true;
		}
		
		
		if(!this.startDate.equals(task.startDate)){
			return false;
		}
		
		//no end date compare
		if(this.endDate==null && task.endDate!=null){
			return false;
		}
		if(this.endDate==null && task.endDate==null){
			return true;
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
	                            this.startDate.getDay() + SystemInfo.SPLIT_DATE_SYMBOL +
	                            SystemInfo.CHECKSTART;
	    }
	    
	    if (this.endDate == null) {
	        endDateStr = SystemInfo.EMPTYDATE;
	    } else {
	        endDateStr =   "" + this.endDate.getYear()  + SystemInfo.SPLIT_DATE_SYMBOL + 
	                            this.endDate.getMonth()  + SystemInfo.SPLIT_DATE_SYMBOL + 
	                            this.endDate.getDay() + SystemInfo.SPLIT_DATE_SYMBOL + 
	                            SystemInfo.CHECKEND;
	    }

	    return this.name + SystemInfo.SEPERATESIMBOL + this.description + 
	           SystemInfo.SEPERATESIMBOL + this.repeatDays + 
	           SystemInfo.SEPERATESIMBOL + startDateStr + 
	           SystemInfo.SEPERATESIMBOL + endDateStr;
	}
	@Override
	public int compareTo(Task o) {
		if(this.endDateCompareTo(o) != 0){
			return this.endDateCompareTo(o);
		} else {
			return this.startDateCompareTo(o);
		}
		
	}
	private int endDateCompareTo(Task o) {
		if(o == null) {
			return 1;
		}
		if(getEndDate() == null) {	
			if(o.getEndDate() != null) {
				return -1;
			} else {
				return 0;
			}
		} else {
			return getEndDate().compareTo(o.getEndDate());
		}
	}
	private int startDateCompareTo(Task o) {
		if(o == null) {
			return 1;
		}
		if(getStartDate() == null) {	
			if(o.getStartDate() != null) {
				return -1;
			} else {
				return 0;
			}
		} else {
			return getStartDate().compareTo(o.getStartDate());
		}
	}
}
