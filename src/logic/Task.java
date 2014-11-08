package logic;

import logic.JDate;

import data_store.SystemInfo;

public class Task {
	private String name;
	private String description;
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
		this.startDate = null;
		this.endDate = null;
		this.done = false;
	}
	
	/*
	 * @para 2 fields
	 */
	public Task(String name, String description){
		this.name = name;
		this.description = description;
		this.startDate = null;
		this.endDate = null;
		this.done = false;
	}
	
	/*
	 * @para 3 fields
	 * Input has No Description,No Start Date
	 */
	public Task(String name, String description, JDate endDate){
		this.name = name;
		this.description = description;
		this.startDate = null;
		this.endDate = endDate;
		this.done = false;
	}
	
	/*
	 * @para 3 fields
	 * Input has No Description, No End Date
	 */
	public Task(String name, JDate endDate){
		this.name = name;
		this.description = null;
		this.startDate = null;
		this.endDate = endDate;
		this.done = false;
	}
	
	/*
	 * @para 4 fields
	 * Input has no Description
	 */
	public Task(String name, JDate startDate, JDate endDate){
		this.name = name;
		this.description = null;
		this.startDate = startDate;
		this.endDate = endDate;
		this.done = false;
	}
	
	/*
	 * Full 5 parts
	 */
	public Task(String name, String description, JDate startDate2, JDate endDate2){
		this.name = name;
		this.description = description;
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

		if((this.done || task.done) && !(this.done && task.done)){
			return false;
		}
		return true;
	}
	
	public String toPersonalString() {
	    String startDateStr;
	    String endDateStr;
	    String status;
	    
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

	    if (this.done){
	    	status = SystemInfo.CHECKDONE;
	    } else {
	    	status = SystemInfo.CHECKUNDONE;
	    }
	    
	    return this.name + 
	    		SystemInfo.SEPERATESIMBOL + this.description +  
	    		SystemInfo.SEPERATESIMBOL + startDateStr + 
	    		SystemInfo.SEPERATESIMBOL + endDateStr +
	    		SystemInfo.SEPERATESIMBOL + status;
	}
}
