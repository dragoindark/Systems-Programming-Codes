package labOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

abstract class CommandAbstract {
	protected Process commandRunner;
	protected ArrayList<String> commandOutputs;

	protected void bufferReader(Process commandRunner) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(commandRunner.getInputStream()));
		if (commandOutputs.isEmpty() == false) {
			commandOutputs.clear();
		}
		String line = "";
		while ((line = reader.readLine()) != null) {
			this.commandOutputs.add(line);
		}
	}

	protected String bufferReaderReturn(Process commandRunner) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(commandRunner.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			line += line;
		}
		return line;
	}

	public void commandPrinter(ArrayList<String> commands) {
		for (String command : commands) {
			System.out.println(command);
		}
	}

	public void resultPrinter() {
		for (String command : this.commandOutputs) {
			System.out.println(command);
		}
	}

	public ArrayList<String> getOutput() {
		if (this.commandOutputs != null) {
			return this.commandOutputs;
		} else {
			return null;
		}
	}
}

class CommandExecuter extends CommandAbstract {
	private static CommandExecuter single_instance = null;

	private CommandExecuter() {
		commandOutputs = new ArrayList<String>();
	}

	public static CommandExecuter CommandExecuter() {
		if (single_instance == null) {
			single_instance = new CommandExecuter();
		}
		return single_instance;
	}

	public void executeCommand(String command) {

		// This is a facade, it will take the command make the necessary
		// operations on the string and execute the correct method

	}

	public void cmdWithoutEnvValue(String command) {
		try {
			this.commandRunner = Runtime.getRuntime().exec(command);
			this.bufferReader(commandRunner);
		} catch (IOException e) {
			System.out.println("Error encountered \n" + e);
		}
	}

	public void cmdWithEnvValue(String[] command) {
		try {
			this.commandRunner = Runtime.getRuntime().exec(command);
			this.bufferReader(commandRunner);
		} catch (IOException e) {
			System.out.println("Error encountered \n" + e);
		}
	}

	public void runFromCertainFolder(String command, String path) {
		try {
			command = "sh -c " + command;
			this.commandRunner = Runtime.getRuntime().exec(command, null, new File(path));
			this.bufferReader(commandRunner);
		} catch (IOException e) {
			System.out.println("Error encountered \n" + e);
		}
	}

	public void runFromCurrentDirectory(String command) {
		try {
			command = "sh -c " + command;
		    Process getPwd=Runtime.getRuntime().exec("pwd");
		    String pwd=this.bufferReaderReturn(getPwd);
		    int occurence=pwd.indexOf("/");
		    ArrayList<Integer> allIndexes=new ArrayList<Integer>();
		    allIndexes.add(occurence);
		    for(int i=0;i<pwd.length();i++) {
		    	occurence=pwd.indexOf("/",occurence+1);
		    	if(occurence==-1) {
		    		break;
		    	}else {
		    		allIndexes.add(occurence);
		    	}
		    }
		    ArrayList<String> subStrings=new ArrayList<String>();
		    int startIndex=0;
		    for(int i=0;i<allIndexes.size();i++) {
		    	if(pwd.substring(startIndex,allIndexes.get(i))!="" && startIndex!=pwd.length()-1) {
		    		subStrings.add(pwd.substring(startIndex,allIndexes.get(i)));
		    	}else if(startIndex!=pwd.length()-1) {
		    		break;
		    	}
		    	startIndex=allIndexes.get(i);
		    }    
			this.commandRunner = Runtime.getRuntime().exec(command, null, new File(pwd));
		} catch (IOException e) {
			System.out.println("Error encountered \n" + e);
		}
	}
	

}
