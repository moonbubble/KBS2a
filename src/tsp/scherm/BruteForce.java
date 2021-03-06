package tsp.scherm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class BruteForce implements AlgoritmeTSP{
	
	private List<Product> orderList;
	private int totaal;
	private int aantalProducten;
	private int afstand;
	private List<Product> snelsteRoute;
	private ArrayList<ArrayList<Product>> visitedRoutes = new ArrayList<ArrayList<Product>>();
	
	public BruteForce(Bestelling bestelling) {
		this.orderList = bestelling.getProducten();
		this.snelsteRoute = orderList;
	}
	
	public List<Product> algoritme() {
		for (int i = 0; i < getTotaal(); i++) {
			createOrder();
		}
		return snelsteRoute;
	}
	
	public int getAfstand(List<Product> o) {
		afstand = 0;
		for (int i = 0; i < aantalProducten - 1; i++) {
			afstand += o.get(i).meetAfstand(o.get(i + 1));
		}
		return afstand;
	}
	
	public List<Product> createOrder() {
		ArrayList<Product> newOrder = new ArrayList<>(orderList);
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
		aantalProducten = orderList.size();
		totaal = aantalProducten;
		for (int i = aantalProducten - 1; i > 1; i--) {
			totaal *= i;
		}
		return totaal;
	}

	@Override
	public String getNaam() {
		return "Brute Force";
	}
}