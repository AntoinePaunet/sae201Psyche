package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Joueur;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
	private Controleur ctrl      ;

	private JTable     table     ;
	private Object[][] tabDonnees;

	/**
	 * Constructeur du Panel d'édition des routes.
	*/
	public PanelScore(Controleur ctrl) 
	{
		this.ctrl = ctrl;
		this.ctrl.getJoueur1().scoreFin();
		this.ctrl.getJoueur2().scoreFin();
		this.ctrl.getJoueur1().scoreSommet();
		this.ctrl.getJoueur2().scoreSommet();

		int bonusJ1 = this.getBonus(this.ctrl.getJoueur1(), this.ctrl.getJoueur2());
		int bonusJ2 = this.getBonus(this.ctrl.getJoueur2(), this.ctrl.getJoueur1());

		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(10, 10, 10, 10));

		// Title Label
		JLabel titleLabel = new JLabel("Fiche de Score", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(titleLabel, BorderLayout.NORTH);

		// Table Data
		String[] columnNames = { "", this.ctrl.getNomThemeJ1(), this.ctrl.getNomThemeJ2() };

		Object[][] data = {
			{"", "", ""},
			{"Points Route"    , this.ctrl.getJoueur1().getScoreRoute(), this.ctrl.getJoueur2().getScoreRoute()},
			{"", "", ""},
			{"Points des Mines", "", ""},
			{"", this.ctrl.getJoueur1().getScoreJaune (), this.ctrl.getJoueur2().getScoreJaune () }, // point Jaune
			{"", this.ctrl.getJoueur1().getScoreBleu  (), this.ctrl.getJoueur2().getScoreBleu  () }, // point Bleu
			{"", this.ctrl.getJoueur1().getScoreGris  (), this.ctrl.getJoueur2().getScoreGris  () }, // point Gris
			{"", this.ctrl.getJoueur1().getScoreVert  (), this.ctrl.getJoueur2().getScoreVert  () }, // point Vert
			{"", this.ctrl.getJoueur1().getScoreRouge (), this.ctrl.getJoueur2().getScoreRouge ()}, // point Rouge
			{"", this.ctrl.getJoueur1().getScoreMarron(), this.ctrl.getJoueur2().getScoreMarron()}, // point Marron
			{"S/Total", this.ctrl.getJoueur1().getSommeScoreSommet(), this.ctrl.getJoueur2().getSommeScoreSommet()}, // Total du dessus
			{"", "", ""},
			{"Plateau Individuel" , "", ""},
			{"Score Pièces"       , this.ctrl.getJoueur1().getScorePiece  (), this.ctrl.getJoueur2().getScorePiece  () }, //scorePieceJ1
			{"Scores des Colonnes", this.ctrl.getJoueur1().getScoreColonne()-1, this.ctrl.getJoueur2().getScoreColonne() -1}, //scoreColJ1
			{"Scores des Lignes"  , this.ctrl.getJoueur1().getScoreLigne  (), this.ctrl.getJoueur2().getScoreLigne  () }, //scoreLigJ1
			{"S/Total"            , this.ctrl.getJoueur1().getSommeScorePlateau(), this.ctrl.getJoueur2().getSommeScorePlateau()},
			{"", "", ""},
			{"Jetons Possession restants", 25 - this.ctrl.getJoueur1().getJetons(), 25 - this.ctrl.getJoueur2().getJetons()}, //jetonPossessJ1
			{"Bonus (10)", bonusJ1 , bonusJ2 },
			{"Total"     , this.ctrl.getJoueur1().getSommeScore() + bonusJ1, this.ctrl.getJoueur2().getSommeScore() + bonusJ2} //Total
		};

		// Creating the table
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		table = new JTable(model);
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);

		// Centering text and icons in the table cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// Adding the table to a scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	public int getBonus(Joueur j1, Joueur j2)
	{
		if (j1.getJetons()<j2.getJetons())
			return 10;
		else
			return 0;
	}
}