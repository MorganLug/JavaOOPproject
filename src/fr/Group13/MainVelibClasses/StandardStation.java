package fr.Group13.MainVelibClasses;
/**
 * Class representing Standard Stations. Extends the abstract class Station
 * 
 * @version 1.0
 * @see Station
 *
 */

public class StandardStation extends Station {

	/**
	 * Constructor of the StandardStation
	 * 
	 * @see Station#Station(int numberSlots, double coordinateX, double coordinateY, String type)
	 * @param numberSlots
	 * @param coordinateX
	 * @param coordinateY
	 */
	public StandardStation(int numberSlots, double coordinateX, double coordinateY) {
		super(numberSlots, coordinateX, coordinateY);
	}

	/**
	 * Nothing special happens when a user comes at normal station
	 */
	@Override
	public void typeStationAction(User user) {}

}
