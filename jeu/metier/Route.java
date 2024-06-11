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


	/**
	 * Constructeur
	 *
	 * @param SommetDep  mine de départ
	 * @param SommetAr  mine d'arrive
	 * @param SecSommet nombre de section
	 * 
	 * 
	 */
	public Route ( Sommet SommetDep, Sommet SommetAr, int SecSommet )
	{
		this.sommetDep  = sommetDep;
		this.secSommet = secSommet;
		this.sommetAr=sommetAr;
		this.sommetDep.addRoute(this);
		this.sommetAr.addRoute(this);
	}

<<<<<<< Updated upstream
	public Sommet getSommetDep  () { return this.sommetDep;  }
	public int  getSecSommet () { return this.secSommet; }
	public Sommet getSommetAr  () { return this.sommetAr;  }

	public void setSecSommet  ( Sommet sommetDep  ) { this.sommetDep = sommetDep;   }
	public void setSommetAr   ( int  secSommet ) { this.secSommet = secSommet; }
=======
	/**
	 * 
	 * @return retourne le sommet
	 * 
	 */
	public Mine getMineSommet  () { return this.mineSommet;  }

	/**
	 *
	 * @return retourne le nombre de section 
	 * 
	 */
	public int  getMineSection () { return this.mineSection; }
	public Mine getMineArrive  () { return this.mineArrive;  }

	/**
	 *
	 * @params prend en paramétre la mine de départ pour l'affercter a la routes
	 * 
	 */
	public void setMineSommet  ( Mine mineSommet  ) { this.mineSommet = mineSommet;   }

	/**
	 *
	 * @params affecte le nombre de section 
	 * 
	 */	
	public void setMineSection ( int  mineSection ) { this.mineSection = mineSection; }
>>>>>>> Stashed changes

}