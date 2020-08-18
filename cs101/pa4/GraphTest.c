// GraphTest.c
// Test file for Graph.c

#include<stdio.h>
#include<stdlib.h>
#include "Graph.h"

int main(int argc, char * argv[]) {

   printf("---------------Directed Graph Test------------------\n");
   int n = 11; 
   int source = 10;
   Graph DG = newGraph(n);
   
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

   BFS(DG, source);
   printf("Graph source is: %d\n", source);
   printf("Graph order is: %d\n", order);
   printf("Graph size is: %d\n", size);
   printf("\n");

   for(int i = 1; i <= order; i++) {
      List A = newList();
      getPath(A, DG, i);
      if(getDist(DG, i) != INF) {
         printf("The distance from %d to %d is %d\n", source, i, length(A)-1);
         printf("A shortest %d-%d path is: ", source, i);
         printList(stdout, A);
         printf("\n\n");
      }else {
         printf("The distance from %d to %d is infinity\n", source, i);
         printf("No %d-%d path exists\n\n", source, i);
      }
      freeList(&A);
   }
   freeGraph(&DG);

   printf("--------------Undirected Graph Test--------------\n");
   n = 12; 
   source = 1;
   Graph UDG = newGraph(n);
   
   addEdge(UDG, 1, 2);
   addEdge(UDG, 2, 3);
   addEdge(UDG, 2, 8);
   addEdge(UDG, 3, 4);
   addEdge(UDG, 3, 8);
   addEdge(UDG, 4, 8);
   addEdge(UDG, 4, 9);
   addEdge(UDG, 4, 12);
   addEdge(UDG, 8, 12);

   addEdge(UDG, 5, 6);
   addEdge(UDG, 6, 7);
   addEdge(UDG, 6, 10);
   addEdge(UDG, 6, 11);
   addEdge(UDG, 7, 11);

   printGraph(stdout, UDG);
   printf("\n");

   order = getOrder(UDG);
   size = getSize(UDG);
   
   BFS(UDG, source);
   printf("Graph source is: %d\n", source);
   printf("Graph order is: %d\n", order);
   printf("Graph size is: %d\n", size);
   printf("\n");

   for(int i = 1; i <= order; i++) {
      List B = newList();
      getPath(B, UDG, i);
      if(getDist(UDG, i) != INF) {
         printf("The distance from %d to %d is %d\n", source, i, length(B)-1);
         printf("A shortest %d-%d path is: ", source, i);
         printList(stdout, B);
         printf("\n\n");
      }else {
         printf("The distance from %d to %d is infinity\n", source, i);
         printf("No %d-%d path exists\n\n", source, i);
      }
      freeList(&B);
   }
   freeGraph(&UDG);

   return EXIT_SUCCESS;
}

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

// Graph source is: 10
// Graph order is: 11
// Graph size is: 19

// The distance from 10 to 1 is 3
// A shortest 10-1 path is: 10 9 4 1 

// The distance from 10 to 2 is 4
// A shortest 10-2 path is: 10 9 4 1 2 

// The distance from 10 to 3 is 3
// A shortest 10-3 path is: 10 11 7 3 

// The distance from 10 to 4 is 2
// A shortest 10-4 path is: 10 9 4 

// The distance from 10 to 5 is 2
// A shortest 10-5 path is: 10 9 5 

// The distance from 10 to 6 is 3
// A shortest 10-6 path is: 10 9 5 6 

// The distance from 10 to 7 is 2
// A shortest 10-7 path is: 10 11 7 

// The distance from 10 to 8 is 2
// A shortest 10-8 path is: 10 9 8 

// The distance from 10 to 9 is 1
// A shortest 10-9 path is: 10 9 

// The distance from 10 to 10 is 0
// A shortest 10-10 path is: 10 

// The distance from 10 to 11 is 1
// A shortest 10-11 path is: 10 11 

// --------------Undirected Graph Test--------------
// 1: 2 
// 2: 1 3 8 
// 3: 2 4 8 
// 4: 3 8 9 12 
// 5: 6 
// 6: 5 7 10 11 
// 7: 6 11 
// 8: 2 3 4 12 
// 9: 4 
// 10: 6 
// 11: 6 7 
// 12: 4 8 

// Graph source is: 1
// Graph order is: 12
// Graph size is: 14

// The distance from 1 to 1 is 0
// A shortest 1-1 path is: 1 

// The distance from 1 to 2 is 1
// A shortest 1-2 path is: 1 2 

// The distance from 1 to 3 is 2
// A shortest 1-3 path is: 1 2 3 

// The distance from 1 to 4 is 3
// A shortest 1-4 path is: 1 2 3 4 

// The distance from 1 to 5 is infinity
// No 1-5 path exists

// The distance from 1 to 6 is infinity
// No 1-6 path exists

// The distance from 1 to 7 is infinity
// No 1-7 path exists

// The distance from 1 to 8 is 2
// A shortest 1-8 path is: 1 2 8 

// The distance from 1 to 9 is 4
// A shortest 1-9 path is: 1 2 3 4 9 

// The distance from 1 to 10 is infinity
// No 1-10 path exists

// The distance from 1 to 11 is infinity
// No 1-11 path exists

// The distance from 1 to 12 is 3
// A shortest 1-12 path is: 1 2 8 12 
//
