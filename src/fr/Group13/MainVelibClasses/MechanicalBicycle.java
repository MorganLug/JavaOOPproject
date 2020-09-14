package fr.Group13.MainVelibClasses;
/**
 * Class representing Mechanical Bicycles. Extends the abstract class Bicycle
 * 
 * @version 1.0
 * @see Bicycle
 */

public class MechanicalBicycle extends Bicycle {

	/**
	 * Constructor of the MechanicalBicycle
	 * 
	 * @see Bicycle#Bicycle(Slot parkingSlot)
	 * @param parkingSlot
	 */
	public MechanicalBicycle(Slot parkingSlot) {
		super(parkingSlot);
	}

	public MechanicalBicycle() {
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
		double mechanicalBicycleSpeed = 5.; //in meters per second
		double distance = Math.sqrt((dX-aX)*(dX-aX) + (dY-aY)*(dY-aY)); //in kilometers
		return((int)(1000*distance/mechanicalBicycleSpeed)); //in seconds
	}
		
		
}

