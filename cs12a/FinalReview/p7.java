// Problem7.java
import java.util.Scanner;
import java.io.*;
class p7{
   public static void main(String[] args)throws FileNotFoundException{

      Scanner sc = new Scanner (new File(args[0]));
      double[] a = new double[8];
      int n = a.length;
      for( int i = 0; i < n; i++){
         a[i] = sc.nextDouble();
      }
      double x = 0.0;
      for(int j = 0; j < n; j++){
         x += a[j];
      }
      System.out.println("The average value in file test is "+ x/n);
   }
}
