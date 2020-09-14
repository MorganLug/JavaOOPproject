package fr.Group13.DesignPatternClasses;
import fr.Group13.MainVelibClasses.*;

/**
 * Visitor interface for the visitor pattern to compute the price of a rent
 * 
 * @see Visitable
 * @see ConcreteVisitor
 *
 */
public interface Visitor {
	public double visit(RentMBicycle rent);
	public double visit(RentEBicycle rent);	
}
