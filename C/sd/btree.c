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


typedef int (*TreeMapFunctor)(int);


void depth_prefix(BinaryTree *t, TreeMapFunctor f){
	if(t==NULL) return;
  f(t->root);
  depth_prefix(t->left,f);
  depth_prefix(t->right,f);
}

void depth_prefix_ite(BinaryTree *t, TreeMapFunctor f){
   BinaryTree *temp=t->left;
  while(temp!=NULL){
	temp->left=NULL;
	temp->right=NULL;
	printf("%d\n", temp->root);
	temp=temp->parent;
  }
}	

void depth_postfix(BinaryTree *t, TreeMapFunctor f){
	if(t==NULL) return;
  depth_postfix(t->left, f);
  depth_postfix(t->right, f);
  f(t->root);
}

void depth_infix(BinaryTree *t, TreeMapFunctor f){
	if(t==NULL) return;
  depth_postfix(t->left, f);
  f(t->root);
  depth_postfix(t->right, f);
}
	

//affichage da valeur 
int print_i(int i){
	printf("%d\n", i);
	return i;
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
	printf("Prefix iteratif:\n");
	depth_prefix_ite(b_3, print_i);
	printf("\nInfix:\n");
	depth_infix(b_3, print_i);
	printf("\nPostfix:\n");
	depth_postfix(b_3, print_i);
	return 0;
}
