package cartes;

public class Botte extends Probleme {

	public Botte(int nombre, Type type) {
		super(nombre, type);
	}


	public String toString() {
		String toString = null;
		switch (getType()) {
		case FEU : toString= "VEHICULE PRIORITAIRE";
				break;
		case ESSENCE : toString= "CITERNE D'ESSENCE";
				break; 
		case CREVAISON : toString= "INCREVABLE";
				break;
		case ACCIDENT : toString= "AS DU VOLANT";
				break;
		default : toString= "Il y a un problème.";
		}

		return toString;
	}


	
}
		
