package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

import org.junit.jupiter.api.Test;

class StatisticObserverTest {
	

	@Test
	void rateOfOccupationWorksWellForAStationWhereNoRentWasDone() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 1, 10, 10, 0.7);
		StatisticObserver observer = (StatisticObserver)system.getObserver(0);
		
		assertTrue(observer.rateOfOccupation(system.getStations().get(0), 0, 1000)== 1000.0*7/(1000.0*10.0));
	}
	
	@Test
	void rateOfOccupationWorksWellForAStationWhereOneRentWasDone() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 1, 10, 10, 0.7);
		StatisticObserver observer = (StatisticObserver)system.getObserver(0);
		factory.addUsers(system, 1, 0);
		
		User user = system.getUsers().get(0);
		Bicycle bike = system.getStations().get(0).findFreeBike();
		
		system.addRent(Rent.createRent(bike, user, 700, system.getStations().get(0), 1500, system.getStations().get(0)));
		
		assertTrue(observer.rateOfOccupation(system.getStations().get(0), 0, 1000)== (1000.0*6+700)/(1000.0*10.0));
	}
	
	@Test
	void rateOfOccupationWorksWellForAStationWhereOneRentWasDoneAndReturned() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 1, 10, 10, 0.7);
		StatisticObserver observer = (StatisticObserver)system.getObserver(0);
		factory.addUsers(system, 1, 0);
		
		User user = system.getUsers().get(0);
		Bicycle bike = system.getStations().get(0).findFreeBike();
		
		system.addRent(Rent.createRent(bike, user, 700, system.getStations().get(0), 900, system.getStations().get(0)));
		
		assertTrue(observer.rateOfOccupation(system.getStations().get(0), 0, 1000)== (1000.0*6+800)/(1000.0*10.0));
	}

	
	@Test
	void rateOfOccupationWorksWellForAStationGenerally() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 2, 15, 10, 0.7);
		StatisticObserver observer = (StatisticObserver)system.getObserver(0);
		factory.addUsers(system, 3, 0);
		
		User user1 = system.getUsers().get(0);
		User user2 = system.getUsers().get(1);
		User user3 = system.getUsers().get(2);
		Bicycle bike1 = system.getStations().get(0).findFreeBike();
		Bicycle bike2 = system.getStations().get(0).findFreeBike();
		Bicycle bike3 = system.getStations().get(1).findFreeBike();
		Station station1 = system.getStations().get(0);
		Station station2 = system.getStations().get(1);
		
		int nbBikesInit = 0;
		for (Slot slot : station1.getParkingSlots()) {
			if (slot.getStatus()==SlotStatus.OCC) {nbBikesInit++;}
		}
		
		system.addRent(Rent.createRent(bike1, user1, 700, station1, 900, station2));
		system.addRent(Rent.createRent(bike2, user1, 800, station1, 1000, station1));
		system.addRent(Rent.createRent(bike3, user3, 800, station2, 1000, station1));

		assertTrue(observer.rateOfOccupation(station1, 600, 1100)== (100+300+100+(nbBikesInit-3)*500)/(1000.0*station1.getParkingSlots().size()));
	}
	
	@Test
	void operationsOnStationInitialisesToZero() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 1, 10, 10, 0.7);
		StatisticObserver observer = (StatisticObserver)system.getObserver(0);
		assertTrue(observer.operationsOnStation(system.getStations().get(0)) == 0);
	}
	
	@Test
	void operationsOnStationWorksWellForAStationWhereOneRentWasDone() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 1, 10, 10, 0.7);
		StatisticObserver observer = (StatisticObserver)system.getObserver(0);
		factory.addUsers(system, 1, 0);
		
		User user = system.getUsers().get(0);
		Bicycle bike = system.getStations().get(0).findFreeBike();
		
		system.addRent(Rent.createRent(bike, user, 700, system.getStations().get(0), 1500, system.getStations().get(0)));
		
		assertTrue(observer.operationsOnStation(system.getStations().get(0)) == 2);
	}

	@Test
	void operationsOnStationWorksWellForAStationGenerally() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 2, 15, 10, 0.7);
		StatisticObserver observer = (StatisticObserver)system.getObserver(0);
		factory.addUsers(system, 3, 0);
		
		User user1 = system.getUsers().get(0);
		User user2 = system.getUsers().get(1);
		User user3 = system.getUsers().get(2);
		Bicycle bike1 = system.getStations().get(0).findFreeBike();
		Bicycle bike2 = system.getStations().get(0).findFreeBike();
		Bicycle bike3 = system.getStations().get(1).findFreeBike();
		Station station1 = system.getStations().get(0);
		Station station2 = system.getStations().get(1);
		
		int nbBikesInit = 0;
		for (Slot slot : station1.getParkingSlots()) {
			if (slot.getStatus()==SlotStatus.OCC) {nbBikesInit++;}
		}
		
		system.addRent(Rent.createRent(bike1, user1, 700, station1, 900, station2));
		system.addRent(Rent.createRent(bike2, user1, 800, station1, 1000, station1));
		system.addRent(Rent.createRent(bike3, user3, 800, station2, 1000, station1));

		assertTrue(observer.operationsOnStation(station1) == 4);
		assertTrue(observer.operationsOnStation(station2) == 2);
	}

}
