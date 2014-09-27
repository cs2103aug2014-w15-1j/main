package logic;

public class CliToLogic {
	private String command;
	private String Arg1;
	private String Arg2;
	private String Arg3;
	private String Arg4;
	private String Arg5;
	
	CliToLogic(String command){
		this.command = command;
		this.Arg1 = null;
		this.Arg2 = null;
		this.Arg3 = null;
		this.Arg4 = null;
		this.Arg5 = null;
	}	
	
	CliToLogic(String command, String Arg1){
		this.command = command;
		this.Arg1 = Arg1;
		this.Arg2 = null;
		this.Arg3 = null;
		this.Arg4 = null;
		this.Arg5 = null;
	}	
	
	CliToLogic(String command, String Arg1, String Arg2){
		this.command = command;
		this.Arg1 = Arg1;
		this.Arg2 = Arg2;
		this.Arg3 = null;
		this.Arg4 = null;
		this.Arg5 = null;
	}	
	
	CliToLogic(String command, String Arg1, String Arg2, String Arg3){
		this.command = command;
		this.Arg1 = Arg1;
		this.Arg2 = Arg2;
		this.Arg3 = Arg3;
		this.Arg4 = null;
		this.Arg5 = null;
	}	
	
	CliToLogic(String command, String Arg1, String Arg2, String Arg3, String Arg4){
		this.command = command;
		this.Arg1 = Arg1;
		this.Arg2 = Arg2;
		this.Arg3 = Arg3;
		this.Arg4 = Arg4;
		this.Arg5 = null;
	}
	
	CliToLogic(String command, String Arg1, String Arg2, String Arg3, String Arg4, String Arg5){
		this.command = command;
		this.Arg1 = Arg1;
		this.Arg2 = Arg2;
		this.Arg3 = Arg3;
		this.Arg4 = Arg4;
		this.Arg5 = Arg5;
	}
	
	public String getCommand(){
		return this.command;
	}
	
	public String getArg1(){
		return this.Arg1;
	}
	
	public String getArg2(){
		return this.Arg2;
	}
	
	public String getArg3(){
		return this.Arg3;
	}
	
	public String getArg4(){
		return this.Arg4;
	}
	
	public String getArg5(){
		return this.Arg5;
	}

}
