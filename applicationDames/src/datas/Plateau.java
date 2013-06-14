package datas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable; 
 
public class Plateau implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

		int x = 0;
		int y = 0;

		// remplissage camp IA
		for(y=0; y<(this.taille)/2-1; y++){ // y
			for (x=(y+1)%2; x<this.taille; x=x+2){ // x
				this.tabPiece[x][y] = new Pion(x,y,this,true);
			}
		}
		// remplissage camp joueur
		for(y=y+2; y<this.taille; y++){ // y
			for (x=(y+1)%2; x<this.taille; x=x+2){ // x
				this.tabPiece[x][y] = new Pion(x,y,this,false);
			}
		}
	}
	
	public boolean isValide(Coordonnee coord){
		boolean valide=true;
		int x=coord.getX();
		int y=coord.getY();
		if (x<0 || x>taille-1) {
			valide=false;
		}
		else if (y<0 || y>taille-1) {
			valide=false;
		}
		else if ((x+y)%2 == 0){
			valide=false;
		}

		return valide;
	}

	public boolean isLibre(Coordonnee coord) {
		boolean libre = false;
		int x = coord.getX();
		int y = coord.getY();

		if(this.tabPiece[x][y] == null){
			libre = true;
		}

		return libre;
	}

	/*
	public void playAction(Coup coup){
		Coordonnee arrivee = coup.getArrivee();
		Piece piece = coup.getPiece();
		Piece[] piecesPrises = coup.getPiecesPrises();

		for(int i=0; i<piecesPrises.length; i++){
			this.deletePiece(piecesPrises[i]);
		}
		this.movePiece(piece, arrivee);

	}
	*/

	/**
	*Deplace une piece vers la coordonnee place en parametre
	*@param piece La piece que l'on deplace
	*@param newPlace Le nouvel emplacement de la piece
	*@throws IllegalArgumentException si la nouvelle position n'est pas sur le plateau
	*@throws IllegalArgumentException si la nouvelle position est occupee par une autre piece
	*/
	public void movePiece(Piece piece, Coordonnee newPlace) throws IllegalArgumentException{
		int newX=newPlace.getX();
		int newY=newPlace.getY();

		this.movePiece(piece,newX,newY);
	}

	/**
	*Deplace une piece vers la coordonnee place en parametre
	*@param piece La piece que l'on deplace
	*@param newX La nouvelle position en abcisse de la piece
	*@param newY La nouvelle position en ordonnee de la piece
	*@throws IllegalArgumentException si la nouvelle position n'est pas sur le plateau
	*@throws IllegalArgumentException si la nouvelle position est occupee par une autre piece
	*/
	public void movePiece(Piece piece, int newX, int newY) throws IllegalArgumentException{
		Coordonnee coord = new Coordonnee(newX,newY);
		if(!this.isValide(coord)){
			throw new IllegalArgumentException("La nouvelle position ("+newX+","+newY+") est invalide.");
		}
		else if(!this.isLibre(coord)){
			throw new IllegalArgumentException("La nouvelle position est occupee par une autre piece.");
		}
		else{
			deletePiece(piece);
			piece.setCoordonnee(coord);
			tabPiece[newX][newY]=piece;
		}
	}	

	/**
	*Supprime une piece du plateau
	*@param piece La piece a supprimer
	*/
	public void deletePiece(Piece piece){
		Coordonnee coord=piece.getCoordonnee();
		int x=coord.getX();
		int y=coord.getY();

		if(this.isValide(coord)) {
			tabPiece[x][y]=null;
		}
	}

	public void updateStatus(){
		int y=0;

		for (int i=1;i<taille ;i=i+2) {
			Piece piece = this.getPiece(new Coordonnee(i,y));

			if(piece!=null && !piece.isIA()) {
				changeStatus(piece);
			}
		}

		y=taille-1;
		for (int i=0;i<taille ;i=i+2) {
			Piece piece = this.getPiece(new Coordonnee(i,y));
			
			if(piece!=null && piece.isIA()) {
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
			boolean pieceIA=piece.isIA();

			deletePiece(piece);
			Dame dame=new Dame(x,y,this,pieceIA);
			this.tabPiece[x][y] = dame;
		}	
	}

	/**
	*Affiche le plateau sous forme d'une chaine de caractere
	*/
	public String toString(){
		String chainePlateau="";
		Piece piece;
		for(int i=0; i<this.taille; i++){ // x

			chainePlateau = chainePlateau+"|";
			for (int j=0; j<this.taille; j++){ // y
				piece = this.tabPiece[j][i];
				if(piece==null){
					chainePlateau = chainePlateau + " |";
				}
				else if(piece.isIA()){ // Sinon,si c'est une piece de l'IA
					if(piece.isDame()){ // Si c'est une dame
						chainePlateau = chainePlateau + "d|";
					}
					else if(!piece.isDame()){ // Si c'est un pion
						chainePlateau = chainePlateau + "o|";
					}
				}
				else if(!piece.isIA()){ // Sinon, si c'est une piece du joueur
					if(piece.isDame()){ // Si c'est une dame
						chainePlateau = chainePlateau + "D|";
					}
					else if(!piece.isDame()){ // Si c'est un pion
						chainePlateau = chainePlateau + "O|";
					}
				}
			}
			chainePlateau = chainePlateau + "\n";
		}
		
		return chainePlateau;
	}

	// Entree / Sorties

	public void savePlateau(){
		String fichierPlateau = "plateau.out";
		FileOutputStream out = null;
		ObjectOutputStream flux = null;
		try {
			out = new FileOutputStream(fichierPlateau);
			flux = new ObjectOutputStream(out);
			flux.writeObject(this);
		}
		catch(IOException e) {
			System.out.println("Erreur I/O");
		}
	}

	public Plateau chargerPlateau() throws Exception{
		Plateau plateau1 = new Plateau(10);
		String fichierPlateau="plateau.out";

		FileInputStream out = new FileInputStream(fichierPlateau);
		ObjectInputStream flux = new ObjectInputStream(out);
		plateau1 = (Plateau) flux.readObject();
		flux.close();

		return plateau1;
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
		Piece piece;
		int x=coord.getX();
		int y=coord.getY();

		if(!this.isValide(coord)){
			piece = null;
		}
		else{
			piece=tabPiece[x][y];
		}
		return piece;
	}

	public Piece getPiece(int x, int y){
		Coordonnee coord = new Coordonnee(x,y);
		return this.getPiece(coord);
	}




	
}
