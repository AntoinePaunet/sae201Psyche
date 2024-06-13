package jeu.metier;

import jeu.metier.*;
import java.util.ArrayList;

/**
 * Cette classe permet d'instancier les joueurs 
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Nahel KOCHAT,			IUT du Havre
 * @author Anas AARAB Vauthier,		IUT du Havre
 * @author Louis THOMAZEAU-AGUILLO,	IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Joueur 
{
	private Materiaux[][] tabPlateau;
	private Materiaux[] tabPiece;
	private ArrayList<JetonPossession> tabJetonPossession;
	private int 		nbJetonsUtiliser;
	private ArrayList<Sommet> tabSommetRecup;

	private int score ;
	private String detailScore;

	private String nomJoueur;

	/**
	 * Constructeur de Joueur.
	 */
	public Joueur()
	{
		this.nomJoueur = "default";
		this.tabPlateau = new Materiaux [4][8];
		this.tabPiece   = new Materiaux [8];
		this.tabJetonPossession = new ArrayList<JetonPossession>();
		this.tabSommetRecup = new ArrayList<Sommet>();
		this.nbJetonsUtiliser=0;
	}

	/**
	 * Méthode permettant de simuler l'utilisation d'un JetonPossession du joueur.
	 */
	public void utiliserUnJetons () {this.nbJetonsUtiliser++;}

	/**
	 * Méthode permettant de modifier le nom du joueur.
	 * @param nom le nom du joueur
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
	public int getScore()
	{
		return this.score;
	}

	/**
	 * Méthode qui calcule le score du joueur.
	 */
	public void score()
	{
		int[] scoresCol = {20,10,0,2           };
		int[] scoresLig = {0,4,9,16,25,36,49,64};
		//Affichage des détails
		int score, scoreMonnaie, scoreCol, scoreLig;
		String detail = "Detail :\n ";


		//Compteur pour le score des Monnaies
		scoreMonnaie = 0;
		for (int i = 1; i < this.tabPiece.length; i++)
		{
			if (this.tabPiece[i] != null)
				scoreMonnaie = (i+1)*(i+1);
		}

		detail += "Monnaies      : " + scoreMonnaie + " pt \n ";

		score = scoreMonnaie;


		//Compteur pour le score des colonnes
		int cptCol = 0;
		
		while (cptCol < this.tabPlateau[0].length) // 0 - 7
			for (int i = 0 ; i < this.tabPlateau.length; i++) // 0 - 3
			{
				scoreCol = 0;
				if  (this.tabPlateau[i][cptCol] != null)
					scoreCol=scoresLig[i];
				detail += "Colonne " + (cptCol+1) + "   : " + String.format("%2d", scoreCol) + " pt\n ";
				score += scoreCol;

				cptCol++;
			}


		//Compteur pour le score des lignes
		int cptRessource= 0;
		int cptLig 		= 1;
		int cptPieceLig = 0;
		

		for(int i = 0; i < this.tabPlateau.length ; i++) //0 - 3
		{
			scoreLig = 0;
			for(int j = 0 ; j < this.tabPlateau[i].length ; j++) //0 - 7
			{
				if(this.tabPlateau[i][j] != null)
					cptRessource++;
			}

			scoreLig += scoresLig[cptRessource];
			detail += "Ligne   " + (i + 1) + "   : " + String.format("%2d", scoreLig) + " pt\n ";
			score  += scoreLig;

			cptRessource = 0;
		}

		this.score = score;
		this.detailScore = detail;

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