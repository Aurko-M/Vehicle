import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MotorVehicle.java
 * Purpose: Implements Vehicle interface, will
 *
 * @author Aurko Mallick
 * @version 1.0 08/13/2016
 */
public class MotorVehicle implements Vehicle {
	/**
	 * Default constructor
	 */
	public MotorVehicle() {
		this._vehicleCurrentSpeed = 0;
		this._vehicleOn = false;
		this._fuelLevels = 0;
		this._batteryLevel = 0;
		this._tirePSI = new int[] { 0, 0, 0, 0 };
		this._brakeFluidLevels = 0;
		this._wheelAllignments = new int[]{ 0, 0, 0, 0 };
		this._lightBulbs = new int[] { 0, 0};
		this._engineOil = new int[] {0, 0};
		this._vehicleDestroyed = true;
		this._totalMileage = 0;
		this._maxVehicleSpeed = 0;
	}

	/**
	 * Overloaded Constructor that will create a MotorVehicle based on the given parameters.
	 *
	 * @param _fuelLevels amount of gas in tank
	 * @param _batteryLevel percentage of power in battery
	 * @param _tirePSI PSI of each tire
	 * @param _brakeFluidLevels percentage of brake fluid
	 * @param _wheelAllignments alignment of each wheel
	 * @param _lightBulbs intensity of each bulb
	 * @param _engineOil status of engine oil
	 * @param _vehicleDestroyed vehicle's operating condition
	 * @param _totalMileage total mileage run by car.
	 * @param _accelerationRate rate at which the vehicle will accelerate
	 * @param _deccelerationRate rate at which the vehicle will decelerate
	 * @param _minAcceptableTirePSI acceptable PSI for the tire
	 */
	public MotorVehicle( int _fuelLevels, int _batteryLevel, int[] _tirePSI,
	                     int _brakeFluidLevels, int[] _wheelAllignments, int[] _lightBulbs,
	                     int[] _engineOil, boolean _vehicleDestroyed, int _totalMileage,
	                     int _accelerationRate, int _deccelerationRate, int _minAcceptableTirePSI,
	                     int _maxVehicleSpeed) {
		this._vehicleCurrentSpeed = 0;
		this._vehicleOn = false;
		this._fuelLevels = _fuelLevels;
		this._batteryLevel = _batteryLevel;
		this._tirePSI = _tirePSI;
		this._brakeFluidLevels = _brakeFluidLevels;
		this._wheelAllignments = _wheelAllignments;
		this._lightBulbs = _lightBulbs;
		this._engineOil = _engineOil;
		this._vehicleDestroyed = _vehicleDestroyed;
		this._totalMileage = _totalMileage;
		this._accelerationRate = _accelerationRate;
		this._deccelerationRate = _deccelerationRate;
		this._minAcceptableTirePSI = _minAcceptableTirePSI;
		this._maxVehicleSpeed = _maxVehicleSpeed;
	}

	/**
	 * Starts the car if the care meets the requirements to run.
	 *
	 * @return whether the engine properly started
	 */
	public boolean startEngine() {
		if (!this.is_vehicleOn()
				&& !this._vehicleDestroyed
				&& this._fuelLevels > 0
				&& this._batteryLevel > 0) {
			return this.set_vehicleOn(true);
		}
		return false;
	}

	/**
	 * Stops the car
	 *
	 * @return whether engine properly ended
	 */
	public boolean stopEngine() {
		if(this.is_vehicleOn()) {
			return  this.set_vehicleOn(false);
		}
		return false;
	}

	/**
	 * Speeds the vehicle up based on it's acceleration rate;
	 * checks to ensure vehicle's current speed doesn't surpass it's max speed.
	 *
	 * @param speed how much speed to increase by
	 * @return current speed of the car after accelerating
	 */
	public int accelerate() {
		if(this._vehicleOn && this.get_fuelLevels() > 10) {
			if (this.get_vehicleCurrentSpeed() + this.get_accelerationRate() >= this.get_maxVehicleSpeed()) {
				this.set_vehicleCurrentSpeed(this.get_maxVehicleSpeed());
				this.set_fuelLevels(this.get_fuelLevels()-10);
			} else {
				this.set_vehicleCurrentSpeed(this.get_vehicleCurrentSpeed() + this.get_accelerationRate());
				this.set_fuelLevels(this.get_fuelLevels()-10);
			}
			return this.get_vehicleCurrentSpeed();
		}
		return 0;
	}

	/**
	 * Slows the vehicle down
	 *
	 * @param speed how much to deduct
	 * @return current speed of the car after decelerating
	 */
	public int decelerate() {
		if(this._vehicleOn) {
			if (this.get_vehicleCurrentSpeed() - this.get_deccelerationRate() <= 0) {
				this.set_vehicleCurrentSpeed(0);
			} else {
				this.set_vehicleCurrentSpeed(this.get_vehicleCurrentSpeed() - this.get_deccelerationRate());
			}
			return this.get_vehicleCurrentSpeed();
		}
		return 0;
	}

	/**
	 * Determine if the vehicle is able to run
	 *  MotorVehicle will work so long as it meets the following requirements
	 *  there is fuel in the vehicle
	 *  there is power in the battery
	 *  there is engine oil in the car
	 *  and the vehicle isn't destroyed
	 * @return whether car works or not
	 */
	public boolean isWorking() {
		if(this.get_fuelLevels() > 0
				&& this.get_batteryLevel() > 0
				&& this.get_engineOil()[1] > 0
				&& !this.is_vehicleDestroyed()) {
			return true;
		}
		return false;
	}

	/**TODO: Testing to see if it works
	 * Engine status of the vehicle as an array
	 * containing list of all problems.
	 * Index determines:
	 *  0 - Engine oil
	 *      0 - ok, 1 - murky, 2 - burnt
	 *      0-100
	 *  1 - Brake fluid
	 *      0 - adequate; 1 - need to fill
	 *  2 - Wheel alignment
	 *      for each tire : 0 - adequate; 1 - misaligned
	 *  3 - Battery
	 *      0 - enough power; 1 - below 15%; 2 - below 5%
	 *  4 - Headlights
	 *      for each light: 0 - works; 1 - not responding
	 *  5 - Tires PSI
	 *      for each tire: 0 above
	 *  6 - Fuel level
	 *  7 - Vehicle is destroyed cannot be repaired
	 *
	 * @return status of engine
	 */
	public List<List<Integer>> engineStatus() {
		List<List<Integer>> engineStatus = new ArrayList<List<Integer>>();
		// Check Engine oil
		engineStatus.add(Arrays.asList(this.get_engineOil()[0],this.get_engineOil()[1]));
		// Check Brake fluid
		engineStatus.add(Arrays.asList(this.get_brakeFluidLevels()));
		// Check Wheel alignmens
		ArrayList<Integer> wheels = new ArrayList<Integer>();
		// Loop through each tire and get it's alignement
		for(int i = 0; i < this.get_wheelAllignments().length; i++) {
			wheels.add(this.get_wheelAllignments()[i]);
		}
		engineStatus.add(wheels);
		// Check Battery
		engineStatus.add(Arrays.asList(this.get_batteryLevel()));
		// Check Headlights
		ArrayList<Integer> lights = new ArrayList<Integer>();
		for(int i = 0; i < this.get_lightBulbs().length; i++) {
			lights.add(this.get_lightBulbs()[i]);
		}
		engineStatus.add(lights);
		// Check Tire PSI
		wheels = new ArrayList<Integer>();
		for(int i = 0; i < this.get_tirePSI().length; i++) {
			wheels.add(this.get_tirePSI()[i]);
		}
		engineStatus.add(wheels);
		// Check fuel level
		engineStatus.add(Arrays.asList(this.get_fuelLevels()));

		return engineStatus;
	}//TODO: testing

	/**
	 * TODO
	 * @return
	 */
	public boolean repairVehicle() {
		// Call engineStatus()
		// Loop through the list and discover problems
			// apply fixer functions for each one
		return false;
	}

	/**
	 * TODO finish testing isWorking to implement this
	 * @return
	 */
	public boolean isVehicleUpToDate() {
		if (!this._vehicleDestroyed) {
			List<List<Integer>> vehicleStatus = this.engineStatus();
			// Check Engine Oil status
				if (this.checkEngineOilGood(vehicleStatus.get(0)) > 1) {
					return false;
				}
			// Check Brake fluid
				if(this.checkBrakeFluid(engineStatus().get(1)) > 1) {
				return false;
			}
			// Check Wheel Alignments
				//TODO: Function that analyzes it
			// Check Battery
				//TODO: Function that analyzes it
			// Check Headlights
				//TODO: Function that analyzes it
			// Check Tire PSI
				//TODO: Function that analyzes it
			// Check Fuel Level
				//TODO: Function that analyzes it
		}
		return false;
	}

	/**
	 * Gets the status of the engine oil
	 * @param vehicleStatus
	 * @return integer status of the engine oil.
	 */
	protected int checkEngineOilGood(List<Integer> vehicleStatus) {
		if(vehicleStatus.get(1) < 60) {
			return 3;
		}
		if(vehicleStatus.get(0) == 1) {
			return 1;
		} else if(vehicleStatus.get(0) == 2) {
			return 2;
		}
		return 0;
	}

	/**
	 * Gets the status of brake fluid
	 * @param vehicleStatus
	 * @return integer status of brake fluid
	 */
	protected int checkBrakeFluid(List<Integer> vehicleStatus) {
		if (this.get_brakeFluidLevels() > 50) {
			return 0;
		} else if (this.get_brakeFluidLevels() < 50 && this.get_brakeFluidLevels()  >25) {
			return 1;
		}
		return 2;
	}

	/**
	 * MotorVehicle can only have it's battery replaced,
	 * set the battery level to max
	 * @return true
	 */
	public boolean chargeUpBattery() {
		this._batteryLevel = 100;
		return true;
	}

	/**
	 * MotorVehicle can only be filled to the max tank capacity
	 * @return true
	 */
	public boolean fillUpTank() {
		this.set_fuelLevels(100);
		return true;
	}

	/**
	 *  Accessors
	 */
	public String get_vehicleName() {
		return _vehicleName;
	}

	public int get_vehicleCurrentSpeed() {
		return _vehicleCurrentSpeed;
	}

	public boolean is_vehicleOn() {
		return _vehicleOn;
	}

	public int get_fuelLevels() {
		return _fuelLevels;
	}

	public int get_batteryLevel() {
		return _batteryLevel;
	}

	public int get_brakeFluidLevels() {
		return _brakeFluidLevels;
	}

	public int get_totalMileage() {
		return _totalMileage;
	}

	public int[] get_tirePSI() {
		return _tirePSI;
	}

	public int[] get_wheelAllignments() {
		return _wheelAllignments;
	}

	public int[] get_lightBulbs() {
		return _lightBulbs;
	}

	public int[] get_engineOil() {
		return _engineOil;
	}

	public boolean is_vehicleDestroyed() {
		return _vehicleDestroyed;
	}

	public int get_accelerationRate() {
		return _accelerationRate;
	}

	public int get_deccelerationRate() {
		return _deccelerationRate;
	}

	public int get_minAcceptableTirePSI() {
		return _minAcceptableTirePSI;
	}

	public int get_maxVehicleSpeed() {
		return _maxVehicleSpeed;
	}

	/**
	 *  Mutators
	 */
	public boolean set_vehicleName(String _vehicleName) {
		if(_vehicleName != null && _vehicleName.length() > 0 && _vehicleName.length() < 32) {
			this._vehicleName = _vehicleName;
			return true;
		}
		return false;
	}

	protected int set_vehicleCurrentSpeed(int _vehicleCurrentSpeed) {
		this._vehicleCurrentSpeed = _vehicleCurrentSpeed;
		return this._vehicleCurrentSpeed;
	}

	protected boolean set_vehicleOn(boolean _vehicleOn) {
		this._vehicleOn = _vehicleOn;
		return this._vehicleOn;
	}

	protected void set_fuelLevels(int _fuelLevels) {
		this._fuelLevels = _fuelLevels;
	}

	protected void set_batteryLevel(int _batteryLevel) {
		if( _batteryLevel >= 0
				&& (this.get_batteryLevel() + _batteryLevel <= 100)) {
			this._batteryLevel = _batteryLevel;
		}
	}

	protected void set_brakeFluidLevels(int _brakeFluidLevels) {
		this._brakeFluidLevels = _brakeFluidLevels;
	}

	protected void set_totalMileage(int _totalMileage) {
		this._totalMileage = _totalMileage;
	}

	protected void set_tirePSI(int[] _tirePSI) {
		this._tirePSI = _tirePSI;
	}

	protected void set_wheelAllignments(int[] _wheelAllignments) {
		this._wheelAllignments = _wheelAllignments;
	}

	protected void set_lightBulbs(int[] _lightBulbs) {
		this._lightBulbs = _lightBulbs;
	}

	protected void set_engineOil(int[] _engineOil) {
		this._engineOil = _engineOil;
	}

	protected void set_vehicleDestroyed(boolean _vehicleDestroyed) {
		this._vehicleDestroyed = _vehicleDestroyed;
	}

	protected void set_deccelerationRate(int _deccelerationRate) {
		this._deccelerationRate = _deccelerationRate;
	}

	protected void set_accelerationRate(int _accelerationRate) {
		this._accelerationRate = _accelerationRate;
	}

	protected void set_minAcceptableTirePSI(int _minAcceptableTirePSI) {
		this._minAcceptableTirePSI = _minAcceptableTirePSI;
	}

	protected void set_maxVehicleSpeed(int _maxVehicleSpeed) {
		this._maxVehicleSpeed = _maxVehicleSpeed;
	}

	/**
	 * Instance Variables
	 */
	protected String _vehicleName;
	protected int _vehicleCurrentSpeed;
	protected boolean _vehicleOn;
	protected int _fuelLevels;
	protected int _batteryLevel;
	protected int _brakeFluidLevels;
	protected int _totalMileage;
	protected int _accelerationRate;
	protected int _deccelerationRate;
	protected int _minAcceptableTirePSI;
	protected int _maxVehicleSpeed;
	/**
	 *  PSI levels for each tire,
	 *  Indexes of tire determined by its position on vehicle
	 *  Even indexes are left tires, Odd indexes are right tires
	 *  Lower indexes are near the front, higher are at the rear
	 */
	protected int[] _tirePSI;

	/**
	 *  Allignment levels for each tire, indexes are determined by position on vehicle
	 *  Even indexes are left tires, Odd indexes are right tires
	 *  Lower indexes are near the front, higher are at the rear
	 *  If value for tire is 0, the tire is perfectly aligned.
	 *  If value for tire is negative, the tire is misaligned to the left
	 *  If value for tire is positive, the tire is misaligned to the right
	 */
	protected int[] _wheelAllignments;


	/**
	 * Collection of light bulbs on vehicle
	 * 0 - Bulb is out or not responding
	 * 1 - Bulb is very low
	 * 2 - Bulb is adequate but dimming
	 * 3 - Bulb is working perfectly
	 */
	protected int[] _lightBulbs;

	/**
	 * Engine Oil status of the vehicle
	 * First index determines the quality of the oil
	 *  0 - good/clean
	 *  1 - adequate but is getting dirty
	 *  2 - Inadequate, engine oil is burnt or very dirty
	 * Second Index determines the amount of engine oil in the vehicle
	 *  0   - There is no oil
	 *  100 - Engine oil is at maximum
	 */
	protected int[] _engineOil;

	/**
	 *  Whether this vehicle can operate or not
	 */
	protected boolean _vehicleDestroyed;
}
