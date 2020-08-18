//Guess.java
//This is a number guessing game.

import java.util.Scanner;
class Guess {
    public static void main(String[] args) {
        //state and initialize varibles
        int d, t=1;
        String G = "first";
        double ran = Math.random();
        int x = (int) (1 + ran * 10);
        Scanner sc = new Scanner(System.in);
        //instructions of the game.
        System.out.println("I'm thinking of an integer in the range 1 to 10. You have three guesses.\n");
        //use "while" loop 
        while (t <= 3) {
            if (t == 2) {
                G = "second";
            }
            if (t == 3) {
                G = "third";
            }

            System.out.print("Enter your " + G + " guess: ");
            d = sc.nextInt();//input of user

            if (d < x) {
                System.out.println("Your guess is too low.");
                t++;
            }
            if (d == x) {
                System.out.println("you win!");
                t = 5;//end loop
            }
            if (d > x) {
                System.out.println("Your guess is too high.");
                t++;
            }
        }
        if (t == 4) {//end loop if the user tries three times
            System.out.println("You lose.  The number was: " + x);
        }
    }
}
