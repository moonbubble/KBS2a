package tsp.scherm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domeinmodel.Product;

public class Route {
	private List<Product> order = new ArrayList<Product>();
	private double afstand;
	
	public Route(List<Product> initialRoute) {
		this.order = (initialRoute);
	}
	public Route (Route route) {
		route.order.stream().forEach(x -> order.add(x));
	}
	public Route(List<Product> routeProducten, double afstand) {
		this.order = routeProducten;
		this.afstand = afstand;
	}
	public List<Product> getOrder() {
		return order;
	}
	
	public String toString() {
		return Arrays.toString(order.toArray()) + " | " + afstand;
	}

	public double getAfstand() {
		return afstand;
	}
}
