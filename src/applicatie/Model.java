package applicatie;

import java.util.Observable;

import domeinmodel.Bestelling;

public class Model extends Observable {

	private int algoritmeIndex;
	private int aantalDozen;
	private int grootteDoos;
	private double percentage;
	private boolean isGestart;
	private boolean isGeladen;
	private boolean XMLgeladen;
	private boolean isGeannuleerd;
	private Bestelling bestelling;

	public Model() {

	}

	public int getAlgoritmeIndex() {
		return algoritmeIndex;
	}

	public void setAlgoritmeIndex(int algoritmeIndex) {
		this.algoritmeIndex = algoritmeIndex;
	}

	public boolean isGestart() {
		return isGestart;
	}

	public void setGestart(boolean isGestart) {
		this.isGestart = isGestart;
		setChanged();
		notifyObservers("isGestart");
		clearChanged();
	}

	public int getAantalDozen() {
		return aantalDozen;
	}

	public void setAantalDozen(int aantalDozen) {
		this.aantalDozen = aantalDozen;
		setChanged();
		notifyObservers("aantalDozenGeupdated");
		clearChanged();
	}

	public boolean isGeladen() {
		return isGeladen;
	}

	public void setGeladen(boolean isGeladen) {
		this.isGeladen = isGeladen;
		setChanged();
		notifyObservers("isGeladen");
		clearChanged();
	}

	public int getGrootteDoos() {
		return grootteDoos;
	}

	public void setGrootteDoos(int grootteDoos) {
		this.grootteDoos = grootteDoos;
	}

	public Bestelling getBestelling() {
		return bestelling;
	}

	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}

	public void setXMLgeladen(boolean XMLgeladen) {
		this.XMLgeladen = XMLgeladen;
		setChanged();
		notifyObservers("XMLgeladen");
		clearChanged();
	}

	public boolean getXMLgeladen() {
		return XMLgeladen;
	}

	public boolean isGeannuleerd() {
		return isGeannuleerd;
	}

	public void setGeannuleerd(boolean isGeannuleerd) {
		this.isGeannuleerd = isGeannuleerd;
		setChanged();
		notifyObservers("isGeannuleerd");
		clearChanged();
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
		this.percentage =  Math.round(percentage * 100) / 100;
	}

}
