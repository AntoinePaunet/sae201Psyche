package jeu.ihm;

import jeu.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

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

	private PanelBoutons panelBoutons          ;

	private JMenuItem    menuiOuvrir           ;
	private JMenuItem    menuiScenario         ;
	private JMenuItem    menuiQuitter          ;

	private FrameChoix	      frameChoix       ;
	private FrameModification frameModification;


	/**
	 * Constructeur de la frame de démarrage
	 * @param ctrl permet d'accéder au controleur dans les frames qui en découlent
	 */
	public FrameDemarrage( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setTitle   ("L'age de psyché");
		this.setSize    (700,650  );
		this.setLocation(0, 0             );
		this.setLayout(new FlowLayout());


		this.panelBoutons = new PanelBoutons(this.ctrl);
		
		// Création et ajout de la barre de menu
		JMenuBar  menuBar  = new JMenuBar(         );
		JMenu menuOuvrir   = new JMenu("Ouvrir"  );
		JMenu menuScenario = new JMenu("Scénario");
		JMenu menuQuitter  = new JMenu("Quitter" );

		this.menuiOuvrir        = new JMenuItem("Importer une carte" );
		this.menuiScenario      = new JMenuItem("Lancer un scénario" );
		this.menuiQuitter       = new JMenuItem("Quitter"            );

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

		this.menuiOuvrir.setAccelerator   (KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK) );
		this.menuiScenario.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK) );
		this.menuiQuitter.setAccelerator  (KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK) );

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
		/*
		// Syso pour confirmer l'action
		if ( e.getSource() instanceof JMenuItem )
			System.out.println ( ( (JMenuItem) e.getSource() ).getText() );
			*/
		
			// Importation des fichiers
			if( e.getSource() == this.menuiOuvrir )
			{
				String cheminFichier;
				JFileChooser fc = new JFileChooser();
				File chooserFile = new File(System.getProperty("user.dir") + "/jeu/src");
				
				try 
			{
				chooserFile = chooserFile.getCanonicalFile();
			} 
			catch (Exception i) 
			{
				/*
				// En cas d'erreur, imprimer le message d'erreur
				System.out.println(i.getMessage());
				// Utiliser le répertoire actuel par défaut
				*/
			}
			fc.setCurrentDirectory(chooserFile);
			
			int returnVal = fc.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				cheminFichier = fc.getSelectedFile().getAbsolutePath();
				try {
					this.ctrl.getEditionFichier().lectureFichier(cheminFichier, true);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Erreur d'entrée/sortie : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		// Lecture des scénarios
		if( e.getSource() == this.menuiScenario )
		{
			String cheminFichier;
			JFileChooser fc = new JFileChooser();
			File chooserFile = new File(System.getProperty("user.dir") + "/jeu/src");
			
			try 
			{
				chooserFile = chooserFile.getCanonicalFile();
			} 
			catch( Exception exp) {}
			
			fc.setCurrentDirectory(chooserFile);
			
			int returnVal = fc.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				this.ctrl.setEstScenar(true);
				this.ctrl.getEditionFichier().initTheme(1);
				this.frameChoix = new FrameChoix( this.ctrl );
				
				cheminFichier = fc.getSelectedFile().getAbsolutePath();
				try
				{
					Timer timerSpawn = new Timer(17, new ActionListener() //Vitesse de déplacement du mob
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if ( ctrl.getFrameDemarrage().getFrameChoix().getFrameJeu() != null  )
							{
								try {
									
									ctrl.getEditionFichier().lireScenario(1,0);
								} catch (IOException r) 
								{
									System.out.println(r.getStackTrace());
								}
							}
							
						}
					});
				}
				catch( Exception ex )
				{
					JOptionPane.showMessageDialog(this, "Erreur d'entrée/sortie : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	

		// Fermeture de l'application
		if ( e.getSource() == this.menuiQuitter )
			System.exit(0);


		// Gestion du bouton Jouer
		if( e.getSource() == this.panelBoutons.btnJouer )
		{
			if (this.panelBoutons.lstTheme.getSelectedItem() != null)
			{
				this.ctrl.setEstScenar(false);
				this.frameChoix = new FrameChoix( this.ctrl );
				this.ctrl.setEstJeu(true);
			}
			else
			{
				this.panelBoutons.lblErreur.setText("Veuillez choisir un thème.");
			}
			
		}

		// Gestion du bouton Modifier
		if( e.getSource() == this.panelBoutons.btnModifier )
		{
			if (this.panelBoutons.lstTheme.getSelectedItem() != null)
			{
				this.frameModification = new FrameModification( this.ctrl ); 
				this.ctrl.setEstJeu(false);
			}
			else
			{
				this.panelBoutons.lblErreur.setText("Veuillez choisir un thème.");
			}			
		}
	}

	/**
	 * Constructeur du panel contenant les boutons Jouer et Modifier
	 */
	public FrameChoix getFrameChoix(){return this.frameChoix;}

	/**
	 * Constructeur du panel contenant les boutons Jouer et Modifier
	 */
	public FrameModification getFrameModification(){return this.frameModification;}

	/**
	 * Classe correspondant au panel contenant les Boutons Jouer et Modifier
	 */
	public class PanelBoutons extends JPanel implements ItemListener
	{
		private JPanel  panelBtnJouer, panelBtnModifier ;
		private JButton btnJouer, btnModifier           ;
		private JLabel  lblTheme                        ;
		private JLabel  lblErreur                       ;
		private List    lstTheme                        ;

		private Controleur ctrl                         ;

	/**
	 * Constructeur du panel contenant les boutons Jouer et Modifier
	 */
		public PanelBoutons(Controleur ctrl)
		{
			this.setLayout(new GridLayout(6,1));
			this.ctrl = ctrl;

			// Création des composants;
			this.panelBtnJouer    = new JPanel();
			this.panelBtnModifier = new JPanel();

			this.btnJouer    = new JButton("Jouer"             );
			this.btnModifier = new JButton("Modifier une carte");
			this.lblTheme    = new JLabel ("Selection du thème");
			this.lstTheme    = new List   (                         );
			this.lblErreur   = new JLabel (""                  );


			for (String s : ctrl.getEditionFichier().lectureNomTheme())
			{
				lstTheme.add( s );
			}
			this.lstTheme.addItemListener(this);
				
		
			this.panelBtnModifier.add( this.btnModifier );
			this.panelBtnJouer.add   (this.btnJouer     );

			this.add(new JLabel("")       );
			this.add(this.panelBtnModifier);
			this.add(this.panelBtnJouer   );
			this.add(this.lblTheme        );
			this.add(this.lstTheme        );
			this.add(this.lblErreur       );
		}

		/**
		 * Methode qui permet de changer thème du jeu, en fonction de l'item selectionné dans lstTheme.
		 */
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				// Appeler initTheme avec l'index de l'élément sélectionné
				this.ctrl.getEditionFichier().initTheme(this.lstTheme.getSelectedIndex()+1);
				this.lblErreur.setText("");
			}
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