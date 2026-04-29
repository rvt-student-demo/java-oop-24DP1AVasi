package gui;

import javax.swing.JPanel;
import java.awt.Graphics;

public class House extends JPanel{

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(400, 200, 200, 200);
		g.drawLine(400,200,500, 100);
		g.drawLine(500,100,600, 200);
		g.drawOval(475, 135, 50, 50);
	}
}