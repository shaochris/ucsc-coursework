// Sparse.java
// main program for pa3 to handel inout and output file

import java.io.*;
import java.util.*;

public class Sparse {
   public static void main(String[] args) throws IOException {
      // check arguments
      if(args.length != 2) {
         System.exit(1);
      }
      Scanner in = new Scanner(new File(args[0]));
      PrintWriter out = new PrintWriter(new FileWriter(args[1]));

      int n = in.nextInt();
      int a = in.nextInt();
      int b = in.nextInt();
      Matrix matrixA = new Matrix(n);
      Matrix matrixB = new Matrix(n);

      //read matrix A
      in.nextLine();
      for(int i = 0; i < a; ++i) {
         int row = in.nextInt();
         int col = in.nextInt();
         double val = in.nextDouble();
         matrixA.changeEntry(row,col,val);
      }

      //read matrix B

      in.nextLine();
      for(int i = 0; i < b; ++i) {
         int row = in.nextInt();
         int col = in.nextInt();
         double val = in.nextDouble();
         matrixB.changeEntry(row,col,val);
      }

      out.println("A has " + matrixA.getNNZ() + " non-zero entries:");
      out.println(matrixA);

      out.println("B has " + matrixB.getNNZ() + " non-zero entries:");
      out.println(matrixB);

      out.println("(1.5)*A =");
      Matrix matrix1 = matrixA.scalarMult(1.5);
      out.println(matrix1);

      out.println("A+B =");
      Matrix matrix2 = matrixA.add(matrixB);
      out.println(matrix2);

      out.println("A+A =");
      Matrix matrix3 = matrixA.add(matrixA);
      out.println(matrix3);

      out.println("B-A =");
      Matrix matrix4 = matrixB.sub(matrixA);
      out.println(matrix4);

      out.println("A-A =");
      Matrix matrix5 = matrixA.sub(matrixA);
      out.println(matrix5);

      out.println("Transpose(A) =");
      Matrix matrix6 = matrixA.transpose();
      out.println(matrix6);

      out.println("A*B =");
      Matrix matrix7 = matrixA.mult(matrixB);
      out.println(matrix7);

      out.println("B*B =");
      Matrix matrix8 = matrixB.mult(matrixB);
      out.println(matrix8);

      // close files
      in.close();
      out.close();
   }
}
