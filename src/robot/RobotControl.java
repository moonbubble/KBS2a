package robot;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import applicatie.Model;
import domeinmodel.Product;
import jssc.SerialPortException;
import robot.tsp.Transporter;
import robot.tsp.Controller;
import robot.tsp.SerialConnection;

public class RobotControl implements Observer {

    private BPProbot bppRobot;
    private List<Product> producten;
    private Model model;
    Controller controller;
    int productInt = 0;
    boolean afleveren = false;

    public RobotControl(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public void routeUitvoeren()
    {
        try {
            controller.sendCommand("s" + producten.get(productInt).getX() + producten.get(productInt).getY());
            System.out.println(producten.get(productInt).getX() + "," + producten.get(productInt).getY());
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void nextProduct()
    {
    	if((productInt + 1) > (producten.size() - 1))
    	{
    		System.out.println("Klaar met het order");
    	}
    	else
    	{
    		if((productInt + 1) % 3 == 0 && !afleveren)
        	{
        		try
        		{
                    controller.sendCommand("bpp");
                    System.out.print("bpp order");
                    afleveren = true;
        		}
        		catch(Exception e)
        		{
        			e.printStackTrace();
        		}  		
        	}
    		else
    		{
    			productInt++;
    			afleveren = false;
    			routeUitvoeren();
    		}
    	}
    }

    @Override
    public void update(Observable model, Object string) {
        if (string.equals("robotGestart")) {
            if (((Model) model).isRobotGestart()) {
                producten = ((Model) model).getRoute();
                bppRobot = new BPProbot((Model) model);
                try
                {
                    controller = new Controller("COM3", this, bppRobot);	
                    controller.openConnection();
                }
                catch(SerialPortException e)
                {
                	e.printStackTrace();
                }
                bppRobot.setController(controller);
                
                routeUitvoeren();
            }
        }
    }
}
