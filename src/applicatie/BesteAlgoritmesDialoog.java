package applicatie;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bpp.simulatie.algoritmes.Algoritme;
import bpp.simulatie.algoritmes.Bibliotheek;
import domeinmodel.Bestelling;
import domeinmodel.Doos;
import domeinmodel.Product;
import domeinmodel.Util;
import tsp.scherm.NearestNeighbour;

public class BesteAlgoritmesDialoog extends JDialog {
	private Bestelling randomBestelling;
	private Integer algoritmes[] = new Integer[4];

	public BesteAlgoritmesDialoog(int aantalSimulatiesRandom, int aantalProductenRandom, Model model, JFrame scherm) {
		super(scherm, false);
		
		setTitle("Beste algoritmes");
		setSize(new Dimension(700, 500));
		setLayout(new FlowLayout());
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		algoritmes[0] = 0;
		algoritmes[1] = 0;
		algoritmes[2] = 0;
		algoritmes[3] = 0;
		Algoritme meesteEfficiente = bepaalMeestEfficienteAlgoritme(aantalSimulatiesRandom, aantalProductenRandom);
		if (aantalSimulatiesRandom == 1) {
			NearestNeighbour nearestNeighbour = new NearestNeighbour(randomBestelling);
			randomBestelling.setProducten(nearestNeighbour.algoritme());

			model.setRoute(randomBestelling.getProducten());
			model.setDozen(
					new Bibliotheek().getAlgoritme(0).bepaalDozen(Util.wisselArray(randomBestelling.getProducten()), 5));
			model.setBestelling(randomBestelling);
			model.setXMLgeladen(true);
		}
		add(new JLabel("Beste inpakalgoritme: " + meesteEfficiente.getNaam()));
		
		setVisible(true);
	}

	private Bestelling makeRandomBestelling(int a) {
		Bestelling bestelling = new Bestelling();

		int artikelnr = (int) (Math.random() * 25);

		bestelling.voegProductToe(new Product(Integer.parseInt(Main.database[artikelnr][0]),
				Integer.parseInt(Main.database[artikelnr][1]), Integer.parseInt(Main.database[artikelnr][2]),
				Integer.parseInt(Main.database[artikelnr][3]), Main.database[artikelnr][4]));

		while (bestelling.getProducten().size() < a) {
			artikelnr = (int) (Math.random() * 25);
			boolean exists = false;

			for (Product product : bestelling.getProducten()) {
				if (artikelnr + 1 == product.getArtikelnummer()) {
					exists = true;
				}
			}
			if (!exists) {
				bestelling.voegProductToe(new Product(Integer.parseInt(Main.database[artikelnr][0]),
						Integer.parseInt(Main.database[artikelnr][1]), Integer.parseInt(Main.database[artikelnr][2]),
						Integer.parseInt(Main.database[artikelnr][3]), Main.database[artikelnr][4]));
			}
		}
		return bestelling;
	}

	private Algoritme bepaalMeestEfficienteAlgoritme(int aantalSimulatiesRandom, int aantalProductenRandom) {
		for (int i = 1; i <= aantalSimulatiesRandom; i++) {
			randomBestelling = makeRandomBestelling(aantalProductenRandom);
			List<Doos> dozen;
			int besteAlgoritme = 0;
			int besteAlgoritmeDozen = 0;

			for (int j = 0; j < new Bibliotheek().getAlgoritmeNamen().size(); j++) {
				Algoritme algoritme = new Bibliotheek().getAlgoritme(j);
				dozen = algoritme.bepaalDozen(Util.wisselArray(randomBestelling.getProducten()), 5);
				if (j == 0) {
					besteAlgoritme = j;
					besteAlgoritmeDozen = dozen.size();
				}
				if (dozen.size() < besteAlgoritmeDozen) {
					besteAlgoritme = j;
					besteAlgoritmeDozen = dozen.size();
				}
			}
			algoritmes[besteAlgoritme]++;
		}

		int besteAlgoritme = 0;
		for (Integer algoritme : algoritmes) {
			if (algoritme > besteAlgoritme) {
				besteAlgoritme = algoritme;
			}
		}
		return new Bibliotheek().getAlgoritme(Arrays.asList(algoritmes).indexOf(besteAlgoritme));
	}
}
