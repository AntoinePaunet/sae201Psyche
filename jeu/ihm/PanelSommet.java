package jeu.ihm;

import jeu.Controleur;
import jeu.metier.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.event.*;
import java.io.IOException;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

/**
 * Cette classe créé le panel pour modifier ou créer les sommets.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class PanelSommet extends JPanel  implements ActionListener
{
	private Controleur ctrl             ;
	private JTable     table            ;
	private JPanel     panelInput       ;
	private JTextField txtNumero        ;
	private JTextField txtNomCouleur    ;
	private JTextField txtX             ;
	private JTextField txtY             ;

	private JLabel     lblErreur        ;

	private JButton    btnAjouterSommet ;
	private JButton    btnModifierSommet;

	/**
	 * Constructeur du Panel de modification ou création des sommets.
	 * @param ctrl qui est le controleur qui gère l'application
	 */
	public PanelSommet( Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout( new GridLayout(  1, 2 ) );
		this.ajouterTabSommet();;
		this.panelInput();
		//this.ajouterBoutonModifier();
		this.setVisible( true );
	}


	/**
	 * Methode qui permet de créer un panel contenant les textfields et labels du PanelSommet
	 */
	public void panelInput()
	{
		this.panelInput    = new JPanel();
	
		this.txtNumero     = new JTextField();
		this.txtNomCouleur = new JTextField();
		this.txtX          = new JTextField();
		this.txtY          = new JTextField();
		
		this.panelInput.setLayout( new GridLayout( 5 , 2 ) );
			

		this.btnAjouterSommet = new JButton( "Ajouter" );
			
		
		this.panelInput.add( new JLabel("Numero : "),  JPanel.RIGHT_ALIGNMENT );
		this.panelInput.add( this.txtNumero    );

		this.panelInput.add( new JLabel("Couleur : "),  JPanel.RIGHT_ALIGNMENT );
		this.panelInput.add( this.txtNomCouleur);
				
		this.panelInput.add( new JLabel("X : "      ),  JPanel.RIGHT_ALIGNMENT  );
		this.panelInput.add( this.txtX         );
		
		this.panelInput.add( new JLabel("Y : "      ) );
		this.panelInput.add( this.txtY         );

		this.panelInput.add(this.lblErreur = new JLabel(""));
		this.lblErreur.setFont(new Font("Arial", Font.BOLD, 15));
		this.panelInput.add( btnAjouterSommet );


			
		btnAjouterSommet.addActionListener( this );
				
		this.add( panelInput );

    }

	/**
	 * Methode qui permet de créer le tableau qui contient les sommets déjà existants
	 */
	public void ajouterTabSommet()
	{
		// Tableau contenant tout les routes
		List<Sommet> lstSommet = ctrl.getTabSommet();
		String[][]  data     = new String[ lstSommet.size() ][ 4 ];
			
		// nom des colonnes
		String[] columnNames = {"Numero", "Couleur", "X", "Y"};
			
		for ( int lig = 0; lig < ( lstSommet ).size(); lig++ )
		{
				
			data[ lig ][ 0 ] = ( lstSommet.get( lig ) ).getNumSom () + "";
			data[ lig ][ 1 ] = ( lstSommet.get( lig ) ).getNomCoul() ;
			data[ lig ][ 2 ] = ( lstSommet.get( lig ) ).getX() + ""  ;
			data[ lig ][ 3 ] = ( lstSommet.get( lig ) ).getY() + ""  ;

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
	 * Méthode qui ajoute un bouton
	 */
	public void ajouterBoutonModifier()
	{
		JPanel panelTest = new JPanel();
		
		this.btnModifierSommet = new JButton( "Modifier" );
		this.btnModifierSommet.addActionListener( this );
		panelTest.add(this.btnModifierSommet);
		this.add(panelTest);
	}
	
	/**
	 * Réalise une action lorsqu'un bouton ou le tableau est appuyé
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed( ActionEvent e )
	{
		String idVille =null;
		String nomVile =null;
		String x       =null;
		String y       =null;
		// Get the selected row index
		int selectedRowIndex = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRowIndex != -1) {
			// Get the table model
			TableModel model = table.getModel();

			// Get data from the selected row
			idVille = Integer.parseInt( (String) model.getValueAt(selectedRowIndex, 0 ) );
			x       = Integer.parseInt( (String) model.getValueAt(selectedRowIndex, 2 ) );
			y       = Integer.parseInt( (String) model.getValueAt(selectedRowIndex, 3 ) );
			nomVile = (String) model.getValueAt(selectedRowIndex, 1 );
		}
		else if ( !(this.txtNomCouleur.getText().isEmpty() ||
                    this.txtNumero.getText().isEmpty()     ||  
                    this.txtX.getText().isEmpty()          ||
                    this.txtY.getText().isEmpty() 
					)
				)
		{
			idVille = Integer.parseInt( (String) this.txtNumero.getText() );
			x       = Integer.parseInt( (String) this.txtX.getText()      );
			y       = Integer.parseInt( (String) this.txtY.getText()      );
			nomVile = (String) this.txtNomCouleur.getText()                ;

			
		}


		if (e.getSource() == this.btnAjouterSommet)
		{
			try
			{
				if ( this.txtNomCouleur.getText().isBlank() ||
					 this.txtNumero.getText().isBlank()     ||
					 this.txtX.getText().isBlank()          ||
					 this.txtY.getText().isBlank()            )
				{
					this.lblErreur.setText("<html> Tous les champs  <br> ne sont pas complétés. </html>");
				}
				else
				{
					this.lblErreur.setText("");

					this.ctrl.ecrireSommet(Integer.parseInt(this.txtNumero.getText()), this.txtNomCouleur.getText(),
					Integer.parseInt(this.txtX.getText()), Integer.parseInt(this.txtY.getText()),
					new Materiaux("AU"), false);
					System.out.println("sommet ajouté normalement");

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[]{ txtNumero.getText(),txtNomCouleur.getText(), txtX.getText(), txtY.getText()});

					this.ctrl.MajFrameModification();
					
				}
					
			}
			catch (IOException ex)
			{
				System.out.println("Erreur dans le catch");
				JOptionPane.showMessageDialog(this, "Erreur d'entrée/sortie : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
			
			}

		if (e.getSource() == this.btnModifierSommet)
		{
			//this.ctrl.modifierVille(  Integer.parseInt( x ), Integer.parseInt( y ), Integer.parseInt( idVille ) );
		}
		this.ctrl.MajFrameModification();
	}
}