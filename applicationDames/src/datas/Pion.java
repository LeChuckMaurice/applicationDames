package datas;

public class Pion extends Piece {

	public Pion(int positionX, int positionY, boolean pieceIA) {
		super(positionX,positionY,pieceIA,false);
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
		return false;
	}

	public boolean canTake(Coordonnee place) {
		return false;
	}

}
