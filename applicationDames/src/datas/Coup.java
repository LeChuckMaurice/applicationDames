package datas;

import java.util.ArrayList;

public class Coup {

	private int nbPrises;
	private int nbPoints;
	private ArrayList<Coordonnee> parcours;
	private Piece piece;

	public Coup(int nbPrises, ArrayList<Coordonnee> parcours, Piece piece){
		this.nbPrises = nbPrises;
		this.parcours = parcours;
		this.piece = piece;
		this.nbPoints = calculerPoints();
	}
	
	//modifier int en void avec à la fin de la methode nbPoints =xx  ?
	public int calculerPoints(){
		return 1;
	}

	
	private boolean makeVulnerable(){
		return true;
	}

	public boolean isAuthorized(){
		boolean authorized=false;
		
		return authorized;
	}

	public int getNbPoints(){
		return this.nbPoints;
	}

	public int getNbPrises(){
		return this.nbPrises;
	}

	public ArrayList<Coordonnee> getParcours(){
		return this.parcours;
	}

	public Coordonnee getDepart(){
		return this.parcours.get(0);
	}

	public Coordonnee getArrivee(){
		return this.parcours.get(this.parcours.size()-1);
	}

	public Piece getPiece(){
		return this.piece;
	}

	public Piece[] getPiecesPrises(){
		Piece[] piecesPrises = new Piece[this.nbPrises];
		Piece piecePrise = null;;

		Coordonnee place1;
		Coordonnee place2;

		int xPiece;
		int yPiece;


		if(this.nbPrises!=0){
			for(int i=0; i<nbPrises; i++){
				place1 = this.parcours.get(i);
				place2 = this.parcours.get(i+1);

				xPiece = (int) (place1.getX()+place2.getX())/2;
				yPiece = (int) (place1.getY()+place2.getY())/2;

				piecePrise=this.piece.getPlateau().getPiece(new Coordonnee(xPiece,yPiece));
				
				if(piecePrise!=null){
					piecesPrises[i] = piecePrise;
				}
			}
		}
		return piecesPrises;
	}

	public void setNbPrises( int nbPrises ){}

	public void setParcours( Coordonnee[] parcours){}



}
