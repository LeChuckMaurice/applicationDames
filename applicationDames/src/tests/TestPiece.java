package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datas.*;

public class TestPiece {

	private Plateau plateau10;

	private Plateau plateauVide10;
	private Piece[][] tabPieceVide10;

	private Pion pionIA;
	private Dame dameIA;

	private Pion pionJoueur;
	private Dame dameJoueur;

	@Before
	public void setUp() throws Exception {
		this.plateau10 = new Plateau(10);

		this.plateauVide10 = new Plateau(10);
		
		this.plateau10.remplirPlateau();

		this.tabPieceVide10 = this.plateauVide10.getTabPiece();

		this.pionIA = new Pion(4,5,this.plateauVide10,true);
		this.tabPieceVide10[4][5] = this.pionIA;

		this.dameIA = new Dame(1,0,this.plateauVide10,true);
		this.tabPieceVide10[1][0] = this.dameIA;

		this.pionJoueur = new Pion(6,7,this.plateauVide10,false);
		this.tabPieceVide10[6][7] = this.pionJoueur;

		this.dameJoueur = new Dame(2,9,this.plateauVide10,false);
		this.tabPieceVide10[7][0] = this.dameJoueur;

	}

	@Test
	public void testIsVulnerable() {
		System.out.println(this.plateauVide10.toString());
		assertFalse(this.pionIA.isVulnerable());

		// pion peut prendre
		this.plateauVide10.movePiece(pionJoueur,5,6);

		System.out.println(this.plateauVide10.toString());
		assertTrue(this.pionIA.isVulnerable());

		// dameIA ne peut pas prendre car bloquée par pionIA
		this.plateauVide10.movePiece(dameJoueur,3,4);

		System.out.println(this.plateauVide10.toString());
		assertFalse(this.pionIA.isVulnerable());


	}



}