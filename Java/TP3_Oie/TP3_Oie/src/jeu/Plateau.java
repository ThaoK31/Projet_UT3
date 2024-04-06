/**
 * 
 */
package jeu;
import jeu.cases.*;

/**
 * @author rml3452a
 *
 */
public class Plateau {
	private int nb_cases=64;
	private Case cases[]= new Case[nb_cases];
	/**
	 * 
	 */
	public Plateau() {
		super();
		
		for (int i=0;i<nb_cases;i++) {
			cases[i]=new Case(i);
			
		}
		intialisationCasesSpecifique();
	}
	
	public Case donnerCase(int numCase) {
		return cases[numCase]; 		
	}
	
	public Case caseDebutPartie() {
		return cases[0];
	}
	
	private void intialisationCasesSpecifique() {
		cases[9]=new CaseFaste(9,"Faste");
		cases[18]=new CaseFaste(18,"Faste");
		cases[27]=new CaseFaste(27,"Faste");
		cases[36]=new CaseFaste(36,"Faste");
		cases[45]=new CaseFaste(45,"Faste");
		cases[54]=new CaseFaste(54,"Faste");
		
		cases[6]=new CasePont(6,"Pont");
		cases[19]=new CaseHotel(19,"Hotel");
		cases[31]=new CasePuit(31,"Puit");
		cases[58]=new CaseMort(58,"Mort");
		cases[42]=new CaseLabyrinthe(42,"Labyrinthe");
		cases[52]=new CasePrison(52,"Prison");
		
	}

	
}
