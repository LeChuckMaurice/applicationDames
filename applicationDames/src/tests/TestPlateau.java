package tests;

import datas.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import datas.Plateau;

public class TestPlateau {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testPlateau() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemplirPlateau() {
		Plateau plateau = new Plateau(8);
		plateau.remplirPlateau();
		System.out.println(plateau.toString());		
	}

	@Test
	public void testIsValide() {
		fail("Not yet implemented");
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
	public void testGetTaille() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTabPiece() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPiece() {
		fail("Not yet implemented");
	}

}
