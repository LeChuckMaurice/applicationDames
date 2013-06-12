package controller;

import view.*;
import java.awt.event.*;

public class DamesControl implements Globale{
	
	public DamesControl(){

	attacherReactionsMenu();

	}

	public void attacherReactionsMenu(){

		(Globale.theView).getNew().addActionListener(new ReactionAction(this));
		(Globale.theView).getCharger().addActionListener(new ReactionAction(this));
		(Globale.theView).getQuitter().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogWin().getMenu().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogLose().getMenu().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogCharge().getCharge().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogCharge().getAnnuler().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogSave().getSave().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogSave().getAnnuler().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogQuit().getSave().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogQuit().getNoSave().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogQuit().getAnnuler().addActionListener(new ReactionAction(this));
		 
		(Globale.theView).getDialogTaille().getAnnuler().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogTaille().getConfirmer().addActionListener(new ReactionAction(this));
		
	}

	public void attacherReactionsPlateau(){

		(Globale.theView).getExit().addMouseListener(new ReactionClick(this));
		(Globale.theView).getHelp().addMouseListener(new ReactionClick(this));
		(Globale.theView).getSave().addMouseListener(new ReactionClick(this));
		
		int taillePlateau=theView.getTaillePlateau();

		for (int y=0;y<taillePlateau ;y++ ) {
			
			for (int x=0;x<taillePlateau ;x++ ) {
				theView.getCase(x,y).addMouseListener(new ReactionClick(this));
			}

		}
	}	
	
	public void attacherReactionsAide(){
		(Globale.theView).getExitAide().addMouseListener(new ReactionClick(this));
	}

public static void main(String[] args) {
	DamesControl ctrl = new DamesControl();
}

}
