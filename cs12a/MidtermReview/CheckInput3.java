import java.util.Scanner;

class CheckInput3{

   public static void main( String[] args ){

      Scanner sc = new Scanner(System.in);

      while(true){ // seemingly infinite loop

         System.out.print("Enter a number: ");
         if(sc.hasNextInt()){
            System.out.println("That was an int");
            sc.nextInt();
            continue;  // note this continue is unnecessary
         }else if(sc.hasNextDouble()){
            System.out.println("That was a double");
            sc.nextDouble();
            continue;  // so is this one
         }else{
            System.out.println("That wasn't a number");
            sc.next();
            break;
         }
         
         // continue lands here
                }
                      // break lands here
                            System.out.println("bye!");
                               }
                               }
         
