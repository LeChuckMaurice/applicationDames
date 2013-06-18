package pda.view.viewDames;

import javax.swing.*;

import java.awt.*;

/**
*Crée la boite de dialogue qui symbolise la fin de la partie, cette classe est une sous classe de JDialog
*Elle ce compose d'un JLabel indiquant la victoire ou la défaite et d'un bouton pour revenir au menu principal
*/
public class DialogFin extends JDialog{

private JPanel pPrincipal;
private JLabel lText;
private JPanel pButton;
private JButton bMenu;

public DialogFin(){
	super();
	creerInterface();
	this.setSize(240,130);
	this.setVisible(false);
	this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
	this.setUndecorated(true);
	init();
}

/**
*Crée le décors de la boite de dialogue
*/
public void creerInterface(){
		
	pPrincipal = new JPanel();
	pPrincipal.setLayout(new GridLayout(2,1));
	pPrincipal.setBackground(Color.darkGray);
	this.add(pPrincipal);

	lText = new JLabel();
	lText.setFont(new Font(Font.DIALOG, Font.BOLD, 28) );
	lText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	pPrincipal.add(lText);

	pButton = new JPanel();
	pButton.setLayout(new FlowLayout(1));
	pButton.setOpaque(false);
	pPrincipal.add(pButton);

	bMenu = new JButton("Menu Principal");
	bMenu.setPreferredSize( new Dimension(160,35));
	bMenu.setBackground(Color.black);
	bMenu.setForeground(Color.white);
	bMenu.setBorderPainted(false);
	bMenu.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
	pButton.add(bMenu);

}

/**
*Definit le style du JLabel selon la victoire/défaite
*@param victoire true si le joueur gagne, false si c'est l'IA
*/
public void styleLabel(boolean victoire){
	if (victoire) {
		lText.setForeground(new Color(170,248,7));
		lText.setText("VICTOIRE");
	}
	else {
		lText.setForeground(new Color(255,23,23));
		lText.setText("DEFAITE");
	}

}


/**
*Renvoit le bouton servant au retour menu
*/
public JButton getMenu(){
	return bMenu;
}

/**
*Initialise l'emplacement de la boite au centre de la fenetre appelante
*/
public void init () {
   	JFrame parent = (JFrame) this.getOwner();
    Dimension d = parent.getSize () ;
    Point p = parent.getLocation () ;
    setLocation (p.x+(d.width-getSize().width)/2, p.y+(d.height-getSize().height)/2);
}
	


}
