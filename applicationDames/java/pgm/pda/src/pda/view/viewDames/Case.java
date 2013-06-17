package pda.view.viewDames;

import javax.swing.*;

import pda.datas.datasDames.Coordonnee;

import java.awt.*;

public class Case extends JButton {

	private int x;
	private int y;
	private ImageIcon image;
	private int taillePlateau;

	public Case(int theX, int theY,int theTaillePlateau){
		super();
		this.x = theX;
		this.y  = theY;
		//this.setTaille(theTaillePlateau);
		this.taillePlateau=theTaillePlateau;
	}
	
	public void setCaseNoire(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" +taillePlateau +"/caseNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setCaseBlanche(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" + taillePlateau+"/caseBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setPionBlanc(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" +taillePlateau +"/pionBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setDameBlanc(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" + taillePlateau+"/dameBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setPionNoir(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" +taillePlateau +"/pionNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setDameNoir(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" +taillePlateau +"/dameNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setCaseJouable(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {

			String chemin = "datas/img/" +taillePlateau+"/caseJouable.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setPionBlancSurl(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" +taillePlateau +"/pionBlancSurl.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setDameBlancSurl(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" + taillePlateau+"/dameBlancSurl.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setPionBlancOver(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" +taillePlateau +"/pionBlancOver.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setDameBlancOver(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/img/" + taillePlateau+"/dameBlancOver.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public Coordonnee getCoordonnee(){
		Coordonnee coord = new Coordonnee(x,y);

		return coord;
	}
	
	public String toString(){
		String retCase="case";

		return retCase;
	}
	
	
	
	
	
}
