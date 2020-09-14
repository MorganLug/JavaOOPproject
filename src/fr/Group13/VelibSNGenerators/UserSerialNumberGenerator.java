package fr.Group13.VelibSNGenerators;

/**
 * UserSerialNumberGenerator is the class implementing the Singleton pattern in order to provide unique ID when creating User object.
 * 
 * @version 1.0
 * @see fr.Group13.MainVelibClasses.User
 * @see fr.Group13.ClassesTestFiles.UserSerialNumberGeneratorTest
 */

public class UserSerialNumberGenerator {
	private static UserSerialNumberGenerator instance = null;
	private int num;

	
	private UserSerialNumberGenerator() {}

/**
 * getInstance
 * Function that returns the unique instance of the ID generator
 * @return instance
 */
	public static UserSerialNumberGenerator getInstance() {
	if (instance==null) {
		instance = new UserSerialNumberGenerator();
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