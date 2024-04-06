package attaque;

import java.util.HashSet;
import java.util.Set;
import protagoniste.ZoneDeCombat;

public abstract class Arme<ZoneDeCombat> extends ForceDeCombat {
	private String nom;
	private Set<ZoneDeCombat> zonesDeCombat = new HashSet<>();
	@SafeVarargs
	public Arme(int PointDeDegat, String nom, ZoneDeCombat ... zonesDeCombats ) {
		super(PointDeDegat,nom);
		this.nom = nom;
		for (int i = 0; i < zonesDeCombats.length; i++) {
			this.zonesDeCombat.add(zonesDeCombats[i]);
		}
		
	}
	public Set<ZoneDeCombat> getZonesDeCombat() {
		return zonesDeCombat;
	}
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((zonesDeCombat == null) ? 0 : zonesDeCombat.hashCode());
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
		Arme<?> other = (Arme<?>) obj;
		if (zonesDeCombat == null) {
			if (other.zonesDeCombat != null)
				return false;
		} else if (!zonesDeCombat.equals(other.zonesDeCombat))
			return false;
		return true;
	}
	
	public int compareTo(Arme<?> a2) {
		return nom.compareTo(a2.nom);
	}
}
