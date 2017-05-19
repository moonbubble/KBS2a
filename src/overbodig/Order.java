package overbodig;

import java.util.ArrayList;

public class Order {
	private ArrayList<Product> order;

	public Order() {
		order = new ArrayList<Product>();
	}

	public void voegProductToe(Product p) {
		this.order.add(p);
	}

	public int telAantalProducten() {
		int aantal = 0;
		for (int i = 0; i < order.size(); i++) {
			aantal++;
		}
		return aantal;
	}

	public void printOrder() {
		System.out.println("--- ORDER ---");
		int index = 0;
		for (Product p : order) {	
				System.out.println((index++) + ". " + p.getLocatie());
		}
	}
	
	public ArrayList<Product> getOrder() {
		return order;
	}
}
