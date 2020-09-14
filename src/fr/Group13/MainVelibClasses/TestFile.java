package fr.Group13.MainVelibClasses;

import java.util.ArrayList;
import java.util.Random;

import fr.Group13.DesignPatternClasses.*;
public class TestFile {
	
	public static void testClass(Class bikeClass) {
		if (bikeClass == MechanicalBicycle.class) {System.out.println("ouié");} 
		else {System.out.println("nokdf");}
	}
	
	public static void main(String[] args) {
		Class bikeClass = MechanicalBicycle.class;
		TestFile.testClass(bikeClass);
		
	}
}
