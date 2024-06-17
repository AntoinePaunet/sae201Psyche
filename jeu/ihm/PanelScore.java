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
public class PanelScore extends JPanel, AbstractTableModel
{
    private Controleur ctrl;
    private JTable table;
	private int [] tabJ1;
	private int [] tabJ2;

	private Object[][] tabDonnees;

    /**
     * Constructeur du Panel d'édition des routes.
     */
    public PanelScore(Controleur ctrl) 
	{
        this.ctrl = ctrl;

		this.ctrl.getJ1().score();
		this.ctrl.getJ1().scoreSommet();

		this.ctrl.getJ2().score();
		this.ctrl.getJ2().scoreSommet();

		this.tabJ1=this.ctrl.getJ1().getScore();
		this.tabJ2=this.ctrl.getJ2().getScore();

		this.table = new JTable(this.initDonnees());
		this.add(this.table);

        this.setVisible(true);
    }

	public 


   
}