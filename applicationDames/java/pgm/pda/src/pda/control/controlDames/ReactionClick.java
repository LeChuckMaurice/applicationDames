package pda.control.controlDames;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import pda.datas.datasDames.*;
import pda.view.viewDames.Case;

/**
*Cette methode définie les réaction sur les JLabel de la partie et sur les cases du plateau
*/
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

	/**
	*Cette methode permet le retour à l'interface de jeu depuis l'aide, en restaurant l'emplacement des pions
	*/
	private void retourPlateau(){
		(Globale.theView).creerInterfaceJeu((Globale.thePart).getPlateau().getTaille());
		myCtrl.attacherReactionsPlateau();

		(Globale.thePart).getPlateau().remplirPlateau();
		myCtrl.updateView();
	}

	/**
	*Permet de lancer l'aide
	*/
	private void lancementAide(){
		(Globale.theView).creerInterfaceAide();
		myCtrl.attacherReactionsAide();
	}

	/**
	*Permet de lancer la boite de dialog dialogQuit pour quitter la partie
	*/
	private void quitterPlateau(){
		(Globale.theView).getDialogQuit().setVisible(true);
	
	}


	/**
	*Effection les actions sur le plateau en fonctions des case cliquée
	*@param theCase La case sur laquelle le joueur à cliqué
	*/
	private void reactionCase(Case theCase){
		
		// Si c'est le tour du joueur
		if (!(Globale.thePart).getTourIA()) {

			// On récupère la case cliqué et la piece du tableau correspondante
			Case laCase=theCase;
			Plateau plateau=(Globale.thePart).getPlateau();
			Piece laPiece=plateau.getPiece(laCase.getCoordonnee());

			//Si la case ne contient pas de piece
			if (laPiece!=null) {
				// Si on est pas au milieu d'un double coup
				if (!myCtrl.getInDoubleCoup()) {
				myCtrl.updateView();
					// Si c'est une piece du joueur
					if (!(laPiece.isIA())) {
						//Et qu'elle peux bouger
						if (laPiece.canMove()) {
							this.selectionPiece(laPiece,laCase);
						
						}
					}
				}
			}
			else{
				//Si on à aucune piece selectionnée
				if (myCtrl.getPieceSelect()!=null) {
					jouerCoup(laPiece,laCase,plateau);
				}
			}
		}
	}

	private void selectionPiece(Piece laPiece,Case laCase){

		//On selectionne la piece
		myCtrl.setPieceSelect(laPiece);

		ArrayList<Coordonnee> listCases = laPiece.getDeplacements();
		
		//La case change d'aspect pour celui du pion ou de la dame entourée d'un halo rouge
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
		//On obtient les coordonne de la case cliqué
		Coordonnee coord=laCase.getCoordonnee();
		int x=coord.getX();
		int y=coord.getY();

		//Si on a une piece de selectionne et que on clic sur une case jouable (noire)
		if (((x+y)%2)==1) {
			boolean caseValide = false;
			//On obtient le tableau des cases sur lesquels la piece peut ce deplacer
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
			
			//Si la case sur laquelle on a cliqué est une de celle sur lesquelles la pièce peux ce deplacer6
			if (caseValide) {
				boolean caseCoup=false;

				ArrayList<Coup> listeCoups = myCtrl.getPieceSelect().generateCoups();

				int j=0;
				Coup theCoup=null;

				//On récupère le coup correspondant au deplacement choisit
				while (!(caseCoup) && j<listeCoups.size()) {
					Coup coupActuel = listeCoups.get(j);
					Coordonnee coordArrive = coupActuel.getArrivee();
								
					if (coordArrive.equals(coord)) {
						caseCoup=true;
						theCoup = coupActuel;
					}
								
					j++;
				}
				//Une fois choisit on joue le coup
				if (caseCoup) {
					plateau.playAction(theCoup);
					myCtrl.updateView();
					Coordonnee coordArrive = theCoup.getArrivee();
					Piece laPieceFin = plateau.getPiece(coordArrive);

					//Si on n'a pris une piece et que on peux reprendre une pièce on lance le cas de double coups
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

				//Si c'est au tour de l'IA on lance cette dernière
				if ((Globale.thePart).getTourIA()==true) {
					myCtrl.coupIA();
				}
			}
		}
	}
}




