package protagoniste;

import java.util.*;

import attaque.Arme;
import attaque.KeyArme;
import bataille.Bataille;

public class Homme extends EtreVivant{
	private Arme armeChoisie;
	private Map<ZoneDeCombat, List<Arme>> armes = new EnumMap<ZoneDeCombat, List<Arme>>(ZoneDeCombat.class);
	public Homme(String nom) {
		super(nom,70);
        ArrayList<Arme> a = new ArrayList<>();
        armes.put(ZoneDeCombat.AERIEN, a);
        armes.put(ZoneDeCombat.TERRESTRE, a);
        armes.put(ZoneDeCombat.AQUATIQUE, a);
	}
	
	public void rejointBataille(Bataille bataille) {
		bataille.ajouter(this);;
	}
	
	public void mourir() {
		this.bataille.eliminer(this);
	}

	@Override
	public String toString() {
		return "Homme [getNom()=" + getNom() + ", getForceDeVie()=" + getForceDeVie() + "]";
	}
	
	public void ajouterArmes(Arme... arme) {
        for(int i = 0; i < arme.length ; i++) {
            for(Iterator<ZoneDeCombat> iter = arme[i].getZonesDeCombat().iterator(); iter.hasNext();) {
                ZoneDeCombat zdc = iter.next();
                armes.get(zdc).add(arme[i]);
            }
        }
    }
	
	public void supprimerArme(Arme armeSuprr) {
		for (Iterator<ZoneDeCombat> iterator = armeSuprr.getZonesDeCombat().iterator(); iterator.hasNext();) {
			ZoneDeCombat zdc = iterator.next();
			armes.remove(zdc, armeSuprr);
		}
	}
	
	public Arme choisirArme(Monstre<?> monstre) {
		ZoneDeCombat zoneDeCombat = monstre.getZoneDeCombat();
		Arme arme = null;
		System.out.println("zoneDeCombat choisirArme : "+zoneDeCombat);
		System.out.println("armes choisirArme : "+armes);
		if(armes.containsKey(zoneDeCombat)) {
			List<Arme> listeArmes = this.armes.get(zoneDeCombat);
			NavigableSet<Arme> armesTriees = new TreeSet<>(listeArmes);
			if(!armesTriees.isEmpty()) {
				int forceMonstre = monstre.getForceDeVie();
				NavigableSet<Arme> armesAdaptees = armesTriees.tailSet(new KeyArme(forceMonstre), true);
				if(!armesAdaptees.isEmpty()) {
					arme = armesAdaptees.pollFirst();
				} else {
					arme = armesTriees.pollLast();
				}
			}
		}
		this.armeChoisie = arme;
		return arme;
	}

	public Arme getArmeChoisie() {
		return armeChoisie;
	}
	
}
