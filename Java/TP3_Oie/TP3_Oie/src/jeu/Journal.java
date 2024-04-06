/**
 * 
 */
package jeu;

/**
 * @author rml3452a
 *
 */
public class Journal {
	private String message="";


	public void ajouterMessage(String message) {
		this.message+=message;
	}
	
	public void afficherMessage() {
		System.out.println(this.message+" ");
		this.message="";
	}
}

	