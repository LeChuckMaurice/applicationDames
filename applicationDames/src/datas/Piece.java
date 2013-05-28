package datas;

public abstract class Piece {

	private boolean camp;
	private Coordonnee place;

 
	public Piece(){}

	// Méthodes

	public boolean isVulnerable(){}

	public boolean isVulnerable(Coordonnee place){}

	public abstract boolean isDame(){}

	public abstract Coup[] generateCoups(){}

	public abstract Coordonnee[] getDeplacements(Coordonnee place){}

	public abstract boolean canMove(){}

	public abstract boolean canMove( Coordonnee place ){}

	public abstract boolean canTake(){}

	public abstract boolean canTake( Coordonnee place ){}

	//Accesseur

}
