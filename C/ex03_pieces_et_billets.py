#!/usr/bin/python3

def pieces_et_billets(somme_a_payer):
	liste_billets=[]
	liste_billets_dispos=[500,100,50,20,20,10,2,1]
	for valeur in liste_billets_dispos:
		i = 1
		nombre_de_billets=somme_a_payer//valeur
		while i<=nombre_de_billets:
			i+=1
			liste_billets.append(valeur)
			somme_a_payer-=valeur
			nombre_de_billets=somme_a_payer//valeur
	
	print(liste_billets)

def test_pieces_et_billets():
	print("test de la fonction pieces_et_billets")
	assert pieces_et_billets(762)==[500,200,50,10,2] , "erreur cas general"
	assert pieces_et_billets(0)==[] , "erreur valeur 0"
	assert pieces_et_billets(693)==[500,100,50,20,20,2,1] , "erreur memes billets"
	
	
	
	
	
	print("  OK")


test_pieces_et_billets()

