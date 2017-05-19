package tspScherm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Enumeration;

public class TspScherm extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private TspGraphic jpGraphic;
	private JPanel jpStatistics;
	private JLabel JLstatistics;
	private JLabel jlStatisticsText;
	private String StatisticsText;
	private JPanel jpButtons;
	private JButton jbStart;
    private JButton jbStop;
    private JButton jbReset;
    private JButton jbOrder;
    private JButton jbInstellingen;
	private JPanel jpOutput;
	private JLabel jlOutput;
	private JLabel jlOutputText;
	private String OutputText;
	private JPanel jpAlgorithms;    
	private ButtonGroup radioButtons;
    private JRadioButton JRBneighbour;
    private JRadioButton JRBrandom;
    private JRadioButton JRBexchange;
    private JRadioButton JRBannealing;
    
    private Order order = new Order();   
    private Timer timer;
    
    public TspScherm()
    {
        setTitle("TSP simulator");
        this.setResizable(false);
        this.setPreferredSize(new Dimension(1020,750));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        jpGraphic = new TspGraphic();
        add(jpGraphic);
        jpGraphic.drawGrid = true;
        jpGraphic.drawOrder = false;
        jpGraphic.drawLines = false;
        
        jpStatistics = new JPanel();
        jpStatistics.setPreferredSize(new Dimension(250,455));
        jpStatistics.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
        jpStatistics.setLayout(null);
        add(jpStatistics);
        
	        JLstatistics = new JLabel("Statistics");
	        JLstatistics.setFont(new Font("Roboto", Font.BOLD, 20));
	        JLstatistics.setBounds(5, 0, 240, 35);
	        jpStatistics.add(JLstatistics);
	        
	        StatisticsText = "dit is een random test regel <br> Dit is nog een text regel";
	        
	        jlStatisticsText = new JLabel("<html>" + StatisticsText + "</html>", JLabel.LEFT);
	        jlStatisticsText.setVerticalAlignment(JLabel.TOP);
	        jlStatisticsText.setBounds(5, 35, 240, 420);
	        jpStatistics.add(jlStatisticsText);
        
        jpButtons = new JPanel();
        jpButtons.setPreferredSize(new Dimension(1000, 45));
        jpButtons.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
        jpButtons.setLayout(null);
        add(jpButtons);
        
	        jbStart = new JButton("Start");
	        jbStart.setBackground(new Color(20,200,50));
	        jbStart.setBorder(BorderFactory.createLineBorder(Color.black));
	        jbStart.setFocusPainted(false);
	        jbStart.addActionListener(this);
	        jbStart.setBounds(5, 5, 100, 35);
	        jpButtons.add(jbStart);
	        
	        jbStop = new JButton("Stop");
	        jbStop.setBackground(new Color(250,0,0));
	        jbStop.setBorder(BorderFactory.createLineBorder(Color.black));
	        jbStop.setFocusPainted(false);
	        jbStop.addActionListener(this);
	        jbStop.setBounds(110, 5, 100, 35);
	        jpButtons.add(jbStop);
	        
	        jbReset = new JButton("Reset");
	        jbReset.setBackground(new Color(100,100,255));
	        jbReset.setBorder(BorderFactory.createLineBorder(Color.black));
	        jbReset.setFocusPainted(false);
	        jbReset.addActionListener(this);
	        jbReset.setBounds(215, 5, 100, 35);
	        jpButtons.add(jbReset);
	        
	        jbOrder = new JButton("Order");
	        jbOrder.setBorder(BorderFactory.createLineBorder(Color.black));
	        jbOrder.setFocusPainted(false);
	        jbOrder.addActionListener(this);
	        jbOrder.setBounds(790, 5, 100, 35);
	        jpButtons.add(jbOrder);
	        
	        jbInstellingen = new JButton("Instellingen");
	        jbInstellingen.setBorder(BorderFactory.createLineBorder(Color.black));
	        jbInstellingen.setFocusPainted(false);
	        jbInstellingen.addActionListener(this);
	        jbInstellingen.setBounds(895, 5, 100, 35);
	        jpButtons.add(jbInstellingen);
        
        jpOutput = new JPanel();
        jpOutput.setPreferredSize(new Dimension(750, 200));
        jpOutput.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        jpOutput.setLayout(null);
        add(jpOutput);
        
	        jlOutput = new JLabel("Output:");
	        jlOutput.setBounds(5, 0, 190, 35);
	        jlOutput.setFont(new Font("Roboto", Font.BOLD, 20));
	        jpOutput.add(jlOutput);
	        
	        OutputText = "dit is een random test regel <br> Dit is nog een text regel";
	        
	        jlOutputText = new JLabel("<html>" + OutputText + "</html>", JLabel.LEFT);
	        jlOutputText.setVerticalAlignment(JLabel.TOP);
	        jlOutputText.setBounds(5, 35, 190, 165);
	        jpOutput.add(jlOutputText);
        
        jpAlgorithms = new JPanel();
        jpAlgorithms.setPreferredSize(new Dimension(250, 200));
        jpAlgorithms.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
        jpAlgorithms.setLayout(null);
        add(jpAlgorithms);
        
	        JRBrandom = new JRadioButton("Random path");
	        JRBrandom.setBounds(0, 5, 200, 40);
	        JRBrandom.setFont(new Font("Roboto", Font.BOLD, 15));
	        JRBrandom.addActionListener(this);
	        JRBrandom.setSelected(true);
	        jpAlgorithms.add(JRBrandom);
        
	        JRBneighbour = new JRadioButton("Nearest neighbour");
	        JRBneighbour.setBounds(0, 45, 200, 40);
	        JRBneighbour.setFont(new Font("Roboto", Font.BOLD, 15));
	        JRBneighbour.addActionListener(this);
	        jpAlgorithms.add(JRBneighbour);
	        
	        JRBexchange = new JRadioButton("Brute force");
	        JRBexchange.setBounds(0, 85, 200, 40);
	        JRBexchange.setFont(new Font("Roboto", Font.BOLD, 15));
	        JRBexchange.addActionListener(this);
	        jpAlgorithms.add(JRBexchange);
	        
	        JRBannealing = new JRadioButton("Simulated annealing");
	        JRBannealing.setBounds(0, 125, 200, 40);
	        JRBannealing.setFont(new Font("Roboto", Font.BOLD, 15));
	        JRBannealing.addActionListener(this);
	        jpAlgorithms.add(JRBannealing);
        
        radioButtons = new ButtonGroup();
        radioButtons.add(JRBrandom);
        radioButtons.add(JRBneighbour);
        radioButtons.add(JRBexchange);
        radioButtons.add(JRBannealing);
                
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    private String getSelectedRadioButton(ButtonGroup bg)
    {
    	for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource().equals(jbInstellingen))
    	{
    		TspInstellingen tspInstellingen = new TspInstellingen(this);
    		tspInstellingen.setLocationRelativeTo(null);
    		tspInstellingen.setVisible(true);
    		tspInstellingen.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    	}
    	else if(e.getSource().equals(jbOrder))
    	{
    		TspOrder TspOrder = new TspOrder(this);
    		TspOrder.setLocationRelativeTo(null);
    		TspOrder.setVisible(true);
    		TspOrder.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    		
    		order = TspOrder.getOrder();
    		jpGraphic.setOrder(order);
    		jpGraphic.drawOrder = true;
    		jpGraphic.drawLines = false;
    		repaint();
    	}
    	else if(e.getSource().equals(jbStart))
    	{
    		order.resetOrderList();
    		Collections.sort(order.getOrderList(), new MyComparator());
    		System.out.println(order.getOrdernr());
    		if(getSelectedRadioButton(radioButtons) == "Random path")
    		{
    			RandomPath algoritme = new RandomPath(order.getOrderList());
    			order.setOrderList(algoritme.algoritme());
    		}
    		else if(getSelectedRadioButton(radioButtons) == "Nearest neighbour")
    		{
    			
    			NearestNeighbour algoritme = new NearestNeighbour(order.getOrderList());
    			order.setOrderList(algoritme.algoritme());
    			
    		}
    		else if(getSelectedRadioButton(radioButtons) == "Brute force")
    		{
    			BruteForce algoritme = new BruteForce(order.getOrderList());
    			order.setOrderList(algoritme.algoritme());
    		}
    		else if(getSelectedRadioButton(radioButtons) == "Simulated annealing")
    		{
    			
    		}
    		order.getOrderList().get(0).Visited();
    		jpGraphic.setOrder(order);
    		jpGraphic.drawLines = true;
    		jpGraphic.i = 1;
    		repaint();
    		
    		ActionListener actionListener = new ActionListener()
    		{
    			int i = 1;
    			
    			@Override
                public void actionPerformed(ActionEvent actionEvent)
    			{
    				order.getOrderList().get(i).Visited();
    				jpGraphic.setOrder(order);
    				repaint();
    				i++;
                    if(i >= order.getOrderList().size()){timer.stop();}
                    
                }
            };
            timer = new Timer(TspInstellingen.snelheid, actionListener);
            timer.start();	
    	}
    	else if(e.getSource().equals(jbReset))
    	{
    		jpGraphic.resetGraphic();
    		timer.stop();
    		this.order = null;
    	}
    }
}
