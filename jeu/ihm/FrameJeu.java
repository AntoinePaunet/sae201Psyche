package jeu.ihm;

import jeu.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Cette classe créé la Frame sur laquelle les joueurs vont jouer. Elle affiche le plateau et tous les pions des joueurs.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class FrameJeu extends JFrame implements ActionListener
{
	private PanelCarte 	panelCarte;
	private JPanel     	panelScore;

	private JMenuItem menuiSauvegarder;
	private JMenuItem menuiAbandonner;

	private Controleur 	ctrl;

	/**
	 * Constructeur de la Frame du jeu
	 */
	public FrameJeu(Controleur ctrl)
	{
		this.setTitle("L'Âge de Psyché");
		this.setSize    ( 1000,750 );
		this.setLocation(  0, 0 );
		this.ctrl = ctrl;


		// Création et ajout du Panel
		this.panelCarte = new PanelCarte(ctrl);
		this.add(this.panelCarte);

		// Création et ajout de la barre de menu
		JMenuBar menuBar  = new JMenuBar();
		JMenu menuOptions = new JMenu("Options");

		this.menuiSauvegarder = new JMenuItem ("Sauvegarder et quitter");
		this.menuiAbandonner  = new JMenuItem ("Abandonner" );

		menuOptions.add(this.menuiSauvegarder);
		menuOptions.add(this.menuiAbandonner );

		menuBar.add(menuOptions);

		this.setJMenuBar( menuBar );

		menuOptions.setMnemonic('O');

		this.menuiSauvegarder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK) );
		this.menuiAbandonner .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK) );

		// Gestion de la fermeture de la fenêtre
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				try {
					ctrl.getEditionFichier().sauvegarde();
					System.exit(0);	
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Ne ferme pas automatiquement
		this.setVisible(true);
	}

	/**
	 * Réalise une action lorsqu'on clique sur la menubar
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed ( ActionEvent e )
	{
		/*
		Syso pour confirmer l'action
		if ( e.getSource() instanceof JMenuItem )
			//System.out.println ( ( (JMenuItem) e.getSource() ).getText() );
		*/
		if ( e.getSource() == this.menuiSauvegarder )
			System.exit(0);	


		// Fermeture de l'application
		if ( e.getSource() == this.menuiAbandonner )
			System.exit(0);
	}

	public void majIHM(){this.panelCarte = new PanelCarte(ctrl);this.add(this.panelCarte);this.setVisible(true);}

	public PanelCarte getPanelCarte (){return this.panelCarte;}


	public static void main( String[] args ) throws IOException
	{
		new FrameJeu(new Controleur());
	}

}