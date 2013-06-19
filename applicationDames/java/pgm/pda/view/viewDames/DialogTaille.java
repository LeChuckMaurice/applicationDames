package pda.view.viewDames;

import javax.swing.*;
import java.awt.*;

/**
*Cette boite de dialogue permet la selection de la taille du plateau, elle est sous classe de JDialog
*Elle ce compose d'un JLabel indiquant l'action, d'une Combo box permettant le choix parmis les differentes tailles proposer et de 
*boutons pour annuler ou comfirmer
*/
public class DialogTaille extends JDialog{
	
	private JPanel pPrincipal;
	private JLabel lText;
	private JPanel pComboBox;
	private JComboBox<Integer> cbTaille;
	private JPanel pButton;
	private JButton bAnnuler;
	private JPanel pAnnuler;
	private JButton bConfirmer;
	private JPanel pConfirmer;


	public DialogTaille(){
		super ();
		creerInterface();

		this.setSize(240,160);
		this.setVisible(false);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
		this.setUndecorated(true);
	}

	/**
	*Cree le décor de la boite de dialogue
	*/
	public void creerInterface(){

		pPrincipal= new JPanel();
		pPrincipal.setBackground(Color.darkGray);
		this.add(pPrincipal);

		pPrincipal.setLayout(new GridLayout(3,1));

		lText = new JLabel("Taille du plateau");
		lText.setForeground(Color.white);
		lText.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		lText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pPrincipal.add(lText);

		pComboBox = new JPanel();
		pComboBox.setOpaque(false);
		pPrincipal.add(pComboBox);

		cbTaille = new JComboBox<Integer>();
		cbTaille.setPreferredSize(new Dimension(200,25));
		// On ajoute les différentes tailles de tableau possible a la JComboBox
		cbTaille.addItem(8);
		cbTaille.addItem(10);
		cbTaille.addItem(12);

		pComboBox.add(cbTaille);

		pButton= new JPanel();
		pButton.setOpaque(false);
		pPrincipal.add(pButton);

		pButton.setLayout(new GridLayout(1,2));

		pAnnuler = new JPanel();
		pAnnuler.setOpaque(false);
		pButton.add(pAnnuler);

		bAnnuler= new JButton("Annuler");
		setStyleButton(bAnnuler);
		pAnnuler.add(bAnnuler);

		pConfirmer = new JPanel();
		pConfirmer.setOpaque(false);
		pButton.add(pConfirmer);
		
		bConfirmer=new JButton("Confirmer");
		setStyleButton(bConfirmer);
		pConfirmer.add(bConfirmer);


	}

	/**
	*Definit un style au bouton passé en parametre
	*Utilisé pour unifié le look des différent boutons de la boite
	*/
	public void setStyleButton(JButton button){
		button.setSize(100,35);
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
		button.setBorderPainted(false);
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

	/**
	*Renvoit le bouton servant à quitter la boite
	*/
	public JButton getAnnuler(){
		return bAnnuler;
	}

	/**
	*Renvoit le bouton servant à valider le choix
	*/
	public JButton getConfirmer(){
		return bConfirmer;
	}

	/**
	*Renvoit l'élément de la JComboBox actuellement selectionné
	*/
	public int getContenu(){
		return (int) cbTaille.getSelectedItem();
	}
}
