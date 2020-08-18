//GCD.java
//Program3
//this program can compute the GCD of two positive integers

import java.util.Scanner;
class GCD{
        public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int x, y;
                System.out.print("Enter a positive integer: ");
                while(true){
                        if(sc.hasNextInt()){
                                x = sc.nextInt();
                                if( x>0 ) break;
                        }else{
                                sc.next();
                        }
                        System.out.print("Please enter a positive integer: ");
                }
                System.out.print("Enter another positive integer:");

                while(true){
                        if(sc.hasNextInt()){
                                y = sc.nextInt();
                                if( y>0 ) break;
                        }else{
                                sc.next();
                        }
                        System.out.print("Please enter a positive integer: ");
                }


                System.out.println("The GCD of "+x+" and "+y+" is " + gcd(x,y));

        }
        private static int gcd(int a, int b) {
                return (b == 0) ? a : gcd(b, a % b);
        }

}


