package utils;

import java.util.*;

import cartes.Carte;

public class Utils {
	
	private static Random rng = new Random();
	
	public static <T> T extraire(List<T> list) {
		int randomIndex = rng.nextInt(list.size());
		return list.remove(randomIndex);
	}

	public static <T> T extraireIterator(List<T> list) {
		int randomIndex = rng.nextInt(list.size());
		ListIterator<T> it = list.listIterator(randomIndex);
		T e = it.next();
		it.remove();
		return e;
	}
	
	public static <T> List<T> melanger(List<T> list) {
		List<T> melange = new LinkedList<>();
		while (!list.isEmpty())
			melange.add(extraire(list));
		return melange;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		if (l1.size() != l2.size()) return false;
		for (T e : l1)
			if (Collections.frequency(l1, e) != Collections.frequency(l2, e))
				return false;
		return true;
	}
	

	public static <T> List<T> rassembler(List<T> list){
		List<T> sortedList =new ArrayList<>();
		if (list.isEmpty()) {
			return sortedList;
		}
		for (ListIterator<T> it =list.listIterator();it.hasNext();) {
			T e =it.next();
			if (sortedList.contains(e)) {
				sortedList.add(sortedList.indexOf(e),e);
				}
			else sortedList.add(e);			
			}
		return sortedList;
		}
	
	public static <T> boolean verifierRassemblement(List<T> list) {
		List<T> seen = new LinkedList<>();
		if (list.isEmpty()) return true;
		
		T last = list.get(0);		
		ListIterator<T> it = list.listIterator(1);
		
		while (it.hasNext()) {
			T next = it.next();
		
			if (seen.contains(next)) return false;
			
			if (!next.equals(last)) {
				seen.add(last);
				last = next;
			}
		}
		return true;
	}

}