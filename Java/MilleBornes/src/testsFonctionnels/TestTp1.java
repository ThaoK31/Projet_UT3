package testsFonctionnels;
import java.util.*;
import cartes.*;
import jeu.*;


public class TestTp1 {
	public static void main(String[] args) {
		Sabot sabot = new Sabot(110);
		sabot.ajouterFamilleCarte(new Attaque(3, Type.ACCIDENT ), new Parade(Type.ACCIDENT, 3), new Botte(1, Type.ACCIDENT));
		for (Iterator<Carte> it = sabot.iterator(); it.hasNext();) {
			System.out.println(sabot.piocher());		
		}
		
		
		Sabot sabot2 = new Sabot(110);
		sabot2.ajouterFamilleCarte(new Attaque(3, Type.ACCIDENT ), new Parade(Type.ACCIDENT, 3), new Botte(1, Type.ACCIDENT));
		Iterator<Carte> iterator =sabot2.iterator();
		System.out.println("je Pioche "+iterator.next());
		iterator.remove();
//		sabot2.piocher();
//		sabot.ajouterFamilleCarte(new Botte(1,Type.ACCIDENT));
		System.out.println("je Pioche " +iterator.next());
		iterator.remove();
		System.out.println("je Pioche "+iterator.next());
		iterator.remove();
		System.out.println("je Pioche "+iterator.next());
		iterator.remove();
		System.out.println("je Pioche "+iterator.next());
		iterator.remove();
		System.out.println("je Pioche "+iterator.next());
		iterator.remove();
		System.out.println("je Pioche "+iterator.next());
		iterator.remove();
		
		
		
		

		


		
		
		
		

		
	}
}

	

