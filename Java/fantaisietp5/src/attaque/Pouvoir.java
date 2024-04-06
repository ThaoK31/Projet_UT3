package attaque;

public abstract class Pouvoir extends ForceDeCombat{
	private int nbUtilisationPouvoir;
	private int nbUtilisationPouvoirInitial;
	
	public Pouvoir(int pointDeDegat, String nom, int nbUtilisationPouvoir) {
		super(pointDeDegat,nom);
		this.nbUtilisationPouvoir = nbUtilisationPouvoir;
	}
	
	public void regenererPouvoir() {
		this.nbUtilisationPouvoir = this.nbUtilisationPouvoirInitial;
	}
	
	public int utiliser() {
		if(this.nbUtilisationPouvoir>0) {
			this.nbUtilisationPouvoir--;
		}else {
			operationnel = false;
		}
		
		return this.getPointDeDegat();
	}

}
