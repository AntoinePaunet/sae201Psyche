package jeu.metier;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Jeton
{
	private IRessource type;

	public Jeton(IRessource type)
	{
		this.type = type;
	}

	public IRessource getType()
	{
		return this.type;
	}

	public String toString()
	{
		String sRet = "";

		sRet = this.type.toString();
		return sRet;
	}
}