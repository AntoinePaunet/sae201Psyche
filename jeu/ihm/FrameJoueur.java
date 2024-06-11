package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Jeton;
import jeu.metier.Joueur;
import jeu.metier.Materiaux;

import javax.swing.*;

import java.awt.Color;
import java.util.Arrays;

/**
 * Cette classe créé la Frame correspondant aux plateaux individuels des joueurs. Elle affiche leur score et leurs jetons.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class FrameJoueur extends JFrame
{
    private JLayeredPane panelFond;

	private Controleur ctrl;
	private Joueur joueur;

    /**
     * Constructeur de la classe interface permettant de générer une IHM de 900px / 535px
     * Contient tout les éléments graphiques
     */
    public FrameJoueur(String nomJoueur, int j, Controleur ctrl)
    {
		this.setTitle( "Plateau de " + nomJoueur );

        this.setSize( 500 , 400 );
        this.panelFond = new JLayeredPane();

		this.ctrl = ctrl;

		if( j == 1 )
		{
			this.setLocation( 100, 550 );
			this.ajoutImage(0,0, "bgSolaire.png", 0);
			this.joueur = ctrl.getJoueur1();
		}
		else
		{
			this.setLocation( 600, 550 );
			this.ajoutImage(0,0, "bgSyndicat.png", 0);
			this.joueur = ctrl.getJoueur2();
		}


        this.add( this.panelFond );

		this.refresh();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

	/**
	 * Méthode de mise a jour de l'affichage des plateaux des joueurs
	 */

	public void refresh()
	{
		int x = 80;
		int y = 20;
		for(int i = 0; i < this.joueur.getTableMateriaux().length ; i++) //Affichage des épices
		{
			for(int j = 0 ; j < this.joueur.getTableMateriaux()[i].length ; j++)
			{
				if(this.joueur.getTableMateriaux()[i][j] != null)
				{
					this.ajoutImage(y, x, this.joueur.getTableMateriaux()[i][j].getNom() + ".png", 1);
				}
				x += 160;
			}
			x = 80;
			y += 120;
		}
		System.out.println(Arrays.deepToString(this.joueur.getTableMateriaux()));

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
        this.panelFond.add(imgLabel, Integer.valueOf(layer));
    }

}