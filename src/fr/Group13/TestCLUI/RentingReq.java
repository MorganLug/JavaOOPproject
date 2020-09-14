package fr.Group13.TestCLUI;

import java.util.Calendar;
import fr.Group13.CLUIClasses.*;
import fr.Group13.MainVelibClasses.*;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RentingReq {
	// JUnit test for section 23 / Requirement 1
	void requirement_1_loadScenario(MainCLUI cli) {
		System.out.println("\tSCN_1: Optional loading of a textual scenario << req_1.txt >> bringing");
		System.out.println("\t\tthe system into the required configuration to test the requirement ! ");
		
		Runtest runtest = new Runtest();
		runtest.launchTest("req_1.txt", cli);
	}

	@Test
	void requirement_1_Test() {
		System.out.println("REQUIREMENT 1 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_1: To rent a bicycle a user must get to one station, identify herself(either through");
		System.out.println("\t\ta velib-card or through a credit-card) and pick up one of the available bikes.");
		MainCLUI cli = Runini.launchIni("my_velib.ini");
		
		Bicycle bike = cli.getSystem(0).getStations().get(0).getParkingSlots().get(0).getBike();
		User user = cli.getSystem(0).getUsers().get(0);
		
		
		requirement_1_loadScenario(cli);
		
		assertTrue(user.getHasABike());
		assertTrue(user.getBikeRented()==bike);
	}

	// JUnit test for section X / Requirement 2
	void requirement_2_loadScenario(MainCLUI cli) {
		System.out.println("\tSCN_2: Optional loading of << req_2.txt >>");
		Runtest runtest = new Runtest();
		runtest.launchTest("req_2.txt", cli);
		
	}

	@Test
	void requirement_2_Test() {
		System.out.println("REQUIREMENT 2 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_2: A user can only rent at most one bicycle (i.e. if she has a bicycle");
		System.out.println("\t\tand has not yet returned it, she cannot rent a second one).");
		MainCLUI cli = Runini.launchIni("my_velib.ini");
		
		Bicycle bike = cli.getSystem(0).getStations().get(0).getParkingSlots().get(0).getBike();
		User user = cli.getSystem(0).getUsers().get(0);
		
		requirement_2_loadScenario(cli);
		
		assertTrue(user.getHasABike());
		assertTrue(user.getBikeRented()==bike);
		assertTrue(cli.getSystem(0).getStations().get(1).getParkingSlots().get(0).getStatus()==SlotStatus.OCC);
		assertTrue(cli.getSystem(0).getStations().get(1).getParkingSlots().get(0).getBike()!=null);
	}
	
	// JUnit test for section X / Requirement 3
	void requirement_3_loadScenario(MainCLUI cli) {
		System.out.println("\tSCN_3: Optional loading of << req_3.txt >>");
		Runtest runtest = new Runtest();
		runtest.launchTest("req_3.txt", cli);
	}

	@Test
	void requirement_3_Test() {
		System.out.println("REQUIREMENT 3 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_3: To return a bicycle a user must park it to a free (and on-duty) parking bay of some station.");
		MainCLUI cli = Runini.launchIni("my_velib.ini");
		
		Bicycle bike = cli.getSystem(0).getStations().get(0).getParkingSlots().get(0).getBike();
		User user = cli.getSystem(0).getUsers().get(0);
		
		Station station = cli.getSystem(0).getStations().get(1);
		
		requirement_3_loadScenario(cli);
		
		assertTrue(!user.getHasABike());
		assertTrue(bike==station.getParkingSlots().get(0).getBike());
	}
	
	// JUnit test for section X / Requirement 4
	void requirement_4_loadScenario(MainCLUI cli) {
		System.out.println("\tSCN_4: Optional loading of << req_4.txt >>");
		Runtest runtest = new Runtest();
		runtest.launchTest("req_4.txt", cli);
	}

	@Test
	void requirement_4_Test() {
		System.out.println("REQUIREMENT 4 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_4: When the bike is returned the cost for the ride is computed and user is");
		System.out.println("\t\tautomatically charged (if a charge applies).");
		
		MainCLUI cli = Runini.launchIni("my_velib.ini");
		
		Bicycle bike = cli.getSystem(0).getStations().get(0).getParkingSlots().get(0).getBike();
		User user = cli.getSystem(0).getUsers().get(0);
		
		requirement_4_loadScenario(cli);
		
		if (bike instanceof MechanicalBicycle) {
			assertTrue(user.getTotalMoneySpent()==2500.0/3600.0);
		} else {
			assertTrue(user.getTotalMoneySpent()==2.0*2500.0/3600.0);
		}
		
	}
	

	@AfterAll
	static void tearDownAll() {
		System.out.println("\nTEAR DOWN ALL @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tReset once the system after all tests, if possible");
	}

}