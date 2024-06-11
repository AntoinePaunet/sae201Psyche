package jeu.metier;

public class Route
{
	private Mine mineDep;
	private Mine mineArr;
	private int  nbTroncons;

	public Route ( Mine mineDep, Mine mineArr, int nbTroncons  )
	{
		this.mineDep    = mineDep;
		this.mineArr    = mineArr;
		this.nbTroncons = nbTroncons;
	}

	public Mine getMineDep   () { return mineDep;    }
	public int  getNbTroncons() { return nbTroncons; }
	public Mine getMineArr   () { return mineArr;    }

	public void setMineDep   ( Mine mineDep   ) { this.mineDep = mineDep;       }
	public void setMineArr   ( Mine mineArr   ) { this.mineArr = mineArr;       }
	public void setNbTroncons( int nbTroncons ) { this.nbTroncons = nbTroncons; }

}