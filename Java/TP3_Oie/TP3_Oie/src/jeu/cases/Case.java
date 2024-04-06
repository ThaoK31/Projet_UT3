/**
 * 
 */
package jeu.cases;

import jeu.Oie;
import jeu.Plateau;

/**
 * @author rml3452a
 *
 */
public class Case {
	protected int numCase;
		

	/**
	 * @param numCase
	 */
	public Case(int numCase) {
		this.numCase = numCase;
	}
	
	public String getNom() {
		return ("numero "+numCase);
	}
	
	public Case depart(Oie oie) {
		String couleurOie = oie.getCouleur();
        String nomCase = getNom();
        oie.ajouterMessage("L'oie " + couleurOie + " est sur la case "+ nomCase);
        int valeurDes = oie.lancerDe();
        oie.ajouterMessage(" elle fait " + valeurDes);
        return caseSuivante(oie, valeurDes + numCase);
	}
	
	protected Case arrivee(Oie oieEnJeu) {
		String nomCase = getNom();
        oieEnJeu.ajouterMessage(" elle va sur la case " + nomCase +" \n");
        return this;
	}
	
	protected Case caseSuivante(Oie oie, int numCaseDestination){
		Plateau plateau = oie.getPlateau();
		if(numCaseDestination>63) {
			int retour=numCaseDestination-63;
			numCaseDestination=63-retour;
		}
        Case caseDestination = plateau.donnerCase(numCaseDestination);
		if(caseDestination==null) return null;
        return caseDestination.arrivee(oie);
	}
}
