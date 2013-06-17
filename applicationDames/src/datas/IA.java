package datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
 
public class IA implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plateau plateau;

	public IA(Plateau plateauIA){
		this.plateau = plateauIA;
	}

	public Coup getActionPiece(Piece piece){
		

		ArrayList<Coup> allCoups = this.getAllCoupsPiece(piece);
		Coup coupTmp = null;
		Coup bestCoup;

		if(allCoups.size()==0){
			bestCoup=null;
		}
		else{
			bestCoup = allCoups.get(0);

			for(int i=0; i<allCoups.size(); i++){
				coupTmp = allCoups.get(i);

				if(coupTmp.getNbPoints()>bestCoup.getNbPoints()){
					bestCoup = coupTmp;
				}
			}
		}
		return bestCoup;
	}

	private ArrayList<Coup> getAllCoupsPiece(Piece piece){
		ArrayList<Coup> allCoups = new ArrayList<Coup>();
		// S'il y a une piece dans la case et que c'est une piece de l'ordinateur
		if(piece!=null && piece.isIA()==true){
			// Si cette piece peut bouger
			if(piece.canMove()){
				// Recuperation des coups possible par cette piece
				allCoups = piece.generateCoups();
			}
		}
		Collections.shuffle(allCoups);
		return allCoups;
	}

	public Coup getAction(){
		ArrayList<Coup> allCoups = this.getAllCoups();
		Coup coupTmp = null;
		Coup bestCoup;

		if(allCoups.size()==0){
			bestCoup=null;
		}
		else{
			bestCoup = allCoups.get(0);

			for(int i=0; i<allCoups.size(); i++){
				coupTmp = allCoups.get(i);

				if(coupTmp.getNbPoints()>bestCoup.getNbPoints()){
					bestCoup = coupTmp;
				}
			}
		}
		return bestCoup;	
	}


	private ArrayList<Coup> getAllCoups(){
		
		ArrayList<Coup> coupsTmpPiece = new ArrayList<Coup>();
		ArrayList<Coup> allCoups = new ArrayList<Coup>();
		Piece pieceTmp = null;

		for(int i=0; i<this.plateau.getTaille(); i++){ // boucle de colonne
			for(int j=0; j<this.plateau.getTaille(); j++){ // boucle de ligne
				pieceTmp = this.plateau.getPiece(i,j);
				// S'il y a une piece dans la case et que c'est une piece de l'ordinateur
				if(pieceTmp!=null && pieceTmp.isIA()==true){

					// Si cette piece peut bouger
					if(pieceTmp.canMove()){

						// Recuperation des coups possible par cette piece
						coupsTmpPiece = pieceTmp.generateCoups();

						// Ajout des coups de cette piece a la liste contenant tous les coups possibles
						for(int k=0; k<coupsTmpPiece.size(); k++){
							allCoups.add(coupsTmpPiece.get(k));
						}

					}

				}
			}
		}

		Collections.shuffle(allCoups);
		return allCoups;

	}




	public void play(){
		boolean canContinue = true;
		int nbDeplacements = 0;
		int nbPrises = 0;
		Coup coup = null;
		Piece piece = null;

		while(canContinue){

			if(nbDeplacements==0){
				coup = getAction();
			}
			else{
				coup= getActionPiece(piece);
			}		

			// Si aucun coup n'est possible
			if(coup==null){
				canContinue=false;
			}
			else {
				piece = coup.getPiece();
				// Si le deplacement implique une prise
				if(coup.getPiecePrise()!=null){
					nbPrises++;
				}
				plateau.playAction(coup);
				nbDeplacements++;


				// Si le pion peut rejouer pour faire une prise
				if(nbDeplacements==nbPrises && piece.canTake()){
					canContinue=true;
				}
				else {
					canContinue = false;
				}
			}

		}

	}

}
