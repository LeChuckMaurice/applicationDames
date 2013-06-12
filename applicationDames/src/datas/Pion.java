package datas;

import java.util.ArrayList;

public class Pion extends Piece {

	public Pion(int positionX, int positionY, Plateau thePlateau, boolean pieceIA) {
		super(positionX,positionY,pieceIA,thePlateau,false);
	}

	public ArrayList<Coordonnee> generateCoups() {
		return null;
	}

	public ArrayList<Coordonnee> getDeplacements(Coordonnee place){
		return null;
	}

	public boolean canMove() {
		return this.canMove(this.place, this.pieceIA);
	}

	public boolean canMove(Coordonnee thePlace, boolean pieceIA) {
		boolean move=false;
		int x=thePlace.getX();
		int y=thePlace.getY();

		// Si la piece appartient à l'IA
		if (pieceIA==true) {
			Coordonnee gche= new Coordonnee(x-1,y+1);
			Coordonnee drte= new Coordonnee(x+1,y+1);

			// Si case droite valide et libre
			if(this.plateau.isValide(drte) && this.plateau.isLibre(drte)){
				move=true;
			}
			// Sinon, si case gauche valide et libre
			else if(this.plateau.isValide(gche) && this.plateau.isLibre(gche)){
				move=true;
			}
			// Sinon, si le pion peut prendre (il peut donc bouger)
			else if(this.canTake(thePlace,pieceIA)){
				move=true;
			}
			
		}
		// Sinon si la piece appartient au joueur
		else if (pieceIA==false) {
			Coordonnee gche= new Coordonnee(x-1,y-1);
			Coordonnee drte= new Coordonnee(x+1,y-1);

			// Si case droite valide et libre
			if(this.plateau.isValide(drte) && this.plateau.isLibre(drte)){
				move=true;
			}
			// Sinon, si case gauche valide et libre
			if(this.plateau.isValide(gche) && this.plateau.isLibre(gche)){
				move=true;
			}
			// Sinon, si le pion peut prendre (il peut donc bouger)
			else if(this.canTake(thePlace,pieceIA)){
				move=true;
			}
			
		}
		return move;
	}

	public boolean canTake() {
		return this.canTake(this.place,this.pieceIA);
	}

	public boolean canTake(Coordonnee thePlace, boolean pieceIA) {
		boolean take=false;
		int x=thePlace.getX();
		int y=thePlace.getY();

		// coordonnees des cases a une case de distance
		Coordonnee[] cases1 = new Coordonnee[4];
		cases1[0] = new Coordonnee(x-1,y+1);
		cases1[1] = new Coordonnee(x+1,y+1);
		cases1[2] = new Coordonnee(x-1,y-1);
		cases1[3] = new Coordonnee(x+1,y-1);

		// coordonnees des cases a 2 case de distance en diagonale
		Coordonnee[] cases2 = new Coordonnee[4];
		cases2[0] = new Coordonnee(x-2,y+2);
		cases2[1] = new Coordonnee(x+2,y+2);
		cases2[2] = new Coordonnee(x-2,y-2);
		cases2[3] = new Coordonnee(x+2,y-2);

		// coordonnees des pieces a une case de distance
		Piece[] pieces1 = new Piece[4];
		for(int i=0; i<=3; i++){
			pieces1[i] = this.plateau.getPiece(cases1[i]);
		}


		for(int j=0; j<=3; j++){
			// Si la case contient une piece et que la case d'apres est valide et libre
			if(pieces1[j]!=null && this.plateau.isValide(cases2[j]) && this.plateau.isLibre(cases2[j])){
				// Si la piece a abattre est du camp adverse
				if(pieces1[j].isIA()!=pieceIA){
					take=true;
				}
			}
		}
		
		return take;
	}
}
