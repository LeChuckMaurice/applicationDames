package datas;

public class Pion extends Piece {

	public Pion(int positionX, int positionY, Plateau thePlateau, boolean pieceIA) {
		super(positionX,positionY,pieceIA,thePlateau,false);
	}

	public Coup[] generateCoups() {
		return null;
	}

	public Coordonnee[] getDeplacements(Coordonnee place) {
		return null;
	}

	public boolean canMove() {

		boolean move=false;
		int x=this.place.getX();
		int y=this.place.getY();

		if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-1,y+1);
			Coordonnee drte= new Coordonnee(x+1,y+1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					move=true;
				}
			}
			
		}
		else{
			Coordonnee gche= new Coordonnee(x-1,y-1);
			Coordonnee drte= new Coordonnee(x+1,y-1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					move=true;
				}
			}
		}

		return move;
	}

	public boolean canMove(Coordonnee thePlace) {
		boolean move=false;
		int x=thePlace.getX();
		int y=thePlace.getY();

		if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-1,y+1);
			Coordonnee drte= new Coordonnee(x+1,y+1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					move=true;
				}
			}
			
		}
		else{
			Coordonnee gche= new Coordonnee(x-1,y-1);
			Coordonnee drte= new Coordonnee(x+1,y-1);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					move=true;
				}
			}
		}

		return move;
	}

	public boolean canTake() {
		boolean take=false;
		int x=this.place.getX();
		int y=this.place.getY();

		if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-2,y+2);
			Coordonnee drte= new Coordonnee(x+2,y+2);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					take=true;
				}
			}
			
		}
		else{
			Coordonnee gche= new Coordonnee(x-2,y-2);
			Coordonnee drte= new Coordonnee(x+2,y-2);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					take=true;
				}
			}
		}

		return take;
	}

	public boolean canTake(Coordonnee thePlace) {
		boolean take=false;
		int x=thePlace.getX();
		int y=thePlace.getY();

		if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-2,y+2);
			Coordonnee drte= new Coordonnee(x+2,y+2);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					take=true;
				}
			}
			
		}
		else{
			Coordonnee gche= new Coordonnee(x-2,y-2);
			Coordonnee drte= new Coordonnee(x+2,y-2);
			if (this.plateau.isValide(gche) && this.plateau.isValide(drte)) {
				Piece pieceGche=plateau.getPiece(gche);
				Piece pieceDrte=plateau.getPiece(drte);
			
				if (pieceGche==null || pieceDrte==null) {
					take=true;
				}
			}
		}

		return take;
	}

}
