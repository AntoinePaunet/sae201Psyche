import javax.swing.*;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Nahel KOCHAT,			IUT du Havre
 * @author Louis THOMAZEAU--AGULLO,	IUT du Havre
 * @version 1.0 , 2024-05-23
 */


public class FrameJoueur extends JFrame
{
    private JLayeredPane panel;


    /**
     * Constructeur de la classe interface permettant de générer une IHM de 900px / 535px
     * Contient tout les éléments graphiques
     */
    public FrameJoueur(int j)
    {
		if( j == 2 )
			this.setTitle( "Synicat Astral (joueur 2)" );
		else
			this.setTitle( "Corporation Astral (joueur 1)" );

        this.setSize( 900 , 535 );
        this.panel = new JLayeredPane();


        this.setIconImage(new ImageIcon(getClass().getResource("/images/paprika.png")).getImage());


        //Mise en place du fond d'écran

        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/plateau.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        this.panel.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);



        this.add(this.panel);

        this.setVisible( true );
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Permet d'ajouter des éléments dans l'interface graphique
     * @param x Coordonnée de l'abscisse de l'image à placer
     * @param y Coordonnée de l'ordonnée de l'image à placer
     * @param url lien de l'image dans le dossier image.
     */
    public void ajoutElements(int x, int y, String url)
    {
        ImageIcon image = new ImageIcon(getClass().getResource("/images/" + url));
        JLabel imgLabel = new JLabel(image);
        imgLabel.setBounds(x, y, image.getIconWidth(), image.getIconHeight());
        this.panel.add(imgLabel, JLayeredPane.DRAG_LAYER);
    }

}