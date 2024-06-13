package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import jeu.ihm.*;
import jeu.metier.*;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Controleur 
{
	private Joueur j1;
	private Joueur j2;
	protected ArrayList<Sommet> 	tabSommet;
	protected ArrayList<Route>	tabRoute;
	private boolean             estJeu;

	private boolean tourJ1;
	private boolean finPartie;
	public  FrameDemarrage frameDemarrage;

	private EditionFichier editionFichier;

	/**
	 * Constructeur du controleur
	 */
	public Controleur() throws IOException
	{
		this.j1      		= new Joueur ();
		this.j2      		= new Joueur ();
		this.editionFichier = new EditionFichier(this);
		this.tabSommet 		= this.editionFichier.getTabSommet();
		this.tabRoute		= this.editionFichier.getTabRoute();
		this.tourJ1 = true;
		this.finPartie = false;
		this.estJeu = false;

		this.initJetonPossession();
		this.frameDemarrage = new FrameDemarrage(this);
		this.editionFichier.lectureFichier("data.txt");
	}




	public void setTabSommet(ArrayList<Sommet> tabSmt)
	{
		this.tabSommet = tabSmt;
	}

	public void setTabRoute(ArrayList<Route> tabRt)
	{
		this.tabRoute = tabRt;
	}

	/**
	 * A completer.
	 * @return La liste des sommets
	 */
	public boolean getEstJeu() { return estJeu; }


	public EditionFichier getEditionFichier()
	{
		return this.editionFichier;
	}
	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public void setEstJeu(boolean estJeu) { this.estJeu = estJeu; }


	/**
	 * Méthode qui vérifie si la route est valide.
	 * @param r la route concernée
	 * @return La liste des sommets
	 */
	public boolean estValide(Route r)
	{
		if (r.getJoueur()!=null) {return false;}

		//verifie si l'un des deux sommet deja pris ou non
		if (r.getSommetDep().getMateriaux()==null || r.getSommetArr().getMateriaux()==null)
			return true ;

		return false ;

	}

	/**
	 * Renvoie la liste des sommets de la carte
	 * @return La liste des sommets
	 */
	public ArrayList<Sommet> getTabSommet() 
	{ 
		ArrayList<Sommet> tempSommet = new ArrayList<Sommet>();

		for ( Sommet som : this.tabSommet )
			tempSommet.add(som);

		return tempSommet;
	}

	public FrameDemarrage getFrameDemarrage()
	{
		return this.frameDemarrage;
	}

	/**
	 * Renvoie la liste des routes de la catre
	 * @return La liste des routes
	 */
	public ArrayList<Route> getTabRoute() 
	{ 
		ArrayList<Route> tempRoute = new ArrayList<Route>();

		for ( Route rot : this.tabRoute )
			tempRoute.add(rot);

		return tempRoute;
	}

	/**
	 * Méthode qui vérifie si les règles du jeu sont respectées, et effectue les actions du joueurs.
	 * @param r La route concernée
	 */
	public void jouer (Route r)
	{
		if (!this.finPartie)
		{
			//System.out.print(r);
			if (this.tourJ1)
			{
				
				if (this.estValide(r))
				{
					r.setJoueur(this.j1);

					if (r.getSommetDep().getMateriaux()!=null)
						this.j1.addSommetRecup(r.getSommetDep());
					
					if (r.getSommetArr().getMateriaux()!=null)
						this.j1.addSommetRecup(r.getSommetArr());

					this.j1.utiliserUnJetons();
					
					this.frameDemarrage.getFrameChoix().getF1().refresh();
					
					this.tourJ1= !this.tourJ1;
					this.frameDemarrage.getFrameChoix().getF1().majTitre(null, this.j1, null);
					
				}
			}
			else
			{
				if (this.estValide( r))
				{
					r.setJoueur(this.j2);

					if (r.getSommetDep().getMateriaux()!=null)
						this.j2.addSommetRecup(r.getSommetDep());
					
					if (r.getSommetArr().getMateriaux()!=null)
						this.j2.addSommetRecup(r.getSommetArr());

					this.j2.utiliserUnJetons();
					
					this.frameDemarrage.getFrameChoix().getF2().refresh();
					

					this.frameDemarrage.getFrameChoix().getF1().majTitre(null, this.j2, null);
					this.tourJ1= !this.tourJ1;
				}
			}
		}
		this.frameDemarrage.getFrameChoix().getFrameJeu().getPanelCarte().chargerImages(r);
		this.frameDemarrage.getFrameChoix().getFrameJeu().repaint();
	}

	/**
	 * Méthode qui initialise le jeu. Elle met donc en place la carte, ses chemins et ses sommets.
	 */
	public void init() throws IOException
	{
		String tmpCoul = "";
		int    tmpZone = -1;
		int rndm;
		Materiaux tmpMat;

		String[] 	tabNomSmt = new String[] { "V4", "V8", "V2", "V3","V6", "B6", "B8", "R5", "R3", "R1", "G2", "B3", "B4", "B2", "G4", "J3", "R4", "R2", "M2", "G0", "J1", "J4", "M5", "M3", "M4", "M1", "G3", "G1", "J5", "J2"};
		int[] 		tabCooX    = new int[]    { 336,  265,  317,  394, 251,  336,  414,  80,  156,  257,  510,  308,  346,  440,  575,  648,  111,  185,  353,  576,  696,  774,  200,  330,  427,  501,  606,  556,  696,  773};
		int[] 		tabCooY    = new int[]    {  92,  111,  187,  175, 239,  284,  270,  300,  298,  322,  295,  382,  339,  366,  337,  319,  456,  440,  428,  433,  442,  443,  517,  542,  541,  519,  512,  586,  583,  582};


		String[] tabNomsMat = new String[]{ "FE", "AL", "AU", "TI", "AG", "CO", "NI", "PT" };

		ArrayList<String> tmpLst = new ArrayList<>(Arrays.asList("NR", "NR", "NR", "NR", "NR", "NR", "NR", "NR"));


		//génération du tableau contenant les noms des matériaux possibles
		for(int i = 0 ; i < tabNomsMat.length ; i++)
		{
			for(int j = 0; j < 4 ; j++)
			{
				tmpLst.add(tabNomsMat[i]);
			}
		}


		//Génération des Sommet
		for(int cpt = 0; cpt < tabNomSmt.length ; cpt++)
		{
			switch ( tabNomSmt[cpt].substring( 0, 1 ) )
			{
				case "J" -> tmpCoul = "Jaune";
				case "B" -> tmpCoul = "Bleu";
				case "G" -> tmpCoul = "Gris";
				case "V" -> tmpCoul = "Vert";
				case "R" -> tmpCoul = "Rouge";
				case "M" -> tmpCoul = "Marron";
			}

			rndm = (int)(Math.random()*(40-cpt));

			tmpMat = new Materiaux(tmpLst.remove(rndm));

			tmpZone = Integer.parseInt( tabNomSmt[cpt].substring( 1, 2 ) );
			this.tabSommet.add( new Sommet( tmpZone, tmpCoul, tabCooX[cpt], tabCooY[cpt], tmpMat, false ) );

		}

		//Ajout de la zone de départ
		this.tabSommet.add( new Sommet(0, null, 442, 475, null, true));


		this.tabRoute.add(new Route(this.tabSommet.get(0), this.tabSommet.get(1), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(0), this.tabSommet.get(2), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(1), this.tabSommet.get(2), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(0), this.tabSommet.get(3), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(2), this.tabSommet.get(3), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(4), this.tabSommet.get(2), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(4), this.tabSommet.get(5), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(5), this.tabSommet.get(2), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(5), this.tabSommet.get(6), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(6), this.tabSommet.get(3), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(7), this.tabSommet.get(4), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(7), this.tabSommet.get(8), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(8), this.tabSommet.get(4), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(9), this.tabSommet.get(5), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(9), this.tabSommet.get(8), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(10), this.tabSommet.get(13), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(11), this.tabSommet.get(9), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(11), this.tabSommet.get(5), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(11), this.tabSommet.get(12), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(12), this.tabSommet.get(13), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(12), this.tabSommet.get(6), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(14), this.tabSommet.get(10), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(6), this.tabSommet.get(13), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(14), this.tabSommet.get(15), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(16), this.tabSommet.get(7), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(16), this.tabSommet.get(17), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(9), this.tabSommet.get(17), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(18), this.tabSommet.get(this.tabSommet.size()-1), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(this.tabSommet.size()-1), this.tabSommet.get(13), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(this.tabSommet.size()-1), this.tabSommet.get(19), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(18), this.tabSommet.get(11), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(19), this.tabSommet.get(14), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(19), this.tabSommet.get(20), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(20), this.tabSommet.get(21), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(21), this.tabSommet.get(15), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(22), this.tabSommet.get(23), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(22), this.tabSommet.get(17), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(23), this.tabSommet.get(24), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(24), this.tabSommet.get(25), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(23), this.tabSommet.get(18), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(25), this.tabSommet.get(this.tabSommet.size()-1), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(26), this.tabSommet.get(19), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(26), this.tabSommet.get(20), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(27), this.tabSommet.get(26), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(27), this.tabSommet.get(25), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(27), this.tabSommet.get(28), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(29), this.tabSommet.get(28), 1));
		this.tabRoute.add(new Route(this.tabSommet.get(28), this.tabSommet.get(20), 2));
		this.tabRoute.add(new Route(this.tabSommet.get(29), this.tabSommet.get(21), 2));

		this.editionFichier.sauvegarde();
	}

	/**
	 * Méthode qui affecte les JetonsPossessions des joueurs.
	 */
	private void initJetonPossession()
	{
		final int NB_JETONS = 25;

		for (int i=0; i<NB_JETONS; i++)
		{
			j1.addJetonPossession(new JetonPossession(j1));
			j2.addJetonPossession(new JetonPossession(j2));
		}
	}

	/**
	 * Renvoie le joueur n°1
	 * @return le joueur 1
	 */
	public Joueur getJoueur1 ()
	{
		return this.j1;
	}

	/**
	 * Renvoie le joueur n°1
	 * @return le joueur 1
	 */
	public Joueur getJoueur2 ()
	{
		return this.j2;
	}


	/**
	 * Méthode qui recherche un sommet dans la liste de sommets a partir de son numéro.
	 * @param numSmt le numéro du sommet
	 * @return le sommet correspondant au numéro
	 */
	public Sommet rechercheSommet(String numSmt)
	{
		for (Sommet s : this.tabSommet)
		{
			if (s.getNumSom() == Integer.parseInt(numSmt.substring(0, 1)) && s.getNomCoul().equals(numSmt.substring(1)))
				return s;
		}
		return null;
	}



	/**
	 * Méthode qui appelle la méthode de mise a jour de la carte.
	 */
	public void MajFrameModification() { this.frameDemarrage.getFrameModification().repaint(); }

	/**
	 * Méthode qui recherche un sommet dans la liste de sommets a partir de son numéro.
	 * @param sommetDep le sommet de départ
	 * @param sommetArr le sommet d'arrivée
	 * @param nbTroncon le nombre de troncons de la route
	 */
	public void ajouterOuSupprimerRoute( Sommet sommetDep, Sommet sommetArr, int nbTroncon) 
	{
		Route tempRoute = new Route(sommetDep, sommetArr, nbTroncon);
		boolean tempEstSup = false;

		for ( Route rt : this.tabRoute )
		{
			if ( tempRoute.equals(rt) )
			{
				this.tabRoute.remove(rt);
				tempEstSup = true;
				break;
			}
		}
		if ( !tempEstSup )
		{
			this.tabRoute.add( tempRoute );
		}
	}

	public void ajouterOuSupprimerSommet( int numSom, String nomCoul, int x, int y, boolean estDepart ) 
	{ 
		Sommet tempSommet = new Sommet(y, nomCoul, x, y, null , estJeu) ;
		boolean tempEstSup = false;

		for ( Sommet rt : this.tabSommet )
		{
			if ( rt.getNumSom() == numSom &&  rt.possede(x, y) && rt.getDepard() == estDepart)
			{
				this.tabSommet.remove(rt);
				tempEstSup = true;
				break;
			}
		}
		if ( !tempEstSup )
		{
			this.tabSommet.add( tempSommet );
		}
	}

	/**
	 * Méthode qui renvoie le booléen correspondant au joueur qui doit jouer.
	 * @return vrai si c'est au tour du joueur 1, faux si c'et au tour du joueur 2.
	 */
	public boolean getTour()
	{
		return this.tourJ1;
	}

	/**
	 * Méthode qui recherche et renvoie le sommet placé aux coordonnées données.
	 * @param x l'abcisse du sommet
	 * @param y l'ordonnée du sommet
	 * @return le sommet placé à ces coordonnées.
	 */
	public Sommet getSommet(int x, int y)
	{

		for ( Sommet s : this.tabSommet )
		{
			if ( s.possede(x, y) )
				return s;
		}
			
		return null;
	}

	public void supprimerTout()
	{
		this.tabSommet = new ArrayList<Sommet>(30);
		this.tabRoute  = new ArrayList<Route>(40);
	}

	/**
	 * Méthode qui modifie les coordonnées d'un sommet.
	 * @param x l'abcisse du sommet
	 * @param y l'ordonnée du sommet
	 * @param som le sommet à déplacer
	 */
	public void setSommet(int x, int y, Sommet som)
	{
		som.setX(x); som.setY(y);
	}

	/**
	 * Méthode principale de la classe Controleur. Elle lance l'application.
	 * @param arg les arguments de la méthode
	 */
	public static void main (String[] arg) throws IOException
	{
		Controleur ctrl = new Controleur();
	}
}