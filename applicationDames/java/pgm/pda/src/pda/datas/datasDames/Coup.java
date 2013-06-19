package pda.datas.datasDames;
 
import java.util.ArrayList;
/**La classe Coup décrit le mouvement d'une piece : coordonnées d'arrivée, piecePrise. 
 *Un pion peut effectuer plusieurs coup pendant un tour s'il peut effectuer plusieurs prises.
 *<BR>Elle permet aussi d'évaluer la qualité du coup d'un point de vue stratégique avec un système de points.
 */
public class Coup {

	// Attributs

	/**
	 * Coordonnées de la case d'arrivée de la pièce.
	 */
	private Coordonnee arrivee;

	/**
	 * Piece qui effectue le coup
	 */
	private Piece piece;

	/**
	 * Piece abattue pendant le coup, vaut null si aucune pièce n'est abattue.
	 */
	private Piece piecePrise; 

	/**
	 * Nombre de points que vaut le coup, permet d'évaluer si le coup est bon à jouer d'un point de vue stratégique.
	 */
	private int nbPoints;
 

 	// Constructeur

	/**
	 * Constructeur de la classe Coup
	 * @param piece piece qui effectue le coup
	 * @param arrivee coordonnées de la case d'arrivée de la piece
	 */
	public Coup(Piece piece, Coordonnee arrivee){
		this.arrivee = arrivee;
		this.piece = piece;
		this.piecePrise = findPiecePrise();
		this.nbPoints = calculerPoints();
	}


	// Méthodes

	/**
	 * Calcule le nombre de points de ce Coup.
	 *<BR>Le calcul se bases sur plusieurs critères comme le nombre de pièce prises, la vulnérabilité de la pièce 
	 *<BR>avant ou après le coup, la possibilité pour un pion de se transformer en Dame.
	 */
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

	/**
	 *	Indique si le coup donne à la pièce la possibilité de se transformer en Dame.
	 * @return transformDame vaut vrai si la pièce est un pion et qu'il permet de se transformer en dame, faux sinon.
	 */
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

	/**
	 * Indique si le coup met en danger la pièce.
	 * @return makeVulnerable vaut vrai si la pièce sera vulnérable lorsqu'elle aura effectué ce Coup, faux sinon
	 */
	private boolean makeVulnerable(){
		boolean makeVulnerable = false;
		if(this.piece.isVulnerable(this.arrivee)){
			makeVulnerable = true;
		}
		return makeVulnerable;
	}

	/**
	 * Indique si la pièce est en danger
	 * @return isVulnerable vaut vrai si la pièce est en danger, faux sinon.
	 */
	private boolean isVulnerable(){
		boolean isVulnerable = false;
		if(this.piece.isVulnerable()){
			isVulnerable = true;
		}
		return isVulnerable;
	}

	/**
	 * Indique la pièce qui sera prise pendant ce coup.
	 * @return piecePrise la pièce qui sera prise pendant ce coup, null si aucune prise n'est faite
	 */
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


	// Accesseurs

	/**
	 * Accesseur de l'attribut nbPoints
	 * @return l'attribut nbPoints
	 */
	public int getNbPoints(){
		return this.nbPoints;
	}

	/**
	 * Accesseur des coordonnées de l'attribut Piece
	 * @return les coordonnées de l'attribut Piece 
	 */
	public Coordonnee getDepart(){
		return this.piece.getCoordonnee();
	}

	/**
	 * Accesseur de l'attribut arrivee
	 * @return l'attribut arrivee
	 */
	public Coordonnee getArrivee(){
		return this.arrivee;
	}

	/**
	 * Accesseur de l'attribut piece
	 * @return l'attribut piece
	 */
	public Piece getPiece(){
		return this.piece;
	}

	/**
	 * Accesseur de l'attribut piecePrise
	 * @return l'attribut piecePrise
	 */
	public Piece getPiecePrise(){
		return this.piecePrise;
	}
	
}
