package main;

import banque.*;
import banque.paiement.*;
import util.Date;
public class TestBank {

	public static void main(String[] args) {
		System.out.println("_____________");
		System.out.println("    DATE");
		System.out.println("_____________");

		System.out.println(new Date(14,02,2007));
		Date date1 = new Date(10, 02, 2021);
		Date date2 = new Date(28, 9, 2025);
		System.out.println(date1);
		System.out.println(date1.toString());
		
		System.out.println("_____________");
		System.out.println("   COMPTE");
		System.out.println("_____________");
		
		Compte utilisateur = new Compte();
		Compte destinataire = new Compte();
		utilisateur.depot(200);
		utilisateur.afficherSolde();
		utilisateur.retrait(220);
		utilisateur.afficherSolde();
		utilisateur.virer(20, destinataire);
		System.out.println("destinataire");
		destinataire.afficherSolde();
		utilisateur.depot(100);
		utilisateur.afficherSolde();
		utilisateur.virer(60, destinataire);
		utilisateur.afficherSolde();
		System.out.println("destinataire");
		destinataire.afficherSolde();
		
		System.out.println("_____________");
		System.out.println("   CLIENT");
		System.out.println("_____________");
		
		Client client1 = new Client("Eren", date1);
		client1.getCompte(0).depot(100);
		client1.ajouterCompte();
		client1.getCompte(1).depot(200);
		client1.ajouterCompte();
		client1.getCompte(2).depot(5);
		client1.afficherBilan();
		client1.afficherSolde();
		
		System.out.println("_____________");
		System.out.println("   BANQUE");
		System.out.println("_____________");
		
		Banque banque1 = new Banque();
		banque1.ajouterClient("Eren", date2);
		banque1.getClient(0).getCompte(0).depot(100);
		banque1.getClient(0).ajouterCompte();
		banque1.getClient(0).getCompte(1).depot(1200);
		banque1.ajouterClient("Jaeger", date1);
		banque1.getClient(1).getCompte(0).depot(300);
		banque1.getClient(1).ajouterCompte();
		banque1.getClient(1).getCompte(1).depot(20);
		banque1.afficherBilan();
		
		System.out.println("_____________");
		System.out.println("   CARTE");
		System.out.println("_____________");

		int[] codeVrai = {5,2,2,4};
		int[] codeFaux = {5,2,6,4};
		Carte cartebleue = new Carte(codeVrai, date2, banque1, 0, 0);
		System.out.println("code :5224, code saisi :5264 "+cartebleue.codeValide(codeFaux));
		System.out.println("code :5224, code saisi :5224 "+cartebleue.codeValide(codeVrai));
		System.out.println("Solde Eren Initiale");
		banque1.getClient(0).getCompte(0).afficherSolde();
		System.out.println("Solde Jaeger Initiale");
		banque1.getClient(1).getCompte(0).afficherSolde();
		cartebleue.payer(banque1, 1, 0, 100);
		System.out.println("Solde Eren");
		banque1.getClient(0).getCompte(0).afficherSolde();
		System.out.println("Solde Jaeger");
		banque1.getClient(1).getCompte(0).afficherSolde();
		banque1.getClient(0).getCompte(0).depot(110);
		System.out.println(banque1.genererAutorisation(cartebleue, 100));
		
		System.out.println("_____________");
		System.out.println("  TERMINAL");
		System.out.println("_____________");

		Terminal term = new Terminal(banque1, 1, 0);
		Carte cartebleueJ = new Carte(codeVrai, date2, banque1, 1, 0);
		System.out.println("Solde Eren Initiale");
		banque1.getClient(0).getCompte(0).afficherSolde();
		System.out.println("Solde Jaeger Initiale");
		banque1.getClient(1).getCompte(0).afficherSolde();
		System.out.println(term.payer(200, cartebleueJ, banque1, 0, 0));
		System.out.println("Solde Eren");
		banque1.getClient(0).getCompte(0).afficherSolde();
		System.out.println("Solde Jaeger");
		banque1.getClient(1).getCompte(0).afficherSolde();
	}
}
