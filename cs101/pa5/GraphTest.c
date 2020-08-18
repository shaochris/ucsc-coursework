// GraphTest.c
// Test file for Graph.c

#include<stdio.h>
#include<stdlib.h>
#include "Graph.h"

int main(int argc, char * argv[]) {

   printf("---------------Directed Graph Test------------------\n");
   int n = 11;
   Graph DG = newGraph(n);
   List S = newList();
   for(int i = 1; i <= n; i++) append(S, i);

   addArc(DG, 1, 2);
   addArc(DG, 2, 5);
   addArc(DG, 3, 2);
   addArc(DG, 3, 5);
   addArc(DG, 4, 1);
   addArc(DG, 5, 4);
   addArc(DG, 5, 6);
   addArc(DG, 6, 3);
   addArc(DG, 6, 9);
   addArc(DG, 6, 10);
   addArc(DG, 7, 3);
   addArc(DG, 7, 6);
   addArc(DG, 8, 4);
   addArc(DG, 9, 4);
   addArc(DG, 9, 5);
   addArc(DG, 9, 8);
   addArc(DG, 10, 9);
   addArc(DG, 10, 11);
   addArc(DG, 11, 7);
   printGraph(stdout, DG);
   printf("\n");


   int order = getOrder(DG);
   int size = getSize(DG);

   DFS(DG, S);
   printf("Graph order is: %d\n", order);
   printf("Graph size is: %d\n", size);
   printf("\n");
   fprintf(stdout, "\n");
   fprintf(stdout, "x:  d  f  p\n");
   for(int i=1; i<=n; i++){
      fprintf(stdout, "%d: %2d %2d %2d\n", i, getDiscover(DG, i), getFinish(DG, i), getParent(DG, i));
   }
   fprintf(stdout, "\n");
   printList(stdout, S);
   fprintf(stdout, "\n");

   Graph GT = transpose(DG);
   Graph CG = copyGraph(DG);
   fprintf(stdout, "\n");
   printGraph(stdout, CG);
   fprintf(stdout, "\n");
   printGraph(stdout, GT);
   fprintf(stdout, "\n");

   DFS(GT, S);
   fprintf(stdout, "\n");
   fprintf(stdout, "x:  d  f  p\n");
   for(int i=1; i<=n; i++){
      fprintf(stdout, "%d: %2d %2d %2d\n", i, getDiscover(GT, i), getFinish(GT, i), getParent(GT, i));
   }
   fprintf(stdout, "\n");
   printList(stdout, S);
   fprintf(stdout, "\n");


   freeGraph(&DG);
   freeGraph(&GT);
   freeGraph(&CG);
   freeList(&S);
   return EXIT_SUCCESS;
}

// output

// ---------------Directed Graph Test------------------
// 1: 2
// 2: 5
// 3: 2 5
// 4: 1
// 5: 4 6
// 6: 3 9 10
// 7: 3 6
// 8: 4
// 9: 4 5 8
// 10: 9 11
// 11: 7

// Graph order is: 11
// Graph size is: 19


// x:  d  f  p
// 1:  1 22  0
// 2:  2 21  1
// 3:  7  8  6
// 4:  4  5  5
// 5:  3 20  2
// 6:  6 19  5
// 7: 15 16 11
// 8: 10 11  9
// 9:  9 12  6
// 10: 13 18  6
// 11: 14 17 10

// 1 2 5 6 10 11 7 9 8 3 4

// 1: 2
// 2: 5
// 3: 2 5
// 4: 1
// 5: 4 6
// 6: 3 9 10
// 7: 3 6
// 8: 4
// 9: 4 5 8
// 10: 9 11
// 11: 7

// 1: 4
// 2: 1 3
// 3: 6 7
// 4: 5 8 9
// 5: 2 3 9
// 6: 5 7
// 7: 11
// 8: 9
// 9: 6 10
// 10: 6
// 11: 10


// x:  d  f  p
// 1:  1 22  0
// 2:  4 15  5
// 3:  5 14  2
// 4:  2 21  1
// 5:  3 18  4
// 6:  6 13  3
// 7:  7 12  6
// 8: 19 20  4
// 9: 16 17  5
// 10:  9 10 11
// 11:  8 11  7

// 1 4 8 5 9 2 3 6 7 11 10
