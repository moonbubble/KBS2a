package bpp.simulatie;

import javax.swing.JPanel;

import applicatie.Model;

public class BPPpanel extends JPanel {
	private SimulatieTekening simulatie;
	private SimulatieKeuzemenu keuzemenu;
	private SimulatieOpties opties;
	private SimulatieInformatie info;

	public BPPpanel(Model model) {
		setSize(1600, 900);
		setLayout(null);

		simulatie = new SimulatieTekening(model);
		opties = new SimulatieOpties(model, simulatie);
		keuzemenu = new SimulatieKeuzemenu(model);
		info = new SimulatieInformatie(model);

		simulatie.setLocation(0, 0);
		opties.setLocation(15, 815);
		info.setLocation(800, 815);
		keuzemenu.setLocation(1210, 0);

		add(simulatie);
		add(keuzemenu);
		add(opties);
		add(info);

		setVisible(true);
	}
}