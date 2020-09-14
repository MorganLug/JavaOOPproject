package fr.Group13.DesignPatternClasses;

import java.util.ArrayList;
import java.util.Collections;

import fr.Group13.MainVelibClasses.*;


/**
 * Class implementing the Observer interface of Observer design pattern to compute statistics on the MyVelib System.
 * 
 * @see fr.Group13.MainVelibClasses.MyVelib
 *
 */
public class StatisticObserver implements Observer {
	
	private MyVelib system;
	private ArrayList<StatisticVector> mostUsedStation = new ArrayList<StatisticVector>();
	private ArrayList<StatisticVector> leastOccupiedStation = new ArrayList<StatisticVector>();
	
	/**
	 * Constructor of the StatisticObserverClass
	 * @param system
	 */
	public StatisticObserver(MyVelib system) {
		this.system = system;
		this.update();
	}

	//methods
	/**
	 *	Updates the sorting lists of stations of this observer
	 * @see #stationSorting
	 */
	@Override
	public void update() {
		this.stationSorting();
	}
	
	/**
	 * Prints a report of current status of user
	 * @param idUser
	 */
	public void userStatus(int idUser) {
		User user = null;
		for (User userSearching : this.system.getUsers()) {
			if (userSearching.getId()==idUser) {user = userSearching;}
		}
		ArrayList<Rent> rents = this.system.getRents();
		int timeCreditEarned =0;
		int timeOnBike = 0;
		int nbRides = 0;
		
		for (Rent rent : rents) {
			if (rent.getUser()==user) {
				nbRides++;
				timeOnBike+=rent.getTimeArrival()-rent.getTimeDeparture();
				if (rent.getStationArrival() instanceof PlusStation && user.getHasRegistationCard()) {
					timeCreditEarned += 300;
				}
			}
		}
		
		System.out.println("User of id : " + user.getId());
		System.out.println("Last 4 digits of Credit card : " + user.getCreditCard().charAt(8)+user.getCreditCard().charAt(9)+user.getCreditCard().charAt(10)+user.getCreditCard().charAt(11));
		if (user.getHasRegistationCard()) {
			System.out.println("Holder of Registration Card of id " +user.getRegistrationCard().getId() +" with current time credit in seconds : " +user.getRegistrationCard().getTimeCredit());
			System.out.println("Earned " + timeCreditEarned + "Seconds with its card");
		}
		else {System.out.println("Doesn't have a Registration card");}
		System.out.println("Was charged for a total of " + user.getTotalMoneySpent());
		System.out.println("Did " + nbRides +" Rides");
		System.out.println("Spent " +timeOnBike+ " seconds on bike");
		if (user.getHasABike()) {System.out.println("Currently riding bike of id " + user.getBikeRented().getId());}
		else {System.out.println("Currently not renting a bike");}
	}
	
	/**
	 * Prints a report of current status of station
	 * @param idStation
	 */
	public void stationStatus(int idStation, int timeBegin, int timeEnd) {
		Station station=null;
		for (Station stationSearch : this.system.getStations()) {
			if (stationSearch.getId()==idStation) {station=stationSearch;}			
		}
		
		int rentOp = 0;
		int returnOp = -1;
		for (Rent rent : this.system.getRents()) {
			if (rent.getStationArrival()==station) {returnOp++;}
			else if (rent.getStationDeparture()==station) {rentOp++;}	
		}
		
		System.out.println("Station of id "+idStation);
		if (station.getIsOnline()) {System.out.println("Station is online");}
		else {System.out.println("Station is offline");}
		System.out.println("Had a total of "+rentOp+" rent operations");
		System.out.println("Had a total of "+returnOp+" return operations");
		System.out.println("And over period of time between "+timeBegin+ " and "+timeEnd+", had a rate of occupation of "+this.rateOfOccupation(station, timeBegin, timeEnd));
		
		System.out.println("At the moment it has :");
		int freeSlots=0;
		int occupiedSlots=0;
		int oooSlots=0;
		
		for (Slot slot : station.getParkingSlots()) {
			if (slot.getStatus()==SlotStatus.Free) {freeSlots++;}
			else if (slot.getStatus()==SlotStatus.OCC) {occupiedSlots++;}
			else if (slot.getStatus()==SlotStatus.OOO) {oooSlots++;}		
		}
		
		System.out.println(freeSlots + " free Slots");
		System.out.println(occupiedSlots + " occupied Slots");
		System.out.println(oooSlots + " Out of Order Slots");
	}
	
	/**
	 * Prints a report of current status of system
	 * 
	 */
	public void systemStatus() {
		System.out.println("Listing of stations of the system : ");
		for (Station station : this.system.getStations()) {
			if (station.getIsOnline()) {System.out.print("Station of id "+ station.getId() +" is online");}
			else {System.out.print("Station of id "+ station.getId() +" is offline");}
			int nbBikes = 0;
			int nbSlots = 0;
			for (Slot slot : station.getParkingSlots()) {
				if (slot.getStatus()==SlotStatus.OCC) {nbBikes++;}
				nbSlots++;
			}
			System.out.println(" and has " +nbBikes + " bikes and "+nbSlots+" total slots.");
			}
			
		System.out.println("\n Listing users of the system :");
		for (User user : this.system.getUsers()) {
			this.userStatus(user.getId());
			System.out.println("\n");
		}
		
	}
		
	
	
	/**
	 * Computes the rate of occupation of station between timeBegin and timeEnd
	 * @param station
	 * @param timeBegin
	 * @param timeEnd
	 * @return rateOfOccupation
	 */
	public double rateOfOccupation(Station station, int timeBegin, int timeEnd) {
		ArrayList<Rent> arrivalRent = new ArrayList<Rent>();
		ArrayList<Rent> departureRent = new ArrayList<Rent>();
		ArrayList<Rent> treatedArrivalRent = new ArrayList<Rent>();
		ArrayList<Rent> treatedDepartureRent = new ArrayList<Rent>();
		ArrayList<Rent> rents = system.getRents();
		Collections.sort(rents);
		
		for (Rent rent : rents) {
			if (rent.getStationArrival()==station && rent.getTimeArrival() <= timeEnd) {
				arrivalRent.add(rent);
			}
			if (rent.getStationDeparture()==station && rent.getTimeDeparture() >= timeBegin) {
				departureRent.add(rent);
			}
		}
		
		//Computing for bikes that arrived and left during the time window
		double timeOccupied = 0.0;
		for (Rent rentArrival : arrivalRent) {
			Bicycle bike = rentArrival.getBike();
			int timeArrival = rentArrival.getTimeArrival();
			for (Rent rentDeparture : departureRent) {
				if (rentDeparture.getBike()==bike && rentDeparture.getTimeDeparture()>timeArrival) {
					timeOccupied+=rentDeparture.getTimeDeparture()-timeArrival;
					treatedDepartureRent.add(rentDeparture);
					treatedArrivalRent.add(rentArrival);
				}
			}
		}
		
		//Computing for other bikes
		arrivalRent.removeAll(treatedArrivalRent);
		departureRent.removeAll(treatedDepartureRent);
		
		for (Rent rent : arrivalRent) {
			timeOccupied+=timeEnd - rent.getTimeArrival();
		}
		for (Rent rent : departureRent) {
			timeOccupied+=rent.getTimeDeparture()-timeBegin;
		}
		
		return timeOccupied/(double)(station.getParkingSlots().size()*(timeEnd-timeBegin));
	}
	
	/**
	 * Computes the number of rent and return operation for a given station
	 * @param station
	 * @return numberOfOperations
	 */
	public int operationsOnStation(Station station) {
		ArrayList<Rent> rents = system.getRents();
		int rentAndDrop = 0;
		for (Rent rent : rents) {
			if (rent.getUser()!=null) {
				if (rent.getStationArrival()==station) {rentAndDrop++;}
				if (rent.getStationDeparture()==station) {rentAndDrop++;}
			}
		}
		return rentAndDrop;
	}
	
	/**
	 * Does all the station sorting, timeEnd is the latest time when a rent has ended
	 * @see #mostUsedStationSorting
	 * @see #leastOccupiedStationSorting
	 */
	public void stationSorting() {
		this.mostUsedStationSorting();
		int timeEnd = 0;
		for (Rent rent : this.system.getRents()) {
			if (rent.getTimeArrival()>timeEnd) {timeEnd=rent.getTimeArrival();}
		}
		this.leastOccupiedStationSorting(timeEnd);
	}
	
	public void mostUsedStationSorting() {
		ArrayList<Station> stations = this.system.getStations();
		ArrayList<StatisticVector> stats = new ArrayList<StatisticVector>();
		for (Station station : stations) {
			int rentAndDrop=this.operationsOnStation(station);
			stats.add(new StatisticVector(station, rentAndDrop));
		}
		Collections.sort(stats, Collections.reverseOrder());
		this.mostUsedStation=stats;		
	}
	
	/**
	 * Sorts station in ascending order according to rate of Occupation
	 * @param timeEnd
	 * 
	 * @see #rateOfOccupation
	 */
	public void leastOccupiedStationSorting(int timeEnd) {
		ArrayList<Station> stations = this.system.getStations();
		ArrayList<Rent> rents = system.getRents();
		ArrayList<StatisticVector> stats = new ArrayList<StatisticVector>();
		Collections.sort(rents);
		
		for (Station station : stations) {
			double rentAndDrop=this.rateOfOccupation(station, 0, timeEnd);
			stats.add(new StatisticVector(station, rentAndDrop));
		}
		Collections.sort(stats);
		this.leastOccupiedStation=stats;	
			
	}

	/**
	 * Displays a report on a sorting list called ranking
	 * @param ranking
	 */
	public void rankingStatus(ArrayList<StatisticVector> ranking) {
		System.out.println("Display sorting :");
		for (StatisticVector statisticVector : ranking) {
			if (statisticVector.getObject() instanceof Station) {
				Station station = (Station)statisticVector.getObject();
				System.out.println("Station " + station.getId() + " has a value of " + statisticVector.getStatistic());
			}
		}
	}

	//setters
	/**
	 * 
	 * @param system
	 */
	public void setSystem(MyVelib system) {
		this.system = system;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<StatisticVector> getMostUsedStation() {
		return mostUsedStation;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<StatisticVector> getLeastOccupiedStation() {
		return leastOccupiedStation;
	}
	
	
	
	
	
	

}
