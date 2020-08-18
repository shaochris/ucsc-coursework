// FindComponents.c
// Main file for pa5 to implement DFS algorithm

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"

void Print(List L, Graph GT, FILE *out, int SCC) {
   // fields
   int count = 1;
   int end = front(L);
   int len = length(L);
   // allocate memory for SCC List
   List *CL = calloc(SCC + 1, sizeof(List));
   // make a SCC List array
   for(int i = 1; i <= SCC; i++) {
      CL[i] = newList();
   }

   for(int i = 1; i < len; i++) {
      int start = back(L);
      deleteBack(L);
      prepend(CL[count], start);
      if(getParent(GT, start) == NIL) {
         count++;
      }
   }
   prepend(CL[count], end);

   for(int i = 1; i <= SCC ; i++) {
      fprintf(out, "Component %d: ", i);
      printList(out, CL[i]);
      fprintf(out, "\n");
   }
   free(CL);
}

int main(int argc, char * argv[]) {
   // fields
   int n = 0; // number of vertices
   int v1 = 0, v2 = 0;
   int SCC = 0;
   FILE *in = NULL;
   FILE *out = NULL;
   Graph G = NULL;
   Graph GT = NULL;

   // check arguments
   if(argc != 3) {
      printf("Usage: %s infile outfile\n", argv[0]);
      exit(EXIT_FAILURE);
   }
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   // check reading files
   if(in == NULL) {
      printf("Error on reading input file %s.\n", argv[2]);
      exit(EXIT_FAILURE);
   }
   if(out == NULL) {
      printf("Error on reading output file %s.\n", argv[3]);
      exit(EXIT_FAILURE);
   }

   // read infile
   fscanf(in, "%d", &n);
   G = newGraph(n);
   while(fgetc(in) != EOF) {
      fscanf(in, "%d", &v1);
      fscanf(in, "%d", &v2);
      if(v1 == 0 && v2 == 0) break;
      addArc(G, v1, v2);
   }
   // Print
   fprintf(out, "Adjacency list representation of G:\n");
   printGraph(out, G);
   fprintf(out, "\n");

   // create a List with n nodes
   List L = newList();
   for(int i = 1; i <= n; i++){
      append(L, i);
   }
   GT = transpose(G);

   // run DFS search on G
   DFS(G, L);

   // run DFS on GT to get SSC
   DFS(GT, L);

   for(int i = 1; i <= getOrder(GT); i++) {
      if(getParent(GT, i) == NIL) {
         SCC++;
      }
   }
   fprintf(out, "G contains %d strongly connected components:\n", SCC);
   // print result
   Print(L, GT, out, SCC);

   // free all memory
   freeList(&L);
   freeGraph(&G);
   freeGraph(&GT);
   // close files
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}
