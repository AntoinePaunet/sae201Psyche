package jeu.metier;

/**
 * Cette classe permet d'attribué a un joueur des jeton 
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class JetonPossession
{
	private Joueur joueur ;

	private int x;
	private int y;

	public JetonPossession (Joueur joueur)
	{
		this.joueur=joueur;
	}

	public void setX (int x) {this.x = x;}
	public void setY (int y) {this.y = y;}

	public int getX() {return this.x ; }
	public int getY() {return this.y; }
}