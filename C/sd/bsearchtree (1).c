#include "btree.h"
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>


struct _btree {
	BinaryTree * parent;
	BinaryTree * left;
	BinaryTree * right;
	int root;
};

BinaryTree *btree_create(){
	return NULL;
}



BinaryTree *btree_cons(BinaryTree *left, BinaryTree *right, int root){
	BinaryTree *t = malloc(sizeof(BinaryTree));
	t->parent = NULL;

	t->right = right;
	if(t->right)t->right->parent = t;

	t->left = left;
	if(t->left) t->left->parent = t;

	t-> root = root;
	return t;
}

int root(BinaryTree *t){
	
	return(t->root);
}

BinaryTree *parent(BinaryTree *t){
	assert(t->parent!=NULL);
	return t->parent;
}

BinaryTree *left(BinaryTree *t){
	assert(t->left!=NULL);
	return t->left;
}
 
BinaryTree *right(BinaryTree *t){
		assert(t->right!=NULL);
	return t->right;
}


//http://math.univ-lyon1.fr/irem/IMG/pdf/parcours_arbre_avec_solutions-2.pdf
//l’ordre infixe : on liste chaque sommet ayant un fils gauche la seconde 
//fois qu’on le voit et chaquesommet sans fils gauche la première fois qu’on 
//le voit. Ce qui donne ici : . . 

typedef int (*TreeMapFunctor)(int);


void depth_prefix(BinaryTree *t, TreeMapFunctor f){
	if(t==NULL) return;
  f(t->root);
  depth_prefix(t->left,f);
  depth_prefix(t->right,f);
}

void depth_infix(BinaryTree *t, TreeMapFunctor f){
	if(t==NULL) return;

	depth_infix(t->left, f);
	f(t->root);
	depth_infix(t->right, f);
}

void depth_postfix(BinaryTree *t, TreeMapFunctor f){
	if(t==NULL) return;

 depth_postfix(t->left, f);
 depth_postfix(t->right, f);
 f(t->root);
}

//affichage da valeur 
int print_i(int i){
	printf("%d\n", i);
	return i;
}

//Nouvelles implémentations
//  Binary search tree : renvoi 0 si l'arbre n'est pas vide 
bool btree_empty( BinaryTree *t) {
	return t == NULL;
}




//implémentation récursive de add
BinaryTree * bstree_add_rec(BinaryTree *t,int v){
	if(t == NULL){
		return t = btree_cons(btree_create(),btree_create(),v);
	}else {

		if(v < t->root) {
			t->left = bstree_add_rec(&(*t->left),v);
			t->left->parent = t;
		}
		else if (v > t->root){
			t->right = bstree_add_rec(&(*t->right),v);
			t->right->parent = t;
		}
	}
		
	return t;
}


//implémentation itérative de add
BinaryTree * bstree_add_iter(BinaryTree *t,int v){

	BinaryTree *cur = t;
	BinaryTree *par = NULL;

	while( btree_empty(cur)!=1  ){
		printf("%d\n",  root(cur));
		par = cur;
		if(root(cur)== v){
			return t;
		}
		cur = ( ((cur)->root > v) ? &(*cur->left) : &(*cur->right));
	}
	cur =  btree_cons(btree_create(),btree_create(),v);
	(cur)->parent = par;
	return t;
}




//version récursive de search 
bool bstree_search_rec( BinaryTree *t, int v) {
	if(btree_empty(t)==1) //Arbre vide
		return false;
	else if (root(t)==v) 
		return true;      //valeure trouvée
	else if (v < root(t) && !btree_empty(t->left)  ) 
		return (!btree_empty(left(t)) && bstree_search_rec(left(t),v));
	else if  (v > root(t) && !btree_empty(t->right)  ) 
		return (!btree_empty(right(t)) && bstree_search_rec(right(t),v));
	else return false; 
}

//version itérative de search
bool bstree_search_iter(BinaryTree *t, int v){
	while(!btree_empty(t)){
		if(v < root(t) ){
			if(btree_empty(t->left)) return false;
			t = left(t);
		}
		else if(v > root(t) ){
			if(btree_empty(t->right)) return false;
			t = right(t);
		}
		else break;	
	}
	return !btree_empty(t);
}

//Successeur d'un noeud
// revoie le Nœud y dont la clé est la plus grande qui soit inférieure à la clé de x
BinaryTree * sucessor(BinaryTree *x){
	BinaryTree *y = x->right;
	if(y){
		while(y->left) y = y->left;
	}else {
		y = x->parent;
		while(y && (x== y->right)){
			x = y;
			y = y->parent;
		}
	}
	return(y);
}





void permute(BinaryTree *tree, BinaryTree *from, BinaryTree *to) {
   
 	assert(!btree_empty(tree) && !btree_empty(from) && !btree_empty(to));

	if (from->parent) {
        if (from->parent->left == from)
            from->parent->left = to;
        else
            from->parent->right = to;
    } else
        tree = to;
        if (to->parent) {
        if (to->parent->left == to)
            to->parent->left = from;
        else
            to->parent->right = from;
    }


    BinaryTree *tmp = from->parent;
    from->parent = to->parent;
    to->parent = tmp;

    if (from->left)
        from->left->parent = to;

    if (to->left)
        to->left->parent = from;

    tmp = from->left;
    from->left = to->left;
    to->left = tmp;

    if (from->right)
        from->right->parent = to;

    if (to->right)
        to->right->parent = from;

    tmp = from->right;
    from->right = to->right;
    to->right = tmp;

}


void *remove_node(BinaryTree *t, BinaryTree * current) {
	assert(!btree_empty(t) && !btree_empty(current));
	BinaryTree *substitute;


	if (current->left == current->right) {
        substitute = NULL;
        printf("J'y suis 1\n");
    } else if (! current->left) {
        /* current a juste un enfant droit */
        substitute = current->right;

       printf("J'y suis 2\n");
    } else if (! current->right) {
        /* current a juste un enfant gauche */
        substitute = current->left;
        printf("J'y suis 3\n");
    } else {

    	printf("J'y suis 4\n");
        /* current a deux enfant*/
        BinaryTree * leaf;
        leaf = sucessor(current);
        permute(t, current, leaf);
        substitute = current->right;
    }

    //mise à jour de l'arbre
    if (substitute != NULL){
    	printf("J'y suis 5\n");
        substitute->parent = current->parent;
    }
    if ( ! current->parent ){
    	printf("J'y suis 6\n");
        t = substitute;
    }
 	else if (current->parent->left == current){
 		printf("J'y suis 7\n");
        current->parent->left = substitute;
    }
    else{
    	printf("J'y suis 8\n");
        current->parent->right = substitute;
    }
//To do : ajouter free() au bon endroit !

}



void * remove_tree(BinaryTree *t,int v){
	BinaryTree *current  = t;

	while(current){
		if (current->root == v) break;
		  current = ((current->root>v) ? current->left : current->right);
	}
		if(current){
		remove_node(t,current);
		}
	
}




int main(){

	
	printf("Session 1 \n" );

	BinaryTree  *b = btree_cons(btree_create(),btree_create(),1);
	BinaryTree *b_2 = btree_cons(b,b,3);
	printf("%d\n", b_2->left->root);
	printf("%d\n", b_2->right->root);
	printf("%d\n", b_2->root);
	printf("fusion\n");
	BinaryTree *b_3 = btree_cons(b_2,b_2,4);
	printf("%d\n", root(b_3));
	printf("%d\n", root(left(b_3)));
	printf("%d\n", root(left(left(b_3))));


	printf("Session 2 \n" );
	printf("Prefix:\n");
	depth_prefix(b_3, print_i);
	
	printf("Session 3 \n");
	printf("%d\n",btree_empty(b_2));

	
	BinaryTree  *bin_tree = bstree_add_rec(NULL, 22);
	bin_tree = bstree_add_rec(bin_tree , 7);
	bin_tree = bstree_add_rec(bin_tree , 29);
	bin_tree = bstree_add_rec(bin_tree , 17);
	bin_tree = bstree_add_rec(bin_tree , 25);
	bin_tree = bstree_add_rec(bin_tree , 32);
	bin_tree = bstree_add_rec(bin_tree , 9);
	bin_tree = bstree_add_rec(bin_tree , 21);
	bin_tree = bstree_add_rec(bin_tree , 23);
	bin_tree = bstree_add_rec(bin_tree , 28);
	bin_tree = bstree_add_rec(bin_tree , 30);
	bin_tree = bstree_add_rec(bin_tree , 8);
	bin_tree = bstree_add_rec(bin_tree , 16);
	bin_tree = bstree_add_rec(bin_tree , 27);
	bin_tree = bstree_add_rec(bin_tree , 26);

	depth_prefix(bin_tree , print_i);
	//depth_postfix(bin_tree , print_i);
	printf("vide ? %d\n",btree_empty(bin_tree) );

 	printf("Recherche récursive de 23 : %d \n",bstree_search_rec(bin_tree,23));
 	printf("Recherche récursive de 40 %d \n",bstree_search_rec(bin_tree,40));
 	printf("Recherche iterative de 23 %d \n",bstree_search_iter(bin_tree,23));
	printf("Recherche iterative de 23 %d \n",bstree_search_iter(bin_tree,40));

 

	printf("Successeur de 22?  %d\n",sucessor(bin_tree)->root );


	//fils orphelin
	//remove_tree(bin_tree,23);

	//fils ayant deux enfants
	//remove_tree(bin_tree,17);

	//fils ayant un enfant à droite
	//remove_tree(bin_tree,27);

	//fils ayant un enfant à gauche
	remove_tree(bin_tree,9);


	// printf("%d\n",root(bin_tree) );
	 depth_prefix(bin_tree, print_i);




	return 0;
}
