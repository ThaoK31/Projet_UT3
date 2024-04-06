package protagoniste;

import java.util.Arrays;
import java.util.Iterator;

import attaque.Pouvoir;
import bataille.Bataille;

public class Monstre< T extends Pouvoir> extends EtreVivant{
	private T attaques[];
	private ZoneDeCombat zoneDeCombat;
	private Domaine domaine;
	private GestionAttaque gestionAttaque;
	
	@SafeVarargs
	public Monstre(String nom, int forceDeVie, ZoneDeCombat zoneDeCombat, Domaine domaine, T... attaques) {
		super(nom,forceDeVie);
		this.zoneDeCombat = zoneDeCombat;
		this.domaine = domaine;
		this.attaques = attaques;
		this.gestionAttaque = new GestionAttaque(attaques);
	}

	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}

	public Domaine getDomaine() {
		return domaine;
	}
	
	public class GestionAttaque implements Iterator<T>{
		private T[] attaquesPossibles;
		private int nbAttaquesPossibles;
		
		@SafeVarargs
		public GestionAttaque(T... attaques) {
			this.attaquesPossibles = attaques;
			this.nbAttaquesPossibles = attaques.length;
		}
		
		public boolean hasNext() {
			boolean verify = false;
			for(int i=0; i<this.nbAttaquesPossibles; i++) {
				if(attaquesPossibles[i].isOperationnel() == false) {
					this.attaquesPossibles[i] = this.attaquesPossibles[nbAttaquesPossibles - 1];
					nbAttaquesPossibles--;
					i--;
				} else {
					verify = true;
				}
			}
			return verify;
		}
		
		public T next() {
			int random = (int)Math.random() * ((nbAttaquesPossibles -1) - 0);
			return this.attaquesPossibles[random];
		}
		
	}
	
	public void entreEnCombat() {
		for(int i=0; i<Monstre.this.attaques.length;i++) {
			this.attaques[i].regenererPouvoir();
		}
		this.gestionAttaque = new GestionAttaque(this.attaques);
	}
	
	public T attaque() {
		if(this.gestionAttaque.hasNext()) {
			T a = this.gestionAttaque.next();
			a.utiliser();
			return a;
		}
		return null;
	}
	
	public void rejointBataille(Bataille bataille) {
		bataille.ajouter(this);;
	}
	
	public void mourir() {
		this.bataille.eliminer(this);

	}

	@Override
	public String toString() {
		return "Monstre [attaques=" + Arrays.toString(attaques) + ", zoneDeCombat=" + zoneDeCombat + ", domaine="
				+ domaine + ", gestionAttaque=" + gestionAttaque + ", forceDeVie=" + forceDeVie + ", bataille="
				+ bataille + ", getNom()=" + getNom() + ", getForceDeVie()=" + getForceDeVie() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
