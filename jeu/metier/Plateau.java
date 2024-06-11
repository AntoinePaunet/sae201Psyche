package jeu.metier;

public class Plateau 
{
	private Materiaux tabPlateau[][];
	private Materiaux tabPiece[];

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
}