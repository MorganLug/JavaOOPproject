package fr.Group13.MainVelibClasses;

import java.util.ArrayList;
import java.util.Scanner;


import fr.Group13.VelibSNGenerators.UserSerialNumberGenerator;
import fr.Group13.DesignPatternClasses.AvoidPlusRidePlanning;
import fr.Group13.DesignPatternClasses.ClassicRidePlanning;
import fr.Group13.DesignPatternClasses.PreferPlusRidePlanning;
import fr.Group13.DesignPatternClasses.Visitable;
import fr.Group13.DesignPatternClasses.Visitor;

/**
 * User is the class representing user
 * 
 *
 * 
 * @version 1.0
 * @see fr.Group13.ClassesTestFiles.UserTest
 * @see UserSerialNumberGenerator
 */

public class User {
	
	public String name;
	private int id;
	private UserSerialNumberGenerator idGenerator = UserSerialNumberGenerator.getInstance();
	private String creditCard;
	private Card registrationCard;
	private double totalMoneySpent;
	private Bicycle bikeRented;

	//Constructors
	/**
     * Constructor of the User class
     *  
     *  @param name The name of the user
     *  @param creditCard contains the credit card ID of the user, modified to fit the requirements
     *  @param totalMoneySpent Money spent by the user initialized at 0
     *  @param registrationCard Registration Card owned by the user
     */
	public User(String name, String creditCard, Card registrationCard) {
		this.name = name;
		this.id = idGenerator.getNextSerialNumber();
		this.totalMoneySpent = 0;
		this.registrationCard=registrationCard;
		
		String creditCardFormatted = creditCard.replaceAll("[^1234567890]", "");
		while (creditCardFormatted.length() !=12) {
			Scanner myObj = new Scanner(System.in); 
			System.out.println("String given for creditCard doesn't match a Credit Card ID. Please enter a new value consisting of 12 digits : ");
			creditCardFormatted = myObj.nextLine();
			creditCardFormatted=creditCardFormatted.replaceAll("[^1234567890]", "");
		}
		this.creditCard=creditCardFormatted;
	}
	
	/**
     * Constructor of the User class
     *  
     *  @param name The name of the user
     *  @param creditCard contains the credit card ID of the user, modified to fit the requirements
     *  @param totalMoneySpent Money spent by the user initialized at 0

     */
	public User(String name, String creditCard) {
		this.name = name;
		this.id = idGenerator.getNextSerialNumber();
		this.totalMoneySpent = 0;
		
		String creditCardFormatted = creditCard.replaceAll("[^1234567890]", "");
		while (creditCardFormatted.length() !=12) {
			Scanner myObj = new Scanner(System.in); 
			System.out.println("String given for creditCard doesn't match a Credit Card ID. Please enter a new value consisting of 12 digits : ");
			creditCardFormatted = myObj.nextLine();
			creditCardFormatted=creditCardFormatted.replaceAll("[^1234567890]", "");
		}
		this.creditCard=creditCardFormatted;
	}


	//methods
	/**
	 * Action of taking a bike at the station
	 * @param station
	 */
	public void takeBike(Station station) {
		if (this.bikeRented==null) {
			Bicycle bike = station.findFreeBike();
			try {
				if (bike != null) {
					bike.setUserRenting(this);
					this.setBikeRented(bike);
				}
				else {
					throw new Exception("There is no bike at the station or the station is offline");
				}
			}
			catch (Exception e) {
				System.err.println("Exception happened : " + e.getMessage());
			}
		}
	}
	
	/**
	 * Action of taking a mechanical bike at the station
	 * @param station
	 */
	public void takeMechanicalBike(Station station) {
		if (this.bikeRented==null) {
			Bicycle bike = station.findFreeMechanicalBike();
			try {
				if (bike != null) {
					bike.setUserRenting(this);
					this.setBikeRented(bike);
				}
				else {
					throw new Exception("There is no bike at the station or the station is offline");
				}
			}
			catch (Exception e) {
				System.err.println("Exception happened : " + e.getMessage());
			}
		}
	}
	
	/**
	 * Action of taking an electrical bike at the station
	 * @param station
	 */
	public void takeElectricalBike(Station station) {
		if (this.bikeRented==null) {
			Bicycle bike = station.findFreeElectricalBike();
			try {
				if (bike != null) {
					bike.setUserRenting(this);
					this.setBikeRented(bike);
				}
				else {
					throw new Exception("There is no bike at the station or the station is offline");
				}
			}
			catch (Exception e) {
				System.err.println("Exception happened : " + e.getMessage());
			}
		}
	}

	/**
	 * Action of returning a bike at the closest station from the arrival point
	 * @param aX
	 * @param aY
	 * @param stations
	 */
	public void doReturn(Station station) {
		if (this.bikeRented!= null) {
			if (station != null) {
				station.parkBike(this.bikeRented);
				this.bikeRented.setUserRenting(null);
				this.setBikeRented(null);
			}
		}
		station.typeStationAction(this);
	}
	
	/**
	 * Function that simulates a rent done by a user
	 * 
	 * @param bikeType
	 * @param timeBegin
	 * @param dX
	 * @param dY
	 * @param aX
	 * @param aY
	 * @param system
	 * 
	 * @see #doReturn(Station)
	 * @see #getTravelTime(double, double, double, double)
	 * @see #takeBike(Station)
	 * @see fr.Group13.DesignPatternClasses.RidePlanning
	 */
	public void doRent(String bikeType, int timeBegin, double dX, double dY, double aX, double aY, MyVelib system) {
		int timeEnd;
		Station dstation;
		Station astation;
		//mechanical bike
		if ((bikeType.charAt(0) == 'm') ^ (bikeType.charAt(0) == 'M')) {
			dstation = new ClassicRidePlanning().dStationMechanical(dX, dY, system.getStations());
			astation = new ClassicRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeMechanicalBike(dstation);
		}
		//electrical bike
		else if ((bikeType.charAt(0) == 'e') ^ (bikeType.charAt(0) == 'E')) {
			dstation = new ClassicRidePlanning().dStationElectrical(dX, dY, system.getStations());
			astation = new ClassicRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeElectricalBike(dstation);
		}
		else {
			dstation = new ClassicRidePlanning().dStation(dX, dY, system.getStations());
			astation = new ClassicRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeBike(dstation);
		}
		Bicycle bikeRentedBefore = this.bikeRented;
		timeEnd+=this.getTravelTime(astation.getCoordinateX(), astation.getCoordinateY(), dstation.getCoordinateX(), dstation.getCoordinateY());
		this.doReturn(astation);
		timeEnd += this.getTravelTime(astation.getCoordinateX(), astation.getCoordinateY(), aX, aY);
		system.addRent(Rent.createRent(bikeRentedBefore, this, timeBegin, dstation, timeEnd, astation));
		
	}
	
	/**
	 * Function that simulates a rent done by a user
	 * 
	 * @param bikeType
	 * @param timeBegin
	 * @param dX
	 * @param dY
	 * @param aX
	 * @param aY
	 * @param system
	 * 
	 * @see #doReturn(Station)
	 * @see #getTravelTime(double, double, double, double)
	 * @see #takeBike(Station)
	 * @see fr.Group13.DesignPatternClasses.AvoidPlusRidePlanning
	 */
	public void doRentAvoidPlus(String bikeType, int timeBegin, double dX, double dY, double aX, double aY, MyVelib system) {
		int timeEnd;
		Station dstation;
		Station astation;
		//mechanical bike
		if ((bikeType.charAt(0) == 'm') ^ (bikeType.charAt(0) == 'M')) {
			dstation = new ClassicRidePlanning().dStationMechanical(dX, dY, system.getStations());
			astation = new AvoidPlusRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeMechanicalBike(dstation);
		}
		//electrical bike
		else if ((bikeType.charAt(0) == 'e') ^ (bikeType.charAt(0) == 'E')) {
			dstation = new ClassicRidePlanning().dStationElectrical(dX, dY, system.getStations());
			astation = new AvoidPlusRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeElectricalBike(dstation);
		}
		else {
			dstation = new ClassicRidePlanning().dStation(dX, dY, system.getStations());
			astation = new AvoidPlusRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeBike(dstation);
		}
		Bicycle bikeRentedBefore = this.bikeRented;
		timeEnd+=this.getTravelTime(astation.getCoordinateX(), astation.getCoordinateY(), dstation.getCoordinateX(), dstation.getCoordinateY());
		this.doReturn(astation);
		timeEnd += this.getTravelTime(astation.getCoordinateX(), astation.getCoordinateY(), aX, aY);
		system.addRent(Rent.createRent(bikeRentedBefore, this, timeBegin, dstation, timeEnd, astation));
		
	}
	
	/**
	 * Function that simulates a rent done by a user
	 * 
	 * @param bikeType
	 * @param timeBegin
	 * @param dX
	 * @param dY
	 * @param aX
	 * @param aY
	 * @param system
	 * 
	 * @see #doReturn(Station)
	 * @see #getTravelTime(double, double, double, double)
	 * @see #takeBike(Station)
	 * @see fr.Group13.DesignPatternClasses.AvoidPlusRidePlanning
	 */
	public void doRentPreferPlus(String bikeType, int timeBegin, double dX, double dY, double aX, double aY, MyVelib system) {
		int timeEnd;
		Station dstation;
		Station astation;
		//mechanical bike
		if ((bikeType.charAt(0) == 'm') ^ (bikeType.charAt(0) == 'M')) {
			dstation = new ClassicRidePlanning().dStationMechanical(dX, dY, system.getStations());
			astation = new PreferPlusRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeMechanicalBike(dstation);
		}
		//electrical bike
		else if ((bikeType.charAt(0) == 'e') ^ (bikeType.charAt(0) == 'E')) {
			dstation = new ClassicRidePlanning().dStationElectrical(dX, dY, system.getStations());
			astation = new PreferPlusRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeElectricalBike(dstation);
		}
		else {
			dstation = new ClassicRidePlanning().dStation(dX, dY, system.getStations());
			astation = new PreferPlusRidePlanning().aStation(aX, aY, system.getStations());
			timeEnd = this.getTravelTime(dstation.getCoordinateX(), dstation.getCoordinateY(), dX, dY);
			this.takeBike(dstation);
		}
		Bicycle bikeRentedBefore = this.bikeRented;
		timeEnd+=this.getTravelTime(astation.getCoordinateX(), astation.getCoordinateY(), dstation.getCoordinateX(), dstation.getCoordinateY());
		this.doReturn(astation);
		timeEnd += this.getTravelTime(astation.getCoordinateX(), astation.getCoordinateY(), aX, aY);
		system.addRent(Rent.createRent(bikeRentedBefore, this, timeBegin, dstation, timeEnd, astation));
		
	}
	
	/**
	 * returns the time the user took to go from (dX,dY) to (aX,aY)
	 * @param bike
	 * @param dX
	 * @param dY
	 * @param aX
	 * @param aY
	 * @return the travel time
	 */
	public int getTravelTime(double dX, double dY, double aX, double aY) {
		double walkingSpeed = 1.0; //in meters per second
		double mechanicalSpeed = 5.0; //in meters per second
		double electricalSpeed = 10.0; //in meters per second
		double distance = Math.sqrt((dX-aX)*(dX-aX) + (dY-aY)*(dY-aY)); //in kilometers
		if (this.bikeRented != null) {
			if (bikeRented instanceof MechanicalBicycle) {
				return((int)(1000*distance/mechanicalSpeed)); //in seconds
			}
			else {
				return((int)(1000*distance/electricalSpeed)); //in seconds
			}
		}
		else {
			return((int)(1000*distance/walkingSpeed)); //in seconds
		}
	}
	
	//getters and setters, adders
	/**
	 * Verifies and tells if a user has a RegCard
	 * @return bool True if the user has a card
	 */
	public Boolean getHasRegistationCard() {
		return (this.registrationCard!=null);
	}
	
	/**
	 * getRegistrationCard
	 * @return registrationCard
	 */
	public Card getRegistrationCard() {
		return registrationCard;
	}

	/**
	 * setRegistrationCard
	 * @param registrationCard
	 */
	public void setRegistrationCard(Card registrationCard) {
		this.registrationCard = registrationCard;
	}

	/**
	 * getName
	 * @return name 
	 */
	public String getName() {
		return name;
	}

	/**
	 * getId
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * getCreditCard
	 * @return creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}
	
	/**
	 * getTotalMoneySpent
	 * @return
	 */
	public double getTotalMoneySpent() {
		return totalMoneySpent;
	}
	
	/**
	 * Adds amount to the user's money spent
	 * @param amount
	 */
	public void addMoneySpent(double amount) {
		this.totalMoneySpent+=amount;
	}

	/**
	 * Verifies and tells if a user is renting a bike
	 * @return bool True if the user has a bike
	 */
	public Boolean getHasABike() {
		return (this.bikeRented!=null);
	}
	
	/**
	 * Set the bike the user is renting
	 * @param bikeRented
	 */
	public void setBikeRented(Bicycle bikeRented) {
		this.bikeRented = bikeRented;
	}

	/**
	 *  getBikeRented
	 * @return bikeRented
	 */
	public Bicycle getBikeRented() {
		return bikeRented;
	}

}
