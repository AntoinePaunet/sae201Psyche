package jeu.metier;

import jeu.metier.*;
import java.util.ArrayList;

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
public class Joueur 
{
	private Materiaux[][] tabPlateau;
	private Materiaux[] tabPiece;
	private ArrayList<JetonPossession> tabJetonPossession;
	private ArrayList<Mine> tabMineRecup;

	private int score ;
	private String detailScore;

	private String nomJoueur;

	public Joueur()
	{
		this.nomJoueur = "default";
		this.tabPlateau = new Materiaux [4][8];
		this.tabPiece   = new Materiaux [8];
		this.tabJetonPossession = new ArrayList<JetonPossession>();
		this.tabMineRecup = new ArrayList<Mine>();
	}


	public void setNomJoueur(String nom){this.nomJoueur=nom;}


	public boolean ajouterMateriaux(Materiaux m)
	{
		if (m.toString().equals("NR"))
		{
			this.ajouterPiece(m);
			return true;
		}

		for (int i=0; i<this.tabPlateau.length; i++)
		{
			if (this.tabPlateau[i][0]==null)
			{
				this.tabPlateau[i][0]=m;
				return true;
			}


			if (this.tabPlateau[i][0].toString().equals(m.toString()))
			{
				for (int j=0; j<this.tabPlateau[0].length; j++)
				{
					if (this.tabPlateau[i][j]==null)
					{
						this.tabPlateau[i][0]=m;
						return true;
					}
						
				}
			}
		}
		return false;
	}

	public Materiaux[][] getTableMateriaux()
	{
		return this.tabPlateau;
	}

	public Materiaux[] getTabPiece()
	{
		return this.tabPiece;
	}

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


	public void addMineRecup (Mine m)
	{
		this.tabMineRecup.add(m);
		this.ajouterMateriaux(m.getMateriaux());
	}

	//Ajouter des jetons
	public void addJetonPossession(JetonPossession j)
	{
		this.tabJetonPossession.add(j);
	}

	//récupérer les jetons
	public ArrayList<JetonPossession> getTabJetonPossession ()
	{
		return this.tabJetonPossession;
	}

	//enlever les jetons
	public void enleverJetonsPossession ()
	{
		this.tabJetonPossession.remove(0);
	}

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
}