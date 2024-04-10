package cartes;

public class Parade extends Bataille {

	public Parade(Type type, int nombre) {
		super(nombre, type);
	}
	public String toString() {
		String toString = null;
		switch (getType()) {
		case FEU : toString= "FEU VERT";
				break;
		case ESSENCE : toString= "ESSENCE";
				break; 
		case CREVAISON : toString= "ROUE DE SECOURS";
				break;
		case ACCIDENT : toString= "REPARATION";
				break;
		default : toString= "Il y a un problème.";
		
		}
		return toString;
			
		
	}

}
