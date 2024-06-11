package jeu;

import java.util.ArrayList;

import jeu.ihm.*;
import jeu.metier.*;

public class Controleur 
{
	private Plateau j1;
	private Plateau j2;
	private ArrayList<Mine> tabMine;

	public Controleur()
	{
		this.j1      = new Plateau ();
		this.j2      = new Plateau ();
		this.tabMine = new ArrayList<Mine>();
	}


	public void initMine()
	{
		String tmpCoul = "";
		int    tmpZone = -1;

		String[] tabMine = {"J1","J5","J2","J3","J4","B2","B3","B4","B6","B8","G0","G1","G2","G3","G4","V2","V3","V4","V6","V8","R1","R2","R3","R4","R5","M1","M2","M3","M4","M5"};

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

	public static void main (String[] arg)
	{

		new Controleur();
	}
}