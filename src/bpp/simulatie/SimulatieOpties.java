package bpp.simulatie;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulatieOpties extends JPanel implements ActionListener, Observer {
	private JButton afspelen;
	private JButton volgendeStap;
	private JButton doorNaarEind;
	private SimulatieTekening simulatie;
	private Timer timer = new Timer(250, this);
	private static int indexDoos = 0;
	private static int indexProduct = 0;
	private Model model;

	public SimulatieOpties(Model model, SimulatieTekening simulatie) {
		this.model = model;
		this.model.addObserver(this);
		this.simulatie = simulatie;
		setSize(new Dimension(400, 30));
		setLayout(new GridLayout(1, 3, 20, 20));

		afspelen = new JButton("Afspelen");
		volgendeStap = new JButton(">");
		doorNaarEind = new JButton(">>");

		add(afspelen);
		add(volgendeStap);
		add(doorNaarEind);

		afspelen.addActionListener(this);
		volgendeStap.addActionListener(this);
		doorNaarEind.addActionListener(this);

		verbergKnoppen();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == doorNaarEind) {
			simulatie.tekenAlleDozen();
			verbergKnoppen();
		} else if (e.getSource() == afspelen) {
			timer.start();
		} else if (e.getSource() == timer) {
			DoosAfbeelding doosAfbeelding = simulatie.getDoosAfbeeldingen().get(indexDoos);
			if (indexProduct >= doosAfbeelding.getProductAfbeeldingen().size()) {
				indexProduct = 0;
				indexDoos += 1;
			}
			if (indexDoos < simulatie.getDoosAfbeeldingen().size()) {
				simulatie.tekenInStappen(indexDoos, indexProduct);
				indexProduct += 1;
			} else {
				timer.stop();
				verbergKnoppen();
			}
		} else if (e.getSource() == volgendeStap) {
			DoosAfbeelding doosAfbeelding = simulatie.getDoosAfbeeldingen().get(indexDoos);
			if (indexDoos < simulatie.getDoosAfbeeldingen().size()) {
				simulatie.tekenInStappen(indexDoos, indexProduct);
				indexProduct += 1;
			}
			if (indexDoos >= (simulatie.getDoosAfbeeldingen().size() - 1)
					&& indexProduct > (doosAfbeelding.getProductAfbeeldingen().size() - 1)) {
				verbergKnoppen();
			}
			if (indexProduct >= doosAfbeelding.getProductAfbeeldingen().size()) {
				indexProduct = 0;
				indexDoos += 1;
			}
		}
	}

	public void verbergKnoppen() {
		afspelen.setEnabled(false);
		volgendeStap.setEnabled(false);
		doorNaarEind.setEnabled(false);
	}

	public void toonKnoppen() {
		afspelen.setEnabled(true);
		volgendeStap.setEnabled(true);
		doorNaarEind.setEnabled(true);
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("isGestart")) {
			if (((Model) model).isGestart()) {
				toonKnoppen();
				indexDoos = 0;
				indexProduct = 0;
			} else {
				verbergKnoppen();
			}
		}
	}
}
