package cartes;

public class Attaque extends Bataille {

	public Attaque(int nombre, Type type) {
		super(nombre, type);
	}

	public String toString() {
		String toString = null;
		switch (getType()) {
		case FEU : toString= "FEU ROUGE";
				break;
		case ESSENCE : toString= "PANNE D'ESSENCE";
				break; 
		case CREVAISON : toString= "CREVAISON";
				break;
		case ACCIDENT : toString= "ACCIDENT";
				break;
		default : toString= "Il y a un problème.";
		
		}
		return toString;
			
		
	}
	
}

