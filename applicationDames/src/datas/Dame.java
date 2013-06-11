package datas;

public class Dame extends Piece {

	public Dame(int positionX, int positionY, Plateau thePlateau, boolean pieceIA) {
		super(positionX,positionY,pieceIA,thePlateau,true);
	}

	public Coup[] generateCoups() {
		return null;
	}

	public Coordonnee[] getDeplacements(Coordonnee place) {
		return null;
	}

	public boolean canMove() {
		return false;
	}

	public boolean canMove(Coordonnee place, boolean pieceIA ) {
		return false;
	}

	public boolean canTake() {
		return false ;
	}

	public boolean canTake(Coordonnee place, boolean pieceIA ) {
		return false;
	}

}
