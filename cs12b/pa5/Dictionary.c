// Dictionary.c
// implementation of Dictionary ADT by using hashing in C
#include "Dictionary.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const int tableSize = 101;

struct Pair {
   char* key;
   char* value;
   struct Pair* next;
};

struct DictionaryObj {
   int numItems;
   struct Pair** table;
};

// -------------------------provided functions-------------------------
// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8 * sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
   unsigned int result = 0xBAE86554;
   while (*input) {
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1 
int hash(char* key) {
   return pre_hash(key) % tableSize;
}
// -------------------------provided functions-------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void) {
   struct DictionaryObj* D = malloc(sizeof(struct DictionaryObj));
   D->numItems = 0;
   D->table = calloc(tableSize, sizeof(struct Pair*));
   for(int i = 0; i < tableSize; ++i) {
      D->table[i] = NULL;
   }
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD) {
   for(int i = 0; i < tableSize; ++i) {
      if((*pD)->table[i] != NULL) {
         struct Pair* p = (*pD)->table[i];
         while(p != NULL) {
            struct Pair* prev = p;
            p = p->next;
            free(prev->key);
            free(prev->value);
            free(prev);
            }
         (*pD)->table[i] = NULL;
      }
   }
   free((*pD)->table);
   free(*pD);
   *pD = NULL;
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D) {
   return D->numItems > 0 ? 0 : 1;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D) {
   return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {
   int index = hash(k);
   if(D->table[index] != NULL) {
      struct Pair* p = D->table[index];
      while(p != NULL) {
         if(strcmp(p->key, k) == 0) {
             return p->value;
         }
         p = p->next;
      }
   }
   return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v) {
   if(lookup(D,k) == NULL){
      ++(D->numItems);
      int index = hash(k);
      struct Pair* newPair = malloc(sizeof(struct Pair));
      newPair->key = malloc(strlen(k) + 1);
      newPair->value = malloc(strlen(v) + 1);
      newPair->next = NULL;
      strcpy(newPair->key, k);
      strcpy(newPair->value, v);
      if(D->table[index] == NULL){
         D->table[index] = newPair;
      }else {
         struct Pair* p = D->table[index];
         newPair->next = p;
         D->table[index] = newPair;
      }
   }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k) {
   if(lookup(D, k) != NULL) {
      --(D->numItems);
      int index = hash(k);
      struct Pair* p = D->table[index];
      if(strcmp(p->key, k) == 0) {
         D->table[index] = p->next;
         free(p->key);
         free(p->value);
         free(p);
         return;
      }else {
         while(p != NULL) {
            struct Pair* prev = p;
            p = p->next;
            if(strcmp(p->key, k) == 0) {
               prev->next = p->next;
               free(p->key);
               free(p->value);
               free(p);
               break;
            }
         }
      }
   }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D) {
   for(int i = 0; i < tableSize; ++i) {
      if(D->table[i] != NULL) {
         struct Pair* p = D->table[i];
         while(p != NULL) {
            struct Pair* prev = p;
            p = p->next;
            free(prev->key);
            free(prev->value);
            free(prev);
         }
         D->table[i] = NULL;
      }
   }
   D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D) {
   for(int i = 0; i < tableSize; ++i) {
      if(D->table[i] != NULL) {
         struct Pair* p = D->table[i];
         while(p != NULL) {
            fprintf(out, "%s %s\n", p->key, p->value);
            p = p->next;
         }
      }
   }        
}



