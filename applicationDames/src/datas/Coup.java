package datas;
 
import java.util.ArrayList;

public class Coup {

	private Coordonnee arrivee;
	private Piece piece;
	private Piece findPiecePrise;
	private int nbPoints;

	public Coup(Piece piece, Coordonnee arrivee){
		this.arrivee = arrivee;
		this.piece = piece;
		this.piecePrise = getPiecePrise();
		this.nbPoints = calculerPoints();
	}
	
	//modifier int en void avec à la fin de la methode nbPoints =xx  ?
	public int calculerPoints(){
		int points =0;

		points = points+10*nbPrises;
		return 1;
	}

	public voi

	
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

	public Coordonnee getDepart(){
		return this.piece.getCoordonnee();
	}

	public Coordonnee getArrivee(){
		return this.arrivee;
	}

	public Piece getPiece(){
		return this.piece;
	}

	public Piece findPiecePrise(){
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

}
