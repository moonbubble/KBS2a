package robot;

import java.util.List;

import applicatie.Model;
import domeinmodel.Bestelling;
import domeinmodel.Doos;
import domeinmodel.Product;

public class BPProbot {
	private Bestelling bestelling;
	private List<Doos> dozen;
	private List<Product> route;
	private int inhoudDoos0 = 0;
	private int inhoudDoos1 = 0;

	public BPProbot(Model model) {
		this.bestelling = model.getBestelling();
		this.route = model.getRoute();
		this.dozen = model.getDozen();
	}

	public void bepaalPlaats(Product productTSP) {
		for (int i = 0; i < dozen.size(); i++) {
			Doos doos = dozen.get(i);
			for (Product product : doos.getProducten()) {
				if (productTSP.equals(product)) {
					if (i % 2 == 0) {
						plaatsProduct(0);
						inhoudDoos0 += product.getGrootte();
						if (inhoudDoos0 == doos.getInhoud()) {
							knipperLampje(0);
							inhoudDoos0 = 0;
						}
					} else if (i % 2 == 1) {
						plaatsProduct(1);
						inhoudDoos1 += product.getGrootte();
						if (inhoudDoos1 == doos.getInhoud()) {
							knipperLampje(1);
							inhoudDoos1 = 0;
						}
					}
				}
			}
		}
	}

	private void knipperLampje(int doosVol) {
		if (doosVol == 0) {
			// Arduinocommando voor knipper lampje links;
			System.out.println("Knipper lampje: Links");
		} else if (doosVol == 1) {
			// Arduinocommando voor knipper lampje rechts;
			System.out.println("Knipper lampje: Rechts");
		}
	}

	public void plaatsProduct(int richting) {
		if (richting == 0) {
			// Arduinocommando voor band naar links;
			System.out.println("Band naar: Links");
		} else if (richting == 1) {
			// Arduinocommando voor band naar rechts;
			System.out.println("Band naar: Rechts");
		}
	}
}
