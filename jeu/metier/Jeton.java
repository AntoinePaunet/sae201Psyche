package jeu.metier;
public class Jeton
{
	private IRessource type;

	public Jeton(IRessource type)
	{
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
}