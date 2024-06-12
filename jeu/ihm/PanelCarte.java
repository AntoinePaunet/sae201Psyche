package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Route;
import jeu.metier.Sommet;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	private BufferedImage image;


	/**
	 * Constructeur du panel
	 */
	public PanelCarte(Controleur ctrl)
	{
		try {
			this.image = ImageIO.read(new File("jeu/src/images/bgPlateau.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


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

		g2.drawImage(this.image, 20,0, this);


		this.g2.setStroke (new BasicStroke (2.0f));
		int adjCercle = this.RAYON / 2 ;
		this.g2.setColor( new Color(000060) );


		for(Sommet s : ctrl.getTabSommet())
		{
			if(s.getNomCoul() != null) //Si pas le départ
			{
				int x = s.getX();
				int y = s.getY();

				this.g2.setStroke (new BasicStroke (2.0f));

				this.g2.setColor( new Color(000060) );

				this.g2.drawOval( x , y , this.RAYON, this.RAYON );
				this.g2.fillOval( x , y , this.RAYON, this.RAYON );

				this.g2.drawString( s.getNumSom() + s.getNomCoul(), x-10, y-5 );
			}
		}

		for(Route r : ctrl.getTabRoute())
		{
			int x1 = r.getSommetDep().getX(), y1 = r.getSommetDep().getY();
			int x2 = r.getSommetArr().getX(), y2 = r.getSommetArr().getY();

			this.g2.drawLine(x1 + adjCercle, y1 + adjCercle, x2 + adjCercle, y2 + adjCercle);
		}
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
					ctrl.jouer(r);
				}
			}
		}


		private boolean isNearLine(Point p) {
			// Calculer la distance entre le point de clic et la ligne
			double distance = distanceARoute(p, ptSmt1, ptSmt2);
			return distance <= 25; //5px de tolérance
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