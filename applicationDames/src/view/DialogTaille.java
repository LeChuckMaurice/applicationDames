package view;

import javax.swing.*;
import java.awt.*;

public class DialogTaille extends JDialog{
	
	private JPanel pPrincipal;
	private JLabel lText;
	private JComboBox<Integer> cbTaille;
	private JPanel pButton;
	private JButton bAnnuler;
	private JButton bConfirmer;


	public DialogTaille(){
		super ();
		creerInterface();

		this.setSize(240,160);
		this.setVisible(true);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
	}

	public void creerInterface(){

		pPrincipal= new JPanel();
		pPrincipal.setBackground(Color.black);
		this.add(pPrincipal);

		pPrincipal.setLayout(new GridLayout(3,1));

		lText = new JLabel("Taille du plateau");
		lText.setForeground(Color.white);
		lText.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
		lText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pPrincipal.add(lText);

		cbTaille = new JComboBox<Integer>();
		cbTaille.addItem(8);
		cbTaille.addItem(10);
		cbTaille.addItem(12);

		pPrincipal.add(cbTaille);

		pButton= new JPanel();
		pButton.setOpaque(false);
		pPrincipal.add(pButton);

		pButton.setLayout(new GridLayout(1,2));

		bAnnuler= new JButton("Annuler");
		bAnnuler.setBackground(Color.black);
		bAnnuler.setForeground(Color.white);
		bAnnuler.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		pButton.add(bAnnuler);
		
		bConfirmer=new JButton("Confirmer");
		pButton.add(bConfirmer);
		bConfirmer.setBackground(Color.black);
		bConfirmer.setForeground(Color.white);
		bConfirmer.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));


	}

	public static void main(String[] args) {
		DialogTaille dialog = new DialogTaille();
	}

}
