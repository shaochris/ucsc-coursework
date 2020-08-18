// List.java

public class List {

   // private inner Node class
   @SuppressWarnings("overrides")
   private class Node {
      // Fields
      int data;
      Node next;
      Node prev;

      // constructor
      Node(int data) {
         this.data = data;
         next = prev = null;
      }

      // toString():  overrides Object's toString() method
      // copy from Queue.java example
      public String toString() {
         return String.valueOf(data);
      }

      //equals(): overrides Object's equals() method
      //copy from Queue.java example
      public boolean equals(Object x) {
         boolean eq = false;
         Node that;
         if(x instanceof Node) {
            that = (Node) x;
            eq = (this.data == that.data);
         }
         return eq;
      }
   }

   // Fields
   private Node front;
   private Node back;
   private Node present;
   private int length;
   private int cursorIndex;

   // Constructor
   List() { // Creates a new empty list.
      front = null;
      back = null;
      present = null;
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
      if(length() > 0 ) {
         return cursorIndex;
      }else {
         return -1; // means cursor is undefined
      }
   }

   // Returns front element. Pre: length()>0
   int front() {
      if(length() > 0) {
         return front.data;
      }else {
         throw new RuntimeException("List Error: front() called on empty List");
      }
   }

   // Returns back element. Pre: length()>0
   int back() {
      if(length() <= 0) {
         throw new RuntimeException("List Error: back() called on empty List");
      }
      return back.data;
   }

   // Returns cursor element. Pre: length()>0, index()>=0
   int get() {
      if(length() <= 0) {
         throw new RuntimeException("List Error: get() called on empty List");
      }else if(present == null){
         throw new RuntimeException("List Error: get() called on undefined cursor");
      }else {
         return present.data;
      }
   }

   // Returns true if and only if this List and L are the same
   // integer sequence. This states of the cursors in the two Lists 
   // are not used in determinging equality
   // copy from Queue.java example and modified
   boolean equals(List L) {
      boolean eq = false;
      List Q;
      Node N, M;
      if(L instanceof List) {
         Q = L;
         N = this.front;
         M = Q.front;
         eq = (this.length == Q.length);
         while(eq && N != null) {
            eq = N.equals(M);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }

   // Manipulation procedures

   // Resets this List to its original empty state.
   void clear() {
      front = null;
      back = null;
      present = null;
      length = 0;
      cursorIndex = -1;
   }

   // If List is non-empty, places the cursor under the front element,
   // otherwise does nothing.
   void moveFront() {
      if(length() > 0) {
         present = front;
         cursorIndex = 0; // index starts from 0;
      }

   }

   // If List is non-empty, places the cursor under the back element,
   // otherwise does nothing.
   void moveBack() {
      if(length() > 0) {
         present = back;
         cursorIndex = length -1;
      }
   }

   // If cursor is defined and not at front, moves cursor one step toward
   // front of this List, if cursor is defiend and at front, cursor becomes
   // undefined, if cursor is undefined does nothing.
   void movePrev() {
      if(present == front) { // if cursor is defined and at front
         present = null;
         cursorIndex = -1; // make cursor undefine
      }else { 
         present = present.prev;
         cursorIndex--;
      }
   }



   // If cursor is defined and not at back, moves cursor one step toward 
   // back of this List, if cursor is defiend and at back, cursor becomes
   // undefined, if cursor is undefined does nothing.
   void moveNext() {
      if(present == back) {
         present = null;
         cursorIndex = -1;
      }else {
         present = present.next;
         cursorIndex++;
      }
   }

   // Insert new element into this List. If List is non-empty, 
   // insertion takes place before front element.
   void prepend(int data) {
      Node N = new Node(data);
      if(length() <= 0) {
         front = N;
         back = N;
      }else {
         front.prev = N;
         N.next = front;
         N.prev = null;
         front = N;
      }
      cursorIndex++;
      length++;
   }

   // Insert new element into this List. If List is non-empty.
   // insertion takes place after back element.
   // copy from Queue.java example
   void append(int data) {
      Node N = new Node(data);
      if(length() <= 0) {
         front = N;
         back = N;
      }else {
         back.next = N;
         N.prev = back; // when it reaches the end, it should points back
         back = N;
      }
      length++;
   }

   // Insert new element before cursor.
   // Pre: length()>0, index()>=0
   void insertBefore(int data) {
      if(length() > 0 && index() >= 0) {
         if(present == front) {
            prepend(data);
         }else {
            Node N = new Node(data);
            N.prev = present.prev;
            N.next = present;
            present.prev.next = N;
            present.prev = N;
            length++;
            cursorIndex++;
         }
      }else {
         throw new RuntimeException("List Error: insertBefore() called on empty List");
      }
   }

   // Insert new element after cursor.
   // Pre: length()>0, index()>0
   void insertAfter(int data) {
      if(length() > 0 && index() >= 0) {
         if(present == back) {
            append(data);
         }else {
            Node N = new Node(data);
            N.prev = present;
            N.next = present.next;
            N.next.prev = N;
            present.next = N;
            length++;
         }
      }else {
         throw new RuntimeException("List Error: insertafter() called on empty List");
      }
   }

   // Deletes the front element. Pre: length()>0
   void deleteFront() {
      if(length() <= 0){
         throw new RuntimeException("List Error: deleteFront() called on empty List");
      }else if(back == front) {
         back = front = null;
         present = null;
         cursorIndex = -1;
      }else{
         front = front.next;
         front.prev = null;
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
      if(front == back) {
         front = back = null;
         present = null;
         cursorIndex = -1; 
      }
      if(present == back){
         present = null;
         cursorIndex = -1;
      }else{
         back = back.prev;
         back.next = null;
      }
      length--;
   }

   // Deletes cursor element, making cursor undefined
   // Pre: length()>0, index()>=0
   void delete() {
      if(length() <= 0 && index() < 0) {
         throw new RuntimeException("List Error: delete() called on empty List");
      }
      if(present == back){
         deleteBack();
         back = back.prev;
      }else if(present == front) {
         deleteFront();
      }else {
         present.prev.next = present.next;
         present.next.prev = present.prev;
         present = null;
         cursorIndex = -1;
         length--;
      }
   }

   //other methods

   // Overrides Object's toString method. Returns a String
   // representation of this List consisting of a space
   // separated sequence of integers, with front on left.
   // copy from Queue.java and modified
   public String toString() {
      StringBuffer sb = new StringBuffer();
      Node N = front;
      while( N != null) {
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
      Node N = this.front;

      while(N != null) {
         L.append(N.data);
         N = N.next;
      }
      return L;
   }
}




