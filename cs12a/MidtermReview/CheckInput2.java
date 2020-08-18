import java.util.Scanner;

class CheckInput2{

   public static void main( String[] args ){

      Scanner sc = new Scanner(System.in);
      int i;
for(i=0; i<3; i++){

         System.out.print("Enter a positive double: ");
         while(true){
while( !sc.hasNextDouble() ){ // if its not a double
               sc.next(); // discard the non-double
               System.out.print("Please enter a positive double: "); }
if( sc.nextDouble()>0 ) break;
System.out.print("Please enter a positive double: "); 
}
}
 System.out.println("bye!");
}
}
