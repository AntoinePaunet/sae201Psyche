package jeu.metier;

public class Mine
{

    private int    numMine;
    private String nomCoul;
    
    public Mine( int numMine, String nomCoul )
    {
        this.numMine = numMine;
        this.nomCoul = nomCoul;
    }

    public int    getNumMine() { return this.numMine; }
    public String getNomCoul() { return this.nomCoul; }

    

}