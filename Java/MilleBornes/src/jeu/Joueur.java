package jeu;

import java.util.*;
import cartes.*;

public class Joueur {
	private String name;
	protected ZoneDeJeu zoneJoueur;
	public ZoneDeJeu getZoneJoueur() {
		return zoneJoueur;
	}

	public void setZoneJoueur(ZoneDeJeu zoneJoueur) {
		this.zoneJoueur = zoneJoueur;
	}


	private MainAsListe main = new MainAsListe();
	
	public Joueur(String name) {
		this.name = name;
		this.zoneJoueur =new ZoneDeJeu();
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(List <Carte> sabot) {
		if (sabot.isEmpty()) return null;
		Carte prise = sabot.get(0);
		sabot.remove(0); 
		donner(prise);
		return prise;
		
	}
	public boolean deposer(Borne borne) { // A surcharger
		zoneJoueur.getBornes().add(borne);
		return true;
	}
	
	public int donnerKmParcourus() {
		int kmParcourus = 0;
		for (Borne borne : zoneJoueur.getBornes()) {
			kmParcourus += borne.getKm();
		}
		return kmParcourus;
	}
	public int donnerLimitationVitesse() {
		if((zoneJoueur.getPileLimites().isEmpty()) || 
		   (zoneJoueur.getPileLimites().get(0) instanceof FinLimite) ||
		   (zoneJoueur.getEnsembleBottes().contains(Cartes.PRIORITAIRE))) {
			return 200;
		}
		
		else {
			return 50;
		}
	}
	
	
	public String getName() {
		return name;
	}
	
	
	@Override
	public int hashCode() {
		return 31 * name.hashCode() ;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			Joueur joueur = (Joueur) obj;
			return name == joueur.getName();
		}
		return false;
	}


	@Override
	public String toString() {
		return "Joueur [name=" + name + "]";
	}


	public MainAsListe getMain() {
		return main;
	}


	
}

	
	

