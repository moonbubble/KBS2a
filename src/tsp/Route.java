import java.util.ArrayList;
import java.util.Arrays;

public class Route {
	private ArrayList<Product> order = new ArrayList<Product>();
	private double distance;
	
	public Route(ArrayList<Product> initialRoute) {
		this.order = (initialRoute);
	}
	public Route (Route route) {
		route.order.stream().forEach(x -> order.add(x));
	}
	public Route(ArrayList<Product> order, double distance) {
		this.order = order;
		this.distance = distance;
	}
	public ArrayList<Product> getOrder() {
		return order;
	}
	
	public String toString() {
		return Arrays.toString(order.toArray()) + " | " + distance;
	}

	public double getDistance() {
		return distance;
	}
}
