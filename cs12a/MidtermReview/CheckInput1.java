//-----------------------------------------------------------------------------
//// CheckInput1.java
//// Illustrates break and continue.
//// Reads positive integers from stdin and does nothing with them.  Halts
//// when the first non-positive is entered.
////----------------------------------------------------------------------------
import java.util.Scanner;

class CheckInput1{

   public static void main( String[] args ){

      Scanner sc = new Scanner(System.in);
      int a = sc.nextInt();

      while(true){  // seemingly infinite loop
         if(a>0){
            a = sc.nextInt();
            continue;
         }
         break;
         // continue lands here
          }
                    // break lands here
                            System.out.println("done");
                               }
                               }
         
