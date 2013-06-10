package datas;

public class Plateau {

	public int taille;
	public IA ordinateur;
	public Piece[][] tabPiece;

	public Plateau(int taillePlat){
		this.taille = taillePlat;
		this.ordinateur = new IA(this); 
		this.tabPiece = new Piece[taillePlat][taillePlat];
	}

	// Methodes generales

	public void remplirPlateau(){

		int i = 0;
		int j = 0;
		int depart;
		// nombre de pieces a inserer
		int nbLignes = (this.taille-2)/2;
		int nbPiecesParLigne = this.taille/2;
		int nbPieces = nbLignes*nbPiecesParLigne;

		// remplissage camp joueur
		for(i=0; i<nbLignes; i++){ // y
			depart = (int)Math.pow(-1,i);
			for (j=depart; j<nbPiecesParLigne; i++){ // x
				tabPiece[j][i] = new Piece(j,i,this,false);
				j++;
			}
		}

		// remplissage camp ordinateur
		for(i=0; i<nbLignes; i++){ // y
			depart = (int) Math.pow(-1,i);
			for (j=depart; j<nbPiecesParLigne; i++){ // x
				tabPiece[j][i] = new Piece(j,i,this,true);
				j++;
			}
		}
	}
	
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
		Coordonnee coord=piece.getCoordonnee();
		int x=coord.getX();
		int y=coord.getY();

		tabPiece[x][y]=null;
	}

	private void updateStatus(){
		int y=0;

		for (int i=1;i<taille ;i=i+2) {
			Piece piece = this.getPiece(new Coordonnee(i,y));
			boolean pieceIA=piece.getCamp();
			if (!(pieceIA)) {
				changeStatus(piece);
			}
		}

		y=taille-1;
		for (int i=0;i<taille ;i=i+2) {
			Piece piece = this.getPiece(new Coordonnee(i,y));
			boolean pieceIA=piece.getCamp();
			if (pieceIA) {
				changeStatus(piece);
			}
		}
	}

	/**
	*Change un pion en Dames
	*@param piece Le pion a changer en dames
	*/
	private void changeStatus(Piece piece){

		if (!(piece.isDame())) {
			Coordonnee coord=piece.getCoordonnee();
			int x=coord.getX();
			int y=coord.getY();
			boolean pieceIA=piece.getCamp();

			deletePiece(piece);
			Dames dame=new Dames(x,y,this,pieceIA);
		}	
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
		return null;
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
		int y=coord.getY();

		return (tabPiece[x][y]);
	}




	
}
