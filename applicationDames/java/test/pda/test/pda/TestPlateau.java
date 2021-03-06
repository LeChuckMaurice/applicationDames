package test.pda;

import pda.datas.datasDames.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestPlateau {

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
	public void testRemplirPlateau() {

		Plateau plateau8 = new Plateau(8);
		plateau8.remplirPlateau();
		System.out.println("test remplirPlateau() taille 8");
		System.out.println(plateau8.toString());

		Plateau plateau10 = new Plateau(10);
		plateau10.remplirPlateau();
		System.out.println("test remplirPlateau() taille 10");
		System.out.println(plateau10.toString());	

		Plateau plateau12 = new Plateau(12);
		plateau12.remplirPlateau();
		System.out.println("test remplirPlateau() taille 12");
		System.out.println(plateau12.toString());	
	}

	@Test
	public void testIsValide() {
		Coordonnee coord1 = new Coordonnee(10,10); // case en dehors du plateau
		Coordonnee coord2 = new Coordonnee(9,9); // case non jouable
		Coordonnee coord3 = new Coordonnee(1,10); // case en dehors du plateau
		Coordonnee coord4 = new Coordonnee(9,8); // case jouable

		assertFalse(this.plateau10.isValide(coord1));
		assertFalse(this.plateau10.isValide(coord2));
		assertFalse(this.plateau10.isValide(coord3));
		assertTrue(this.plateau10.isValide(coord4));
	}

	@Test
	public void testMovePiece() {
		System.out.println("__________plateau 10 avant_________________\n");
		System.out.println(plateauVide10.toString());

		// Deplacement sur case libre
		try{
			Coordonnee coordTest = new Coordonnee(2,7);
			// deplacement dans une case libre et vide
			this.plateauVide10.movePiece(this.pionJoueur, coordTest);
			assertEquals(pionJoueur.getCoordonnee().getX(), coordTest.getX());
			assertEquals(pionJoueur.getCoordonnee().getY(), coordTest.getY());
			assertEquals(this.tabPieceVide10[2][7], this.pionJoueur);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			fail("testMovePiece1 echoue");
		}

		// Deplacement sur case invalide (non comprises dans le plateau du tableau)
		try{
			Coordonnee coordTest2 = new Coordonnee(15,20);
			// deplacement dans une case invalide
			this.plateauVide10.movePiece(this.pionJoueur, coordTest2);
			fail("testMovePiece2 echoue");
		}
		catch(IllegalArgumentException e){
			System.out.println("testMovePiece2 OK : exception capturée");	
		}

		// Deplacement sur case occupee
		try{
			Coordonnee coordTest3 = new Coordonnee(0,7);
			// deplacement dans une case occupee
			this.plateauVide10.movePiece(this.pionJoueur, coordTest3);
			fail("testMovePiece3 echoue");
		}
		catch(IllegalArgumentException e){
			System.out.println("testMovePiece3 OK : exception capturée");	
		}


		System.out.println("__________plateau 10 apres_________________\n");
		System.out.println(plateauVide10.toString());

	}

	@Test
	public void testUpdateStatus() {
		// placement du pion joueur sur la derniere ligne adverse
		Pion pionJoueur = new Pion(5,0,this.plateauVide10,false);
		this.tabPieceVide10[5][0]=pionJoueur;
		assertEquals(pionJoueur.isDame(), false);

		// placement du pion IA sur la derniere ligne adverse
		Pion pionIA = new Pion(6,9,this.plateauVide10,true);
		this.tabPieceVide10[6][9]=pionIA;

		assertEquals(pionIA.isDame(), false);

		// mise a jour des statuts des pieces
		this.plateauVide10.updateStatus();
		System.out.println(plateauVide10.toString());
		// verification si les deux pions places sur la derniere ligne adverse
		// ont bien ete transformes en Dame
		Piece pieceJoueur2 = this.plateauVide10.getPiece(5,0);
		Piece pieceIA2 = this.plateauVide10.getPiece(6,9);
		assertEquals(true, pieceJoueur2.isDame());
		assertEquals(true, pieceIA2.isDame());

	}


	@Test
	public void testToString() {
		System.out.println("Test méthode toString() :\n");
		System.out.println("Plateau 8\n"+this.plateau8.toString());
		System.out.println("Plateau 10\n"+this.plateau10.toString());
		System.out.println("Plateau 12\n"+this.plateau12.toString());
	}

	@Test
	public void testGetPiece() {
		Piece[][] tabPiece = this.plateau10.getTabPiece();
		Piece piece = tabPiece[0][1];

		// cas d'une case occupée par une piece
		assertEquals(this.plateau10.getPiece(new Coordonnee(0,1)),piece);
		// cas d'une case vide (doit renvoyer null)
		assertEquals(this.plateau10.getPiece(new Coordonnee(0,0)),null);
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
