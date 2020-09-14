package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.Group13.DesignPatternClasses.ClassicRidePlanning;
import fr.Group13.MainVelibClasses.Bicycle;
import fr.Group13.MainVelibClasses.MechanicalBicycle;
import fr.Group13.MainVelibClasses.StandardStation;
import fr.Group13.MainVelibClasses.Station;

class RidePlanningTest {
	
	/**
	 * Verifies that the closest station is picked
	 */
	@Test
	void departureRidePlanning() {
		double dX = 0.0;
		double dY = 0.0;
		Station station1 = new StandardStation(10, 10.0, 10.0);
		Station station2 = new StandardStation(10, -15.0, 15.0);
		Station station3 = new StandardStation(10, -5.0, -30.0);
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
		Station station = new ClassicRidePlanning().dStation(dX, dY, stations);
		assertTrue(station == station1);
		
	}
	
	/**
	 * Verifies null is returned when no station has bikes
	 */
	@Test
	void departureRidePlanningNoBike() {
		double dX = 0.0;
		double dY = 0.0;
		Station station1 = new StandardStation(10, 10.0, 10.0);
		Station station2 = new StandardStation(10, -15.0, 15.0);
		Station station3 = new StandardStation(10, -5.0, -30.0);
		//there is no bike at all
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(station1);
		stations.add(station2);
		stations.add(station3);
		Station station = new ClassicRidePlanning().dStation(dX, dY, stations);
		assertTrue(station == null);
		
	}
	
	/**
	 * Verifies that the closest station is picked
	 */
	@Test
	void arrivalRidePlanning() {
		double aX = 5.0;
		double aY = -50.0;
		Station station1 = new StandardStation(10, 10.0, 10.0);
		Station station2 = new StandardStation(10, -15.0, 15.0);
		Station station3 = new StandardStation(10, -5.0, -30.0);
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
		Station station = new ClassicRidePlanning().aStation(aX, aY, stations);
		assertTrue(station == station3);
		
	}
	
	/**
	 * Verifies null is returned when no station has bikes
	 */
	@Test
	void arrivalRidePlanningNoBike() {
		double aX = 5.0;
		double aY = -50.0;
		Station station1 = new StandardStation(1, 10.0, 10.0);
		Station station2 = new StandardStation(1, -15.0, 15.0);
		Station station3 = new StandardStation(1, -5.0, -30.0);
		Bicycle bike1 = new MechanicalBicycle();
		Bicycle bike2 = new MechanicalBicycle();
		Bicycle bike3 = new MechanicalBicycle();
		station1.parkBike(bike1);
		station2.parkBike(bike2);
		station3.parkBike(bike3);
		//all slots are taken
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(station1);
		stations.add(station2);
		stations.add(station3);
		Station station = new ClassicRidePlanning().aStation(aX, aY, stations);
		assertTrue(station == null);
		
	}
	
	
}
