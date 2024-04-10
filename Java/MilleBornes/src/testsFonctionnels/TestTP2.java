package testsFonctionnels;
import java.util.*;

import cartes.*;
import jeu.*;
import utils.Utils;

public class TestTP2 {
	public static void main(String[] args) {
		Attaque atk1 = new Attaque(1, Type.ACCIDENT );
		Attaque atk2 = new Attaque(2, Type.ACCIDENT );
		Attaque atk3 = new Attaque(3, Type.CREVAISON );
		Parade parade1 = new Parade(Type.ACCIDENT, 2) ;
		Parade parade2 = new Parade(Type.ACCIDENT, 1) ;
		Parade parade3 = new Parade(Type.CREVAISON, 4) ;

		Botte botte1 = new Botte(8, Type.ACCIDENT);
		Botte botte2 = new Botte(4, Type.ACCIDENT); 
		Botte botte3 = new Botte(2, Type.CREVAISON); 

		System.out.println(atk1.equals(atk2)); //vrai
		System.out.println(atk2.equals(atk3)); //faux
		System.out.println(parade1.equals(parade2)); //vrai
		System.out.println(parade1.equals(parade3)); //faux
		System.out.println(botte1.equals(botte2)); //vrai
		System.out.println(botte3.equals(botte2)); //faux
		System.out.println(atk1.equals(parade1)); //faux
		System.out.println(atk3.equals(botte3)); //faux
		
		JeuDeCartes jeu= new JeuDeCartes();
		System.out.println(jeu.getListeCartes());		
		System.out.println(jeu.checkCount());

		List<Carte>listeCarteNonMelangee=jeu.getListeCartes(); 
		List<Carte>listeCartes=new ArrayList<>(listeCarteNonMelangee);
		
		
		System.out.println("taille liste melangé : "+listeCartes.size()+" taille liste non melangé : "+ listeCarteNonMelangee.size()); 

		System.out.println("Liste avant mélange\n"+listeCartes);
		listeCartes=Utils.melanger(listeCartes);		
		System.out.println("Liste après mélange\n"+listeCartes);
		
		System.out.println("Liste mélangée sans erreur (resultat attendu  true) : "+Utils.verifierMelange(listeCarteNonMelangee,listeCartes));
		
		System.out.println("taille liste melangé : "+listeCartes.size()+" taille liste non melangé : "+ listeCarteNonMelangee.size()); 
		
		System.out.println("Liste non rassemblée (resultat attendu  false) : "+Utils.verifierRassemblement(listeCartes));
		listeCartes=Utils.rassembler(listeCartes); 
		System.out.println("Liste après rassemblement\n"+listeCartes);
		System.out.println("Liste rassemblée sans erreur (resultat attendu  true) : "+Utils.verifierRassemblement(listeCartes)); 
		
		
	}

}
