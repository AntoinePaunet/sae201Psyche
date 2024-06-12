package jeu.ihm;

import jeu.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;


public class FrameModification extends JFrame implements ActionListener
{
	
	private Controleur ctrl;

	private PanelCarte     panelC;

	private JMenuItem     creerSommet;
	private JMenuItem     creerRoute;

	private JMenuItem     enregistrerF;
	private JMenuItem     supprimerF;

	public FrameModification(Controleur ctrl)
	{
		this.setTitle("Modification");
		this.setSize    ( 1000,800 );
		this.setLocation(  150, 50 );

		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuCreer = new JMenu("Creer");
		JMenu menuEnreg = new JMenu("Enregistrer");
		JMenu menusup = new JMenu("supprimer");

		this.creerSommet = new JMenuItem ("Sommet" );
		this.creerRoute  = new JMenuItem ("Route");

		this.enregistrerF = new JMenuItem ("enregistrer");
		this.supprimerF   = new JMenuItem ("supprimer");
		
		menuCreer.add( this.creerSommet );
		menuCreer.addSeparator();
		menuCreer.add( this.creerRoute );

		menuEnreg.add(this.enregistrerF);
		menusup.add(this.supprimerF);

		menubMaBarre.add( menuCreer );
		menubMaBarre.add( menuEnreg );
		menubMaBarre.add( menusup );

		this.setJMenuBar( menubMaBarre );
		

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

	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.creerSommet )
		{
			//this.ctrl.ecrireSommet(numSmt, nomCoul, x, y, materiaux, estDepart);
		}

		if ( e.getSource() == this.creerRoute )
		{
			this.ctrl.ecrireRoute(smtDepart, smtArrivee, nbTroncon);
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
/* 
	public static void main (String[]args) throws IOException
	{
		Controleur ctrl = new Controleur(); 
		new FrameModification(ctrl);
	}
*/
}