package applicatie;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import bpp.simulatie.algoritmes.Algoritme;
import bpp.simulatie.algoritmes.Bibliotheek;
import domeinmodel.Bestelling;
import domeinmodel.Product;
import tsp.scherm.NearestNeighbour;

public class OpeningsDialoog extends JDialog implements ActionListener {
	private Model model;
	private JButton JBupload;
	private JButton JBannuleren;
	private JButton JBrandom;
	private JTextField JTFrandom;
	final JFileChooser fc = new JFileChooser();
	private Bestelling bestelling;

	public OpeningsDialoog(JFrame scherm, Model model) {
		super(scherm, true);
		this.model = model;
		setTitle("XML uploaden");
		setSize(new Dimension(700, 500));
		setLayout(new FlowLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JBupload = new JButton("Upload XML");
		JBupload.addActionListener(this);
		add(JBupload);

		JTFrandom = new JTextField(20);
		add(JTFrandom);

		JBrandom = new JButton("Random order");
		JBrandom.addActionListener(this);
		add(JBrandom);

		JBannuleren = new JButton("Annuleren");
		JBannuleren.addActionListener(this);
		add(JBannuleren);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBupload) {
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				XML parser = new XML();
				bestelling = parser.getData(file);
				model.setBestelling(bestelling);
				NearestNeighbour nearestNeighbour = new NearestNeighbour(bestelling.getProducten());
				model.setRoute(nearestNeighbour.algoritme());
				model.setDozen(new Bibliotheek().getAlgoritme(0)
						.bepaalDozen(Algoritme.wisselArray(bestelling.getProducten()), 5));
				model.setXMLgeladen(true);
				setVisible(false);
			}
		} else if (e.getSource() == JBannuleren) {
			model.setGeannuleerd(true);
		} else if (e.getSource() == JBrandom) {
			model.setBestelling(makeRandomBestelling(Integer.parseInt(JTFrandom.getText())));
			model.setXMLgeladen(true);
			setVisible(false);
		}
	}

	private Bestelling makeRandomBestelling(int a) {
		Bestelling randomBestelling = new Bestelling();

		int artikelnr = (int) (Math.random() * 25);

		randomBestelling.voegProductToe(new Product(Integer.parseInt(Main.database[artikelnr][0]),
				Integer.parseInt(Main.database[artikelnr][1]), Integer.parseInt(Main.database[artikelnr][2]),
				Integer.parseInt(Main.database[artikelnr][3]), Main.database[artikelnr][4]));

		while (randomBestelling.getProducten().size() < a) {
			artikelnr = (int) (Math.random() * 25);
			boolean exists = false;

			for (Product product : randomBestelling.getProducten()) {
				if (artikelnr + 1 == product.getArtikelnummer()) {
					exists = true;
				}
			}
			if (!exists) {
				randomBestelling.voegProductToe(new Product(Integer.parseInt(Main.database[artikelnr][0]),
						Integer.parseInt(Main.database[artikelnr][1]), Integer.parseInt(Main.database[artikelnr][2]),
						Integer.parseInt(Main.database[artikelnr][3]), Main.database[artikelnr][4]));
			}
		}
		return randomBestelling;
	}
}
