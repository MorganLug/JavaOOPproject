package fr.Group13.MainVelibClasses;
/**
 * Enum of the Status a Slot can have
 * 
 * @version 1.0
 * @see Slot
 */
public enum SlotStatus {
	  OOO ("Out Of Order"),
	  Free ("Free"),
	  OCC ("Occupied");
	   
	  private String name = "";
	  
	  /**
	   * Constructor of the enum
	   * @param name the description of the status
	   */
	  SlotStatus(String name){
	    this.name = name;
	  }
	  
	  /**
	   * toString returns the description associated with the status of the slot
	   * @return name the description of the status
	   */
	  public String toString(){
	    return name;
	  }
}
