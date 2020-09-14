package fr.Group13.VelibSNGenerators;

/**
 * StationSerialNumberGenerator is the class implementing the Singleton pattern in order to provide unique ID when creating Station instance.
 * 
 * @version 1.0
 * @see fr.Group13.MainVelibClasses.Station
 * @see fr.Group13.MainVelibClassesTestFiles.StationSerialNumberGeneratorTest
 */

public class StationSerialNumberGenerator {
	private static StationSerialNumberGenerator instance = null;
	private int num;

	
	private StationSerialNumberGenerator() {}

/**
 * getInstance
 * Function that returns the unique instance of the ID generator
 * @return instance
 */
	public static StationSerialNumberGenerator getInstance() {
	if (instance==null) {
		instance = new StationSerialNumberGenerator();
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