package jeu.ihm;

import jeu.Controleur;
import jeu.ihm.*;
import javax.swing.*;


public class FrameChoix extends JFrame
{
	private PanelChoixJoueur panelChoixJoueur;
	private Controleur ctrl;

	public FrameChoix(Controleur ctrl)
	{
		this.setTitle   ("Choix des joueurs et de leur plateau");
		this.setSize    ( 550,200 );
		this.setLocation(  300, 300 );

		this.ctrl = ctrl;


		// Création et ajout du Panel
		this.panelChoixJoueur = new PanelChoixJoueur(this);
		this.add(this.panelChoixJoueur);

		
		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// Crée le plateau des joueurs et ferme cette fenêtre
	public void creerFrameJoueur()
	{
		new FrameJoueur(this.panelChoixJoueur.getText1(), 1, ctrl);
		new FrameJoueur(this.panelChoixJoueur.getText2(), 2, ctrl);
		this.dispose();
	}


}
