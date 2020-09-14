package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;
import fr.Group13.MainVelibClasses.*;

import org.junit.jupiter.api.Test;

class RentTest {

	@Test
	void createRentWorksWell() {
		User user = new User("Bob", "123412341234");
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		assertTrue(Rent.createRent(mbike, user, 0, dstation, 1000, astation) instanceof RentMBicycle);
		assertTrue(Rent.createRent(ebike, user, 0, dstation, 1000, astation) instanceof RentEBicycle);
	}
	
	@Test
	void comparisonOfRentWorksWell() {
		User user = new User("Bob", "123412341234");
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		Rent mrent = Rent.createRent(mbike, user, 0, dstation, 1000, astation);
		Rent erent = Rent.createRent(ebike, user, 10, dstation, 1000, astation);
		
		assertTrue(erent.compareTo(mrent)==-1);
	}

}
