package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Joueur;

import javax.swing.*;

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
	private char nom = 'J';

    /**
     * Constructeur de la classe interface permettant de générer une IHM de 900px / 535px
     * Contient tout les éléments graphiques
     */
    public FrameJoueur(String nomJoueur, int j, Controleur ctrl)
    {
		this.setTitle( "Plateau de " + nomJoueur );

        this.setSize( 700 , 550 );
        this.panelFond = new JLayeredPane();

		this.ctrl = ctrl;

		if( j == 1 )
		{
			this.setLocation( 1100, 0 );
			this.ajoutImage(0,0, "bgSolaire.png", 0);
			this.joueur = ctrl.getJoueur1();
		}
		else
		{
			this.setLocation( 1100, 550 );
			this.ajoutImage(0,0, "bgSyndicat.png", 0);
			this.joueur = ctrl.getJoueur2();
		}

		this.setName("PanelJoueur");

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
		int x = 0;
		int y = 500;
		for(int i = 0; i < this.joueur.getTableMateriaux().length ; i++)
		{
			for(int j = 0 ; j < this.joueur.getTableMateriaux()[i].length ; j++)
			{
				if(this.joueur.getTableMateriaux()[i][j] != null)
				{
					this.ajoutImage(x, y, this.joueur.getTableMateriaux()[i][j].getNom() + ".png", 1);
				}
				x += 60;
			}
			x = 80;
			y -= 80;
		}


		for(int i = 0 ; i < this.joueur.getTabPiece().length ; i++) 
		{
			if(this.joueur.getTabPiece()[i] != null)
			{
				this.ajoutImage(x,400, "NR.png", 1);
				x += 60;
			}
		}

	}

	


    public char getNom() {
		return nom;
	}

	/**
     * Permet d'ajouter des éléments dans l'interface graphique. Dans ce cas, il ajoute les images correspondantes aux Jetons sur le Plateau.
     * @param x Coordonnée de l'abscisse de l'image à placer
     * @param y Coordonnée de l'ordonnée de l'image à placer
     * @param url lien de l'image dans le dossier image.
     */
    public void ajoutImage(int x, int y, String url, int layer)
    {
        ImageIcon image = new ImageIcon(getClass().getResource("../src/images/" + url));
        JLabel imgLabel = new JLabel(image);
        imgLabel.setBounds(x, y, image.getIconWidth(), image.getIconHeight());
        this.panelFond.add(imgLabel, Integer.valueOf(layer));
    }
/*
	public static void main( String[] args )
	{
		new FrameJoueur();
	}
*/
}