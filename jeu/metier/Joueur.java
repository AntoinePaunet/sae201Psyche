public class Joueur
{

	private FrameJoueur plateauIndividuel;

	private String nomJoueur;

	public Joueur(FrameJoueur plateauIndividuel, String nomJoueur)
	{

		this.plateauIndividuel = plateauIndividuel;
		this.nomJoueur = nomJoueur;

	}

	public String getNomJoueur        () { return this.nomJoueur        ; }
	public String getPlateauIndividuel() { return this.plateauIndividuel; }
	






}