package jeu.ihm;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class PanelChoixJoueur extends JPanel
{
	private JLabel lblNomJoueur1;
	private JLabel lblNomJoueur2;

	private JTextField txtJoueur1;
	private JTextField txtJoueur2;

	private JButton btnSauvegarder;
	private JButton btnAnnuler    ;

	public PanelChoixJoueur()
	{
		this.setBackground( new Color( 909090 ));
		this.setLayout(new GridLayout(3, 2));

		this.add(lblNomJoueur1 = new JLabel("Entrez le nom du joueur numéro 1 "));
		this.add(txtJoueur1    = new JTextField()                                    );

		this.add(lblNomJoueur1 = new JLabel("Entrez le nom du joueur numéro 1 "));
		this.add(txtJoueur1    = new JTextField()                                    );

		this.add(btnSauvegarder = new JButton("SAUVEGARDER"));
		this.add(btnAnnuler = new JButton("ANNULER"));

	}
}
