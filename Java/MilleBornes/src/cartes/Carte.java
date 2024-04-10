package cartes;

public abstract class Carte {
	private int nombre;

	protected Carte(int nombre) {
		this.nombre = nombre;
	}

	public int getNombre() {
		return nombre;
	}


}
