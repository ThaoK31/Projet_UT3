package cartes;

import java.util.*;

public class Borne extends Carte {
	private int km;
	
	public Borne(int nombre, int km) {
		super(nombre);
		this.km = km;
	}

	public int getKm() {
		return km;
	}

	@Override
	public String toString() {
		return "Borne [km=" + km + "]";
	}





}
