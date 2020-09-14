package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

import org.junit.jupiter.api.Test;

class MyVelibTest {

	@Test
	void systemCreationWorksWell() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 5, 10, 10, 0.7);
		
		assertTrue(system.getStations().size()==5);
		ArrayList<Station> stations = new ArrayList<Station>();
		int NumberSlots = 0;
		int NumberEbike = 0;
		int NumberMbike = 0;
		int ExpectedNumberEbike = (int)(0.3*(int)(0.7*10));
		int ExpectedNumberMbike = (int)(0.7*10) - ExpectedNumberEbike;
		for (Station station : system.getStations()) {
			ArrayList<Slot> slots = station.getParkingSlots();
			NumberSlots += slots.size();
			for (Slot slot : slots) {
				if (slot.getStatus()==SlotStatus.OCC) {
					if (slot.getBike() instanceof ElectricalBicycle) {NumberEbike++;}
					else if (slot.getBike() instanceof MechanicalBicycle) {NumberMbike++;}
				}
			}
			
		}
		
		
		assertTrue(NumberSlots==10);
		assertTrue(NumberEbike==ExpectedNumberEbike);
		assertTrue(NumberMbike==ExpectedNumberMbike);		
	}
	
	@Test
	void addingUserWorksWell() {
		MyVelibFactory factory = new MyVelibFactory();
		MyVelib system = factory.systemCreation("default", 5, 10, 10, 0.7);
		User user = new User("Bob", "123412341234");
		system.addUser(user);
		assertTrue(system.getUsers().get(0) == user);
	}

}
