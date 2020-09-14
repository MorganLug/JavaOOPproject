package fr.Group13.DesignPatternClasses;

import fr.Group13.MainVelibClasses.Card;
import fr.Group13.MainVelibClasses.Rent;
import fr.Group13.MainVelibClasses.RentEBicycle;
import fr.Group13.MainVelibClasses.RentMBicycle;
import fr.Group13.MainVelibClasses.*;

/**
 * Visitor side of the Visitor design pattern to calculate prices for Rents
 * 
 * @see fr.group13.MainVelibClasses.Rent
 * @see Visitor
 *
 */
public class ConcreteVisitor implements Visitor {
	
	/**
	 * Constructor of the class
	 */
	public ConcreteVisitor() {}

	/**
	 * Function that takes into account the amount left to pay over an hour, a card, subtracts max(amountToPay, card.timeCredit) to the time credit of the card and returns the amount left to pay (or 0)
	 * @param card
	 * @param amountToPay
	 * @return amountLeft, is 0 if everything is paid
	 * 
	 * @see fr.Group13.MainVelibClasses.Card
	 */
	public int substractTimeCredit(Card card, int amountToPay) {
		int amountLeft = amountToPay - card.getTimeCredit();
		if (amountLeft<0) {
			card.substractTimeCredit(amountToPay);
			return 0;
		} else {
			card.substractTimeCredit(card.getTimeCredit());
			return amountLeft;
		}
	}
	
	
	/**
	 * Visit method for rent of mechanical bicycle.
	 * Computes the price of the rent, based on criteria such as time and registration card of the user if any
	 * 
	 * @param rent of class RentMBicycle
	 * @return price double
	 * 
	 * @see fr.group13.MainVelibClasses.RentMBicycle
	 */
	@Override
	public double visit(RentMBicycle rent) {
		User user = rent.getUser();
		if (!user.getHasRegistationCard()) {
			return (rent.getTimeArrival()-rent.getTimeDeparture())*RentMBicycle.pricePerHourUser/3600;
		} else {
			Card card = user.getRegistrationCard();
			if (card instanceof VlibreCard) {
				int timeOverHour = rent.getTimeArrival()-rent.getTimeDeparture() - 3600;
				if (timeOverHour < 0) {
					return (rent.getTimeArrival()-rent.getTimeDeparture())*RentMBicycle.pricePerHourVlibreBeforeOneHour/3600;
				} else {
					return RentMBicycle.pricePerHourVlibreBeforeOneHour + substractTimeCredit(card, timeOverHour)*RentMBicycle.pricePerHourVlibreAfterOneHour/3600;
				}
			} else if (card instanceof VmaxCard) {
				int timeOverHour = rent.getTimeArrival()-rent.getTimeDeparture() - 3600;
				if (timeOverHour < 0) {
					return 0;
				} else {
					return substractTimeCredit(card, timeOverHour)*RentMBicycle.pricePerHourVmax/3600;
				}
			} else {return 0;}
		}
	}

	/**
	 * Visit method for rent of electrical bicycle.
	 * Computes the price of the rent, based on criteria such as time and registration card of the user if any
	 * 
	 * @param rent of class RentMBicycle
	 * @return price double
	 * 
	 * @see fr.group13.MainVelibClasses.RentEBicycle
	 */
	@Override
	public double visit(RentEBicycle rent) {
		User user = rent.getUser();
		if (!user.getHasRegistationCard()) {
			return (rent.getTimeArrival()-rent.getTimeDeparture())*RentEBicycle.pricePerHourUser/3600;
		} else {
			Card card = user.getRegistrationCard();
			if (card instanceof VlibreCard) {
				int timeOverHour = rent.getTimeArrival()-rent.getTimeDeparture() - 3600;
				if (timeOverHour < 0) {
					return (rent.getTimeArrival()-rent.getTimeDeparture())*RentEBicycle.pricePerHourVlibreBeforeOneHour/3600;
				} else {
					return RentEBicycle.pricePerHourVlibreBeforeOneHour + substractTimeCredit(card, timeOverHour)*RentEBicycle.pricePerHourVlibreAfterOneHour/3600;
				}
			} else if (card instanceof VmaxCard) {
				int timeOverHour = rent.getTimeArrival()-rent.getTimeDeparture() - 3600;
				if (timeOverHour < 0) {
					return 0;
				} else {
					return substractTimeCredit(card, timeOverHour)*RentEBicycle.pricePerHourVmax/3600;
				}
			} else {return 0;}
		}
	}
}
