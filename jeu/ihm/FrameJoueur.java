package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Jeton;

import javax.swing.*;

import java.awt.Color;

/**
 * Cette classe créé la Frame correspondant aux plateaux individuels des joueurs. Elle affiche leur score et leurs jetons.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Nahel KOCHAT,			IUT du Havre
 * @author Anas AARAB Vauthier,		IUT du Havre
 * @author Louis THOMAZEAU-AGUILLO,	IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class FrameJoueur extends JFrame
{
    private JPanel panelFond;

	private Controleur ctrl;

    /**
     * Constructeur de la classe interface permettant de générer une IHM de 900px / 535px
     * Contient tout les éléments graphiques
     */
    public FrameJoueur(String nomJoueur, int j, Controleur ctrl)
    {
		this.setTitle( "Plateau de " + nomJoueur );

        this.setSize( 500 , 400 );
        this.panelFond = new JPanel();

		this.ctrl = ctrl;

		if( j == 1 )
			this.ajoutImage(0,0, "bgSolaire.png", JLayeredPane.DEFAULT_LAYER);
		else
			this.ajoutImage(0,0, "bgSyndicat.png", JLayeredPane.DEFAULT_LAYER);

        this.add( this.panelFond );

		this.refresh();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

	public void refresh()
	{
		for(Jeton this.ctrl.)
	}


    /**
     * Permet d'ajouter des éléments dans l'interface graphique. Dans ce cas, il ajoute les images correspondantes aux Jetons sur le Plateau.
     * @param x Coordonnée de l'abscisse de l'image à placer
     * @param y Coordonnée de l'ordonnée de l'image à placer
     * @param url lien de l'image dans le dossier image.
     */
    public void ajoutImage(int x, int y, String url, int layer)
    {
        ImageIcon image = new ImageIcon(getClass().getResource("images/" + url));
        JLabel imgLabel = new JLabel(image);
        imgLabel.setBounds(x, y, image.getIconWidth(), image.getIconHeight());
        this.panelFond.add(imgLabel, layer);
    }

}