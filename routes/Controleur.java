public class Controleur 
{
	private Plateau j1;
	private Plateau j2;

	public Controleur()
	{
		this.j1= new Plateau ();
		this.j2= new Plateau ();
	}

	public static void main (String[] arg)
	{
		new Controleur();
	}
}