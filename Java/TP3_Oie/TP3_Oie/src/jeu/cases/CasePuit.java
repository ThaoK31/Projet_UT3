package jeu.cases;

import jeu.Oie;

public class CasePuit extends Case{
	private String nom;
	private Oie oieBloque = null;
	public CasePuit(int numCase, String nom) {
		super(numCase);
		this.nom = nom;
	}
	protected Case arrivee(Oie oie) {
		if(oieBloque==null) {
			oie.ajouterMessage(" elle va sur la case "+nom+" et attend l'arrivé d'une autre oie");
			oieBloque=oie;
		} else if (oieBloque.equals(oie)){
			oie.ajouterMessage(" elle reste bloqué sur la case "+nom);
		} else {
			oie.ajouterMessage(" elle va sur la case "+nom+" et débloque l'oie "+oieBloque.getCouleur());
			oieBloque = null;
			
		}
		return new Case(numCase);
	}
	public Case depart(Oie oie) {
		String couleurOie = oie.getCouleur();
        String nomCase = getNom();
        oie.ajouterMessage("L'oie " + couleurOie + " est sur la case "+ nomCase);
		if (oieBloque.equals(null)) {
			int valeurDes = oie.lancerDe();
	        oie.ajouterMessage(" elle fait " + valeurDes);
	        return caseSuivante(oie, valeurDes + numCase);
		}
		else {
		return arrivee(oie);
		}
	}
}
