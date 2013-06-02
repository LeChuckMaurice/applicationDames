package datas;

public class Dames extends Piece {

	public Dames() {
		
	}

	public boolean isDame() {
		return false;
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
