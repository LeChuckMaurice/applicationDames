package tests;

import java.util.ArrayList;

import datas.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datas.Plateau;


public class TestDame {
	private Plateau plateau8;
	private Plateau plateau10;
	private Plateau plateau12;

	private Plateau plateauVide10;
	private Piece[][] tabPieceVide10;

	private Pion pionIA;
	private Dame dameIA;

	private Pion pionJoueur;
	private Dame dameJoueur;


	@Before
	public void setUp() throws Exception {
		this.plateau8 = new Plateau(8);
		this.plateau10 = new Plateau(10);
		this.plateau12 = new Plateau(12);

		this.plateauVide10 = new Plateau(10);
		
		this.plateau8.remplirPlateau();
		this.plateau10.remplirPlateau();
		this.plateau12.remplirPlateau();

		this.tabPieceVide10 = this.plateauVide10.getTabPiece();

		this.pionIA = new Pion(0,7,this.plateauVide10,true);
		this.tabPieceVide10[0][7] = this.pionIA;

		this.dameIA = new Dame(3,0,this.plateauVide10,true);
		this.tabPieceVide10[3][0] = this.dameIA;

		this.pionJoueur = new Pion(1,8,this.plateauVide10,false);
		this.tabPieceVide10[1][8] = this.pionJoueur;

		this.dameJoueur = new Dame(2,9,this.plateauVide10,false);
		this.tabPieceVide10[2][9] = this.dameJoueur;

	}

	@After
	public void tearDown() {
		// vidage des tableaux apres chaque test
		viderPlateau(plateau8);
		viderPlateau(plateau10);
		viderPlateau(plateau12);
		viderPlateau(plateauVide10);
	}


	@Test
	public void testGenerateCoups() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDeplacements() {
		viderPlateau(plateauVide10);

		Dame dame1 = new Dame(4,5,this.plateauVide10,false);
		this.tabPieceVide10[4][5] = dame1;

		ArrayList<Coordonnee> deplacements = dame1.getDeplacements();
		
		assertEquals(deplacements.size(), 17);

		
		System.out.println("\ntestGetDeplacements() 1 : dame libre (17 deplacements possibles).");
		for(int i=0; i<deplacements.size(); i++){
			System.out.print((i+1)+" : "+deplacements.get(i).toString()+", ");
		}
		System.out.print("\n");

		// avec pion (plus que  3 déplacements possibles)
		Pion pion2 = new Pion(5,6,this.plateauVide10,true);
		this.tabPieceVide10[5][6] = pion2;

		ArrayList<Coordonnee> deplacements2 = dame1.getDeplacements();
		assertEquals(deplacements2.size(), 3);
		
		System.out.println("\ntestGetDeplacements() 2 : dame avec un pion adverse a abbattre)");
		System.out.println("Resultat attendu : (6,7) (7,8) (8,9)");
		System.out.print("Resultat : ");
		for(int i=0; i<deplacements2.size(); i++){
			System.out.print(deplacements2.get(i).toString()+", ");
		}	

		// avec 2 pions (3 + 1 = 4 deplacements possibles) + 1 pion genant du même camp
		Pion pion3 = new Pion(6,3,this.plateauVide10,true);
		this.tabPieceVide10[6][3] = pion3;

		Pion pion4 = new Pion(8,1,this.plateauVide10,false);
		this.tabPieceVide10[8][1] = pion4;

		ArrayList<Coordonnee> deplacements3 = dame1.getDeplacements();

		System.out.println("\ntestGetDeplacements() 3 : dame avec 2 pion adverses a abbattre et un pion genant du même camp");
		System.out.println("Resultat attendu : (6,7) (7,8) et (8,9), et (7,2)");
		System.out.print("Resultat : ");
		for(int i=0; i<deplacements3.size(); i++){
			System.out.print(deplacements3.get(i).toString()+", ");
		}	

		assertEquals(deplacements3.size(), 4);


	}

	@Test
	public void testCanMove() {
		this.viderPlateau(plateauVide10);
		// dame principale
		Dame dame1Joueur = new Dame(0,9,this.plateauVide10,false);
		this.tabPieceVide10[0][9] = dame1Joueur;

		Pion pionIA1 = new Pion(1,8,this.plateauVide10,true);
		this.tabPieceVide10[1][8] = pionIA1;

		Pion pionGenantJoueur = new Pion(2,7,this.plateauVide10,false);
		this.tabPieceVide10[2][7] = pionGenantJoueur;

		assertFalse(dame1Joueur.canMove());

		this.plateauVide10.movePiece(pionGenantJoueur,3,6);

		assertTrue(dame1Joueur.canMove());

		this.plateauVide10.movePiece(pionIA1,1,0);
		
		assertTrue(dame1Joueur.canMove());
		

	}

	@Test
	public void testPrisesPossibles() {
		this.viderPlateau(plateauVide10);
		// dame principale
		Dame dame1Joueur = new Dame(4,5,this.plateauVide10,false);
		this.tabPieceVide10[4][5] = dame1Joueur;

		Pion pionIA1 = new Pion(3,4,this.plateauVide10,true);
		this.tabPieceVide10[3][4] = pionIA1;
		
		Pion pionIA2 = new Pion(7,2,this.plateauVide10,true);
		this.tabPieceVide10[7][2] = pionIA2;
		
		Pion pionIA3 = new Pion(3,6,this.plateauVide10,true);
		this.tabPieceVide10[3][6] = pionIA3;

		Pion pionGenantJoueur = new Pion(5,6,this.plateauVide10,false);
		this.tabPieceVide10[5][6] = pionGenantJoueur;

		ArrayList<Piece> pieces = dame1Joueur.prisesPossibles(dame1Joueur.getCoordonnee(), dame1Joueur.isIA());
		
		System.out.println("\ntestPrisesPossibles() : resultats attendus (3,4) (3,6) et (7,2)");
		for(int i = 0; i<pieces.size(); i++){
			System.out.println(pieces.get(i).getCoordonnee().toString());
		}

	}


	@Test
	public void testCanTake() {
		this.viderPlateau(plateauVide10);
		// dame principale
		Dame dame1Joueur = new Dame(4,5,this.plateauVide10,false);
		this.tabPieceVide10[4][5] = dame1Joueur;

		Pion pionIA1 = new Pion(3,4,this.plateauVide10,true);
		this.tabPieceVide10[3][4] = pionIA1;

		Pion pionGenantJoueur = new Pion(2,3,this.plateauVide10,false);
		this.tabPieceVide10[2][3] = pionGenantJoueur;


		assertFalse(dame1Joueur.canTake());

		this.plateauVide10.movePiece(pionGenantJoueur,1,0);

		assertTrue(dame1Joueur.canTake());

		// pion a abattre dans la diagonale a 2 cases de distance
		this.plateauVide10.movePiece(pionIA1,2,3);
		assertTrue(dame1Joueur.canTake());

		// pion a abattre en bout de diagonale
		this.plateauVide10.movePiece(pionIA1,0,1);
		assertFalse(dame1Joueur.canTake());

		// pion a abattre dans la diagonale a 3 cases de distance
		// avec case d'apres occupee
		this.plateauVide10.movePiece(pionIA1,1,2);
		this.plateauVide10.movePiece(pionGenantJoueur,0,1);
		assertFalse(dame1Joueur.canTake());

	}

	private void viderPlateau(Plateau plateau){
		Piece[][] tabPiece = plateau.getTabPiece();
		for(int i=0; i<plateau.getTaille(); i++){
			for(int j=0; j<plateau.getTaille(); j++){
				tabPiece[i][j] = null;
			}
		}
	}

}
