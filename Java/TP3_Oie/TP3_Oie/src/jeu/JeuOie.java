/**
 * 
 */
package jeu;

/**
 * @author rml3452a
 *
 */
public class JeuOie {
	private Oie[] oies;  
	private int nbOie = 0;  
	private Plateau plateau = new Plateau();
	private De de = new De();

	public JeuOie(int nbOieMax) {  
		oies = new Oie[nbOieMax];
	}

	public void ajouterOie(String couleur) {  
		if (nbOie < oies.length) {
			oies[nbOie] = new Oie(couleur, plateau, de);
			nbOie++;
		}
	}

	public void jouer() {
		boolean finPartie = nbOie<2;
		while(!finPartie) {
			for (int i=0; i<nbOie && !finPartie; i++){
				if(oies[i].action()) {
					System.out.println("L'oie "+oies[i].getCouleur()+" a gagné");
					finPartie=true;
				}
			}
		}

	}
}


