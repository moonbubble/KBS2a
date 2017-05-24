/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.tsp;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author carlo
 */
public class SerialConnection {

    private static SerialPort serialPort;


    public SerialConnection() {

        serialPort = new SerialPort("COM4");

        try {
            // opening port
            serialPort.openPort();

            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
                    | SerialPort.FLOWCONTROL_RTSCTS_OUT);

            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

        } catch (SerialPortException ex) {
            System.out.println("Error in writing data to port: " + ex);
        }
    }

//     receiving response from port
    private static class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {

                    String receivedData = serialPort.readString(event.getEventValue());

                    System.out.println(receivedData);
                    if (receivedData.equals("zzzz")) {

                        Transporter transporter = null;
                        try {
                            transporter = new Transporter("COM6");
                            transporter.testCommand("kraanStop");
                            System.out.println("zwart gezien");

                        } catch (SerialPortException e) {
                            e.printStackTrace();
                        }

                    }

                } catch (SerialPortException ex) {
                    System.out.println("Error in receiving response from port: " + ex);
                }
            }
        }
    }
}


