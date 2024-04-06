package banque.paiement;
import banque.*;


import java.util.Scanner;

import banque.*;

public class Terminal {
	private int numeroClient, numeroCompte;
	
	public Terminal(Banque b, int numeroClient, int numeroCompte) {
		this.numeroClient = numeroClient;
		this.numeroCompte = numeroCompte;
	}
	
	public String payer(float montant, Carte cb, Banque banque, int numeroClient, int numeroCompte) {
		Scanner sc = new Scanner(System.in);
		int code[] = new int[4];
		for (int i = 0; i<3 ; i++) {
			System.out.println("Saisissez votre mot de passe a 4 chiffres : ");
			int entier = sc.nextInt();
			for (int j = 3; j >= 0; j--) {
				code[j] = entier%10;
				entier/=10;
			}
			if(cb.codeValide(code)) {
				Banque bank = cb.getBanque();
				if(bank.genererAutorisation(cb, montant).equals("OK")) {
					cb.payer(bank, numeroClient, numeroCompte, montant);
					return "OK";
				}
			}
		}
		return "Erreur";
	}
}
