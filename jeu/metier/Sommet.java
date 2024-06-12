package jeu.metier;

import java.util.ArrayList;

/**
 * Cette classe d'instansié les mines
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Sommet
{

    private int     numSom;
    private String  nomCoul;
    private boolean depart;

    private int x;
    private int y;

	private ArrayList<Route> routes;


    private Materiaux materiaux;

    /**
	 * Constructeure
	 *
	 * @param numMine     numéro de la mine
	 * @param nomCoul     nom de la couleur de la mine
	 * @param x           postion x
	 * @param y           postion y
	 * @param materiaux   matérieux assignée a la mine
	 * @param estDepart   boolean qui permt de savoir si c'est la frame de départ du jeux
	 * 
	 * 
	 */
    
    public Sommet( int numSom, String nomCoul, int x, int y, Materiaux materiaux, boolean estDepart )
    {
        this.numSom     = numSom;
        this.nomCoul    = nomCoul;
        this.materiaux  = materiaux;
        this.x          = x;
        this.y          = y;
        this.depart     = estDepart;
		this.routes = new ArrayList<>(10);
    }

    public int       getNumSom()    { return this.numSom;    }
    public String    getNomCoul()   { return this.nomCoul;   }
    public int       getX()         { return this.x;         }
    public int       getY()         { return this.y;         }
    public Materiaux getMateriaux() { return this.materiaux; }
    public boolean   getDepard()    { return this.depart;    }


	public void addRoute(Route r)
	{
		this.routes.add(r);
	}

    /**
	 * @param  i numéro de la route
	 * @return retourne la route
	 * 
	 */
	public Route getRoute(int i)
	{
		return this.routes.get(i);
	}

    /**
	 * 
	 * @return retourne la tableau de routes
	 * 
	 */
	public ArrayList<Route> getTabRoute()
	{
		return this.routes;
	}

     /**
	 * 
	 * @return retourne le matérieau prit
	 * 
	 */
	public Materiaux prendreMateriaux ()
	{
		Materiaux temp=this.materiaux;

		this.materiaux=null;
		return temp;
	}

    public String    toString()     { return "Num : " + this.numSom + " Coul : " + this.nomCoul + " Depart " + this.depart + " x : " + this.x + " y : " + this.y + " Matériau : " + this.materiaux + "\n"; }

}