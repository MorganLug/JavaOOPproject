package fr.Group13.DesignPatternClasses;
import java.util.ArrayList;

import fr.Group13.MainVelibClasses.*;

/**
 * RidePlanning interface
 * 
 * @see ClassicRidePlanning
 * @see AvoidPlusRidePlanning
 * @see PreferPlusRidePlanning
 *
 */
public interface RidePlanning {
	public Station dStation(double dX, double dY, ArrayList<Station> stations);
	public Station dStationMechanical(double dX, double dY, ArrayList<Station> stations);
	public Station dStationElectrical(double dX, double dY, ArrayList<Station> stations);
	public Station aStation(double aX, double aY, ArrayList<Station> stations);

}
