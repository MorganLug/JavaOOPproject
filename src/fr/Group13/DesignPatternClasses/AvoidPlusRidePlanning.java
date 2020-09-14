package fr.Group13.DesignPatternClasses;

import java.util.ArrayList;

import fr.Group13.MainVelibClasses.ElectricalBicycle;
import fr.Group13.MainVelibClasses.MechanicalBicycle;
import fr.Group13.MainVelibClasses.PlusStation;
import fr.Group13.MainVelibClasses.Slot;
import fr.Group13.MainVelibClasses.SlotStatus;
import fr.Group13.MainVelibClasses.StandardStation;
import fr.Group13.MainVelibClasses.Station;

/**
 * Planning of the ride when given departure and arrival coordinates, avoiding plus stations
 * 
 * @see RidePlanning
 */
public class AvoidPlusRidePlanning implements RidePlanning {
	
	/**
	 * Constructor of the class
	 */
	public AvoidPlusRidePlanning() {}
	
	
	
	/**
	 * @param departure coordinates and an array of stations
	 * @return closest online station with at least one bike, or null
	 */
	@Override
	public Station dStation(double dX, double dY, ArrayList<Station> stations) {
		Station station = null;
		double squareDistance = 10000000000000000000000000000.0; //infinite distance
		try {
			for (Station station2 : stations) {
				Slot occSlot = null;
				ArrayList<Slot> slots = station2.getParkingSlots();
				for (Slot slot : slots) {
					if (slot.getStatus()==SlotStatus.OCC) {
						occSlot = slot;
						break;
					}
				}
				//check if the station in online and has at least one occupied slot
				if ((station2.getIsOnline()==true) & (occSlot!=null)) {
					double sX = station2.getCoordinateX();
					double sY = station2.getCoordinateY();
					//checks if this station is closer to the departure coordinates than the closer station amongst the station before this one
					if (squareDistance > (dX - sX)*(dX - sX) + (dY - sY)*(dY - sY)) {
						station = station2;
						squareDistance = (dX - sX)*(dX - sX) + (dY - sY)*(dY - sY);
					}
				}
			}
			if (station == null) {
				throw new Exception("There is no available station");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
		}
		return(station);
	}
	
	/**
	 * For a mechanical bicycle
	 * @param departure coordinates and an array of stations
	 * @return closest online station with at least one mechanical bike, or null
	 */
	@Override
	public Station dStationMechanical(double dX, double dY, ArrayList<Station> stations) {
		Station station = null;
		double squareDistance = 10000000000000000000000000000.0; //infinite distance
		try {
			for (Station station2 : stations) {
				Slot occSlot = null;
				ArrayList<Slot> slots = station2.getParkingSlots();
				for (Slot slot : slots) {
					if ((slot.getStatus()==SlotStatus.OCC) & (slot.getBike() instanceof MechanicalBicycle)) {
						occSlot = slot;
						break;
					}
				}
				//check if the station in online and has at least one occupied slot
				if ((station2.getIsOnline()==true) & (occSlot!=null)) {
					double sX = station2.getCoordinateX();
					double sY = station2.getCoordinateY();
					//checks if this station is closer to the departure coordinates than the closer station amongst the station before this one
					if (squareDistance > (dX - sX)*(dX - sX) + (dY - sY)*(dY - sY)) {
						station = station2;
						squareDistance = (dX - sX)*(dX - sX) + (dY - sY)*(dY - sY);
					}
				}
			}
			if (station == null) {
				throw new Exception("There is no available station");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
		}
		return(station);
	}
	
	/**
	 * For an electrical bicycle
	 * @param departure coordinates and an array of stations
	 * @return closest online station with at least one electrical bike, or null
	 */
	@Override
	public Station dStationElectrical(double dX, double dY, ArrayList<Station> stations) {
		Station station = null;
		double squareDistance = 10000000000000000000000000000.0; //infinite distance
		try {
			for (Station station2 : stations) {
				Slot occSlot = null;
				ArrayList<Slot> slots = station2.getParkingSlots();
				for (Slot slot : slots) {
					if ((slot.getStatus()==SlotStatus.OCC) & (slot.getBike() instanceof ElectricalBicycle)) {
						occSlot = slot;
						break;
					}
				}
				//check if the station in online and has at least one occupied slot
				if ((station2.getIsOnline()==true) & (occSlot!=null)) {
					double sX = station2.getCoordinateX();
					double sY = station2.getCoordinateY();
					//checks if this station is closer to the departure coordinates than the closer station amongst the station before this one
					if (squareDistance > (dX - sX)*(dX - sX) + (dY - sY)*(dY - sY)) {
						station = station2;
						squareDistance = (dX - sX)*(dX - sX) + (dY - sY)*(dY - sY);
					}
				}
			}
			if (station == null) {
				throw new Exception("There is no available station");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
		}
		return(station);
	}
	
	
	
	
	/**
	 * @param departure coordinates and an array of stations
	 * @return closest online station with at least free slot, or null ; avoiding plus stations
	 */
	@Override
	public Station aStation(double aX, double aY, ArrayList<Station> stations) {
		Station station = null;
		double squareDistance = 10000000000000000000000000000.0; //infinite distance
		try {
			for (Station station2 : stations) {
				//checks if the station is online and has at least one free slot, and is not a PlusStation
				if ((station2.getIsOnline() == true) & (station2.findFreeSlot() != null) & (station2 instanceof StandardStation)) {
					double sX = station2.getCoordinateX();
					double sY = station2.getCoordinateY();
					//checks if this station is closer to the departure coordinates than the closer station amongst the station before this one
					if (squareDistance > (aX - sX)*(aX - sX) + (aY - sY)*(aY - sY)) {
						station = station2;
						squareDistance = (aX - sX)*(aX - sX) + (aY - sY)*(aY - sY);
					}
				}
			}
			if (station == null) {
				throw new Exception("There is no available station");
			}
		} catch (Exception e) {
			System.err.println("Exception happened : " + e.getMessage());
		}
		return(station);
	}
	
	

}
