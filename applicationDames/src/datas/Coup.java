package datas;
 
import java.util.ArrayList;

public class Coup {

	private Coordonnee arrivee;
	private Piece piece;
	private Piece piecePrise; 
	private int nbPoints;


	public Coup(Piece piece, Coordonnee arrivee){
		this.arrivee = arrivee;
		this.piece = piece;
		this.piecePrise = findPiecePrise();
		this.nbPoints = calculerPoints();
		if(piece==null)
			System.out.println("piece nulle");
		if(arrivee==null)
			System.out.println("arrivee nulle");

	}
	
	//modifier int en void avec à la fin de la methode nbPoints =xx  ?
	public int calculerPoints(){
		int points =0;

		if(piecePrise!=null){
			points = points + 10;
		}
		
		return points;
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

		Piece piecePrise = null;
		Plateau plateau = this.piece.getPlateau();

		int departX = this.piece.getCoordonnee().getX();
		int departY = this.piece.getCoordonnee().getY();
		int arriveeX = this.arrivee.getX();
		int arriveeY = this.arrivee.getY();


		int dirX;
		int dirY;

		if((arriveeX-departX)>=0) 
			dirX = 1;
		else 
			dirX = -1;

		if((arriveeY-departY)>=0) 
			dirY = 1;
		else 
			dirY = -1;


		Coordonnee coordTmp = new Coordonnee(departX,departY);
		Piece pieceTmp = null;
		do{
			coordTmp.setX(coordTmp.getX()+dirX);
			coordTmp.setY(coordTmp.getY()+dirY);
			pieceTmp = plateau.getPiece(coordTmp);

			// Si c'est une piece adverse
			if(pieceTmp!= null && pieceTmp.isIA()!=this.piece.isIA()){
				piecePrise = pieceTmp;
			}

		}while(pieceTmp==null && plateau.isValide(coordTmp) && plateau.isValide(coordTmp) && !coordTmp.equals(this.arrivee));



		return piecePrise;
	}


	public Piece getPiecePrise(){
		return this.piecePrise;
	}

	public String toString(){
		String chaine = "";
		if(this.piece==null){
			chaine = chaine + "Probleme!!!";
		}
		else{
			chaine = chaine + "Depart"+this.piece.getCoordonnee().toString();
		}
		chaine = chaine +"Arrivee : "+this.arrivee.toString()+"\n";
		return chaine;
	}

}
