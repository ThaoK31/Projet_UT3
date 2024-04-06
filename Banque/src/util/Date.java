package util;


public class Date {
	private int jour, mois, an, heure, minute, seconde;

	public Date(int jour, int mois, int an) {
		this.jour = jour;
		this.mois = mois;
		this.an = an;
	}

	public int getJour() {
		return jour;
	}

	public int getMois() {
		return mois;
	}

	public int getAn() {
		return an;
	}

	public int getHeure() {
		return heure;
	}

	public int getMinute() {
		return minute;
	}

	public int getSeconde() {
	System.out.println();
		return seconde;
	}

	@Override
	
	public String toString() {
		return 	"[" + heure + ":" + minute + ":" + seconde + " " + jour + "/" + mois
				+ "/=" + an + "]";
	}
	public boolean after(Date valid) {
		if(valid.an > this.an)
			return true;
		else if(valid.an == this.an) {
			if(valid.mois > this.mois)
				return true;
			else if(valid.mois == this.mois && valid.jour >= this.jour) {
				return true;
			}
			return false;
		}
		return false;
	}
}

	
	



