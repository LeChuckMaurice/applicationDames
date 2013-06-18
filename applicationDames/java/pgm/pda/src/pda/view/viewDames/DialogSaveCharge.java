package pda.view.viewDames;

import javax.swing.*;
import java.awt.*;

/**
*Cette boite de dialogue sert de squelettes à celles permettant de sauvegarder et de quitter, elle est sous classe de JDialog
*Elle ce compose dd'un JLabel indiquant l'action que on va effectuer et de 2 boutons pour confirmer ou annuler cette action
*/
public class DialogSaveCharge extends JDialog{

	private JPanel pPrincipal;
	private JLabel lText;
	private JPanel pButton;
	private JPanel pAnnuler;
	private JButton bAnnuler;
	private JPanel pConfirmer;
	private JButton bSave;
	private JButton bCharge;

	
	public DialogSaveCharge(boolean isCharge){
		super ();
		creerInterface(isCharge);

		this.setSize(240,130);
		this.setVisible(false);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
		this.setUndecorated(true);
		this.init();
	}

	/**
	*Crée le décors de la boite de dialogue 
	*@param isCharge true pour une boite de chargement, false pour une sauvegarde. On crée le décor en conséquence
	*/
	public void creerInterface(boolean isCharge){
		
		pPrincipal = new JPanel();
		pPrincipal.setLayout(new GridLayout(2,1));
		pPrincipal.setBackground(Color.darkGray);
		this.add(pPrincipal);

		lText = new JLabel();
		lText.setForeground(Color.white);
		lText.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		lText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pPrincipal.add(lText);

		pButton = new JPanel();
		pButton.setLayout(new GridLayout(1,2));
		pButton.setOpaque(false);
		pPrincipal.add(pButton);


		pAnnuler = new JPanel();
		pAnnuler.setOpaque(false);
		pButton.add(pAnnuler);

		bAnnuler = new JButton("Annuler");
		setStyleButton(bAnnuler);
		pAnnuler.add(bAnnuler);

		pConfirmer = new JPanel();
		pConfirmer.setOpaque(false);
		pButton.add(pConfirmer);

		if (isCharge) {
			lText.setText("Charger la partie");
			bCharge = new JButton("Charger");
			setStyleButton(bCharge);
			pConfirmer.add(bCharge);
		}
		else {
			lText.setText("Sauvegarder la partie");
			bSave = new JButton("Sauvegarder");
			setStyleButton(bSave);
			pConfirmer.add(bSave);
		}
	}

	/**
	*Definit un style au bouton passé en parametre
	*Utilisé pour unifié le look des différent boutons de la boite
	*/
	public void setStyleButton(JButton button){
		button.setPreferredSize( new Dimension(105,35));
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		button.setBorderPainted(false);
	}

	/**
	*@return bCharge le bouton permettant le chargement d'une partie
	*/
	public JButton getCharge(){
		return bCharge;
	}
	
	/**
	*@return bSave le bouton permettant la sauvegarde d'une partie
	*/
	public JButton getSave(){
		return bSave;
	}

	/**
	*@return bAnnuleur le bouton fermant la boite de dialogue
	*/
	public JButton getAnnuler(){
		return bAnnuler;
	}

	/**
	*Initialise l'emplacement de la boite au centre de la fenetre appelante
	*/
	public void init () {
	   	JFrame parent = (JFrame) this.getOwner();
	    Dimension d = parent.getSize () ;
	    Point p = parent.getLocation () ;
	    setLocation (p.x+(d.width-getSize().width)/2, p.y+(d.height-getSize().height)/2);
	}

}
