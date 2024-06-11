package jeu;

import java.util.ArrayList;

import jeu.ihm.*;
import jeu.metier.*;

public class Controleur 
{
	private Plateau j1;
	private Plateau j2;
	private ArrayList<Mine> 		tabMine;
	private ArrayList<Materiaux> 	tabMateriaux;

	public Controleur()
	{
		this.j1      		= new Plateau ();
		this.j2      		= new Plateau ();
		this.tabMine 		= new ArrayList<>(30);
		this.tabMateriaux 	= new ArrayList<>(40);

		this.initMateriaux();
	}


	private void init()
	{
		String tmpCoul = "";
		int    tmpZone = -1;

		String[] tabMine = {"J1","J5","J2","J3","J4","B2","B3","B4","B6","B8","G0","G1","G2","G3","G4","V2","V3","V4","V6","V8","R1","R2","R3","R4","R5","M1","M2","M3","M4","M5"};
		int[] 		tabCooX = new int[]{	 336,  265,  317,  394,	 251,  336,	 414,  104,  156,  257,  510,  308,  346,  440,  575,  648,  111,  185,  353,  576,  696,  774,  200,  330,  427,  501,  606,  556,  696,  773};
		int[] 		tabCooY = new int[]{	  92,  111,  187,  175,	 239,  284,  270,  322,  298,  322,  295,  382,  339,  366,  337,  319,  456,  440,  428,  433,  442,  443,  517,  542,  541,  519,  512,  586,  583,  582};


		for ( String mine : tabMine )
		{
			switch ( mine.substring( 0, 0 ) )
			{
				case "J" -> tmpCoul = "Jaune";
				case "B" -> tmpCoul = "Bleu";
				case "G" -> tmpCoul = "Gris";
				case "V" -> tmpCoul = "Vert";
				case "R" -> tmpCoul = "Rouge";
				case "M" -> tmpCoul = "Marron";
			}

			tmpZone = Integer.parseInt( mine.substring( 1, 1 ) );
			this.tabMine.add( new Mine( tmpZone, tmpCoul ) );
		}
	}


	private void initMateriaux()
	{
		String[] 	tabNom 	= new String[]{	"FE", "PT", "AG", "NR", "CO", "AL", "NI", "NI", "AL", "AU", "TI", "AG", "NR", "AU", "FE", "PT", "TI", "AG", "NR", "CO", "AU", "FE", "NR", "AL", "NI", "AG", "TI", "FE", "NR", "PT"};



		for(int i = 0; i < tabNom.length ; i++)
		{
			this.tabMateriaux.add( new Materiaux(tabNom[i]) );
		}

		System.out.println(this.tabMateriaux.size());
	}

	public static void main (String[] arg)
	{
		new Controleur();
	}
}