package fr.Group13.MainVelibClasses;

import java.util.ArrayList;
import java.util.Iterator;

import fr.Group13.VelibSNGenerators.StationSerialNumberGenerator;

/**
 * Station is the class representing station
 * 
 *
 * 
 * @version 1.0
 * @see fr.Group13.ClassesTestFiles.StationTest
 * @see StationSerialNumberGenerator
 */
public abstract class Station {
	private int id;
	private ArrayList<Slot> parkingSlots = new ArrayList<Slot>();
	private Boolean isOnline = true;
	private double coordinateX;
	private double coordinateY;
	private StationSerialNumberGenerator generator = StationSerialNumberGenerator.getInstance();
	private int nextIdSlot=1;
	
	/**
	 * Constructor of the Station class.
	 * 
	 * @param numberSlots number of Slots in the station
	 * @param coordinateX X coordinate of the station
	 * @param coordinateY Y coordinate of the station
	 * 
	 */
	public Station(int numberSlots, double coordinateX, double coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		for (int i=0; i<numberSlots; i++) {
			parkingSlots.add(new Slot(nextIdSlot++));
		}
		this.id = generator.getNextSerialNumber();
	}
	
	//methods
	/**
	 * typeStationAction is an abstract method extended by subclasses,
	 * that describes a particular behaviour stations can have with users.
	 * 
	 * @param user
	 * @see PlusStation#typeStationAction(User user)
	 */
	public abstract void typeStationAction(User user);
	
	/**
	 * Finds a free slot in the station
	 * @return freeSlot
	 */
	public Slot findFreeSlot() {
		Slot freeSlot = null;
		try {
			if (this.isOnline==true) {
				for (Slot slot : parkingSlots) {
					if (slot.getStatus()==SlotStatus.Free) {
						freeSlot = slot;
						break;
					}
				}
			}
			else {
				throw new Exception("The station is offline");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
		}
		return(freeSlot);
	}
	
	/**
	 * Action of returning a bike to the station
	 * 
	 * @param bike
	 */
	public void parkBike(Bicycle bike) {
		try {
			if (this.isOnline==true) {
				for (Slot slot : parkingSlots) {
					if (slot.getStatus()==SlotStatus.Free) {
						slot.parkBike(bike);
						break;
					}
				}
				if (bike.getIsParked()==false) {
					throw new Exception("There is no free slot");
				}
			}
			else {
				throw new Exception("The station is offline");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
		}
	}
	
	/**
	 * Action of taking a bike
	 * 
	 * @return bikeTaken
	 */
	public Bicycle findFreeBike() {
		try {
			if (this.isOnline==true) {
				boolean noBike=true; //station vide
				Bicycle bikeTaken = null;
				for (Slot slot : parkingSlots) {
					if (slot.getStatus()==SlotStatus.OCC) {
						bikeTaken = slot.takeBike();
						noBike=false; //la station n'était pas vide
						break;
					}
				} 
				if (noBike == true) {
					throw new Exception("There is no bike at the station");
				}
				else {
					return(bikeTaken);
				}
			}
			else {
				throw new Exception("The station is offline");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Action of taking a mechanical bike
	 * 
	 * @return bikeTaken
	 */
	public Bicycle findFreeMechanicalBike() {
		try {
			if (this.isOnline==true) {
				boolean noBike=true; //station vide
				Bicycle bikeTaken = null;
				for (Slot slot : parkingSlots) {
					if ((slot.getStatus()==SlotStatus.OCC) & (slot.getBike() instanceof MechanicalBicycle)) {
						bikeTaken = slot.takeBike();
						noBike=false; //la station n'était pas vide
						break;
					}
				} 
				if (noBike == true) {
					throw new Exception("There is no bike at the station");
				}
				else {
					return(bikeTaken);
				}
			}
			else {
				throw new Exception("The station is offline");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Action of taking an electrical bike
	 * 
	 * @return bikeTaken
	 */
	public Bicycle findFreeElectricalBike() {
		try {
			if (this.isOnline==true) {
				boolean noBike=true; //station vide
				Bicycle bikeTaken = null;
				for (Slot slot : parkingSlots) {
					if ((slot.getStatus()==SlotStatus.OCC) & (slot.getBike() instanceof ElectricalBicycle)) {
						bikeTaken = slot.takeBike();
						noBike=false; //la station n'était pas vide
						break;
					}
				} 
				if (noBike == true) {
					throw new Exception("There is no bike at the station");
				}
				else {
					return(bikeTaken);
				}
			}
			else {
				throw new Exception("The station is offline");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
			return null;
		}
	}
	
	
	//getters and setters, adders
	/**
	 * getIsOnline
	 * @return isOnline
	 */
	public Boolean getIsOnline() {
		return isOnline;
	}
	
	/**
	 * setIsOnline
	 * @param isOnline
	 */
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	/**
	 * getId
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * getParkingSlots
	 * @return parkingSlots
	 */
	public ArrayList<Slot> getParkingSlots() {
		return parkingSlots;
	}
	
	/**
	 * adds one parking slot to the station
	 */
	public void addParkingSlot() {
		this.parkingSlots.add(new Slot(nextIdSlot++));
	}

	/**
	 * getCoordinateX
	 * @return coordinateX
	 */
	public double getCoordinateX() {
		return coordinateX;
	}

	/**
	 * getCoordinateY
	 * @return coordinateY
	 */
	public double getCoordinateY() {
		return coordinateY;
	}
	
	
}
