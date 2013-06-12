package controller;

import view.*;
import java.awt.event.*;

public class DamesControl implements Globale{
	
	public DamesControl(){

	attacherReactions();

	}

	public void attacherReactions(){

		(Globale.theView).getNew().addActionListener(new ReactionAction());
		(Globale.theView).getCharger().addActionListener(new ReactionAction());
		(Globale.theView).getQuitter().addActionListener(new ReactionAction());

		/*
		(Globale.theView).getExit().addMouseListener(new ReactionAction());
		
		(Globale.theView).getHelp().addMouseListener(new ReactionAction());
		(Globale.theView).getSave().addMouseListener(new ReactionAction());

		(Globale.theView).getExitAide().addMouseListener(new ReactionAction());

		(Globale.theView).getDialogFin().getMenu().addActionListener(new ReactionAction());

		(Globale.theView).getDialogCharge().getCharge().addActionListener(new ReactionAction());
		(Globale.theView).getDialogCharge().getAnnuler().addActionListener(new ReactionAction());
		(Globale.theView).getDialogSave().getSave().addActionListener(new ReactionAction());
		(Globale.theView).getDialogSave().getAnnuler().addActionListener(new ReactionAction());

		(Globale.theView).getDialogQuit().getSave().addActionListener(new ReactionAction());
		(Globale.theView).getDialogQuit().getNoSave().addActionListener(new ReactionAction());
		(Globale.theView).getDialogQuit().getAnnuler().addActionListener(new ReactionAction());
		 */
		(Globale.theView).getDialogTaille().getAnnuler().addActionListener(new ReactionAction());
		(Globale.theView).getDialogTaille().getConfirmer().addActionListener(new ReactionAction());
		
	}

	public void attacherReactionsPlateau(){
		int taillePlateau=theView.getTaillePlateau();

		for (int y=0;y<taillePlateau ;y++ ) {
			
			for (int x=0;x<taillePlateau ;x++ ) {
				theView.getCase(x,y).addMouseListener(new ReactionAction());
			}

		}
	}	

public static void main(String[] args) {
	DamesControl ctrl = new DamesControl();
}

}
