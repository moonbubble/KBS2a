package robot;

import java.util.List;

import applicatie.Model;
import domeinmodel.Doos;
import domeinmodel.Product;
import jssc.SerialPortException;
import robot.tsp.Controller;

public class BPProbot {
	private List<Doos> dozen;
	private int inhoudDoos0 = 0;
	private int inhoudDoos1 = 0;
	private Model model;
	private Controller controller;
	private List<Product> producten;
	private int productInt = 2;


	public BPProbot(Model model) {
		this.model = model;
		this.dozen = model.getDozen();
		this.producten = model.getRoute();
	}
	
	public void setController(Controller c)
	{
		this.controller = c;
	}
	
	public void bepaalPlaats() {
		Product productTSP = producten.get(productInt);
		for (int i = 0; i < dozen.size(); i++) {
			Doos doos = dozen.get(i);
			for (int j = 0; j < doos.getProducten().size(); j++) {
				Product product = doos.getProducten().get(j);
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
					model.setIndexen(i, j);
				}
			}
		}
		if((productInt - 1) % 3 == 0)
		{
			productInt += 5;
		}
		else
		{
			productInt--;			
		}
	}

	private void knipperLampje(int doosVol) {
		if (doosVol == 0) {
			controller.sendCommand("ll");
			System.out.println("Knipper lampje: Links");
		} else if (doosVol == 1) {
			 controller.sendCommand("lr");
			System.out.println("Knipper lampje: Rechts");
		}
	}

	public void plaatsProduct(int richting) {
		if (richting == 0) {
			controller.sendCommand("bl");
			System.out.println("Band naar: Links");
		} else if (richting == 1) {
			controller.sendCommand("br");
			System.out.println("Band naar: Rechts");
		}
	}
}
