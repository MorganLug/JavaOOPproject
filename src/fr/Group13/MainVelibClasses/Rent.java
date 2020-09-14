package fr.Group13.MainVelibClasses;

import fr.Group13.DesignPatternClasses.Visitable;
import fr.Group13.DesignPatternClasses.ConcreteVisitor;
import fr.Group13.DesignPatternClasses.Visitor;

/**
 * Class used to create rents, and charge them
 * 
 * @version 1.0
 * @see fr.Group13.DesignPatternClasses.Visitable
 */
public abstract class Rent implements Visitable, Comparable<Rent>{
	private Bicycle bike;
	private User user;
	private int timeDeparture;
	private Station stationDeparture;
	private int timeArrival;
	private Station stationArrival;
	private Boolean charged=false;
	private double price=0;

	/**
	 * Constructor of the Rent class
	 * 
	 * @param bike is the bicycle rented
	 * @param user is the user that did the rent
	 * @param timeDeparture is the time when the bike was taken
	 * @param stationDeparture is the station where the bike was taken
	 * @param timeArrival is the time when bike was dropped
	 * @param stationArrival is the station where bike was dropped
	 */
	public Rent(Bicycle bike, User user, int timeDeparture, Station stationDeparture, int timeArrival, Station stationArrival) {
		this.bike = bike;
		this.user = user;
		this.timeDeparture = timeDeparture;
		this.stationDeparture = stationDeparture;
		this.timeArrival = timeArrival;
		this.stationArrival = stationArrival;
	}
	
	//methods
	/**
	 * Static method using Factory pattern to create Rent instance with the correct subclass
	 * 
	 * @param bike
	 * @param user
	 * @param timeDeparture
	 * @param stationDeparture
	 * @param timeArrival
	 * @param stationArrival
	 * 
	 * @return rent with the correct subclass
	 * 
	 * @see RentEBicycle
	 * @see RentMBicycle
	 */
	public static Rent createRent(Bicycle bike, User user, int timeDeparture, Station stationDeparture, int timeArrival, Station stationArrival) {
		if (bike instanceof MechanicalBicycle) {
			return new RentMBicycle(bike, user, timeDeparture, stationDeparture, timeArrival, stationArrival);
		} else if (bike instanceof ElectricalBicycle) {
			return new RentEBicycle(bike, user, timeDeparture, stationDeparture, timeArrival, stationArrival);
		}
		
		return null;
	}
	
	/**
	 * Charges user for the rent, then set charged to true so the rent won't be charged again
	 */
	public void chargeUser() {
		if (!this.charged) {
			this.user.addMoneySpent(this.price);
			this.charged=true;
		}
	}
	
	/**
	 * Defines the order on Rents. Defined so when using sort(), most recent rents are at the beginning
	 */
	@Override
	public int compareTo(Rent o) {
		if (this.timeDeparture < o.getTimeDeparture()) {return 1;}
		else if (this.timeDeparture == o.getTimeDeparture()) {return 0;}
		else {return -1;}
	}

	//Getters
	/**
	 * 
	 * @return bike
	 */
	public Bicycle getBike() {
		return bike;
	}

	/**
	 * 
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * 
	 * @return timeDeparture
	 */
	public int getTimeDeparture() {
		return timeDeparture;
	}

	/**
	 * 
	 * @return stationDeparture
	 */
	public Station getStationDeparture() {
		return stationDeparture;
	}

	/**
	 * 
	 * @return timeArrival
	 */
	public int getTimeArrival() {
		return timeArrival;
	}

	/**
	 * 
	 * @return stationArrival
	 */
	public Station getStationArrival() {
		return stationArrival;
	}

	/**
	 * 
	 * @return charged
	 */
	public Boolean getCharged() {
		return charged;
	}

	/**
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * sets the price of the rent, then charges the user.
	 * @param price
	 */
	public void setPrice(double price) {
		if (!this.charged) {
			this.price=price;
			this.chargeUser();
		}
	}
}
