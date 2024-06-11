package routes.vue;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

public class PanelCarte extends JPanel
{
	private final int RAYON = 30;
	private Graphics2D g2;

	public PanelCarte()
	{
		this.setBackground( new Color( 909090 ));
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		String nomP1 = "P1" , nomP2 = "P2";

		int x1 = 100, y1 = 200; 
		int x2 = 500, y2 = 700;

		int adjCercle = this.RAYON / 2 ;
		// Dessiner l'ensemble des figures
		this.g2.setStroke (new BasicStroke (2.0f));

		this.g2.setColor( new Color(000060) );

		this.g2.drawOval( x1 , y1 , this.RAYON, this.RAYON );
		this.g2.drawOval( x2 , y2 , this.RAYON, this.RAYON );

		this.g2.fillOval( x1 , y1 , this.RAYON, this.RAYON );
		this.g2.fillOval( x2 , y2 , this.RAYON, this.RAYON );


		this.g2.drawLine(x1 + adjCercle, y1 + adjCercle, x2 + adjCercle, y2 + adjCercle);

		this.g2.drawString( nomP1, x1-10, y1-5 );
		this.g2.drawString( nomP2, x2-10, y2-5 );
		}

}