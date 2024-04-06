#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <stdbool.h>

typedef struct _List List;
typedef struct _Maillon Maillon;

typedef int (*ListMapFunctor)(int);
List *map(List *l,ListMapFunctor f);

typedef void(*ListReduceFunctor)(int, void *);
List *reduce(List *l,ListReduceFunctor f,void *userEnv);

List *push(List *l, int v);
List *pop_front(List *l);
void accumulate(int i, void* env);

void muliplicate(int i, void* env);

 struct _Maillon  {
	int value;
	struct _Maillon  *previous;
	struct _Maillon  *next;
};

//on ajoute une sentinel à la struct liste
struct _List {

	 Maillon *sentinel;
	 unsigned int size;

};

//le contructeur va exploiter la sentinelle 
List  *list(){
	List *l = malloc(sizeof(List));
	l->sentinel = malloc(sizeof(Maillon));
	l->sentinel->previous = l->sentinel->next = l->sentinel;
	l->size = 0;
	return l;
}


//Operateurs d’ajout et de suppression d’un élément à une liste doublement chaînée
List *push(List *l, int v){
	
	Maillon *new = malloc(sizeof(Maillon));
	new -> value = v;
	new -> previous = l -> sentinel;
	new -> next = l -> sentinel -> next;
	new -> next -> previous = new;
	l -> sentinel -> next = new;
	++(l->size);
	return(l);

}

//Opérateurs d'état du TAD Liste
bool list_empty (const List *l){
	return l->size == 0;
}


List *pop_front(List *l){
	assert(!list_empty(l));	
	Maillon *e;
	e = l->sentinel->next;
	l->sentinel->next = e->next;
	l->sentinel->next->previous = l->sentinel;
	--(l->size);
	free(e);
	return(l);
}

//traitement sur une collection
List *map(List *l, ListMapFunctor f){

for(Maillon *e = l->sentinel->next; e != l->sentinel; e =e->next){
	e -> value = f(e->value);
}
return l;
}

//affichage des éléments d'une liste
int print_i(int i){
	printf("%d\n", i);
	return i;
}

//* des éléments d'une liste
int multi_elt(int i){
	printf("%d\n", i*2);

	return i*2;
}

//Transformation sur une collection
List *reduce(List *l, ListReduceFunctor f, void *userEnv){
	for(Maillon *e = l->sentinel->next; e != l->sentinel; e =e->next){
		f(e->value,userEnv);
	}
	return(l);
}

void accumulate(int i, void* env){
	int *accumulator = (int *)env;
	*accumulator += i;
}


void muliplicate(int i, void* env){
	int *muliplicate = (int *)env;
	*muliplicate *=  i;
}


int main(){

	List* l = list();
	int i;
	for(i=1;i<=5;i++){
			l = push(l,i);
			printf("Size %d\n",l->size );
			printf("Previous %d\n" ,l->sentinel->previous->value);
			printf("Next %d\n" ,l->sentinel->next->value);
	}


	
	l = map(l,print_i);



	int sum = 0;
	reduce(l,accumulate,&sum);
	printf("The sum is : %d \n",sum );

	int multi = 1;
	reduce(l,muliplicate,&multi);
	printf("The multiplication is : %d \n",multi );
	

return(0);
}





/*


*/