package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.Group13.VelibSNGenerators.UserSerialNumberGenerator;
/**
 * 
 * Test class for the class UserSerialNumberGenerator. Tests if the number generation works properly (i.e. if two numbers generated consecutively are consecutive numbers) and if we can't
 * have two generators (i.e. if we have two, they are equal)
 * 
 * @see fr.Group13.VelibSNGenerators.UserSerialNumberGenerator
 */
class UserSerialNumberGeneratorTest {

	@Test
	void GetInstanceTwoDifferentInstancesAreTheSame() {
		UserSerialNumberGenerator gen1 = UserSerialNumberGenerator.getInstance();
		UserSerialNumberGenerator gen2 = UserSerialNumberGenerator.getInstance();
		assertTrue(gen1==gen2);
	}

	@Test
	void GetNextSerialNumberReturnsTheNextOne() {
		UserSerialNumberGenerator gen = UserSerialNumberGenerator.getInstance();
		int num1 = gen.getNextSerialNumber();
		assertTrue(gen.getNextSerialNumber()== ++num1);
	}


}
