package view;

import java.awt.*;
import javax.swing.*;

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
	}

	public void creerInterface(){

		//this.setUndecorated(true);
		
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

	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(200,35));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
	}

	public JButton getSave(){
		return bSave;
	}	

	public JButton getNoSave(){
		return bNoSave;
	}	

	public JButton getAnnuler(){
		return bAnnuler;
	}



}
