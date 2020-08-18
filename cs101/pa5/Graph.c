// Graph.c
// implement file for Graph ADT

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"
#include"List.h"

// structs

// private GraphObj type
typedef struct GraphObj {
   List *neighbors;
   int *color;
   int *P; // parent
   int *d; // discover time
   int *f; // finish time

   int order; // number of vertices
   int size; // number of edges
}GraphObj;

// private Node tpye
typedef GraphObj* Graph;

// constructor Destructors

// newGraph()
// returns reference to new empty Graph object
Graph newGraph(int n) {
   // allocate memory for vals
   Graph G = malloc(sizeof(GraphObj));
   G->neighbors = calloc(n+1, sizeof(List));
   G->color = calloc(n+1, sizeof(int));
   G->P = calloc(n+1, sizeof(int));
   G->d = calloc(n+1, sizeof(int));
   G->f = calloc(n+1, sizeof(int));
   // initialize vals
   for(int i = 1; i < n+1; ++i) {
      G->neighbors[i] = newList();
      G->P[i] = NIL;
      G->d[i] = UNDEF;
      G->f[i] = UNDEF;
      G->color[i] = WHITE;
   }
   G->order = n;
   G->size = 0;
   return G;
}
// freeGraph()
// Frees all heap memory associated with Graph *pG, and set *pG to NULL
void freeGraph(Graph* pG) {
   if(pG != NULL && *pG != NULL) {
      Graph T = *pG;
   for(int i = 1; i <= getOrder(T); i++) {
      freeList(&T->neighbors[i]);
   }
   free(T->neighbors);
   free(T->color);
   free(T->P);
   free(T->d);
   free(T->f);
   free(*pG);
   *pG = NULL;
   }
}

// Access functions

// getOrder()
// Returns the number of vertices.
int getOrder(Graph G) {
   if(G == NULL) {
      printf("Graph error: getOrder() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   return G->order;
}

// getSize()
// Returns the edges of edges
int getSize(Graph G) {
   if(G == NULL) {
      printf("Graph error: getSize() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   return G->size;
}

// getPartent()
// Returns the parent of the vertex u
// Pre: 1 <= u <= getOrder(G)
// note the parent of a vertex may be NIL
int getParent(Graph G, int u) {
   if(G == NULL) {
      printf("Graph error: getParent() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   if(u < 1 || u > getOrder(G)) {
      printf("Graph error: getParent() called on invalid u vertex reference.\n");
      exit(EXIT_FAILURE);
   }
   return G->P[u];
}

// getDiscover()
// Returns
// Pre: 1 <= u <= n = getOrder(G)
// Note: will be undefine before DFS is called
int getDiscover(Graph G, int u) {
   if(G == NULL) {
      printf("Graph error: getDiscover() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   return G->d[u];
}

// getFinish()
// Pre: 1 <= u <= n = getOrder(G)
// Note: will be undefine before DFS is called
int getFinish(Graph G, int u) {
   if(G == NULL) {
      printf("Graph error: getFinish() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   return G->f[u];
}
// manipulation procedures

// addEdge()
// Inserts a new edge joining u to v
// Pre: 1 <= u <= getOrder() && 1<= v <= getOrder()
void addEdge(Graph G, int u, int v) {
   if(G == NULL) {
      printf("Graph error: addEdge() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   if(u < 1 || u > getOrder(G)) {
      printf("Graph error: addEdge() called on invalid u vertex reference.\n");
      exit(EXIT_FAILURE);
   }
   addArc(G, u, v);
   addArc(G, v, u);
   G->size--;
}

// addArc()
// Inserts a new directed edge from u to v
// Pre: 1 <= u <= getOrder() && 1<= v <= getOrder()
void addArc(Graph G, int u, int v) {
   if(G == NULL) {
      printf("Graph error: addArc() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   if(u < 1 || u > getOrder(G)) {
      printf("Graph error: addArc() called on invalid u vertex reference.\n");
      exit(EXIT_FAILURE);
   }
   List L = G->neighbors[u];
   // from v to u single direction
   if(length(L) == 0) {
      append(L, v);
      G->size++;
      return;
   }
   for(moveFront(L); index(L) != -1; moveNext(L)) {
      if(v < get(L)) {
         insertBefore(L, v);
         break;
      }
   }
   if(index(L) == -1) append(L, v);
   G->size++;
}
// private helper function Visit()
// implement by option 2( access DFS() local variable time by access its address)
void Visit(Graph G, int x, int *time, List S) {
   G->color[x] = GREY;
   G->d[x] = ++*time;
   List T = G->neighbors[x];

   if(length(T) > 0) {
      moveFront(T);
      while(index(T) != -1 ) {
         int y = get(T);
         if(G->color[y] == WHITE) {
            G->P[y] = x;
            Visit(G, y, time, S);
         }
         moveNext(T);
      }
   }
   G->color[x] = BLACK;
   G->f[x] = ++*time;
   prepend(S, x);
}

// DFS()
// Runs the DFS algorithm on the Graph G with List S,
// perform the depth first search algorithm on G
// List S defines the order in which vertices will be processed in the main loop
// when DFS is complete, it will store the vertices in order of decreasing finish time
// hence S can be considered to be a stack
// Pre: length(S) == n and S contains some permutation of integers {1,2,...,n}
// where n = getOrder(G)
void DFS(Graph G, List S) {
   // check Pre:
   if(length(S) != getOrder(G)) {
      printf("Graph error: DFS() called on incorrect List length and Graph vertices.\n ");
      exit(EXIT_FAILURE);
   }
   // local var
   int time = 0;
   for(int x = 1; x < getOrder(G)+1; x++) {
      G->color[x] = WHITE;
      G->P[x] = NIL;
   }
   moveFront(S);
   while(index(S) >= 0) {
      int x = get(S);
      if(G->color[x] == WHITE) {
         Visit(G, x, &time, S);
      }
      moveNext(S);
   }
   for(int i = 0; i < getOrder(G); i++) {
      deleteBack(S);
   }
}


// other operations

Graph copyGraph(Graph G) {
   Graph GT = newGraph(getOrder(G));
   for(int i = 1; i <= getOrder(G); i++) {
      moveFront(G->neighbors[i]);
      while(index(G->neighbors[i]) != -1) {
         addArc(GT, i, get(G->neighbors[i]));
         moveNext(G->neighbors[i]);
      }
   }
   return GT;
}

Graph transpose(Graph G) {
   Graph CG = newGraph(getOrder(G));
   for(int i = 1; i <= getOrder(G); i++) {
      moveFront(G->neighbors[i]);
      while(index(G->neighbors[i]) != -1) {
         addArc(CG, get(G->neighbors[i]), i);
         moveNext(G->neighbors[i]);
      }
   }
   return CG;
}

void printGraph(FILE* out, Graph G) {
   for(int i = 1; i <= getOrder(G); ++i) {
      List L = G->neighbors[i];
      fprintf(out, "%d: ", i);
      for(moveFront(L); index(L) != -1; moveNext(L)) {
         fprintf(out, "%d ", get(L));
      }
      fprintf(out, "\n");
   }
}
