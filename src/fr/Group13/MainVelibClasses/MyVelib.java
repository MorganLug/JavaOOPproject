package fr.Group13.MainVelibClasses;

import java.util.ArrayList;

import fr.Group13.DesignPatternClasses.*;

/**
 * Class to represent the whole system
 * 
 * @see User
 * @see Station
 * @see Rent
 *
 */
public class MyVelib implements Observable {
	String name;
	ArrayList<Observer> observers = new ArrayList<Observer>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Station> stations = new ArrayList<Station>();
	ArrayList<Rent> rents = new ArrayList<Rent>();
	
	public MyVelib(ArrayList<Station> stations, ArrayList<Rent> rents) {
		super();
		this.observers.add(new StatisticObserver(this));
		this.stations = stations;
		this.rents=rents;
		this.name = "default";
		
	}

	public MyVelib(String name, ArrayList<Station> stations, ArrayList<Rent> rents) {
		super();
		this.observers.add(new StatisticObserver(this));
		this.name = name;
		this.stations = stations;
		this.rents = rents;
	}



	//methods	
	@Override
	/**
	 * Attach an observer to the system
	 */
	public void attach(Observer e) {
		observers.add(e);
		
	}

	@Override
	/**
	 * Detach an observer from the system
	 */
	public void detach(Observer e) {
		observers.remove(e);
		
	}

	@Override
	/**
	 * Updates all observers
	 */
	public void notifyObs() {
		for (Observer observer : observers) {
			observer.update();
		}		
	}
	
	//getters & setters
	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public ArrayList<Rent> getRents() {
		return rents;
	}
	
	public void addUser(User e) {
		users.add(e);
	}
	
	public void addRent(Rent e) {
		rents.add(e);
	}
	
	public Observer getObserver(int index) {
		return observers.get(index);
	}
	
	public String getName() {
		return name;
	}
	
	
}
