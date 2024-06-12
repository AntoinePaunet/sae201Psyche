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
	private ArrayList<Sommet> 		tabSommet;
	private ArrayList<Route>		tabRoute;

	private boolean tourJ1;
	private boolean finPartie;
	public  FrameDemarrage frameDemarrage;

	public Controleur() throws IOException
	{
		this.j1      		= new Joueur ();
		this.j2      		= new Joueur ();
		this.tabSommet 		= new ArrayList<>(30);
		this.tabRoute		= new ArrayList<>(60);
		this.tourJ1=true;
		this.finPartie = false;

		this.init();
		this.initJetonPossession();
		this.frameDemarrage = new FrameDemarrage(this);
		this.lectureFichier(null);
		
	}

	public boolean estValide(Joueur j, Route r)
	{
		if (r.getJoueur()==null) {return false;}

		if (j==this.j1 && !this.tourJ1){return false;} 
		if (j==this.j2 && this.tourJ1){return false;} 
		

		//verifie si l'un des deux sommet deja pris ou non
		if (r.getSommetDep().getMateriaux()==null || r.getSommetArr().getMateriaux()==null)

		{
			return true ;
		}

		return false ;

	}


	private void init()
	{
		String tmpCoul = "";
		int    tmpZone = -1;
		int rndm;
		Materiaux tmpMat;

		String[] 	tabNomSeg = new String[] {"J1", "J5", "J2", "J3", "J4", "B2", "B3", "B4", "B6", "B8", "G0", "G1", "G2", "G3", "G4", "V2", "V3", "V4", "V6", "V8", "R1", "R2", "R3", "R4", "R5", "M1", "M2", "M3", "M4", "M5"};
		int[] 		tabCooX    = new int[]    { 336,  265,  317,  394,	 251,  336,	 414,  104,  156,  257,  510,  308,  346,  440,  575,  648,  111,  185,  353,  576,  696,  774,  200,  330,  427,  501,  606,  556,  696,  773};
		int[] 		tabCooY    = new int[]    { 92,  111,  187,  175,	 239,  284,  270,  322,  298,  322,  295,  382,  339,  366,  337,  319,  456,  440,  428,  433,  442,  443,  517,  542,  541,  519,  512,  586,  583,  582};


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
		for(int cpt = 0; cpt < tabNomSeg.length ; cpt++)
		{
			switch ( tabNomSeg[cpt].substring( 0, 1 ) )
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

			tmpZone = Integer.parseInt( tabNomSeg[cpt].substring( 1, 2 ) );
			this.tabSommet.add( new Sommet( tmpZone, tmpCoul, tabCooX[cpt], tabCooY[cpt], tmpMat, false ) );
		}

		//Ajout de la zone de départ
		this.tabSommet.add( new Sommet(0, null, 442, 475, null, true));

	}

	private void initJetonPossession()
	{
		for (int i=0; i<25; i++)
		{
			j1.addJetonPossession(new JetonPossession(j1));
			j2.addJetonPossession(new JetonPossession(j2));
		}
	}

	public Joueur getJoueur1 ()
	{
		return this.j1;
	}

	public Joueur getJoueur2 ()
	{
		return this.j2;
	}

	public void initFicher(File fichier)
	{
		try( BufferedWriter writer = new BufferedWriter( new FileWriter(fichier) ) )
		{
			writer.write("[SOMMET]\n\n[ROUTES]\n");
			System.out.println("Fichier de données créé : " + fichier.getAbsolutePath());
		}
		catch( IOException e ) {}
	}


	public void lectureFichier(String nomFichier) throws IOException
	{
		File fichier;

		if (nomFichier == null)
		{
			nomFichier = "data.txt";
			fichier = new File(nomFichier);
			fichier.createNewFile();
			this.initFicher(fichier);
			System.out.println("ok");
			return;
		}
		fichier = new File(nomFichier);
		System.out.println("ok2");
		

		try {
			System.out.println(nomFichier);
			FileReader fr = new FileReader(fichier);
			Scanner sc = new Scanner(fr);

			// Vider les tableaux pour ne pas refaire trop de variables
			tabSommet = new ArrayList<Sommet>(tabSommet.size());
			tabRoute  = new ArrayList<Route>(tabRoute.size());

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
							System.out.println(ligne);
						}
					}
					if (etapeLecture == 2) {
						if (!ligne.isEmpty() && !ligne.equals("[ROUTES]")) {
							lireRoute(ligne);
							System.out.println(ligne);
						}
					}

				}
			}
			sc.close();
			fr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}



		System.out.println(tabSommet);
	}


	public void lireSommet(String ligne) {
		String[] smtInfo = ligne.split("\t");

		int num = Integer.parseInt(smtInfo[0]);
		String nom = smtInfo[1];
		int x = Integer.parseInt(smtInfo[2]);
		int y = Integer.parseInt(smtInfo[3]);
		String nomMat = smtInfo[4];

		this.tabSommet.add(new Sommet(num, nom, x, y, new Materiaux(nomMat), false));
	}

	public void lireRoute(String ligne) {
		String[] routeInfo = ligne.split("\t");

		int nbTroncon = Integer.parseInt(routeInfo[2]);

		Sommet smtA = this.rechercheSommet(routeInfo[0]);
		Sommet smtB = this.rechercheSommet(routeInfo[1]);

		if (smtA != null && smtB != null) // Si la ville recherché n'existe plus
		{
			Route r = new Route(smtA, smtB, nbTroncon);

			this.tabRoute.add(r);
			smtA.addRoute(r);
			smtB.addRoute(r);
		}
	}



	public void ecrireSommet(int numSmt, String nomCoul, int x, int y, Materiaux materiaux, boolean estDepart) throws IOException
	{
		FileReader fr = new FileReader("data.txt");
		Scanner sc = new Scanner(fr);

		String donnesFichier = "";

		while (sc.hasNextLine())
			donnesFichier += sc.nextLine() + "\n";

		if (!donnesFichier.contains(numSmt+"")) // Vérification de doubles dans le fichier texte
		{
			String donneesVilles = donnesFichier.substring(donnesFichier.indexOf("[SOMMET]"),
					donnesFichier.indexOf("\n["));
			String donneesRoutes = donnesFichier.substring(donnesFichier.indexOf("[ROUTES]"));

			donnesFichier = donneesVilles + (numSmt + "\t" + nomCoul + "\t" + x + "\t" + y + "\t" + materiaux.getNom() + "\t" + estDepart + "\t" + "\n\n") + donneesRoutes;

			BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));

			try {
				this.tabSommet.add(new Sommet(numSmt, nomCoul, x, y, materiaux, estDepart)); // creation de la ville
				writer.write(donnesFichier);
			} catch (Exception e) {
				e.printStackTrace();
			}

			writer.close();
		}
		sc.close();
	}

	public void ecrireRoute(Sommet smtA, Sommet smtB, int nbTroncons) throws IOException {
		FileReader fr = new FileReader("data.txt");
		Scanner sc = new Scanner(fr);

		String donnesFichier = "";

		while (sc.hasNextLine())
			donnesFichier += sc.nextLine() + "\n";

		donnesFichier += (nbTroncons + "\t" + smtA.getNumSom() + "\t" + smtB.getNumSom() + "\n");

		BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));

		try {
			this.tabRoute.add(new Route(smtA, smtB, nbTroncons));
			writer.write(donnesFichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		writer.close();
		sc.close();
	}









	public Sommet rechercheSommet(String numSmt)
	{
		for (Sommet s : this.tabSommet)
		{

			if (s.getNumSom() == Integer.parseInt(numSmt))
				return s;
		}
		return null;
	}





	public static void main (String[] arg) throws IOException {
		Controleur ctrl = new Controleur();
		if(ctrl.tabSommet.size() >= 29)
		{
			ctrl.getJoueur1().addSommetRecup(ctrl.tabSommet.get(10));
			ctrl.getJoueur1().addSommetRecup(ctrl.tabSommet.get(16));
			ctrl.getJoueur1().addSommetRecup(ctrl.tabSommet.get(1 ));
			ctrl.getJoueur1().addSommetRecup(ctrl.tabSommet.get(22));
			ctrl.getJoueur1().addSommetRecup(ctrl.tabSommet.get(29));
			ctrl.getJoueur1().addSommetRecup(ctrl.tabSommet.get(10));
		}
	}

	public ArrayList<Sommet> getTabSommet() { return this.tabSommet; }
	public ArrayList<Route> getTabRoute  () { return this.tabRoute;  }
}