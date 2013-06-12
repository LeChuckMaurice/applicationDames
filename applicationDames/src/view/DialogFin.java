package view;

import javax.swing.*;
import java.awt.*;

public class DialogFin extends JDialog{

private JPanel pPrincipal;
private JLabel lText;
private JPanel pButton;
private JButton bMenu;

public DialogFin(boolean victoire){
	super();
	creerInterface();
	styleLabel(victoire);
	this.setSize(240,130);
	this.setVisible(true);
	this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
}

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
	bMenu.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
	pButton.add(bMenu);

}

public void styleLabel(boolean victoire){
	if (victoire) {
		lText.setForeground(new Color(170,248,7));
		lText.setText("VICTOIRE");
	}
	else {
		lText.setForeground(new Color(255,23,23));
		lText.setText("DÉFAITE");
	}

}

public static void main(String[] args) {
	DialogFin vict = new DialogFin(true);
	DialogFin def = new DialogFin(false);

}
	

}
