package jeu.metier;

public class Plateau 
{
	private Materiaux tabPlateau[][];
	private Materiaux tabPiece[];

	private int score ;
	private String detailScore;

	public Plateau()
	{
		this.tabPlateau = new Materiaux [4][8];
		this.tabPiece   = new Materiaux [8];
	}

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
				for (int j=0; j<this.tabPlateau[0].length; i++)
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