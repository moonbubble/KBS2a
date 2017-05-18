package bpp.simulatie;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class BPPsimulatie extends JPanel implements Observer {
	private OpeningsDialoog opening;
	private SimulatieTekening simulatie;
	private SimulatieKeuzemenu keuzemenu;
	private SimulatieOpties opties;
	private SimulatieInformatie info;
	private Bestelling bestelling;
	private Model model;

	public BPPsimulatie() {
		this.model = new Model();
		model.addObserver(this);

		simulatie = new SimulatieTekening(model);
		opties = new SimulatieOpties(model, simulatie);
		keuzemenu = new SimulatieKeuzemenu(model);
		info = new SimulatieInformatie(model);

		simulatie.setLocation(0, 0);
		opties.setLocation(15, 815);
		info.setLocation(800, 815);
		keuzemenu.setLocation(1210, 0);
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("XMLgeladen")) {
			if (((Model) model).getXMLgeladen()) {
				add(simulatie);
				add(keuzemenu);
				add(opties);
				add(info);
				setVisible(true);
				repaint();
			}
		} else if (string.equals("isGeannuleerd")) {
			if (((Model) model).isGeannuleerd()) {
				System.exit(0);
			}
		}
	}

}