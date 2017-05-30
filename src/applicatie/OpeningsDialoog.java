package applicatie;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import bpp.simulatie.algoritmes.Bibliotheek;
import domeinmodel.Bestelling;
import domeinmodel.Doos;
import domeinmodel.Product;
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
	private JCheckBox checkbox;

	public OpeningsDialoog(JFrame scherm, Model model) {
		super(scherm, true);
		this.scherm = scherm;
		this.model = model;
		setTitle("XML uploaden");
		setSize(new Dimension(445, 205));

		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JBupload = new JButton("Upload XML");
		JBupload.addActionListener(this);
		add(JBupload);

		JTFaantalRandom = new JTextField(20);
		JLabel JLaantalRandomSimulaties = new JLabel("Aantal randomsimulaties: ");
		add(JLaantalRandomSimulaties);
		add(JTFaantalRandom);

		JTFaantalProductenRandom = new JTextField(20);
		JLabel JLaantalProducten = new JLabel("Aantal producten per bestelling: ");
		add(JLaantalProducten);
		add(JTFaantalProductenRandom);

		JLabel JLbesteAlgoritmesZien = new JLabel("Laat beste algoritmes zien ");
		checkbox = new JCheckBox();
		add(checkbox);
		add(JLbesteAlgoritmesZien);

		JBrandom = new JButton("Random order");
		JBrandom.addActionListener(this);
		add(JBrandom);

		JBannuleren = new JButton("Annuleren");
		JBannuleren.addActionListener(this);
		add(JBannuleren);

		Container contentPane = this.getContentPane();

		layout.putConstraint(SpringLayout.WEST, JLaantalRandomSimulaties, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, JLaantalRandomSimulaties, 5, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, JTFaantalRandom, 200, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, JTFaantalRandom, 5, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, JLaantalProducten, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, JLaantalProducten, 35, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, JTFaantalProductenRandom, 200, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, JTFaantalProductenRandom, 35, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, checkbox, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, checkbox, 65, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, JLbesteAlgoritmesZien, 25, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, JLbesteAlgoritmesZien, 65, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, JBrandom, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, JBrandom, 95, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, JBupload, 20, SpringLayout.EAST, JBrandom);
		layout.putConstraint(SpringLayout.NORTH, JBupload, 95, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, JBannuleren, 330, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, JBannuleren, 125, SpringLayout.NORTH, contentPane);

		setLocationRelativeTo(null);
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

				laadInfoVoorRobot(bestelling);
				setVisible(false);
			}
		} else if (e.getSource() == JBannuleren) {
			model.setGeannuleerd(true);
		} else if (e.getSource() == JBrandom) {
			try {
				int aantalSimulatiesRandom = Integer.parseInt(JTFaantalRandom.getText());
				int aantalProductenRandom = Integer.parseInt(JTFaantalProductenRandom.getText());
				if (checkbox.isSelected()) {
					new BesteAlgoritmesDialoog(aantalSimulatiesRandom, aantalProductenRandom, model, scherm);
				} else {
					BesteAlgoritmesDialoog geenDialoog = new BesteAlgoritmesDialoog();
					Bestelling randomBestelling = geenDialoog.makeRandomBestelling(aantalProductenRandom);

					laadInfoVoorRobot(randomBestelling);

				}

				setVisible(false);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null,
						"Het aantal simulaties en/of het aantal producten is niet correct ingevuld.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void laadInfoVoorRobot(Bestelling bestelling) {
		NearestNeighbour nearestNeighbour = new NearestNeighbour(bestelling);
		List<Product> route = nearestNeighbour.algoritme(model);
		List<Product> gewisseldeList = Util.wisselArray(route);
		for (int i = 1; i < gewisseldeList.size(); i++) {
			Product product = gewisseldeList.get(i);
			if (product.getNaam() == null) {
				gewisseldeList.remove(i);
			}
		}

		List<Doos> dozen = new Bibliotheek().getAlgoritme(0).bepaalDozen(gewisseldeList, 5);
		System.out.println(gewisseldeList);	
		System.out.println(dozen);

		model.setBestelling(bestelling);
		model.setRoute(route);
		model.setDozen(dozen);
		model.setXMLgeladen(true);
	}
}
