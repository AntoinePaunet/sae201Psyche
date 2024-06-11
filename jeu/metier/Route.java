package jeu.metier;

/**
 * Cette classe d'instansié des routes 
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Route
{
	private Sommet sommetDep;
	private Sommet sommetAr;
	private int  secSommet;

	private Joueur joueur;


	public Route ( Sommet SommetDep, Sommet SommetAr, int SecSommet )


	/**
	 * Constructeure
	 *
	 * @param mineSommet  mine de départ
	 * @param mineArrive  mine d'arrive
	 * @param mineSection nombre de section
	 * 
	 * 
	 */
	
	{
		this.sommetDep  = sommetDep;
		this.secSommet = secSommet;
		this.sommetAr=sommetAr;
		this.sommetDep.addRoute(this);
		this.sommetAr.addRoute(this);
		this.joueur=null;
	}


	public Sommet getSommetDep  () { return this.sommetDep;  }
	public int  getSecSommet () { return this.secSommet; }
	public Sommet getSommetAr  () { return this.sommetAr;  }

	public void setSecSommet  ( Sommet sommetDep  ) { this.sommetDep = sommetDep;   }
	public void setSommetAr   ( int  secSommet ) { this.secSommet = secSommet; }

	public void setJoueur (Joueur j) {this.joueur = j;}


}