package datas;

import java.io.Serializable;
import java.util.ArrayList;
 
public class IA implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plateau plateau;

	public IA(Plateau plateauIA){
		this.plateau = plateauIA;
	}

	public Coup getAction(){
		

		ArrayList<Coup> allCoups = this.getAllCoups();
		Coup coupTmp = null;
		
		Coup bestCoup = allCoups.get(0);

		for(int i=0; i<allCoups.size(); i++){
			coupTmp = allCoups.get(i);

			if(coupTmp.getNbPoints()>bestCoup.getNbPoints()){
				bestCoup = coupTmp;
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

		return allCoups;

	}

}
