package pda.datas.datasDames;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class launcher {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String saisie;
		

		boolean canContinue = false;


		Partie partie = null;

		System.out.println("Que souhaitez vous faire ?");
		System.out.println("1 - Demarrer une nouvelle partie");
		System.out.println("2 - Charger une partie sauvegardée");
		System.out.println("3 - Quitter le jeu");
		while(canContinue==false) {

			// Saisie dans que l'utilisateur n'a pas saisi 1, 2 ou 3
			do{
				System.out.println("Veuillez saisir le numero de votre choix (1, 2 ou 3)");
				saisie = scanner.nextLine();	
			}while(!saisie.equals("1") && !saisie.equals("2") && !saisie.equals("3"));


			// Si le joueur a decide de demarrer une nouvelle partie
			if(saisie.equals("1")){
				// Demande de la taille du damier
				// Saisie dans que l'utilisateur n'a pas saisi 1, 2 ou 3
				do{
					System.out.println("Veuillez saisir la taille du damier sur lequel vous souhaitez jouer (8, 10 ou 12)");
					saisie = scanner.nextLine();	
				}while(!saisie.equals("8") && !saisie.equals("10") && !saisie.equals("12"));
				
				partie = new Partie(Integer.parseInt(saisie));

				canContinue=true;
			}
			// Si le joueur a decide de charger une partie sauvegardee
			else if(saisie.equals("2")){
				partie = new Partie();
				try{
					partie.setPlateau(partie.chargerPlateau());
					canContinue = true;
				} 
				catch(Exception e){
					System.out.println("--------------------------------");
					System.out.println("Aucune partie n'est sauvegardée.");
					System.out.println("--------------------------------");
				}
			}
			// Si le joueur a decide de quitter le jeu
			else if(saisie.equals("3")){
				System.exit(0);
			}
		}

		Plateau plateau = partie.getPlateau();

		plateau.remplirPlateau();

		int gagnant = 0;

		Piece pieceTmp;

		// Tant qu'il n'y a pas de gagnant
		while(gagnant==0){
			System.out.println(plateau.toString());
			pieceTmp = choixPiece(plateau, scanner);
			jouerCoup(pieceTmp, plateau, scanner);
			plateau.updateStatus();

			gagnant=partie.getGagnant();
			// S'il n'y a pas encore de gagnant
			if(gagnant==0){
				partie.getPlateau().getOrdinateur().play();
				plateau.updateStatus();
			}
		}

		if(gagnant==1){
			System.out.println("Vous avez perdu.");
		}
		else{
			System.out.println("Vous avez gagné.");
		}


		
	}

	public static Piece choixPiece(Plateau plateau, Scanner scanner){
		Piece piece = null;
		int x;
		int y;
		boolean saisieOk = false;
		Coordonnee coordonnee = new Coordonnee(20,20);

		System.out.println("Quel pion désirez-vous jouer ?");

		do{
			saisieOk = false;

			coordonnee = saisieCoordonnee(scanner);

			piece = plateau.getPiece(coordonnee);

			if(!plateau.isValide(coordonnee)){
				System.out.println("La coordonnee saisie est invalide. (hors du plateau ou case non jouable)");
				saisieOk = false;
			}
			else if(piece==null){
				System.out.println("Veuillez selectionner une case contenant un pion.");
				saisieOk = false;
			}
			else if(piece.isIA()){
				System.out.println("Veuillez selectionner un de vos pions.");
				saisieOk = false;
			}
			else if(!piece.canMove()){
				System.out.println("Ce pion ne peut pas se deplacer.");
				saisieOk = false;
			}
			else{
				saisieOk = true;
			}

		} 
		while(saisieOk!=true);

		return piece;
	}

	public static void jouerCoup(Piece piece, Plateau plateau, Scanner scanner){
		ArrayList<Coordonnee> deplacementsPossibles;
		Coordonnee coordonnee = new Coordonnee(20,20);
		boolean canContinue = true;
		boolean saisieOk = false;
		boolean fini;
		int x=1;
		int y;
		Coup coup;
		int nbDeplacements = 0;
		int nbPrises = 0;
		System.out.println("Sur quelle case souhaitez-vous deplacer cette piece ?");
		int i;

		while(canContinue){

			do {
				deplacementsPossibles = piece.getDeplacements();
				//affichage des deplacements possibles
				System.out.println("deplacements possibles : ");
				for(i=0; i<deplacementsPossibles.size(); i++){
					System.out.print(deplacementsPossibles.get(i));
				}
				System.out.println("\n");

				coordonnee = saisieCoordonnee(scanner);

				if(!plateau.isValide(coordonnee)){
					System.out.println("La coordonnee saisie est invalide. (hors du plateau ou case non jouable)");
					saisieOk = false;
				}
				else{
					for(i=0; i<deplacementsPossibles.size(); i++){
						if(deplacementsPossibles.get(i).equals(coordonnee)){
							coup = new Coup(piece, coordonnee);
							// Si c'est le premier deplacement, alors saisie OK
							if(nbDeplacements==0){
								saisieOk = true;
							}
							// Sinon, il faut que le coup soit une prise et que le joueur n'a pas effectue de coup sans prise avant
							else if(!(coup.getPiecePrise()!=null && nbDeplacements==nbPrises)){
								System.out.println("Vous êtes obligés de prendre une piece adverse.");
							}
							else {
								saisieOk = true;
							}
						}
					}
				}
			}
			while(!saisieOk);

			coup = new Coup(piece, coordonnee);

			plateau.playAction(coup);
			nbDeplacements++;

			// Si le deplacement implique une prise
			if(coup.getPiecePrise()!=null){
				nbPrises++;
			}

			// Si le pion peut rejouer pour faire une prise
			if(nbDeplacements==nbPrises && piece.canTake()){
				canContinue=true;
			}
			else {
				canContinue = false;
			}
			System.out.println(plateau.toString());

		}

	}

	public static Coordonnee saisieCoordonnee(Scanner scanner){
		
		boolean saisieOk;
		Coordonnee coordonnee=null;
		int x;
		int y;
		do{
			scanner = new Scanner(System.in);
			try{
				System.out.print("x = ");
				x = scanner.nextInt();
				System.out.print("y = ");
				y = scanner.nextInt();
				coordonnee = new Coordonnee(x,y);
				saisieOk = true;
			}
			catch(InputMismatchException e){
				System.out.println("Veuillez saisir un entier.");
				saisieOk = false;
			}

		}while(!saisieOk);

		return coordonnee;

	}



}
