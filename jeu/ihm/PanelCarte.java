package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Route;
import jeu.metier.Sommet;

import javax.swing.JPanel;

import java.awt.*;
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


		this.addMouseListener( new GereSouris() );
	}


	/**
	 * Méthode de dessins
	 * @param g l'instance de Graphics2D utilisée sur ce panel
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		String nomP1 = "P1" , nomP2 = "P2";

		System.out.println(this.ctrl.getTabSommet().get(0));

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



	private class GereSouris extends MouseAdapter
	{
		private Sommet villeChoisie ;
		private Route routeChoisie;

		private Point ptSmt1;
		private Point ptSmt2;

		public void mousePressed( MouseEvent e)
		{

			for(Route r : ctrl.getTabRoute())
			{
				this.ptSmt1 = new Point(r.getSommetDep().getX(), r.getSommetDep().getY());
				this.ptSmt2 = new Point(r.getSommetArr().getX(), r.getSommetArr().getY());

				if(isNearLine(e.getPoint()))
				{
					System.out.println("Clic");
				}
			}
		}


		private boolean isNearLine(Point p) {
			// Calculer la distance entre le point de clic et la ligne
			double distance = distanceARoute(p, ptSmt1, ptSmt2);
			return distance <= 5; //5px de tolérance
		}

		// Calculer la distance entre un point et un segment de ligne
		private double distanceARoute(Point p, Point v, Point w)
		{
			double l2 = v.distanceSq(w);
			if (l2 == 0.0) return p.distance(v);
			double t = Math.max(0, Math.min(1, resultPoint(p, v, w) / l2));
			Point projection = new Point((int) (v.x + t * (w.x - v.x)), (int) (v.y + t * (w.y - v.y)));
			return p.distance(projection);
		}

		private double resultPoint(Point p, Point v, Point w)
		{
			return (p.x - v.x) * (w.x - v.x) + (p.y - v.y) * (w.y - v.y);
		}
	}





}