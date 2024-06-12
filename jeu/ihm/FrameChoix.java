package jeu.ihm;

import jeu.Controleur;
import jeu.ihm.*;
import javax.swing.*;

/**
 * Cette classe gère la fenetre du menu principal du jeu.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class FrameChoix extends JFrame
{
	private PanelChoixJoueur panelChoixJoueur;
	private Controleur ctrl;

	/**
	 * Constructeur de la frame de Choix du joueur
	 * @param ctrl le Controleur qui lance le programme
	 */
	public FrameChoix(Controleur ctrl)
	{
		this.setTitle   ("Choix des joueurs et de leur plateau");
		this.setSize    ( 700,200 );
		this.setLocation(  300, 300 );

		this.ctrl = ctrl;


		// Création et ajout du Panel
		this.panelChoixJoueur = new PanelChoixJoueur(this);
		this.add(this.panelChoixJoueur);

		
		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Méthode de lancement des FrameJoueur correspondant à leur choix
	 */
	public void creerFrameJoueur()
	{
		new FrameJoueur(this.panelChoixJoueur.getText1(), 1, ctrl);
		new FrameJoueur(this.panelChoixJoueur.getText2(), 2, ctrl);
		this.dispose();
	}
/*
	public static void main( String[] args )
	{
		new FrameChoix();
	}
*/
}