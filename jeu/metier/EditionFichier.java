package jeu.metier;

import jeu.Controleur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditionFichier {
	private Controleur ctrl;
	private ArrayList<Sommet> tabSommet;
	private ArrayList<Route> tabRoute;
	private File fichier;
	private String emplacementData = "./jeu/src/data.txt";

	public EditionFichier(Controleur ctrl) {
		this.ctrl = ctrl;
		this.tabSommet = new ArrayList<>(30);
		this.tabRoute = new ArrayList<>(60);
		this.fichier = null;
	}

	public ArrayList<Sommet> getTabSommet() {
		return this.tabSommet;
	}

	public ArrayList<Route> getTabRoute() {
		return this.tabRoute;
	}

	/**
	 * Méthode qui initialise un writer afin d'écrire la carte dans un fichier.
	 * 
	 * @param fichier le fichier dans lequel le writer va écrire
	 */
	public void initFicher(File fichier) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
			writer.write("[SOMMET]\n\n[ROUTES]\n\n[JOUEUR1]\n\n[JOUEUR2] ");
			// System.out.println("Fichier de données créé : " + fichier.getAbsolutePath());
		} catch (IOException e) {
		}
	}

	public boolean estVide(String nomFichier) throws FileNotFoundException {
		try {
			FileReader fr = new FileReader(nomFichier);
			Scanner sc = new Scanner(fr);

			sc.nextLine();
			if (sc.nextLine().equals("")) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/**
	 * Méthode qui initialise un lecteur de fichierafin d'y lire la carte du jeu.
	 * 
	 * @param nomFichier le fichier dans lequel le reader va lire
	 */
	public void lectureFichier(String nomFichier, boolean importer) throws IOException {
		File tmpFichier;

		if (estVide(emplacementData))
		{
			this.fichier = new File(emplacementData);
			this.fichier.createNewFile();
			this.initFicher(this.fichier);
			this.ctrl.init();
			return;
		}

		tmpFichier = new File(nomFichier);
		fichier = new File(emplacementData);

		try {
			FileReader fr;

			if (importer) {
				fr = new FileReader(tmpFichier);
				this.supprimer();
			} else {
				fr = new FileReader(fichier);
			}
			Scanner sc = new Scanner(fr);

			// Vider les tableaux pour ne pas refaire trop de variables
			this.tabSommet = new ArrayList<Sommet>(ctrl.getTabSommet().size());
			this.tabRoute = new ArrayList<Route>(ctrl.getTabRoute().size());

			int etapeLecture = 0;

			while (sc.hasNextLine()) {
				String ligne = sc.nextLine();

				if (!ligne.isEmpty()) {
					if (ligne.equals("[SOMMET]")) {
						etapeLecture = 1;
						if (sc.hasNextLine())
							ligne = sc.nextLine();
					} else if (ligne.equals("[ROUTES]")) {
						etapeLecture = 2;
						if (sc.hasNextLine()) {
							ligne = sc.nextLine();
						}
					}

					if (etapeLecture == 1 && !ligne.equals("[SOMMET]")) {
						if (!ligne.isEmpty()) {
							lireSommet(ligne);
							// System.out.println(ligne);
						}
					}
					if (etapeLecture == 2) {
						if (!ligne.isEmpty() && !ligne.equals("[ROUTES]")) {
							lireRoute(ligne);
							//System.out.println(ligne);
						}
					}

				}
			}
			sc.close();
			fr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		Controleur.nbSommets = this.tabSommet.get(this.tabSommet.size()-1).getId() + 1;
		// System.out.println("Nb sommet " + tabSommet);
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
			fr = new FileReader ( "./jeu/src/theme.txt" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() )
			{
				String ligneTheme = sc.nextLine();
				String[] elementsTheme = ligneTheme.split("\t");
				if (elementsTheme.length > 0)
					nomThemes.add(elementsTheme[0]);
			}

			fr.close();
		}
		catch (Exception e){ e.printStackTrace(); }
		return nomThemes;
	}

	/**
	 * Méthode qui permet de changer les nom des éléments en fonction du thème choisi.
	 * @param index permet de récupérer la bonne ligne du fichier.
	 */
	public void initTheme(int index)
	{
		FileReader fr;
		int cpt;
		String ligneTheme = null;

		cpt = 0;
		try
		{
			fr = new FileReader ( "./jeu/src/theme.txt" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() && cpt < index )
			{
				ligneTheme = sc.nextLine();

				cpt++;
			}
			String[] elementsTheme = ligneTheme.split("\t");
			ctrl.setElementsTheme(elementsTheme);

			fr.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}


	/**
	 * Méthode qui crée et ajoute un sommet dans la liste des sommets a partir d'une
	 * ligne d'un fichier texte.
	 * 
	 * @param ligne la ligne où se trouve les données du sommet
	 */
	public void lireSommet(String ligne) {
		String[] smtInfo = ligne.split("\t");

		int num = Integer.parseInt(smtInfo[0]);
		String nom = smtInfo[1];
		int x = Integer.parseInt(smtInfo[2]);
		int y = Integer.parseInt(smtInfo[3]);
		String nomMat = smtInfo[4];
		int id = Integer.parseInt(smtInfo[7]);

		if ( smtInfo[5].equals( "J1" ) )
			this.tabSommet.add( new Sommet( num, nom, x, y, new Materiaux( nomMat ), false,this.ctrl.getJoueur1(), id ) );
		else
			if ( smtInfo[5].equals( "J2" ) )
				this.tabSommet.add( new Sommet( num, nom, x, y, new Materiaux( nomMat ), false, this.ctrl.getJoueur2(), id ) );
			else
				this.tabSommet.add(new Sommet(num, nom, x, y, new Materiaux(nomMat), false, null, id));


		this.ctrl.setTabSommet(this.tabSommet);
	}

	/**
	 * Méthode qui crée et ajoute une route dans la liste des routes a partir d'une
	 * ligne d'un fichier texte.
	 * 
	 * @param ligne la ligne où se trouve les données de la route
	 */
	public void lireRoute(String ligne) {
		String[] routeInfo = ligne.split("\t");

		int nbTroncon = Integer.parseInt(routeInfo[0]);

		Sommet smtA = this.ctrl.rechercheSommet(routeInfo[1]);
		Sommet smtB = this.ctrl.rechercheSommet(routeInfo[2]);

		

		if (smtA != null && smtB != null) // Si la ville recherché n'existe plus
		{
			Route r = new Route(smtA, smtB, nbTroncon);


			if ( routeInfo[3].equals("J1") )
				r.setJoueur( this.ctrl.getJoueur1() );
			else
				if ( routeInfo[3].equals("J2") )
					r.setJoueur( this.ctrl.getJoueur2() );
		
			this.tabRoute.add(r);
			this.ctrl.setTabRoute(this.tabRoute);
			smtA.addRoute(r);
			smtB.addRoute(r);
		}
	}

	/**
	 * Méthode qui écrit et ajoute un sommet dans le fichier texte corrspondant à la
	 * carte.
	 * 
	 * @param numSmt    le numéro du sommet
	 * @param nomCoul   la couleur du sommet
	 * @param x         l'abcisse du sommet sur la carte
	 * @param y         l'ordonnée du sommet sur la carte
	 * @param materiaux la ressource se trouvant sur le sommet
	 * @param estDepart si le sommet est celui sur lequel les jouers commencent
	 * @param joueur    0 si pas de joueur. 1 ou 2 pour les joueur 1 ou 2
	 */
	public void ecrireSommet(int numSmt, String nomCoul, int x, int y, Materiaux materiaux, boolean estDepart, int joueur, int id)
			throws IOException {
		FileReader fr = new FileReader(emplacementData);
		Scanner sc = new Scanner(fr);

		String donnesFichier = "";

		while (sc.hasNextLine())
			donnesFichier += sc.nextLine() + "\n";

		String donneesVilles = donnesFichier.substring(donnesFichier.indexOf("[SOMMET]"),
				donnesFichier.indexOf("\n["));
		String donneesRoutes = donnesFichier.substring(donnesFichier.indexOf("[ROUTES]"));

		if (materiaux != null && !(nomCoul.equals("DEPART"))) {
			donnesFichier = donneesVilles + (numSmt + "\t" + nomCoul + "\t" + x + "\t" + y + "\t" + materiaux.getNom()
								+ "\t" + estDepart + "\t" + "J" + joueur +  "\t" + id + "\n\n") + donneesRoutes;
		}
		else
			donnesFichier = donneesVilles + (0 + "\t" + "DEPART" + "\t" + x + "\t" + y + "\t" + null + "\t" + true + "\t" + "J0" + "\t" + 0 + "\n\n")
					+ donneesRoutes;
		BufferedWriter writer = new BufferedWriter(new FileWriter(emplacementData));

		try {
			writer.write(donnesFichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		writer.close();

		sc.close();
	}

	/**
	 * Méthode qui écrit et ajoute une route dans le fichier texte corrspondant à la
	 * carte.
	 * 
	 * @param smtA       le sommet de départ de la route
	 * @param smtB       le sommet d'arrivée de la route
	 * @param nbTroncons le nombre de troncons de la route
	 * @param joueur     Le numéro du joueur soit 1 ou 2. 0 dans le cas ou le sommet na pas de joueur
	 * 
	 */
	public void ecrireRoute(Sommet smtA, Sommet smtB, int nbTroncons, int joueur) throws IOException {
		FileReader fr = new FileReader(emplacementData);
		Scanner sc = new Scanner(fr);

		String donnesFichier = "";

		while (sc.hasNextLine())
			donnesFichier += sc.nextLine() + "\n";

		if ( joueur == 0 )
			donnesFichier += (nbTroncons + "\t" + smtA.getId() + "\t" + smtB.getId() + "\t" + "J0" + "\n");

		if ( joueur == 1 )
			donnesFichier += (nbTroncons + "\t" + smtA.getId() + "\t" + smtB.getId() + "\t" + "J1" + "\n");

		if ( joueur == 2 )
			donnesFichier += (nbTroncons + "\t" + smtA.getId() + "\t" + smtB.getId() + "\t" + "J2" + "\n");

		BufferedWriter writer = new BufferedWriter(new FileWriter(emplacementData));

		try {
			writer.write(donnesFichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		writer.close();
		sc.close();
	}

	public void supprimer() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fichier))) {
			writer.write("[SOMMET]\n\n[ROUTES]\n");
		} catch (IOException e) {
		}
	}

	/**
	 * Méthode qui sauvagarde les modifications apportées au ficher texte en
	 * appelant les méthodes d'écriture.
	 */
	public void sauvegarde() throws IOException {
		this.supprimer();
		this.tabSommet = ctrl.getTabSommet();
		this.tabRoute = ctrl.getTabRoute();


		for (Route r : this.tabRoute)
		{
			if ( this.ctrl.getJoueur1().equals(r.getJoueur()) )
				this.ecrireRoute(r.getSommetDep(), r.getSommetArr(), r.getNbTroncons(),1);
			else
				if ( this.ctrl.getJoueur2().equals(r.getJoueur()) )
					this.ecrireRoute(r.getSommetDep(), r.getSommetArr(), r.getNbTroncons(),2);
				else
					this.ecrireRoute(r.getSommetDep(), r.getSommetArr(), r.getNbTroncons(),0);

		}

		for (Sommet s : this.tabSommet) 
		{
			if ( this.ctrl.getJoueur1().equals(s.getJoueur()) )
				this.ecrireSommet(s.getNumSom(), s.getNomCoul(), s.getX(), s.getY(), s.getMateriaux(), false,1, s.getId());
			else
				if ( this.ctrl.getJoueur2().equals(s.getJoueur()) )
					this.ecrireSommet(s.getNumSom(), s.getNomCoul(), s.getX(), s.getY(), s.getMateriaux(), false,2, s.getId());
				else
					this.ecrireSommet(s.getNumSom(), s.getNomCoul(), s.getX(), s.getY(), s.getMateriaux(), false,0, s.getId());
			
		}
	}

	public void ecrireScenario( int numJoueur, int nbScenario, int idSommetDep, int idSommeArr, int nbTroncon ) throws IOException
	{
		String tmpDonnes = "";
		String emplacement = "./jeu/src/scenario/"+ nbScenario + ".txt";

		FileReader fr = new FileReader(emplacement);

		BufferedWriter writer = new BufferedWriter(new FileWriter(emplacementData));

		if ( numJoueur == 1  )
			tmpDonnes = "J1 " + idSommetDep + " -> " + idSommeArr + " " + nbTroncon;
		if ( numJoueur == 2 )
			tmpDonnes = "J2 " + idSommetDep + " -> " + idSommeArr + " " + nbTroncon;



		try {
			writer.write( tmpDonnes );
		} catch (Exception e) {
			e.printStackTrace();
		}

		writer.close();

	}

	public void lireScenario( int nbScenario, int numEtape) throws IOException
	{
		try {
			FileReader fr = new FileReader("./jeu/src/scenario/"+ nbScenario + ".txt");
			Scanner sc = new Scanner(fr);

			int etapeLecture = 0;

			while (sc.hasNextLine()) 
			{
				String ligne = sc.nextLine();


				if ( etapeLecture == numEtape )
				{
					String[] action = ligne.split(" ");

					this.ctrl.ajouterOuSupprimerRoute( this.ctrl.getSommet( Integer.parseInt( action[1] ) ), this.ctrl.getSommet( Integer.parseInt( action[3] ) ), Integer.parseInt( action[4] ));

					break;
				}
			}

			sc.close();
			fr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}



	}
	




}
