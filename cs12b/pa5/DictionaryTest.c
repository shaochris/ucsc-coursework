// This is a Dictionary Test

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include "Dictionary.h"

#define MAX_LEN 180

int main(int argc,char **argv) {
   Dictionary A = newDictionary();
   char* word1[] = {"one","two","three","four","five","six","seven"};
   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
   int i;

   for(i=0; i<7; i++) {
    insert(A, word1[i], word2[i]);
   }

   assert(size(A) == 7);
   assert(isEmpty(A) == 0);

   assert(strcmp(lookup(A,"one"),"foo") == 0);
   assert(strcmp(lookup(A,"two"),"bar") == 0);
   assert(strcmp(lookup(A,"three"),"blah") == 0);
   assert(strcmp(lookup(A,"four"),"galumph") == 0);
   assert(strcmp(lookup(A,"five"),"happy") == 0);
   assert(strcmp(lookup(A,"six"),"sad") == 0);
   assert(strcmp(lookup(A,"seven"),"blue") == 0);

   delete(A, "one");
   delete(A, "two");
   delete(A, "three");
   delete(A, "four");

   assert(lookup(A,"one") == NULL);
   assert(lookup(A,"two") == NULL);
   assert(lookup(A,"three") == NULL);
   assert(lookup(A,"four") == NULL);
   assert(strcmp(lookup(A,"five"),"happy") == 0);
   assert(strcmp(lookup(A,"six"),"sad") == 0);
   assert(strcmp(lookup(A,"seven"),"blue") == 0);

   assert(size(A) == 3);

   makeEmpty(A);
   assert(isEmpty(A) == 1);

   freeDictionary(&A);
   assert(A == NULL);
   return EXIT_SUCCESS;
}
