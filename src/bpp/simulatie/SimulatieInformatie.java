package bpp.simulatie;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import applicatie.Model;

public class SimulatieInformatie extends JPanel implements Observer {

	private Model model;
	private JLabel JLgrootteDoos;
	private JLabel JLaantalDozen;
	private JLabel JLpercentage;

	public SimulatieInformatie(Model model) {
		this.model = model;
		this.model.addObserver(this);
		setSize(new Dimension(400, 30));
		setLayout(new FlowLayout());
		JLgrootteDoos = new JLabel();
		JLaantalDozen = new JLabel();
		JLpercentage = new JLabel();

		add(JLgrootteDoos);
		add(JLaantalDozen);
		add(JLpercentage);
	}

	private void laadInfo() {
		JLaantalDozen.setText("Aantal dozen: " + model.getAantalDozen());
		JLgrootteDoos.setText("Grootte doos: " + model.getGrootteDoos());
		JLpercentage.setText("Percentage: " + model.getPercentage());
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("aantalDozenGeupdated")) {
			laadInfo();
		}

	}

}
