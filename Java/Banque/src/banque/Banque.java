package banque;
import java.time.LocalDate;

import banque.paiement.Carte;
import util.Date;

public class Banque {
	private int nbClients = 0;
	private int maxClient = 100;
	private Client[] client = new Client[maxClient];

	
	public int ajouterClient(String nom, Date datenaissance) {
		if (nbClients < maxClient) {
			client[nbClients] = new Client(nom, datenaissance);
			nbClients++;
		}
		else
			System.out.println("La Banque a atteint son maximum de client (100)");
		
		return nbClients;
	}
	
	
	public  Client getClient(int numeroClient) {
		return client[numeroClient];
	}
	
	
	public void afficherBilan() {
		for (int i = 0; i < nbClients; i++) 
			client[i].afficherBilan();
	}
	
	
	public String genererAutorisation(Carte cb, float montant) {
		Date valid = cb.getDateValid();
		LocalDate now = LocalDate.now();
		Date dNow = new Date(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
		if(montant>1000 || dNow.after(valid))
			return "OK";
		return "Erreur";
	}
	
	
}
