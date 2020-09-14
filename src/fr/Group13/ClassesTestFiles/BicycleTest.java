package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import fr.Group13.MainVelibClasses.*;

class BicycleTest {
	
	@Test
	void bikeRentedManagementWorksWell() {
		Bicycle bike = new MechanicalBicycle(new Slot(1));
		assertTrue(bike.getIsParked());
		assertTrue(bike.getUserRenting()==null);
		bike.setUserRenting(new User("BoB", "123412341234")); // To be changed
		assertTrue(!bike.getIsParked());
		assertTrue(bike.getUserRenting()!=null);
	}
	
	@Test
	void userRentingBeingNullDoesntChangeIsRented() {
		Bicycle bike = new MechanicalBicycle(new Slot(1));
		bike.setUserRenting(null);
		assertTrue(bike.getIsParked());
	}
	
	@Test
	void slotBeingNullDoesntChangeIsRented() {
		Bicycle bike = new MechanicalBicycle();
		bike.setParkingSlot(null);
		assertTrue(!bike.getIsParked());
	}
	
	@Test 
	void assigningASlotWorksWell() {
		Bicycle bike = new MechanicalBicycle();
		Slot slot = new Slot(1);
		bike.setParkingSlot(slot);
		assertTrue(bike.getIsParked());
	}
	
	@Test
	void travelTimeTest() {
		Bicycle bike1 = new MechanicalBicycle();
		Bicycle bike2 = new ElectricalBicycle();
		double dX = 0.0;
		double dY = 0.0;
		double aX = 10.0;
		double aY = 0.0;
		//distance is 10 kilometers
		assertTrue(bike1.getTravelTime(dX, dY, aX, aY) == 2000.0);
		assertTrue(bike2.getTravelTime(dX, dY, aX, aY) == 1000.0);
	}

}
