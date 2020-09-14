package fr.Group13.DesignPatternClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.Group13.MainVelibClasses.*;
/**
 * Factory to create a MyVelib instance and add objects to it
 * 
 * @see fr.Group13.MainVelibClasses.MyVelib
 *
 */
public class MyVelibFactory {
	
	public MyVelibFactory() {}
	
	
	/**
	 * Function used to create the MyVelib system
	 * @param name
	 * @param numberStations
	 * @param numberParkingSlots
	 * @param squareSide
	 * @param proportionBike
	 * @return system of instance MyVelib
	 */
	public MyVelib systemCreation(String name, int numberStations, int numberParkingSlots, double squareSide, double proportionBike) {
		ArrayList<Station> stations = new ArrayList<Station>();
		List<Integer> numberSlotPerStation = new ArrayList<Integer>();	
		List<Integer> numberMBikesPerStation = new ArrayList<Integer>();
		List<Integer> numberEBikesPerStation = new ArrayList<Integer>();
		ArrayList<Rent> initialRents = new ArrayList<Rent>();
		Random rand = new Random();
		
		//Assigning slots :
		for (int i = 0; i < numberStations; i++) {
			numberSlotPerStation.add(1);
			numberMBikesPerStation.add(0);
			numberEBikesPerStation.add(0);
		}
		int numberParkingSlotsRemaining = numberParkingSlots - numberStations;
		while (numberParkingSlotsRemaining > 0) {
			int stationIndex = rand.nextInt(numberStations);
			int value = numberSlotPerStation.get(stationIndex) + 1;
			numberSlotPerStation.set(stationIndex, value);
			numberParkingSlotsRemaining--;
		}
		
		//assigning bikes :	
		int numberBikes = (int)(proportionBike*numberParkingSlots);
		int numberEBikes = (int)(0.3*numberBikes);
		int numberMBikes = numberBikes-numberEBikes;
		while (numberMBikes > 0) {
			int stationIndex = rand.nextInt(numberStations);
			int value = numberMBikesPerStation.get(stationIndex) + 1;
			if (value <= numberSlotPerStation.get(stationIndex)) {
				numberMBikesPerStation.set(stationIndex, value);
				numberMBikes--;
			}
		}
		while (numberEBikes > 0) {
			int stationIndex = rand.nextInt(numberStations);
			int value = numberEBikesPerStation.get(stationIndex) + 1;
			if (value + numberMBikesPerStation.get(stationIndex) <= numberSlotPerStation.get(stationIndex)) {
				numberEBikesPerStation.set(stationIndex, value);
				numberEBikes--;
			}
		}
		
		//Assigning coordinates and creating stations :
		for (int i = 0; i < numberStations; i++) {
			int randStat = rand.nextInt(2);
			if (randStat==0) {stations.add(new StandardStation(numberSlotPerStation.get(i), squareSide * rand.nextDouble(), squareSide * rand.nextDouble()));}
			else {stations.add(new PlusStation(numberSlotPerStation.get(i), squareSide * rand.nextDouble(), squareSide * rand.nextDouble()));}
		}
		
		//Assigning bikes :
		for (int i = 0; i < numberStations; i++) {
			Station station = stations.get(i);
			int numberMbikes = numberMBikesPerStation.get(i);
			int numberEbikes = numberEBikesPerStation.get(i);
			for (int mindex = 0; mindex < numberMbikes; mindex++) {
				Bicycle mbike = new MechanicalBicycle();
				station.parkBike(mbike);
				initialRents.add(Rent.createRent(mbike, null, 0, station, 0, station));
			}
			for (int eindex = 0; eindex < numberEbikes; eindex++) {
				Bicycle ebike = new ElectricalBicycle();
				station.parkBike(ebike);
				initialRents.add(Rent.createRent(ebike, null, 0, station, 0, station));
			}
		}
		return new MyVelib(name, stations, initialRents);
	}
	
	
	/**
	 * Function used to mass add users to a system
	 * @param system
	 * @param number
	 * @param proportionCard
	 */
	public void addUsers(MyVelib system, int number, double proportionCard) {
		int numberCardholders = (int)(proportionCard*number);
		Random rand = new Random();
		for (int i = 0; i < numberCardholders; i++) {
			if (rand.nextInt(2)==0) {system.addUser(new User("name","123412341234",new VlibreCard()));}
			else {system.addUser(new User("name","123412341234",new VmaxCard()));}
		}
		for (int i = 0; i < number - numberCardholders; i++) {
			system.addUser(new User("name","123412341234"));
		}
	}
}
	

