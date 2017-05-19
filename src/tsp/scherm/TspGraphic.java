package tsp.scherm;

import javax.swing.*;
import java.awt.*;

public class TspGraphic extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Order order = new Order();
	public boolean drawGrid = false;
	public boolean drawOrder = false;
	public boolean drawLines = false;
	
	public int i = 1;
	
	public TspGraphic()
	{
		this.setPreferredSize(new Dimension(750,455));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		setOpaque(false);
	}
	
	public void setOrder(Order o)
	{
		this.order = o;
	}
	
	public void resetGraphic()
	{
		i = 1;
		drawOrder = false;
		drawLines = false;
		repaint();
	}
	
	private void paintGrid(Graphics g)
	{
		g.setColor(Color.gray);
		g.drawLine(50, 15, 50, 440);
		g.drawLine(700, 15, 700, 440);
		g.drawLine(50, 15, 700, 15);
		g.drawLine(50, 440, 700, 440);
		
		g.drawLine(180, 15, 180, 440);
		g.drawLine(310, 15, 310, 440);
		g.drawLine(440, 15, 440, 440);
		g.drawLine(570, 15, 570, 440);
		
		g.drawLine(50, 100, 700, 100);
		g.drawLine(50, 185, 700, 185);
		g.drawLine(50, 270, 700, 270);
		g.drawLine(50, 355, 700, 355);
	}
	
	private void paintOrder(Graphics g)
	{
		for(Product p : order.getOrderList())
    	{
    		int x = 740 - (p.getX() * 130);
    		int y = 455 - (p.getY() * 85);
    		
    		if(p.getVisited())
    		{
    			g.setColor(Color.green);
    		}
    		else
    		{
    			g.setColor(Color.blue);
    		}
    		
    		g.fillOval(x, y, 50, 50);
    	}
	}
	
	private void paintLines(Graphics g)
	{		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(5));
		
		for(int o = 1; o < i; o++)
		{
			int x1 = 740 - (order.getOrderList().get(o).getX() * 130) + 25;
			int y1 = 455 - (order.getOrderList().get(o).getY() * 85) + 25;
			
			int x2 = 740 - (order.getOrderList().get(o - 1).getX() * 130) + 25;
			int y2 = 455 - (order.getOrderList().get(o - 1).getY() * 85) + 25;
			
			g2.drawLine(x1, y1, x2, y2);			
		}
		i++;
	}
	
    @Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		this.setBackground(Color.WHITE);
		
		if(drawGrid){paintGrid(g);}
		if(drawOrder){paintOrder(g);}
		if(drawLines){paintLines(g);}		
	}
}
