package pda.datas.datasDames;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Partie { 
	private Plateau plateau;
	/**
	 * True si c'est le tour de l'IA.
	 * <BR>False si c'est le tour du joueur
	 */
	private boolean tourIA;
	
	public Partie(){
		this.tourIA = false;
	}
	public Partie(int taillePlateau){
		this.plateau = new Plateau(taillePlateau);
		this.tourIA = false;
	}

	// Entrees / Sorties

	public void savePlateau(){
		String fichierPlateau = "data/save/plateau.out";
		FileOutputStream out = null;
		ObjectOutputStream flux = null;
		try {
			out = new FileOutputStream(fichierPlateau);
			flux = new ObjectOutputStream(out);
			flux.writeObject(this.plateau); 
		}
		catch(IOException e) {
			System.out.println("Erreur I/O");
		}
	}

	public Plateau chargerPlateau() throws Exception{
		Plateau plateau1 = new Plateau(10);
		String fichierPlateau="plateau.out";

		FileInputStream out = new FileInputStream(fichierPlateau);
		ObjectInputStream flux = new ObjectInputStream(out);
		plateau1 = (Plateau) flux.readObject();
		flux.close();

		return plateau1;
	}
	
	/**
	 * @return gagnant 1 si l'IA a gagne, -1 si le joueur a gagne, 0 sinon.
	 */
	public int getGagnant(){
		int gagnant;
		int nbIA=0;
		int nbJoueur=0;
		Piece pieceTmp = null;
		for(int i=0; i<this.plateau.getTaille(); i++){
			for(int j=0; j<this.plateau.getTaille(); j++){

				pieceTmp = this.plateau.getPiece(i,j);
				if(pieceTmp!=null){
					if(pieceTmp.isIA()){
						nbIA++;
					}
					else{
						nbJoueur++;
					}
				}
			}
		}

		if(nbIA==0){
			gagnant=-1;
		}
		else if(nbJoueur==0){
			gagnant=1;
		}
		else{
			gagnant=0;
		}

		return gagnant;

	}

	public Plateau getPlateau(){
		return this.plateau;
	}

	public void setPlateau(Plateau plateau){
		this.plateau = plateau;
	}

	public boolean getTourIA(){
		return this.tourIA;
	}

	public void setTourIA(boolean tourIA){
		this.tourIA = tourIA;
	}
}
