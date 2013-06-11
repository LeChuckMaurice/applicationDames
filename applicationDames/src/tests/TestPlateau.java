package tests;

import datas.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datas.Plateau;

public class TestPlateau {

	private Plateau plateau8;
	private Plateau plateau10;
	private Plateau plateau12;
	
	@Before
	public void setUp() throws Exception {
		this.plateau8 = new Plateau(8);
		this.plateau10 = new Plateau(10);
		this.plateau12 = new Plateau(12);
		
		this.plateau8.remplirPlateau();
		this.plateau10.remplirPlateau();
		this.plateau12.remplirPlateau();
	}

	@Test
	public void testPlateau() {
		fail("Not yet implemented");
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
		Coordonnee coord4 = new Coordonnee(1,0); // case jouable

		assertFalse(this.plateau10.isValide(coord1));
		assertFalse(this.plateau10.isValide(coord2));
		assertFalse(this.plateau10.isValide(coord3));
		assertTrue(this.plateau10.isValide(coord4));
	}

	@Test
	public void testPlayAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSavePlateau() {
		fail("Not yet implemented");
	}

	@Test
	public void testChargerPlateau() {
		fail("Not yet implemented");
	}


	@Test
	public void testGetPiece() {
		Piece[][] tabPiece = this.plateau10.getTabPiece();
		Piece piece = tabPiece[0][1];
		Piece piece2 = null;
		try{
			assertEquals(this.plateau10.getPiece(new Coordonnee(0,1)),piece);
		}
		catch(NullPointerException e){
			fail();
		}

		try{
			piece2 = this.plateau10.getPiece(new Coordonnee(0,0));
			fail();
		}
		catch(NullPointerException e){}

	}

}
