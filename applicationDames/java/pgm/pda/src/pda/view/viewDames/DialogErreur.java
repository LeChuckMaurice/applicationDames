package pda.view.viewDames;

import javax.swing.*;
import java.awt.*;

	/**
	*Construit ne boite de dialogue affichant un message d'erreur
	*Elle ce compose d'un JLabel affichant le message et d'un bouton pour quitter la boite
	*/
	public class DialogErreur extends JDialog{

	private JPanel pPrincipal;
	private JLabel lText;
	private JPanel pButton;
	private JButton bOk;

	/**
	*Construit la boite de dialogue et initialise le JLabel avec le texte passé en parametre
	*/
	public DialogErreur(String msg){
		super();
		creerInterface(msg);
		this.setSize(240,130);
		this.setVisible(false);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
		this.setUndecorated(true);
		//init();
	}

	/**
	*Crée le décor graphique de la boite de message d'erreur
	*/
	public void creerInterface(String msg){
			
		pPrincipal = new JPanel();
		pPrincipal.setLayout(new GridLayout(2,1));
		pPrincipal.setBackground(Color.darkGray);
		this.add(pPrincipal);

		lText = new JLabel(msg);
		lText.setForeground(Color.white);
		lText.setFont(new Font(Font.DIALOG, Font.BOLD, 13) );
		lText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pPrincipal.add(lText);

		pButton = new JPanel();
		pButton.setLayout(new FlowLayout(1));
		pButton.setOpaque(false);
		pPrincipal.add(pButton);

		bOk = new JButton("Ok");
		bOk.setPreferredSize( new Dimension(100,35));
		bOk.setBackground(Color.black);
		bOk.setForeground(Color.white);
		bOk.setBorderPainted(false);
		bOk.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
		pButton.add(bOk);

	}

	/**
    *Initialise l'emplacement de la fenetre
    */
	public void init () {
	
    	JFrame parent = (JFrame) this.getOwner();
    	Dimension d = parent.getSize () ;
    	Point p = parent.getLocation () ;
    	setLocation (p.x+(d.width-getSize().width)/2, p.y+(d.height-getSize().height)/2);
    }

    /**
    *Change le texte du JLabel par celui passé en parametre
    */
	public void changeText(String msg){
		lText.setText(msg);
	}

	/**
	*Renvoit le bouton Ok
	*/
	public JButton getOk(){
		return bOk;
	}
}
