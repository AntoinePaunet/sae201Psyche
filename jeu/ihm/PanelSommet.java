package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Couleur;
import jeu.metier.Materiaux;
import jeu.metier.Sommet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.event.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
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
	private JComboBox<String> lstCouleur;
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
		this.lstCouleur    = new JComboBox<String>(new String[]{"Vert","Jaune","Rouge","Bleu","Gris","Marron"});
		this.txtX          = new JTextField();
		this.txtY          = new JTextField();
		
		this.panelInput.setLayout( new GridLayout( 5 , 2 ) );
			

		this.btnAjouterSommet = new JButton( "Ajouter / Supprimer" );
			
		
		this.panelInput.add( new JLabel("Numero : "),  JPanel.RIGHT_ALIGNMENT );
		this.panelInput.add( this.txtNumero    );

		this.panelInput.add( new JLabel("Couleur : "),  JPanel.RIGHT_ALIGNMENT );
		this.panelInput.add( this.lstCouleur);
				
		this.panelInput.add( new JLabel("X : "      ),  JPanel.RIGHT_ALIGNMENT  );
		this.panelInput.add( this.txtX         );
		
		this.panelInput.add( new JLabel("Y : "      ) );
		this.panelInput.add( this.txtY         );

		this.panelInput.add(this.lblErreur = new JLabel(""));
		this.lblErreur.setFont(new Font("Arial", Font.BOLD, 15));
		this.panelInput.add( btnAjouterSommet );


			
		this.btnAjouterSommet.addActionListener( this );
				
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
	 * Réalise une action lorsqu'un bouton ou le tableau est appuyé
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed( ActionEvent e ) 
	{
		int idVille    =0;
		String nomVile =null;
		int x       =0;
		int y       =0;
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
		else if ( !((
						this.txtNumero.getText().isBlank()     ||
						this.txtX.getText().isBlank()          ||
						this.txtY.getText().isBlank()         )&&
						selectedRowIndex == -1
					)
				)
		{
			try{
				idVille = Integer.parseInt( (String) this.txtNumero.getText() );
				x       = Integer.parseInt( (String) this.txtX.getText()      );
				y       = Integer.parseInt( (String) this.txtY.getText()      );
				nomVile = (String) this.lstCouleur.getSelectedItem()           ;
			}catch (Exception ex)
			{
				this.lblErreur.setText("<html> Numéro, x et y  <br> doivent être entiers. </html>");
				return;
			}
		}

		if(idVille > 99 || idVille < 0)
		{
			this.lblErreur.setText("<html> Numéro compris entre  <br> 0 et 99. </html>");
			return;
		}

		if(x < 0 || y < 0)
		{
			this.lblErreur.setText("<html> x et y doivent <br> être > 0. </html>");
			return;
		}


		if (e.getSource() == this.btnAjouterSommet)
		{
			if ((
				 this.txtNumero.getText().isBlank()     ||
				 this.txtX.getText().isBlank()          ||
				 this.txtY.getText().isBlank()         )&&
				 selectedRowIndex == -1

			   )
			{
				this.lblErreur.setText("<html> Tous les champs  <br> ne sont pas complétés. </html>");
			}
			else
			{
				if(ctrl.rechercheSommet((idVille+nomVile)) != null && (ctrl.rechercheSommet(idVille+nomVile).getX() != x || ctrl.rechercheSommet(idVille+nomVile).getY() != y))
				{
					this.lblErreur.setText("<html> Un sommet avec ce  <br> nom existe déjà. </html>");
					return;
				}

				this.lblErreur.setText("");

				int rndm = (int)(Math.random()*(this.ctrl.getLstMateriaux().size()));

				Materiaux tmpMat = new Materiaux(this.ctrl.getLstMateriaux().remove(rndm));

				this.ctrl.ajouterOuSupprimerSommet(idVille, nomVile,x,y,tmpMat,false);

				//Raffraichir le tableau
				this.removeAll();
				this.ajouterTabSommet();
				this.panelInput();
				this.revalidate();

				System.out.println("sommet ajouté ou suppresion du sommet effectué ");

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[]{ txtNumero.getText(),lstCouleur.getSelectedItem(), txtX.getText(), txtY.getText()});

				this.ctrl.MajFrameModification();
				
			}
		}
	}
}