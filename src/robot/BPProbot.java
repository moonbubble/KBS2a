package robot;

import java.util.ArrayList;
import java.util.List;

import applicatie.Model;
import domeinmodel.Doos;
import domeinmodel.Product;
import domeinmodel.Util;
import robot.tsp.Controller;

public class BPProbot {

    private List<Doos> dozen;
    private int inhoudDoos0 = 0;
    private int inhoudDoos1 = 0;
    private Model model;
    private Controller controller;
    private List<Product> producten;
    private int productInt = 0;
    private String string = "";

    public BPProbot(Model model) {
        this.model = model;
        this.dozen = model.getDozen();
        List<Product> route = model.getRoute();
//        System.out.println("route: " + route);
        if (route.get(0).getNaam() == null) {
        	route.remove(0);
        }
        
        this.producten = new ArrayList<>(Util.wisselArray(route));
//        System.out.println("Producten: " + producten);
    }

    public void setController(Controller c) {
        this.controller = c;
    }

    public void bepaalPlaats() {
        Product productTSP = null;
        if (productInt < producten.size()) {
            productTSP = producten.get(productInt);
            for (int i = 0; i < dozen.size(); i++) {
                Doos doos = dozen.get(i);
                for (int j = 0; j < doos.getProducten().size(); j++) {
                    Product product = doos.getProducten().get(j);
                    if (productTSP.equals(product)) {
                        if (i % 2 == 0) {
                            plaatsProduct(0);
                            inhoudDoos0 += product.getGrootte();
                            if (inhoudDoos0 == doos.getInhoud()) {
                                knipperLampje(0);
                                inhoudDoos0 = 0;
                            }
                        } else if (i % 2 == 1) {
                            plaatsProduct(1);
                            inhoudDoos1 += product.getGrootte();
                            if (inhoudDoos1 == doos.getInhoud()) {
                                knipperLampje(1);
                                inhoudDoos1 = 0;
                            }
                        }
                        model.setIndexen(i, j);
                        controller.sendCommand(string);
                        string = "";

                    }
                }
            }
            productInt++;
        }

    }

    private void knipperLampje(int doosVol) {
        if (doosVol == 0) {
            // controller.sendCommand("ll");
            string += "ll";
        } else if (doosVol == 1) {
            // controller.sendCommand("lr");
            string += "lr";
        }
    }

    public void plaatsProduct(int richting) {
        if (richting == 0) {
            // controller.sendCommand("bl");
            string += "bl";
        } else if (richting == 1) {
            // controller.sendCommand("br");
            string += "br";
        }
    }
}
