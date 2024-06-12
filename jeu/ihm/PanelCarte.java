package jeu.ihm;

import jeu.Controleur;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

/**
 * Cette classe correspond au Panel sur lequel est affiché la carte ainsi
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelCarte extends JPanel
{
	private final int RAYON = 30;
	private Graphics2D g2;
	private Controleur ctrl;

	/**
	 * Constructeur du panel
	 */
	public PanelCarte(Controleur ctrl)
	{
		this.setBackground( new Color( 909090 ));
		this.ctrl = ctrl;
	}

	/**
	 * Méthode de dessins
	 * @param g l'instance de Graphics2D utilisée sur ce panel
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		System.out.println("T");

		this.g2 = (Graphics2D) g;

		String nomP1 = "P1" , nomP2 = "P2";

		int x1 = this.ctrl.getTabSommet().get(0).getX(), y1 = this.ctrl.getTabSommet().get(0).getY();
		int x2 = ctrl.getTabSommet().get(1).getX(), y2 = ctrl.getTabSommet().get(1).getY();

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