package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
	private   Joueur             j1       ;
	private   Joueur             j2       ;
	protected ArrayList<Sommet>  tabSommet;
	protected ArrayList<Route>	 tabRoute ;

	private   boolean            estJeu   ;
	private   boolean            tourJ1        ;
	private   boolean            finPartie     ;
	public    FrameDemarrage     frameDemarrage;

	private EditionFichier editionFichier;
	private String[]       elementsTheme ;

	private ArrayList<String> lstMateriaux;

	/**
	 * Constructeur du Controleur
	 */
	public Controleur() throws IOException
	{
		//new FrameScore(this);
		
		this.j1      		= new Joueur ();
		this.j2      		= new Joueur ();
		this.editionFichier = new EditionFichier(this);
		this.tabSommet 		= this.editionFichier.getTabSommet();
		this.tabRoute		= this.editionFichier.getTabRoute();
		this.tourJ1         = true;
		this.finPartie      = false;
		this.estJeu        = false;
		
		this.lstMateriaux = new ArrayList<>(40);
		this.initMateriaux();

		this.initJetonPossession();
		this.frameDemarrage = new FrameDemarrage(this);
		this.editionFichier.lectureFichier("data.txt", false);


	}

	private void initMateriaux()
	{
		this.lstMateriaux = new ArrayList<>(Arrays.asList("NR", "NR", "NR", "NR", "NR", "NR", "NR", "NR"));
		String[] tabNomsMat = new String[]{ "FE", "AL", "AU", "TI", "AG", "CO", "NI", "PT" };


		//génération du tableau contenant les noms des matériaux possibles
		for(int i = 0 ; i < tabNomsMat.length ; i++)
		{
			for(int j = 0; j < 4 ; j++)
			{
				this.lstMateriaux.add(tabNomsMat[i]);
			}
		}
	}


	/**
	 * A completer.
	 * @return La liste des sommets
	 */
	public void setTabSommet(ArrayList<Sommet> tabSmt)
	{
		this.tabSommet = tabSmt;
	}

	/**
	 * A completer.
	 * @return La liste des sommets
	 */
	public void setTabRoute(ArrayList<Route> tabRt)
	{
		this.tabRoute = tabRt;
	}

	/**
	 * Renvoie un booléen qui indique si on peut modifier et déplacer les villes en fonction de si on est en jeu.
	 * @return La valeur de estJeu
	 */
	public boolean getEstJeu() { return estJeu; }

	/**
	 * Renvoie l'instance de la classe EditionFichier qui est lié au controleur.
	 * @return  la valeur de editionFichier
	 */
	public EditionFichier getEditionFichier()
	{
		return this.editionFichier;
	}

	/**
	 * L'activation du mode jeu empèche de déplacer les villes lorsqu'il est true.
	 * @param estJeu si on peut modifier la carte.
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

		System.out.println(r);
		System.out.println(r.getSommetDep().getMateriaux() == null || r.getSommetArr().getMateriaux() == null);
		//verifie si l'un des deux sommet deja pris ou non
		return (r.getSommetDep().getMateriaux()==null || r.getSommetArr().getMateriaux()==null);
			 
		//return false ;

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

	/**
	 * Renvoie la frameDemarrage liée au controleur
	 * @return la l'instance de frameDemarrage
	 */
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
		//System.out.println(this.estValide(r));

		if (!this.finPartie)
		{
			//System.out.print(r);
			if (this.estValide(r))
			{
				if (this.tourJ1)
				{
					r.setJoueur(this.j1);

					this.j1.addJetons(r.getNbTroncons());

					if (r.getSommetDep().getMateriaux()!=null)
						this.j1.addSommetRecup(r.getSommetDep());
					
					if (r.getSommetArr().getMateriaux()!=null)
						this.j1.addSommetRecup(r.getSommetArr());

					this.j1.utiliserUnJetons();
					
					this.frameDemarrage.getFrameChoix().getF1().refresh();
					
					this.tourJ1= !this.tourJ1;
					this.majFrameJoueur(this.j1, this);
				}
				else
				{
					r.setJoueur(this.j2);
					this.j2.addJetons(r.getNbTroncons());

					if (r.getSommetDep().getMateriaux()!=null)
						this.j2.addSommetRecup(r.getSommetDep());
					
					if (r.getSommetArr().getMateriaux()!=null)
						this.j2.addSommetRecup(r.getSommetArr());

					this.j2.utiliserUnJetons();
					
					this.frameDemarrage.getFrameChoix().getF2().refresh();
					
					this.tourJ1= !this.tourJ1;
					this.majFrameJoueur(this.j2, this);
				}	
			}
		}

		//this.frameDemarrage.getFrameChoix().getFrameJeu().majIHM();
		this.frameDemarrage.getFrameChoix().getFrameJeu().getPanelCarte().chargerImages(r);
		this.frameDemarrage.getFrameChoix().getFrameJeu().repaint();
	}

	/**
	 * Méthode qui renvoie le booléen correspondant au joueur qui doit jouer.
	 * @return vrai si c'est au tour du joueur 1, faux si c'est au tour du joueur 2.
	 */
	public boolean getTour()
	{
		return this.tourJ1;
	}

	/**
	 * Méthode qui renvoie le booléen correspondant à la capacité du joueur de jouer.
	 * @return vrai si c'est au tour du joueur j, faux si c'est au tour de l'autre joueur.
	 */	
	public boolean getTourJ( Joueur j )
	{
		return (j == this.getJoueur1() && this.tourJ1); 
	}

	/**
	 * Méthode qui initialise le jeu. Elle met donc en place la carte, ses chemins et ses sommets lorsque le fichier texte n'est pas utilisé.
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

			rndm = (int)(Math.random()*(this.lstMateriaux.size()));

			tmpMat = new Materiaux(this.lstMateriaux.remove(rndm));

			tmpZone = Integer.parseInt( tabNomSmt[cpt].substring( 1, 2 ) );
			this.tabSommet.add( new Sommet( tmpZone, tmpCoul, tabCooX[cpt], tabCooY[cpt], tmpMat, false, null ) );

		}
		this.tabSommet.add( new Sommet(0, "DEPART", 442, 475, null, true, null));

		//Ajout de la zone de départ
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
	 * Méthode qui affecte les JetonsPossessions aux joueurs.
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
	 * Renvoie le joueur n°2
	 * @return le joueur 2
	 */
	public Joueur getJoueur2 ()
	{
		return this.j2;
	}

	/**
	 * Renvoie le joueur n°2
	 * @return le joueur 2
	 */
	public void majFrameJoueur( Joueur j , Controleur ctrl )
	{
		if( j == this.j1 )
			this.frameDemarrage.getFrameChoix().getF1().majTitre(this.j1, this);
		else
			this.frameDemarrage.getFrameChoix().getF2().majTitre(this.j2, this);
	}


	/**
	 * Méthode qui recherche un sommet dans la liste de sommets a partir de son numéro.
	 * @param numSmt le numéro du sommet
	 * @return le sommet correspondant au numéro
	 */
	public Sommet rechercheSommet(String numSmt)
	{
		int tailleNb = 1;

		try{
			if(Integer.parseInt(numSmt.charAt(1) + "") > -1); //Si un entier plus grand que 10
				tailleNb = 2;
		}catch (Exception e)
		{

		}

		for (Sommet s : this.tabSommet)
		{
			if (s.getNumSom() == Integer.parseInt(numSmt.substring(0, tailleNb)) && s.getNomCoul().equals(numSmt.substring(tailleNb)))
				return s;


		}
		return null;
	}

	public Route rechercheRoute(Sommet smtDep, Sommet smtArr)
	{
		for(Route r : this.tabRoute)
		{
			if(r.getSommetDep() == smtDep && r.getSommetArr() == smtArr)
				return r;
		}
		return null;
	}

	public ArrayList<String> getLstMateriaux()
	{
		return this.lstMateriaux;
	}

	/**
	 * Méthode qui appelle la méthode de mise a jour de la carte.
	 */
	public void MajFrameModification() { this.frameDemarrage.getFrameModification().repaint(); }

	/**
	 * Méthode qui ajoute ou supprime une route dans la liste de route.
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

	/**
	 * Méthode qui ajoute ou supprime un sommet dans la liste de sommet.
	 * @param numSom le nunméro du sommet
	 * @param nomCoul la couleur du sommet
	 * @param x l'abscisse du sommet
	 * @param y l'ordonnée du sommet
	 * @param estDepart vrai si on ajoute un sommet de départ, faux si non
	 */
	public void ajouterOuSupprimerSommet( int numSom, String nomCoul, int x, int y,Materiaux materiaux ,boolean estDepart )
	{
		Sommet tempSommet = new Sommet(numSom, nomCoul, x, y, materiaux , estJeu,null) ;
		boolean tempEstSup = false;

		for ( Sommet rt : this.tabSommet )
		{
			if ( rt.getNumSom() == numSom &&  rt.possede(x, y) && rt.getDepart() == estDepart)
			{
				ArrayList<Route> tmpR = new ArrayList<>(this.tabRoute.size()/2);

				for(Route r : this.tabRoute)
				{
					if(r.getSommetArr() == rt || r.getSommetDep() == rt)
					{
						tmpR.add(r);
					}
				}


				for(Route r : tmpR)
				{
					this.tabRoute.remove(r);
				}

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

	/**
	 * Méthode qui recherche et renvoie le sommet qui a l'id passe en parametre.
	 * @param idsom id du sommet
	 * @return le sommet qui dispose de cette id.
	 */
	public Sommet getSommet(int idsom)
	{

		for ( Sommet s : this.tabSommet )
		{
			if ( s. )
				return s;
		}
		return null;
	}


	public void reInit() throws IOException {
		this.tabSommet = new ArrayList<Sommet>(30);
		this.tabRoute  = new ArrayList<Route>(40);
		this.init();
	}

	public void supprimerTout() throws IOException
	{
		this.tabSommet = new ArrayList<Sommet>(30);
		this.tabSommet.add(new Sommet(0,"DEPART", 500, 500, null, true,null));
		this.tabRoute  = new ArrayList<Route>(40);
		this.editionFichier.sauvegarde();
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

	public void setElementsTheme(String[] elements)
	{
		this.elementsTheme = elements;
	}

	public String[] getElementsTheme()
	{
		return this.elementsTheme;
	}

	public String getNomThemePrincipal()
	{
		return this.elementsTheme[0];
	}

	public String getNomThemeJ1()
	{
		return this.elementsTheme[1];
	}

	public String getNomThemeJ2()
	{
		return this.elementsTheme[2];
	}

	public String getNomThemeSommet()
	{
		return this.elementsTheme[3];
	}

	public String getNomThemeRoute()
	{
		return this.elementsTheme[4];
	}

	/**
	 * Méthode principale de la classe Controleur. Elle lance l'application.
	 * @param arg les arguments de la méthode
	 */
	public static void main (String[] arg) throws IOException
	{
		new Controleur();
	}
}