package fr.Group13.ClassesTestFiles;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import fr.Group13.MainVelibClasses.*;

class CardTest {
	@Test
	void addingThenSubstractingTimeResultsInTheSameInitialTimeCredit() {
		User user = new User("Bob", "123412341234");
		Card card = new VlibreCard();
		user.setRegistrationCard(card);
		assertTrue(card.getTimeCredit()==0);
		int randomAmount = 30;
		card.addTimeCredit(randomAmount);
		card.substractTimeCredit(randomAmount);
		assertTrue(card.getTimeCredit()==0);
	}
	
	@Test
	void substractingMoreThanAvailableSpendsAllAvailable() {
		User user = new User("Bob", "123412341234");
		Card card = new VlibreCard();
		user.setRegistrationCard(card);
		int randomAmount = 30;
		card.addTimeCredit(randomAmount);
		assertTrue(card.substractTimeCredit(40)==30);
	}
}
