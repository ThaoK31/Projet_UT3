package attaque;

public abstract class ForceDeCombat {
	private int PointDeDegat;
	private String nom;
	protected boolean operationnel = true;
	
	public ForceDeCombat(int pointDeDegat, String nom) {
		this.PointDeDegat = pointDeDegat;
		this.nom = nom;
	}
	
	
	public int getPointDeDegat() {
		return PointDeDegat;
	}

	public String getNom() {
		return nom;
	}

	public boolean isOperationnel() {
		return operationnel;
	}

	public int utiliser() {
		return this.PointDeDegat; 
	}

	@Override
	public String toString() {
		return "ForceDeCombat [PointDeDegat=" + PointDeDegat + ", nom=" + nom + ", operationnel=" + operationnel + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
