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

	public PanelChoixJoueur(Joueur[] joueurs)
	{

		this.setLayout(new GridLayout(3, 2));

		this.add(lblNomJoueur1 = new JLabel("  Entrez le nom du joueur numéro 1 "));
		this.add(txtJoueur1    = new JTextField()                                    );

		this.add(lblNomJoueur1 = new JLabel("  Entrez le nom du joueur numéro 2 "));
		this.add(txtJoueur1    = new JTextField()                                    );

		this.add(btnSauvegarder = new JButton("SAUVEGARDER"));
		this.add(btnAnnuler = new JButton("ANNULER"));


		this.btnSauvegarder.addActionListener(this);
		this.btnAnnuler.addActionListener    (this);


	}


	public void actionPerformed(ActionEvent e)
    {
        if( e.getSource().equals(this.btnSauvegarder) )
        {
			if (this.txtJoueur1.getText() != null && this.txtJoueur2.getText() != null)
			{
				joueurs[0].setNomJoueur(this.txtJoueur1.getText());
				joueurs[1].setNomJoueur(this.txtJoueur2.getText());
			}
			
			this.dispose();
		}

		if( e.getSource().equals(this.btnAnnuler) )
        {
			this.dispose();
		}
	}
}
