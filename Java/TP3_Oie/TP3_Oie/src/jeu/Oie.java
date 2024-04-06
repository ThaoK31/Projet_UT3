/**
 * 
 */
package jeu;

import jeu.cases.Case;

/**
 * @author rml3452a
 *
 */
public class Oie {
	private String couleur;
	private Journal journal = new Journal();
	private De des;
	private Plateau plateau;
	private Case casePlateau;

	/**
	 * @param couleur
	 */
	public Oie(String couleur, Plateau plateau, De des) {
		super();		
		this.couleur = couleur;
		this.plateau=plateau;
		this.des=des;
		this.casePlateau = plateau.caseDebutPartie();
	}
	/**
	 * @return the couleur
	 */
	public String getCouleur() {
		return couleur;
	}
	/**
	 * @return the plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	public boolean action() {
		casePlateau = casePlateau.depart(this);
		journal.afficherMessage();
		String nomCase = casePlateau.getNom();
		return nomCase == null;
    	}
	
    public int lancerDe() {
        return des.lancer();
    }

    public void ajouterMessage(String message) {
    	journal.ajouterMessage(message + "\n");
    }
}


