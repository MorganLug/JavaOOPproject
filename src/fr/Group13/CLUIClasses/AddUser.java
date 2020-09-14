package fr.Group13.CLUIClasses;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

public class AddUser implements Command {

	public AddUser() {}
	
	
	@Override
	public void processArguments(String[] arguments, MainCLUI cli) {
		if (arguments.length == 3) {
			boolean cliFound = false;
			for (MyVelib system : cli.systems) {
				if (system.getName().equals(arguments[2])) {cliFound = true;}
			}
			if (!(arguments[1].equals("none") || arguments[1].equals("vlibre") || arguments[1].equals("vmax"))) {
				System.out.println("Card type wasn't recognised, please type in none, vlibre or vmax.");
			} else if (!cliFound){
				System.out.println("Name given doesn't correspond to any system, please retry.");
			} else {
				command(arguments, cli, 1);
			}
		} else {System.out.println("Number of argument is incorrect, please retry");}
		
	}

	@Override
	public void command(String[] args, MainCLUI cli, int configuration) {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib systemWanted = factory.systemCreation("", 1, 1, 1, 0);
		for (MyVelib system : cli.systems) {
			if (system.getName().equals(args[2])) {systemWanted = system; break;}
		}
		switch(args[1]) {
			case "none":
				systemWanted.addUser(new User(args[0],"123412341234"));
				System.out.println("Added " + args[0]+ " without card");
				break;
			case "vlibre":
				systemWanted.addUser(new User(args[0],"123412341234", new VlibreCard()));
				System.out.println("Added " + args[0]+ " with vlibre card");
				break;
			case "vmax":
				systemWanted.addUser(new User(args[0],"123412341234", new VmaxCard()));
				System.out.println("Added " + args[0]+ " with vmax card");
				break;
		}
		
	}

}
