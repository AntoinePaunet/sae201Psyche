import java.util.ArrayList;
import java.util.Arrays;

public class Materiaux implements IRessource
{
	private String 	nom;
	private Couleur couleur;

	private int x;
	private int y;

	public static int nbPiece;

	//Permet de vérifier qu'on génère un minerais qui existe bel et bien.
	public static final ArrayList<String> verif = new ArrayList<>(Arrays.asList("AU", "AG", "FE", "AL", "NI", "PT", "CO", "TI", "NR"));


	/*
	La classe matériaux permet de générer un nouveau minerai ou de la monnaie et permet ainsi de le positionner sur la
	frame avec ses coordonnées.
	@param String nom donne le nom scientifique du minerai / monnaie
	@param int x donne la coordonnée x;
	@param int y donne la coordonnée y;
	 */

	public Materiaux(String nom, int x, int y)
	{
		if(!Materiaux.verif.contains(nom))
		{
			return;
		}

		this.nom 	= nom;
		this.x 		= x;
		this.y 		= y;

		switch (this.nom)
		{
			case "AU" : this.couleur = Couleur.OR; 		break;
			case "AG" : this.couleur = Couleur.GRIS; 	break;
			case "FE" : this.couleur = Couleur.JAUNE; 	break;
			case "AL" : this.couleur = Couleur.PALE; 	break;
			case "NI" : this.couleur = Couleur.BLEU; 	break;
			case "PT" : this.couleur = Couleur.VIOLET; 	break;
			case "CO" : this.couleur = Couleur.MARRON; 	break;
			case "TI" : this.couleur = Couleur.VERT; 	break;
			case "NR" : this.couleur = Couleur.CIAN; 	break;
		}

		Materiaux.nbPiece++;
	}

	public Couleur getCouleur()
	{
		return this.couleur;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}



	public String toString()
	{
		return this.nom;
	}
}