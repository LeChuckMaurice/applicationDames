package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

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

		JBackgroundPanel pPrincipal = new JBackgroundPanel();
		pPrincipal.setBackground(new Color(246,199,139));
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
		bNew.setPreferredSize( new Dimension(240,58));
		bNew.setBackground(Color.black);
		bNew.setForeground(Color.white);
		bNew.setFont(newFont);
		pNew.add(bNew);

		pCharger = new JPanel();
		pCharger.setOpaque (false);
		pPrincipal.add(pCharger);
		bCharger = new JButton("Charger une partie");
		bCharger.setPreferredSize( new Dimension(240,58));
		bCharger.setBackground(Color.black);
		bCharger.setForeground(Color.white);
		bCharger.setFont(newFont);
		pCharger.add(bCharger);

		pQuitter = new JPanel();
		pQuitter.setOpaque (false);
		pPrincipal.add(pQuitter);
		bQuitter = new JButton("Quitter");
		bQuitter.setPreferredSize( new Dimension(240,58));
		bQuitter.setBackground(Color.black);
		bQuitter.setForeground(Color.white);
		bQuitter.setFont(newFont);
		pQuitter.add(bQuitter);
	}


public class JBackgroundPanel extends JPanel {
  private BufferedImage img;
 
  public JBackgroundPanel() {
    // load the background image
    try {
      img = ImageIO.read(new File("datas/Fond_net.jpg"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
 
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // paint the background image and scale it to fill the entire space
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
  }
}



	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
	}

}
