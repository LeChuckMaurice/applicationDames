package datas;

public class Dames extends Piece {

	public Dames(int positionX, int positionY, boolean pieceIA) {
		super(positionX,positionY,pieceIA,true);
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

	public boolean canMove(Coordonnee place) {
		return false;
	}

	public boolean canTake() {
		return false ;
	}

	public boolean canTake(Coordonnee place) {
		return false;
	}

}
