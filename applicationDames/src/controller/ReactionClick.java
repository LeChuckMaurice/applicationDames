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
		
		myCtrl.updateView();
		
		Case laCase=theCase;
		Plateau plateau=myCtrl.getThePlat();
		Piece laPiece=plateau.getPiece(laCase.getCoordonnee());

		if (laPiece!=null) {
			//normalement !(laPiece.isIA()), true pour permettre le deplacement manuel des pions noir
			if (true) {
				if (laPiece.canMove()) {

					//Si on a aucune piece selectionne et que on clic sur une piece joueur pouvant bouger on selectionne cette dernière
					
					myCtrl.setPieceSelect(laPiece);

					ArrayList<Coordonnee> listCases = laPiece.getDeplacements();
					
					if (laPiece.isDame()) {
						laCase.setDameBlancOver();
					}
					else{
						laCase.setPionBlancOver();
					}

					//puis on applique le surlignage rouge a toute les cases sur lesquelles la piece peut ce deplacer
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
			if (myCtrl.getPieceSelect()!=null) {

				Coordonnee coord=laCase.getCoordonnee();
				int x=coord.getX();
				int y=coord.getY();

				//Si on a une piece de selectionne et que on clic sur une case jouable (noire)
				if (((x+y)%2)==1) {
					boolean caseValide = false;
					ArrayList<Coordonnee> listCases = myCtrl.getPieceSelect().getDeplacements();
					int i=0;

					//si la case appartient aux case sur lesquels le pions selectionne peux jouer on passe le booleen a vrai 

					while(!(caseValide) && i<listCases.size()) {
						Coordonnee coordJouable=listCases.get(i);

						if (coordJouable.equals(coord)) {
								caseValide=true;	
						}
						
						i++;
					}
					
					if (caseValide) {
						boolean caseCoup=false;

						ArrayList<Coup> listeCoups = myCtrl.getPieceSelect().generateCoups();

						int j=0;
						Coup theCoup=null;

						


						while (!(caseCoup) && j<listeCoups.size()) {
							Coup coupActuel = listeCoups.get(j);
							Coordonnee coordArrive = coupActuel.getArrivee();
							
							if (coordArrive.equals(coord)) {
								caseCoup=true;
								theCoup = coupActuel;
							}
							
							j++;
						}

						if (caseCoup) {
							plateau.playAction(theCoup);
							myCtrl.setPieceSelect(null);
							plateau.updateStatus();
							myCtrl.updateView();
						}
								
					}
				}

			}
		}
	}

}


