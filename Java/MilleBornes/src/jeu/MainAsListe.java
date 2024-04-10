package jeu;

import java.util.Iterator;
import java.util.*;

import cartes.Carte;

public class MainAsListe implements IMain {
	private List<Carte> main = new LinkedList<>();

	public MainAsListe() {
	}
	
	@Override
	public void prendre(Carte carte) {
		main.add(carte);
	}

	@Override
	public void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}

	@Override
	public String toString() {
		return "MainAsListe [main=" + main + "]";
	}
	
	

}


