package tsp.scherm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TspInstellingen extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton jbOpslaan;
	private JTextField jtSnelheid;
	private JLabel jlSnelheid;
	private JTextField jtSimulaties;
	private JLabel jlSimulaties;

	public static int snelheid = 0;
	public static int aantalSimulaties = 1;

	public TspInstellingen(JFrame frame) {
		super(frame, true);
		setTitle("TSP instellingen");
		this.setPreferredSize(new Dimension(350, 200));
		setLayout(null);

		jlSnelheid = new JLabel("Snelheid:");
		jlSnelheid.setBounds(20, 20, 150, 35);
		jlSnelheid.setFont(new Font("Roboto", Font.PLAIN, 15));
		add(jlSnelheid);

		jtSnelheid = new JTextField();
		jtSnelheid.setText("" + snelheid);
		jtSnelheid.setBounds(175, 20, 100, 35);
		jtSnelheid.setFont(new Font("Roboto", Font.PLAIN, 15));
		add(jtSnelheid);

		jbOpslaan = new JButton("Opslaan");
		jbOpslaan.setBounds(175, 100, 100, 40);
		jbOpslaan.addActionListener(this);
		add(jbOpslaan);

		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jbOpslaan)) {
			snelheid = Integer.parseInt(jtSnelheid.getText());
			setVisible(false);
		}
	}
}
