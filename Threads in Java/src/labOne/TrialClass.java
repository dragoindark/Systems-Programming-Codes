package labOne;

import java.util.ArrayList;

public class TrialClass {

	public static void main(String[] args) {
		CommandExecuter executer=CommandExecuter.CommandExecuter();
		executer.runFromCertainFolder("ls","//home");
		
		ArrayList<String> trial=executer.getOutput();
		for (String name:trial) {
			System.out.println(name);
		}
		
		
	}

}
