package jeu.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameDemarrage extends JFrame implements ActionListener
{
	private PanelReseau panelReseau;

	private JMenuItem     menuiAjouterSommet;
	private JMenuItem     menuiAjouterArrete;
	private JMenuItem     menuiOuvrir       ;
	private JMenuItem     menuiQuitter      ;
	private JMenuItem     menuiScenario     ;

	private JButton btnJouer;

	public FrameDemarrage ()
	{
		this.setTitle   ("L'age de psyché");
		this.setSize    (1040,950         );
		this.setLocation(50, 50           );
		this.setVisible (true             );


		// Création et ajout de la barre de menu
		JMenuBar  menuBar  = new JMenuBar(       );
		JMenu menuAjouter  = new JMenu("Ajouter" );
		JMenu menuOuvrir   = new JMenu("Ouvrir"  );
		JMenu menuQuitter  = new JMenu("Quitter" );
		JMenu menuScenario = new JMenu("Scénario");


		this.menuiAjouterSommet = new JMenuItem("Ajouter un sommet"   );
		this.menuiAjouterArrete = new JMenuItem("Ajouter une arrête"  );
		this.menuiOuvrir        = new JMenuItem("Importer des données");
		this.menuiQuitter       = new JMenuItem("Quitter"             );
		this.menuiScenario      = new JMenuItem("Lancer un scénario"  );

		menuAjouter .add(this.menuiAjouterSommet);
		menuAjouter .add(this.menuiAjouterArrete);
		menuOuvrir  .add(this.menuiOuvrir       );
		menuQuitter .add(this.menuiQuitter      );
		menuScenario.add(this.menuiScenario     );

		menuBar.add(menuAjouter );
		menuBar.add(menuOuvrir  );
		menuBar.add(menuQuitter );
		menuBar.add(menuScenario);

		this.setJMenuBar( menuBar );
		


		// Création et ajout du bouton Jouer
		this.btnJouer = new JButton("Jouer");

		// Activation des composants
		this.menuiAjouterSommet.addActionListener ( this );
		this.menuiAjouterArrete.addActionListener ( this );
		this.menuiOuvrir       .addActionListener ( this );
		this.menuiQuitter      .addActionListener ( this );
		this

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

			/*
			if (returnVal == JFileChooser.APPROVE_OPTION)
				this.ctrl.setFichierImage(fc.getSelectedFile().getAbsolutePath());
			else
				System.out.println("Annuler");
			*/
		}



		// Fermeture de l'application
		if ( e.getSource() == this.menuiQuitter )
			System.exit(0);
	}

	public PanelReseau getPanel()
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

            //Mise en place du fond
			/* 
            ImageIcon backgroundImage = new ImageIcon(this.getClass().getResource("/images/backGround.jpg"));

            JLabel backgroundLabel = new JLabel(backgroundImage);

            backgroundLabel.setBounds(0, 0, this.panelCarte.getWidth(), this.panelCarte.getHeight());

            this.panelCarte.add(backgroundLabel);
			*/
        }
    }

	public static void main (String[]args)
	{
		new FrameDemarrage();
	}
}

