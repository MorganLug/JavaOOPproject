package fr.Group13.MainVelibClasses;

import java.util.Scanner;

import fr.Group13.VelibSNGenerators.BicycleSerialNumberGenerator;

/**
 * User is the class representing bicycle
 * 
 *
 * 
 * @version 1.0
 * @see fr.Group13.MainVelibClassesTestFiles.UserBicycle
 * @see BicycleSerialNumberGenerator
 */

public abstract class Bicycle {
	
	private int id;
	private BicycleSerialNumberGenerator idGenerator = BicycleSerialNumberGenerator.getInstance();
	private Boolean isParked=false;
	private Slot parkingSlot;
	private User userRenting;

	//Constructors
	/**
	 * Constructor of the Bicycle class
	 */
	public Bicycle() {
		this.id=idGenerator.getNextSerialNumber();
	}
	/**
     * Constructor of the Bicycle class
     *  
     *  @param isParked is initialized at true
     *  @param parkingSlot contains the slot of the bicycle
     *  @param userRenting contains the user renting the bicycle
     */
	public Bicycle(Slot parkingSlot) {
		this.id = idGenerator.getNextSerialNumber();
		try {
			if (parkingSlot.getStatus()==SlotStatus.Free) {
				parkingSlot.parkBike(this);
			} else {throw new Exception("Slot is not available");}
		} catch (Exception e) {
			System.err.println("Exception occured : " + e.getMessage() + " \n Use setParkingSlot to park this bike");
		}
		
		
	}
	
	//methods
	/**
	 * Coordinates in km
	 * @param dX
	 * @param dY
	 * @param aX
	 * @param aY
	 * @return the travel time
	 */
	public abstract int getTravelTime(double dX, double dY, double aX, double aY);
	
	//getters and setters, adders
	/**
	 * getId
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * getIsParked
	 * @return isParked
	 */
	public Boolean getIsParked() {
		return isParked;
	}

	/**
	 * Set whether the bicycle is parked or not
	 * @param isParked
	 */
	public void setIsParked() {
		this.isParked= (this.parkingSlot!=null);		
	}

	/**
	 * getParkingSlot
	 * @return parkingSlot
	 */
	public Slot getParkingSlot() {
		return parkingSlot;
	}

	/**
	 * Set the parkingSlot of the bicycle
	 * @param parkingSlot
	 */
	public void setParkingSlot(Slot parkingSlot) {
		this.parkingSlot = parkingSlot;
		this.setIsParked();	
	}

	/**
	 * getUserRenting
	 * @return userRenting
	 */
	public User getUserRenting() {
		return userRenting;
	}

	/**
	 * Set the user renting the bicycle
	 * @param userRenting
	 */
	public void setUserRenting(User userRenting) {
		this.userRenting = userRenting;
		if (userRenting!=null) {
			this.isParked = false;
		}
	}


	
}
