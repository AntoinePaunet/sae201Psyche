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
	private int  troncons;

	private Joueur joueur;

	/**
	 * Constructeur
	 *
	 * @param sommetDep  mine de départ
	 * @param mineArrive  mine d'arrive
	 * @param mineSection nombre de section
	 *
	 *
	 */

	public Route ( Sommet sommetDep, Sommet sommetAr, int troncons )
	{
		this.sommetDep  = sommetDep;
		this.troncons = troncons;
		this.sommetAr=sommetAr;
		this.sommetDep.addRoute(this);
		this.sommetAr.addRoute(this);
		this.joueur=null;
	}


	public Sommet getSommetDep  () { return this.sommetDep;  }
	public int  getNbTroncons () { return this.troncons; }
	public Sommet getSommetArr() { return this.sommetAr;  }


	public void setSecSommet  ( Sommet sommetDep  ) { this.sommetDep = sommetDep;   }
	public void setSommetAr   ( int  troncons ) { this.troncons = troncons; }

	public void setJoueur (Joueur j) {this.joueur = j;}
	public Joueur getJoueur() {return this.joueur;}

	public String toString()
	{
		return "\n"+this.sommetDep+"\n--------\n"+this.sommetAr+"    ("+this.troncons+" )"+ "   p:"+this.joueur+"\n";
	}


}