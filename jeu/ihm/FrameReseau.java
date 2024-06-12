package jeu.ihm;

import jeu.Controleur;
import javax.swing.*;
import java.awt.event.*;


public class FrameReseau extends JFrame implements ActionListener
{
	
	private Controleur ctrl;

	private PanelCarte     panelC;

	private JMenuItem     creerVille;
	private JMenuItem     creerRoute;

	private JMenuItem     enregistrerF;
	private JMenuItem     supprimerF;

	public FrameReseau(Controleur ctrl)
	{
		this.setTitle("Réseau Routier");
		this.setSize    ( 1000,800 );
		this.setLocation(  150, 50 );

		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuCreer = new JMenu("Creer");
		JMenu menuEnreg = new JMenu("Enregistrer");
		JMenu menusup = new JMenu("supprimer");

		this.creerVille  = new JMenuItem ("Ville" );
		this.creerRoute = new JMenuItem ("Route");

		this.enregistrerF = new JMenuItem ("enregistrer");
		this.supprimerF = new JMenuItem ("supprimer");
		
		menuCreer.add( this.creerVille );
		menuCreer.addSeparator();
		menuCreer.add( this.creerRoute );

		menuEnreg.add(this.enregistrerF);
		menusup.add(this.supprimerF);

		menubMaBarre.add( menuCreer );
		menubMaBarre.add( menuEnreg );
		menubMaBarre.add( menusup );

		this.setJMenuBar( menubMaBarre );
		

		this.creerVille .addActionListener ( this );
		this.creerRoute.addActionListener ( this );
		this.enregistrerF.addActionListener( this );
		this.supprimerF.addActionListener( this );

		this.ctrl = ctrl;

		this.panelC = new PanelCarte( this.ctrl );

		// Création et ajout du Panel
		this.add(this.panelC);

		
		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);


	}

	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.creerVille )
		{
			//this.ctrl.ouvrirVille();
		}

		if ( e.getSource() == this.creerRoute )
		{
			//this.ctrl.ouvrirRoute();
		}

		if ( e.getSource() == this.enregistrerF )
		{
			//this.ctrl.enregistrerData();
		}

		if ( e.getSource() == this.supprimerF )
		{
			//this.ctrl.supprimerData();
			this.repaint();
		}
	}

	public PanelCarte getPanelCarte () {return this.panelC;}

}