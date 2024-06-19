package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Joueur;

import javax.swing.*;
import java.awt.BorderLayout;



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
	private JPanel 		 panelDroite;
	private JPanel 		 panelBas;

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
		this.setTitle( this.majTitre(this.ctrl) );
		this.setSize( 680, 500 );
		this.panelFond = new JLayeredPane();

		this.panelBas=new PanelJetons(this,ctrl, j);
		this.add(this.panelBas, BorderLayout.SOUTH);

		this.panelDroite = new PanelMine (ctrl, j);
		this.add(this.panelDroite, BorderLayout.EAST);

		if( this.joueur == this.ctrl.getJoueur1() )
		{
			this.setLocation( 1300, 0 );
			this.ajoutImage(0,0, "plateau_joueur_1.png", 0);
		}
		else
		{
			this.setLocation( 1300, 550 );
			this.ajoutImage(0,0, "plateau_joueur_2.png", 0);
		}

		this.add( this.panelFond, BorderLayout.CENTER );
		this.refresh();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Méthode qui met a jour le nom de la frame des joueurs en fonction de leur tour de jouer.
	 * @param ctrl le Controleur qui lance la frame
	 * @return Le titre de la frame
	 */
	public String majTitre( Controleur ctrl )
	{
		String s = this.joueur.getNomJoueur() + " : ";

		if( this.joueur == this.ctrl.getJoueurJoue() ) 
		{
			s += String.format("%-40s","A vous de jouer !" );
		}
		else 
		{
			s += String.format("%-40s","en attente de l'autre joueur ... " );
		}
			


		s += "Votre score : " + String.valueOf(this.joueur.getScoreRoute());

		this.setTitle(s);
		return s;
	}

	/**
	 * Méthode de mise a jour de l'affichage des plateaux des joueurs
	 */
	public void refresh()
	{
		this.panelBas.repaint();
		this.panelDroite.repaint();


		int x = 65;
		int y = 57;

		for(int i = 0; i < this.joueur.getTableMateriaux().length ; i++) // Ajout des matériaux
		{
			
			for(int j = 0 ; j < this.joueur.getTableMateriaux()[i].length ; j++)
			{
				if(this.joueur.getTableMateriaux()[i][j] != null)
				{
					if(!this.joueur.getTableMateriaux()[i][j].getNom().equals("DP"))
					{
						this.ajoutImage(x, y, this.joueur.getTableMateriaux()[i][j].getNom() + ".png", 1);
					}
				}
				x += 54;
			}
			y += 54;
			x= 65;
		}

		for(int i = 0 ; i < this.joueur.getTabPiece().length ; i++) // Ajout des pièces
		{
			if(this.joueur.getTabPiece()[i] != null)
			{
				this.ajoutImage(x,328, "NR.png", 1);
				x += 54;
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
		imgLabel.setBounds(x, y, image.getIconWidth()+20, image.getIconHeight()+20);
		this.panelFond.add(imgLabel, Integer.valueOf(layer));
	}

	public int calculeScore()
	{
		//System.out.println( "FrameJOueur calcule score  : " + (25 - this.joueur.getJetons() ) );

		return (25 - this.joueur.getJetons() );
	}

	public Joueur getJoueur() {
		return joueur;
	}
	

	/*

	public static void main( String[] args )
	{
		new FrameJoueur();
	}
*/
}