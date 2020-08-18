//Problem6.java
//
import java.util.Scanner;
class Problem6{
   public static void main(String[] args){ 
      Scanner sc = new Scanner(System.in);
      int a, b, c;
      double x, y, z;
      System.out.print("Enter three integers: "); a = sc.nextInt();
      b = sc.nextInt();
      c = sc.nextInt();
      System.out.print("Enter three doubles: ");
      x = sc.nextDouble();
      y = sc.nextDouble();
      z = sc.nextDouble();
      a = (++c)-(b++);
      x += (z - y);
      b *= b;
      y = c*z;
      c = (int)(b - x);
      System.out.println("a=" + a + ", b=" + b + ", c=" + c); System.out.println("x=" + x + ", y=" + y + ", z=" + z); System.out.println("c/a=" + c/a); System.out.println("c%a=" + c%a);
   } 
}
