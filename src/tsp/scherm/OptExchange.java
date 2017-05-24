package tsp.scherm;

import java.util.ArrayList;
import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class OptExchange {
	private List<Product> order;
	
	public OptExchange(Bestelling o) {
		this.order = o.getProducten();
	}
	
	public void algoritme() {
		for (int i = 0; i < order.size(); i++) {
			if (order.get(i).getX() == 1) {
				System.out.println("X is 1");
			}
		}
	}
}
