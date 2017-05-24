/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.tsp;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jssc.SerialPortException;

/**
 *
 * @author carlo
 */
public class Manual extends JPanel {

    private JButton rechts = new JButton();
    private JButton links = new JButton();
    private JButton omhoog = new JButton();
    private JButton omlaag = new JButton();
    private JButton achteruit = new JButton();
    private JButton vooruit = new JButton();
    private JButton optillen = new JButton();
    private JButton zakken = new JButton();
    private JButton basis = new JButton();
    private JButton test = new JButton();

    private JLabel jLabel1  = new javax.swing.JLabel();
    private JLabel jLabel2  = new javax.swing.JLabel();
    private JLabel jLabel3  = new javax.swing.JLabel();
    private JLabel jLabel4  = new javax.swing.JLabel();

    public Manual() {



        setVisible(true);
                

        jLabel1.setText("Niet aangesloten");
        if (PortFinder.portNames.length > 0) {
            jLabel1.setText(PortFinder.portNames[0]);
        }

        jLabel2.setText("Niet aangesloten");
        if (PortFinder.portNames.length == 2) {
            jLabel2.setText(PortFinder.portNames[1]);

        }

        jLabel3.setText("Arduino 1:");

        jLabel4.setText("Arduino 2:");

        rechts.setText("rechts");
        rechts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechtsActionPerformed(evt);
            }
        });

        links.setText("links");
        links.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linksActionPerformed(evt);
            }
        });

        omhoog.setText("omhoog");
        omhoog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                omhoogActionPerformed(evt);
            }
        });

        omlaag.setText("omlaag");
        omlaag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                omlaagActionPerformed(evt);
            }
        });

        achteruit.setText("achteruit");
        achteruit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                achteruitActionPerformed(evt);
            }
        });

        vooruit.setText("vooruit");
        vooruit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vooruitActionPerformed(evt);
            }
        });

        optillen.setText("optillen");
        optillen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optillenActionPerformed(evt);
            }
        });

        zakken.setText("zakken");
        zakken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zakkenActionPerformed(evt);
            }
        });

        basis.setText("basis");
        basis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basisActionPerformed(evt);
            }
        });

        test.setText("test");
        test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testActionPerformed(evt);
            }
        });
//
//        
//        
//        
//        
                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(this);
        setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(omhoog)
                    .addComponent(omlaag)
                    .addComponent(links))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rechts)
                    .addComponent(vooruit)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(achteruit)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zakken)
                            .addComponent(optillen)
                            .addComponent(basis)
                            .addComponent(test))))
                .addContainerGap(388, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(omhoog)
                    .addComponent(vooruit))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(omlaag)
                            .addComponent(achteruit)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(optillen)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zakken)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(basis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(test)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rechts)
                    .addComponent(links))
                .addGap(32, 32, 32))
        );
//        
//        
//        
        
        
    }

    private void omlaagActionPerformed(java.awt.event.ActionEvent evt) {                                       
        System.out.println("verplaats omlaag");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("omlaag");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                      

    private void omhoogActionPerformed(java.awt.event.ActionEvent evt) {                                       
        System.out.println("verplaats omhoog");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("omhoog");

        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                      

    private void vooruitActionPerformed(java.awt.event.ActionEvent evt) {                                        
        System.out.println("arm vooruit");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("vooruit");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                       

    private void achteruitActionPerformed(java.awt.event.ActionEvent evt) {                                          

        System.out.println("arm achteruit");

        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("achteruit");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                         

    private void rechtsActionPerformed(java.awt.event.ActionEvent evt) {                                       
        System.out.println("rechts bewegen");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("rechts");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                      

    private void linksActionPerformed(java.awt.event.ActionEvent evt) {                                      
        System.out.println("links bewegen");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("links");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                     

    private void optillenActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.out.println("optillen");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("optillen");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                        

    private void zakkenActionPerformed(java.awt.event.ActionEvent evt) {                                       
        System.out.println("zakken");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM3");
            transporter.testCommand("zakken");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                      

    private void basisActionPerformed(java.awt.event.ActionEvent evt) {                                      
        System.out.println("basis");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM6");
            transporter.testCommand("basis");
            transporter = new Transporter("COM3");
            transporter.testCommand("basis");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                                     

    private void testActionPerformed(java.awt.event.ActionEvent evt) {                                     
        System.out.println("test");
        Transporter transporter = null;
        try {
            transporter = new Transporter("COM6");
            transporter.testCommand("test");
            transporter = new Transporter("COM3");
            transporter.testCommand("test");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }                
    
    
    
}
