package fr.Group13.MainVelibClasses;

/**
 * Slot is the class used to represent the parking slot of a Station
 * 
 * @version 1.0
 * @see SlotStatus
 */
public class Slot {
	private int id;
	private Bicycle bike;
	private SlotStatus status;
	
	/**
	 * Constructor of the Slot class. By default, Slot is Free
	 * @param id
	 */
	public Slot(int id) {
		this.id=id;
		this.status=SlotStatus.Free;
	}

	
	//Methods
	/**
	 * parkBike parks a bicycle in the slot
	 * @param bike
	 */
	public void parkBike(Bicycle bike) {
		try {
			if (!bike.getIsParked()) {
				try {
					if (this.status==SlotStatus.Free) {
						this.bike = bike;
						this.status=SlotStatus.OCC;
						bike.setParkingSlot(this);
					}
					else {
						throw new Exception("Slot is not available, slot is : " + this.status.toString());
					}
				} catch (Exception e) {
					System.err.println("Exception happened : " + e.getMessage());
				}
			} else {
				throw new Exception("Bike is already parked somewhere");
			}
		} catch (Exception e) { System.err.println("Exception happened : " + e.getMessage());}
	}
	
	/**
	 * takeBike take the bicycle parked in the slot
	 * @returns bikeTaken
	 */
	public Bicycle takeBike() {
		try {
			if (this.status==SlotStatus.OCC) {
				Bicycle bikeTaken= this.bike;
				this.bike = null;
				this.status=SlotStatus.Free;
				bikeTaken.setParkingSlot(null);
				return(bikeTaken);
			} else {throw new Exception("No bike is on the slot");}
		} catch (Exception e) { 
			System.err.println("Exception happened : " + e.getMessage() + "\n null will be returned");
			return null;
		}
	}
	
	//Constructors
	/**
	 * getBike
	 * @return bike
	 */
	public Bicycle getBike() {
		return bike;
	}	
	
	/**
	 * getStatus
	 * @return status
	 */
	public SlotStatus getStatus() {
		return status;
	}

	/**
	 * setStatus
	 * @param status
	 */
	public void setStatus(SlotStatus status) {
		this.status = status;
	}

	/**
	 * getId
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	
	
}
