package bpp.simulatie;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import applicatie.Main;
import applicatie.Model;
import bpp.simulatie.algoritmes.Bibliotheek;
import domeinmodel.*;

public class SimulatieKeuzemenu extends JPanel implements ActionListener, Observer {
	private JLabel JLradiobuttons;
	private JButton JBladen;
	private JButton JBproductToevoegen;
	private JButton JBproductOpslaan;
	private JTextField JTFartikelnummer;
	private ButtonGroup BGalgoritmes;
	private Model model;
	private JComboBox<Integer> JCBgrootteDoos;
	private JPanel JPbestelling;
	private JPanel JPproductToevoegen;
	private JPanel JPlaadKnop;
	private List<Product> producten;
	private Bestelling bestelling;
	private int hoogteJPbestelling;

	public SimulatieKeuzemenu(Model model) {
		this.model = model;
		this.model.addObserver(this);
		setSize(new Dimension(400, 900));
		setLayout(null);

		JPanel JPgrootteDoos = new JPanel();
		JPanel JPalgoritmes = new JPanel();
		JPbestelling = new JPanel();
		JPlaadKnop = new JPanel();
		JPproductToevoegen = new JPanel();

		JLradiobuttons = new JLabel("Algoritmes:");
		BGalgoritmes = new ButtonGroup();
		JPalgoritmes.add(JLradiobuttons);
		List<String> algoritmeNamen = new Bibliotheek().getAlgoritmeNamen();
		for (int i = 0; i < algoritmeNamen.size(); i++) {
			JRadioButton JRB = new JRadioButton(algoritmeNamen.get(i));
			JRB.setActionCommand(String.valueOf(i));
			if (i == 0) {
				JRB.setSelected(true);
			}
			BGalgoritmes.add(JRB);
			JPalgoritmes.add(JRB);
		}

		Integer[] doosGroottes = new Integer[] { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		JCBgrootteDoos = new JComboBox<>(doosGroottes);
		JPgrootteDoos.add(new JLabel("Grootte doos: "));
		JPgrootteDoos.add(JCBgrootteDoos);

		JBladen = new JButton("Laden");
		JPlaadKnop.add(JBladen);

		JPalgoritmes.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPgrootteDoos.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPlaadKnop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JPproductToevoegen.setLayout(new FlowLayout(FlowLayout.LEFT));

		add(JPalgoritmes);
		add(JPgrootteDoos);

		JBladen.addActionListener(this);
		JBproductToevoegen = new JButton("Producten toevoegen");
		JBproductOpslaan = new JButton("Product opslaan");
		JBproductToevoegen.setPreferredSize(new Dimension(165, 25));
		JBproductOpslaan.setPreferredSize(new Dimension(165, 25));
		JPproductToevoegen.add(JBproductToevoegen);
		JPproductToevoegen.add(JBproductOpslaan);

		add(JPproductToevoegen);
		add(JPlaadKnop);
		JBproductOpslaan.setEnabled(false);

		JBproductToevoegen.addActionListener(this);
		JBproductOpslaan.addActionListener(this);
		
		JPalgoritmes.setSize(new Dimension(120, 140));
		JPgrootteDoos.setSize(new Dimension(330, 30));
		JPlaadKnop.setSize(new Dimension(345, 35));

		JPalgoritmes.setLocation(0, 0);
		JPgrootteDoos.setLocation(0, 145);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBladen) {
			model.setAlgoritmeIndex(Integer.parseInt(BGalgoritmes.getSelection().getActionCommand()));
			model.setGrootteDoos((int) JCBgrootteDoos.getSelectedItem());
			model.setGestart(true);
		} else if (e.getSource() == JBproductToevoegen) {
			JPproductToevoegen.removeAll();

			JTFartikelnummer = new JTextField(30);
			JPproductToevoegen.add(JTFartikelnummer);
			JPproductToevoegen.add(JBproductToevoegen);
			JPproductToevoegen.add(JBproductOpslaan);
			JBproductToevoegen.setEnabled(false);
			JBproductOpslaan.setEnabled(true);
			JPproductToevoegen.setLocation(0, 185 + hoogteJPbestelling);
			revalidate();
		} else if (e.getSource() == JBproductOpslaan) {
			try {
				int artikelnummer = Integer.parseInt(JTFartikelnummer.getText());
				if (artikelnummer < 1 || artikelnummer > 25) {
					JOptionPane.showMessageDialog(null, "Artikelnummer is een getal 1 en 25", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Product nieuweProduct = new Product(Integer.parseInt(Main.database[artikelnummer - 1][0]),
							Integer.parseInt(Main.database[artikelnummer - 1][1]),
							Integer.parseInt(Main.database[artikelnummer - 1][2]),
							Integer.parseInt(Main.database[artikelnummer - 1][3]), Main.database[artikelnummer - 1][4]);
					bestelling.voegProductToe(nieuweProduct);
					producten.add(nieuweProduct);
					JBproductToevoegen.setEnabled(false);
					JBproductOpslaan.setEnabled(true);
					toonBestelling();
					JTFartikelnummer.setText("");
				}

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Als artikelnummer moet er een getal worden ingevoerd", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("XMLgeladen")) {
			bestelling = this.model.getBestelling();
			producten = new ArrayList<>(bestelling.getProducten());
			hoogteJPbestelling = (producten.size() + 1) * 30;
			
			JPproductToevoegen.setSize(new Dimension(400, 70));
			toonBestelling();
		}
	}

	public void toonBestelling() {
		hoogteJPbestelling = (producten.size() + 1) * 30;
		JPbestelling.removeAll();
		JPbestelling.setLocation(5, 180);
		JPbestelling.add(new JLabel("Artikelnummer:"));
		JPbestelling.add(new JLabel("Naam:"));
		JPbestelling.setSize(new Dimension(350, hoogteJPbestelling));
		JPbestelling.setLayout(new GridLayout((producten.size() + 1), 2));
		
		for (Product product : producten) {
			JLabel JTFartikelnummer = new JLabel(String.valueOf(product.getArtikelnummer()));
			JLabel JTFnaam = new JLabel(String.valueOf(product.getNaam()));
			JPbestelling.add(JTFartikelnummer);
			JPbestelling.add(JTFnaam);
		}
		add(JPbestelling);
		JPproductToevoegen.setLocation(0, 185 + hoogteJPbestelling);
		JPlaadKnop.setLocation(0, 800);
		add(JPlaadKnop);
		revalidate();
	}
}
