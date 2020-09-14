package fr.Group13.MainVelibClasses;

/**
 * Class representing Plus Stations. Extends the abstract class Station
 * 
 * @version 1.0
 * @see Station
 */
public class PlusStation extends Station {


	/**
	 * Constructor of the PlusStation.
	 * 
	 * @see Station#Station(int numberSlots, double coordinateX, double coordinateY)
	 * @param numberSlots
	 * @param coordinateX
	 * @param coordinateY
	 */
	public PlusStation(int numberSlots, double coordinateX, double coordinateY) {
		super(numberSlots, coordinateX, coordinateY);
	}

	/**
	 * Plus stations add 5 minute credit to registration card of the user if they have any
	 * 
	 * @param user User dropping a bicycle
	 * @see Card#addTimeCredit(int amount)
	 */
	@Override
	public void typeStationAction(User user) {
		if (user.getHasRegistationCard()) {
			Card card = user.getRegistrationCard();
			card.addTimeCredit(5*60);
		}
	}

}
