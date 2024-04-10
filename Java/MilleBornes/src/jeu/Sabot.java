package jeu;
import java.util.*;

import cartes.Carte;


public class Sabot implements Iterable <Carte> {
	private Carte[] cartes;
	private int nombreCartes=0;
	private int nombreOperations = 0;
	
	/* cartesMax =110 */
	
	public Sabot(int cartesMax) {
		this.cartes = new Carte[cartesMax];
	}
	
	public boolean estVide() {
		return nombreCartes==0;
	}
	
	
	public void ajouterCarte(Carte carte) {
		if (nombreCartes >= cartes.length) {
			throw new IllegalStateException();
		}
		cartes[nombreCartes] = carte;
		nombreCartes++;
		nombreOperations++;
	}
	
	public void ajouterFamilleCarte(Carte carte) {
		for (int i = 0; i<carte.getNombre(); i++) {
			ajouterCarte(carte);
		}
	}
	
	public void ajouterFamilleCarte(Carte... carte) {
		for (int i = 0; i<carte.length; i++) {
			ajouterFamilleCarte(carte[i]);
		}
	}
	

	

	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur=0;
		private int nombreOperationsReference=nombreOperations;
		private boolean nextEffectue=false;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur<nombreCartes;
		}

		@Override
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue=true;
				return carte;
			}
			else {
				throw new NoSuchElementException();
			}
			
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (nombreCartes<1 || !nextEffectue)
				throw new IllegalStateException();
			
			for (int i=indiceIterateur-1;i<nombreCartes-1;i++) {
				cartes[i]=cartes[i+1];
			}
			nextEffectue=false;
			indiceIterateur--;
			nombreCartes--;
			nombreOperationsReference++; nombreOperations++;
		}

		
		private void verificationConcurrence() {
			if(nombreOperations !=nombreOperationsReference)
				throw new ConcurrentModificationException();
		}
		
		
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	public Carte piocher() {
		Iterator<Carte> iterateur=iterator();
		System.out.print("je pioche ");
		Carte pioche=iterateur.next();
		iterateur.remove();
		return pioche;
	}
}







