// Queens.java
import java.util.Scanner;
class Queens {
    public static void main(String[] args) {
    int n = 0;
    String s1, s2;
    String ss = "";
    if(args.length == 1){
        s1 = args[0];
        ss = s1;
    }else if(args.length ==2){
        s1 = args[0];
        s2 = args[1];
        ss = s1+" "+s2;
    }else {
        usage();
        System.exit(0);
    }
    int output = 0;
    Scanner sc = new Scanner(ss);
    if(sc.hasNextInt()){
        output = 1;
        n = sc.nextInt();
    }else if(sc.hasNextLine()){
        try{
            try{
                s1 = sc.nextLine();
                if(s1.substring(0,2).equals("-v")){
                    n = Integer.parseInt(s1.substring(3));
                    output =2;
                }else if(s1.length()<3){
                    usage();
                    System.exit(0);
                }else{
                    usage();
                    System.exit(0);
                }
            }catch(StringIndexOutOfBoundsException e){
                usage();
                System.exit(0);
            }
        }catch(NumberFormatException ee){
            usage();
            System.exit(0);
        }
    }
    int[] A = new int[n+1];
    int r ;
    for(r = 0; r <= n; r++){
        A[r] = r;
    }
    int stop = 0, count = 0;
        if(output==1){
            while(stop <=Factorial(n)){
                nextPermutation(A);
                if(isSolution(A) == true){
                    count++;
                }
                stop++;
            }
            System.out.println(n+"-Queens has "+count+" solutions");
        }else if(output == 2){
            while(stop <= Factorial(n)){
                nextPermutation(A);
                if(isSolution(A)){
                    printArray(A);
                    count++;
                }
                stop++;
            }
            System.out.println(n+"-Queens has "+count+" solutions");
        }
    }



    static void nextPermutation(int[] A) {
        int i = A.length-1;
        int pv = 0, scr = 0;
        int pp = 0;

        while(i > 1){
            if(A[i-1] < A[i]){
                pp = i-1;
                pv = A[i-1];
                break;
            }
            i--;
        }
        if(i <= 1){
            reverse(A, 1,A.length-1);
            return;
        }

        int j = A.length-1;

        while(j > 1){
            if(A[j] > pv){
                scr = A[j];
                break;
            }
            j--;
        }

        swap(A, i-1, j);
        reverse(A, pp+1,A.length-1);
        return;

    }


    static int Factorial(int x) {
        int k = x;
        for (int i = 1; i < x; i++) {
            k *= i;
        }
        return k;
    }

    static void printArray(int[] A)
    {
        System.out.print("(");
        for (int i = 1; i <= A.length-2; i++){
            System.out.print(A[i] + ", ");
        }
        System.out.print(A[A.length-1] + ")");
        System.out.println();
    }


    static boolean isSolution(int[] A) {
        int i, j;
        int n = A.length;
        for (i = 1; i <= n - 1; i++) {
            for (j = i + 1; j <= n - 1; j++) {
                if (j - i == Math.abs(A[i] - A[j])) {
                    return false;
                }
            }
        }
        return true;
    }


    static void usage() {
        System.out.println("Usage: java Queens [-v] number");
        System.out.println("Option: -v verbose output, print all solutions");

    }


    static void swap(int[] A, int i, int j) {
        int temp;
        temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


    static void reverse(int[] A, int i, int j) {
        while (i < j) {
            swap(A, i, j);
            i++;
            j--;
        }
    }
}



