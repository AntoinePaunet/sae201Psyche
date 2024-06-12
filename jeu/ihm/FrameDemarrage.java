package jeu.ihm;

import jeu.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class FrameDemarrage extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private PanelBoutons panelBoutons;

	private JMenuItem     menuiOuvrir       ;
	private JMenuItem     menuiScenario     ;
	private JMenuItem     menuiQuitter      ;


	/**
	 * Constructeur de la frame de démarrage
	 * @param ctrl permet d'accéder au controleur dans les frames qui en découlent
	 */
	public FrameDemarrage( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setTitle   ("L'age de psyché");
		this.setSize    (800,750  );
		this.setLocation(0, 0             );
		this.setLayout(new FlowLayout());

		this.panelBoutons  = new PanelBoutons();


		// Création et ajout de la barre de menu
		JMenuBar  menuBar  = new JMenuBar(       );
		JMenu menuOuvrir   = new JMenu("Ouvrir"  );
		JMenu menuScenario = new JMenu("Scénario");
		JMenu menuQuitter  = new JMenu("Quitter" );

		this.menuiOuvrir        = new JMenuItem("Importer une carte");
		this.menuiScenario      = new JMenuItem("Lancer un scénario"  );
		this.menuiQuitter       = new JMenuItem("Quitter"             );

		menuOuvrir  .add(this.menuiOuvrir       );
		menuScenario.add(this.menuiScenario     );
		menuQuitter .add(this.menuiQuitter      );

		menuBar.add(menuOuvrir  );
		menuBar.add(menuScenario);
		menuBar.add(menuQuitter );

		this.setJMenuBar( menuBar );
		
		//Création et ajout du Panel Jouer
		this.add(this.panelBoutons);

		// Création des raccourcis clavier
		menuOuvrir.setMnemonic('O');
		menuScenario.setMnemonic('S');
		menuQuitter.setMnemonic('Q');


		this.menuiOuvrir.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK) );
		this.menuiScenario.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK) );
		this.menuiQuitter.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK) );

		// Activation des composants
		this.menuiOuvrir        .addActionListener( this );
		this.menuiQuitter       .addActionListener( this );
		this.menuiScenario      .addActionListener( this );
		this.panelBoutons.btnJouer.addActionListener( this );
		this.panelBoutons.btnModifier.addActionListener( this );

		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible (true);
		
	}

	/**
	 * Réalise une action lorsqu'on clique sur la menubar
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed ( ActionEvent e )
	{
		String cheminFichier;
		// Syso pour confirmer l'action
		if ( e.getSource() instanceof JMenuItem )
			System.out.println ( ( (JMenuItem) e.getSource() ).getText() );

		
		// Importation des fichiers
		if( e.getSource() == this.menuiOuvrir )
		{
			JFileChooser fc = new JFileChooser();
			File chooserFile = new File(System.getProperty("user.dir") + "/jeu/src");

			try 
			{
				chooserFile = chooserFile.getCanonicalFile();
			} 
			catch (Exception i) 
			{
            // En cas d'erreur, imprimer le message d'erreur
            System.out.println(i.getMessage());
            // Utiliser le répertoire actuel par défaut
        	
			}
			fc.setCurrentDirectory(chooserFile);

			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				cheminFichier = fc.getSelectedFile().getAbsolutePath();
				try {
					this.ctrl.lectureFichier(cheminFichier);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Erreur d'entrée/sortie : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else
			{
				System.out.println("Annuler");
			}
		}

		// Fermeture de l'application
		if ( e.getSource() == this.menuiQuitter )
			System.exit(0);
		

		// Gestion du bouton Jouer
		if( e.getSource() == this.panelBoutons.btnJouer )
			new FrameChoix( this.ctrl );

		
		// Gestion du bouton Modifier
		if( e.getSource() == this.panelBoutons.btnModifier )
			new FrameModification( this.ctrl ); 
		

		// Fermeture de l'application
		if ( e.getSource() == this.menuiQuitter )
			System.exit(0);
	}


	public class PanelBoutons extends JPanel
	{
		private JPanel panelBtnJouer, panelBtnModifier;
		private JButton btnJouer, btnModifier;

		public PanelBoutons()
		{
			this.setLayout(new GridLayout(2,1));

			// création des composants;
			this.panelBtnJouer = new JPanel();
			this.panelBtnModifier = new JPanel();

			this.btnJouer = new JButton("Jouer");
			this.btnModifier = new JButton("Modifier une carte");
		
			this.panelBtnModifier.add( this.btnModifier );
			this.panelBtnJouer.add(this.btnJouer);

			this.add(this.panelBtnModifier);
			this.add(this.panelBtnJouer);

			

		}
	}
/*
	public static void main (String[]args) throws IOException
	{
		Controleur ctrl = new Controleur();
		new FrameDemarrage(ctrl);
	}
*/
}

