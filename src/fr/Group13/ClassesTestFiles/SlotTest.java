package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;

import fr.Group13.MainVelibClasses.*;

import org.junit.jupiter.api.Test;

class SlotTest {

	@Test
	void takingABikeDoesUpdateVariousValues() {
		Slot slot = new Slot(1);
		Bicycle bike = new MechanicalBicycle(slot);
		assertTrue(bike.getParkingSlot()==slot);
		assertTrue(bike.getIsParked());
		Bicycle bike2 = slot.takeBike();
		assertTrue(bike==bike2);
		assertTrue(!bike.getIsParked());
		assertTrue(bike.getParkingSlot()==null);
		assertTrue(slot.getStatus()==SlotStatus.Free);
		assertTrue(slot.getBike()==null);
	}

	@Test
	void parkingABikeDoesUpdateVariousValues() {
		Slot slot = new Slot(1);
		Bicycle bike = new MechanicalBicycle(slot);
		bike = slot.takeBike();
		slot.parkBike(bike);
		assertTrue(bike.getIsParked());
		assertTrue(bike.getParkingSlot()==slot);
		assertTrue(slot.getStatus()==SlotStatus.OCC);
		assertTrue(slot.getBike()==bike);
	}
	
	@Test
	void cantParkBikeWhenSlotNotAvailable() {
		Slot slot = new Slot(1);
		Slot slot2 = new Slot(2);
		Bicycle bike = new MechanicalBicycle(slot);
		Bicycle bike2 = new MechanicalBicycle(slot2);
		bike2=slot2.takeBike();
		slot.parkBike(bike2);
		assertTrue(slot.getBike()==bike);
		assertTrue(slot.getStatus()==SlotStatus.OCC);
		assertTrue(!bike2.getIsParked());
		assertTrue(bike2.getParkingSlot()==null);
		assertTrue(bike.getParkingSlot()==slot);
	}
	
	@Test
	void cantTakeBikeWhenNoBikeOnSlot() {
		Slot slot = new Slot(1);
		Bicycle bike = new MechanicalBicycle(slot);
		bike=slot.takeBike();
		Bicycle bike2 = slot.takeBike();
		assertTrue(slot.getBike()==null);
		assertTrue(slot.getStatus()==SlotStatus.Free);
		assertTrue(bike2==null);
	}
	
	@Test
	void cantParkABikeAlreadyParked() {
		Slot slot = new Slot(1);
		Bicycle bike = new MechanicalBicycle(slot);
		Slot slot2 = new Slot(2);
		assertTrue(bike.getIsParked());
		slot.parkBike(bike);
		assertTrue(bike.getIsParked());
		assertTrue(slot.getBike()==bike);
		assertTrue(slot2.getBike()==null);
	}

}
