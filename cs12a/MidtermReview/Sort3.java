import java.util.Scanner;

class Sort3{

   public static void main(String[] args){
   
      double x, y, z, temp;
      Scanner sc = new Scanner(System.in);
      
      System.out.print("\nEnter three numbers: ");
      x = sc.nextDouble();
      y = sc.nextDouble();
      z = sc.nextDouble();
      
      if(x>y){
         temp = x;
         x = y;
         y = temp;
      }
      if(y>z){
         temp = y;
         y = z;
         z = temp;
      }
      if(x>y){
         temp = x;
         x = y;
         y = temp;
      }
      
      System.out.println("\nIncreasing order: " + x + ", " + y + ", " + z );
      System.out.println("\nDecreasing order: " + z + ", " + y + ", " + x + "\n");
      
   }
}
