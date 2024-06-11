package jeu.metier;

public class JetonPossession
{
	private Plateau joueur ;

	private int x;
	private int y;

	public JetonPossession (Plateau joueur)
	{
		this.joueur=joueur;
	}

	public void setX (int x) {this.x = x;}
	public void setY (int y) {this.y = y;}

	public int getX() {return this.x ; }
	public int getY() {return this.y; }
}