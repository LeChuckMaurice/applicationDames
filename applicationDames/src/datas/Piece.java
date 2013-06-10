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
		
		vulnerable=false;
		int x=coord.getX();
		int y=coor.getY();

		if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-1,y-1);
			Coordonnee drte= new Coordonnee(x+1,y-1);
			Piece pieceGche=plateau.getPiece(gche);
			Piece pieceDrte=plateau.getPiece(drte);
			if (pieceGche==null || pieceDrte==null) {
				vulnerable=true;
			}
			
		}
		else{
			Coordonnee gche= new Coordonnee(x-1,y+1);
			Coordonnee drte= new Coordonnee(x+1,y+1);
			Piece pieceGche=plateau.getPiece(gche);
			Piece pieceDrte=plateau.getPiece(drte);
			if (pieceGche==null || pieceDrte==null) {
				vulnerable=true;
			}
		}

		return vulnerable;
	}

	public boolean isVulnerable (Coordonnee place){
		return false;
	}

	public abstract Coup[] generateCoups();

	public abstract Coordonnee[] getDeplacements(Coordonnee place);

	public abstract boolean canMove();

	public abstract boolean canMove( Coordonnee place );

	public abstract boolean canTake();

	public abstract boolean canTake( Coordonnee place );

	//Accesseur
	
	public boolean getCamp(){
		return this.pieceIA;
	}
	
	public Coordonnee getCoordonnee(){
		return this.place;
	}

	public boolean isDame(){
		return this.dame;
	}
	
	public Plateau getPlateau(){
		return this.plateau;
	}

	//Modificateur

	/**
	*Change un pion en dame
	*/
	public void setDame(){
		if (dame=false) {
			dame=true;
		}
	}
	

}
