package datas;
 
/**
 * La classe Coordonnee décrit une coordonnée en abscisse et en ordonnée.
 */
public class Coordonnee {

	/**
	 * Abscisse
	 */
	public int x;

	/**
	 * Ordonnée
	 */
	public int y;

	/**
	 * Constructeur de la classe Coordonnee
	 * @param x abscisse
	 * @param y ordonnée
	 */
	public Coordonnee(int x, int y){
		this.x = x;
		this.y = y;
	}

	//Modificateur
	
	/**
	 * Modificateur de l'attribut x
	 * @param newX nouvelle abscisse
	 */
	public void setX(int newX){
		this.x = newX;
	}

	/**
	 * Modificateur de l'attribut y
	 * @param newY nouvelle ordonnée
	 */
	public void setY(int newY){
		this.y = newY;
	}

	//Accesseur

	/**
	 * Accesseur de l'attribut x
	 * @return x abscisse
	 */
	public int getX(){
		return this.x;
	}

	/**
	 * Accesseur de l'attribut y
	 * @return y ordonnée
	 */
	public int getY(){
		return this.y;
	}

}
