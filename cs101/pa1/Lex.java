// Lex.java

import java.io.*;
import java.util.Scanner;

class Lex {
   public static void main(String[] args) throws IOException{
      //fields
      Scanner in = null;
      PrintWriter out = null;
      String[] token = null;
      String line = " ";
      int i = 0, n, lineNumber = 0;

      // create a new List()
      List L = new List();

      // check args[]
      if(args.length != 2) {
         System.err.println("Usage: Lex input_file output_file");
         System.exit(1);
      }
      // open files
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      //get input from file args[0]
      while(in.hasNextLine()) {
         lineNumber++;
         line = in.nextLine()+ "|" + line;
         token = line.split("\\|");
         n = token.length;
      }

      Sort(token, L, lineNumber);

      // write output to file out
      L.moveFront();
      while(L.index() >= 0) {
         out.println(token[L.get()]);
         L.moveNext();
      }
      // close fiels
      in.close();
      out.close();
   }
   static void Sort(String[] A, List L, int n) {
      int i, j;
      String temp;
      L.append(0); // assume index 0 is the first
      for(j = 1; j < n; j++) {
         temp = A[j]; // temp is the element we want to insert
         i = j - 1; // i is the element before temp
         L.moveBack(); // make the cursor point to the last element of the list
         while(i >= 0 && temp.compareTo(A[L.get()]) < 0){  // L.get returns the index 
            L.movePrev();
            i--;
         }
         if(L.index() >= 0) {
            L.insertAfter(j);
         }else {
            L.prepend(j);
         }
      }
   }
}