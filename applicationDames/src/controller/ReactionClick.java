package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ReactionClick implements Globale, MouseListener{
	
	private DamesControl myCtrl;
	
	public ReactionClick(DamesControl theCtrl){
		this.myCtrl=theCtrl;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();

		if (src==(Globale.theView).getExit()) {
			quitterPlateau();
		}
		else if (src==(Globale.theView).getHelp()) {
			lancementAide();
		}
		else if (src==(Globale.theView).getSave()) {
			(Globale.theView).getDialogSave().setVisible(true);
		}
		else if (src==(Globale.theView).getExitAide()) {
			retourPlateau();
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

	private void retourPlateau(){
		(Globale.theView).creerInterfaceJeu(8);
		myCtrl.attacherReactionsPlateau();
	}

	private void lancementAide(){
		(Globale.theView).creerInterfaceAide();
		myCtrl.attacherReactionsAide();
	}

	private void quitterPlateau(){
		(Globale.theView).getDialogQuit().setVisible(true);
	
	}

}
