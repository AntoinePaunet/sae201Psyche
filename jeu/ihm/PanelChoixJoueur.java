package jeu.ihm;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class PanelChoixJoueur extends JPanel
{

	private JTextField txtJoueur1;
	private JTextField txtJoueur2;

	private JLabel lblNomJoueur1;
	private JLabel lblNomJoueur2;

	private JButton btnSauvegarder;

	public PanelChoixJoueur()
	{
		this.setBackground( new Color( 909090 ));
		this.setLayout(new GridLayout(2, 2));

	}
}
