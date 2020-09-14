package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.Group13.DesignPatternClasses.ClassicRidePlanning;
import fr.Group13.MainVelibClasses.*;

class UserTest {

	@Test
	void registrationCardManagementWorksWell() {
		User user = new User("Bob", "123412341234");
		assertTrue(!user.getHasRegistationCard());
		assertTrue(user.getRegistrationCard()==null);
		user.setRegistrationCard(new VlibreCard());
		assertTrue(user.getHasRegistationCard());
		assertTrue(user.getRegistrationCard()!=null);
	}
	
	@Test
	void bikeRentedManagementWorksWell() {
		User user = new User("Bob", "123412341234");
		assertTrue(!user.getHasABike());
		assertTrue(user.getBikeRented()==null);
		user.setBikeRented(new MechanicalBicycle(new Slot(1)));
		assertTrue(user.getHasABike());
		assertTrue(user.getBikeRented()!=null);
	}
	
	@Test
	void parkingAndTakingABikeDoesUpdateVariousValues() {
		User user = new User("Bob", "123412341234");
		Station station = new StandardStation(10, 42.0, 35.7);
		Bicycle bike = new MechanicalBicycle();
		user.setBikeRented(bike);
		bike.setUserRenting(user);
		assertTrue(bike==user.getBikeRented());
		assertTrue(user==bike.getUserRenting());
		user.setBikeRented(bike);
		user.doReturn(station);
		assertTrue(user.getBikeRented()==null);
		assertTrue(bike.getUserRenting()==null);
		user.takeBike(station);
		Bicycle bike2=user.getBikeRented();
		assertTrue(bike2==bike);
	}
	
	@Test
	void tryingToParkABikeWhenNoneIsRentedDoesntWork() {
		User user = new User("Bob", "123412341234");
		Station station = new StandardStation(1, 42.0, 35.7);
		user.doReturn(station);
		assertTrue(station.getParkingSlots().get(0).getStatus()==SlotStatus.Free);
	}
	
	@Test
	void tryingToTakeABikeWhenOneIsAlreadyRentedDoesntWork() {
		User user = new User("Bob", "123412341234");
		Station station = new StandardStation(10, 42.0, 35.7);
		Bicycle bike = new MechanicalBicycle();
		Bicycle bike2 = new MechanicalBicycle();
		station.parkBike(bike);
		station.parkBike(bike2);
		user.takeBike(station);
		user.takeBike(station);
		Bicycle firstBicycleTaken = user.getBikeRented();
		user.takeBike(station);
		assertTrue(user.getBikeRented()==firstBicycleTaken);
	}
	
	@Test
	void rentAndReturnTest() {
		User user = new User("Bob", "123412341234");
		double dX = 0.0;
		double dY = 0.0;
		double aX = 5.0;
		double aY = -50.0;
		Station station1 = new StandardStation(1, 10.0, 10.0);
		Station station2 = new StandardStation(2, -15.0, -15.0); //station 3 has 2 slots : one is occupied, the other is free
		Station station3 = new StandardStation(2, -5.0, -30.0); //station 3 has 2 slots : one is occupied, the other is free
		Bicycle bike1 = new MechanicalBicycle();
		Bicycle bike2 = new MechanicalBicycle();
		Bicycle bike3 = new MechanicalBicycle();
		station1.parkBike(bike1);
		station2.parkBike(bike2);
		station3.parkBike(bike3);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(station1);
		stations.add(station2);
		stations.add(station3);
		assertTrue(user.getBikeRented()==null);
		assertTrue(bike1.getUserRenting()==null);
		assertTrue(bike2.getUserRenting()==null);
		assertTrue(bike3.getUserRenting()==null);
		Station dstation = new ClassicRidePlanning().dStation(dX, dY, stations);
		user.takeBike(dstation);
		assertTrue(user.getBikeRented()==bike1);
		assertTrue(bike1.getUserRenting()==user);
		assertTrue(bike2.getUserRenting()==null);
		assertTrue(bike3.getUserRenting()==null);
		assertTrue(station1.findFreeSlot()!=null); //The only slot of station 1 is now free
		Station astation = new ClassicRidePlanning().aStation(aX, aY, stations);
		user.doReturn(astation);
		assertTrue(user.getBikeRented()==null);
		assertTrue(bike1.getUserRenting()==null);
		assertTrue(bike2.getUserRenting()==null);
		assertTrue(bike3.getUserRenting()==null);
		assertTrue(station3.findFreeSlot()==null); //station 3 has no free slots anymore
	}
	
	//Same test as before but station 3 has only one slot, and it is occupied : the user has to return the bike to station 2, event though station 3 is closer to the arrival point
	@Test
	void rentAndReturnTestStation3Full() {
		User user = new User("Bob", "123412341234");
		double dX = 0.0;
		double dY = 0.0;
		double aX = 5.0;
		double aY = -50.0;
		Station station1 = new StandardStation(1, 10.0, 10.0);
		Station station2 = new StandardStation(2, -15.0, -15.0); //station 3 has 2 slots : one is occupied, the other is free
		Station station3 = new StandardStation(1, -5.0, -30.0); //station 3 has 1 slot : it is occupied
		Bicycle bike1 = new MechanicalBicycle();
		Bicycle bike2 = new MechanicalBicycle();
		Bicycle bike3 = new MechanicalBicycle();
		station1.parkBike(bike1);
		station2.parkBike(bike2);
		station3.parkBike(bike3);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(station1);
		stations.add(station2);
		stations.add(station3);
		assertTrue(user.getBikeRented()==null);
		assertTrue(bike1.getUserRenting()==null);
		assertTrue(bike2.getUserRenting()==null);
		assertTrue(bike3.getUserRenting()==null);
		Station dstation = new ClassicRidePlanning().dStation(dX, dY, stations);
		user.takeBike(dstation);
		assertTrue(user.getBikeRented()==bike1);
		assertTrue(bike1.getUserRenting()==user);
		assertTrue(bike2.getUserRenting()==null);
		assertTrue(bike3.getUserRenting()==null);
		assertTrue(station1.findFreeSlot()!=null); //The only slot of station 1 is now free
		Station astation = new ClassicRidePlanning().aStation(aX, aY, stations);
		user.doReturn(astation);
		assertTrue(user.getBikeRented()==null);
		assertTrue(bike1.getUserRenting()==null);
		assertTrue(bike2.getUserRenting()==null);
		assertTrue(bike3.getUserRenting()==null);
		assertTrue(station2.findFreeSlot()==null); //station 2 has no free slots anymore
	}
	
	@Test
	void travelTimeTest() {
		User user = new User("Bob", "123412341234");
		Bicycle bike1 = new MechanicalBicycle();
		Bicycle bike2 = new ElectricalBicycle();
		double dX = 0.0;
		double dY = 0.0;
		double aX = 10.0;
		double aY = 0.0;
		//distance is 10 kilometers
		assertTrue(user.getTravelTime(dX, dY, aX, aY) == 10000.0); //walking
		user.setBikeRented(bike1);
		assertTrue(user.getTravelTime(dX, dY, aX, aY) == 2000.0); //with mechanical bike
		user.setBikeRented(bike2);
		assertTrue(user.getTravelTime(dX, dY, aX, aY) == 1000.0); //with electrical bike
	}

	@Test
	void userDroppingBikeAtPlusStationGetsTimeCredit() {
		User user = new User("Bob", "123412341234", new VlibreCard());
		Station station1 = new PlusStation(1, 10.0, 10.0);
		Bicycle bike1 = new MechanicalBicycle();
		
		station1.parkBike(bike1);
		user.takeBike(station1);
		user.doReturn(station1);
		assertTrue(user.getRegistrationCard().getTimeCredit()==300);
		
	}
}
