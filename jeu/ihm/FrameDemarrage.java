package jeu.ihm;

import jeu.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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

	private JMenuItem    menuiOuvrir           ;
	private JMenuItem    menuiScenario         ;
	private JMenuItem    menuiQuitter          ;
	private PanelBoutons panelBoutons          ;
	private PanelFond	 panelFond			   ;

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
		this.setSize    (554,508  );
		this.setLocation(700, 0             );

		this.panelFond    = new PanelFond();
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

		this.add(panelFond);

		panelFond.add(this.panelBoutons);



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

	public class PanelFond extends JPanel
	{
		private BufferedImage image;
		private Graphics2D g2;

		public PanelFond()
		{
			this.setLayout(new FlowLayout());
			try
			{
				this.image = ImageIO.read(new File("jeu/src/images/fond_accueil.jpg"));
			}
			catch( IOException e ) { e.printStackTrace(); }
		}
	
		/**
		* Méthode de dessins
		* @param g l'instance de Graphics2D utilisée sur ce panel
		*/
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			this.g2 = (Graphics2D) g;
			g2.drawImage(this.image, 0,0, this);
		}
	}

		

	/**
	 * Réalise une action lorsqu'on clique sur la menubar
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed ( ActionEvent e )
	{
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
				try
				{
					this.ctrl.getEditionFichier().lectureFichier(cheminFichier, true);
				}
				catch( IOException ex )
				{
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
				this.ctrl.setNbScenario(  Integer.parseInt( cheminFichier.substring( cheminFichier.indexOf( ".txt" ) - 1, cheminFichier.indexOf( ".txt" ) ) ) );
				
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
									
									ctrl.getEditionFichier().lireScenario(ctrl.getNbScenario(),ctrl.getNbEtapeScenario(),false);
									((Timer)e.getSource()).stop();

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
			if (this.panelBoutons.lstTheme.getSelectedItem() == null)
			{
				this.panelBoutons.lblErreur.setText("Veuillez choisir un thème.");
				this.panelBoutons.lblErreur.setOpaque(true);
			}
			else
			{
				this.ctrl.setEstScenar(false);

				if( !this.ctrl.getEstSauvegarde() )
				{
					String[] options ={ "Continuer la partie sauvegardée" , "Recommencer une nouvelle partie" };

					if( JOptionPane.showOptionDialog(null, "Une partie en cours est déjà sauvegardée. Voulez vous continuer cette partie, ou écraser cete sauvegarde et recommencer une nouvelle partie ?", "Sauvegarde détectée !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null) == JOptionPane.NO_OPTION )
					{
						try{ this.ctrl.reInit(); }
						catch( IOException ioe ){ ioe.printStackTrace(); }
					}
				}
				this.frameChoix = new FrameChoix( this.ctrl );
				this.ctrl.setEstJeu(true);
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
				this.panelBoutons.lblErreur.setOpaque(true);
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
		private JLabel  lblTheme, lblTitre              ;
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
			this.setOpaque(false);

			// Création des composants;
			this.panelBtnJouer    = new JPanel();
			this.panelBtnModifier = new JPanel();


			this.lblTitre    = new JLabel("L'ÂGE DE PSYCHE");
			this.btnJouer    = new JButton("Jouer"             );
			this.btnModifier = new JButton("Modifier une carte");
			this.lblTheme    = new JLabel ("Selection du thème");
			this.lstTheme    = new List   (                         );
			this.lblErreur   = new JLabel (""                  );

			this.lblTitre.setFont(new Font("Arial", Font.BOLD, 30));
			this.lblTitre.setOpaque(true);
			this.panelBtnJouer.setOpaque(false);
			this.panelBtnModifier.setOpaque(false);
			this.lblTheme.setOpaque(true);

			for (String s : ctrl.getEditionFichier().lectureNomTheme())
			{
				lstTheme.add( s );
			}
			this.lstTheme.addItemListener(this);
				
		
			this.panelBtnModifier.add( this.btnModifier );
			this.panelBtnJouer.add   ( this.btnJouer    );


			this.add(this.lblTitre		  );
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
				this.lblErreur.setOpaque(false);
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