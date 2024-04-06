package bataille;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import protagoniste.EtreVivant;

public class Camp<T extends EtreVivant> {
	List<EtreVivant> liste = new LinkedList<>(); 
	public void ajouter(EtreVivant etreVivant) {
		if(! liste.contains(etreVivant)) {
			liste.add(etreVivant);
		}
	}
	
	public void eliminer(EtreVivant etreVivant) {
		etreVivant.mourir();
	}
	
	public String toString() {
		return liste.toString();
	}
	
	public ListIterator<EtreVivant> iterator(){
		ListIterator<EtreVivant> iterateur = liste.listIterator();
		return iterateur;
	}
	public int nbCombattants() {
		return liste.size();
	}

	public T selectionner() {
		Random randomGenerateur = new Random();
		int numeroCombattant = randomGenerateur.nextInt(liste.size());
		return (T) liste.get(numeroCombattant);
	}
}
