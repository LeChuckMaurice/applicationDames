package view;

import javax.swing.*;
import java.awt.*;

public class Case extends JLabel {

	private int x;
	private int y;
	private ImageIcon image;
	//private int tailleCase;
	private int taillePlateau;

	public Case(int theX, int theY,int theTaillePlateau){
		super();
		this.x = theX;
		this.y  = theY;
		//this.setTaille(theTaillePlateau);
		this.taillePlateau=theTaillePlateau;
	}

	/*
	public void setTaille(int taillePlateau){
		if (taillePlateau==8) {
			tailleCase=30;
		}
		else if (taillePlateau==10) {
			tailleCase=24;
		}
		else if (taillePlateau==12) {
			tailleCase=20;
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
	}
	*/

	public void setCaseNoire(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+taillePlateau +"/caseBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setCaseBlanche(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+ taillePlateau+"/caseNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setPionBlanc(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+taillePlateau +"/pionBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setDameBlanc(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+ taillePlateau+"/dameBlanc.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setPionNoir(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+taillePlateau +"/pionNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setDameNoir(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+taillePlateau +"/dameNoir.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setCaseJouable(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {

			String chemin = "datas/"+taillePlateau+"/caseJouable.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setPionBlancSurl(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+taillePlateau +"/pionBlancSurl.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	public void setDameBlancSurl(){
		if (taillePlateau==8 || taillePlateau==10 || taillePlateau==12) {
			String chemin = "datas/"+ taillePlateau+"/dameBlancheSurl.png";
			image = new ImageIcon(chemin);
			this.setIcon(image);
		}
		else{
			System.out.println("Taille plateau Invalide");
		}
		
	}

	
}
