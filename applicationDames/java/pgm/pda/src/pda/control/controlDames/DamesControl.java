package pda.control.controlDames;

import pda.view.viewDames.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JPanel;
import pda.control.PdaCtrl;
import pda.datas.datasDames.Piece;
import pda.datas.datasDames.Plateau;

public class DamesControl implements Globale, pda.control.IApplication{
	
	private Piece pieceSelect = null;
	private boolean inDoubleCoup=false;
	private String name;
	
	public DamesControl(){

	attacherReactionsMenu();
	setAppliName("Dames");

	}

	public void attacherReactionsMenu(){

		(Globale.theView).getNew().addActionListener(new ReactionAction(this));
		(Globale.theView).getCharger().addActionListener(new ReactionAction(this));
		(Globale.theView).getQuitter().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogFin().getMenu().addActionListener(new ReactionAction(this));

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
		Plateau thePlat=(Globale.thePart).getPlateau();
		int taillePlateau=thePlat.getTaille();

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

	public void coupIA(){
		
		
		(Globale.thePart).getPlateau().getOrdinateur().play();
    	
    	
		(Globale.thePart).getPlateau().updateStatus();
		this.updateView();
		(Globale.thePart).setTourIA(false);
		isFin();
	}

	public void isFin(){
		int isGagnant = (Globale.thePart).getGagnant();

		if (isGagnant==1) {
			(Globale.theView).getDialogFin().styleLabel(false);
			(Globale.theView).getDialogFin().setVisible(true);
		}
		if (isGagnant==(-1)) {
			(Globale.theView).getDialogFin().styleLabel(true);
			(Globale.theView).getDialogFin().setVisible(true);
		}
	}

	public void charger() throws Exception{
		(Globale.thePart).setPlateau(thePart.chargerPlateau());
		
	}
	
	public void sauver(){
		thePart.savePlateau();
	}
	
	public void createThePlat(int taille){
		(Globale.thePart).setPlateau(new Plateau(taille));
	}

	public void setPieceSelect(Piece laPiece){
		this.pieceSelect=laPiece;
	}

	public Piece getPieceSelect(){
		return this.pieceSelect;
	}

	public void setDoubleCoup(boolean status){
		this.inDoubleCoup=status;
	}

	public boolean getInDoubleCoup(){
		return this.inDoubleCoup;
	}


	//Methodes de l'interface IApplication

	public void start(PdaCtrl pda){
		System.out.println("Lancement du jeu de Dames");
	}

	public String getAppliName(){
		return name;

	}

	public JPanel getAppliPanel(){
		JPanel panel=(Globale.theView).getPanel();

		return panel;
	}

	public boolean close(){
		return true;
	}

	public void setAppliName ( String theName ){
		this.name=theName;
	}




}