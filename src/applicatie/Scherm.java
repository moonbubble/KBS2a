package applicatie;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import bpp.simulatie.*;

public class Scherm extends JFrame implements Observer{
	private Model model;

	
	public Scherm() {
		this.model = new Model();
		// Code voor je scherm
		add(new BPPsimulatie());
		OpeningsDialoog opening = new OpeningsDialoog(this, model);
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
}
