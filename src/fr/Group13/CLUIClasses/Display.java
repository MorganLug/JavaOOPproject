package fr.Group13.CLUIClasses;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

public class Display implements Command{

	@Override
	public void processArguments(String[] arguments, MainCLUI cli) {
		if (arguments.length == 1) {
			boolean cliFound = false;
			MyVelibFactory factory = new MyVelibFactory();
			MyVelib systemWanted = factory.systemCreation("", 1, 1, 1, 0);
			for (MyVelib system : cli.systems) {
				if (system.getName().equals(arguments[0])) {cliFound = true; systemWanted = system;}
			}
			if (cliFound) {
				command(arguments, cli, 1);
			} else {System.out.println("System wasn't found, please retry");}
		} else {System.out.println("Number of argument is incorrect, please retry");}
		
	}

	@Override
	public void command(String[] args, MainCLUI cli, int configuration) {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib systemWanted = factory.systemCreation("", 1, 1, 1, 0);
		for (MyVelib system : cli.systems) {
			if (system.getName().equals(args[0])) {systemWanted = system; break;}
		}
		Observer observer = systemWanted.getObserver(0);
		if (observer instanceof StatisticObserver) {
			StatisticObserver statObs = (StatisticObserver)observer;
			statObs.systemStatus();
		}
		
	}

}
