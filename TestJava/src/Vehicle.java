import java.util.List;
/**
 * Vehicle.java
 * Purpose: Interface class for all vehicles
 *
 * @author Aurko Mallick
 * @version 1.0 08/13/2016
 */
public interface Vehicle {
	boolean startEngine();
	boolean stopEngine();
	int accelerate();
	int decelerate();
	boolean isWorking();
	List<List<Integer>> engineStatus();
}
