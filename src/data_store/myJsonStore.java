package data_store;

import logic.LogicToStore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import logic.Task;


@SuppressWarnings("unused")
public class myJsonStore {
	
	public static void writeAllData(LogicToStore allData) {
		writeTrash(allData.getTrashbinList());
		writeTask(allData.getTaskList());
	}

	
	public static void writeTrash(ArrayList<Task> trashbin){
	
		
	}
	
	
	public static void writeTask(ArrayList<Task> tasks){
		
	}
	
	public void create(ArrayList<Task> info, String filename){
				
		for(int i=0; i < info.size(); i++){
		
		Gson convertTask = new Gson();	
		String jsonStringTaskObj = convertTask.toJson(info.get(i));
		
		try {
			//write converted json data to a file named "filename"
			PrintWriter fileOut = new PrintWriter(
					new BufferedWriter (
                    new FileWriter("filename")));
			
			fileOut.write(jsonStringTaskObj);
			fileOut.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		}
	}
	
	}

