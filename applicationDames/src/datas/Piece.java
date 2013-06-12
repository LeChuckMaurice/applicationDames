package datas;

import java.util.ArrayList;


public abstract class Piece {

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

	public boolean isVulnerable(){

		boolean vulnerable=false;
		int x=this.place.getX();
		int y=this.place.getY();

		if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-1,y-1);
			Coordonnee drte= new Coordonnee(x+1,y-1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					vulnerable=true;
				}
			}
			
		}
		else{
			Coordonnee gche= new Coordonnee(x-1,y+1);
			Coordonnee drte= new Coordonnee(x+1,y+1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					vulnerable=true;
				}
			}
		}

		return vulnerable;
	}

	public boolean isVulnerable (Coordonnee thePlace){
		
		boolean vulnerable=false;
		int x=thePlace.getX();
		int y=thePlace.getY();

		if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-1,y-1);
			Coordonnee drte= new Coordonnee(x+1,y-1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					vulnerable=true;
				}
			}
			
		}
		else{
			Coordonnee gche= new Coordonnee(x-1,y+1);
			Coordonnee drte= new Coordonnee(x+1,y+1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					vulnerable=true;
				}
			}
		}

		return vulnerable;
	}

	public abstract ArrayList<Coordonnee> generateCoups();
	
	public abstract ArrayList<Coordonnee> getDeplacements(Coordonnee place);

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
	

}
