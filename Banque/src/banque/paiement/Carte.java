package banque.paiement;
import banque.*;
import util.Date;

public class Carte {
	private int code[] = new int[4];
	private Banque banque;
	private int numeroClient, numeroCompte;
	private int  compteur = 3;
	private Date dateValide;
	
	
	
	public Carte(int[] code, Date dateValide, Banque banque, int numeroClient, int numeroCompte) {
		this.code = code;
		this.banque = banque;
		this.numeroClient = numeroClient;
		this.numeroCompte = numeroCompte;
		this.dateValide = dateValide;
	}

	
	public boolean codeValide(int[] code) {
		return code[0]==this.code[0] && code[1]==this.code[1] && code[2]==this.code[2] && code[3]==this.code[3];
	}

	public void payer(Banque b, int numeroClient, int numeroCompte, float montant) {
		Compte compte = banque.getClient(this.numeroClient).getCompte(this.numeroCompte);
		float solde = compte.getSolde();
		Compte compt = b.getClient(numeroClient).getCompte(numeroCompte);
		if((solde-montant)>=0) {
			compte.retrait(montant);
			compt.depot(montant);
		} 
		else
			System.out.println("Paiement impossible, vous n'avez pas assez d'argent sur votre compte.");
	}

		
	public Date getDateValid() {
		return dateValide;
	}
	
	public Banque getBanque() {
		return banque;
	}
}
