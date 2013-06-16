package tests;

import datas.Coordonnee;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCoordonnee {
	
	private Coordonnee coordonnee;
	
	@Before 
	public void instancier () { 
		this.coordonnee = new Coordonnee(1,1);
	} 

	@Test
	public void testSetX() {
		assertEquals(coordonnee.getX(),1);
		coordonnee.setX(0);
		assertEquals(coordonnee.getX(),0);
	}

	@Test
	public void testSetY() {
		assertEquals(coordonnee.getY(),1);
		coordonnee.setY(0);
		assertEquals(coordonnee.getY(),0);
	}

	@Test
	public void testGetX() {
		assertEquals(coordonnee.getX(),1);
	}

	@Test
	public void testGetY() {
		assertEquals(coordonnee.getY(),1);
	}

	@Test
	public void testEquals(){
		Coordonnee coordTrue = new Coordonnee(1,1);
		Coordonnee coordFalse = new Coordonnee(2,1);
		Coordonnee coordNull = null;

		assertTrue(this.coordonnee.equals(coordTrue));
		assertFalse(this.coordonnee.equals(coordFalse));
		assertFalse(this.coordonnee.equals(coordNull));
	}

}
