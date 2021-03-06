package tsp.scherm;

import javax.swing.*;

import applicatie.Model;
import applicatie.Scherm;
import domeinmodel.Bestelling;
import domeinmodel.Product;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

public class TSPpanel extends JPanel implements ActionListener, Observer {
	private Model model;
	private static final long serialVersionUID = 1L;
	private Scherm scherm;
	private TspGraphic jpGraphic;
	private JPanel jpStatistics;
	private JLabel JLstatistics;
	private JLabel jlStatisticsText;
	private String StatisticsText;
	private JPanel jpButtons;
	private JButton jbStart;
	private JButton jbStop;
	private JButton jbReset;
	private JButton jbInstellingen;
	private JPanel jpOutput;
	private JLabel jlOutput;
	private JLabel jlOutputText;
	private String OutputText;
	private JPanel jpAlgorithms;
	private ButtonGroup radioButtons;
	private JRadioButton JRBneighbour;
	private JRadioButton JRBrandom;
	private JRadioButton JRBbrute;
	private JRadioButton JRBcolony;

	private Bestelling order;
	private Timer timer;
	private List<Product> producten;

	public TSPpanel(Scherm scherm, Model model) {
		this.scherm = scherm;
		this.model = model;
		model.addObserver(this);
		this.setPreferredSize(new Dimension(1020, 750));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		jpGraphic = new TspGraphic(model);
		add(jpGraphic);
		jpGraphic.drawGrid = true;
		jpGraphic.drawOrder = false;
		jpGraphic.drawLines = false;

		jpStatistics = new JPanel();
		jpStatistics.setPreferredSize(new Dimension(250, 455));
		jpStatistics.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		jpStatistics.setLayout(null);
		add(jpStatistics);

		JLstatistics = new JLabel("Statistieken");
		JLstatistics.setFont(new Font("Roboto", Font.BOLD, 20));
		JLstatistics.setBounds(5, 0, 240, 35);

		jlStatisticsText = new JLabel("<html>" + StatisticsText + "</html>", JLabel.LEFT);
		jlStatisticsText.setVerticalAlignment(JLabel.TOP);
		jlStatisticsText.setBounds(5, 35, 240, 420);

		jpButtons = new JPanel();
		jpButtons.setPreferredSize(new Dimension(1000, 45));
		jpButtons.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		jpButtons.setLayout(null);
		add(jpButtons);

		jbStart = new JButton("Start");
		jbStart.setBackground(new Color(20, 200, 50));
		jbStart.setBorder(BorderFactory.createLineBorder(Color.black));
		jbStart.setFocusPainted(false);
		jbStart.addActionListener(this);
		jbStart.setBounds(5, 5, 100, 35);
		jpButtons.add(jbStart);

		jbStop = new JButton("Stop");
		jbStop.setBackground(new Color(250, 0, 0));
		jbStop.setBorder(BorderFactory.createLineBorder(Color.black));
		jbStop.setFocusPainted(false);
		jbStop.addActionListener(this);
		jbStop.setBounds(110, 5, 100, 35);
		jpButtons.add(jbStop);

		jbReset = new JButton("Reset");
		jbReset.setBackground(new Color(100, 100, 255));
		jbReset.setBorder(BorderFactory.createLineBorder(Color.black));
		jbReset.setFocusPainted(false);
		jbReset.addActionListener(this);
		jbReset.setBounds(215, 5, 100, 35);
		jpButtons.add(jbReset);

		jbInstellingen = new JButton("Instellingen");
		jbInstellingen.setBorder(BorderFactory.createLineBorder(Color.black));
		jbInstellingen.setFocusPainted(false);
		jbInstellingen.addActionListener(this);
		jbInstellingen.setBounds(895, 5, 100, 35);
		jpButtons.add(jbInstellingen);

		jpOutput = new JPanel();
		jpOutput.setPreferredSize(new Dimension(750, 200));
		jpOutput.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		jpOutput.setLayout(null);
		add(jpOutput);

		jlOutput = new JLabel("Output:");
		jlOutput.setBounds(5, 0, 190, 35);
		jlOutput.setFont(new Font("Roboto", Font.BOLD, 20));

		OutputText = "Output<br>Coordinaten";

		jlOutputText = new JLabel("<html>" + OutputText + "</html>", JLabel.LEFT);
		jlOutputText.setVerticalAlignment(JLabel.TOP);
		jlOutputText.setBounds(5, 35, 190, 165);

		jpAlgorithms = new JPanel();
		jpAlgorithms.setPreferredSize(new Dimension(250, 200));
		jpAlgorithms.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		jpAlgorithms.setLayout(null);
		add(jpAlgorithms);

		JRBrandom = new JRadioButton("Random path");
		JRBrandom.setBounds(0, 45, 200, 40);
		JRBrandom.setFont(new Font("Roboto", Font.BOLD, 15));
		JRBrandom.addActionListener(this);
		jpAlgorithms.add(JRBrandom);

		JRBneighbour = new JRadioButton("Nearest neighbour");
		JRBneighbour.setBounds(0, 5, 200, 40);
		JRBneighbour.setFont(new Font("Roboto", Font.BOLD, 15));
		JRBneighbour.addActionListener(this);
		JRBneighbour.setSelected(true);
		jpAlgorithms.add(JRBneighbour);

		JRBbrute = new JRadioButton("Brute force");
		JRBbrute.setBounds(0, 85, 200, 40);
		JRBbrute.setFont(new Font("Roboto", Font.BOLD, 15));
		JRBbrute.addActionListener(this);
		jpAlgorithms.add(JRBbrute);

		JRBcolony = new JRadioButton("Ant colony optimization");
		JRBcolony.setBounds(0, 125, 200, 40);
		JRBcolony.setFont(new Font("Roboto", Font.BOLD, 15));
		JRBcolony.addActionListener(this);
		jpAlgorithms.add(JRBcolony);

		radioButtons = new ButtonGroup();
		radioButtons.add(JRBrandom);
		radioButtons.add(JRBneighbour);
		radioButtons.add(JRBbrute);
		radioButtons.add(JRBcolony);

		setVisible(true);

	}

	private String getSelectedRadioButton(ButtonGroup bg) {
		for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jbInstellingen)) {
			TspInstellingen tspInstellingen = new TspInstellingen(scherm);
			tspInstellingen.setLocationRelativeTo(null);
			tspInstellingen.setVisible(true);
			tspInstellingen.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		} else if (e.getSource().equals(jbStart)) {
			producten = new ArrayList<>(model.getBestelling().getProducten());
			resetRoute();
			Collections.sort(producten, new MyComparator());
			if (getSelectedRadioButton(radioButtons) == "Random path") {
				RandomPath algoritme = new RandomPath(order);
				producten = algoritme.algoritme();
			} else if (getSelectedRadioButton(radioButtons) == "Nearest neighbour") {
				NearestNeighbour algoritme = new NearestNeighbour(order);
				producten = algoritme.algoritme();
			} else if (getSelectedRadioButton(radioButtons) == "Brute force") {
				BruteForce algoritme = new BruteForce(order);
				producten = algoritme.algoritme();
			} else if (getSelectedRadioButton(radioButtons) == "Ant colony optimization") {
				AntColonyOptimization algoritme = new AntColonyOptimization(order);
				producten = algoritme.algoritme();
			}
			producten.get(0).visited();
			jpGraphic.drawLines = true;
			jpGraphic.i = 1;
			repaint();

			ActionListener actionListener = new ActionListener() {
				int i = 1;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == timer) {
						if (i >= producten.size()) {
							timer.stop();
						} else {
							producten.get(i).visited();
							jpGraphic.setProducten(producten);
							repaint();
							if (jpGraphic.i < producten.size()) {
								jpGraphic.i++;
								i++;
							}
						}
					}
				}
			};
			timer = new Timer(TspInstellingen.snelheid, actionListener);
			timer.start();
		} else if (e.getSource().equals(jbReset)) {
			jpGraphic.resetGraphic();
			timer.stop();
			resetRoute();
		} else if (e.getSource().equals(jbStop)) {
			timer.stop();
		}
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("XMLgeladen")) {
			order = ((Model) model).getBestelling();
			jpGraphic.setProducten(((Model) model).getBestelling().getProducten());
			producten = ((Model) model).getBestelling().getProducten();
		} else if (string.equals("TSPindexGewijzigd")) {
			jpGraphic.i = this.model.getTSPindex() + 2;
			updateScherm(this.model.getTSPindex());
		} else if (string.equals("robotGestart")) {
			if (this.model.isRobotGestart()) {
				resetRoute();
				jpGraphic.resetGraphic();
				verbergScherm();
				producten = new ArrayList<>(this.model.getRoute());
				jpGraphic.setProducten(producten);
				jpGraphic.drawLines = true;
			} else {
				toonScherm();
				producten = new ArrayList<>(this.model.getBestelling().getProducten());
				jpGraphic.setProducten(producten);
			}
		}
	}

	public void updateScherm(int i) {
		jpGraphic.setProducten(producten);
		repaint();
		System.out.println("TSP index: " + i);
	}

	public void verbergScherm() {
		jbStart.setEnabled(false);
		jbStop.setEnabled(false);
		jbReset.setEnabled(false);
		jbInstellingen.setEnabled(false);
		JRBbrute.setEnabled(false);
		JRBcolony.setEnabled(false);
		JRBneighbour.setEnabled(false);
		JRBrandom.setEnabled(false);
	}

	public void toonScherm() {
		jbStart.setEnabled(true);
		jbStop.setEnabled(true);
		jbReset.setEnabled(true);
		jbInstellingen.setEnabled(true);
		JRBbrute.setEnabled(true);
		JRBcolony.setEnabled(true);
		JRBneighbour.setEnabled(true);
		JRBrandom.setEnabled(true);
	}

	public void resetRoute() {
		for (int i = 0; i < producten.size(); i++) {
			producten.get(i).resetVisited();
		}
	}
}
