package view;

import javax.swing.*;
import java.awt.*;


public class ViewDames {
	
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

	private int taillePlateau=8;

	private Case[][] tabCase = new Case[taillePlateau][taillePlateau];

	public ViewDames(){

		pGlobal = new JPanel();
		pGlobal.setSize(322,346);

		bpPrincipal = new JBackgroundPanel("datas/Fond_net.jpg");
		pGlobal.add(bpPrincipal);

	}

	public void creerInterfaceJeu(){

		bpPrincipal.setLayout(new BorderLayout());

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
		
		initPlateau();
		
		remplirPlateau();

	}

	public void creerInterfaceMenu(){

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
					
					theCase.setCaseNoire();

					pPlateau.add(theCase);

					tabCase[x][y]=theCase;
				}
				else{
					Case theCase = new Case(x,y,taillePlateau);

					theCase.setCaseBlanche();

					pPlateau.add(theCase);

					tabCase[x][y]=theCase;
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



}
