package view;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame{

	private JPanel pPrincipal;
	private JPanel pLogo;
	private JLabel lLogo;

	private JButton bNew;
	private JPanel pNew;
	private JButton bCharger;
	private JPanel pCharger;
	private JButton bQuitter;
	private JPanel pQuitter;

	private Font newFont = new Font(Font.DIALOG, Font.PLAIN, 24);

	public MainMenu(){
		super(" MainMenu ");
		this.creerInterface();
		//this.attacherReactions();
		this.setSize(322,346);
		this.setVisible(true);
		this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
	} 

	public void creerInterface(){
		this.setLayout(new BorderLayout());

		JBackgroundPanel pPrincipal = new JBackgroundPanel("datas/Fond_net.jpg");
		this.add(pPrincipal,BorderLayout.CENTER);

		pPrincipal.setLayout(new GridLayout(4,1));

		pLogo = new JPanel();
		pLogo.setOpaque (false);
		pPrincipal.add(pLogo);
		lLogo = new JLabel();
		ImageIcon logoDame=new ImageIcon("datas/logo.png");
		lLogo.setIcon(logoDame);
		pLogo.add(lLogo);

		pNew = new JPanel();
		pNew.setOpaque (false);
		pPrincipal.add(pNew);
		bNew = new JButton("Nouvelle partie");
		setStyleButton(bNew);
		pNew.add(bNew);

		pCharger = new JPanel();
		pCharger.setOpaque (false);
		pPrincipal.add(pCharger);
		bCharger = new JButton("Charger une partie");
		setStyleButton(bCharger);
		pCharger.add(bCharger);

		pQuitter = new JPanel();
		pQuitter.setOpaque (false);
		pPrincipal.add(pQuitter);
		bQuitter = new JButton("Quitter");
		setStyleButton(bQuitter);
		pQuitter.add(bQuitter);
	}

	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(240,58));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(newFont);
	}



	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
	}

}
