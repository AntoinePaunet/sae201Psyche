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

	private PanelCarte     panelC;

	private JMenuItem     creerSommet;
	private JMenuItem     creerRoute;

	private JMenuItem     enregistrerF;
	private JMenuItem     supprimerF;

	/**
	 * Constructeur de la frame qui permet de modifier la carte.
	 * @param ctrl le Controleur qui lance la frame
	 */
	public FrameModification(Controleur ctrl)
	{
		this.setTitle("Modification");
		this.setSize    ( 1000,800 );
		this.setLocation(  150, 50 );

		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuCreer = new JMenu("Creer"      );
		JMenu menuEnreg = new JMenu("Enregistrer");
		JMenu menuSup   = new JMenu("Supprimer"  );

		this.creerSommet = new JMenuItem ("Créer ou supprimer un  sommet");
		this.creerRoute  = new JMenuItem ("Créer ou supprimer une route" );

		this.enregistrerF = new JMenuItem ("Enregistrer la carte");
		this.supprimerF   = new JMenuItem ("Supprimer la carte"  );
		
		menuCreer.add( this.creerSommet );
		menuCreer.addSeparator();
		menuCreer.add( this.creerRoute  );

		menuEnreg.add(this.enregistrerF);
		menuSup.add(this.supprimerF    );

		menubMaBarre.add( menuCreer );
		menubMaBarre.add( menuEnreg );
		menubMaBarre.add( menuSup   );

		this.setJMenuBar( menubMaBarre );
		
		// Création des raccourcis clavier
		menuCreer.setMnemonic('C');
		menuEnreg.setMnemonic('S');
		menuSup.setMnemonic  ('P');

		this.creerSommet.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK) );
		this.creerRoute.setAccelerator  (KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK) );
		this.supprimerF.setAccelerator  (KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK) );
		this.enregistrerF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK) );


		this.creerSommet .addActionListener ( this );
		this.creerRoute  .addActionListener ( this );
		this.enregistrerF.addActionListener ( this );
		this.supprimerF  .addActionListener ( this );

		this.ctrl = ctrl;

		this.panelC = new PanelCarte( this.ctrl );

		// Création et ajout du Panel
		this.add(this.panelC);

		
		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Méthode de définition des evenements de la frame. 
	 * @param e l'évennement déclenché
	 */
	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.creerSommet )
		{
			new FrameSommet( this.ctrl);
		}

		if ( e.getSource() == this.creerRoute )
		{
			new FrameRoute( this.ctrl );
		}

		if ( e.getSource() == this.enregistrerF )
		{
			try {
				this.ctrl.getEditionFichier().sauvegarde();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
			this.dispose();
		}

		if ( e.getSource() == this.supprimerF )
		{
			this.ctrl.supprimerTout();
			this.repaint();
			try {
				this.ctrl.getEditionFichier().sauvegarde();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	/**
	 * Renvoie le panel contenant la carte modifiable
	 * @return le panel contenant la carte
	 */
	public PanelCarte getPanelCarte () {return this.panelC;}

	// public static void main (String[]args) throws IOException
	// {
	// 	Controleur ctrl = new Controleur(); 
	// 	new FrameModification(ctrl);
	// }

}