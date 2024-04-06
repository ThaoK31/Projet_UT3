#include "bstree.h"
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "queue.h"

void bstree_remove_node(ptrBinarySearchTree *t, ptrBinarySearchTree current);

/*------------------------  BSTreeType  -----------------------------*/

struct _bstree {
    BinarySearchTree *parent;
    BinarySearchTree *left;
    BinarySearchTree *right;
    int root;
};

/*------------------------  BaseBSTree  -----------------------------*/

BinarySearchTree *bstree_create() {
    return NULL;
}

/* This constructor is private so that we can maintain the oredring invariant on
 * nodes. The only way to add nodes to the tree is with the bstree_add function
 * that ensures the invariant.
 */
BinarySearchTree *bstree_cons(BinarySearchTree *left, BinarySearchTree *right, int root) {
    BinarySearchTree *t = malloc(sizeof(struct _bstree));
    t->parent = NULL;
    t->left = left;
    t->right = right;
    if (t->left != NULL)
        t->left->parent = t;
    if (t->right != NULL)
        t->right->parent = t;
    t->root = root;
    return t;
}

void bstree_delete(ptrBinarySearchTree *t) {
    while (!bstree_empty(*t))
        bstree_remove_node(t, *t);
}

bool bstree_empty(const BinarySearchTree *t) {
    return t == NULL;
}

int bstree_root(const BinarySearchTree *t) {
    assert(!bstree_empty(t));
    return t->root;
}

BinarySearchTree *bstree_left(const BinarySearchTree *t) {
    assert(!bstree_empty(t));
    return t->left;
}

BinarySearchTree *bstree_right(const BinarySearchTree *t) {
    assert(!bstree_empty(t));
    return t->right;
}

BinarySearchTree *bstree_parent(const BinarySearchTree *t) {
    assert(!bstree_empty(t));
    return t->parent;
}

/*------------------------  BSTreeDictionary  -----------------------------*/

/* Obligation de passer l'arbre par référence pour pouvoir le modifier */
void bstree_add(ptrBinarySearchTree *t, int v) {
    BinarySearchTree *cur=t;
    BinarySearchTree *par=NULL;
	while (cur) 
    {
        *par=*cur;
        if ((cur)->root==v)
        {
                break;
        }
        t=(((*t)->root>v)? &((*t)->left):&((*t)->right));      
    }
    t=cons(NULL, NULL, v);
    (*t)->parent=par;
}

bool bstree_search(const BinarySearchTree *t, int v) {
    if(bstree_empty(t))
        return false;
    if (root(t)==v)
    {
        return true;
    }
    if(v<root(t))
        return !bstree_empty(left(t))&&bstree_search(left(t), v);
    else
        return !bstree_empty(right(t))&&bstree_search(right(t), v);
}

BinarySearchTree *bstree_successor(const BinarySearchTree *x) {
    BinarySearchTree (*y)=x->right;
    if (y) {
        while (y->left)
        y=y->left;
    } else {
        y=x->parent;
        while(y&&(x==y->right)) {
            x=y;
            y=y->parent;
        }
    }
    return (y);
}

BinarySearchTree *bstree_predecessor(const BinarySearchTree *x) {
    BinarySearchTree (*y)=x->left;
    if (y) {
        while (y->right)
        y=y->right;
    } else {
        y=x->parent;
        while(y&&(x==y->left)) {
            x=y;
            y=y->parent;
        }
    }
    return (y);
}

void bstree_swap_nodes(ptrBinarySearchTree *tree, ptrBinarySearchTree from, ptrBinarySearchTree to) {
    assert(!bstree_empty(*tree) && !bstree_empty(from) && !bstree_empty(to));
    (void)tree; (void)from; (void)to;
}

// t -> the tree to remove from, current -> the node to remove
void bstree_remove_node(ptrBinarySearchTree *t, ptrBinarySearchTree current) {
    assert(!bstree_empty(*t) && !bstree_empty(current));
    BinarySearchTree **m;
    if (!current->parent)
        m=t;
    else if(current->parent->left==current)
        m=&(current->parent->left);
    else
        m=&(current->parent->right);
    if(current->left && current->right) {
        BinarySearchTree *s;
        s = bstree_successor(current);
        current->root=s->root;
        current=s;
    }
    if (!current->left)
    {
        *m = current->right;
        if(*m)
            (*m)->parent=current->parent;
    } else {
        *m = current->left;
        (*m)->parent=current->parent;
    }
    free(current);
    
}

void bstree_remove(ptrBinarySearchTree *t, int v) {
    BinarySearchTree *node =*t;
    while (node&&node->root!=v)
        node = ((node->root>v)?node->left:node->right);
    if(node)
        bstree_remove_node(t, node);
}

/*------------------------  BSTreeVisitors  -----------------------------*/

void bstree_depth_prefix(const BinarySearchTree *t, OperateFunctor f, void *userData) {
    if(bstree_empty(t))
        return;
    f(t->root, NULL);
    bstree_depth_prefix(t->left, f, NULL);
    bstree_depth_prefix(t->right, f, NULL);
}

void bstree_depth_infix(const BinarySearchTree *t, OperateFunctor f, void *userData) {
    if(bstree_empty(t))
        return;
    bstree_depth_prefix(t->left, f, NULL);
    f(t->root, NULL);
    bstree_depth_prefix(t->right, f, NULL);
}

void bstree_depth_postfix(const BinarySearchTree *t, OperateFunctor f, void *userData) {
    if(bstree_empty(t))
        return;
    bstree_depth_prefix(t->left, f, NULL);
    bstree_depth_prefix(t->right, f, NULL);
    f(t->root); 
}

void bstree_iterative_depth_infix(const BinarySearchTree *t, OperateFunctor f, void *userData) {
    BinarySearchTree *current = t;
    BinarySearchTree *next =bstree_parent(t);
    BinarySearchTree *prev =bstree_parent(t);

    while (!bstree_empty(current)) {
        if(prev==bstree_parent(current)) {
            prev=current; next=bstree_left(current);
        }
        if(bstree_empty(next)||prev==bstree_left(current)) {
            f(bstree_root(current), NULL); prev=current; next = bstree_right(current);
        }
        if(bstree_empty(next)||prev==bstree_right(current)) {
            prev=current; next=bstree_parent(current);
        }
        current=next;
    }
}

void bstree_iterative_breadth_prefix(const BinarySearchTree *t, OperateFunctor f, void *userData) {
    (void)t; (void) f; (void)userData;
}

/*------------------------  BSTreeIterator  -----------------------------*/

struct _BSTreeIterator {
    /* the collection the iterator is attached to */
    const BinarySearchTree *collection;
    /* the first element according to the iterator direction */
    const BinarySearchTree *(*begin)(const BinarySearchTree *);
    /* the current element pointed by the iterator */
    const BinarySearchTree *current;
    /* function that goes to the next element according to the iterator direction */
    BinarySearchTree *(*next)(const BinarySearchTree *);
};

/* minimum element of the collection */
const BinarySearchTree *goto_min(const BinarySearchTree *e) {
	(void)e;
	return NULL;
}

/* maximum element of the collection */
const BinarySearchTree *goto_max(const BinarySearchTree *e) {
	(void)e;
	return NULL;
}

/* constructor */
BSTreeIterator *bstree_iterator_create(const BinarySearchTree *collection, IteratorDirection direction) {
	(void)collection; (void)direction;
	return NULL;
}

/* destructor */
void bstree_iterator_delete(ptrBSTreeIterator *i) {
    free(*i);
    *i = NULL;
}

BSTreeIterator *bstree_iterator_begin(BSTreeIterator *i) {
    i->current = i->begin(i->collection);
    return i;
}

bool bstree_iterator_end(const BSTreeIterator *i) {
    return i->current == NULL;
}

BSTreeIterator *bstree_iterator_next(BSTreeIterator *i) {
    i->current = i->next(i->current);
    return i;
}

const BinarySearchTree *bstree_iterator_value(const BSTreeIterator *i) {
    return i->current;
}

