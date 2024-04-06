package jeu.cases;

import jeu.Oie;

public class CaseMort extends Case{
	private String nom;
	public CaseMort(int numCase, String nom) {
		super(numCase);
		this.nom = nom;
	}
	public Case arrivee(Oie oie) {
		int numCaseDest = 1;
		oie.ajouterMessage(" elle va sur la case "+nom+" meurt et revient à la case "+numCaseDest+"\n");
		return new Case(numCaseDest);
	}
}
