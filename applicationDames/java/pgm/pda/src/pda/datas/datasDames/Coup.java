package pda.datas.datasDames;
 
import java.util.ArrayList;
/** La classe Coup décrit un Coup*/
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
	}
	
	//modifier int en void avec à la fin de la methode nbPoints =xx  ?
	public int calculerPoints(){
		int points =0;

		if(piecePrise!=null){
			points = points + 10;
		}
		if(transformDame()){
			points = points + 20;
		}
		if(makeVulnerable()){
			points = points - 11;
		}
		else if(isVulnerable()){
			points = points + 11;
		}

		
		return points;
	}

	public boolean transformDame(){
		boolean transformDame = false;
		// Si la piece n'est pas deja une dame
		if(!piece.isDame()){
			if(piece.isIA()){
				if(this.arrivee.getY()==this.piece.getPlateau().getTaille()-1){
					transformDame = true;
				}
			}
			else{
				if(this.arrivee.getY()==0){
					transformDame = true;
				}
			}
		}

		return transformDame;
	}

	private boolean makeVulnerable(){
		boolean makeVulnerable = false;
		if(this.piece.isVulnerable(this.arrivee)){
			makeVulnerable = true;
		}
		return makeVulnerable;
	}

	private boolean isVulnerable(){
		boolean isVulnerable = false;
		if(this.piece.isVulnerable()){
			isVulnerable = true;
		}
		return isVulnerable;
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
