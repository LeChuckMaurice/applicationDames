package pda.datas.datasDames;
 
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Dame
 */
public class Dame extends Piece implements Serializable {


	/**
	 * Numéro de version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de la classe Dame
	 */
	public Dame(int positionX, int positionY, Plateau thePlateau, boolean pieceIA) {
		super(positionX,positionY,pieceIA,thePlateau,true);
	}

	/**
	 * Génère tous les déplacements possibles à partir de la position actuelle de la Dame
	 * @return tous les déplacements possibles
	 */
	@Override
	public ArrayList<Coordonnee> getDeplacements() {
		return this.getDeplacements(this.place);
	}

	/**
	 * Génère tous les déplacements possibles à partir de la position passée en paramètre
	 * @param place position simulée de la pièce
	 * @return tous les déplacements possibles
	 */
	@Override
	public ArrayList<Coordonnee> getDeplacements(Coordonnee place) {

		ArrayList<Coordonnee> tabCoord = new ArrayList<Coordonnee>();
		Coordonnee coord;
		
		int x = place.getX();
		int y = place.getY();

		int dirX=1;
		int dirY=1;
		int i;
		int j;
		// déplacements dans le cas d'une prise
		ArrayList<Piece> prisesPossibles = this.prisesPossibles();

		int xPrise;
		int yPrise;
		// Si des prises sont possibles, on ajoute les deplacements correspondants a la liste des deplacements
		if(prisesPossibles().size()>0){
			for(i=0; i<prisesPossibles.size(); i++){
				xPrise = prisesPossibles.get(i).getCoordonnee().getX(); 
				yPrise = prisesPossibles.get(i).getCoordonnee().getY();

				j=1;

				if((xPrise-x)>=0) dirX = 1;
				if((xPrise-x)<0) dirX = -1;
				if((yPrise-y)>=0) dirY = 1;
				if((yPrise-y)<0) dirY = -1;

				coord = new Coordonnee(xPrise+j*dirX, yPrise+j*dirY);

				while(this.plateau.isValide(coord) && this.plateau.isLibre(coord)){

					if((xPrise-x)>=0) dirX = 1;
					if((yPrise-y)<0) dirY = -1;
	
					tabCoord.add(coord);
					j++;
					coord = new Coordonnee(xPrise+j*dirX, yPrise+j*dirY);
				}
			}
		}
		// Ajout de tous les deplacements possibles sans prises

		ArrayList<Coordonnee> diagonaleHG = this.getDiagonale(-1,-1);
		ArrayList<Coordonnee> diagonaleHD = this.getDiagonale(+1,-1);
		ArrayList<Coordonnee> diagonaleBG = this.getDiagonale(+1,+1);
		ArrayList<Coordonnee> diagonaleBD = this.getDiagonale(-1,+1);			
			
		i=0;
		while(this.plateau.isValide(diagonaleHG.get(i)) && this.plateau.isLibre(diagonaleHG.get(i)) && i<diagonaleHG.size()){
			tabCoord.add(diagonaleHG.get(i));
			i++;
		}
		i=0; 
		while(this.plateau.isValide(diagonaleHD.get(i)) && this.plateau.isLibre(diagonaleHD.get(i)) && i<diagonaleHD.size()){
			tabCoord.add(diagonaleHD.get(i));
			i++;
		}
		i=0;
		while(this.plateau.isValide(diagonaleBG.get(i)) && this.plateau.isLibre(diagonaleBG.get(i)) && i<diagonaleBG.size()){
			tabCoord.add(diagonaleBG.get(i));
			i++;
		}
		i=0;
		while(this.plateau.isValide(diagonaleBD.get(i)) && this.plateau.isLibre(diagonaleBD.get(i)) && i<diagonaleBD.size()){
			tabCoord.add(diagonaleBD.get(i));
			i++;
		}

		return tabCoord;

	}


	/**
	 * Recherche tous les pièces adverses que la Dame peut prendre à partir de sa position actuelle
	 * @return La liste des pièces prenables
	 */
	@Override
	public ArrayList<Piece> prisesPossibles() {
		return this.prisesPossibles(this.place);
	}

	/**
	 * Recherche tous les pièces adverses que la Dame peut prendre à partir de la position passée en paramètre
	 * @param place position simulée de la pièce
	 * @return La liste des pièces prenables
	 */
	@Override
	public ArrayList<Piece> prisesPossibles(Coordonnee place) {
		ArrayList<Piece> prisesPossibles = new ArrayList<Piece>();

		int x = place.getX();  
		int y = place.getY();
		int deplacementX=0; // deplacement en x
		int deplacementY=0; // deplacement en y
		Coordonnee coord = new Coordonnee(x,y);

		Piece piece;

		for(int i=0; i<=3; i++){
			// 1er tour de boucle : diagonale bas droite
			if(i==0){
				deplacementX=1; deplacementY=1;
			}
			// 2eme tour de boucle : diagonale bas gauche
			if(i==1){
				deplacementX=-1; deplacementY=1;
			}			
			// 3eme tour de boucle : diagonale haut gauche
			if(i==2){
				deplacementX=-1; deplacementY=-1;
			}			
			// 4eme tour de boucle : diagonale haut droite
			if(i==3){
				deplacementX=1; deplacementY=-1;
			}

			x = place.getX();
			y = place.getY();
			// diagonale 
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
				if(piece!=null && piece.isIA()!=this.isIA() 
					&& this.plateau.isValide(coord) && this.plateau.isLibre(coord)){
					prisesPossibles.add(piece);
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
	 * @param thePlace position simulée de la pièce
	 * @return vrai si la pièce peut bouger, faux sinon
	 */
	@Override
	public boolean canMove(Coordonnee place) {
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
			if(this.canTake()){
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
	public boolean canTake(Coordonnee place) {
		boolean take = false;

		int x = place.getX();  
		int y = place.getY();
		int deplacementX=0; // deplacement en x
		int deplacementY=0; // deplacement en y
		Coordonnee coord = new Coordonnee(x,y);

		Piece piece;

		for(int i=0; i<=3; i++){
			// 1er tour de boucle : diagonale bas droite
			if(i==0){
				deplacementX=1; deplacementY=1;
			}
			// 2eme tour de boucle : diagonale bas gauche
			if(i==1){
				deplacementX=-1; deplacementY=1;
			}			
			// 3eme tour de boucle : diagonale haut gauche
			if(i==2){
				deplacementX=-1; deplacementY=-1;
			}			
			// 4eme tour de boucle : diagonale haut droite
			if(i==3){
				deplacementX=1; deplacementY=-1;
			}

			x = place.getX();
			y = place.getY();
			// diagonale 
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
				if(piece!=null && piece.isIA()!=this.isIA()
					&& this.plateau.isValide(coord) && this.plateau.isLibre(coord)){
					take = true;
				}
			}
		}

		return take;
	}



}
