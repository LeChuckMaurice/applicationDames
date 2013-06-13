package datas;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class Piece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// PieceIA : true=IA / false=joueur
	protected boolean pieceIA;
	// dame : true=Dame / false=Pion
	protected boolean dame;
	protected Coordonnee place;
	protected Plateau plateau;

 
	public Piece(int positionX, int positionY, boolean camp,Plateau thePlateau, boolean isDame){
		this.place = new Coordonnee(positionX,positionY);
		this.pieceIA=camp;
		this.dame=isDame;
		this.plateau=thePlateau;
	}

	// Methodes

	public ArrayList<Coup> generateCoups(){

		ArrayList<Coup> coups = new ArrayList<Coup>();
		ArrayList<Coordonnee> deplacementsPossibles  = this.getDeplacements();



		for(int i=0; i<deplacementsPossibles.size(); i++){

			coups.add(new Coup(this, deplacementsPossibles.get(i)));
		}

		return coups;
	}

	public boolean isVulnerable(){
		return this.isVulnerable(this.place,this.pieceIA);
	}

	public boolean isVulnerable (Coordonnee thePlace, boolean pieceIA){
		boolean vulnerable = false;

		int x = place.getX();  
		int y = place.getY();
		int deplacementX=0; // deplacement en x
		int deplacementY=0; // deplacement en y
		Coordonnee coord = new Coordonnee(x,y);

		Piece piece1=null; 

		// recuperation des 4 diagonales
		ArrayList<Coordonnee> diagonaleHG = this.getDiagonale(-1,-1);
		ArrayList<Coordonnee> diagonaleHD = this.getDiagonale(1, -1);
		ArrayList<Coordonnee> diagonaleBD = this.getDiagonale(1,1);
		ArrayList<Coordonnee> diagonaleBG = this.getDiagonale(-1, 1);
		
		int i=0;
		do{
			// piece1 = diagonaleHG.get(i);
			i++;
		}
		// jusqu'a ce que la case soit occupée
		while(piece1!=null && i<diagonaleHG.size());

		// si c'est un pion adverse et qu'il est a 1 de distance
		if(piece1!=null && piece1.isIA()){
				//
		}

		return vulnerable;
	}


	public abstract ArrayList<Coordonnee> getDeplacements();
	public abstract ArrayList<Coordonnee> getDeplacements(Coordonnee place);

	public abstract ArrayList<Piece> prisesPossibles();
	public abstract ArrayList<Piece> prisesPossibles(Coordonnee thePlace, boolean pieceIA);

	public abstract boolean canMove();
	public abstract boolean canMove( Coordonnee place, boolean pieceIA );

	public abstract boolean canTake();
	public abstract boolean canTake( Coordonnee place, boolean pieceIA );

	//Accesseurs
	
	/**
	*Renvoie le camp auquel appartient la piece
	*@return pieceIA true pour un piece appartenant à l'ordinateur, false pour une du joueur
	*/
	public boolean isIA(){
		return this.pieceIA;
	}
	
	/**
	*Renvoie un objet de type coordonnee modelisant l'emplacement de la piece
	*@return place La coordonnee de la piece
	*/
	public Coordonnee getCoordonnee(){
		return this.place;
	}

	/**
	*Renvoi un boolean indiquant si la piece est ou non une dame
	*@return dame true si la piece est une dame, false si c'est un pion
	*/
	public boolean isDame(){
		return this.dame;
	}
	

	public Plateau getPlateau(){
		return this.plateau;
	}


	public void setCoordonnee(Coordonnee newPlace){
		this.place = newPlace;
	}


	protected ArrayList<Coordonnee> getDiagonale(int dirX, int dirY){
		return this.getDiagonale(this.place, dirX, dirY);
	}


	protected ArrayList<Coordonnee> getDiagonale(Coordonnee place, int dirX, int dirY){
		ArrayList<Coordonnee> tabCoord = new ArrayList<Coordonnee>();

		int x = place.getX();
		int y = place.getY();

		do{
			x = x + dirX;
			y = y + dirY;
			tabCoord.add(new Coordonnee(x,y));
		}
		// tant qu'on n'est pas sorti du plateau et que la case est libre
		while(this.plateau.isValide(new Coordonnee(x,y)));

		//if(tabCoord.size()>0){
			//tabCoord.remove(tabCoord.size()-1);
		//}
		return tabCoord;
	}
	

}
