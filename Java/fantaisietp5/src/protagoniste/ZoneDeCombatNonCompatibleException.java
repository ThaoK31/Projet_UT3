package protagoniste;

public class ZoneDeCombatNonCompatibleException extends Exception {
	public ZoneDeCombatNonCompatibleException(ZoneDeCombat z1, ZoneDeCombat z2) {
		System.out.println("La zone de combat de la salle et de type "+z1+", alors que celle du monstre est "+z2);
	}
}
