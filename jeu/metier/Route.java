package jeu.metier;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Nahel KOCHAT,			IUT du Havre
 * @author Anas AARAB Vauthier,		IUT du Havre
 * @author Louis THOMAZEAU-AGUILLO,	IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Route
{
	private Sommet sommetDep;
	private Sommet sommetAr;
	private int  secSommet;

	public Route ( Sommet SommetDep, Sommet SommetAr, int SecSommet )
	{
		this.sommetDep  = sommetDep;
		this.secSommet = secSommet;
		this.sommetAr=sommetAr;
		this.sommetDep.addRoute(this);
		this.sommetAr.addRoute(this);
	}

	public Sommet getSommetDep  () { return this.sommetDep;  }
	public int  getSecSommet () { return this.secSommet; }
	public Sommet getSommetAr  () { return this.sommetAr;  }

	public void setSecSommet  ( Sommet sommetDep  ) { this.sommetDep = sommetDep;   }
	public void setSommetAr   ( int  secSommet ) { this.secSommet = secSommet; }

}