package datas;
import datas.*;

public abstract class Piece {

	private boolean camp;
	private Coordonnee place;

 
	public Piece(){}

	// Methodes

	public boolean isVulnerable(){}

	public boolean isVulnerable (Coordonnee place){}

	public abstract boolean isDame()

	public abstract Coup[] generateCoups()

	public abstract Coordonnee[] getDeplacements(Coordonnee place)

	public abstract boolean canMove()

	public abstract boolean canMove( Coordonnee place )

	public abstract boolean canTake()

	public abstract boolean canTake( Coordonnee place )

	//Accesseur
	
	public boolean getCamp(){
		return camp;
	}
	
	public Coordonnee getCoordonnee(){
		return place;
	}
	
	public Plateau getPlateau(){}
	
}
