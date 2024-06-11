package jeu.ihm;

import javax.swing.*;
import java.awt.event.*;

public class FrameJeu extends JFrame
{
	private PanelCarte panelCarte;
	private JPanel     panelScore;

	private JMenuItem enregistrerF;
	private JMenuItem supprimerF;

	public FrameJeu()
	{
		this.setTitle("L'Âge de Psyché");
		this.setSize    ( 1920,1080 );
		this.setLocation(  0, 0 );
		//this.ctrl = ctrl;


		// Création et ajout du Panel
		this.panelCarte = new PanelCarte();
		this.add(this.panelCarte);


		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);


	}

	public static void main( String[] args )
	{
		new FrameJeu();
	}
}