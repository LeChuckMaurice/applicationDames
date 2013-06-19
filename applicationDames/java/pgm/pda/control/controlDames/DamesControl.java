package pda.control.controlDames;

import pda.view.viewDames.*;
import javax.swing.JPanel;
import pda.control.PdaCtrl;
import pda.datas.datasDames.Piece;
import pda.datas.datasDames.Plateau;


/**
*Cette classe centralise les réactions de l'application Dames et effectue les modification en conséquence sur les parties view et datas.
*
*/
public class DamesControl implements Globale, pda.control.IApplication{
	
	private Piece pieceSelect = null;
	private boolean inDoubleCoup=false;
	private String name;
	
	public DamesControl(){

	attacherReactionsMenu();
	setAppliName("Dames");

	}

	/**
	*Attache les réactions au éléments du menu
	*/
	public void attacherReactionsMenu(){

		(Globale.theView).getNew().addActionListener(new ReactionAction(this));
		(Globale.theView).getCharger().addActionListener(new ReactionAction(this));
		(Globale.theView).getQuitter().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogFin().getMenu().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogCharge().getCharge().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogCharge().getAnnuler().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogSave().getSave().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogSave().getAnnuler().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogQuit().getSave().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogQuit().getNoSave().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogQuit().getAnnuler().addActionListener(new ReactionAction(this));
		 
		(Globale.theView).getDialogTaille().getAnnuler().addActionListener(new ReactionAction(this));
		(Globale.theView).getDialogTaille().getConfirmer().addActionListener(new ReactionAction(this));

		(Globale.theView).getDialogErreur().getOk().addActionListener(new ReactionAction(this));
		
	}

	/**
	*Attache les réactions au éléments de l'interace de jeu
	*/
	public void attacherReactionsPlateau(){

		(Globale.theView).getExit().addMouseListener(new ReactionClick(this));
		(Globale.theView).getHelp().addMouseListener(new ReactionClick(this));
		(Globale.theView).getSave().addMouseListener(new ReactionClick(this));
		
		int taillePlateau=theView.getTaillePlateau();

		for (int y=0;y<taillePlateau ;y++ ) {
			
			for (int x=0;x<taillePlateau ;x++ ) {
				theView.getCase(x,y).addMouseListener(new ReactionClick(this));
			}

		}
	}	
	
	/**
	*Attache l'es réactions au éléments de l'aide
	*/
	public void attacherReactionsAide(){
		(Globale.theView).getExitAide().addMouseListener(new ReactionClick(this));
	}

	/**
	*Mets à jour le plateau graphique en fonction de l'état du plateau datas
	*On parcours toute les cases noire que on modifie en fonctions de la pièce (ou l'absence ) présente au même coordonnées dans le plateau
	*/
	public void updateView(){
		Plateau thePlat=(Globale.thePart).getPlateau();
		int taillePlateau=thePlat.getTaille();

		if (thePlat!=null) {
			for (int y=0;y<taillePlateau;y++) {
				
				for (int x=(y+1)%2; x<taillePlateau; x=x+2){ // x
				
					Piece piece = thePlat.getPiece(x,y);
					Case laCase = theView.getCase(x,y);

					if (piece==null) {
						laCase.setCaseNoire();
					}
					else if (piece.isIA()) {
						
						if (piece.isDame()) {
							laCase.setDameNoir();
						}
						else{
							laCase.setPionNoir();
						}

					}
					else if (!(piece.isIA())) {
						
						if (piece.isDame()) {
							if (piece.canMove()) {
								laCase.setDameBlancSurl();
							}
							else{
								laCase.setDameBlanc();
							}
						}
						else{
							if (piece.canMove()) {
								laCase.setPionBlancSurl();
							}
							else{
								laCase.setPionBlanc();
							}
						}
						
					}
				}

			}
		}

	}

	/**
	*Effectue un tour de jeu pour l'IA
	*@see Plateau
	*/
	public void coupIA(){
		
		
		(Globale.thePart).getPlateau().getOrdinateur().play();
    	
    	
		(Globale.thePart).getPlateau().updateStatus();
		this.updateView();
		(Globale.thePart).setTourIA(false);
		isFin();
	}

	/**
	*Verifie si la partie et fini et le cas échéant affiche la boite dialogFin correspondante
	*/
	public void isFin(){
		int isGagnant = (Globale.thePart).getGagnant();
		

		if (isGagnant==1) {
			(Globale.theView).getDialogFin().styleLabel(false);
			(Globale.theView).init((Globale.theView).getDialogFin());
			(Globale.theView).getDialogFin().setVisible(true);
		}
		if (isGagnant==(-1)) {
			(Globale.theView).getDialogFin().styleLabel(true);
			(Globale.theView).init((Globale.theView).getDialogFin());
			(Globale.theView).getDialogFin().setVisible(true);
		}
	}

	/**
	*Charge le plateau de datas
	*/
	public void charger() throws Exception{
		(Globale.thePart).setPlateau(thePart.chargerPlateau());
		
	}
	
	/**
	*Sauvegarde le plateau
	*/
	public void sauver(){
		thePart.savePlateau();
	}
	
	/**
	*Initialise le plateau de la partie avec un nouveau plateau
	*@param taille La taille du nouveau plateau ( d'un coté )
	*/
	public void createThePlat(int taille){
		(Globale.thePart).setPlateau(new Plateau(taille));
	}

	/**
	*Attribut à pieceSelect la pièce passé en paramêtre, cette methode est utilisé pour sauvegarder une piece lorsque on joue un coup graphiquement
	*@param laPiece piece a selectionné
	*/
	public void setPieceSelect(Piece laPiece){
		this.pieceSelect=laPiece;
	}

	/**
	*@return pieceSelect Renvoie la piece selectionnée
	*/
	public Piece getPieceSelect(){
		return this.pieceSelect;
	}

	/**
	*Passe le booleen à vrai pour indique que le coup actuellement jouer est un double coup, false pour signaler que on sort du double coup
	*Double coup designe un coup à 2 (ou plus) prises
	*/
	public void setDoubleCoup(boolean status){
		this.inDoubleCoup=status;
	}

	/**
	*@return inDoubleCoup true si le coup actuel à plus d'une prise, false sinon
	*/
	public boolean getInDoubleCoup(){
		return this.inDoubleCoup;
	}


	//Methodes de l'interface IApplication

	/**
	*Affiche un message signalant le lancement de l'application
	*/
	public void start(PdaCtrl pda){
		System.out.println("Lancement du jeu de Dames");
	}

	/**
	*@return name Le nom de l'application
	*/
	public String getAppliName(){
		return name;

	}

	/**
	*@return panel Le panel global de la vue
	*/
	public JPanel getAppliPanel(){
		JPanel panel=(Globale.theView).getPanel();

		return panel;
	}

	public boolean close(){
		return true;
	}

	/**
	*Change le nom de l'application pour celui passé en parametre
	*@param theName Le nouveau nom de l'application
	*/
	public void setAppliName ( String theName ){
		this.name=theName;
	}




}
