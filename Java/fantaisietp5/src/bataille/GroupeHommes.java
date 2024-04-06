package bataille;

import java.util.*;

import attaque.Arme;
import attaque.Pouvoir;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class GroupeHommes {
	NavigableSet<Homme> groupe = new TreeSet<>();
	
	public void ajouterHommes(Homme ... hommes ) {
		for (int i = 0; i < hommes.length; i++) {
			groupe.add(hommes[i]);
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupe == null) ? 0 : groupe.hashCode());
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
		GroupeHommes other = (GroupeHommes) obj;
		if (groupe == null) {
			if (other.groupe != null)
				return false;
		} else if (!groupe.equals(other.groupe))
			return false;
		return true;
	}
	
	public static class ComparateurHommes implements Comparator<Homme> {
		public int compare(Homme h1, Homme h2) {
			if(h1.getForceDeVie()==h2.getForceDeVie()) {
                return (h1.compareTo(h2));
            }
            return Math.abs(h1.getForceDeVie()-h2.getForceDeVie());
		}
	}
	
	public static class ComparateurArmes implements Comparator<Arme> {
		Monstre<?> monstre;
		TreeMap<Integer, Arme<ZoneDeCombat>> classementForce = new TreeMap<>();
		
		public ComparateurArmes(Monstre<?> monstre) {
			this.monstre = monstre;
		}
		
		public int compare(Arme a1, Arme a2) {
			int forceDeVie = monstre.getForceDeVie();
			int d1 = a1.getPointDeDegat();
			int d2 = a2.getPointDeDegat();
			if(d1!=d2) {
				classementForce.put(d1, a1);
				classementForce.put(d2, a2);
				if(d1<=forceDeVie)
					return -1;
				else if(d2<=forceDeVie)
					return 1;
				else if(d1<d2)
					return -1;
				else
					return 1;
			} else
				return a1.compareTo(a2);
		}
	}

	public List<Homme> choixCampHomme(Bataille bataille){
        Monstre<?> monstre = (Monstre<?>) bataille.getCampMonstres().selectionner();
        Map<Arme, NavigableSet<Homme>> hommesArmes = new TreeMap<>(new ComparateurArmes(monstre));
        for(Homme homme : this.groupe) {
            Arme arme = homme.choisirArme(monstre);
            System.out.println("Arme choisirCampHomme "+arme);
            if(arme != null){
                if(hommesArmes.containsKey(arme)) {
                    hommesArmes.get(arme).add(homme);
                }else{
                    NavigableSet<Homme> listeHomme = new TreeSet<>(new ComparateurHommes());
                    listeHomme.add(homme);
                    hommesArmes.put(arme, listeHomme);
                }
            
            }
        }
        
        List<Homme> hommes = new ArrayList<>();
        for(Arme arme : hommesArmes.keySet()){
            for(Homme homme : hommesArmes.get(arme)) {
                if(hommes.size() < 3){
                    hommes.add(homme);
                    homme.rejointBataille(bataille);
                }
            }
                
        }
        return hommes ;
    }
}
