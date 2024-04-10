package jeu;

import cartes.*;

public interface Cartes {
	  public static final Botte PRIORITAIRE = new Botte(1, Type.FEU); 
	    public static final Attaque FEU_ROUGE = new Attaque(5, Type.FEU);
	    public static final Parade FEU_VERT = new Parade(Type.FEU, 14);
	}

