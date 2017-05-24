package robot.tsp;

import jssc.*;

import java.util.Scanner;


public class Controller implements SerialPortEventListener {

    private Scanner ob;
    private SerialPort serialPort;

    public Controller(String portDescription) throws SerialPortException {
        serialPort = new SerialPort(portDescription);
        openConnection();
    }

    public void openConnection() {
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE,
                    true, false);
            serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
            Thread.sleep(1);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("new connection");
    }

    public void closeConnection() {
        try {
            serialPort.closePort();
            System.out.println("connection closed");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String input) {
        try {
            serialPort.writeString(input);
            Thread.sleep(1);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void startTestCommand(String input) {
//        ob = new Scanner(System.in);
//        String input = ob.nextLine();
//        while(input != "n") {
//            try {
//                serialPort.writeString(input);
//            } catch (SerialPortException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println("command");
//                input = ob.nextLine();
//            }
//        }
//        ob.close();
//        String input = "test";
        try {
            serialPort.writeString(input);
            closeConnection();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    public void sendRoute(int[] route) {
        //geef route aan arduino
    }

//    public String getState() throws NullPointerException {
//        String state = null;
//        try {
//            serialPort.writeString("state");
//            Thread.sleep(2000);
//            state = serialPort.readString();
//        } catch (SerialPortException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("command");
//
//        }
//        return state;
//    }
    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                String receivedData = serialPort.readString(event.getEventValue());
                System.out.println("Received response from port: " + receivedData);
                Thread.sleep(200);
            } catch (SerialPortException ex) {
                System.out.println("Error in receiving response from port: " + ex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
