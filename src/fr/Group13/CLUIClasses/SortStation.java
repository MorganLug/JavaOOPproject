package fr.Group13.CLUIClasses;

import fr.Group13.DesignPatternClasses.MyVelibFactory;
import fr.Group13.DesignPatternClasses.Observer;
import fr.Group13.DesignPatternClasses.StatisticObserver;
import fr.Group13.MainVelibClasses.MyVelib;

public class SortStation implements Command {

	@Override
	public void processArguments(String[] arguments, MainCLUI cli) {
		if (arguments.length == 2) {
			boolean cliFound = false;
			MyVelibFactory factory = new MyVelibFactory();
			MyVelib systemWanted = factory.systemCreation("", 1, 1, 1, 0);
			for (MyVelib system : cli.systems) {
				if (system.getName().equals(arguments[0])) {cliFound = true; systemWanted = system;}
			}
			if (arguments[1] != "mostUsed" && arguments[1] != "leastOccupied") {
				System.out.println("Sorting policy (second argument) must be either mostUsed of leastOccupied, please retry");
			} else if (cliFound) {
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
			statObs.stationSorting();
			if (args[0].equals("mostUsed")) {
				statObs.rankingStatus(statObs.getMostUsedStation());
			} else if (args[0].equals("leastOccupied")) {
				statObs.rankingStatus(statObs.getLeastOccupiedStation());
			} else {System.out.println("Sorting policy unknown, can either be mostUsed or leastOccupied, please retry.");}
		}
		
	}

}
