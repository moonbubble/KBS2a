package bpp.simulatie;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import applicatie.Model;
import bpp.simulatie.algoritmes.Algoritme;
import bpp.simulatie.algoritmes.Bibliotheek;
import domeinmodel.Bestelling;
import domeinmodel.Doos;

public class SimulatieTekening extends JPanel implements Observer {
	private Model model;
	private Bestelling bestelling;
	private List<Doos> dozen;
	private List<DoosAfbeelding> doosAfbeeldingen;

	private int COx;
	private int COy;
	private int grootteDoos;

	public SimulatieTekening(Model model) {
		this.model = model;
		this.model.addObserver(this);
		setSize(new Dimension(1200, 800));
		setBackground(Color.WHITE);
		setLayout(null);
	}

	public int getAantalDozen() {
		return dozen.size();
	}

	public void tekenDoos(int index) {
		DoosAfbeelding doosAfbeelding = doosAfbeeldingen.get(index);
		add(doosAfbeelding);
		repaint();
	}

	public void tekenAlleDozen() {
		for (DoosAfbeelding doosAfbeelding : doosAfbeeldingen) {
			add(doosAfbeelding);
			doosAfbeelding.tekenAlleProducten();
		}
		repaint();
	}

	public void tekenInStappen(int indexDoos, int indexProduct) {
		DoosAfbeelding doosAfbeelding = getDoosAfbeeldingen().get(indexDoos);
		doosAfbeelding.tekenProduct(indexProduct);
		tekenDoos(indexDoos);
	}

	public List<DoosAfbeelding> getDoosAfbeeldingen() {
		return doosAfbeeldingen;
	}

	private void laadTekening() {
		this.bestelling = model.getBestelling();
		doosAfbeeldingen = new ArrayList<>();
		Algoritme algoritme = new Bibliotheek().getAlgoritme(model.getAlgoritmeIndex());
		grootteDoos = model.getGrootteDoos();
		dozen = algoritme.bepaalDozen(bestelling.getProducten(), grootteDoos);

		COx = 100;
		COy = 50;

		for (int i = 0; i < dozen.size(); i++) {
			Doos doos = dozen.get(i);
			if (i <= 8) {
				if (i == 4) {
					COx = 100;
					COy = COy + 400;
				}
				DoosAfbeelding doosAfbeelding = new DoosAfbeelding(doos);
				doosAfbeelding.setLocation(COx, COy);
				doosAfbeeldingen.add(doosAfbeelding);

				COx = COx + 250;
			}
		}
		this.model.setGeladen(true);

	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("isGestart")) {
			if (((Model) model).isGestart()) {
				removeAll();
				repaint();
				laadTekening();
			}
		} else if (string.equals("isGeladen")) {
			if (((Model) model).isGeladen()) {
				double grootteProducten = 0;
				for (Doos doos : dozen) {
					grootteProducten += doos.getInhoud();
				}
				double doosGrootte = this.model.getGrootteDoos();
				double aantalDozen = dozen.size();
				this.model.setPercentage((grootteProducten / (aantalDozen * doosGrootte)) * 100);
				this.model.setAantalDozen(dozen.size());
			}
		} else if (string.equals("indexenGewijzigd")) {
			laadTekening();
                        
			tekenInStappen(((Model) model).getDoosIndex(), ((Model) model).getProductIndex());
		}
	}
}
