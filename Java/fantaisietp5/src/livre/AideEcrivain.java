package livre;

import java.util.*;

import attaque.*;
import bataille.*;
import protagoniste.*;
import bataille.*;

public class AideEcrivain {
	private Bataille bataille;
	private NavigableSet<Monstre<?>> monstresDomaineSet = new TreeSet<>(
            new Comparator<Monstre<?>>() {
                public int compare (Monstre<?> m1, Monstre<?> m2) {
                    
                    if(m1.getDomaine().equals(m2.getDomaine())) {
                        return (m1.compareTo(m2));
                    }
                    return m1.getDomaine().compareTo(m2.getDomaine());
                }
            }
            );
	private NavigableSet<Monstre<?>> monstresZoneSet = new TreeSet<>(
            new Comparator<Monstre<?>>() {
                public int compare (Monstre<?> m1, Monstre<?> m2) {
                    
                    if(m1.getZoneDeCombat().equals(m2.getZoneDeCombat())) {
                        return (m1.compareTo(m2));
                    }
                    return m1.getZoneDeCombat().compareTo(m2.getZoneDeCombat());
                }
            }
            );
	private NavigableSet<Monstre<?>> monstresDeFeu = new TreeSet<>();
	private NavigableSet<Monstre<?>> monstresDeGlace = new TreeSet<>();
	private NavigableSet<Monstre<?>> monstresTranchants = new TreeSet<>();
	public AideEcrivain(Bataille bataille) {
		this.bataille = bataille;
	}
	
	public List<EtreVivant> visualiserForcesHumaines() {
		List<EtreVivant> listeTriee = new LinkedList<>();
		int nbHeros = 0;
		for(ListIterator<EtreVivant> iter = bataille.getCampHumains().iterator(); iter.hasNext();) {
			EtreVivant humainAcomparer = iter.next();
			if(humainAcomparer instanceof Heros) {
				listeTriee.add(nbHeros,humainAcomparer);
				nbHeros++;
			} else {
				listeTriee.add(humainAcomparer);
			}
		}
		return listeTriee;
		
	}
	
	public String ordreNaturelMonstre() {
		String e = "";
		NavigableSet<String> ens = new TreeSet<String>();
		for(Iterator<Monstre<?>> iterator = bataille.getCampMonstres().iterator(); iterator.hasNext();) {
				Monstre monstre = iterator.next();
				ens.add(monstre.getNom());
		}
		for (Iterator iterator = ens.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if(iterator.hasNext())
				e += string + ", ";
			else
				e += string + " ";
		}
		return e;
	}
	
	
	public void updateMonstresZone() {
		NavigableSet<Monstre<?>> ensAerien = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensAquatique = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensTerrestre = new TreeSet<Monstre<?>>();
		for(Iterator<Monstre<?>> iterator = bataille.getCampMonstres().iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			switch (monstre.getZoneDeCombat()) {
			case AERIEN:
				ensAerien.add(monstre);
				break;
			case AQUATIQUE:
				ensAquatique.add(monstre);
				break;
			case TERRESTRE:
				ensTerrestre.add(monstre);
				break;
			}
		}
		monstresZoneSet.addAll(ensAerien);
		monstresZoneSet.addAll(ensAquatique);
		monstresZoneSet.addAll(ensTerrestre);
	}
	
	public String ordreMonstreZone() {
		updateMonstresZone();
		NavigableSet<Monstre<?>> ensAerien = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensAquatique = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensTerrestre = new TreeSet<Monstre<?>>();
		for (Iterator<Monstre<?>> iterator = monstresZoneSet.iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			switch (monstre.getZoneDeCombat()) {
			case AERIEN:
				ensAerien.add(monstre);
				break;
			case AQUATIQUE:
				ensAquatique.add(monstre);
				break;
			case TERRESTRE:
				ensTerrestre.add(monstre);
				break;
			}
		}
		String e = "AERIEN :\n";
		for (Iterator<Monstre<?>> iterator = ensAerien.iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			e += monstre.getNom() + " : " + monstre.getForceDeVie() + ", ";
		}
		e += "\nAQUATIQUE :\n";
		for (Iterator<Monstre<?>> iterator = ensAquatique.iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			e += monstre.getNom() + " : " + monstre.getForceDeVie() + ", ";
		}
		e += "\nTERRESTRE :\n";
		for (Iterator<Monstre<?>> iterator = ensTerrestre.iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			if(iterator.hasNext())
				e += monstre.getNom() + " : " + monstre.getForceDeVie() + ", ";
			else
				e += monstre.getNom() + " : " + monstre.getForceDeVie() + " ";
		}
		return e;
	}
	/**
	public void updateMonstresDomaine() {
		NavigableSet<Monstre<?>> ensAerien = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensAquatique = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensTerrestre = new TreeSet<Monstre<?>>();
		for(Iterator<Monstre<?>> iterator = bataille.getCampMonstres().iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			switch (monstre.getZoneDeCombat()) {
			case AERIEN:
				ensFeu.add(monstre);
				break;
			case AQUATIQUE:
				ensGlace.add(monstre);
				break;
			case TERRESTRE:
				ensTranchant.add(monstre);
				break;
			}
		}
		monstresDomaineSet.addAll(ensFeu);
		monstresDomaineSet.addAll(ensGlace);
		monstresDomaineSet.addAll(ensTranchant);
	}*/
	public void updateMonstresDomaine(){
        for(ListIterator<Monstre<?>> iter = bataille.getCampMonstres().iterator(); iter.hasNext();) {
            Monstre<?> monstreAcomparer = iter.next();
            monstresDomaineSet.add(monstreAcomparer);
        }
    }
	
	public String ordreMonstreDomaine() {
		updateMonstresDomaine();
		NavigableSet<Monstre<?>> ensFeu = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensGlace = new TreeSet<Monstre<?>>();
		NavigableSet<Monstre<?>> ensTranchant = new TreeSet<Monstre<?>>();
		for (Iterator<Monstre<?>> iterator = monstresDomaineSet.iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			switch (monstre.getDomaine()) {
			case FEU:
				ensFeu.add(monstre);
				break;
			case GLACE:
				ensGlace.add(monstre);
				break;
			case TRANCHANT:
				ensTranchant.add(monstre);
				break;
			}
		}
		String e = "FEU :\n";
		for (Iterator<Monstre<?>> iterator = ensFeu.iterator(); iterator.hasNext();) {
			String nom = iterator.next().getNom();
			e += nom + ",";
		}
		e += "\nGLACE :\n";
		for (Iterator<Monstre<?>> iterator = ensGlace.iterator(); iterator.hasNext();) {
			String nom = iterator.next().getNom();
			e += nom + ",";
		}
		e += "\nTRANCHANT :\n";
		for (Iterator<Monstre<?>> iterator = ensTranchant.iterator(); iterator.hasNext();) {
			String nom = iterator.next().getNom();
			if(iterator.hasNext())
				e += nom + ", ";
			else
				e += nom + " ";
		}
		return e;
	}

	/**
	 * @return the monstresTranchants
	 */
	public NavigableSet<Monstre<?>> getMonstresTranchants() {
		updateMonstresZone();
		updateMonstresDomaine();
		initMonstresDeTranchant();
		return monstresTranchants;
	}

	/**
	 * @return the monstresDeGlace
	 */
	public NavigableSet<Monstre<?>> getMonstresDeGlace() {
		updateMonstresZone();
		updateMonstresDomaine();
		initMonstresDeGlace();
		return monstresDeGlace;
	}

	/**
	 * @return the monstresDeFeu
	 */
	public NavigableSet<Monstre<?>> getMonstresDeFeu() {
		updateMonstresZone();
		updateMonstresDomaine();
		initMonstresDeFeu();
		return monstresDeFeu;
	}
	
	public Monstre<?> firstMonstreDomaine(Domaine domaine) {
		return monstresDomaineSet.ceiling(new Monstre(" ", 0, null, domaine, new PicsDeGlace(0)));
	}
	/**
	public void initMonstresDeFeu() {
		updateMonstresDomaine();
		for (Iterator<Monstre<?>> iterator = monstresDomaineSet.iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			if(monstre.getDomaine().equals(Domaine.FEU)) {
				monstresDeFeu.add(monstre);
			}
		}
	}*/
	public void initMonstresDeFeu() {
		monstresDeFeu = monstresDomaineSet.headSet(new Monstre(" ", 0, null, Domaine.GLACE, new PicsDeGlace(0)), false);
    }
	public void initMonstresDeGlace() {
        monstresDeGlace = monstresDomaineSet.subSet(new Monstre(" ", 0, null, Domaine.GLACE, new PicsDeGlace(0)), false, new Monstre(" ", 0, null, Domaine.TRANCHANT, new Griffe()), false);
    }
	public void initMonstresDeTranchant() {
        monstresTranchants = monstresDomaineSet.tailSet(new Monstre(" ", 0, null, Domaine.TRANCHANT, new PicsDeGlace(0)), false);
    }
	
}
