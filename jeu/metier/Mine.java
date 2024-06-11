package jeu.metier;

import java.util.ArrayList;

public class Mine
{

    private int     numMine;
    private String  nomCoul;
    private boolean depart;

    private int x;
    private int y;

	private ArrayList<Route> routes;


    private Materiaux materiaux;
    
    public Mine( int numMine, String nomCoul, int x, int y, Materiaux materiaux, boolean estDepart )
    {
        this.numMine    = numMine;
        this.nomCoul    = nomCoul;
        this.materiaux  = materiaux;
        this.x          = x;
        this.y          = y;
        this.depart     = estDepart;
		this.routes = new ArrayList<>(10);
    }

    public int       getNumMine()   { return this.numMine;   }
    public String    getNomCoul()   { return this.nomCoul;   }
    public int       getX()         { return this.x;         }
    public int       getY()         { return this.y;         }
    public Materiaux getMateriaux() { return this.materiaux; }
    public boolean   getDepard()    { return this.depart;    }

	public void addRoute(Route r)
	{
		this.routes.add(r);
	}

	public Route getRoute(int i)
	{
		return this.routes.get(i);
	}

	public ArrayList<Route> getTabRoute()
	{
		return this.routes;
	}


	public Materiaux prendreMateriaux ()
	{
		Materiaux temp=this.materiaux;

		this.materiaux=null;
		return temp;
	}

    public String    toString()     { return "Num : " + this.numMine + " Coul : " + this.nomCoul + " Depart " + this.depart + " x : " + this.x + " y : " + this.y + " Matériau : " + this.materiaux + "\n"; }

}