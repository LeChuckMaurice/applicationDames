package test.pda;

import pda.datas.datasDames.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestCoup {

	private Plateau plateau8;
	private Plateau plateau10;
	private Plateau plateau10Vide;
	private Plateau plateau12;

	private Piece[][] tabPieceVide10;

	private Coup coupGenerique10;

	@Before
	public void setUp() throws Exception {

		this.plateau8 = new Plateau(8);
		this.plateau10 = new Plateau(10);
		this.plateau12 = new Plateau(12);
		this.plateau10Vide = new Plateau(10);

		this.plateau8.remplirPlateau();
		this.plateau10.remplirPlateau();
		this.plateau12.remplirPlateau();

		this.tabPieceVide10 = this.plateau10Vide.getTabPiece();

		Piece piece = plateau10.getPiece(new Coordonnee(1,6));

		this.coupGenerique10 = new Coup(piece, new Coordonnee(2,5));
		
	}

	@Test
	public void testFindPiecePrise(){
		Dame dame1IA = new Dame(4,5,this.plateau10Vide,true);
		this.tabPieceVide10[4][5] = dame1IA;

		Pion pion1Joueur = new Pion(6,7,this.plateau10Vide,false);
		this.tabPieceVide10[6][7] = pion1Joueur;

		Coup coup1 = new Coup(dame1IA,new Coordonnee(8,9));
		assertEquals(pion1Joueur, coup1.findPiecePrise());

		Dame dame1Joueur = new Dame(6,3,this.plateau10Vide,false);
		this.tabPieceVide10[6][3] = dame1Joueur;

		Coup coup2 = new Coup(dame1IA,new Coordonnee(8,1));
		assertEquals(dame1Joueur, coup2.findPiecePrise());

		Coup coup3 = new Coup(dame1IA,new Coordonnee(2,3));
		assertEquals(null, coup3.findPiecePrise());

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

}
