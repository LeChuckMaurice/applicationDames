package controller;

import view.*;
import java.awt.event.*;
import java.io.*;
import datas.Piece;
import datas.Plateau;

public class DamesControl implements Globale{
	
	private Plateau thePlat = null;
	private Piece pieceSelect = null;
	
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

		(Globale.theView).getDialogErreur().getOk().addActionListener(new ReactionAction(this));
		
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

	public void updateView(){
		int taillePlateau=thePlat.getTaille();;

		if (thePlat!=null) {
			for (int y=0;y<taillePlateau;y++) {
				
				for (int x=(y+1)%2; x<taillePlateau; x=x+2){ // x
				
					Piece piece = thePlat.getPiece(x,y);
					Case laCase = theView.getCase(x,y);

					if (piece==null) {
						laCase.setCaseNoire();
					}
					else if (piece.isIA()) {
						
						if (piece.isDame()) {
							laCase.setDameNoir();
						}
						else{
							laCase.setPionNoir();
						}

					}
					else if (!(piece.isIA())) {
						
						if (piece.isDame()) {
							if (piece.canMove()) {
								laCase.setDameBlancSurl();
							}
							else{
								laCase.setDameBlanc();
							}
						}
						else{
							if (piece.canMove()) {
								laCase.setPionBlancSurl();
							}
							else{
								laCase.setPionBlanc();
							}
						}
						
					}
				}

			}
		}


	}

	public void charger() throws Exception{
		this.thePlat = thePart.chargerPlateau();
		
	}
	
	public void sauver(){
		thePart.savePlateau();
	}
	
	public void createThePlat(int taille){
		this.thePlat = new Plateau(taille);
	}

	public void setPieceSelect(Piece laPiece){
		this.pieceSelect=laPiece;
	}

	public Piece getPieceSelect(){
		return this.pieceSelect;
	}

	public Plateau getThePlat(){
		return thePlat;
	}

public static void main(String[] args) {
	DamesControl ctrl = new DamesControl();
}

}
