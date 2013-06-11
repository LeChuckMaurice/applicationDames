package view;

import javax.swing.*;
import java.awt.*;



public class GameIG extends JFrame{

	private JBackgroundPanel bpPrincipal;
	private JPanel pMenu;
	private JPanel pGame;
	private JPanel pMenuLeft;
	private JPanel pMenuRight;
	private JPanel pPlateau;

	private JLabel bExit;
	private JLabel bHelp;
	private JLabel bSave;

	public GameIG(){
		super(" GameIG ");
		this.creerInterface();
		//this.attacherReactions();
		this.setSize(322,346);
		this.setVisible(true);
		this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
	}

	public void creerInterface(){

		bpPrincipal = new JBackgroundPanel("datas/Fond_net.jpg");
		this.add(bpPrincipal);

		bpPrincipal.setLayout(new BorderLayout());

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



	}

	public static void main(String[] args) {
		GameIG game = new GameIG();
	}



}
