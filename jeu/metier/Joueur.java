package jeu.metier;

import java.util.ArrayList;
import java.util.ResourceBundle.Control;
import java.util.concurrent.ConcurrentHashMap;
import jeu.Controleur;

/**
 * Cette classe permet d'instancier les joueurs 
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB Vauthier,		IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Joueur 
{
	private static final int NB_MAX_JETON_POSSESSION = 25;

	private Materiaux[][] tabPlateau;
	private Materiaux[] tabPiece;
	private ArrayList<JetonPossession> tabJetonPossession;
	private int 		nbJetonsUtiliser;
	private ArrayList<Sommet> tabSommetRecup;

	private int[] tabScore ;

	private String nomJoueur;
	private Controleur ctrl;

	/**
	 * Constructeur de Joueur.
	 */
	public Joueur( Controleur ctrl )
	{
		this.nomJoueur = "default";
		this.tabPlateau = new Materiaux [4][8];
		this.tabPiece   = new Materiaux [8];
		this.tabJetonPossession = new ArrayList<JetonPossession>();
		this.tabSommetRecup = new ArrayList<Sommet>();
		this.nbJetonsUtiliser=0;
		this.score = new int[11];
		this.ctrl = ctrl;
	}

	public void addJetons(int i)
	{
		this.nbJetonsUtiliser+=i;
	}

	public void reset()
	{
		this.nbJetonsUtiliser=0;
		this.tabSommetRecup = new ArrayList<Sommet>();

		ArrayList<Route> lstRoute = this.ctrl.getTabRoute();

		for ( Route r : lstRoute )
			if ( r.getJoueur().equals(this) )
				this.nbJetonsUtiliser += r.getNbTroncons();
	}

	public int getJetons()
	{
		return this.nbJetonsUtiliser;
	}
	/**
	 * Méthode permettant de simuler l'utilisation d'un JetonPossession du joueur.
	 */
	public void utiliserUnJetons ()
	{
		if( this.nbJetonsUtiliser < Joueur.NB_MAX_JETON_POSSESSION )
			this.nbJetonsUtiliser ++;
	}

	/**
	 * Retourne le nom du joueur.
	 * @return le nom du joueur
	 */
	public String getNomJoueur(){ return this.nomJoueur; }

	/**
	 * Méthode permettant de modifier le nom du joueur.
	 * @param nom le nom du joueur
	 */
	public void setNomJoueur(String nom){this.nomJoueur=nom;}

	/**
	 * Méthode permettant d'ajouter des matériaux au joueur sur son plateau.
	 * @param m le matériau a ajouter
	 * @return vrai si l'action à été effecctuée, faux si non.
	 */
	public boolean ajouterMateriaux(Materiaux m)
	{
		if (m.toString().equals("NR"))
		{
			this.ajouterPiece(m);
			return true;
		}


		for(int i = 0 ; i < this.tabPlateau[0].length ; i++)
		{
			if(this.tabPlateau[1][i] == null || this.tabPlateau[2][i] == null || this.tabPlateau[0][i] == null || this.tabPlateau[3][i] == null) //Il faut que les cases soient vides pour y placer les éléments
			{
				if(this.tabPlateau[3][i] != null && this.tabPlateau[3][i].getNom().equals(m.getNom())) //Si on a la même ressource que dans le tableau
				{
					for(int i2 = tabPlateau.length-1 ; i2 > -1 ; i2--) //On fait dans le sens inverse afin de placer les éléments de bas en haut dans le tableau
					{
						if(this.tabPlateau[i2][i] == null)
						{
							this.tabPlateau[i2][i] = m;
							return true;
						}
					}

				}
				else if(this.tabPlateau[3][i] == null) //Si la ressource n'est pas la même
				{
					this.tabPlateau[3][i] = m;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Renvoie le tableau de matériaux du joueur.
	 * @return le tableau de matériaux du joueur
	 */
	public Materiaux[][] getTableMateriaux()
	{
		return this.tabPlateau;
	}

	/**
	 * Renvoie le tableau de pieces du joueur.
	 * @return le tableau de pieces du joueur
	 */
	public Materiaux[] getTabPiece()
	{
		return this.tabPiece;
	}

	/**
	 * Méthode qui ajoute un matériau au joueur.
	 * @param m le matériau a ajouter
	 * @return vrai si l'action a été effectuée, faux sinon.
	 */
	public boolean ajouterPiece(Materiaux m)
	{
		for (int i=0; i<this.tabPiece.length; i++)
		{
			if (this.tabPiece[i]==null)
			{
				this.tabPiece[i]=m;
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode qui permet au joueur de récupérer un sommet. Il déclenche aussi la méthode d'ajout de matériau au joueur.
	 * @param s le sommet a récupérer
	 */
	public void addSommetRecup (Sommet s)
	{
		this.tabSommetRecup.add(s);
		this.ajouterMateriaux(s.prendreMateriaux());
	}

	public ArrayList<Sommet> getSommetRecup ()
	{
		return this.tabSommetRecup;
	}

	/**
	 * Méthode qui permet de donner des JetonPossession au joueur.
	 * @param j le jeton a ajouter
	 */
	public void addJetonPossession(JetonPossession j)
	{
		this.tabJetonPossession.add(j);
	}

	/**
	 * Renvoie les JetonPossession du joueur.
	 * @return les JetonPossession du joueur
	 */
	public ArrayList<JetonPossession> getTabJetonPossession ()
	{
		return this.tabJetonPossession;
	}

	/**
	 * Méthode qui enleve un JetonPossession du joueur afin de simuler son utilisation.
	 */
	public void enleverJetonsPossession ()
	{
		this.tabJetonPossession.remove(0);
	}


	/**
	 * Renvoie le score du joueur.
	 * @return le score du joueur
	 */
	public int[] getTabScore()
	{
		return this.tabScore;
	}

	/**
	 * Méthode qui augmente le score du joueur lorsqu'il prend une Route.
	 */
	public void ajouterScoreRoute(int score)
	{
		this.tabScore[10] += score;
	}


	/**
	 * Méthode qui calcule le score du joueur à la fin de la partie.
	 */
	public void scoreFin()
	{
		int[] scoresLig = {0,4,9,16,25,36,49,64};
		//Affichage des détails
		int  scoreMonnaie, scoreCol, scoreLig;


		//Compteur pour le score des Monnaies
		scoreMonnaie = 0;
		for (int i = 1; i < this.tabPiece.length; i++)
		{
			if (this.tabPiece[i] != null)
				scoreMonnaie = (i+1)*(i+1);
		}

		this.tabScore[0] = scoreMonnaie;


		//Compteur pour le score des colonnes
		int cptCol = 0;
		
		while (cptCol < this.tabPlateau[0].length) // 0 - 7
			for (int i = 0 ; i < this.tabPlateau.length; i++) // 0 - 3
			{
				scoreCol = 0;
				if  (this.tabPlateau[i][cptCol] != null)
					scoreCol=scoresLig[i];

				this.tabScore[1] += scoreCol;

				cptCol++;
			}


		//Compteur pour le score des lignes
		int cptRessource= 0;		

		for(int i = 0; i < this.tabPlateau.length ; i++) //0 - 3
		{
			scoreLig = 0;
			for(int j = 0 ; j < this.tabPlateau[i].length ; j++) //0 - 7
			{
				if(this.tabPlateau[i][j] != null)
					cptRessource++;
			}

			scoreLig += scoresLig[cptRessource];
			this.tabScore[2]  += scoreLig;

			cptRessource = 0;
		}

		tabScore[3]=this.nbJetonsUtiliser; // + les points qui viennent en récupérant la valeur sur un sommet

	}

	public void scoreSommet()
	{
		int temp = 0;

		String[] tempS = {"Vert", "Bleu", "Rouge", "Gris", "Jaune","Marron"};

		for (int i=0; i < 6; i++)
		{
			temp = 0;
			for(int j=0; j < this.tabSommetRecup.size(); j++)
			{
				if (this.tabSommetRecup.get(j).getNomCoul().equals(tempS[i]))
					if (this.tabSommetRecup.get(j).getNumSom()>temp)
						temp=this.tabSommetRecup.get(j).getNumSom();
				
			}
			this.tabScore[4+i]=temp;
		}
	}

	public int getScorePiece     () { return this.getTabScore()[0] ; }
	public int getScoreColonne   () { return this.getTabScore()[1] ; }
	public int getScoreLigne     () { return this.getTabScore()[2] ; }
	public int getScorePointJeton() { return this.getTabScore()[3] ; }
	public int getScoreVert      () { return this.getTabScore()[4] ; }
	public int getScoreBleu      () { return this.getTabScore()[5] ; }
	public int getScoreRouge     () { return this.getTabScore()[6] ; }
	public int getScoreGris      () { return this.getTabScore()[7] ; }
	public int getScoreJaune     () { return this.getTabScore()[8] ; }
	public int getScoreMarron    () { return this.getTabScore()[9] ; }
	public int getScoreRoute	 () { return this.getTabScore()[10]; }

	public int getSommeScore     ()
	{
		int totalScore = 0;
		for (int i = 0; i < this.getTabScore().length ; i++)
			totalScore += this.getTabScore()[i];
		return totalScore;
	}

	public static int getNbMaxJetonsPossession()
	{
		return Joueur.NB_MAX_JETON_POSSESSION;
	}

	/**
	 * Renvoie le nom du Joueur sous forme texte.
	 * @return le nom du Joueur
	 */
	public String toString ()
	{
		return this.nomJoueur;
	}
}