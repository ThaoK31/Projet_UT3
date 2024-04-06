package jeu.cases;

import jeu.Oie;

public class CaseLabyrinthe extends Case{
	private String nom;
	public CaseLabyrinthe(int numCase, String nom) {
		super(numCase);
		this.nom = nom;
	}
	public Case arrivee(Oie oie) {
		int numCaseDest = 30;
		oie.ajouterMessage(" elle va sur la case "+nom+" et finit sur la case "+numCaseDest+"\n");
		return new Case(numCaseDest);
	}
}
