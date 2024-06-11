package jeu.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Nahel KOCHAT,			IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @author Louis THOMAZEAU-AGULLO,	IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelChoixJoueur extends JPanel implements ActionListener
{
	private JLabel lblNomJoueur1;
	private JLabel lblNomJoueur2;

	private JTextField txtJoueur1;
	private JTextField txtJoueur2;

	private JButton btnSauvegarder;
	private JButton btnAnnuler    ;

	private FrameChoix frameChoix;

	public PanelChoixJoueur(FrameChoix frameChoix)
	{

		this.setLayout(new GridLayout(3, 2));
		this.frameChoix = frameChoix;

		this.add(lblNomJoueur1 = new JLabel("  Entrez le nom du joueur Corporation Solaire : "));
		this.add(txtJoueur1    = new JTextField()                                    );

		this.add(lblNomJoueur2 = new JLabel("  Entrez le nom du joueur Syndiat Astral : "));
		this.add(txtJoueur2    = new JTextField()                                    );

		this.add(btnSauvegarder = new JButton("Sauvegarder"));
		this.add(btnAnnuler = new JButton("Quitter"));


		this.btnSauvegarder.addActionListener(this);
		this.btnAnnuler.addActionListener    (this);


	}

	//@return le String contenu dans txtJoueur1.getText()
	public String getText1()
	{
		return this.txtJoueur1.getText();
	}

	//@return le String contenu dans txtJoueur2.getText()
	public String getText2()
	{
		return this.txtJoueur2.getText();
	}


	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource().equals(this.btnSauvegarder) )
		{	
			//Si les texteField sont écrits, alors change le nom du joueur
			if (!this.txtJoueur1.getText().isEmpty() && !this.txtJoueur2.getText().isEmpty() )
			{
				this.frameChoix.creerFrameJoueur();
			}
		}

		if( e.getSource().equals(this.btnAnnuler) )
		{
			//Ferme la fenêtre
			this.frameChoix.dispose();
		}
	}
}
