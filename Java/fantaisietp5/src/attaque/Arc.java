package attaque;

import protagoniste.ZoneDeCombat;

public class Arc extends Arme{
	private int nbFlechesRestantes;
	
	public Arc(int nbFlechesRestantes) {
		super(50,"Arc", ZoneDeCombat.AQUATIQUE, ZoneDeCombat.AERIEN, ZoneDeCombat.TERRESTRE);
		this.nbFlechesRestantes = nbFlechesRestantes;
	}
	
	public int utiliser() {
		this.nbFlechesRestantes--;
		return this.nbFlechesRestantes;
	}
}
