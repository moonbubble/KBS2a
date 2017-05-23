package robot;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import applicatie.Model;

public class Robotpanel extends JPanel implements ActionListener{
	JButton JBstartRobot;
	Model model;

	public Robotpanel(Model model) {
		this.model = model;
		setLayout(new FlowLayout());
		
		JBstartRobot = new JButton("Start robot");
		add(JBstartRobot);
		
		JBstartRobot.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == JBstartRobot) {
			model.setRobotGestart(true);
		}
	}

	
}
