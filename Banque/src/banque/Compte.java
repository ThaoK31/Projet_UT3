package banque;

public class Compte {
	private float solde;
	
	public void depot(float valeur) {
		solde=solde+valeur;
	}
	public void retrait(float valeur) {
		if((solde-valeur)<0) {
			System.out.println("Vous êtes à découvert.");
		}
		solde=solde-valeur;
	}
	public float getSolde() {
		return this.solde;
	}
	public void afficherSolde () {
		System.out.println("Solde : "+ solde +" ");
	}
	
	public void virer(float valeur,Compte destinataire) {
		if((solde-valeur)>0) {
			retrait(valeur);
			destinataire.depot(valeur);
			System.out.println("Votre virement a ete effectue.");
		}
		else
			System.out.println("Virement impossible, vous n'avez pas assez d'argent sur votre compte.");
	}

}
