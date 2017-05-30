package robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import applicatie.Model;
import domeinmodel.Product;
import jssc.SerialPortException;
import robot.tsp.Controller;

public class RobotControl implements Observer {

    private BPProbot bppRobot;
    private List<Product> producten;
    private Model model;
    Controller controller;
    int productInt = 0;
    boolean afleveren = false;
    private boolean klaar = false;

    public RobotControl(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public void routeUitvoeren() {
        try {
            controller.sendCommand("s" + producten.get(productInt).getX() + producten.get(productInt).getY());
            System.out.println(producten.get(productInt).getX() + "," + producten.get(productInt).getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextProduct() {
        
        if ((productInt + 1) > (producten.size() - 1) && !klaar) {
            System.out.println("Klaar met het order");
            controller.sendCommand("bpp");
//            model.setRobotGestart(false);
            klaar = true;
        } else if (!klaar){
            model.setTSPindex(productInt);
            if ((productInt + 1) % 3 == 0 && !afleveren) {
                try {
                    controller.sendCommand("bpp");
                    System.out.print("bpp order");
                    afleveren = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
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
                producten = new ArrayList<>(((Model) model).getRoute());
                if (producten.get(0).getNaam() == null) {
                    producten.remove(0);
                }
                System.out.print(producten);
                bppRobot = new BPProbot((Model) model);
                try {
                    controller = new Controller("COM3", this, bppRobot, this.model);
                    controller.openConnection();
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
                bppRobot.setController(controller);

                routeUitvoeren();
            }
        }
    }
}
