package jeu.ihm;

import javax.swing.*;

import jeu.Controleur;

import java.awt.*;
import java.awt.event.*;
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

	private PanelReseau panelReseau;
	private PanelJouer panelJouer;

	private JMenuItem     menuiOuvrir       ;
	private JMenuItem     menuiScenario     ;
	private JMenuItem     menuiQuitter      ;



	public FrameDemarrage( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setTitle   ("L'age de psyché");
		this.setSize    (1500,850  );
		this.setLocation(50, 50             );
		this.setVisible (true                 );

		this.panelJouer  = new PanelJouer(      );
		this.panelReseau = new PanelReseau(     );


		// Création et ajout de la barre de menu
		JMenuBar  menuBar  = new JMenuBar(       );
		JMenu menuOuvrir   = new JMenu("Ouvrir"  );
		JMenu menuScenario = new JMenu("Scénario");
		JMenu menuQuitter  = new JMenu("Quitter" );

		this.menuiOuvrir        = new JMenuItem("Importer des données");
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
		this.add(this.panelReseau,BorderLayout.CENTER);
		this.add(this.panelJouer, BorderLayout.EAST );


		// Activation des composants
		this.menuiOuvrir        .addActionListener( this );
		this.menuiQuitter       .addActionListener( this );
		this.panelJouer.btnJouer.addActionListener( this );
		this.menuiScenario      .addActionListener( this );


		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed ( ActionEvent e )
	{
		// Syso pour confirmer l'action
		if ( e.getSource() instanceof JMenuItem )
			System.out.println ( ( (JMenuItem) e.getSource() ).getText() );

		
		// Importation des fichiers
		if( e.getSource() == this.menuiOuvrir )
		{
			JFileChooser fc = new JFileChooser();

			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				this.panelReseau.changerFond(fc.getSelectedFile().getAbsolutePath());
			}
			else
			{
				System.out.println("Annuler");
			}
			
		}

		if( e.getSource() == this.panelJouer.btnJouer )
			new FrameChoix( this.ctrl );


		// Fermeture de l'application
		if ( e.getSource() == this.menuiQuitter )
			System.exit(0);
	}

	public PanelReseau getPanelR()
	{
		return this.panelReseau;
	}


	public class PanelReseau extends JPanel
	{

		private JPanel panelAjout;
		private JPanel panelCarte;

		public PanelReseau ( )
		{

			this.setLayout(new BorderLayout(0,0));

			// création des composants;
			this.panelAjout = new JPanel ();
			this.panelCarte = new JPanel ();


			// positionnement des composants
			this.add(panelAjout, BorderLayout.NORTH );
			this.add(panelCarte, BorderLayout.CENTER);

			this.changerFond("/images/bgSyndicat.png");

		}
		public void changerFond(String cheminAbsolu)
		{
			//Mise en place du fond

			ImageIcon backgroundImage = new ImageIcon(cheminAbsolu);

			JLabel backgroundLabel = new JLabel(backgroundImage);

			backgroundLabel.setBounds(0, 0, this.panelCarte.getWidth(), this.panelCarte.getHeight());

			this.panelCarte.add(backgroundLabel);
		}

	}


	public class PanelJouer extends JPanel
	{
		private JButton btnJouer;

		public PanelJouer()
		{
			this.setLayout(new BorderLayout(0,0));

			// création des composants;
			this.btnJouer = new JButton("Jouer");
			this.add(this.btnJouer);

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

