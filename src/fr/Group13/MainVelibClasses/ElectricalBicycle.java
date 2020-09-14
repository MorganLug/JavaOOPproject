package fr.Group13.MainVelibClasses;
/**
 * Class representing Electrical Bicycles. Extends the abstract class Bicycle
 * 
 * @version 1.0
 * @see Bicycle
 *
 */

public class ElectricalBicycle extends Bicycle {

	/**
	 * Constructor of the ElectricalBicycle
	 * 
	 * @see Bicycle#Bicycle(Slot parkingSlot)
	 * @param parkingSlot
	 */
	public ElectricalBicycle(Slot parkingSlot) {
		super(parkingSlot);
	}
	
	public ElectricalBicycle() {
		super();
	}

	/**
	 * Coordinates in km
	 * @param dX
	 * @param dY
	 * @param aX
	 * @param aY
	 * @return the travel time
	 */
	@Override
	public int getTravelTime(double dX, double dY, double aX, double aY) {
		double electricalBicycleSpeed = 10.; //in meters per second
		double distance = Math.sqrt((dX-aX)*(dX-aX) + (dY-aY)*(dY-aY)); //in kilometers
		return((int)(1000*distance/electricalBicycleSpeed)); //in seconds
		
	}

}