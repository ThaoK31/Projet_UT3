package livre;

public class Ecran implements Livre{
	@Override
	public void ecrire(String chaine) {
		Livre.super.ecrire(chaine);
	}
}
