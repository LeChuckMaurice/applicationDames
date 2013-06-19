package pda.datas.datasDames;
 
import java.util.ArrayList;

public class Pion extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de la classe Pion
	 */
	public Pion(int positionX, int positionY, Plateau thePlateau, boolean pieceIA) {
		super(positionX,positionY,pieceIA,thePlateau,false);
	}
	
	/**
	 * Génère tous les déplacements possibles à partir de la position actuelle du Pion
	 * @return tous les déplacements possibles
	 */
	@Override
	public ArrayList<Coordonnee> getDeplacements(){
		return this.getDeplacements(this.place);
	}

	/**
	 * Génère tous les déplacements possibles à partir de la position passée en paramètre
	 * @param place position simulée de la pièce
	 * @return tous les déplacements possibles
	 */
	@Override
	public ArrayList<Coordonnee> getDeplacements(Coordonnee place){

		ArrayList<Coordonnee> tabCoord = new ArrayList<Coordonnee>();

		int x = place.getX();
		int y = place.getY();

		Coordonnee caseAvantGauche;
		Coordonnee caseAvantDroit;

		if(this.isIA()){
			caseAvantGauche = new Coordonnee(x-1,y+1);
			caseAvantDroit = new Coordonnee(x+1,y+1);
		}
		else{
			caseAvantGauche = new Coordonnee(x-1,y-1);
			caseAvantDroit = new Coordonnee(x+1,y-1);
		}

		if(this.plateau.isValide(caseAvantGauche) && this.plateau.isLibre(caseAvantGauche)){
			tabCoord.add(caseAvantGauche);
		}
		if(this.plateau.isValide(caseAvantDroit) && this.plateau.isLibre(caseAvantDroit)){
			tabCoord.add(caseAvantDroit);
		}

		ArrayList<Piece> prisesPossibles = this.prisesPossibles();
		int xPrise;
		int yPrise;
		if(prisesPossibles.size()>0){
			for(int i=0; i<prisesPossibles.size(); i++){
				xPrise = prisesPossibles.get(i).getCoordonnee().getX();
				yPrise = prisesPossibles.get(i).getCoordonnee().getY();
				tabCoord.add(new Coordonnee(2*xPrise-x,2*yPrise-y));
			}
		}

		return tabCoord;
	}

	/**
	 * Recherche tous les pièces adverses que le Pion peut prendre à partir de sa position actuelle
	 * @return La liste des pièces prenables
	 */
	@Override
	public ArrayList<Piece> prisesPossibles() {
		return this.prisesPossibles(this.place);
	}

	/**
	 * Recherche tous les pièces adverses que le Pion peut prendre à partir de la position passée en paramètre
	 * @param thePlace position simulée de la pièce
	 * @return La liste des pièces prenables
	 */
	@Override
	public ArrayList<Piece> prisesPossibles(Coordonnee thePlace) {
		ArrayList<Piece> prisesPossibles = new ArrayList<Piece>();

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
				if(pieces1[j].isIA()!=this.isIA()){
					prisesPossibles.add(pieces1[j]);
				}
			}
		}
		
		return prisesPossibles;
	}

	/**
	 * Indique si la Pièce peut bouger
	 * @return vrai si la pièce peut bouger, faux sinon
	 */
	@Override
	public boolean canMove() {
		return this.canMove(this.place);
	}

	/**
	 * Indique si la Pièce peut bouger
	 * @param place position simulée de la pièce
	 * @return vrai si la pièce peut bouger, faux sinon
	 */
	@Override
	public boolean canMove(Coordonnee thePlace) {
		boolean move=false;
		int x=thePlace.getX();
		int y=thePlace.getY();
		// Si la piece appartient à l'IA
		if (this.isIA()==true) {
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
			else if(this.canTake(thePlace)){
				move=true;
			}
			
		}
		// Sinon si la piece appartient au joueur
		else if (this.isIA()==false) {
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
			else if(this.canTake(thePlace)){
				move=true;
			}
			
		}
		return move;
	}

	/**
	 * Indique si la Pièce peut prendre
	 * @return vrai si la pièce peut prendre, faux sinon
	 */
	@Override
	public boolean canTake() {
		return this.canTake(this.place);
	}

	/**
	 * Indique si la Pièce peut prendre
	 * @param place position simulée de la pièce
	 * @return vrai si la pièce peut prendre, faux sinon
	 */
	@Override
	public boolean canTake(Coordonnee thePlace) {
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
				if(pieces1[j].isIA()!=this.isIA()){
					take=true;
				}
			}
		}
		
		return take;
	}
}
