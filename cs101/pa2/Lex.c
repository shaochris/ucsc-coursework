// Lex.c
// indirect sort every line as a String alphabetically in C

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"
#define MAX_LEN 160

// indirect insertion Sort
void Sort(char *A, List L, int n){
   int j;
   char *temp;
   append(L, 0);
   for(j = 1; j < n; j++) {
      temp = &A[j*MAX_LEN];
      moveBack(L);
      while(index(L) >= 0 && strcmp(temp, &A[get(L)*MAX_LEN]) < 0) {
         movePrev(L);
      }
      if(index(L) >= 0) {
         insertAfter(L, j);
      }else {
         prepend(L, j);
      }
   }
}

int main(int argc, char * argv[]) {
   // fields
   int count = 0;
   FILE *in, *out;
   char tempLine[MAX_LEN];

   // check command line for correct number of arguments
   if(argc != 3) {
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }
   // open files for reading and writing.
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   if(in == NULL) {
      printf("Unable to open file %s for reading.\n", argv[1]);
      exit(EXIT_FAILURE);
   }
   if(out == NULL) {
      printf("Unable to open file %s for writing.\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // read lines
   while(fgets(tempLine, MAX_LEN, in) != NULL) {
      count++;
   }
   fclose(in);
   // allocate memory for *line
   char *line = calloc(count * MAX_LEN, sizeof(char));

   // reopen file in
   in = fopen(argv[1], "r");
   // reset count
   count = 0;
   while(fgets(&line[count * MAX_LEN], MAX_LEN, in) != NULL) {
      count++;
   }
   // create a new List
   List L = newList();
   Sort(line, L, count);

   // write output to file out
   moveFront(L);
   while(index(L) >= 0) {
      fprintf(out, "%s", &line[get(L)*MAX_LEN]);
      moveNext(L);
   }
   // close files
   fclose(in);
   fclose(out);

   // free the list and line.
   freeList(&L);
   free(line);
   return EXIT_SUCCESS;
}
