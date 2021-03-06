package applicatie;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import bpp.simulatie.BPPpanel;
import robot.RobotControl;
import robot.Robotpanel;
import tsp.scherm.TSPpanel;

public class Scherm extends JFrame implements Observer {
	private Model model;
	private JTabbedPane tabbedPane;

	public Scherm() {
		model = new Model();
		model.addObserver(this);
		
		setTitle("KBS2a");
		setSize(1610, 940);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1600, 900);
		
		tabbedPane.addTab("Robot", null, new Robotpanel(model), null);
		tabbedPane.addTab("TSP", null, new TSPpanel(this, model), null);
		tabbedPane.addTab("BPP", null, new BPPpanel(model), null);
		
		new OpeningsDialoog(this, model);
		new RobotControl(model);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void update(Observable model, Object string) {
		if (string.equals("XMLgeladen")) {
			if (((Model) model).getXMLgeladen()) {
				add(tabbedPane);
				repaint();
				revalidate();
			}
		} else if (string.equals("isGeannuleerd")) {
			if (((Model) model).isGeannuleerd()) {
				System.exit(0);
			}
		}
		
	}

}
