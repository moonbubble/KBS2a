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
import tsp.scherm.AlgoritmeTSP;
import tsp.scherm.BibliotheekTSP;
import tsp.scherm.NearestNeighbour;

public class BesteAlgoritmesDialoog extends JDialog {
	private Bestelling randomBestelling;
	private Integer algoritmesBPP[] = new Integer[new Bibliotheek().getAlgoritmeNamen().size()];
	private Integer algoritmesTSP[] = new Integer[4];
	private Algoritme meestEfficienteBPP;
	private AlgoritmeTSP meestEfficienteTSP;

	public BesteAlgoritmesDialoog(int aantalSimulatiesRandom, int aantalProductenRandom, Model model, JFrame scherm) {
		super(scherm, false);

		setTitle("Beste algoritmes");
		setSize(new Dimension(700, 500));
		setLayout(new FlowLayout());
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		algoritmesBPP[0] = 0;
		algoritmesBPP[1] = 0;
		algoritmesBPP[2] = 0;
		algoritmesBPP[3] = 0;
		
		algoritmesTSP[0] = 0;
		algoritmesTSP[1] = 0;
		algoritmesTSP[2] = 0;
		algoritmesTSP[3] = 0;

		bepaalMeestEfficienteAlgoritmes(aantalSimulatiesRandom, aantalProductenRandom);
		if (aantalSimulatiesRandom == 1) {
			NearestNeighbour nearestNeighbour = new NearestNeighbour(randomBestelling);
			randomBestelling.setProducten(nearestNeighbour.algoritme());

			model.setRoute(randomBestelling.getProducten());
			model.setDozen(new Bibliotheek().getAlgoritme(0)
					.bepaalDozen(Util.wisselArray(randomBestelling.getProducten()), 5));
			model.setBestelling(randomBestelling);
			model.setXMLgeladen(true);
		}
		add(new JLabel("Beste inpakalgoritme: " + meestEfficienteBPP.getNaam()));
		add(new JLabel(
				"Beste ophaalalgoritme: " + meestEfficienteTSP.getNaam()));

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

	private void bepaalMeestEfficienteAlgoritmes(int aantalSimulatiesRandom, int aantalProductenRandom) {
		for (int i = 1; i <= aantalSimulatiesRandom; i++) {
			randomBestelling = makeRandomBestelling(aantalProductenRandom);
			List<Doos> dozen;

			int besteAlgoritmeTSP = 0;
			int besteAlgoritmeDozen = 0;
			
			int besteAlgoritmeBPP = 0;
			int kortsteAfstand = 0;
			int afstand;

			for (int j = 0; j < new Bibliotheek().getAlgoritmeNamen().size(); j++) {
				randomBestelling.setProducten(new BibliotheekTSP(randomBestelling).getAlgoritme(j).algoritme());
				Algoritme algoritmeBPP = new Bibliotheek().getAlgoritme(j);
				dozen = algoritmeBPP.bepaalDozen(Util.wisselArray(randomBestelling.getProducten()), 5);
				
				AlgoritmeTSP algoritmeTSP = new BibliotheekTSP(randomBestelling).getAlgoritme(j);
				afstand = AlgoritmeTSP.getAfstand(randomBestelling.getProducten());
				System.out.println(afstand);
				if (j == 0) {
					besteAlgoritmeBPP = j;
					besteAlgoritmeDozen = dozen.size();
					besteAlgoritmeTSP = j;
					kortsteAfstand = afstand;
				}
				if (dozen.size() < besteAlgoritmeDozen) {
					besteAlgoritmeBPP = j;
					besteAlgoritmeDozen = dozen.size();
				}
				if (afstand > kortsteAfstand) {
					besteAlgoritmeTSP = j;
					kortsteAfstand = afstand;
				}
			}
			algoritmesBPP[besteAlgoritmeBPP]++;
			algoritmesTSP[besteAlgoritmeTSP]++;
		}

		int besteAlgoritmeBPP = 0;
		int besteAlgoritmeTSP = 0;
		
		for (Integer algoritme : algoritmesBPP) {
			if (algoritme > besteAlgoritmeBPP) {
				besteAlgoritmeBPP = algoritme;
			}
		}
		
		for (Integer algoritme : algoritmesTSP) {
			if (algoritme > besteAlgoritmeTSP) {
				besteAlgoritmeTSP = algoritme;
			}
		}

		meestEfficienteBPP = new Bibliotheek().getAlgoritme(Arrays.asList(algoritmesBPP).indexOf(besteAlgoritmeBPP));
		meestEfficienteTSP = new BibliotheekTSP(randomBestelling).getAlgoritme(Arrays.asList(algoritmesTSP).indexOf(besteAlgoritmeTSP ));
	}
}
