package robot;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import applicatie.Model;

public class Robotpanel extends JPanel implements ActionListener{
	JButton JBstartRobot;
	JButton JBstopRobot;
	JButton JBpauzeerRobot;
	Model model;

	public Robotpanel(Model model) {
		this.model = model;
		setLayout(new FlowLayout());
		
		JBstartRobot = new JButton("Start robot");
		add(JBstartRobot);
		
		JBstopRobot = new JButton("Stop robot");
		add(JBstopRobot);
		
		JBpauzeerRobot = new JButton("Pauzeer robot");
		add(JBpauzeerRobot);
		
		JBstartRobot.addActionListener(this);
		JBstopRobot.addActionListener(this);
		JBpauzeerRobot.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == JBstartRobot) {
			model.setRobotGestart(true);
		} else if (e.getSource() ==  JBstopRobot) {
			model.setRobotGestart(false);
		} else if (e.getSource() == JBpauzeerRobot) {
			
		}
	}

	
}
