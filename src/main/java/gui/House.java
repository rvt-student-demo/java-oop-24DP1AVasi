package gui;

import javax.swing.JPanel;
import java.awt.Graphics;

public class House extends JPanel{

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Frame of the house
		g.drawRect(400, 200, 200, 200);
		g.drawLine(400,200,500, 100);
		g.drawLine(500,100,600, 200);
		// Attic window
		g.drawOval(475, 135, 50, 50);
		g.drawLine(500,135,500,185);
		g.drawLine(475,160,525,160);
		// Door
		g.drawRect(430,290,50,110);
		g.drawOval(465, 340, 10, 10);
		// Groundfloor window
		g.drawRect(520,290,50,50);
		g.drawLine(545,290,545,340);
		g.drawLine(520,315,570,315);
	}
}