package banque;

import util.Date;

public class Client {
	private String nom;
	private Date datenaissance;
	private Compte[] comptes= new Compte[100];
	private int nbComptes;
	
	public Client(String nom, Date datenaissance) {
		this.nom = nom;
		this.datenaissance = datenaissance;
		comptes[nbComptes]=new Compte();
		nbComptes++;
		
	}
	
	
	public Compte getCompte(int numero) {
		return comptes[numero];
	}
	
	public String getNom() {
		return nom;
	}

	public Date getDateNaissance() {
		return datenaissance;
	}

	public void afficherBilan() { 
		for (int i = 0; i < nbComptes; i++)
			comptes[i].afficherSolde();
		}
	
	public float soldeTotal() {
		float soldeTotal = 0;
		for (int i = 0; i < nbComptes; i++) {
			soldeTotal += comptes[i].getSolde();
		}
		return soldeTotal;		
	}  
	
	public void afficherSolde() {
		System.out.println("Votre solde total est de :"+ soldeTotal() +" euros. ");
		
	}
	public int ajouterCompte () {
		comptes[nbComptes]=new Compte();
		nbComptes++;
		return nbComptes;
	}
}

