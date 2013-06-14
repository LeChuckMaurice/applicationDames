package datas;

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
	
	
	public Partie(int taillePlateau){
		this.plateau = new Plateau(taillePlateau);
		this.tourIA = false;
	}

	// Entrees / Sorties

	public void savePlateau(){
		String fichierPlateau = "plateau.out";
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

	public Plateau getPlateau(){
		return this.plateau;
	}

	public boolean getTourIA(){
		return this.tourIA;
	}
}
