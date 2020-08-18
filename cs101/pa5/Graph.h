// Graph.h
// header file for Graph.c

#include<stdio.h>
#include"List.h"

#ifndef _GRAPH_H_INCLUDE_
#define _GRAPH_H_INCLUDE_

// macros
#define UNDEF -1 // -1 represents UNDEF value
#define NIL 0 // -2 represents NIL value
#define WHITE 1
#define GREY 2
#define BLACK 3

/*** Constructors-Destructors ***/
typedef struct GraphObj* Graph;

Graph newGraph(int n);

void freeGraph(Graph*   pG);

/*** Access functions ***/
int getOrder(Graph G);

int getSize(Graph G);

int getParent(Graph G, int u);

int getDiscover(Graph G, int u);

int getFinish(Graph G, int u);

/*** Manipulation procedures ***/

void addEdge(Graph G, int u, int v);

void addArc(Graph G, int u, int v);

void DFS(Graph G, List L);

/*** Other operations ***/
Graph transpose(Graph G);

Graph copyGraph(Graph G);

void printGraph(FILE* out, Graph G);

#endif
