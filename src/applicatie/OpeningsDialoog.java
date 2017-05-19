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

import bpp.simulatie.Bestelling;
import bpp.simulatie.Model;
import bpp.simulatie.XML;

public class OpeningsDialoog extends JDialog implements ActionListener {
	private Model model;
	private JButton JBupload;
	private JButton JBannuleren;
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
				model.setXMLgeladen(true);
				setVisible(false);
			}
		} else if (e.getSource() == JBannuleren) {
			model.setGeannuleerd(true);
		}
	}

}
