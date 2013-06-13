package controller;

import java.awt.*;
import java.awt.event.*;

import datas.Plateau;
import view.*;

public class ReactionAction implements Globale, ActionListener{
	
	private DamesControl myCtrl;
	
	public ReactionAction(DamesControl theCtrl){
		this.myCtrl=theCtrl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src==(Globale.theView).getNew()) {
			reactionNewGame();
		}
		else if (src==(Globale.theView).getCharger()) {
			(Globale.theView).getDialogCharge().setVisible(true);
		}
		else if (src==(Globale.theView).getQuitter()) {
			System.exit(0);
		}
		else if (src==(Globale.theView).getDialogTaille().getConfirmer()) {
			creationNewGame();
		}
		else if (src==(Globale.theView).getDialogWin().getMenu() || src==(Globale.theView).getDialogWin().getMenu()) {
			(Globale.theView).getDialogWin().setVisible(false);
			retourMenu();
		}
		else if (src==(Globale.theView).getDialogQuit().getNoSave()) {
			(Globale.theView).getDialogQuit().setVisible(false);
			retourMenu();
		}
		else if (src==(Globale.theView).getDialogQuit().getSave()) {
			save();
			(Globale.theView).getDialogQuit().setVisible(false);
			retourMenu();
		}
		else if (src==(Globale.theView).getDialogQuit().getAnnuler()) {
			(Globale.theView).getDialogQuit().setVisible(false);
		}
		else if (src==(Globale.theView).getDialogSave().getSave()) {
			save();
			(Globale.theView).getDialogSave().setVisible(false);			
		}
		else if (src==(Globale.theView).getDialogSave().getAnnuler()) {
			(Globale.theView).getDialogSave().setVisible(false);
		}
		else if (src==(Globale.theView).getDialogCharge().getCharge()) {
			charger();
			(Globale.theView).getDialogCharge().setVisible(false);
		}
		else if (src==(Globale.theView).getDialogCharge().getAnnuler()) {
			(Globale.theView).getDialogCharge().setVisible(false);
		}

	
		
	}
	private void reactionNewGame(){
		//(Globale.theView).getDialogTaille().init();
		(Globale.theView).getDialogTaille().setVisible(true);
	}

	private void creationNewGame(){
		DialogTaille dialogTaille=(Globale.theView).getDialogTaille();
		int taillePlat=dialogTaille.getContenu();
		dialogTaille.setVisible(false);

		(Globale.theView).creerInterfaceJeu(taillePlat);
		myCtrl.attacherReactionsPlateau();

		myCtrl.createThePlat(taillePlat);
		myCtrl.getThePlat().remplirPlateau();
		//myCtrl.updateView();
		
	}


	private void retourMenu(){
		(Globale.theView).creerInterfaceMenu();
		myCtrl.attacherReactionsMenu();
	}
	
	private void save(){
	

	}


	private void charger(){

	}
	
}
		
	