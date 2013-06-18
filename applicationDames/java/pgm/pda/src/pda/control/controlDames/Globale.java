package pda.control.controlDames;

import pda.datas.datasDames.*;
import pda.view.viewDames.*;

/**
*Cette interface declare les élements des datas et view sur lesquelles le controller effectura ses références
*@see Partie
*@see ViewDames
*/
public interface Globale {
	public static final ViewDames theView = new ViewDames();
	public static final Partie thePart = new Partie();
}
