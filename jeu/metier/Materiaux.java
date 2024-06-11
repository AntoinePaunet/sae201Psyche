package jeu.metier;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Nahel KOCHAT,			IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @author Louis THOMAZEAU-AGULLO,	IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Materiaux implements IRessource
{
	private String 	nom;
	private Couleur couleur;


	public static int nbPiece;

	//Permet de vérifier qu'on génère un minerais qui existe bel et bien.
	public static final ArrayList<String> verif = new ArrayList<>(Arrays.asList("AU", "AG", "FE", "AL", "NI", "PT", "CO", "TI", "NR"));


	/*
	La classe matériaux permet de générer un nouveau minerai ou de la monnaie et permet ainsi de le positionner sur la
	frame avec ses coordonnées.
	@param String nom donne le nom scientifique du minerai / monnaie
	@param int x donne la coordonnée x;
	@param int y donne la coordonnée y;
	 */

	public Materiaux(String nom)
	{
		if(!Materiaux.verif.contains(nom))
		{
			System.out.println("oui");
			return;
		}

		this.nom 	= nom;

		switch (this.nom)
		{
			case "AU" : this.couleur = Couleur.OR; 		break;
			case "AG" : this.couleur = Couleur.GRIS; 	break;
			case "FE" : this.couleur = Couleur.JAUNE; 	break;
			case "AL" : this.couleur = Couleur.PALE; 	break;
			case "NI" : this.couleur = Couleur.BLEU; 	break;
			case "PT" : this.couleur = Couleur.VIOLET; 	break;
			case "CO" : this.couleur = Couleur.MARRON; 	break;
			case "TI" : this.couleur = Couleur.VERT; 	break;
			case "NR" : this.couleur = Couleur.CIAN; 	break;
		}

		Materiaux.nbPiece++;
	}

	public Couleur getCouleur()
	{
		return this.couleur;
	}

	public String getNom()
	{
		return this.nom;
	}

	public String toString()
	{
		return this.nom;
	}
}