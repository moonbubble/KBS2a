package robot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Robotpanel extends JPanel {

	JProgressBar progressBar;
	JButton btnImporteerBestelling;
	JButton btnNewButton;

	public void Robotpanel() {
		setLayout(null);

//		JButton btnNewButton = new JButton("Start");
//		btnNewButton.setToolTipText("");
//		btnNewButton.setBounds(505, 382, 460, 78);
//		add(btnNewButton);
//		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
//
//		JButton btnImporteerBestelling = new JButton("Importeer bestelling");
//		btnImporteerBestelling.setBounds(12, 13, 460, 69);
//		add(btnImporteerBestelling);
//		btnImporteerBestelling.setFont(new Font("Tahoma", Font.PLAIN, 25));
//
//		ImageIcon image = new ImageIcon(
//				new ImageIcon("..\\image.png").getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT));
//
//		JLabel picLabel = new JLabel(image);
//		picLabel.setBounds(12, 5, 448, 225);
//		JPanel panel_info = new JPanel();
//		panel_info.setBounds(12, 95, 460, 365);
//		add(panel_info);
//		panel_info.setLayout(null);
//		panel_info.add(picLabel);
//
//		JLabel lblUploadAubEen = new JLabel("Upload aub een bestelling");
//		lblUploadAubEen.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
//		lblUploadAubEen.setBounds(76, 243, 327, 41);
//		panel_info.add(lblUploadAubEen);
//
//		Panel panel_tijd = new Panel();
//		panel_tijd.setBounds(505, 181, 460, 145);
//		add(panel_tijd);
//		panel_tijd.setLayout(null);
//		panel_tijd.setFont(null);
//		panel_tijd.setBackground(new Color(192, 192, 192));
//
//		Label label_bezigmetproduct = new Label("Bezig met product:");
//		label_bezigmetproduct.setFont(new Font("Calibri", Font.PLAIN, 25));
//		label_bezigmetproduct.setBounds(10, 25, 218, 35);
//		panel_tijd.add(label_bezigmetproduct);
//
//		Label label_levertijd = new Label("Geschatte levertijd:");
//		label_levertijd.setFont(new Font("Calibri", Font.PLAIN, 25));
//		label_levertijd.setBounds(10, 80, 218, 42);
//		panel_tijd.add(label_levertijd);
//
//		Panel panel_status = new Panel();
//		panel_status.setBounds(505, 13, 460, 150);
//		add(panel_status);
//		panel_status.setBackground(new Color(192, 192, 192));
//		panel_status.setFont(null);
//		panel_status.setLayout(null);
//
//		Label label_statuspak = new Label("Status pakrobot:");
//		label_statuspak.setFont(new Font("Calibri", Font.PLAIN, 25));
//		label_statuspak.setBounds(15, 10, 324, 35);
//		panel_status.add(label_statuspak);
//
//		Label label_statusinpak = new Label("Status inpakrobot:");
//		label_statusinpak.setFont(new Font("Calibri", Font.PLAIN, 25));
//		label_statusinpak.setBounds(10, 74, 267, 42);
//		panel_status.add(label_statusinpak);
//
//		progressBar = new JProgressBar();
//		progressBar.setBounds(505, 323, 460, 46);
//		progressBar.setMaximum(100);
//		progressBar.setValue(0);
//		add(progressBar);
//
//		btnImporteerBestelling.addActionListener(this);
//		btnNewButton.addActionListener(this);
//
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (bimported && artikelnummers.size() >= 1) {
//					System.out.println("tjaa ");
//				} else if (bimported && artikelnummers.size() == 0) {
//					JOptionPane.showMessageDialog(null, "Er staan geen artikelen in de bestelling");
//				} else {
//					JOptionPane.showMessageDialog(null, "Upload eerst een bestelling");
//				}
//			}
//		});

	}

//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == btnImporteerBestelling) {
//		JFileChooser chooser = new JFileChooser();
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML bestanden", "XML");
//		chooser.setFileFilter(filter);
//		int returnVal = chooser.showOpenDialog(contentPane);
//
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			String filename = chooser.getSelectedFile().getName();
//			String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
//			String XML = "xml";
//
//			if (!XML.equals(extension)) {
//				JOptionPane.showMessageDialog(null, "Kies een XML bestand!");
//			} else {
//				artikelnummers.clear();
//				JOptionPane.showMessageDialog(null, "Upload Successvol!");
//				System.out.println("Je hebt dit"
//						+ " bestand gekozen: " + chooser.getSelectedFile().getName());
//				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//				DocumentBuilder db;
//				try {
//
//					db = dbf.newDocumentBuilder();
//					Document document = db.parse(chooser.getSelectedFile());
//					NodeList nList = document.getElementsByTagName("bestelling");
//					NodeList aList = document.getElementsByTagName("artikelnr");
//
//					for (int i = 0; i < nList.getLength(); i++) {
//						Node nNode = nList.item(i);
//						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//							Element eElement = (Element) nNode;
//							ordernummer = (eElement.getElementsByTagName("ordernummer").item(0)
//									.getTextContent());
//							voornaam = (eElement.getElementsByTagName("voornaam").item(0).getTextContent());
//							achternaam = (eElement.getElementsByTagName("achternaam").item(0).getTextContent());
//							adres = (eElement.getElementsByTagName("adres").item(0).getTextContent());
//							postcode = (eElement.getElementsByTagName("postcode").item(0).getTextContent());
//							plaats = (eElement.getElementsByTagName("plaats").item(0).getTextContent());
//							datum = (eElement.getElementsByTagName("datum").item(0).getTextContent());
//
//							for (int a = 0; a < aList.getLength(); a++) {
//								artikelnummers.add(Integer.parseInt(aList.item(a).getTextContent()));
//							}
//						}
//					}
//
//					if (bimported) {
//						robotPanel.remove(panel_new);
//						panel_tijd.remove(label_bezigmetproduct2);
//						panel_tijd.remove(label_levertijd2);
//					}
//					panel_new = new Panel();
//					panel_new.setBounds(10, 0, 350, 500);
//					JLabel lblXML = new JLabel();
//					lblXML.setLayout(null);
//					lblXML.setFont(new Font("Calibri", Font.PLAIN, 15));
//					lblXML.setBounds(10, 0, 350, 500);
//					panel_new.setLayout(null);
//					lblXML.setText("<HTML>" + "<b>Naam: </b>" + voornaam + " " + achternaam
//							+ "<br><b> Ordernummer: </b>" + ordernummer + "<br><b> Adres:</b> " + adres
//							+ "<br><b> Postcode:</b> " + postcode + "<br> <b>Plaats:</b> " + plaats
//							+ "<br> <b>Datum:</b> " + datum + "<br> <b>Artikelnummers:</b> " + artikelnummers
//							+ "</HTML>");
//					lblXML.setFont(new Font("Tahoma", Font.PLAIN, 20));
//					panel_new.add(lblXML);
//					panel_new.setLayout(null);
//					robotPanel.remove(panel_info);
//					robotPanel.add(panel_new);
//
//					label_bezigmetproduct2 = new Label("0 / " + artikelnummers.size());
//					label_bezigmetproduct2.setFont(new Font("Calibri", Font.PLAIN, 25));
//					label_bezigmetproduct2.setBounds(301, 25, 259, 35);
//					panel_tijd.add(label_bezigmetproduct2);
//
//					label_levertijd2 = new Label((artikelnummers.size() * 5) / 10 + " min");
//					label_levertijd2.setFont(new Font("Calibri", Font.PLAIN, 25));
//					label_levertijd2.setBounds(301, 85, 259, 35);
//					panel_tijd.add(label_levertijd2);
//
//					bimported = true;
//
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//			}
//		}
	
}
