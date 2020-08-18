// FindPath.c
// Purpose: Print a shortest path found by BFS

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"

void Search(FILE *in, FILE *out, Graph G) {
   int s = 0, dist = 0;
   while(fgetc(in) != EOF) {
      List L = NULL;
      int len = 0;
      fscanf(in, "%d", &s);
      fscanf(in, "%d", &dist);
      if(s == 0 && dist == 0) break; // stop reading
      BFS(G, s);
      L = newList();
      getPath(L, G, dist);
      len = length(L) - 1;
      if(getDist(G, dist) != INF) {
         fprintf(out, "The distance from %d to %d is %d\n", s, dist, len);
         fprintf(out, "A shortest %d-%d path is: ", s, dist);
         printList(out, L);
         fprintf(out,"\n\n");
      }else {
         fprintf(out, "The distance from %d to %d is infinity\n", s, dist);
         fprintf(out, "No %d-%d path exists\n\n", s, dist);
      }
      freeList(&L);
   }
}
int main(int argc, char * argv[]) {
   // fields
   int n = 0; // nember of vertices
   int v1 = 0, v2 = 0;
   FILE *in = NULL;
   FILE *out = NULL;
   Graph G = NULL;

   // check arguments
   if(argc != 3) {
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   // check reading file
   if(in == NULL) {
      printf("Error on reading input file %s.\n", argv[2]);
      exit(EXIT_FAILURE);
   }
   if(out == NULL) {
      printf("Error on reading output file %s.\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // read file
   fscanf(in, "%d", &n);
   G = newGraph(n);
   while(fgetc(in) != EOF) {
      fscanf(in, "%d", &v1);
      fscanf(in, "%d", &v2);
      if(v1 == 0 && v2 == 0) break; // stop reading
      addEdge(G, v1, v2);
   }
   printGraph(out, G);
   fprintf(out, "\n");

   // BFS
   Search(in, out, G);
   freeGraph(&G);

   //close files
   fclose(in);
   fclose(out);
   return EXIT_SUCCESS;
}
