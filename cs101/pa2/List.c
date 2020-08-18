// List.c
// Implementation file for List ADT

#include<stdio.h>
#include<stdlib.h>
#include"List.h"

// structs

// private NodeObj type
typedef struct NodeObj {
   int data;
   struct NodeObj* next;
   struct NodeObj* prev;
} NodeObj;

// private Node type
typedef NodeObj* Node;

Node newNode(int data) {
   Node N = malloc(sizeof(NodeObj));
   N->data = data;
   N->next = NULL;
   N->prev = NULL;
   return(N);
}

void freeNode(Node* pN) {
   if(pN != NULL && pN != NULL) {
      free(*pN);
      *pN = NULL;
   }
}
// private ListObj type
typedef struct ListObj {
   Node front;
   Node back;
   Node curr;
   int length;
   int cursorIndex;
} ListObj;

// Constructor-Destrucotrs

// newList()
// Returns reference to new empty List object.
List newList(void) {
   List L = malloc(sizeof(ListObj));
   L->front = L->back = L->curr = NULL;
   L->cursorIndex = -1;
   L->length = 0;
   return (L);
}


// freeList()
// Frees all heap memory associated with List *pL, and sets *pL to NUll.
// alter from Queue.c example
void freeList(List* pL) {
   if(pL != NULL && *pL != NULL) {
      Node N = (*pL)->front;
      while(N != NULL) {
         Node curr = N;
         N = N->next;
         free(curr);
      }
      free(*pL);
      *pL = NULL;
   }
}

// Accrss functions

// length()
// Returns the length of L.
int length(List L) {
   if(L == NULL) {
      printf("List Error: calling length() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   return L->length;
}

// index()
// Returns the index of cursor.
// If curosr is defined, returns the index of the cursor element,
// otherwise returns -1;
int index(List L) {
   if(L == NULL) {
      printf("List Error: calling index() calling on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(L->curr == NULL) {
      return -1;
   }

   if(length(L) > 0) {
      return L->cursorIndex;
   }else {
      return -1;
   }
}

// front()
// Returns the front element of L.
// Pre: length() > 0
int front(List L) {
   if(L == NULL) {
      printf("List Error: calling front() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling front() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   return L->front->data;
}

// back()
// Returns the back element of L.
// Pre: length()>0
int back(List L) {
   if(L == NULL) {
      printf("List Error: calling back() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling back() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   return L->back->data;
}

// get()
// Returns the present elemnt that cursor locates.
// Pre: length()>0, index()>=0
int get(List L) {
   if(L == NULL) {
      printf("List Error: calling get() on NULL List.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling get() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   if(index(L) < 0) {
      printf("List Error: calling get() on an NULL cursor List.\n");
      exit(EXIT_FAILURE);
   }
   return L->curr->data;
}

// equals()
// Returns true (1) if A is identical to B, false (0) otherwise
// copy from Queue.c example
int equals(List A, List B) {
   int eq = 0;
   Node N = NULL;
   Node M = NULL;
   if( A == NULL || B == NULL ){
      printf("List Error: calling equals() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   eq = ( A->length == B->length );
   N = A->front;
   M = B->front;
   while( eq && N!=NULL){
      eq = (N->data == M->data);
      N = N->next;
      M = M->next;
   }
   return eq;
}

// Manipulation procedures

// clear()
// Make L become empty.
void clear(List L) {
   if(L == NULL) {
      printf("List Error: calling clear() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   Node N = L->front;
   while(N != NULL) {
      Node temp = N;
      N = N->next;
      freeNode(&temp);
   }
   L->front = NULL;
   L->back = NULL;
   L->curr = NULL;
   L->length = 0;
   L->cursorIndex = -1;
}

// moveFront()
// Move the cursor locate at the first element
void moveFront(List L) {
   if(L == NULL) {
      printf("List Error: calling moveBack() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling moveBack() on an empty List.\n");
      exit(EXIT_FAILURE);
   }else {
      L->curr = L->front;
      L->cursorIndex = 0;
   }
}

// moveBack()
// Move the cursor locate at the last element
void moveBack(List L) {
   if(L == NULL) {
      printf("List Error: calling moveBack() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling moveBack() on an empty List.\n");
      exit(EXIT_FAILURE);
   }else {
      L->curr = L->back;
      L->cursorIndex = (L->length) - 1;
   }
}

// movePrev()
// Make the cursor locate at the element in front of it.
void movePrev(List L){
   if(L == NULL) {
      printf("List Error: calling movePrev() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling movePrev() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   if(L->curr == L->front) {
      L->curr = NULL;
      freeNode(&L->curr);
      L->cursorIndex = -1;
   }else {
      L->curr = L->curr->prev;
      L->cursorIndex--;
   }
}

// moveNext()
// Make the cursor locate at the element after it.
void moveNext(List L) {
   if(L == NULL) {
      printf("List Error: calling moveNext() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling moveNext() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   if(L->curr == L->back) {
      L->curr = NULL;
      freeNode(&L->curr);
      L->cursorIndex = -1;
   }else {
      L->curr = L->curr->next;
      L->cursorIndex++;
   }
}

// prepend()
// Insert an elment at the head of the List.
void prepend(List L, int data) {
   if(L == NULL) {
      printf("List Error: calling prepend() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   Node N = newNode(data);
   if(length(L) == 0) {
      L->front = L->back = N;
      N->prev = N->next = NULL;
   }else {
      L->front->prev = N;
      N->next = L->front;
      L->front = N;
      N->prev = NULL;
   }
   if(L->curr != NULL) {
      L->cursorIndex++;
   }
   L->length++;
}

// append()
// Insert an element at the end of the List.
void append(List L, int data) {
   if(L == NULL) {
      printf("List Error: calling append() on NUll List reference.\n");
      exit(EXIT_FAILURE);
   }
   Node N = newNode(data);
   if(length(L) <= 0) {
      L->front = L->back = N;
      N->next = N->prev = NULL;
   }else {
      L->back->next = N;
      N->prev = L->back;
      L->back = N;
      L->back->next = NULL;
   }
   L->length++;
}

// insertBefore()
// Insert an element in front of the cursor element.
void insertBefore(List L, int data) {
   if(L == NULL) {
      printf("List Error: calling insertBefore() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling insertBefore() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   if(index(L) < 0) {
      printf("List Error: calling insertBefore() on undefined cursor.\n");
      exit(EXIT_FAILURE);
   }
   if(L->curr == L->front) {
      prepend(L, data);
   }else {
      Node N = newNode(data);
      N->prev = L->curr->prev;
      N->next = L->curr;
      L->curr->prev->next = N;
      L->curr->prev = N;
      L->length++;
      L->cursorIndex++;
   }
}

// insertAfter
// Insert an element after the cursor element.
void insertAfter(List L, int data) {
   if(L == NULL) {
      printf("List Error: calling insertAfter() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling insertAfter() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   if(index(L) < 0) {
      printf("List Error: calling insertAfter() on undefined cursor.\n");
      exit(EXIT_FAILURE);
   }
   if(L->curr == L->back) {
      append(L, data);
   }else {
      Node N = newNode(data);
      N->prev = L->curr;
      N->next = L->curr->next;
      N->next->prev = N;
      L->curr->next = N;
      L->length++;
   }
}

// deleteFront()
// Delete the front element
void deleteFront(List L) {
   if( L == NULL) {
      printf("List Error: calling deleteFront() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) == 0) {
      printf("List Error: calling deleteFront() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   Node N = NULL;
   N = L->front;
   if(length(L) > 1) {
      L->front = L->front->next;
      L->front->prev = NULL;
   }else {
      L->front = L->back = NULL;
   }
   L->length--;
   if(L->curr != NULL) {
      L->cursorIndex--;
   } else {
      L->cursorIndex = -1;
      //free(L->curr);
   }
   freeNode(&N);
}

// deleteBack()
// Delete the back element
void deleteBack(List L) {
   if(L == NULL) {
      printf("List Error: calling deleteback() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
   if(length(L) <= 0) {
      printf("List Error: calling deleteBack() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   Node N = NULL;
   N = L->back;
   if(L->curr == L->back) {
      L->curr = NULL;
      L->cursorIndex = -1;
      //free(L->curr);
   }
   if(length(L) > 1) {
      L->back = L->back->prev;
      L->back->next = NULL;
   }else {
      L->front = L->back = NULL;
   }
   L->length--;
   freeNode(&N);
}

// delete()
// Delete the current element that cursor locates
void delete(List L) {
   if(L == NULL) {
      printf("List Error: calling delete() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }
      if(length(L) <= 0) {
      printf("List Error: calling delete() on an empty List.\n");
      exit(EXIT_FAILURE);
   }
   if(index(L) < 0) {
      printf("List Error: calling delete() on undefined cursor.\n");
      exit(EXIT_FAILURE);
   }
   if(L->curr == L->back) {
      deleteBack(L);
   }else if(L->curr == L->front) {
      deleteFront(L);
   }else {
      L->curr->prev->next = L->curr->next;
      L->curr->next->prev = L->curr->prev;
      L->curr = NULL;
      L->cursorIndex = -1;
      L->length--;
      freeNode(&L->curr);

   }
}

// Other operations

// PrintList()
// Prints data elements in L on a single line to stdout.
void printList(FILE* out, List L) {
   Node N = NULL;
   if(L==NULL){
      printf("List Error: calling printList() on NULL List reference.\n");
      exit(EXIT_FAILURE);
   }

   for(N = L->front; N != NULL; N = N->next){
      fprintf(out,"%d ", N->data);
   }
}

// copyList()
// Create a new List by copy from L.
List copyList(List L) {
   List A = newList();
   Node N = L->front;
   while(N != NULL) {
      append(A, N->data);
      N = N->next;
   }
   return A;
}
