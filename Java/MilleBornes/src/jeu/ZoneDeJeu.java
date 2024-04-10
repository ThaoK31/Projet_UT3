package jeu;

import java.util.*;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> pileLimites;
	private List<Carte> pileBataille;
	private Collection<Borne> bornes;
	private Set<Carte> ensembleBottes;
	
	public ZoneDeJeu() {
		this.pileLimites = new LinkedList<>();
		this.pileBataille = new LinkedList<>();
		this.bornes = new LinkedList<>();
		this.ensembleBottes = new HashSet<>();
	}

	public List<Limite> getPileLimites() {
		return pileLimites;
	}


	public List<Carte> getPileBataille() {
		return pileBataille;
	}
	

	public Collection<Borne> getBornes() {
		return bornes;
	}


	public Set<Carte> getEnsembleBottes() {
		return ensembleBottes;
	}





	
	
	
	
}

