package datas;

import java.io.Serializable;
 
public class IA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plateau plateau;

	public IA(Plateau plateauIA){
		this.plateau = plateauIA;
	}

	public Coup getAction(){
		return null;
	}

	private Coup[] getAllCoups( boolean camp){
		return null;
	}

}
