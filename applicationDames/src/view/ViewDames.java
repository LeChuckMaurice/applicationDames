package view;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;


public class ViewDames extends JFrame{
	
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

	private DialogFin dialogWin;
	private DialogFin dialogLose;
	private DialogSaveCharge dialogSave;
	private DialogSaveCharge dialogCharge;
	private DialogQuitter dialogQuit;
	private DialogTaille dialogTaille;

	public ViewDames(){

		super();
		this.setSize(330,350);
		pGlobal = new JPanel();
		pGlobal.setSize(322,346);
		//pGlobal.removeAll();
		pGlobal.updateUI();
		this.add(pGlobal);

		dialogWin = new DialogFin(true);
		dialogLose = new DialogFin(false);
		dialogSave = new DialogSaveCharge(false);
		dialogCharge = new DialogSaveCharge(true);
		dialogQuit = new DialogQuitter();
		dialogTaille = new DialogTaille();
		
		this.setVisible(true);
		this.setDefaultCloseOperation ( EXIT_ON_CLOSE );

		this.creerInterfaceMenu();
		
		

	}

	public void creerInterfaceJeu(int laTaille){

		pGlobal.removeAll();
		pGlobal.updateUI();
		this.taillePlateau=laTaille;

		bpPrincipal = new JBackgroundPanel("datas/Fond_net.jpg");
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

		ImageIcon iSave=new ImageIcon("datas/save.png");
		bSave = new JLabel();
		bSave.setIcon(iSave);
		bSave.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bSave);
		
		ImageIcon iHelp=new ImageIcon("datas/help.png");
		bHelp = new JLabel();
		bHelp.setIcon(iHelp);
		bHelp.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bHelp);

		ImageIcon iExit=new ImageIcon("datas/exit.png");
		bExit = new JLabel();
		bExit.setIcon(iExit);
		bExit.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bExit);

		tabCase = new Case[taillePlateau][taillePlateau];
		
		initPlateau();

	}

	public void creerInterfaceMenu(){
		
		pGlobal.removeAll();
		pGlobal.updateUI();

		bpPrincipal = new JBackgroundPanel("datas/Fond_net.jpg");
		pGlobal.add(bpPrincipal);
		bpPrincipal.setLayout(new GridLayout(4,1));

		pLogo = new JPanel();
		pLogo.setOpaque (false);
		bpPrincipal.add(pLogo);
		lLogo = new JLabel();
		ImageIcon logoDame=new ImageIcon("datas/logo.png");
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

	public void creerInterfaceAide(){
		
		pGlobal.removeAll();
		pGlobal.updateUI();
		
		bpPrincipal = new JBackgroundPanel("datas/Fond_net.jpg");
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

		ImageIcon iExit=new ImageIcon("datas/exit.png");
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
			u = new File("datas/aide.html").toURI().toURL();
			
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
		spAide.setPreferredSize(new Dimension(300, 800));
		spAide.setOpaque(false);
		spAide.getViewport().setOpaque(false);
		pAide.add(spAide,BorderLayout.CENTER);
			
			

	}

	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(240,58));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.PLAIN, 22));
	}

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

	public Case getCase(int x, int y){
		return tabCase[x][y];
	}
	
	public JPanel getPanel(){
		return pGlobal;
	}

		
	public JButton getNew(){
		return bNew;
	}

	public JButton getCharger(){
		return bCharger;
	}

	public JButton getQuitter(){
		return bQuitter;
	}

	public JLabel getExit(){
		return bExit;
	}

	public JLabel getHelp(){
		return bHelp;
	}

	public JLabel getSave(){
		return bSave;
	}

	public JLabel getExitAide(){
		return bExitAide;
	}

	public Case[][] getTabCase(){
		return tabCase;
	}

	public int getTaillePlateau(){
		return taillePlateau;
	}
	
	public Dimension getSize(){
		return pGlobal.getSize();
	}

	public Point getLocation(){
		return pGlobal.getLocation();
	}

	public DialogFin getDialogWin(){
		return dialogWin;
	}
	
	public DialogFin getDialogLose(){
		return dialogLose;
	}

	public DialogSaveCharge getDialogSave(){
		return dialogSave;
	}

	public DialogSaveCharge getDialogCharge(){
		return dialogCharge;
	}

	public DialogQuitter getDialogQuit(){
		return dialogQuit;
	}
	
	public DialogTaille getDialogTaille(){
		return dialogTaille;
	}



}
