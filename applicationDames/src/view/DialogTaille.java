package view;

import javax.swing.*;
import java.awt.*;

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

	public void setStyleButton(JButton button){
		button.setSize(100,35);
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
		button.setBorderPainted(false);
	}

	/*
	public void init () {
   	JFrame parent = ;
    Dimension d = parent.getSize () ;
    Point p = parent.getLocation () ;
    setLocation (p.x+(d.width-getSize().width)/2, p.y+(d.height-getSize().height)/2);
	}
	*/

	public JButton getAnnuler(){
		return bAnnuler;
	}

	public JButton getConfirmer(){
		return bConfirmer;
	}

	public int getContenu(){
		return (int) cbTaille.getSelectedItem();
	}
}
