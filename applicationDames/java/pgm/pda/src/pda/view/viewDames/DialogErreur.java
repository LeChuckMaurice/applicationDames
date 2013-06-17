package pda.view.viewDames;

import javax.swing.*;
import java.awt.*;

	public class DialogErreur extends JDialog{

	private JPanel pPrincipal;
	private JLabel lText;
	private JPanel pButton;
	private JButton bOk;

	public DialogErreur(String msg){
		super();
		creerInterface(msg);
		this.setSize(240,130);
		this.setVisible(false);
		this.setDefaultCloseOperation ( HIDE_ON_CLOSE );
		this.setUndecorated(true);
	}

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

	public void changeText(String msg){
		lText.setText(msg);
	}

	public JButton getOk(){
		return bOk;
	}
}
