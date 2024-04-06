package bataille;

import java.util.*;
import java.util.Map.Entry;

import protagoniste.*;

public class Grotte {
	private Map<Salle, List<Salle>> planGrotte = new LinkedHashMap<>();
	private Map<Salle, Bataille> batailles = new HashMap<>();
	private Set<Salle> sallesExplorees = new HashSet<>();
	private int numeroSalleDecisive;
	
	public void setNumeroSalleDecisive(int numeroSalleDecisive) {
		this.numeroSalleDecisive = numeroSalleDecisive;
	}
	
	public boolean salleDecisive(Salle salle) {
		return salle.getNumSalle()==numeroSalleDecisive;
	}
	
	public Salle ajouterSalle(ZoneDeCombat zoneDeCombat, Monstre<?>  ... monstre) throws ZoneDeCombatNonCompatibleException {
		
		Salle salle = new Salle(planGrotte.size()+1, zoneDeCombat);
        Bataille bataille = new Bataille();
        for(int i = 0; i<monstre.length ; i++) {
        	if (monstre[i].getZoneDeCombat()!=zoneDeCombat) {
                throw new ZoneDeCombatNonCompatibleException(zoneDeCombat, monstre[i].getZoneDeCombat());
            }
        	bataille.ajouter(monstre[i]);
        }
        List<Salle> liste = new ArrayList<>();
        planGrotte.put(salle, liste);
        batailles.put(salle, bataille);
		return salle;
	}
	public String afficherPlanGrotte() {
		StringBuilder affichage = new StringBuilder();
		for (Map.Entry<Salle, List<Salle>> entry : planGrotte.entrySet()) {
			Salle salle = entry.getKey();
		    List<Salle> acces = planGrotte.get(salle);
		    affichage.append("La " + salle + ".\nelle possede " + acces.size() + " acces : ");
		    for (Salle access : acces) {
		    	affichage.append(" vers la salle " + access);
		    }
		    Bataille bataille = batailles.get(salle);
		    @SuppressWarnings("unchecked")
			Camp<Monstre<?>> camp = bataille.getCampMonstres();
		    Monstre<?> monstre = camp.selectionner();
		    if (camp.nbCombattants() > 1) {
		    	affichage.append("\n" + camp.nbCombattants() + " monstres de type ");
		    } else {
		    	affichage.append("\nUn monstre de type ");
		    }
		    affichage.append(monstre.getNom() + " la protege.\n");
		    if (salle.getNumSalle() == numeroSalleDecisive) {
		    	affichage.append("C'est dans cette salle que se trouve la pierre de sang.\n");
		    }
		    affichage.append("\n");
		}
		return affichage.toString();
	}
	
	public void configurerAcces(int numSalle, int ... numSalleSups) {
		Salle salle = trouverSalle(numSalle);
		List<Salle> organisationSalles = planGrotte.get(salle);
		for(int numSalleSup : numSalleSups) {
			Salle salleAccessible = trouverSalle(numSalleSup);
			organisationSalles.add(salleAccessible);
		}
	}
	
	private Salle trouverSalle(int numSalle) {
		Set<Salle> salles = planGrotte.keySet();
		int indice = 1;
		Salle salle = null;
		for (Iterator<Salle> iterator = salles.iterator(); indice<=numSalle && iterator.hasNext();) {
			salle = iterator.next();
			indice++;
		}
		return salle;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grotte other = (Grotte) obj;
		if (batailles == null) {
			if (other.batailles != null)
				return false;
		} else if (!batailles.equals(other.batailles))
			return false;
		if (numeroSalleDecisive != other.numeroSalleDecisive)
			return false;
		if (planGrotte == null) {
			if (other.planGrotte != null)
				return false;
		} else if (!planGrotte.equals(other.planGrotte))
			return false;
		if (sallesExplorees == null) {
			if (other.sallesExplorees != null)
				return false;
		} else if (!sallesExplorees.equals(other.sallesExplorees))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batailles == null) ? 0 : batailles.hashCode());
		result = prime * result + numeroSalleDecisive;
		result = prime * result + ((planGrotte == null) ? 0 : planGrotte.hashCode());
		result = prime * result + ((sallesExplorees == null) ? 0 : sallesExplorees.hashCode());
		return result;
	}

	public Salle premiereSalle() {
		Salle salleTrouvee = trouverSalle(1);
		sallesExplorees.add(salleTrouvee);
		return salleTrouvee;
	}

	public Salle salleSuivante(Salle salle) {
		List<Salle> valide = new ArrayList<>();
		Random randomGenerateur = new Random();
		Set<Entry<Salle, List<Salle>>> suivante = planGrotte.entrySet();
		for (Iterator<Entry<Salle, List<Salle>>> iterator = suivante.iterator(); iterator.hasNext();) {
			Entry<Salle, List<Salle>> entry = iterator.next();
			if(entry.getKey()==salle) {
				System.out.println("SallesExplorees contient toutes les salles : "+sallesExplorees.containsAll(entry.getValue()));
				if(sallesExplorees.containsAll(entry.getValue())) sallesExplorees.clear();
				for(Salle access: entry.getValue()) {
					if(!(sallesExplorees.contains(access)))
						valide.add(access);
				}
			}
		}
		int num = randomGenerateur.nextInt(valide.size());
		Salle bonneSalle = valide.get(num);
		sallesExplorees.add(bonneSalle);
		return bonneSalle;
	}
}
