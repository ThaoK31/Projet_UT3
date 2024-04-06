package jeu.cases;

import jeu.Oie;

public class CaseHotel extends Case {

	private String nom;
	private int tour;
	public CaseHotel(int numCase,String nom) {
		super(numCase);
		this.nom=nom;
		this.tour=2;
	}
	protected Case arrivee(Oie oie) {
		if(tour==2) {
			oie.ajouterMessage(" elle va sur la case "+nom+" passe son tour "+tour+" fois");
		} else {
			oie.ajouterMessage(" elle reste à l'"+nom+" encore "+tour+" tour");
		}
		tour--;
		System.out.println(tour);
		return new Case(numCase);
	}
	
	public Case depart(Oie oie) {
		String couleurOie = oie.getCouleur();
		String nomCase = getNom();
		oie.ajouterMessage("L'oie "+couleurOie+" est sur la case "+nomCase);
		if(tour==0) {
			System.out.println("HELLO");
			int valeurDes = oie.lancerDe();
			int numCaseDest = valeurDes+numCase;
			oie.ajouterMessage(" elle fait "+valeurDes);
			return caseSuivante(oie, numCaseDest);
		} else
			return arrivee(oie);
			
	}
}
