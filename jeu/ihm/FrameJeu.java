package jeu.ihm;

import javax.swing.*;
import java.awt.event.*;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Nahel KOCHAT,			IUT du Havre
 * @author Anas AARAB Vauthier,		IUT du Havre
 * @author Louis THOMAZEAU-AGUILLO,	IUT du Havre
 * @version 1.0 , 2024-05-23
 */
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