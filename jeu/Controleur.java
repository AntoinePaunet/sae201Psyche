package jeu;

import java.util.ArrayList;
import java.util.Arrays;

import jeu.ihm.*;
import jeu.metier.*;

public class Controleur 
{
	private Joueur j1;
	private Joueur j2;
	private ArrayList<Mine> 		tabMine;

	private boolean tourJ1;
	private boolean finPartie;

	public Controleur()
	{
		this.j1      		= new Joueur ();
		this.j2      		= new Joueur ();
		this.tabMine 		= new ArrayList<>(30);
		this.tourJ1=true;
		this.finPartie = false;

		this.init();
		new FrameChoix();

		
	}


	private void init()
	{
		String tmpCoul = "";
		int    tmpZone = -1;
		int rndm;
		Materiaux tmpMat;

		String[] 	tabNomMine = new String[] {"J1", "J5", "J2", "J3", "J4", "B2", "B3", "B4", "B6", "B8", "G0", "G1", "G2", "G3", "G4", "V2", "V3", "V4", "V6", "V8", "R1", "R2", "R3", "R4", "R5", "M1", "M2", "M3", "M4", "M5"};
		int[] 		tabCooX    = new int[]    { 336,  265,  317,  394,	 251,  336,	 414,  104,  156,  257,  510,  308,  346,  440,  575,  648,  111,  185,  353,  576,  696,  774,  200,  330,  427,  501,  606,  556,  696,  773};
		int[] 		tabCooY    = new int[]    { 92,  111,  187,  175,	 239,  284,  270,  322,  298,  322,  295,  382,  339,  366,  337,  319,  456,  440,  428,  433,  442,  443,  517,  542,  541,  519,  512,  586,  583,  582};


		String[] tabNomsMat = new String[]{ "FE", "AL", "AU", "TI", "AG", "CO", "NI", "PT" };

		ArrayList<String> tmpLst = new ArrayList<>(Arrays.asList("NR", "NR", "NR", "NR", "NR", "NR", "NR", "NR"));


		//génération du tableau contenant les noms des matériaux possibles
		for(int i = 0 ; i < tabNomsMat.length ; i++)
		{
			for(int j = 0; j < 4 ; j++)
			{
				tmpLst.add(tabNomsMat[i]);
			}
		}


		//Génération des mines
		for(int cpt = 0; cpt < tabNomMine.length ; cpt++)
		{
			switch ( tabNomMine[cpt].substring( 0, 1 ) )
			{
				case "J" -> tmpCoul = "Jaune";
				case "B" -> tmpCoul = "Bleu";
				case "G" -> tmpCoul = "Gris";
				case "V" -> tmpCoul = "Vert";
				case "R" -> tmpCoul = "Rouge";
				case "M" -> tmpCoul = "Marron";
			}

			rndm = (int)(Math.random()*(40-cpt));

			tmpMat = new Materiaux(tmpLst.remove(rndm));

			tmpZone = Integer.parseInt( tabNomMine[cpt].substring( 1, 2 ) );
			this.tabMine.add( new Mine( tmpZone, tmpCoul, tabCooX[cpt], tabCooY[cpt], tmpMat, false ) );
		}

		this.tabMine.add( new Mine(0, null, 442, 475, null, true));
		System.out.println(this.tabMine);
	}

	private void initJetonPossession()
	{
		for (int i=0; i<25; i++)
		{
			this.j1.addJetonPossession(new JetonPossession(j1));
			this.j2.addJetonPossession(new JetonPossession(j2));
		}
	}

	public static void main (String[] arg)
	{
		new Controleur();
	}
}