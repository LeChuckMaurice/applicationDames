package tests;

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
		fail("Not yet implemented");
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

		System.out.println(this.plateauVide10.toString());

		assertFalse(dame1Joueur.canMove());

		this.plateauVide10.movePiece(pionGenantJoueur,3,6);

		System.out.println(this.plateauVide10.toString());

		assertTrue(dame1Joueur.canMove());

		this.plateauVide10.movePiece(pionIA1,1,0);
		
		assertTrue(dame1Joueur.canMove());
		

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
