package protagoniste;

import java.util.Objects;

import bataille.Bataille;

public abstract class EtreVivant implements Comparable<EtreVivant>{
	private String nom;
	protected int forceDeVie;
	protected Bataille bataille;
	
	public EtreVivant(String nom, int forceDeVie) {
		this.forceDeVie = forceDeVie;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public int getForceDeVie() {
		return forceDeVie;
	}

	@Override
	public String toString() {
		return "EtreVivant [nom=" + nom + ", forceDeVie=" + forceDeVie + "]";
	}

	public void rejointBataille(Bataille bataille) {
		this.bataille = bataille;
	}
	
	public abstract void mourir();
	
	public boolean equals(Object obj) {
		if(obj instanceof EtreVivant) {
			EtreVivant etre = (EtreVivant) obj;
			return nom.equals(etre.nom);
		}
		return false;
	}
	
	public int compareTo(EtreVivant etreVivant) {
		return nom.compareTo(etreVivant.nom);
	}
	
	public int hashCode() {
		return Objects.hash(nom);
	}
}
