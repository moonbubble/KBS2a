package tsp.scherm;

import java.util.Collections;
import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class RandomPath {
	private List<Product> orderList;

	public RandomPath(Bestelling bestelling) {
		this.orderList = bestelling.getProducten();
	}

	public void printRoute() {
		System.out.println("--- ROUTE ---");
		for (Product p : orderList) {
			System.out.println(p.getLocatie());
		}
	}

	public void printOrder() {
		System.out.println("--- ORDER ---");
		for (Product p : orderList) {
			System.out.println(p.getLocatie());
		}
	}

	public List<Product> algoritme() {
		Collections.shuffle(orderList);
		return orderList;
	}

}
