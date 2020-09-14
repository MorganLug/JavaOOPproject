package fr.Group13.VelibSNGenerators;

/**
 * CardSerialNumberGenerator is the class implementing the Singleton pattern in order to provide unique ID when creating Card object.
 * 
 * @version 1.0
 * @see fr.Group13.MainVelibClasses.Card
 * @see fr.Group13.MainVelibClassesTestFiles.CardSerialNumberGeneratorTest
 */

public class CardSerialNumberGenerator {
	private static CardSerialNumberGenerator instance = null;
	private int num;

	
	private CardSerialNumberGenerator() {}

/**
 * getInstance
 * Function that returns the unique instance of the ID generator
 * @return instance
 */
	public static CardSerialNumberGenerator getInstance() {
	if (instance==null) {
		instance = new CardSerialNumberGenerator();
	}
	return instance;
	}
	
/**
 * getNextSerialNumber
 * 
 * Provides the next unique ID
 * @return num
 */
	public int getNextSerialNumber() {
		return num++;
	}
}