// List.java
// List ADT for pa3

public class List {
   // private inner Node class
   @SuppressWarnings("overrides")
   private class Node {
      // Fields
      Object entry;
      Node next;
      Node prev;

      // constructor
      Node(Object entry) {
         this.entry = entry;
         next = prev = null;
      }

      // toString():  overrides Object's toString() method
      // copy from Queue.java example
      public String toString() {
         return String.valueOf(entry);
      }
   }

   // Fields
   private Node node;
   private Node curr;
   private int cursorIndex;
   private int length;

   // Constructor
   List() { // Creates a new empty list.
      node = new Node(null);
      node.prev = node;
      node.next = node;
      curr = node;
      cursorIndex = -1;
      length = 0;
   }

   // access functions

   // Returns the number of elements in this List
   int length() {
      return length;
   }

   // If cursor is defined, returns the index of the cursor element,
   // otherwise returns -1;
   int index() {
      return cursorIndex;
   }

   // Returns front element. Pre: length()>0
   Object front() {
      if(length() > 0) {
         return node.next.entry;
      }else {
         throw new RuntimeException("List Error: front() called on empty List");
      }
   }

   // Returns back element. Pre: length()>0
   Object back() {
      if(length() > 0) {
         return node.prev.entry;
      }else{
         throw new RuntimeException("List Error: back() called on empty List");
      }
   }

   // Returns cursor element. Pre: length()>0, index()>=0
   Object get() {
      if(length() > 0 && index() >= 0) {
         return curr.entry;
      }else {
         return null;
      }
   }

   // Returns true if and only if this List and L are the same
   // integer sequence. This states of the cursors in the two Lists 
   // are not used in determinging equality
   boolean equals(List L) {
      if(length() != L.length()){
         return false;
      }
      Node lhs = node.next;
      Node rhs = L.node.next;
      while(lhs != node && rhs != L.node) {
         if(!lhs.entry.equals(rhs.entry)) {
            return false;
         }
         lhs = lhs.next;
         rhs = rhs.next;
      }
      return true;
   }

   // Manipulation procedures

   // Resets this List to its original empty state.
   void clear() {
      node.next = node;
      node.prev = node;
      length = 0;
      curr = node;
      cursorIndex = -1;
   }

   // If List is non-empty, places the cursor under the front element,
   // otherwise does nothing.
   void moveFront() {
      if(length() > 0) {
         curr = node.next;
         cursorIndex = 0; // index starts from 0;
      }
   }

   // If List is non-empty, places the cursor under the back element,
   // otherwise does nothing.
   void moveBack() {
      if(length() > 0) {
         curr = node.prev;
         cursorIndex = length -1;
      }
   }

   // If cursor is defined and not at front, moves cursor one step toward
   // front of this List, if cursor is defiend and at front, cursor becomes
   // undefined, if cursor is undefined does nothing.
   void movePrev() {
      if(cursorIndex == 0) { // if cursor is defined and at front
         curr = node;
         cursorIndex = -1; // make cursor undefine
      }else if(cursorIndex > 0) { 
         curr = curr.prev;
         cursorIndex--;
      }
   }

   // If cursor is defined and not at back, moves cursor one step toward 
   // back of this List, if cursor is defiend and at back, cursor becomes
   // undefined, if cursor is undefined does nothing.
   void moveNext() {
      if(cursorIndex != -1 && cursorIndex == length() - 1) {
         curr = node;
         cursorIndex = -1;
      }else if(cursorIndex != -1 && cursorIndex != length() - 1) {
         curr = curr.next;
         cursorIndex++;
      }
   }

   // Insert new element into this List. If List is non-empty, 
   // insertion takes place before front element.
   void prepend(Object entry) {
      Node N = new Node(entry);
      N.prev = node;
      N.next = node.next;
      node.next.prev = N;
      node.next = N;
      cursorIndex++;
      length++;
   }

   // Insert new element into this List. If List is non-empty.
   // insertion takes place after back element.
   // copy from Queue.java example
   void append(Object entry) {
      Node N = new Node(entry);
      N.prev = node.prev;
      N.next = node;
      node.prev.next = N;
      node.prev = N;
      length++;
   }

   // Insert new element before cursor.
   // Pre: length()>0, index()>=0
   void insertBefore(Object entry) {
      if(length() > 0 && index() >= 0) {
         Node N = new Node(entry);
         N.next = curr;
         N.prev= curr.prev;
         curr.prev.next = N;
         curr.prev = N;
         length++;
         cursorIndex++;
      }else {
         throw new RuntimeException("List Error: insertBefore() called on empty List");
      }
   }

   // Insert new element after cursor.
   // Pre: length()>0, index()>0
   void insertAfter(Object entry) {
      if(length() > 0 && index() >= 0) {
         Node N = new Node(entry);
         N.next = curr.next;
         N.prev = curr;
         curr.next.prev = N;
         curr.next = N;
         length++;
      }else {
         throw new RuntimeException("List Error: insertafter() called on empty List");
      }
   }

   // Deletes the front element. Pre: length()>0
   void deleteFront() {
      if(length() <= 0) {
         throw new RuntimeException("List Error: deleteFront() called on empty List");
      }else {
         node.next.next.prev = node;
         node.next = node.next.next;
         cursorIndex--;
      }
      //cursorIndex = -1;
      length--;
   }

   // Delete the back element. Pre: length()>0
   void deleteBack() {
      if(length() <= 0) {
         throw new RuntimeException("List Error: deleteBack() called on empty List");
      }
         node.prev.prev.next = node;
         node.prev = node.prev.prev;
      if(index() == length() - 1) {
         cursorIndex = -1;
         curr = node;
      }
      length--;
   }

   // Deletes cursor element, making cursor undefined
   // Pre: length()>0, index()>=0
   void delete() {
      if(length() <= 0 && index() < 0) {
         throw new RuntimeException("List Error: delete() called on empty List");
      }else {
         curr.prev.next = curr.next;
         curr.next.prev = curr.prev;
         curr = node;
         cursorIndex = -1;
         length--;
      }
   }

   //other methods

   // Overrides Object's toString method. Returns a String
   // representation of this List consisting of a space
   // separated sequence of integers, with front on left.
   // copy from Queue.java example
   public String toString() {
      StringBuffer sb = new StringBuffer();
      Node N = node.next;
      while( N != node) {
         sb.append(N.toString());
         sb.append(" ");
         N = N.next;
      }
      return new String(sb);
   }

   // Returns a new List representing the same integer sequence as this 
   // List. The cursor in the new List is undefined, regardless of the 
   // state of the cursor in this List and L. THe states of this List and L 
   // are unchanged
   // copy from Queue.java example
   List copy() {
      List L = new List();
      Node N = node.next;

      while(N != node) {
         L.append(N.entry);
         N = N.next;
      }
      return L;
   }
}
