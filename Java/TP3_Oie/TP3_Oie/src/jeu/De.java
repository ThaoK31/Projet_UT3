/**
 * 
 */
package jeu;
import java.util.Random;

/**
 * @author rml3452a
 *
 */
public class De {


	public int lancer() {
		int[] faces = {1, 2, 3, 4, 5, 6};
		Random rand= new Random();
		return faces[rand.nextInt(faces.length)];
		}
}
		


