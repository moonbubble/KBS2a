package robot;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import applicatie.Model;
import domeinmodel.Doos;
import domeinmodel.Product;

public class RobotControl implements Observer {
	private BPProbot bppRobot;
	private List<Product> producten;
	private List<Doos> dozen;

	public RobotControl(Model model) {
		model.addObserver(this);
	}

	public void routeUitvoeren() {
		for (Product product : producten) {
			// Ga naar x en y;
			// Update tsppanel;
			bppRobot.bepaalPlaats(product);
			// Update bpppanel;
		}
		
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("robotStarten")) {
			if (((Model) model).getRobotStarten()) {
				producten = ((Model) model).getRoute();
				dozen = ((Model) model).getDozen();
				bppRobot = new BPProbot((Model) model);
				routeUitvoeren();
			}
		}
	}
}
