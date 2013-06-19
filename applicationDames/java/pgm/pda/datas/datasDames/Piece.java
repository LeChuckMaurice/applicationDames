package pda.datas.datasDames;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Piece décrit une pièce : c'est une super-classe abstraite des classes Dame et Pion.
 * Une pièce est caractérisée par son camp (Ordinateur ou joueur), son statut (Dame ou Pion),
 *<BR> sa coordonnée sur le Plateau. Elle possède une référence sur le Plateau.
 */
public abstract class Piece implements Serializable {

	/**
	 * Camp : vrai si la pièce appartient à l'Ordinateur, faux si elle appartient au Joueur.
	 */
	protected boolean pieceIA;

	/**
	 * statut : vrai si la pièce appartient est une Dame, faux c'est un Pion.
	 */	
	protected boolean dame;
	
	/**
	 * Coordonnées de la Pièce sur le Plateau de jeu.
	 */
	protected Coordonnee place;

	/**
	 * Plateau de jeu
	 */
	protected Plateau plateau;

 
 	/**
 	 * Constructeur de la classe Piece
 	 */
	public Piece(int positionX, int positionY, boolean camp,Plateau thePlateau, boolean isDame){
		this.place = new Coordonnee(positionX,positionY);
		this.pieceIA=camp;
		this.dame=isDame;
		this.plateau=thePlateau;
	}

	// Methodes

	/**
	 * Génère tous les coups jouables par cette Piece
	 */
	public ArrayList<Coup> generateCoups(){

		ArrayList<Coup> coups = new ArrayList<Coup>();
		ArrayList<Coordonnee> deplacementsPossibles  = this.getDeplacements();



		for(int i=0; i<deplacementsPossibles.size(); i++){

			coups.add(new Coup(this, deplacementsPossibles.get(i)));
		}

		return coups;
	}

	/**
	 * Indique si la Piece vulnérable dans sa position actuelle
	 * @return Vrai si la pièce est vulnérable, faux sinon
	 */
	public boolean isVulnerable(){
		return this.isVulnerable(this.place);
	}

	/**
	 * Indique si la Piece vulnérable serait vulnérable si elle était placée 
	 * <BR>sur la case de coordonnée passée en paramètre.
	 * @return Vrai si la pièce la pièce serait vulnérable, faux sinon.
	 */
	public boolean isVulnerable (Coordonnee thePlace){
		boolean vulnerable = false;

		int x = thePlace.getX();  
		int y = thePlace.getY();
		Coordonnee coord = new Coordonnee(x,y);
		Coordonnee arriere;
		Piece piece1=null; 

		ArrayList<Coordonnee> diagonale;
		
		int[][] param = {{-1,-1},{-1,1},{1,-1},{1,1}};
		int j;
		for(int i=0; i<4; i++){

			arriere = new Coordonnee(x-param[i][0],y-param[i][1]);

			//Si la case derriere la piece est libre 
			if(this.plateau.isValide(arriere) && this.plateau.isLibre(arriere)){
				diagonale = this.getDiagonale(coord,param[i][0],param[i][1]);
				j=-1;
				do{
					j++;
					piece1 = this.plateau.getPiece(diagonale.get(j));
				}
				// jusqu'a ce que la case soit occupée
				while(piece1==null && j<diagonale.size()-1);

				// si c'est une piece adverse
				if(piece1!=null && piece1.isIA()!=this.isIA()){
					// Si c'est un pion a un de distance
					if(piece1.isDame()==false && j==0){
						vulnerable=true;
					}
					else if(piece1.isDame()==true){
						vulnerable=true;
					}
				}
			}
		}

		return vulnerable;
	}

	/**
	 * Génère tous les déplacements possibles à partir de la position actuelle de la Pièce
	 * @return tous les déplacements possibles
	 */
	public abstract ArrayList<Coordonnee> getDeplacements();

	/**
	 * Génère tous les déplacements possibles à partir de la position passée en paramètre
	 * @param place position simulée de la pièce
	 * @return tous les déplacements possibles
	 */
	public abstract ArrayList<Coordonnee> getDeplacements(Coordonnee place);


	/**
	 * Recherche tous les pièces adverses que la pièce peut prendre à partir de sa position actuelle
	 * @return La liste des pièces prenables
	 */
	public abstract ArrayList<Piece> prisesPossibles();

	/**
	 * Recherche tous les pièces adverses que la pièce peut prendre à partir de la position passée en paramètre
	 * @param thePlace position simulée de la pièce
	 * @return La liste des pièces prenables
	 */
	public abstract ArrayList<Piece> prisesPossibles(Coordonnee thePlace);

	/**
	 * Indique si la Pièce peut bouger
	 * @return vrai si la pièce peut bouger, faux sinon
	 */
	public abstract boolean canMove();
	/**
	 * Indique si la Pièce peut bouger
	 * @param thePlace position simulée de la pièce
	 * @return vrai si la pièce peut bouger, faux sinon
	 */
	public abstract boolean canMove(Coordonnee place);

	/**
	 * Indique si la Pièce peut prendre
	 * @return vrai si la pièce peut prendre, faux sinon
	 */
	public abstract boolean canTake();
	/**
	 * Indique si la Pièce peut prendre
	 * @param place position simulée de la pièce
	 * @return vrai si la pièce peut prendre, faux sinon
	 */
	public abstract boolean canTake(Coordonnee place);

	//Accesseurs
	
	/**
	*Renvoie le camp auquel appartient la piece
	*@return pieceIA true pour un piece appartenant à l'ordinateur, false pour une du joueur
	*/
	public boolean isIA(){
		return this.pieceIA;
	}
	
	/**
	*Renvoie un objet de type coordonnee modelisant l'emplacement de la piece
	*@return place La coordonnee de la piece
	*/
	public Coordonnee getCoordonnee(){
		return this.place;
	}

	/**
	*Renvoi un boolean indiquant si la piece est ou non une dame
	*@return dame true si la piece est une dame, false si c'est un pion
	*/
	public boolean isDame(){
		return this.dame;
	}
	
	/**
	 * Accesseur de l'attribut Plateau
	 * @return plateau attribut Plateau
	 */
	public Plateau getPlateau(){
		return this.plateau;
	}

	/**
	 * Modificateur de l'attribut place
	 * @param newPlace nouvelle place de la pièce
	 */
	public void setCoordonnee(Coordonnee newPlace){
		this.place = newPlace;
	}

	/**
	 * Liste les coordonnées des cases situées dans une diagonale à partir de la position actuelle de la pièce
	 * @param dirX direction de la diagonale en x. Si dirX=-1, direction haut. Si dirX=1, direction bas.
	 * @param dirY direction de la diagonale en y. Si dirY=-1, direction gauche. Si dirY=1, direction droite.
	 * @return La liste des coordonnées des cases de la diagonale
	 */
	protected ArrayList<Coordonnee> getDiagonale(int dirX, int dirY){
		return this.getDiagonale(this.place, dirX, dirY);
	}

	/**
	 * Liste les coordonnées des cases situées dans une diagonale à partir de la position passée en paramètre
	 * @param dirX direction de la diagonale en x. Si dirX=-1, direction haut. Si dirX=1, direction bas.
	 * @param dirY direction de la diagonale en y. Si dirY=-1, direction gauche. Si dirY=1, direction droite.
	 * @param place position à partir de laquelle part la diagonale.
	 * @return tabCoord liste des coordonnées des cases de la diagonale
	 */
	protected ArrayList<Coordonnee> getDiagonale(Coordonnee place, int dirX, int dirY){
		ArrayList<Coordonnee> tabCoord = new ArrayList<Coordonnee>();

		int x = place.getX();
		int y = place.getY();

		do{
			x = x + dirX;
			y = y + dirY;
			tabCoord.add(new Coordonnee(x,y));
		}
		// tant qu'on n'est pas sorti du plateau et que la case est libre
		while(this.plateau.isValide(new Coordonnee(x,y)));

		//if(tabCoord.size()>0){
			//tabCoord.remove(tabCoord.size()-1);
		//}
		return tabCoord;
	}
	

}
