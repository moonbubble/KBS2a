package robot.tsp;

import jssc.*;
public class Robot {

    private Controller controller;

    public Robot(String portDescription) throws SerialPortException {
        controller = new Controller(portDescription, null, null);
    }

    public void command(String input) {
        controller.sendCommand(input);
    }

    public void testCommand(String input) {
        controller.startTestCommand(input);
    }

    public void sendRoute(int[] route) {
        controller.sendRoute(route);
    }
}

