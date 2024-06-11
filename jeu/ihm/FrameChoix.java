package jeu.ihm;

import javax.swing.*;
import java.awt.event.*;


public class FrameChoix extends JFrame
{
	private PanelChoixJoueur panelChoixJoueur;


	public FrameChoix()
	{
		this.setTitle("Choix des joueurs et de leur plateau");
		this.setSize    ( 450,200 );
		this.setLocation(  300, 300 );


		// Création et ajout du Panel
		this.panelChoixJoueur = new PanelChoixJoueur();
		this.add(this.panelChoixJoueur);

		
		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);


	}

	public static void main( String[] args )
	{
		Joueur[] joueurs = {null, null};
		new FrameChoix(joueurs);
	}
}
