package pda.view.viewDames;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

/**
*Cette classe regroupe tout les éléments de la vue de l'application Dames
*Elle est construite autour d'un JPanel principal (pGlobal) dont le contenu change selon la vue affichée (Menu,jeu ou aide)
*Cette classe construit aussi toute les fenetres de dialogue de l'application
*
*/

public class ViewDames{
	
	//Attributs globaux
	private JPanel pGlobal;

	//Attributs pour le menu
	private JPanel pLogo;
	private JLabel lLogo;

	private JButton bNew;
	private JPanel pNew;
	private JButton bCharger;
	private JPanel pCharger;
	private JButton bQuitter;
	private JPanel pQuitter;

	//Attributs pour l'interface d'aide

	private JEditorPane epAide;
	private JScrollPane spAide;
	private JPanel pAide;
	private JLabel bExitAide;


	//Attributs pour interface de jeu
	private JBackgroundPanel bpPrincipal;
	private JPanel pMenu;
	private JPanel pGame;
	private JPanel pMenuLeft;
	private JPanel pMenuRight;
	private JPanel pPlateau;

	private JLabel bExit;
	private JLabel bHelp;
	private JLabel bSave;

	private int taillePlateau;

	private Case[][] tabCase;

	//Boites de dialogues

	private DialogFin dialogFin;
	private DialogSaveCharge dialogSave;
	private DialogSaveCharge dialogCharge;
	private DialogQuitter dialogQuit;
	private DialogTaille dialogTaille;
	private DialogErreur dialogErreur;

	/**
	*Le constructeur cree et initialise la taille du JPanel principal pGlobal
	*Il cree aussi des instance de toute les boites de dialogue
	*Puis lance la création de l'interface du menu
	*/
	public ViewDames(){

		pGlobal = new JPanel();
		pGlobal.setLayout(new GridLayout(1,1));
		pGlobal.setBackground(Color.black);
		pGlobal.setPreferredSize(new Dimension(320,325));
		pGlobal.setSize(320,325);

		dialogFin = new DialogFin();
		init(dialogFin);
		dialogSave = new DialogSaveCharge(false);
		init(dialogSave);
		dialogCharge = new DialogSaveCharge(true);
		init(dialogCharge);
		dialogQuit = new DialogQuitter();
		init(dialogQuit);
		dialogTaille = new DialogTaille();
		init(dialogTaille);
		dialogErreur = new DialogErreur("");
		init(dialogErreur);

		this.creerInterfaceMenu();
		
		

	}

	/**
	*Cree l'interface de jeu
	*Jeu:
	*L'interface de jeu ce compose de 3 JLabel en haut à droite pour la sauvegarde, l'aide et l'arret de la partie
	*Mais aussi d'un plateau carre de x Cases de cotés, ou x est la taille du plateau prealablement choisit
	*@see Case
	*@param laTaille La taille du tableau 2D de Cases et donc du plateau
	*/
	public void creerInterfaceJeu(int laTaille){

		pGlobal.removeAll();
		pGlobal.updateUI();
		this.taillePlateau=laTaille;

		bpPrincipal = new JBackgroundPanel();
		pGlobal.add(bpPrincipal);
		bpPrincipal.setLayout(new BorderLayout());

		// Boutons superieur gauche

		pMenu = new JPanel();
		pMenu.setOpaque(false);
		bpPrincipal.add(pMenu,BorderLayout.NORTH);
		pMenu.setLayout(new GridLayout(1,2));

		pMenuLeft = new JPanel();
		pMenuLeft.setOpaque(false);
		pMenuLeft.setBackground(new Color(0,0,0,0));
		pMenu.add(pMenuLeft);
		pMenuRight = new JPanel();
		pMenuRight.setOpaque(false);
		pMenu.add(pMenuRight);

		pMenuRight.setLayout(new GridLayout(1,3));

		ImageIcon iSave=new ImageIcon("data/img/save.png");
		bSave = new JLabel();
		bSave.setIcon(iSave);
		bSave.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bSave);
		
		ImageIcon iHelp=new ImageIcon("data/img/help.png");
		bHelp = new JLabel();
		bHelp.setIcon(iHelp);
		bHelp.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bHelp);

		ImageIcon iExit=new ImageIcon("data/img/exit.png");
		bExit = new JLabel();
		bExit.setIcon(iExit);
		bExit.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bExit);

		tabCase = new Case[taillePlateau][taillePlateau];
		
		initPlateau();

	}

	/**
	*Cree l'interface du menu principal/Acceuil 
	*Menu :
	*Le menu principal ce compose d'un logo et 3 boutons
	*/
	public void creerInterfaceMenu(){
		
		pGlobal.removeAll();
		pGlobal.updateUI();

		bpPrincipal = new JBackgroundPanel();
		pGlobal.add(bpPrincipal);
		bpPrincipal.setLayout(new GridLayout(4,1));

		pLogo = new JPanel();
		pLogo.setOpaque (false);
		bpPrincipal.add(pLogo);
		lLogo = new JLabel();
		ImageIcon logoDame=new ImageIcon("data/img/logo.png");
		lLogo.setIcon(logoDame);
		pLogo.add(lLogo);

		pNew = new JPanel();
		pNew.setOpaque (false);
		bpPrincipal.add(pNew);
		bNew = new JButton("Nouvelle partie");
		setStyleButton(bNew);
		pNew.add(bNew);

		pCharger = new JPanel();
		pCharger.setOpaque (false);
		bpPrincipal.add(pCharger);
		bCharger = new JButton("Charger une partie");
		setStyleButton(bCharger);
		pCharger.add(bCharger);

		pQuitter = new JPanel();
		pQuitter.setOpaque (false);
		bpPrincipal.add(pQuitter);
		bQuitter = new JButton("Quitter");
		setStyleButton(bQuitter);
		pQuitter.add(bQuitter);
	}

	/**
	*Cree l'interface d'aide
	*Aide:
	*L'interface d'aide ce compose d'un bouton dans le coin superieur droit pour revenir à la partie précédente
	*Et d'un JScrollPane contenant le texte recuperer du fichier "aide.html" placé dans le dossier data
	*/
	public void creerInterfaceAide(){
		
		pGlobal.removeAll();
		pGlobal.updateUI();
		
		bpPrincipal = new JBackgroundPanel();
		bpPrincipal.setLayout(new BorderLayout());

		pGlobal.add(bpPrincipal);
		// Bouton superieur gauche

		pMenu = new JPanel();
		pMenu.setOpaque(false);
		bpPrincipal.add(pMenu,BorderLayout.NORTH);
		pMenu.setLayout(new GridLayout(1,2));

		pMenuLeft = new JPanel();
		pMenuLeft.setOpaque(false);
		pMenuLeft.setBackground(new Color(0,0,0,0));
		pMenu.add(pMenuLeft);
		pMenuRight = new JPanel();
		pMenuRight.setOpaque(false);
		pMenu.add(pMenuRight);

		pMenuRight.setLayout(new GridLayout(1,3));

		bSave = new JLabel();
		bSave.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bSave);
		
		bHelp = new JLabel();
		bHelp.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bHelp);

		ImageIcon iExit=new ImageIcon("data/img/exit.png");
		bExitAide = new JLabel();
		bExitAide.setIcon(iExit);
		bExitAide.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bExitAide);
		pAide = new JPanel();
		pAide.setLayout(new BorderLayout());
		pAide.setOpaque(false);
		bpPrincipal.add(pAide);
		
		
		JEditorPane epAide = new JEditorPane("text/html",null);
		epAide.setOpaque(false);
			
		URL u=null;
		try {
			u = new File("data/aide.html").toURI().toURL();
			
			try {
				epAide.setPage(u);
			} catch (IOException e) {
				e.printStackTrace();
				epAide.setText("Fichier d'aide introuvable");
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			epAide.setText("Fichier d'aide introuvable");
		}
		        	
		spAide = new JScrollPane(epAide);
		spAide.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spAide.setPreferredSize(new Dimension(320,325));
		spAide.setOpaque(false);
		spAide.getViewport().setOpaque(false);
		pAide.add(spAide,BorderLayout.CENTER);
			
			

	}

	/**
	*Cet methode change le look du bouton passé en paramètre
	*Elle est utilisé pour unifier les boutons du menu principal
	*/
	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(240,58));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.PLAIN, 22));
		button.setBorderPainted(false);
	}

	/**
	*Cette methode crée le plateau servant de support au jeu
	*Le plateau est un JPanel divise par un GridLayout de nb lignes et nb colonnes = taille du tableau
	*On place dans chaque "case" du GridLayout un objet de type case avec un fond noir ou blanc
	*/
	public void initPlateau(){

		// Plateau

		pGame = new JPanel();
		pGame.setLayout(new FlowLayout(1));
		pGame.setOpaque(false);
		bpPrincipal.add(pGame,BorderLayout.CENTER);
		
		pPlateau = new JPanel();
		pPlateau.setBackground(Color.black);
		pPlateau.setPreferredSize(new Dimension(240,240));
		pGame.add(pPlateau);

		pPlateau.setLayout(new GridLayout(taillePlateau,taillePlateau));
		
		//Boucle d'ajout des cases
		for (int y=0;y<taillePlateau ;y++ ) {
			
			for (int x=0;x<taillePlateau ;x++ ) {
				if ((x+y)%2 ==0) {
					
					Case theCase = new Case(x,y,taillePlateau);
					
					theCase.setCaseBlanche();
					
					tabCase[x][y]=theCase;

					pPlateau.add(tabCase[x][y]);

				}
				else{
					Case theCase = new Case(x,y,taillePlateau);

					theCase.setCaseNoire();

					tabCase[x][y]=theCase;

					pPlateau.add(tabCase[x][y]);

				}
			}

		}
	}

	public void remplirPlateau() {
		int x = 0;
		int y = 0;
		

		// remplissage camp joueur
		for(y=0; y<(this.taillePlateau)/2-1; y++){ // y
			for (x=(y+1)%2; x<this.taillePlateau; x=x+2){ // x
				tabCase[x][y].setPionNoir();

			}
		}
		// remplissage camp joueur
		for(y=y+2; y<this.taillePlateau; y++){ // y
			for (x=(y+1)%2; x<this.taillePlateau; x=x+2){ // x
				tabCase[x][y].setPionBlanc();

			}
		}
	}

	/**
	*Initialise l'emplacement de la boite au centre de la fenetre appelante
	*/
	public void init (JDialog fenetre) {
	    Dimension d = pGlobal.getSize () ;
	    Point p = pGlobal.getLocation () ;
	    fenetre.setLocation (p.x+(d.width-getSize().width)/2, p.y+(d.height-getSize().height)/2);
	}


	//Accesseur

	/**
	*@return case La case dont l'abscisse et l'ordonnée sont placé en parametre 
	*/
	public Case getCase(int x, int y){
		return tabCase[x][y];
	}
	
	/**
	*@return pGlobal Le JPanel contenant l'ensemble de l'interface
	*/	
	public JPanel getPanel(){
		return pGlobal;
	}

	/**
	*@return bNew Le bouton de création d'une nouvelle partie dans le menu
	*/		
	public JButton getNew(){
		return bNew;
	}

	/**
	*@return bCharger Le bouton de chargement d'une partie dans le menu
	*/
	public JButton getCharger(){
		return bCharger;
	}

	/**
	*@return bQuitter Le bouton quittant l'application
	*/
	public JButton getQuitter(){
		return bQuitter;
	}

	/**
	*@return bExit Le bouton quittant la phase de jeu pour un retour au menu en lancant DialogExit
	*/
	public JLabel getExit(){
		return bExit;
	}

	/**
	*@return bHelp Le bouton de lancement de l'aide
	*/
	public JLabel getHelp(){
		return bHelp;
	}

	/**
	*@return bSave Le bouton permettant de sauvegarder sa partie en lancant DialogSaveCharge
	*/
	public JLabel getSave(){
		return bSave;
	}

	/**
	*@return bExitAide Le bouton permettant de quitter l'aide pour revenir à la partie
	*/
	public JLabel getExitAide(){
		return bExitAide;
	}

	/**
	*@return tabCase La tableau de case utilisé pour placer les cases dans le plateau
	*/
	public Case[][] getTabCase(){
		return tabCase;
	}

	/**
	*@return taillePlateau La taille du plateau ( d'un coté)
	*/
	public int getTaillePlateau(){
		return taillePlateau;
	}
	
	/**
	*@return sizeGlobal La taille du panel de fond pGlobal
	*/
	public Dimension getSize(){
		return pGlobal.getSize();
	}

	/**
	*@return pointGlobal L'origine du panel de fond pGlobal
	*/
	public Point getLocation(){
		return pGlobal.getLocation();
	}

	/**
	*@return dialogFin La boite de dialogue DialogFin
	*/
	public DialogFin getDialogFin(){
		return this.dialogFin;
	}
	/**
	*@return dialogSave La boite de dialogue DialogSave
	*/
	public DialogSaveCharge getDialogSave(){
		return this.dialogSave;
	}
	/**
	*@return dialogCharge La boite de dialogue DialogCharge
	*/
	public DialogSaveCharge getDialogCharge(){
		return this.dialogCharge;
	}
	/**
	*@return dialogQuit La boite de dialogue DialogQuit
	*/
	public DialogQuitter getDialogQuit(){
		return this.dialogQuit;
	}
		/**
		*@return dialogTaille La boite de dialogue DialogTaille
		*/
	public DialogTaille getDialogTaille(){
		return this.dialogTaille;
	}
		/**
		*@return dialogErreur La boite de dialogue DialogErreur
		*/
	public DialogErreur getDialogErreur(){
		return this.dialogErreur;
	}




}
