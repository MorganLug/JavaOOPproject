package fr.Group13.DesignPatternClasses;

public interface Observable {
	public void attach(Observer e);
	public void detach(Observer e);
	public void notifyObs();
}
