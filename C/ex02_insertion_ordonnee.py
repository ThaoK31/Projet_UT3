#!/usr/bin/python3

def insertion_ordonnee(liste,valeur):	
	
	if len(liste)==0:
		liste.append(valeur)
		return(liste)
		
	elif len(liste)==1:
		if liste[0]<=valeur:
			liste.append(valeur)
			return(liste)
		else:
			liste.insert(0,valeur)
			return(liste)
			
	elif valeur>=liste[-1]:
		liste.append(valeur)
		return(liste)
		
	elif valeur<=liste[0]:
		liste.insert(0,valeur)
		return(liste)
	
	else:
		for i in range(1,len(liste)):
			if liste[i-1]<=valeur<=liste[i]:
				liste.insert(i,valeur)
				return(liste)
				


def test_insertion_ordonnee():
	print("test de la fonction insertion_ordonnee")
	# tests à compléter
	assert insertion_ordonnee([-3,2,7,11],9)==[-3,2,7,9,11] , "erreur simple"
	assert insertion_ordonnee([-7],12)==[-7,12] , "erreur une valeur"
	assert insertion_ordonnee([],4)==[4] , "erreur liste vide"
	assert insertion_ordonnee([1,3,7],3)==[1,3,3,7] , "erreur meme valeur"
	assert insertion_ordonnee([4,8,17],20)==[4,8,17,20] , "erreur fin"
	assert insertion_ordonnee([2,3,9],1)==[1,2,3,9] , "erreur debut"
  
	print("  OK")


test_insertion_ordonnee()
