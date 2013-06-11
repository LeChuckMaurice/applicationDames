package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datas.Coordonnee;
import datas.Coup;
import datas.Piece;
import datas.Plateau;

public class TestCoup {

	private Plateau plateau8;
	private Plateau plateau10;
	private Plateau plateau12;

	private Coup coupGenerique10;

	@Before
	public void setUp() throws Exception {

		this.plateau8 = new Plateau(8);
		this.plateau10 = new Plateau(10);
		this.plateau12 = new Plateau(12);

		this.plateau8.remplirPlateau();
		this.plateau10.remplirPlateau();
		this.plateau12.remplirPlateau();

		ArrayList listeCoord = new ArrayList<Coordonnee>();
		listeCoord.add(new Coordonnee(1,6));
		listeCoord.add(new Coordonnee(2,5));

		Piece piece = null;
		
		try{
			piece = plateau10.getPiece(new Coordonnee(1,6));
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		
		this.coupGenerique10 = new Coup(0,listeCoord,piece);
	}

	@Test
	public void testCalculerPoints() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAuthorized() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNbPoints() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNbPrises() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParcours() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDepart() {
		Coordonnee depart = this.coupGenerique10.getDepart();
		assertEquals(depart.getX(),1);
		assertEquals(depart.getY(),6);
	}

	@Test
	public void testGetArrivee() {
		Coordonnee arrivee = this.coupGenerique10.getArrivee();
		assertEquals(arrivee.getX(),2);
		assertEquals(arrivee.getY(),5);
	}

	@Test
	public void testGetPiece() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPiecesPrises() {
				fail("Not yet implemented");
	}

	@Test
	public void testSetNbPrises() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetParcours() {
		fail("Not yet implemented");
	}

}
