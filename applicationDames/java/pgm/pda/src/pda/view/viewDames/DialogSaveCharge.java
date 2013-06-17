package pda.view.viewDames;

import javax.swing.*;
import java.awt.*;

public class DialogSaveCharge extends JDialog{

	private JPanel pPrincipal;
	private JLabel lText;
	private JPanel pButton;
	private JPanel pAnnuler;
	private JButton bAnnuler;
	private JPanel pConfirmer;
	private JButton bSave;
	private JButton bCharge;

	//
	public DialogSaveCharge(boolean isCharge){
		super ();
		creerInterface(isCharge);

		this.setSize(240,130);
		this.setVisible(false);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
		this.setUndecorated(true);
	}

	public void creerInterface(boolean isCharge){
		
		pPrincipal = new JPanel();
		pPrincipal.setLayout(new GridLayout(2,1));
		pPrincipal.setBackground(Color.darkGray);
		this.add(pPrincipal);

		lText = new JLabel();
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

		if (isCharge) {
			lText.setText("Charger la partie");
			bCharge = new JButton("Charger");
			setStyleButton(bCharge);
			pConfirmer.add(bCharge);
		}
		else {
			lText.setText("Sauvegarder la partie");
			bSave = new JButton("Sauvegarder");
			setStyleButton(bSave);
			pConfirmer.add(bSave);
		}
	}

	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(105,35));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		button.setBorderPainted(false);
	}

	public JButton getCharge(){
		return bCharge;
	}
	
	public JButton getSave(){
		return bSave;
	}

	public JButton getAnnuler(){
		return bAnnuler;
	}

	public static void main(String[] args) {
		DialogSaveCharge charg = new DialogSaveCharge(true);
		DialogSaveCharge save = new DialogSaveCharge(false);
	}

}
