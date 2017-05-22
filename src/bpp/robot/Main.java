package bpp.robot;

import java.util.ArrayList;
import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class Main {
	private static Product appel = new Product(1, 1, 1, 5, "appel");
	private static Product peer = new Product(2, 2, 1, 4, "peer");
	private static Product banaan = new Product(3, 3, 1, 10, "peer");
	private static Product lamp = new Product(4, 4, 1, 10, "lamp");
	private static Product bank = new Product(5, 5, 1, 10, "peer");
	static int paar = 1;
	static int aantalx = 1;

	public static void main(String[] args) {

		Bestelling bestelling = new Bestelling();
		bestelling.voegProductToe(appel);
		bestelling.voegProductToe(peer);
		bestelling.voegProductToe(banaan);
		bestelling.voegProductToe(lamp);
		bestelling.voegProductToe(bank);
		bestelling.getProducten();
		bestelling.getKlant();

		// Product p1 = bestelling.getProducten(0);
		// Product p2 = bestelling.getProducten(1);
		// Product p3 = bestelling.getProducten(2);

		List<Product> lijstVanProducten = new ArrayList<>(bestelling.getProducten());

		Product p1 = null;
		Product p2 = null;
		Product p3;
		for (int i = 0; i < lijstVanProducten.size(); i++) {
			if (i % 3 == 0) {
				p1 = lijstVanProducten.get(i);
			} else if (i % 3 == 1) {
				p2 = lijstVanProducten.get(i);
			} else if (i % 3 == 2) {
				p3 = lijstVanProducten.get(i);
				lijstVanProducten.set(i, p1);
				lijstVanProducten.set(i - 1, p2);
				lijstVanProducten.set(i - 2, p3);
			}
		}
		
		if ((lijstVanProducten.size() -1)  % 3 == 1){
			p1 = lijstVanProducten.get(lijstVanProducten.size() -2);
			p2 = lijstVanProducten.get(lijstVanProducten.size() -1);
			
			lijstVanProducten.set(lijstVanProducten.size() -1, p1);
			lijstVanProducten.set(lijstVanProducten.size() -2, p2);
		}
		System.out.println(lijstVanProducten);
	}

}
