package datas;
import datas.*;

public abstract class Piece {

	// PieceIA : true=IA / false=joueur
	protected boolean pieceIA;
	// dame : true=Dame / false=Pion
	protected boolean dame;
	protected Coordonnee place;
	protected Plateau plateau;

 
	public Piece(int positionX, int positionY, boolean camp,boolean isDame){
		place = new Coordonnee(positionX,positionY);
		pieceIA=camp;
		dame=isDame;
	}

	// Methodes

	public boolean isVulnerable(){
		return true;
	}

	public boolean isVulnerable (Coordonnee place){
		return true;
	}

	public abstract boolean isDame();

	public abstract Coup[] generateCoups();

	public abstract Coordonnee[] getDeplacements(Coordonnee place);

	public abstract boolean canMove();

	public abstract boolean canMove( Coordonnee place );

	public abstract boolean canTake();

	public abstract boolean canTake( Coordonnee place );

	//Accesseur
	
	public boolean getCamp(){
		return pieceIA;
	}
	
	public Coordonnee getCoordonnee(){
		return place;
	}
	
	public Plateau getPlateau(){
		return this.plateau;
	}
	

}
