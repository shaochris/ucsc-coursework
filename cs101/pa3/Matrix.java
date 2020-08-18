// Matrix.java
// Matrix ADT for pa3

@SuppressWarnings("overrides")
public class Matrix {

   // private inner Entry class
   @SuppressWarnings("overrides")
   private class Entry {
      // fields
      private int column;
      private double value;

      // Constructor for private inner Entry class
      Entry(int column, double value) {
         this.column = column;
         this.value = value;
      }

      // toString(): overrides Object's toString() method
      public String toString() {
         return column + " " + value;
      }

      // equals(): overrides Object's equals() method
      // copy form Queue.java example and modified
      public boolean equals(Object x) {
         Entry entry = (Entry)x;
         if(this.column == entry.column && this.value == entry.value) {
            return true;
         }else {
            return false;
         }
      }
   }

   // fields
   private List[] row;
   private int size;
   private int NNZ;

   // Constructor

   // Makes a new n x n zero Matrix.
   // pre: n>=1
   Matrix(int n) {
      this.row = new List[n];
      for(int i = 0; i < n; i++) {
         this.row[i] = new List();
      }
      this.size = n;
      this.NNZ = 0;
   }

   // Access functions

   // Returns n, hte number of the rows and columns of the Matrix
   int getSize() {
      return size;
   }
   // Returns the number of non-zero entries in this Matrix
   int getNNZ() {
      return NNZ;
   }
   // overrides Object's equals() method
   public boolean equals(Object x) {
      Matrix entry = (Matrix)x;
      if(this.size != entry.size || this.NNZ != entry.NNZ) {
         return false;
      }
      for(int i = 0; i < this.size; i++) {
         if(!this.row[i].equals(entry.row[i])) {
            return false;
         }
      }
      return true;
   }
   // Manipulation procedures
   // sets this Matrix to the zero state
   void makeZero() {
      this.row = new List[size];
      for(int i = 0; i < size; i++) {
         this.row[i] = new List();
      }
      this.NNZ = 0;
   }
   // returns a new Matrix having the same entries as this Matrix
   Matrix copy() {
      Matrix matrix = new Matrix(this.size);
      matrix.row = new List[this.size];
      for(int i = 0; i < this.size; i++) {
         matrix.row[i] = new List();
      }
      matrix.size = this.size;
      for(int i = 0; i < this.size; i++) {
         this.row[i].moveFront();
         while(this.row[i].index() >= 0) {
            Entry entry = (Entry)row[i].get();
            matrix.changeEntry(i+1, entry.column, entry.value);
            this.row[i].moveNext();
         }
      }
      return matrix;
   }
   // changes ith row, jth column of this Matrix to x
   // pre: 1<=i<=getSize(), 1<=j<=getSize()
   void changeEntry(int i, int j, double x) {
      if((i < 1 || i > getSize() || j < 1 || j > getSize())) {
         return;
      }
      boolean flag = false;
      this.row[i-1].moveFront();
      while(this.row[i-1].index() >= 0) {
         Entry entry = (Entry)row[i-1].get();
         if(entry == null) {
            return;
         }
         if(entry.column == j) {
            flag = true;
            if(x != 0) {
               entry.value = x;
            }else {
               this.row[i-1].delete();
               NNZ--;
            }
            break;
         }else if(entry.column > j) {
            flag = true;
            if(x != 0) {
               this.row[i-1].insertBefore(new Entry(j,x));
               NNZ++;
            }
            break;
         }
         this.row[i-1].moveNext();
      }
      if(!flag && x != 0) {
         this.row[i-1].append(new Entry(j,x));
         NNZ++;
      }
   }
   // returns a new Matrix that is the scalar product of this Matrix with x
   Matrix scalarMult(double x) {
      Matrix newMatrix = new Matrix(this.size);
      for(int i = 0; i < size; i++) {
         this.row[i].moveFront();
         while(row[i].index() >= 0) {
            Entry entry = (Entry)row[i].get();
            newMatrix.changeEntry(i+1, entry.column, entry.value * x);
            row[i].moveNext();
         }
      }
      return newMatrix;
   }
   // returns a new Matrix that is the sum of this Matrix with M
   // pre: getSize()==M.getSize()
   Matrix add(Matrix M) {
      if(M.getSize() != getSize()) {
         return null;
      }
      if(M == this) {
         return scalarMult(2);
      }
      Matrix newMatrix = new Matrix(size);
      for(int i = 0; i < size; i++) {
         this.row[i].moveFront();
         M.row[i].moveFront();
         while(row[i].index() >= 0 || M.row[i].index() >= 0) {
            Entry entry1 = (Entry)row[i].get();
            Entry entry2 = (Entry)M.row[i].get();
            if(entry1 == null) {
               M.row[i].moveNext();
               newMatrix.changeEntry(i+1,entry2.column, entry2.value);
            }else if(entry2 == null) {
               this.row[i].moveNext();
               newMatrix.changeEntry(i+1, entry1.column, entry1.value);
            }else {
               if(entry1.column == entry2.column) {
                  this.row[i].moveNext();
                  M.row[i].moveNext();
                  newMatrix.changeEntry(i+1, entry1.column, entry1.value + entry2.value);
               }else if(entry1.column < entry2.column) {
                  this.row[i].moveNext();
                  newMatrix.changeEntry(i+1, entry1.column, entry1.value);
               }else {
                  M.row[i].moveNext();
                  newMatrix.changeEntry(i+1, entry2.column, entry2.value);
               }
            }
         }
      }
      return newMatrix;
   }
   // returns a new Matrix that is the difference of this Matrix with M
   // pre: getSize()==M.getSize()
   Matrix sub(Matrix M) {
      if(M.getSize() != getSize()) {
         return null;
      }
      if(M == this) {
         return new Matrix(size);
      }
      Matrix newMatrix = new Matrix(size);
      for(int i = 0; i < size; i++) {
         this.row[i].moveFront();
         M.row[i].moveFront();
         while(this.row[i].index() >= 0 || M.row[i].index() >= 0) {
            Entry entry1 = (Entry)row[i].get();
            Entry entry2 = (Entry)M.row[i].get();
            if(entry1 == null) {
               M.row[i].moveNext();
               newMatrix.changeEntry(i+1, entry2.column, -entry2.value);
            }else if(entry2 == null) {
               row[i].moveNext();
               newMatrix.changeEntry(i+1, entry1.column, entry1.value);
            }else {
               if(entry1.column == entry2.column) {
                  this.row[i].moveNext();
                  M.row[i].moveNext();
                  if(entry1.value != entry2.value) {
                     newMatrix.changeEntry(i+1, entry1.column, entry1.value-entry2.value);
                  }
               }else if(entry1.column < entry2.column) {
                  this.row[i].moveNext();
                  newMatrix.changeEntry(i+1, entry1.column, entry1.value);
               }else {
                  M.row[i].moveNext();
                  newMatrix.changeEntry(i+1, entry2.column, -entry2.value);
               }
            }
         }
      }
      return newMatrix;
   }
   // returns a new Matrix that is the transpose of this Matrix
   Matrix transpose() {
      Matrix newMatrix = new Matrix(size);
      for(int i = 0; i < size; i++) {
         this.row[i].moveFront();
         while(this.row[i].index() >= 0) {
            Entry entry = (Entry)row[i].get();
            newMatrix.changeEntry(entry.column, i+1, entry.value);
            this.row[i].moveNext();
         }
      }
      return newMatrix;
   }
   // returns a new Matrix that is the product of this Matrix with M
   // pre: getSize()==M.getSize()
   Matrix mult(Matrix M) {
      if(getSize() != M.getSize()) {
         return null;
      }
      Matrix tp = M.transpose();
      Matrix newMatrix = new Matrix(size);
      for(int i = 0; i < size; i++) {
         if(this.row[i].length() == 0) {
            continue;
         }
         for(int j = 0; j < tp.size; j++) {
            if(tp.row[j].length() == 0) {
               continue;
            }
            double inner = dot(this.row[i], tp.row[j]);
            if(inner != 0) {
               newMatrix.changeEntry(i+1, j+1, inner);
            }
         }
      }
      return newMatrix;
   }
   // help dot product function
   private static double dot(List P, List Q) {
      double result = 0.0;
      P.moveFront();
      Q.moveFront();
      while(P.index() >= 0 && Q.index() >= 0) {
         if(((Entry)P.get()).column == ((Entry)Q.get()).column) {
            result += ((Entry)P.get()).value * ((Entry)Q.get()).value;
            P.moveNext();
            Q.moveNext();
         }else if(((Entry)P.get()).column < ((Entry)Q.get()).column) {
            P.moveNext();
         }else {
            Q.moveNext();
         }
      }
      return result;
   }
   // Other functions
   // overrides Object's toString() method
   public String toString() {
      StringBuffer sb = new StringBuffer();
      for(int i = 0; i < size; i++) {
         if(this.row[i].length() == 0) {
            continue;
         }else {
            this.row[i].moveFront();
            sb.append(i+1);
            sb.append(':');
            while(this.row[i].index() >= 0) {
               Entry entry = (Entry)this.row[i].get();
               sb.append(" (");
               sb.append(entry.column);
               sb.append(", ");
               sb.append(entry.value);
               sb.append(')');
               this.row[i].moveNext();
            }
            sb.append('\n');
         }
      }
      return sb.toString();
   }
}
