package bataille;

import protagoniste.*;

public class Salle {
	private int numSalle;
	private ZoneDeCombat zoneDeCombat;
	public Salle(int numSalle, ZoneDeCombat zoneDeCombat) {
		this.numSalle = numSalle;
		this.zoneDeCombat = zoneDeCombat;
	}
	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}
	public int getNumSalle() {
		return numSalle;
	}
	@Override
	public String toString() {
		return "Salle n °" + numSalle + " de type combat " + zoneDeCombat;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numSalle;
		result = prime * result + ((zoneDeCombat == null) ? 0 : zoneDeCombat.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		if (numSalle != other.numSalle)
			return false;
		if (zoneDeCombat != other.zoneDeCombat)
			return false;
		return true;
	}
}
