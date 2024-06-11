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
	private Mine mineSommet;
	private Mine mineArrive;
	private int  mineSection;

	public Route ( Mine mineSommet, Mine mineArrive, int mineSection )
	{
		this.mineSommet  = mineSommet;
		this.mineSection = mineSection;
		this.mineArrive=mineArrive;
		this.mineSommet.addRoute(this);
		this.mineArrive.addRoute(this);
	}

	public Mine getMineSommet  () { return this.mineSommet;  }
	public int  getMineSection () { return this.mineSection; }
	public Mine getMineArrive  () { return this.mineArrive;  }

	public void setMineSommet  ( Mine mineSommet  ) { this.mineSommet = mineSommet;   }
	public void setMineSection ( int  mineSection ) { this.mineSection = mineSection; }

}