package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Route;
import jeu.metier.Sommet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import java.awt.event.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe créé le panel pour modifier ou créer les routes.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelRoutes extends JPanel implements ActionListener
{
    private Controleur        ctrl;
    private JTable            table;
    private JScrollPane       scrollPaneLstVilleDep;
    private JScrollPane       scrollPaneLstVilleArrive;
    private JPanel            panelInput;
    private JTextField        inputRoute;
    private JButton           btnAjouteRoute;
    private JComboBox<String> lstVilleDep;
    private JComboBox<String> lstVilleArrive;

	/**
	 * Constructeur du Panel d'édition des routes.
	 */
    public PanelRoutes( Controleur ctrl)
    {

        this.ctrl = ctrl;
        this.setLayout( new GridLayout(  1 , 2 ) );  
        this.ajouterTabRoute();
        this.initListe();
        this.panelInput();
        this.setVisible( true );
    }


	/**
	 * Méthode qui initialise tous les composants du panel d'édition des routes.
	 */
    public void panelInput()
    {
        this.panelInput = new JPanel() ;
    
        this.panelInput.setLayout( new GridLayout( 5 , 2 ) );
        
        this.inputRoute = new JTextField();

        this.btnAjouteRoute = new JButton( "Ajouter/Supprimer" );
        
        this.panelInput.add( new JLabel("VilleDep : ") );
        this.panelInput.add( scrollPaneLstVilleDep    );
        
        this.panelInput.add( new JLabel("VilleArr : ") );
        this.panelInput.add( scrollPaneLstVilleArrive );
       
        this.panelInput.add( new JLabel("Nombre Troncons : ") );
        this.panelInput.add( inputRoute );

		this.panelInput.add( new JLabel( ) ) ;

        this.panelInput.add( btnAjouteRoute );
        
        btnAjouteRoute.addActionListener( this );
        
        this.add( panelInput );

    }

	/**
	 * Initialise la liste contenant toutes les routes.
	 */
    public void initListe()
    {

         ArrayList<Sommet> tabVille = this.ctrl.getTabSommet() ;
        
        // Liste ville
        String[] data = new String[ tabVille.size() ];

        for ( int cpt = 0; cpt < data.length; cpt++ )
        {

            data[ cpt ] = ( tabVille.get( cpt ).getNumSom( ) ) + " "  + tabVille.get( cpt ).getNomCoul();

        }

        lstVilleDep    = new JComboBox<>(data);
        lstVilleArrive = new JComboBox<>(data);

        // Add the list to a scroll pane (allows scrolling if needed)
        this.scrollPaneLstVilleDep    = new JScrollPane( lstVilleDep    );
        this.scrollPaneLstVilleArrive = new JScrollPane( lstVilleArrive );

        if (this.panelInput != null )
        { 
            this.panelInput.add( scrollPaneLstVilleDep    );
            this.panelInput.add( scrollPaneLstVilleArrive );
            this.repaint();
        }

    }

	/**
	 * Méthode qui ajoute une route à la liste de routes de la carte.
	 */
    public void ajouterTabRoute()
    {
        // Tableau contenant tout les routes
        List<Route> lstRoute = ctrl.getTabRoute();
        String[][]  data     = new String[ lstRoute.size() ][ 3 ];
        
        // nom des colonnes
        String[] columnNames = {"SommetDep", "SommetArr", "nbTroncons"};
        
        for ( int lig = 0; lig < ( lstRoute ).size(); lig++ )
        {
            
            data[ lig ][ 0 ] = ( lstRoute.get( lig ) ).getSommetDep().getNumSom() + "";
            data[ lig ][ 1 ] = ( lstRoute.get( lig ) ).getSommetArr().getNumSom() + "";
            data[ lig ][ 2 ] = ( lstRoute.get( lig ) ).getNbTroncons() + ""  ;

        }

        // Create a table model and set the data and column names
        DefaultTableModel model = new DefaultTableModel( data, columnNames );

        this.table = new JTable( model );

        // Add the table to a scroll pane (allows scrolling if needed)
        JScrollPane scrollPane = new JScrollPane( table );

        this.add( scrollPane );
        this.repaint();
    }

	/**
	 * Réalise une action lorsqu'on clique sur un élément activable.
	 * @param e est un événement lié à un composant du panel
	 */
    public void actionPerformed( ActionEvent e )
    {
        Sommet vDep = null, vArr = null;
       	Integer tr = Integer.parseInt( inputRoute.getText() );

		if( inputRoute.getText()==null ) return ;


        for ( Sommet ville : this.ctrl.getTabSommet() )
        {
            if ( ( (String)this.lstVilleArrive.getSelectedItem( ) ).equals( ( ville.getNumSom() + " "  + ville.getNomCoul() ) ) ){ vArr = ville; }
            
            if ( ( (String)this.lstVilleDep.getSelectedItem( ) ).equals( ( ville.getNumSom() + " "  + ville.getNomCoul() ) ) ){ vDep = ville; }

        }

        this.ctrl.ajouterOuSupprimerRoute(vDep, vArr, tr);

        this.ctrl.MajFrameModification();

    }
}