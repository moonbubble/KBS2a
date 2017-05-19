package applicatie;

import javax.swing.JFrame;

import bpp.simulatie.*;

public class Scherm extends JFrame {
	private SimulatieTekening simulatie;
	private SimulatieKeuzemenu keuzemenu;
	private SimulatieOpties opties;
	private SimulatieInformatie info;

	public Scherm() {
		Model model = new Model();
		setTitle("KBS2a");
		setSize(1600, 900);
		setLayout(null);
		setVisible(true);

		add(new BPPsimulatie(model));

		OpeningsDialoog opening = new OpeningsDialoog(this, model);
	}

}
