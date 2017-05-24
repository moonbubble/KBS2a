package tsp.scherm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class NewBruteForce {
	
	private Bestelling order;
	private int totaal;
	private int aantalProducten;
	private int afstand;
	private List<Product> snelsteRoute;
	private List<List<Product>> visitedRoutes = new ArrayList<List<Product>>();
	
	public NewBruteForce(Bestelling order) {
		this.order = order;
		this.snelsteRoute = order.getProducten();
	}
	
	public List<Product> algoritme() {
		for (int i = 0; i < getTotaal(); i++) {
			createOrder();
		}
		System.out.println("snelste route = " + snelsteRoute);
		return snelsteRoute;
	}
	
	public int getAfstand(List<Product> snelsteRoute2) {
		afstand = 0;
		for (int i = 0; i < aantalProducten - 1; i++) {
			afstand += snelsteRoute2.get(i).meetAfstand(snelsteRoute2.get(i + 1));
		}
		return afstand;
	}
	
	public List<Product> createOrder() {
		ArrayList<Product> newOrder = new ArrayList<>(order.getProducten());
		shuffleOrder(newOrder);
		while (visitedRoutes.contains(newOrder)) {
			Collections.shuffle(newOrder);
		}
		if (getAfstand(newOrder) < getAfstand(snelsteRoute)) {
			snelsteRoute = newOrder;
		}
		voegToeAanVisited(newOrder);
		return snelsteRoute;
	}
	
	public void voegToeAanVisited(ArrayList<Product> order) {
		visitedRoutes.add(order);
	}
	
	public ArrayList<Product> shuffleOrder(ArrayList<Product> order) {
		ArrayList<Product> shuffle = order;
		Collections.shuffle(shuffle);
		return shuffle;
	}
	
	public int getTotaal() {
		aantalProducten = order.getProducten().size();
		totaal = aantalProducten;
		for (int i = aantalProducten - 1; i > 1; i--) {
			totaal *= i;
		}
		return totaal;
	}
}
