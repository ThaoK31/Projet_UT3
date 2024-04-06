package jeu.cases;

import jeu.Oie;

public class CasePont extends Case{
	private String nom;
	public CasePont(int numCase, String nom) {
		super(numCase);
		this.nom = nom;
	}
	public Case arrivee(Oie oie) {
		int numCaseDest = 12;
		oie.ajouterMessage(" elle va sur la case "+nom+" et finit sur la case"+numCaseDest+"\n");
		return new Case(numCaseDest);
	}
}
