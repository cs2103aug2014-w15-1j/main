package cli;

public class CliToLog{
		
		private String commandFirst;
		
		/* Assigned from an array of strings behind command word
		 * 
		 * Maximum number of arguments for any command is 4
		 */
		private String argument1;
		private String argument2;
		private String argument3;
		private String argument4;
		
		public CliToLog(String[] input) {
			this.commandFirst = input[0];
			this.argument1 = input[1];
			this.argument2 = input[2];
			this.argument3 = input[3];
			this.argument4 = input[4];			

		}


		public String getcommand(){
			return commandFirst;
		}
		
		public String getArg1(){
			return this.argument1;
		}
		
		public String getArg2(){
			return this.argument2;
		}
		
		public String getArg3(){
		   return this.argument3;
		}
		
		public String getArg4(){
			return this.argument4;
		}
		

	}

