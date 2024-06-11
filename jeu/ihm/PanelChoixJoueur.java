package jeu.ihm;

import jeu.metier.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

		this.add(lblNomJoueur1 = new JLabel("  Entrez le nom du joueur numéro 1 "));
		this.add(txtJoueur1    = new JTextField()                                    );

		this.add(lblNomJoueur2 = new JLabel("  Entrez le nom du joueur numéro 2 "));
		this.add(txtJoueur2    = new JTextField()                                    );

		this.add(btnSauvegarder = new JButton("SAUVEGARDER"));
		this.add(btnAnnuler = new JButton("ANNULER"));


		this.btnSauvegarder.addActionListener(this);
		this.btnAnnuler.addActionListener    (this);


	}


	public void actionPerformed(ActionEvent e)
    {
        if( e.getSource().equals(this.btnSauvegarder) )
        {	
			//Si les texteField sont écrits, alors change le nom du joueur
			if (this.txtJoueur1.getText() != null && this.txtJoueur2.getText() != null)
			{
				//joueurs[0].setNomJoueur(this.txtJoueur1.getText());
				//joueurs[1].setNomJoueur(this.txtJoueur2.getText());
			}
			//Ferme la fenêtre
			this.frameChoix.dispose();
		}

		if( e.getSource().equals(this.btnAnnuler) )
        {
			//Ferme la fenêtre
			this.frameChoix.dispose();
		}
	}
}
