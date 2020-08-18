// Graph.c
// implement file for Graph ADT

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"
#include"List.h"

// INF and NIL are two macros with different nagative values.
#define INF -1 // -1 is a negative int and stands for infinity
#define NIL -2 // -2 is a non-positive int and stands for undefined vertex label
#define WHITE 0
#define GREY 1
#define BLACK 2

// structs

// private GraphObj type
typedef struct GraphObj {
   List *neighbors;
   int *color;
   int *P;
   int *d;

   int order; // number of vertices
   int size; // number of edges
   int source; // source vertex
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
   // initialize vals
   for(int i = 1; i < n+1; ++i) {
      G->neighbors[i] = newList();
      G->P[i] = NIL;
      G->d[i] = INF;
      G->color[i] = WHITE;
   }
   G->order = n;
   G->size = 0;
   G->source = NIL;
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

// getSource()
// Returns the source vertex
int getSource(Graph G) {
   if(G == NULL) {
      printf("Graph error: getSource() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   return G->source;
}

// getPartent()
// Returns the parent of the vertex u
// Pre: 1 <= u <= getOrder(G)
int getParent(Graph G, int u) {
   if(G == NULL) {
      printf("Graph error: getParent() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   if( u < 1 || u > getOrder(G)) {
      printf("Graph error: getParent() called on invalid u vertex reference.\n");
      exit(EXIT_FAILURE);
   }
   return G->P[u];
}

// getDist()
// Returns the distance from the most recent BFS source to vertex u
// Pre: 1 <= u <= getOrder(G)
int getDist(Graph G, int u) {
   if(G == NULL) {
      printf("Graph error: getDist() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   if( u < 1 || u > getOrder(G)) {
      printf("Graph error: getDist() called on invalid u vertex reference.\n");
      exit(EXIT_FAILURE);
   }
   if(getSource(G) == NIL) return INF;
   return G->d[u];
}

// getPath()
// Appends to the List L the vertices of a shortest path in G from source to u, or
// appends to L the value NIL if no such path exists.
// Pre: getSource(G) != NIL, so this function must call after BFS()
// Pre: 1 <= u <= getOrder(G)
void getPath(List L, Graph G, int u) {
   if(G == NULL) {
      printf("Graph error: getPath() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   if(getSource(G) == NIL) {
      printf("Graph error: getPath() called on no source.\n");
      exit(EXIT_FAILURE);
   }
   if( u < 1 || u > getOrder(G)) {
      printf("Graph error: getPath() called on invalid u vertex reference.\n");
      exit(EXIT_FAILURE);
   }
   if(u == getSource(G)) { // if the u == source
      append(L, getSource(G));
   }else if(G->P[u] == NIL) { // if parent value is NIL
      append(L, NIL);
   }else {
      getPath(L, G, G->P[u]);
      append(L, u);
   }
}

// manipulation procedures

// makeNull()
// deletes all edges of G, restoring it to its original (no edge) state
void makeNull(Graph G) {
   if(G == NULL) {
      printf("Graph error: makeNull() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   for(int i = 1; i < getOrder(G) + 1; i++) {
      clear(G->neighbors[i]);
      G->P[i] = NIL;
      G->d[i] = INF;
      G->color[i] = WHITE;
   }
   G->source = NIL;
   G->size = 0;
}
// addEdge()
// Inserts a new edge joining u to v
// Pre: 1 <= u <= getOrder() && 1<= v <= getOrder()
void addEdge(Graph G, int u, int v) {
   if(G == NULL) {
      printf("Graph error: addEdge() called on NULL Graph reference.\n");
      exit(EXIT_FAILURE);
   }
   if( u < 1 || u > getOrder(G)) {
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
   if( u < 1 || u > getOrder(G)) {
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

// BFS()
// Runs the BFS algorithm on the Graph G with source s,
// setting the color, distance, parent, and source fields of G accordingly.
void BFS(Graph G, int s) {
   for(int x = 1; x < getOrder(G)+1; x++) {
      G->color[x] = WHITE;
      G->d[x] = INF;
      G->P[x] = NIL;
   }
   G->color[s] = GREY;
   G->d[s] = 0;
   G->P[s] = NIL;
   G->source = s;
   List L = newList();
   append(L, s);
   while(length(L) > 0) {
      int x = front(L);
      deleteFront(L);
      List T = G->neighbors[x];
      moveFront(T);
      while(index(T) != -1) {
         int y = get(T);
         if(G->color[y] == WHITE) {
            G->color[y] = GREY;
            G->d[y] = G->d[x] + 1;
            G->P[y] = x;
            append(L, y);
         }
         moveNext(T);
         }
         G->color[x] = BLACK;
   }
   freeList(&L);
}

// other operations
void printGraph(FILE* out, Graph G) {
   for(int i = 1; i <= getOrder(G); ++i) {
      List L = G->neighbors[i];
      fprintf(out, "%d: ", i);
      for(moveFront(L); index(L) != -1; moveNext(L)) {
         //fprintf(out, "%d%c", get(L), index(L) == length(L) - 1 ? '\n' : ' ');
         fprintf(out, "%d ", get(L));
      }
      fprintf(out, "\n");
   }
}
