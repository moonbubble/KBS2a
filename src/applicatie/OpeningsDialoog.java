package applicatie;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bpp.simulatie.algoritmes.Bibliotheek;
import domeinmodel.Bestelling;
import domeinmodel.Util;
import tsp.scherm.NearestNeighbour;

public class OpeningsDialoog extends JDialog implements ActionListener {
	private Model model;
	private JButton JBupload;
	private JButton JBannuleren;
	private JButton JBrandom;
	private JTextField JTFaantalProductenRandom;
	private JTextField JTFaantalRandom;
	final JFileChooser fc = new JFileChooser();
	private Bestelling bestelling;
	private JFrame scherm;
	

	public OpeningsDialoog(JFrame scherm, Model model) {
		super(scherm, true);
		this.scherm = scherm;
		this.model = model;
		setTitle("XML uploaden");
		setSize(new Dimension(700, 500));
		setLayout(new FlowLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JBupload = new JButton("Upload XML");
		JBupload.addActionListener(this);
		add(JBupload);

		JTFaantalRandom = new JTextField(20);
		add(new JLabel("Aantal randomsimulaties: "));
		add(JTFaantalRandom);

		JTFaantalProductenRandom = new JTextField(20);
		add(new JLabel("Aantal producten per bestelling: "));
		add(JTFaantalProductenRandom);

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

				NearestNeighbour nearestNeighbour = new NearestNeighbour(bestelling);
				bestelling.setProducten(nearestNeighbour.algoritme());
				model.setBestelling(bestelling);
				model.setRoute(bestelling.getProducten());
				model.setDozen(
						new Bibliotheek().getAlgoritme(0).bepaalDozen(Util.wisselArray(bestelling.getProducten()), 5));
				model.setXMLgeladen(true);
				setVisible(false);
			}
		} else if (e.getSource() == JBannuleren) {
			model.setGeannuleerd(true);
		} else if (e.getSource() == JBrandom) {
			int aantalSimulatiesRandom = Integer.parseInt(JTFaantalRandom.getText());
			int aantalProductenRandom = Integer.parseInt(JTFaantalProductenRandom.getText());
			new BesteAlgoritmesDialoog(aantalSimulatiesRandom, aantalProductenRandom, model, scherm); 
			setVisible(false);
		}
	}


}
