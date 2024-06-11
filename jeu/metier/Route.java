package jeu.metier;

public class Route
{
	private Mine mineSommet;
	private int  mineSection;

	public Route ( Mine mineSommet, int mineSection )
	{
		this.mineSommet  = mineSommet;
		this.mineSection = mineSection;
	}

	public Mine getMineSommet  () { return this.mineSommet;  }
	public int  getMineSection () { return this.mineSection; }

	public void setMineSommet  ( Mine mineSommet  ) { this.mineSommet = mineSommet;   }
	public void setMineSection ( int  mineSection ) { this.mineSection = mineSection; }

}