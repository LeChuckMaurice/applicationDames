package view;

import javax.swing.*;
import java.awt.*;

public class DialogSaveCharge extends JDialog{

	private JPanel pPrincipal;
	private JLabel lText;
	private JPanel pButton;
	private JPanel pAnnuler;
	private JButton bAnnuler;
	private JPanel pConfirmer;
	private JButton bConfirmer;
	
	public DialogSaveCharge(String text){
		super ();
		creerInterface(text);

		this.setSize(240,130);
		this.setVisible(true);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
	}

	public void creerInterface(String text){
		
		pPrincipal = new JPanel();
		pPrincipal.setLayout(new GridLayout(2,1));
		pPrincipal.setBackground(Color.darkGray);
		this.add(pPrincipal);

		lText = new JLabel(text);
		lText.setForeground(Color.white);
		lText.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		lText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pPrincipal.add(lText);

		pButton = new JPanel();
		pButton.setLayout(new GridLayout(1,2));
		pButton.setOpaque(false);
		pPrincipal.add(pButton);


		pAnnuler = new JPanel();
		pAnnuler.setOpaque(false);
		pButton.add(pAnnuler);

		bAnnuler = new JButton("Annuler");
		setStyleButton(bAnnuler);
		pAnnuler.add(bAnnuler);

		pConfirmer = new JPanel();
		pConfirmer.setOpaque(false);
		pButton.add(pConfirmer);

		bConfirmer = new JButton("Confirmer");
		setStyleButton(bConfirmer);
		pConfirmer.add(bConfirmer);
	}

	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(100,35));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
	}

	public static void main(String[] args) {
		DialogSaveCharge dialog = new DialogSaveCharge("Sauvegarder la partie? ");
		
		DialogSaveCharge dialog2 = new DialogSaveCharge("Charger la partie? ");
	}

}
