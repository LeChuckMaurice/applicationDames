package datas;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class Piece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// PieceIA : true=IA / false=joueur
	protected boolean pieceIA;
	// dame : true=Dame / false=Pion
	protected boolean dame;
	protected Coordonnee place;
	protected Plateau plateau;

 
	public Piece(int positionX, int positionY, boolean camp,Plateau thePlateau, boolean isDame){
		this.place = new Coordonnee(positionX,positionY);
		this.pieceIA=camp;
		this.dame=isDame;
		this.plateau=thePlateau;
	}

	// Methodes

	public ArrayList<Coup> generateCoups(){

		ArrayList<Coup> coups = new ArrayList<Coup>();
		ArrayList<Coordonnee> deplacementsPossibles  = this.getDeplacements();



		for(int i=0; i<deplacementsPossibles.size(); i++){

			coups.add(new Coup(this, deplacementsPossibles.get(i)));
		}

		return coups;
	}

	public boolean isVulnerable(){
		return this.isVulnerable(this.place);
	}

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


	public abstract ArrayList<Coordonnee> getDeplacements();
	public abstract ArrayList<Coordonnee> getDeplacements(Coordonnee place);

	public abstract ArrayList<Piece> prisesPossibles();
	public abstract ArrayList<Piece> prisesPossibles(Coordonnee thePlace, boolean pieceIA);

	public abstract boolean canMove();
	public abstract boolean canMove( Coordonnee place, boolean pieceIA );

	public abstract boolean canTake();
	public abstract boolean canTake( Coordonnee place, boolean pieceIA );

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
	

	public Plateau getPlateau(){
		return this.plateau;
	}


	public void setCoordonnee(Coordonnee newPlace){
		this.place = newPlace;
	}


	protected ArrayList<Coordonnee> getDiagonale(int dirX, int dirY){
		return this.getDiagonale(this.place, dirX, dirY);
	}


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
