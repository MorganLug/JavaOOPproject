package fr.Group13.DesignPatternClasses;
import fr.Group13.MainVelibClasses.*;

/**
 * Visitable interface for the visitor pattern to compute the price of a rent
 * 
 * @see Visitor
 * @see fr.group13.MainVelibClasses.Rent
 *
 */
public interface Visitable {
	public double accept(Visitor visitor);
}
