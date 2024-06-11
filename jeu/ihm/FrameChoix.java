package jeu.ihm;

import javax.swing.*;


public class FrameChoix extends JFrame
{
	private PanelChoixJoueur panelChoixJoueur;


	public FrameChoix()
	{
		this.setTitle   ("Choix des joueurs et de leur plateau");
		this.setSize    ( 700,200 );
		this.setLocation(  300, 300 );


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
		new FrameJoueur(this.panelChoixJoueur.getText1(), 1);
		new FrameJoueur(this.panelChoixJoueur.getText2(), 2);
		this.dispose();
	}

	public static void main( String[] args )
	{
		new FrameChoix();
	}

}
