package pda.control.controlDames;

import java.awt.*;
import java.awt.event.*;

import pda.datas.datasDames.Plateau;
import pda.view.viewDames.*;

/**
*Cette methode definit les réactions au bouton du menu principal et des boites de dialogues
*Elle implémente Globale pour les références et ActionListener pour les réactions
*/
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
		else if (src==(Globale.theView).getDialogTaille().getAnnuler()) {
			(Globale.theView).getDialogTaille().setVisible(false);
		}
		else if (src==(Globale.theView).getDialogFin().getMenu()) {
			(Globale.theView).getDialogFin().setVisible(false);
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
		else if (src==(Globale.theView).getDialogErreur().getOk()) {
			(Globale.theView).getDialogErreur().setVisible(false);
		}

	
		
	}

	/**
	*Affiche la fentre de selection de taille du plateau
	*/
	private void reactionNewGame(){
		(Globale.theView).getDialogTaille().setVisible(true);
	}

	/**
	*Crée une nouvelle partie en récupérant la taille choisie dans dialogTaille
	*/
	private void creationNewGame(){
		DialogTaille dialogTaille=(Globale.theView).getDialogTaille();
		int taillePlat=dialogTaille.getContenu();
		dialogTaille.setVisible(false);

		(Globale.theView).creerInterfaceJeu(taillePlat);
		myCtrl.attacherReactionsPlateau();

		myCtrl.createThePlat(taillePlat);
		(Globale.thePart).getPlateau().remplirPlateau();
		myCtrl.updateView();
		
	}

	/**
	*Retour au menu principal
	*/
	private void retourMenu(){
		(Globale.theView).creerInterfaceMenu();
		myCtrl.attacherReactionsMenu();
	}
	
	/**
	*Lance la methode de sauvegarde de DameControl
	*/
	private void save(){
		myCtrl.sauver();

	}

	/**
	*Lance la methode de chargement de DameControl
	*Affiche un DialoErreur si aucune partie n'est sauvegardée
	*/
	private void charger(){
			try{
			myCtrl.charger();
			(Globale.theView).creerInterfaceJeu((Globale.thePart).getPlateau().getTaille());
			myCtrl.attacherReactionsPlateau();
			myCtrl.updateView();
			}
			catch(Exception e){
				(Globale.theView).getDialogErreur().changeText("Aucun plateau n'est sauvegarder");
				(Globale.theView).getDialogErreur().setVisible(true);
			}
			myCtrl.isFin();		
	}
	
}
		
	
