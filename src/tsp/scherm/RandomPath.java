package tsp.scherm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class RandomPath implements Functies {
	private List<Product> order;
	private ArrayList<Product> route;

	public RandomPath(Bestelling order) {
		this.order = order.getProducten();
		route = new ArrayList<Product>();
	}

	public RandomPath() {
		order = new ArrayList<Product>();
		route = new ArrayList<Product>();
	}

	@Override
	public void voegProductToeAanRoute(Product p) {
		this.route.add(p);
	}

	@Override
	public void printRoute() {
		System.out.println("--- ROUTE ---");
		for (Product p : route) {
			System.out.println(p.getLocatie());
		}
	}

	@Override
	public void printOrder() {
		System.out.println("--- ORDER ---");
		for (Product p : order) {
			System.out.println(p.getLocatie());
		}
	}

	public int getRandom(int min, int max) {
		double randomDouble = Math.floor(Math.random() * (max - min)) + min;
		int randomInt = (int) randomDouble;
		return randomInt;
	}

	public Product maakRandomProduct() {
		int x = getRandom(1, 6);
		int y = getRandom(1, 6);
		// System.out.println("(" + x + ", " + y + ")");
		Product p = new Product(x, y);
		return p;
	}

	public Bestelling maakOrder(int aantalItems) {
		Bestelling ord = new Bestelling();
		for (int i = 0; i < aantalItems; i++) {
			Product pro = maakRandomProduct(); // maak één random product aan
			if (ord.getProducten().size() > 0) {
				for (int m = 0; m < ord.getProducten().size(); m++) {
					for (int j = 0; j < ord.getProducten().size(); j++) { 
						int vergelijkX = ord.getProducten().get(j).getX(); 
						int vergelijkY = ord.getProducten().get(j).getY();
						while (pro.getX() == vergelijkX && pro.getY() == vergelijkY) {
							pro = maakRandomProduct();
						}
					}
				}
			}
			ord.getProducten().add(pro);
		}
		return ord;
	}
	
	public void buurmanMol(String tekst) {
		tekst = tekst.substring(0, 1).toUpperCase() + tekst.substring(1);
		System.out.println("'" + tekst + "', zei buurman Mol.");
	}

	public void algoritme() {
		while (order.size() > 0) {
			for (int i = 0; i < order.size(); i++)
			{
				voegProductToeAanRoute(order.get(0));
				order.remove(order.get(0));
			}
		}
		Collections.shuffle(route);
		printRoute();
	}

}
