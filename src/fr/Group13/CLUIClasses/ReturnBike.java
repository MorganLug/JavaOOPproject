package fr.Group13.CLUIClasses;

import fr.Group13.DesignPatternClasses.MyVelibFactory;
import fr.Group13.MainVelibClasses.*;

public class ReturnBike implements Command {

	@Override
	public void processArguments(String[] arguments, MainCLUI cli) {
		if (arguments.length == 6) {
			boolean cliFound = false;
			MyVelibFactory factory = new MyVelibFactory();
			MyVelib systemWanted = factory.systemCreation("", 1, 1, 1, 0);
			for (MyVelib system : cli.systems) {
				if (system.getName().equals(arguments[0])) {cliFound = true; systemWanted = system;}
			}
			if (cliFound) {
				boolean userFound=false;
				boolean station1Found=false;
				boolean station2Found=false;
				try {
					int userId = Integer.parseInt(arguments[1]);
					int stationId1 = Integer.parseInt(arguments[2]);
					int stationId2 = Integer.parseInt(arguments[4]);
					for (Station station : systemWanted.getStations()) {
						if (station.getId()==stationId1) {station1Found=true;}
						if (station.getId()==stationId2) {station2Found=true;}
					}
					for (User user : systemWanted.getUsers()) {
						if (user.getId()==userId) {userFound=true;}
						}
					Integer.parseInt(arguments[3]);
					Integer.parseInt(arguments[5]);
					if (userFound && station1Found && station2Found) {command(arguments, cli, 1);}
					else {System.out.println("User or stations weren't found, please retry.");}
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
		Station stationBegin=systemWanted.getStations().get(0);
		for (Station station : systemWanted.getStations()) {
			if (station.getId()==Integer.parseInt(args[2])) {
				stationWanted= station;
			} else if (station.getId()==Integer.parseInt(args[4])) {
				stationBegin= station;
			}
		}
		User userWanted=systemWanted.getUsers().get(0);
		for (User user : systemWanted.getUsers()) {
			if (user.getId()==Integer.parseInt(args[1])) {
				userWanted= user;
			}
		}
		
		Slot slot = stationWanted.findFreeSlot();
		Bicycle bikeRented;
		if (slot!=null) {
			if (userWanted.getHasABike()) {
				bikeRented = userWanted.getBikeRented();
				userWanted.doReturn(stationWanted);
				System.out.println("User succesfully returned a bike");
				
				//price computing
				int timeBegin = 0;
				for (Rent rent : systemWanted.getRents()) {
					if (rent.getTimeArrival()>timeBegin) {timeBegin=rent.getTimeArrival();}
				}
				Rent rent = Rent.createRent(bikeRented, userWanted, Integer.parseInt(args[5]), stationBegin, Integer.parseInt(args[3]), stationWanted);
				System.out.println("Price was " + rent.getPrice());
				systemWanted.addRent(rent);
			} else {System.out.println("User has no bike, return was aborted");}
		} else {System.out.println("Couldn't operate return, please try again");}
		
		
		
		
	}
	
}
