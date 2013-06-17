package pda.control.controlDames;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import pda.datas.datasDames.*;
import pda.view.viewDames.Case;


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
		(Globale.theView).creerInterfaceJeu((Globale.thePart).getPlateau().getTaille());
		myCtrl.attacherReactionsPlateau();

		(Globale.thePart).getPlateau().remplirPlateau();
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
		
		if (!(Globale.thePart).getTourIA()) {
			
		
			
			
			Case laCase=theCase;
			Plateau plateau=(Globale.thePart).getPlateau();
			Piece laPiece=plateau.getPiece(laCase.getCoordonnee());

			if (laPiece!=null) {
				if (!myCtrl.getInDoubleCoup()) {
				myCtrl.updateView();
					if (!(laPiece.isIA())) {
						if (laPiece.canMove()) {
						
							this.selectionPiece(laPiece,laCase);
						
						}
					}
				}
			}
			else{
				if (myCtrl.getPieceSelect()!=null) {
					jouerCoup(laPiece,laCase,plateau);
				}
			}
		}
	}

	private void selectionPiece(Piece laPiece,Case laCase){

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

	public void jouerCoup(Piece laPiece,Case laCase,Plateau plateau){
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
					myCtrl.updateView();
					Coordonnee coordArrive = theCoup.getArrivee();
					Piece laPieceFin = plateau.getPiece(coordArrive);

					if (theCoup.getPiecePrise()!=null && laPieceFin.canTake()) {
						int newX = coordArrive.getX();
						int newY = coordArrive.getY();
						Case newCase = (Globale.theView).getCase(newX,newY);
						myCtrl.setDoubleCoup(true);
						this.selectionPiece(laPieceFin,newCase);
						this.jouerCoup(laPieceFin,newCase,plateau);
					}
					else {
						myCtrl.setPieceSelect(null);
						(Globale.thePart).setTourIA(true);
						myCtrl.setDoubleCoup(false);
					}

				}
				plateau.updateStatus();
				myCtrl.isFin();

				if ((Globale.thePart).getTourIA()==true) {
					myCtrl.coupIA();
				}
			}
		}
	}
}




