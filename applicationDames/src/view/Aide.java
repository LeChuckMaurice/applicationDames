package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



	public class Aide extends JFrame{

	private JBackgroundPanel bpPrincipal;
	private JPanel pMenu;
	private JPanel pMenuLeft;
	private JPanel pMenuRight;
	private JPanel pAide;
	private JEditorPane epAide;
	private JScrollPane spAide;	
	private JLabel bExit;
	private JLabel bHelp;
	private JLabel bSave;

	public Aide(){
		super(" Aide ");
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
		bExit = new JLabel();
		bExit.setIcon(iExit);
		bExit.setBackground(new Color(0,0,0,0));
		pMenuRight.add(bExit);
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

		

	public JLabel getExit(){
		return bExit;
	}



}


