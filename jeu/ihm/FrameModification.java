package jeu.ihm;

import jeu.Controleur;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Cette classe créé la Frame permettant aux joueurs de changer la carte de jeu.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class FrameModification extends JFrame implements ActionListener
{
	
	private Controleur ctrl;

	private PanelCarte    panelC;

	private JMenuItem     menuiCreerSommet;
	private JMenuItem     menuiCreerRoute;

	private JMenuItem     menuiSave;
	private JMenuItem     menuiRein;
	private JMenuItem     menuiSupp;

	private JMenuItem     menuiQuitter;

	/**
	 * Constructeur de la frame qui permet de modifier la carte.
	 * @param ctrl le Controleur qui lance la frame
	 */
	public FrameModification(Controleur ctrl)
	{
		this.setTitle("Modification de la carte");
		this.setSize    ( 1269,1122 );
		this.setLocation(  150, 50 );

		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuCreer = new JMenu("Création"  );
		JMenu menuSave  = new JMenu("Sauvegarde");
		JMenu menuQuit  = new JMenu("Quitter"   );

		this.menuiCreerSommet = new JMenuItem ("Créer ou supprimer un  sommet : " + ctrl.getNomThemeSommet());
		this.menuiCreerRoute  = new JMenuItem ("Créer ou supprimer une route  : " + ctrl.getNomThemeRoute ());

		this.menuiSave = new JMenuItem ("Enregistrer la carte");
		this.menuiRein = new JMenuItem ("Réinitialiser la carte");
		this.menuiSupp = new JMenuItem ("Supprimer la carte"  );

		this.menuiQuitter = new JMenuItem ("Quitter sans enregistrer"  );
		
		menuCreer.add( this.menuiCreerSommet );
		menuCreer.add( this.menuiCreerRoute  );

		menuQuit.add(this.menuiRein);
		menuSave.addSeparator();
		menuSave.add(this.menuiSupp);

		menuSave.add(this.menuiSave);
		menuQuit.add(this.menuiQuitter);

		menubMaBarre.add( menuCreer );
		menubMaBarre.add( menuSave  );
		menubMaBarre.add( menuQuit );

		this.setJMenuBar( menubMaBarre );
		
		// Création des raccourcis clavier
		menuCreer.setMnemonic('C');
		menuSave .setMnemonic('S');
		menuQuit .setMnemonic('Q');

		this.menuiCreerSommet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK) );
		this.menuiCreerRoute.setAccelerator	(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK) );

		this.menuiSupp.setAccelerator		(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK) );
		this.menuiRein.setAccelerator		(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK) );

		this.menuiSave.setAccelerator		(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK) );
		this.menuiQuitter.setAccelerator	(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK) );


		this.menuiCreerSommet.addActionListener ( this );
		this.menuiCreerRoute .addActionListener ( this );
		this.menuiSave.addActionListener 		( this );
		this.menuiRein.addActionListener		( this );
		this.menuiSupp.addActionListener 		( this );
		this.menuiSave.addActionListener 		( this );
		this.menuiQuitter.addActionListener 	( this );

		this.ctrl = ctrl;

		this.panelC = new PanelCarte( this.ctrl );

		// Création et ajout du Panel
		this.add(this.panelC);

		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void majIhm()
	{
		this.panelC = new PanelCarte( this.ctrl );
		this.add(this.panelC);
		this.repaint();
	}

	/**
	 * Méthode de définition des evenements de la frame. 
	 * @param e l'évennement déclenché
	 */
	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.menuiCreerSommet )
			new FrameSommet( this.ctrl);

		if ( e.getSource() == this.menuiCreerRoute )
			new FrameRoute( this.ctrl );

		if( e.getSource() == this.menuiRein)
		{
			try
			{
				this.ctrl.reInit();
				this.repaint();
			} catch( IOException ex ) { throw new RuntimeException(ex); }
		}

		if ( e.getSource() == this.menuiSupp )
		{
			if( JOptionPane.showConfirmDialog(null,"Êtes-vous sur de voulir tout supprimer ?") == JOptionPane.YES_OPTION )
			{
				try
				{
					this.ctrl.supprimerTout();
				}
				catch( IOException ex ) { throw new RuntimeException(ex); }
				this.repaint();

				try
				{
					this.ctrl.getEditionFichier().sauvegarde();
				}
				catch( IOException ex ) { throw new RuntimeException(ex); }
			}
		}

		if ( e.getSource() == this.menuiSave )
		{
			try
			{
				this.ctrl.getEditionFichier().sauvegarde();
			}
			catch( IOException ex ) { throw new RuntimeException(ex); }
			this.dispose();
		}

		if ( e.getSource() == this.menuiQuitter )
		{
			if( JOptionPane.showConfirmDialog(null,"Êtes-vous sur ?\nVos modifications ne seront pas sauvegardées.") == JOptionPane.YES_OPTION )
				System.exit(0);
		}


	}

	/**
	 * Renvoie le panel contenant la carte modifiable
	 * @return le panel contenant la carte
	 */
	public PanelCarte getPanelCarte () {return this.panelC;}

 /*
	public static void main (String[]args) throws IOException
	{
		Controleur ctrl = new Controleur(); 
		new FrameModification(ctrl);
	}
*/
}