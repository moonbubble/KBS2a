package robot;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import applicatie.Model;
import domeinmodel.Product;

public class RobotControl implements Observer {
	private BPProbot bppRobot;
	private List<Product> producten;
	private Model model;

	public RobotControl(Model model) {
		this.model = model;
		model.addObserver(this);
	}

	public void routeUitvoeren() {
		for (Product product : producten) {
			// Ga naar x en y;
			// Update tsppanel;
			bppRobot.bepaalPlaats(product);
			// Update bpppanel;
		}
		model.setRobotGestart(false);
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("robotGestart")) {
			if (((Model) model).isRobotGestart()) {
				producten = ((Model) model).getRoute();
				bppRobot = new BPProbot((Model) model);
				routeUitvoeren();
			}
		}
	}
}
