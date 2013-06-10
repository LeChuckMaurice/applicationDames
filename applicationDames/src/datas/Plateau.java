package datas;

public class Plateau {

	public int taille;
	public IA ordinateur;
	public Piece[][] tabPiece;

	public Plateau(){

	}

	// Methodes generales

	public boolean isValide(Coordonnee coord){
		boolean valide=true;
		int x=coord.getX();
		int y=coord.getY();
		if (x<0 || x>=taille) {
			valide=false;
		}
		else if (y<0 || y>=taille) {
			valide=false;
		}

		return valide;
	}

	public void playAction(Coup coup){

	}

	/**
	*Deplace une piece vers la coordonnee place en parametre
	*@param piece La piece que l'on deplace
	*@param newPlace Le nouvel emplacement de la piece
	*/
	private void movePiece(Piece piece, Coordonnee newPlace){

	}

	/**
	*Deplace une piece vers la coordonnee place en parametre
	*@param piece La piece que l'on deplace
	*@param newX La nouvelle position en abcisse de la piece
	*@param newY La nouvelle position en ordonnee de la piece
	*/
	private void movePiece(Piece piece, int newX, int newY){

	}	

	/**
	*Supprime une piece du plateau
	*@param piece La piece a supprimer
	*/
	private void deletePiece(Piece piece){

	}

	private void updateStatus(){

	}

	/**
	*Change un pion en Dames
	*@param piece Le pion a changer en dames
	*/
	private void changeStatus(Piece piece){
		piece.setDame();
	}

	/**
	*Affiche le plateau sous forme d'une chaine de caractere
	*/
	public String toString(){
		String chainePlateau=null;


		return chainePlateau;
	}

	// Entree / Sorties

	public void savePlateau(){

	}

	public Plateau chargerPlateau(){

	}

	// Accesseurs

	/**
	*Accesseur de la taille du plateau, sachant que ce dernier est un carre
	*@return taille la taille en nombre de case d'un coté du plateau
	*/
	public int getTaille(){
		return this.taille;
	}

	/**
	*Accesseur du tableau à 2 dimensions de pièces
	*@return tabPiece Le tableau contenant l'ensemble des pieces du plateau
	*/
	public Piece[][] getTabPiece(){
		return tabPiece;
	}

	/**
	*Accesseur d'une piece contenue dans le plateau
	*@param coord Les coordonnee de la piece que on souhaite obtenir
	*/
	public Piece getPiece(Coordonnee coord){
		int x=coord.getX();
		int y=coor.getY();

		return (tabPiece[x][y]);
	}




	
}
