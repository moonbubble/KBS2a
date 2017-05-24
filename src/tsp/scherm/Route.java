package tsp.scherm;

import java.util.ArrayList;
import java.util.Arrays;

import domeinmodel.Product;

public class Route {
	private ArrayList<Product> order = new ArrayList<Product>();
	private double afstand;
	
	public Route(ArrayList<Product> initialRoute) {
		this.order = (initialRoute);
	}
	public Route (Route route) {
		route.order.stream().forEach(x -> order.add(x));
	}
	public Route(ArrayList<Product> order, double afstand) {
		this.order = order;
		this.afstand = afstand;
	}
	public ArrayList<Product> getOrder() {
		return order;
	}
	
	public String toString() {
		return Arrays.toString(order.toArray()) + " | " + afstand;
	}

	public double getAfstand() {
		return afstand;
	}
}
