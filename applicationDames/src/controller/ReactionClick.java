package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import datas.*;

import view.Case;


public class ReactionClick implements Globale, MouseListener{
	
	private DamesControl myCtrl;
	
	public ReactionClick(DamesControl theCtrl){
		this.myCtrl=theCtrl;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();

		if (src==(Globale.theView).getExit()) {
			quitterPlateau();
		}
		else if (src==(Globale.theView).getHelp()) {
			lancementAide();
		}
		else if (src==(Globale.theView).getSave()) {
			(Globale.theView).getDialogSave().setVisible(true);
		}
		else if (src==(Globale.theView).getExitAide()) {
			retourPlateau();
		}
		else if ((src.toString()).equals("case")) {
			Case laCase = (Case) src;
			reactionCase(laCase);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	private void retourPlateau(){
		(Globale.theView).creerInterfaceJeu(myCtrl.getThePlat().getTaille());
		myCtrl.attacherReactionsPlateau();

		myCtrl.getThePlat().remplirPlateau();
		myCtrl.updateView();
	}

	private void lancementAide(){
		(Globale.theView).creerInterfaceAide();
		myCtrl.attacherReactionsAide();
	}

	private void quitterPlateau(){
		(Globale.theView).getDialogQuit().setVisible(true);
	
	}

	private void reactionCase(Case theCase){
		Case laCase=theCase;
		Plateau plateau=myCtrl.getThePlat();
		Piece laPiece=plateau.getPiece(laCase.getCoordonnee());

		if (laPiece!=null) {
			if (!(laPiece.isIA())) {
				if ((myCtrl.getPieceSelect())==null) {
					if (laPiece.canMove()) {
						
						myCtrl.setPieceSelect(laPiece);

						if (laPiece.isDame()) {
							laCase.setDameBlancOver();
						}
						else{
							laCase.setPionBlancOver();
						}

						ArrayList<Coordonnee> listCases = laPiece.getDeplacements();

						for (int i=0;i<listCases.size() ;i++ ) {
							Coordonnee coordJouable=listCases.get(i);
							int x = coordJouable.getX();
							int y = coordJouable.getY();
							Case caseJouable=(Globale.theView).getCase(x,y);
							caseJouable.setCaseJouable();
						}

					}
					
				}
			}
			else{
				if ((myCtrl.getPieceSelect())==null) {
					myCtrl.setPieceSelect(laPiece);
				}
			}
		}
		else{
			if (myCtrl.getPieceSelect()!=null) {
				Coordonnee coord=laCase.getCoordonnee();
				int x=coord.getX();
				int y=coord.getY();

				if (((x+y)%2)==1) {
					plateau.movePiece(myCtrl.getPieceSelect(),coord);
					myCtrl.setPieceSelect(null);
					plateau.updateStatus();
					myCtrl.updateView();
				}
			}
		}


	}

}
