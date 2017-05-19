import java.util.ArrayList;

public class NearestNeighbour implements Functies {
	private ArrayList<Product> order;
	private ArrayList<Product> route;
//	private ArrayList<Product> bezocht;
	private int stappen;
	private Product goeie;
	private int temp;
//	private boolean iNeedYou = false;

	public NearestNeighbour(Order order) {
		this.order = order.getOrder();
		route = new ArrayList<Product>();
//		bezocht = new ArrayList<Product>();
	}

	public void voegProductToeAanRoute(Product p) {
		this.route.add(p);
	}
	
//	public void voegProductToeAanBezocht(Product p) {
//		this.bezocht.add(p);
//	}

	public void printRoute() {
		System.out.println("--- NEARESTNEIGHBOUR ROUTE ---");
		for (Product p : route) {
			System.out.println(p.getLocatie());
		}
	}

	public void printOrder() {
		System.out.println("--- ORDER ---");
		for (Product p : order) {
			System.out.println(p.getLocatie());
		}
	}

	public int routeBerekenen(Product bp, Product vp) {
		stappen = bp.meetAfstand(vp);
		System.out.println("aantal stappen naar " + vp.getLocatie() + " = " + stappen);
		return stappen;
	}

	public ArrayList<Product> algoritme() {
		printOrder();
		goeie = order.get(0);
		while (order.size() > 0) {
			voegProductToeAanRoute(goeie);
			order.remove(goeie);
			Product kruisje = goeie;
			System.out.println(
					"(" + kruisje.getLocatie() + ") toegevoegd aan route en verwijderd uit order");
			temp = 1000;
			for (int i = 0; i < order.size(); i++) {
				Product rondje = order.get(i);
				routeBerekenen(kruisje, rondje);
				if (stappen < temp) {
					temp = stappen;
					goeie = rondje;
				}
			}
		}
		return route;
	}
}
