package pda.view.viewDames;

import java.awt.*;
import javax.swing.*;

/**
*Cete boite de dialogue permet de quitter une partie, elle est sous classe de JDialog
*On propose 3 choix dans cette boite : Quitter et sauvegarder, quitter sans sauvegarder ou annuler
*/
public class DialogQuitter extends JDialog{

	private JPanel pPrincipal;
	private JPanel pSave;
	private JButton bSave;
	private JPanel pNoSave;
	private JButton bNoSave;
	private JPanel pAnnuler;
	private JButton bAnnuler;
	
	public DialogQuitter(){
		super ();
		creerInterface();

		this.setSize(240,180);
		this.setVisible(false);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
		this.setUndecorated(true);
	}

	/**
	*Crée le décors de la boite 
	*/
	public void creerInterface(){
		
		pPrincipal = new JPanel();
		pPrincipal.setLayout(new GridLayout(3,1));
		pPrincipal.setBackground(Color.darkGray);
		this.add(pPrincipal);

		pSave = new JPanel();
		pSave.setOpaque(false);
		pPrincipal.add(pSave);

		bSave = new JButton("Quitter et sauvegarder");
		setStyleButton(bSave);
		pSave.add(bSave);


		pNoSave = new JPanel();
		pNoSave.setOpaque(false);
		pPrincipal.add(pNoSave);

		bNoSave = new JButton("Quitter sans sauvegarder");
		setStyleButton(bNoSave);
		pNoSave.add(bNoSave);


		pAnnuler = new JPanel();
		pAnnuler.setOpaque(false);
		pPrincipal.add(pAnnuler);

		bAnnuler = new JButton("Annuler");
		setStyleButton(bAnnuler);
		pAnnuler.add(bAnnuler);


	}

	/**
	*Definit un style au bouton passé en parametre
	*Utilisé pour unifié le look des différent boutons de la boite
	*/
	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(200,35));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
		button.setBorderPainted(false);
	}

	/**
	*Renvoit le bouton permettant de sauvegarder et quitter
	*/
	public JButton getSave(){
		return bSave;
	}	

	/**
	*Renvoit le bouton permettant de ne pas sauvegarder et de quitter
	*/
	public JButton getNoSave(){
		return bNoSave;
	}	

	/**
	*Renvoit le bouton permettant de ne pas quitter
	*/
	public JButton getAnnuler(){
		return bAnnuler;
	}



}
