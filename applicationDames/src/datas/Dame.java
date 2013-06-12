package datas;
 
import java.util.ArrayList;

public class Dame extends Piece {

	public Dame(int positionX, int positionY, Plateau thePlateau, boolean pieceIA) {
		super(positionX,positionY,pieceIA,thePlateau,true);
	}

	public ArrayList<Coordonnee> generateCoups() {
		return null;
	}

	public ArrayList<Coordonnee> getDeplacements(Coordonnee place) {
		return null;
	}

	public boolean canMove() {
		return this.canMove(this.place, this.pieceIA);
	}

	public boolean canMove(Coordonnee place, boolean pieceIA ) {
		boolean move = false;

		int x = place.getX();
		int y = place.getY();

		// tableau des coordonnees des cases de distance 1
		Coordonnee[] cases1 = new Coordonnee[4];
		cases1[0] = new Coordonnee(x+1,y+1);
		cases1[1] = new Coordonnee(x+1,y-1);
		cases1[2] = new Coordonnee(x-1,y+1);
		cases1[3] = new Coordonnee(x-1,y-1);

		for(int i=0; i<=3; i++){
			// Si la case est valide et libre
			if(this.plateau.isValide(cases1[i]) && this.plateau.isLibre(cases1[i])){
				move=true;
			}
		}
		// S'il ne peut pas bouger sans prendre
		if(!move){
			// Si il peut prendre (il peut donc bouger)
			if(this.canTake(place,pieceIA)){
				move=true;
			}
		}

		return false;
	}

	public boolean canTake() {

		return this.canTake(this.place, this.pieceIA);
	}

	public boolean canTake(Coordonnee place, boolean pieceIA ) {
		boolean take = false;

		int x = place.getX();
		int y = place.getY();
		int deplacementX=0; // deplacement en x
		int deplacementY=0; // deplacement en y
		Coordonnee coord = new Coordonnee(x,y);

		Piece piece;

		for(int i=0; i<3; i++){
			// 1er tour de boucle : diagonale bas droite
			if(i==0){
				deplacementX=1; deplacementY=1;
			}
			// 2eme tour de boucle : diagonale bas gauche
			if(i==0){
				deplacementX=-1; deplacementY=1;
			}			
			// 3eme tour de boucle : diagonale haut gauche
			if(i==0){
				deplacementX=-1; deplacementY=-1;
			}			
			// 4eme tour de boucle : diagonale haut droite
			if(i==0){
				deplacementX=1; deplacementY=-1;
			}

			x = place.getX();
			y = place.getY();
			// diagonale vers bas droite
			do{
				x = x + deplacementX;
				y = y + deplacementY;
				coord.setX(x);
				coord.setY(y);
			}
			// tant qu'on n'est pas sorti du plateau et que la case est libre
			while(this.plateau.isValide(coord) && this.plateau.isLibre(coord));

			// Si on est encore dans le tableau
			if(this.plateau.isValide(coord)){
				// Recuperation de la piece
				piece = this.plateau.getPiece(coord);

				x = x + deplacementX;
				y = y + deplacementY;
				coord.setX(x);
				coord.setY(y);
				// La piece n'est pas null, qu'elle est adverse et que la case d'apres est libre et valide
				if(piece!=null && piece.isIA()!=pieceIA 
					&& this.plateau.isValide(coord) && this.plateau.isLibre(coord)){
					take = true;
				}
			}
		}

		return take;
	}

}
