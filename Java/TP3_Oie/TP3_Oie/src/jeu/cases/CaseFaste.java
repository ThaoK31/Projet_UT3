package jeu.cases;

import jeu.Oie;

public class CaseFaste extends Case{
	private String nom;
	public CaseFaste(int numCase, String nom) {
		super(numCase);
		this.nom = nom;
	}
	public Case arrivee(Oie oie) {
		oie.ajouterMessage(" elle va sur la case "+nom+"\n");
		int valeurDes = oie.lancerDe();
		int numCaseDest = valeurDes+numCase;
		oie.ajouterMessage(" elle fait "+valeurDes);
		return caseSuivante(oie, numCaseDest);
	}
}
