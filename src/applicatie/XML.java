package applicatie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domeinmodel.*;

public class XML {

	private Bestelling bestelling;
	private List<Product> orderList = new ArrayList<>();

	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document document;

	public XML() {
	}

	public Bestelling getData(File file) {
		
		try {
			dbf = DocumentBuilderFactory.newInstance();

			db = dbf.newDocumentBuilder();
			document = db.parse(file);

			NodeList nList = document.getElementsByTagName("bestelling");
			NodeList aList = document.getElementsByTagName("artikelnr");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					Klant k = new Klant((eElement.getElementsByTagName("voornaam").item(0).getTextContent()),
							(eElement.getElementsByTagName("achternaam").item(0).getTextContent()),
							(eElement.getElementsByTagName("adres").item(0).getTextContent()),
							(eElement.getElementsByTagName("postcode").item(0).getTextContent()),
							(eElement.getElementsByTagName("plaats").item(0).getTextContent()));
					bestelling = new Bestelling(
							Integer.parseInt((eElement.getElementsByTagName("ordernummer").item(0).getTextContent())),
							(eElement.getElementsByTagName("datum").item(0).getTextContent()), k);
					for (int a = 0; a < aList.getLength(); a++) {
						this.orderList.add(new Product(
								Integer.parseInt(Main.database[(Integer.parseInt(aList.item(a).getTextContent()) - 1)][0]),
								Integer.parseInt(
										Main.database[(Integer.parseInt(aList.item(a).getTextContent()) - 1)][1]),
								Integer.parseInt(
										Main.database[(Integer.parseInt(aList.item(a).getTextContent()) - 1)][2]),
								Integer.parseInt(
										Main.database[(Integer.parseInt(aList.item(a).getTextContent()) - 1)][3]),
								Main.database[(Integer.parseInt(aList.item(a).getTextContent()) - 1)][4]));
					}
					bestelling.setProducten(orderList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bestelling;
	}

	public Object[][] getDatabase(Object[][] temp) {
		try {
			dbf = DocumentBuilderFactory.newInstance();

			db = dbf.newDocumentBuilder();
			document = db.parse("src/applicatie/database.xml");

			NodeList productList = document.getElementsByTagName("Product");

			for (int i = 0; i < productList.getLength(); i++) {
				Node productNode = productList.item(i);
				Element eElement = (Element) productNode;

				temp[i][0] = eElement.getAttribute("artikelnr");
				temp[i][1] = eElement.getElementsByTagName("X").item(0).getTextContent();
				temp[i][2] = eElement.getElementsByTagName("Y").item(0).getTextContent();
				temp[i][3] = eElement.getElementsByTagName("Gewicht").item(0).getTextContent();
				temp[i][4] = eElement.getElementsByTagName("Naam").item(0).getTextContent();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
