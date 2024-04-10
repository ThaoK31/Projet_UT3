package jeu;
import cartes.Carte;

public interface IMain extends Iterable<Carte> {
	void prendre(Carte carte);
	void jouer(Carte carte);

}



