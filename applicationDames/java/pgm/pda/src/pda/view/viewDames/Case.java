package pda.view.viewDames;

import javax.swing.*;

import pda.datas.datasDames.Coordonnee;

import java.awt.*;


/**
*Cette clases sert à la représentation graphique des cases du jeu de dames
*Elle est sous classe de JButton pour permettre les réaction et une image de fond
*x et y sont les coordonnés de la case dans le tableau (graphique) et sont utilisé pour la synchronisation avec le plateau ( données )
*Les modificateur modifie l'image de fond de la case correspondant au differends status possible
*/


public class Case extends JButton {

	private int x;
	private int y;
	private ImageIcon image;
	private int taillePlateau;

	public Case(int theX, int theY,int theTaillePlateau){
		super();
		this.x = theX;
		this.y  = theY;
		this.taillePlateau=theTaillePlateau;
	}
	
	/**
	*Change l'image de fond de la case par celle d'une case noire vide
	*/
	public void setCaseNoire(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "data/img/" +taillePlateau +"/caseNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'une case blanche vide
	*/
	public void setCaseBlanche(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin =  "data/img/" +taillePlateau+"/caseBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'un pion blanc
	*/
	public void setPionBlanc(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "data/img/" +taillePlateau +"/pionBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'une dame blanche
	*/
	public void setDameBlanc(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin =  "data/img/" +taillePlateau+"/dameBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'un pion noir
	*/
	public void setPionNoir(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "data/img/" +taillePlateau +"/pionNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'une dame noire
	*/
	public void setDameNoir(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "data/img/" +taillePlateau +"/dameNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'une case noire surlignée en rouge, attirant l'attention sur le fait que on peut bouger le pion sur cette case
	*/
	public void setCaseJouable(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {

			String chemin = "data/img/" +taillePlateau+"/caseJouable.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'un pion blanc entouré de jaune, attirant l'attention sur le fait que on peut jouer ce pion
	*/
	public void setPionBlancSurl(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "data/img/" +taillePlateau +"/pionBlancSurl.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'une dame blanche entourée de jaune, attirant l'attention sur le fait que on peut jouer cette dame
	*/
	public void setDameBlancSurl(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin =  "data/img/" +taillePlateau+"/dameBlancSurl.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'un pion blanc entouré de rouge, attirant l'attention sur le fait ce pion est selectionné
	*/
	public void setPionBlancOver(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "data/img/" +taillePlateau +"/pionBlancOver.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Change l'image de fond de la case par celle d'une dame blanc entouré de rouge, attirant l'attention sur le fait cette dame est selectionnée
	*/
	public void setDameBlancOver(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin =  "data/img/" +taillePlateau+"/dameBlancOver.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	/**
	*Renvoie les coordonne de la case dans le damier graphique
	*@return coord Les Coordonnee de la case
	*/
	public Coordonnee getCoordonnee(){
		Coordonnee coord = new Coordonnee(x,y);

		return coord;
	}
	
	/**
	*Renvoit "case", utilisé pour verifier que l'objet sur lequels on clic est une case
	*/
	public String toString(){
		String retCase="case";

		return retCase;
	}
	
	
	
	
	
}
