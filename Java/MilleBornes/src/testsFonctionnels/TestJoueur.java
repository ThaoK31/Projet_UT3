package testsFonctionnels;

import java.util.*;

import cartes.*;
import jeu.*;

public class TestJoueur {
	public static void main(String[] args) {
		
		Borne borne10=new Borne(1, 10);
		Borne borne20=new Borne(1,20);
		Borne borne30=new Borne(1,30);
		
		
		Joueur joueur1=new Joueur("Joueur1");
		
		System.out.println(joueur1.donnerKmParcourus()+" (0 km attendu )");// attendu 0
		joueur1.getZoneJoueur().getBornes().add(borne10); 
		System.out.println(joueur1.donnerKmParcourus()+" (10 km attendu )"); // attendu 10
		joueur1.getZoneJoueur().getBornes().add(borne20);
		System.out.println(joueur1.donnerKmParcourus() +" (30 km attendu )");// attendu 30
		joueur1.getZoneJoueur().getBornes().add(borne30);
		System.out.println(joueur1.donnerKmParcourus()+" (60 km attendu )");// attendu 60
	}
}
