package robot.tsp;

import jssc.SerialPortList;

public class PortFinder {
    public static String[] portNames = SerialPortList.getPortNames();
}