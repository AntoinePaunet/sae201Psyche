package jeu.ihm;

import jeu.Controleur;

import javax.swing.JPanel;
import javax.swing.JTable;


/**
 * Cette classe affiche le score.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelScore extends JPanel 
{
    private Controleur ctrl;
    private JTable table;

    /**
     * Constructeur du Panel d'édition des routes.
     */
    public PanelScore(Controleur ctrl) 
	{
        this.ctrl = ctrl;
		this.table = new JTable(23,3);
		this.add(this.table);

        this.setVisible(true);
    }

   
}