package jeu.metier;

import jeu.Controleur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditionFichier
{
	private Controleur ctrl;
	private ArrayList<Sommet> tabSommet;
	private ArrayList<Route>  tabRoute;
	private File fichier;

	/**
	 * Constructeur de la classe EditionFichier
	 * @param estJeu le Jeu
	 */
	public EditionFichier(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.tabSommet = new ArrayList<>(30);
		this.tabRoute  = new ArrayList<>(60);
		this.fichier = null;
	}

	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public ArrayList<Sommet> getTabSommet()
	{
		return this.tabSommet;
	}

	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public ArrayList<Route> getTabRoute()
	{
		return this.tabRoute;
	}

	/**
	 * Méthode qui initialise un writer afin d'écrire la carte dans un fichier.
	 * @param fichier le fichier dans lequel le writer va écrire
	 */
	public void initFicher(File fichier)
	{
		try( BufferedWriter writer = new BufferedWriter( new FileWriter(fichier) ) )
		{
			writer.write("[SOMMET]\n\n[ROUTES]\n");
			//System.out.println("Fichier de données créé : " + fichier.getAbsolutePath());
		}
		catch( IOException e ) {}
	}

	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public boolean estVide(String nomFichier) throws FileNotFoundException
	{
		try
		{
			FileReader fr = new FileReader(nomFichier);
			Scanner sc = new Scanner(fr);

			sc.nextLine();
			if (sc.nextLine().equals(""))
			{
				return true;
			}
		}
		catch( Exception e ) { return true; }
		return false;
	}

	/**
	 * Méthode qui initialise un lecteur de fichierafin d'y lire la carte du jeu.
	 * @param nomFichier le fichier dans lequel le reader va lire
	 */
	public void lectureFichier(String nomFichier, boolean importer) throws IOException
	{
		File tmpFichier;
		if(estVide(nomFichier))
		{
			nomFichier = "data.txt";
			this.fichier = new File(nomFichier);
			this.fichier.createNewFile();
			this.initFicher(this.fichier);
			this.ctrl.init();
			return;
		}

		tmpFichier = new File(nomFichier);
		fichier = new File("data.txt");

		try
		{
			FileReader fr;
			if(importer)
			{
				fr = new FileReader(tmpFichier);
				this.supprimer();
			}else
			{
				fr = new FileReader(fichier);
			}
			Scanner sc = new Scanner(fr);

			// Vider les tableaux pour ne pas refaire trop de variables
			this.tabSommet = new ArrayList<Sommet>(ctrl.getTabSommet().size());
			this.tabRoute  = new ArrayList<Route>(ctrl.getTabRoute().size());

			int etapeLecture = 0;

			while (sc.hasNextLine())
			{
				String ligne = sc.nextLine();

				if (!ligne.isEmpty())
				{
					if (ligne.equals("[SOMMET]"))
					{
						etapeLecture = 1;
						if (sc.hasNextLine())
							ligne = sc.nextLine();
					}
					else if (ligne.equals("[ROUTES]"))
					{
						etapeLecture = 2;
						if (sc.hasNextLine())
						{
							ligne = sc.nextLine();
						}
					}

					if (etapeLecture == 1 && !ligne.equals("[SOMMET]"))
					{
						if (!ligne.isEmpty())
						{
							lireSommet(ligne);
							//System.out.println(ligne);
						}
					}
					if (etapeLecture == 2)
					{
						if (!ligne.isEmpty() && !ligne.equals("[ROUTES]"))
						{
							lireRoute(ligne);
							//System.out.println(ligne);
						}
					}

				}
			}
			sc.close();
			fr.close();
		}
		catch( Exception exp ) { exp.printStackTrace();	}

		//System.out.println("Nb sommet " + tabSommet);
	}

	/**
	 * Methode qui lit le fichier theme.data, qui permet de récupérer chaque nom de thème.
	 * @return une List<String> qui contient chaque nom de thème.
	 */
	public List<String> lectureNomTheme()
	{
		FileReader fr;
		List<String> nomThemes = new ArrayList<>();

		try
		{
			fr = new FileReader ( "theme.txt" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() )
			{
				String ligneTheme = sc.nextLine();
				String[] elements = ligneTheme.split("\t");
				if (elements.length > 0)
					nomThemes.add(elements[0]);
			}

			fr.close();
		}
		catch (Exception e){ e.printStackTrace(); }
		return nomThemes;
	}

	/**
	 * Méthode qui crée et ajoute un sommet dans la liste des sommets a partir d'une ligne d'un fichier texte.
	 * @param ligne la ligne où se trouve les données du sommet
	 */
	public void lireSommet(String ligne)
	{
		String[] smtInfo = ligne.split("\t");

		int num = Integer.parseInt(smtInfo[0]);
		String nom = smtInfo[1];
		int x = Integer.parseInt(smtInfo[2]);
		int y = Integer.parseInt(smtInfo[3]);
		String nomMat = smtInfo[4];

		if (nomMat.equals("null"))
			this.tabSommet.add(new Sommet(num, nom, x, y, null, true));
		else
			this.tabSommet.add(new Sommet(num, nom, x, y, new Materiaux(nomMat), true));
		
		this.ctrl.setTabSommet(this.tabSommet);
	}

	/**
	 * Méthode qui crée et ajoute une route dans la liste des routes a partir d'une ligne d'un fichier texte.
	 * @param ligne la ligne où se trouve les données de la route
	 */
	public void lireRoute(String ligne)
	{
		String[] routeInfo = ligne.split("\t");

		int nbTroncon = Integer.parseInt(routeInfo[0]);

		Sommet smtA = this.ctrl.rechercheSommet(routeInfo[1]);
		Sommet smtB = this.ctrl.rechercheSommet(routeInfo[2]);

		

		if (smtA != null && smtB != null) // Si la ville recherché n'existe plus
		{
			Route r = new Route(smtA, smtB, nbTroncon);

			this.tabRoute.add(r);
			this.ctrl.setTabRoute(this.tabRoute);
			smtA.addRoute(r);
			smtB.addRoute(r);
		}
	}


	/**
	 * Méthode qui écrit et ajoute un sommet dans le fichier texte corrspondant à la carte.
	 * @param numSmt le numéro du sommet
	 * @param nomCoul la couleur du sommet
	 * @param x l'abcisse du sommet sur la carte
	 * @param y l'ordonnée du sommet sur la carte
	 * @param materiaux la ressource se trouvant sur le sommet
	 * @param estDepart si le sommet est celui sur lequel les jouers commencent
	 */
	public void ecrireSommet(int numSmt, String nomCoul, int x, int y, Materiaux materiaux, boolean estDepart) throws IOException
	{
		FileReader fr = new FileReader("data.txt");
		Scanner sc = new Scanner(fr);

		String donnesFichier = "";

		while (sc.hasNextLine())
			donnesFichier += sc.nextLine() + "\n";

		String donneesVilles = donnesFichier.substring(donnesFichier.indexOf("[SOMMET]"),
				donnesFichier.indexOf("\n["));
		String donneesRoutes = donnesFichier.substring(donnesFichier.indexOf("[ROUTES]"));

		if(!(materiaux == null))
			donnesFichier = donneesVilles + (numSmt + "\t" + nomCoul + "\t" + x + "\t" + y + "\t" + materiaux.getNom() + "\t" + estDepart + "\n\n") + donneesRoutes;
		else
			donnesFichier = donneesVilles + (0      + "\t" + null 		 + "\t" + x + "\t" + y + "\t" + null			   + "\t" + true + "\n\n") + donneesRoutes;
		BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));

		try
		{
			writer.write(donnesFichier);
		}
		catch( Exception e ) { e.printStackTrace();	}

		writer.close();
		sc.close();
	}

	/**
	 * Méthode qui écrit et ajoute une route dans le fichier texte corrspondant à la carte.
	 * @param smtA le sommet de départ de la route
	 * @param smtB le sommet d'arrivée de la route
	 * @param nbTroncons le nombre de troncons de la route
	 */
	public void ecrireRoute(Sommet smtA, Sommet smtB, int nbTroncons) throws IOException
	{
		FileReader fr = new FileReader("data.txt");
		Scanner sc = new Scanner(fr);

		String donnesFichier = "";

		while (sc.hasNextLine())
			donnesFichier += sc.nextLine() + "\n";

		donnesFichier += (nbTroncons + "\t" + smtA.getNumSom()+smtA.getNomCoul() + "\t" + smtB.getNumSom()+ smtB.getNomCoul() + "\n");

		BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));

		try
		{
			writer.write(donnesFichier);
		}
		catch( Exception e ) { e.printStackTrace();	}

		writer.close();
		sc.close();
	}

	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public void supprimer()
	{
		try( BufferedWriter writer = new BufferedWriter( new FileWriter(this.fichier) ) )
		{
			writer.write("[SOMMET]\n\n[ROUTES]\n");
		}
		catch( IOException e ) {}
	}


	/**
	 * Méthode qui sauvagarde les modifications apportées au ficher texte en appelant les méthodes d'écriture.
	 */
	public void sauvegarde() throws IOException
	{
		this.supprimer();

		for(Route r : this.tabRoute)
			this.ecrireRoute(r.getSommetDep(), r.getSommetArr(), r.getNbTroncons());

		for(Sommet s : this.tabSommet)
			this.ecrireSommet(s.getNumSom(), s.getNomCoul(), s.getX(), s.getY(), s.getMateriaux(), false);
		
	}
}
