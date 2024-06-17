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
	 * Constructeur de la Frame contenant le plateau du joueur correspondant
	 * @param nomJoueur le nom du joueur a qui appartient la frame
	 * @param j le numéro correspondant au joueur
	 * @param ctrl le Controleur qui lance la frame
	 */
	public FrameJoueur(Joueur j, Controleur ctrl)
	{
		this.joueur = j;
		this.ctrl = ctrl;
		this.setTitle( this.majTitre(this.joueur, ctrl) );
		this.setSize( 700 , 500 );
		this.panelFond = new JLayeredPane();

		if( j == this.ctrl.getJoueur1() )
		{
			this.setLocation( 1269, 0 );
			this.ajoutImage(0,0, "bgSolaire.png", 0);
			this.joueur = ctrl.getJoueur1();
		}
		else
		{
			this.setLocation( 1269, 550 );
			this.ajoutImage(0,0, "bgSyndicat.png", 0);
			this.joueur = ctrl.getJoueur2();
		}

		this.add( this.panelFond );

		this.refresh();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Méthode qui met a jour le nom de la frame des joueurs en fonction de leur tour de jouer.
	 * @param j le numéro correspondant au joueur
	 * @param ctrl le Controleur qui lance la frame
	 * @return Le titre de la frame
	 */
	public String majTitre( Joueur j , Controleur ctrl )
	{
		String s = this.joueur.getNomJoueur() + " : ";

		if( ctrl.getTour() ) // si c'est le tour du Joueur 1
			s += String.format("%-40s","A vous de jouer !" );
		else				 // si c'est le tour du Joueur 2
			s += String.format("%-40s","en attente de l'autre joueur ... " ) ;

		s += "Votre score : " + String.valueOf(this.joueur.getScore());
		return s;
	}

	/**
	 * Méthode de mise a jour de l'affichage des plateaux des joueurs
	 */
	public void refresh()
	{
		int x = 0;
		int y = 500;
		for(int i = 0; i < this.joueur.getTableMateriaux().length ; i++) // Ajout des matériaux
		{
			for(int j = 0 ; j < this.joueur.getTableMateriaux()[i].length ; j++)
			{
				if(this.joueur.getTableMateriaux()[i][j] != null)
				{
					if (!this.joueur.getTableMateriaux()[i][j].getNom().equals("DP"))
						this.ajoutImage(x, y, this.joueur.getTableMateriaux()[i][j].getNom() + ".png", 1);
				}
				x += 60;
			}
			x = 85;
			y -= 80;
		}

		for(int i = 0 ; i < this.joueur.getTabPiece().length ; i++) // Ajout des pièces
		{
			if(this.joueur.getTabPiece()[i] != null)
			{
				this.ajoutImage(x,400, "NR.png", 1);
				x += 60;
			}
		}
	}

	/**
	 * Renvoie le nom du Joueur
	 * @return le caractere du joueur
	 */
	public char getNom() {return nom;}

	/**
	 * Permet d'ajouter des éléments dans l'interface graphique. Dans ce cas, il ajoute les images correspondantes aux Jetons sur le Plateau.
	 * @param x Coordonnée de l'abscisse de l'image à placer
	 * @param y Coordonnée de l'ordonnée de l'image à placer
	 * @param url lien de l'image dans le dossier image.
	 * @param layer la couche de l'image
	 */
	public void ajoutImage(int x, int y, String url, int layer)
	{
		ImageIcon image = new ImageIcon(getClass().getResource("../src/images/"+ ctrl.getNomThemePrincipal() +"/"+ url));
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