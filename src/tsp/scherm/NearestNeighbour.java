package tsp.scherm;
import java.util.ArrayList;
import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class NearestNeighbour implements Functies, AlgoritmeTSP {
	private List<Product> orderList;
	private ArrayList<Product> route;
	private int stappen;
	private Product goeie;
	private int temp;

	public NearestNeighbour(Bestelling bestelling) {
		this.orderList = bestelling.getProducten();
		route = new ArrayList<Product>();
	}

	public void voegProductToeAanRoute(Product p) {
		this.route.add(p);
	}

	public void printRoute() {
		for (Product p : route) {
			System.out.println(p.getLocatie());
		}
	}

	public void printOrder() {
		for (Product p : orderList)
		{
			System.out.println(p.getLocatie());
		}
	}

	private int routeBerekenen(Product bp, Product vp) {
		stappen = bp.meetAfstand(vp);
		return stappen;
	}
	
	public ArrayList<Product> algoritme() {
		goeie = orderList.get(0);
		while (orderList.size() > 0) {
			voegProductToeAanRoute(goeie);
			orderList.remove(goeie);
			Product kruisje = goeie;
//			System.out.println(
//					"(" + kruisje.getLocatie() + ") toegevoegd aan route en verwijderd uit order");
			temp = 1000;
			for (int i = 0; i < orderList.size(); i++) {
				Product rondje = orderList.get(i);
				routeBerekenen(kruisje, rondje);
				if (stappen < temp) {
					temp = stappen;
					goeie = rondje;
				}
			}
		}
		return route;
	}

	@Override
	public String getNaam() {
		return "Nearest Neighbour";
	}
}
