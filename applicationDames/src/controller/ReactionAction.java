package controller;

import java.awt.*;
import java.awt.event.*;
import view.*;

public class ReactionAction implements Globale, ActionListener, MouseListener{


	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src==(Globale.theView).getNew()) {
			reactionNewGame();
		}
		else if (src==(Globale.theView).getDialogTaille().getConfirmer()) {
			creationNewGame();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	
	}

	public void reactionNewGame(){
		(Globale.theView).getDialogTaille().setVisible(true);
	}

	public void creationNewGame(){
		System.out.println("Coucou");
		DialogTaille dialogTaille=(Globale.theView).getDialogTaille();
		int taillePlat=dialogTaille.getContenu();
		dialogTaille.setVisible(false);
		(Globale.theView).creerInterfaceJeu(taillePlat);
	}
	
}
		
	
