package fr.Group13.MainVelibClasses;

import fr.Group13.DesignPatternClasses.ConcreteVisitor;
import fr.Group13.DesignPatternClasses.Visitor;

/**
 * Class used for mechanical bike rents
 * 
 * @version 1.0
 * @see Rent
 */
public class RentMBicycle extends Rent {
	
	public final static double pricePerHourUser = 1;
	public final static double pricePerHourVlibreBeforeOneHour = 0;
	public final static double pricePerHourVlibreAfterOneHour = 1;
	public final static double pricePerHourVmax = 1;
	
	/**
	 * Constructor of the class. Uses super constructor
	 * Also computes price and charges the client
	 * 
	 * @param bike
	 * @param user
	 * @param timeDeparture
	 * @param stationDeparture
	 * @param timeArrival
	 * @param stationArrival
	 */
	public RentMBicycle(Bicycle bike, User user, int timeDeparture, Station stationDeparture, int timeArrival,
			Station stationArrival) {
		super(bike, user, timeDeparture, stationDeparture, timeArrival, stationArrival);
		if (user !=null) {
			super.setPrice(this.accept(new ConcreteVisitor()));
		}
	}
	
	//methods
	/**
	 * Method of the visitable interface
	 * 
	 * @see fr.Group13.DesignPatternClasses.Visitor
	 * @see fr.Group13.DesignPatternClasses.Visitable
	 */
	@Override
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
