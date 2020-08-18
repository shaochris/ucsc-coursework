// ListTest.java
// Test Client for List.java

class ListTest {
   public static void main(String[] args) {
      List A = new List();
      List B = new List();

      for(int i = 1; i <= 20; i++) {
         A.append(i);
         B.prepend(i);
      }
      System.out.println(A);
      System.out.println(B);

      A.moveFront();
      while(A.index() >= 0) {
         System.out.print(A.get() + " ");
         A.moveNext();
      }
      System.out.println();

      B.moveBack();
      while(B.index() >= 0) {
         System.out.print(B.get() + " ");
         B.movePrev();
      }
      System.out.println();

      List C = A.copy();
      System.out.println(A.equals(B));
      System.out.println(B.equals(C));
      System.out.println(C.equals(A));

      A.moveFront();
      for(int i = 0; i < 5; i++) A.moveNext(); // at index 5
      A.insertBefore(-1);
      for(int i = 0; i < 9; i++) A.moveNext();
         A.insertAfter(-2);
      for(int i = 0; i < 5; i++) A.movePrev();
         A.delete();
      System.out.println(A);

      A.moveBack();
      System.out.println(A.index());
      A.deleteBack();
      System.out.println(A.index());

      B.moveFront();
      System.out.println(B.index());
      B.deleteFront();
      System.out.println(B.index());

      B.prepend("A");
      System.out.print(B);
      System.out.println();
      //System.out.println(B.index()); // what's the result should be? 0 or -1
      System.out.println(A.length());
      A.clear();
      System.out.println(A.length());

   }
}

// output
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// false
// false
// true
// 1 2 3 4 -1 5 6 7 8 9 10 11 12 13 14 15 -2 16 17 18 19 20
// 20
// -1
// 0
// -1
// A 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
// 20
// 0