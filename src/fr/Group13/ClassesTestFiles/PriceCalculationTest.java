package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;
import fr.Group13.MainVelibClasses.*;

import org.junit.jupiter.api.Test;


/** 
 * Tests to verify the visitor pattern implemented to calculate the price of rents works well
 * 
 * @see fr.Group13.DesignPatternClasses.ConcreteVisitor
 * @see fr.Group13.MainVelibClasses.Rent
 */
class PriceCalculationTest {

	/**
	 * Verifying price calculation for a user without registration card with a mechanical Bicycle
	 */
	@Test
	void priceForStandardUserMBicycleIsCorrect() {
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		User user = new User("bob", "123412341234");
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		int dtime = 100;
		int atime1 = 3600;
		int atime2 = 5000;
		double price1 = 3500.0/3600;
		double price2 = 4900.0/3600;
		
		Rent rent1 = Rent.createRent(mbike, user, dtime, dstation, atime1, astation);
		Rent rent2 = Rent.createRent(mbike, user, dtime, dstation, atime2, astation);
		assertTrue(rent1.getPrice()==price1);
		assertTrue(rent2.getPrice()==price2);
		
	}
	
	/**
	 * Verifying price calculation for a user with Vlibre card with a mechanical Bicycle
	 */
	@Test
	void priceForVlibreCardMBicycleIsCorrect() {
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		User user = new User("bob", "123412341234", new VlibreCard());
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		int dtime = 100;
		int atime1 = 3600;
		int atime2 = 5000;
		double price1 = 0.0;
		double price2 = (4900.0-3600.0)/3600.0;
		
		Rent rent1 = Rent.createRent(mbike, user, dtime, dstation, atime1, astation);
		Rent rent2 = Rent.createRent(mbike, user, dtime, dstation, atime2, astation);
		assertTrue(rent1.getPrice()==price1);
		assertTrue(rent2.getPrice()==price2);
		
	}
	
	/**
	 * Verifying price calculation for a user with Vmax card with a mechanical Bicycle
	 */
	@Test
	void priceForVmaxCardMBicycleIsCorrect() {
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		User user = new User("bob", "123412341234", new VmaxCard());
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		int dtime = 100;
		int atime1 = 3600;
		int atime2 = 5000;
		double price1 = 0.0;
		double price2 = (4900.0-3600.0)/3600.0;
		
		Rent rent1 = Rent.createRent(mbike, user, dtime, dstation, atime1, astation);
		Rent rent2 = Rent.createRent(mbike, user, dtime, dstation, atime2, astation);
		assertTrue(rent1.getPrice()==price1);
		assertTrue(rent2.getPrice()==price2);
		
	}
	
	/**
	 * Verifying price calculation for a user without registration card with an electrical Bicycle
	 */
	@Test
	void priceForStandardUserEBicycleIsCorrect() {
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		User user = new User("bob", "123412341234");
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		int dtime = 100;
		int atime1 = 3600;
		int atime2 = 5000;
		double price1 = 2*3500.0/3600;
		double price2 = 2*4900.0/3600;
		
		Rent rent1 = Rent.createRent(ebike, user, dtime, dstation, atime1, astation);
		Rent rent2 = Rent.createRent(ebike, user, dtime, dstation, atime2, astation);
		assertTrue(rent1.getPrice()==price1);
		assertTrue(rent2.getPrice()==price2);
		
	}
	
	/**
	 * Verifying price calculation for a user with Vlibre card with an electrical Bicycle
	 */
	@Test
	void priceForVlibreCardEBicycleIsCorrect() {
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		User user = new User("bob", "123412341234", new VlibreCard());
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		int dtime = 100;
		int atime1 = 3600;
		int atime2 = 5000;
		double price1 = 3500.0/3600;
		double price2 = 2*(4900.0-3600.0)/3600.0 + 1.0;
		
		Rent rent1 = Rent.createRent(ebike, user, dtime, dstation, atime1, astation);
		Rent rent2 = Rent.createRent(ebike, user, dtime, dstation, atime2, astation);
		assertTrue(rent1.getPrice()==price1);
		assertTrue(rent2.getPrice()==price2);
		
	}
	
	/**
	 * Verifying price calculation for a user with Vmax card with an electrical Bicycle
	 */
	@Test
	void priceForVmaxCardEBicycleIsCorrect() {
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		User user = new User("bob", "123412341234", new VmaxCard());
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		int dtime = 100;
		int atime1 = 3600;
		int atime2 = 5000;
		double price1 = 0.0;
		double price2 = (4900.0-3600.0)/3600.0;
		
		Rent rent1 = Rent.createRent(ebike, user, dtime, dstation, atime1, astation);
		Rent rent2 = Rent.createRent(ebike, user, dtime, dstation, atime2, astation);
		assertTrue(rent1.getPrice()==price1);
		assertTrue(rent2.getPrice()==price2);
		
	}
	
	/**
	 * Verifying price calculation when a card has time credit
	 */
	@Test
	void timeCreditManagementWorksWell() {
		Bicycle mbike = new MechanicalBicycle();
		Bicycle ebike = new ElectricalBicycle();
		User user = new User("bob", "123412341234", new VmaxCard());
		int timeCredit = 5*60;
		user.getRegistrationCard().addTimeCredit(timeCredit);
		Station dstation = new StandardStation(1, 0, 0);
		Station astation = new StandardStation(1, 0, 0);
		int dtime = 100;
		int atime1 = 3600;
		int atime2 = 5000;
		double price1 = 0.0;
		double price2 = (4900.0-3600.0-timeCredit)/3600.0;
		
		Rent rent1 = Rent.createRent(ebike, user, dtime, dstation, atime1, astation);
		Rent rent2 = Rent.createRent(ebike, user, dtime, dstation, atime2, astation);
		assertTrue(rent1.getPrice()==price1);
		assertTrue(rent2.getPrice()==price2);
	}

}
