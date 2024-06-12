package jeu.ihm;

import jeu.Controleur;

import javax.swing.JFrame;

public class FrameRoute extends JFrame
{
    private PanelRoutes panelDesRoutes;

    public FrameRoute( Controleur ctrl)
	{
		this.setTitle   ( "Frame Routes" );
		this.setSize    ( 800, 200 );
		this.setLocation( 50,50 );   
        
        this.panelDesRoutes = new PanelRoutes( ctrl );
		
        this.add( this.panelDesRoutes );

		this.setVisible(true);

        
	}

    
    
}
