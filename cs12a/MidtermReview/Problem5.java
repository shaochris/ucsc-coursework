// Problem5.java

class Problem5{

    static int sum(int n){
        int a = n;
        for(int i=1; i<n; i++){
            a = a + i;
        }
        return a;
    }

    static double avg(int n){
        //double k = n;

        //for(int i=1; i<n; i++){
        //k = k + i;
        double k = (double)sum(n)/n;
        return k;

        //double b = k/n;
        //return b;
    }

    static boolean ord(double x, double y, double z){
        boolean t = false;
        if(x < y && y < z){
            t = true;
        }
        return t;
    }
    public static void main(String[] args){
        System.out.println(avg(12));
        System.out.println(ord(1.2, 3.4 , 5.6));
        System.out.println(ord(3.4, 1.2 , 5.6));
    }
}
