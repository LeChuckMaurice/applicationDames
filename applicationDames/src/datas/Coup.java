package datas;

public class Coup {

	private int nbPrises;
	private int nbPoints;
	private Coordonnee[] parcours;

	public Coup(){}
	
	//modifier int en void avec à la fin de la methode nbPoints =xx  ?
	public int calculerPoints(){}

	
	private boolean makeVulnerable(){}

	public boolean isAuthorized(){
		boolean authorized=false;
		
		return authorized;
	}

	public int getNbPoints(){
		return nbPoints;
	}

	public int getNbPrises(){
		return nbPrises;
	}

	public Coordonnee[] getParcours(){
		return parcours;
	}

	public Piece[] getPiecesPrises(){
		return null;
	}

	public void setNbPrises( int nbPrises ){}

	public void setParcours( Coordonnee[] parcours){}



}
