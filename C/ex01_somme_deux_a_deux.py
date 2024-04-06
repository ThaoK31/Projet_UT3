#!/usr/bin/python3

def somme_deux_a_deux(liste):
	liste_somme=[]
	for i in range(1,len(liste)):
		liste_somme.append(liste[i-1]+liste[i])
	return(liste_somme)
		


def test_somme_deux_a_deux():
  print("test de la fonction somme_deux_a_deux")
  assert somme_deux_a_deux([1,2,3,4])==[3,5,7] , "erreur 1"
  assert somme_deux_a_deux([0,8])==[8] , "erreur 2"
  assert somme_deux_a_deux([0,0,0,1,2,10])==[0,0,1,3,12] , "erreur 3"
  assert somme_deux_a_deux([-4,-8,5,12,0])==[-12,-3,17,12] , "erreur 4"
  print("  OK")



test_somme_deux_a_deux()
