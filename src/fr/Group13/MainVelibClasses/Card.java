package fr.Group13.MainVelibClasses;

import fr.Group13.VelibSNGenerators.CardSerialNumberGenerator;
/**
 * Class that represents Registration Cards
 * @version 1.0
 * @see Card
 *
 */
public abstract class Card {
	private int id;
	private int timeCredit;
	private CardSerialNumberGenerator generator = CardSerialNumberGenerator.getInstance();
	
	/**
	 * Constructor of the Card class
	 * Generates id with Singleton pattern
	 * 
	 * @see CardSerialNumberGenerator
	 */
	public Card() {
		this.id=generator.getNextSerialNumber();
		this.timeCredit=0;
	}
	
	//getters & setters
	/**
	 * getTimeCredit
	 * @return timeCredit
	 */
	public int getTimeCredit() {
		return timeCredit;
	}
	
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Adds amount to the card's time credit
	 * @param amount
	 */
	public void addTimeCredit(int amount) {
		this.timeCredit=this.timeCredit+amount;
	}
	
	/**
	 * Subtracts amount to the card's time credit
	 * @param amount
	 */
	public int substractTimeCredit(int amount) {
		try {
			if (amount <= this.timeCredit) {
				this.timeCredit=this.timeCredit-amount;
				return this.timeCredit;
			} else {
				throw new Exception("Not enough time credit left");
			}
		} catch (Exception e) {
			int amountleft = this.timeCredit;
			System.err.println("Exception occured : " + e.getMessage() + " \n All time credit will be consumed, i.e : " + amountleft);
			this.timeCredit = 0;
			return(amountleft);
		}		
	}
}
