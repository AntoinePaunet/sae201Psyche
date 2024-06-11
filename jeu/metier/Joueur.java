package jeu.metier;

public class Joueur
{


	

	/*
	La classe joueur permet de créer un nouveau joueur.
	@param String nom donne son nom au joueur
	@param FrameJoueur plateauIndividuel permet d'acceder au plateau du joueur 
	 */
	/*private FrameJoueur plateauIndividuel;*/

	private String nomJoueur;

	public Joueur(/*FrameJoueur plateauIndividuel,*/ String nomJoueur)
	{

		/*this.plateauIndividuel = plateauIndividuel;*/
		this.nomJoueur = nomJoueur;

	}

	public String getNomJoueur        () { return this.nomJoueur        ; }
	/*public String getPlateauIndividuel() { return this.plateauIndividuel; }*/
	

}