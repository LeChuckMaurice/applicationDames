package tests;

import datas.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datas.Plateau;

public class TestPion {

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
		Piece pieceFalse = this.plateau10.getPiece(1,0);
		Piece pieceTrue = this.plateau10.getPiece(0,3);
		
		System.out.println(this.plateau10.toString()+"------");
		assertFalse(pieceFalse.canMove());
		assertTrue(pieceTrue.canMove());

		// test avec pion qui peut prendre (mais ne peut pas bouger sans prendre)
		Piece piecePrise = this.plateau10.getPiece(1,6);
		this.plateau10.movePiece(piecePrise,1,4);
		assertTrue(pieceTrue.canMove());

	}

	@Test
	public void testCanTake() {
		this.viderPlateau(plateauVide10);
		// pion principal
		Piece pion1 = new Pion(4,5,this.plateauVide10,true);
		this.tabPieceVide10[4][5] = pion1;

		Piece pionJoueur1 = new Pion(3,4,this.plateauVide10,false);
		this.tabPieceVide10[3][4] = pionJoueur1;

		Piece pionJoueur2 = new Pion(5,4,this.plateauVide10,false);
		this.tabPieceVide10[5][4] = pionJoueur2;

		Piece pionIA1 = new Pion(3,6,this.plateauVide10,true);
		this.tabPieceVide10[3][6] = pionIA1;

		Piece pionIA2 = new Pion(5,6,this.plateauVide10,true);
		this.tabPieceVide10[5][6] = pionIA2;

		System.out.println(this.plateauVide10.toString());
		// test prise en avant
		assertTrue(pion1.canTake());

		// tests prise avec pion la piece a abattre  (donc pas possible)
		assertFalse(pionJoueur1.canTake());
		assertFalse(pionJoueur2.canTake());
		assertFalse(pionIA1.canTake());
		assertFalse(pionIA2.canTake());

		this.plateauVide10.deletePiece(pionJoueur2);
		this.plateauVide10.movePiece(pionIA2,5,4);

		// test prise en arriere
		assertTrue(pion1.canTake());


		System.out.println(this.plateauVide10.toString());
	}

	private void viderPlateau(Plateau plateau){
		Piece[][] tabPiece = plateau.getTabPiece();
		for(int i=0; i<plateau.getTaille()-1; i++){
			for(int j=0; j<plateau.getTaille()-1; j++){
				tabPiece[i][j] = null;
			}
		}
	}

}
