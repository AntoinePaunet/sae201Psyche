public class Jeton
{
	private static int nbJetonCorp;
	private static int nbJetonSynd;

	private Controleur ctrl;

	private IRessource type;

	public Jeton(IRessource type)
	{
		this.ctrl.
		this.type = type;
	}

	public IRessource getType()
	{
		return this.type;
	}

	public String toString()
	{
		String sRet = "";

		sRet = this.type.toString();
		return sRet;
	}

	public int x()
	{

	}

	public int y()
	{
		
	}
}