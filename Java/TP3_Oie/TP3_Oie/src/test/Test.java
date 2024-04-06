/**
 * 
 */
package test;

import jeu.JeuOie;

/**
 * @author rml3452a
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    JeuOie jeuOie = new JeuOie(3); 
	    jeuOie.ajouterOie("verte");
	    jeuOie.ajouterOie("jaune");
	    jeuOie.ajouterOie("bleu");
	    jeuOie.jouer();
	  }

}


