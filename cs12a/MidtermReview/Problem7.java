//Problem7.java

import java.util.Scanner;
class Problem7{
   public static void main( String[] args ){
      Scanner sc = new Scanner(System.in);
      int a = 0;
      double x=0, y=0,z=0;
      while( a<30 ){
         while(true){
            while( !sc.hasNextDouble() ){
               sc.next();
            }
            if( a<10 ){
               x = sc.nextDouble();
               if( x>0 ) break;
            }else if( a<20 ){
               y = sc.nextDouble();
               if( y<0 ) break;
            }else{
               z = sc.nextDouble();
               if( z>0 ) break;
            } 
         }
         a += 10; 
      }
      System.out.println("x = " + x + ", y = " + y + ", z = " + z); 
   }
}
