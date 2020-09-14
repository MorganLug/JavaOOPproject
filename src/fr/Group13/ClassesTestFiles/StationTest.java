package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.Group13.MainVelibClasses.Bicycle;
import fr.Group13.MainVelibClasses.MechanicalBicycle;
import fr.Group13.MainVelibClasses.Slot;
import fr.Group13.MainVelibClasses.SlotStatus;
import fr.Group13.MainVelibClasses.StandardStation;
import fr.Group13.MainVelibClasses.Station;

class StationTest {

	@Test
	void parkingAndTakingABikeDoesUpdateVariousValues() {
		Station station = new StandardStation(10, 42.0, 35.7);
		ArrayList<Slot> slots=station.getParkingSlots();
		assertTrue(slots.get(0).getStatus()==SlotStatus.Free); //All the slots are free by default
		assertTrue(slots.get(1).getStatus()==SlotStatus.Free);
		Bicycle bike = new MechanicalBicycle();
		station.parkBike(bike); //The bike will be parked at the slot 1
		assertTrue(bike.getIsParked());
		assertTrue(slots.get(0).getStatus()==SlotStatus.OCC); //The slot 1 becomes occupied
		assertTrue(slots.get(1).getStatus()==SlotStatus.Free);
		Bicycle bike2 = station.findFreeBike(); //The bike parked at the slot 1 will be taken
		assertTrue(!bike2.getIsParked());
		assertTrue(bike2.getParkingSlot()==null);
		assertTrue(bike==bike2); //The bike taken is the bike that just got parked
		assertTrue(slots.get(0).getStatus()==SlotStatus.Free); //The slot 1 is free once more
	}
	
}
