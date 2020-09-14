package fr.Group13.CLUIClasses;

import fr.Group13.DesignPatternClasses.MyVelibFactory;
import fr.Group13.MainVelibClasses.*;

public class RentBike implements Command {

	@Override
	public void processArguments(String[] arguments, MainCLUI cli) {
		if (arguments.length == 3) {
			boolean cliFound = false;
			MyVelibFactory factory = new MyVelibFactory();
			MyVelib systemWanted = factory.systemCreation("", 1, 1, 1, 0);
			for (MyVelib system : cli.systems) {
				if (system.getName().equals(arguments[0])) {cliFound = true; systemWanted = system;}
			}
			if (cliFound) {
				boolean userFound=false;
				boolean stationFound=false;
				try {
					int userId = Integer.parseInt(arguments[1]);
					int stationId = Integer.parseInt(arguments[2]);
					for (Station station : systemWanted.getStations()) {
						if (station.getId()==stationId) {stationFound=true;}
						}
					for (User user : systemWanted.getUsers()) {
						if (user.getId()==userId) {userFound=true;}
						}
					Integer.parseInt(arguments[2]);
					if (userFound && stationFound) {command(arguments, cli, 1);}
					else {System.out.println("User or station wasn't found, please retry.");}
				} catch (NumberFormatException e) {System.out.println("command will be ignored \n one parameter wasn't identified as a number, please retry.");}
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
		Station stationWanted=systemWanted.getStations().get(0);
		for (Station station : systemWanted.getStations()) {
			if (station.getId()==Integer.parseInt(args[2])) {
				stationWanted= station;
			}
		}
		User userWanted=systemWanted.getUsers().get(0);
		for (User user : systemWanted.getUsers()) {
			if (user.getId()==Integer.parseInt(args[1])) {
				userWanted= user;
			}
		}
		
		boolean hasABicycle = false;
		
		for (Slot slot : stationWanted.getParkingSlots()) {
			if (slot.getStatus()==SlotStatus.OCC) {hasABicycle=true;}
		}
		if (hasABicycle && stationWanted.getIsOnline()) {
			if (!userWanted.getHasABike()) {
				userWanted.takeBike(stationWanted);
				System.out.println("User succesfully rented a bike");
			} else {System.out.println("User already has a bike, rent was aborted");}
		} else {System.out.println("Couldn't operate rent, please try again");}
	}
	
}
