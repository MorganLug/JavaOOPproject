package fr.Group13.CLUIClasses;

import java.util.Arrays;

public class CommandProcessing {

	public void identifyCommand(String command, MainCLUI cli) {
		System.out.println("\n");
		String[] commandAndArgs = command.split(" ");
		String commandName = commandAndArgs[0];
		String[] arguments = Arrays.copyOfRange(commandAndArgs, 1, commandAndArgs.length);
		if (commandName.equals("exit")) {
			cli.active=false;
			System.out.println("You asked to exit the CLUI.");
		} else if (commandName.equals("setup")) {
			Setup setup = new Setup();
			setup.processArguments(arguments, cli);
		}  else if (commandName.equals("addUser")) {
			AddUser addUser = new AddUser();
			addUser.processArguments(arguments, cli);
		}  else if (commandName.equals("offline")) {
			Offline offline = new Offline();
			offline.processArguments(arguments, cli);
		} else if (commandName.equals("online")) {
			Online online = new Online();
			online.processArguments(arguments, cli);
		} else if (commandName.equals("displayUser")) {
			DisplayUser displayUser = new DisplayUser();
			displayUser.processArguments(arguments, cli);
		} else if (commandName.equals("displayStation")) {
			DisplayStation displayStation = new DisplayStation();
			displayStation.processArguments(arguments, cli);
		} else if (commandName.equals("display")) {
			Display display = new Display();
			display.processArguments(arguments, cli);
		} else if (commandName.equals("sortStation")) {
			SortStation sortingStation = new SortStation();
			sortingStation.processArguments(arguments, cli);
		} else if (commandName.equals("rentBike")) {
			RentBike rentBike = new RentBike();
			rentBike.processArguments(arguments, cli);
		} else if (commandName.equals("returnBike")) {
			ReturnBike returnBike = new ReturnBike();
			returnBike.processArguments(arguments, cli);
		} else if (commandName.equals("runtest")) {
			Runtest runtest = new Runtest();
			runtest.processArguments(arguments, cli);
		} else {
			System.out.println("Command wasn't recognized please try again.");
		}
		
	}
}
