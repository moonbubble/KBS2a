package overbodig;

import java.util.ArrayList;
import java.util.List;

import bpp.simulatie.algoritmes.Algoritme;
import bpp.simulatie.algoritmes.Bibliotheek;
import domeinmodel.Bestelling;
import domeinmodel.Doos;
import domeinmodel.Product;

public class Main {
	private static Product appel = new Product(1, 1, 1, 2, "appel");
	private static Product peer = new Product(2, 2, 1, 4, "peer");
	private static Product banaan = new Product(3, 3, 1, 5, "banaan");
	private static Product lamp = new Product(4, 4, 1, 4, "lamp");
	private static Product bank = new Product(5, 5, 1, 2, "bank");
	private static Product bureau = new Product(6, 1, 1, 5, "bureau");
	private static Product teddybeer = new Product(7, 2, 1, 4, "ted");
	private static Product laptop = new Product(8, 3, 1, 5, "laptop");
	private static Product pop = new Product(9, 4, 1, 4, "pop");
	private static Product pen = new Product(10, 5, 1, 1, "pen");

	public static void main(String[] args) {

		Bestelling bestelling = new Bestelling();
		bestelling.voegProductToe(appel);
		bestelling.voegProductToe(peer);
		bestelling.voegProductToe(banaan);
		bestelling.voegProductToe(lamp);
		bestelling.voegProductToe(bank);
		bestelling.voegProductToe(bureau);
		bestelling.voegProductToe(teddybeer);
		bestelling.voegProductToe(laptop);
		bestelling.voegProductToe(pop);
		bestelling.voegProductToe(pen);
		bestelling.getProducten();
		bestelling.getKlant();

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

		if ((lijstVanProducten.size() - 1) % 3 == 1) {
			p1 = lijstVanProducten.get(lijstVanProducten.size() - 2);
			p2 = lijstVanProducten.get(lijstVanProducten.size() - 1);

			lijstVanProducten.set(lijstVanProducten.size() - 1, p1);
			lijstVanProducten.set(lijstVanProducten.size() - 2, p2);
		}

		System.out.print("VOLGORDE PRODUCTEN: ");
		List<Product> volgorde = lijstVanProducten;
		System.out.println(volgorde);

		Algoritme algoritme = new Bibliotheek().getAlgoritme(1);
		List<Doos> dozen = algoritme.bepaalDozen(lijstVanProducten, 10);
		int productx = 0;
		int linksx = 0;
		int rechtsx = 0;

		System.out.print("VOLGORDE PRODUCTEN: ");
		System.out.println(lijstVanProducten);
		System.out.println("Er zijn " + dozen.size() + " dozen:");

		for (Doos doos : dozen) {
			System.out.println(doos.getProducten());
			List<Product> productlijst = doos.getProducten();

			for (Product product : productlijst) {
				if (volgorde.get(productx) == product) {
					System.out.println(volgorde.get(productx) + " links");
					linksx++;

					if (linksx == productlijst.size()) {
						System.out.println("VERVANG LINKS");
						linksx = 0;
					}
				} else {
					System.out.println(volgorde.get(productx) + " rechts");
					rechtsx++;
					if (rechtsx == productlijst.size()) {
						System.out.println("VERVANG RECHTS");
						rechtsx = 0;
					}
				}
				productx++;
			}
		}
	}
}
