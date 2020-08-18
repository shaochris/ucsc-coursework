// List.h
// Header file for the List ADT

#ifndef _LIST_H_INCLUDE_
#define _LIST_H_INCLUDE_

#include <stdio.h>

// Exported tpte
typedef struct ListObj* List;

// Constructor-Destrucotrs

// newList()
// Returns reference to new empty List object.
List newList(void);

// freeList()
// Frees all heap memory associated with List *pL, and sets *pL to NUll.
void freeList(List* pL);

// Accrss functions

// length()
// Returns the length of L.
int length(List L);

// index()
// Returns the index of cursor.
int index(List L);

// front()
// Returns the front element of L.
int front(List L);

// back()
// Returns the back element of L.
int back(List L);

// get()
// Returns the present elemnt that cursor locates.
int get(List L);

// equals()
// Returns true (1) if A is identical to B, false (0) otherwise
int equals(List A, List B);

// Manipulation procedures

// clear()
// Make L become empty.
void clear(List L);

// moveFront()
// Move the cursor locate at the first element
void moveFront(List L);

// moveBack()
// Move the cursor locate at the last element
void moveBack(List L);

// movePrev()
// Make the cursor locate at the element in front of it.
void movePrev(List L);

// moveNext()
// Make the cursor locate at the element after it.
void moveNext(List L);

// prepend()
// Insert an elment at the head of the List.
void prepend(List L, int data);

// append()
// Insert an element at the end of the List.
void append(List L, int data);

// insertBefore()
// Insert an element in front of the cursor element.
void insertBefore(List L, int data);

// insertAfter
// Insert an element after the cursor element.
void insertAfter(List L, int data);

// deleteFront()
// Delete the front element
void deleteFront(List L);

// deleteBack()
// Delete the back element
void deleteBack(List L);

// delete()
// Delete the current element that cursor locates
void delete(List L);

// Other operations

// PrintList()
// Prints data elements in L on a single line to stdout.
void printList(FILE* out, List L);

// copyList()
// Create a new List by copy from L.
List copyList(List L);

#endif
