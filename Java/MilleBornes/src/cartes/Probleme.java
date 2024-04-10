package cartes;

import cartes.*;
import java.util.*;


public abstract class Probleme extends Carte {

	private Type type; 

	protected Probleme(int nombre, Type type) {
		super(nombre);
		this.type = type;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			Probleme probleme = (Probleme) obj;
			return type == probleme.getType();
		}
		return false;
	}

	public Type getType() {
		return type;
	}


}
