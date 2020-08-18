import java.util.*;
public class Roots {

    public static void main(String[] args) {
        /*Declare variable */
        Scanner sc = new Scanner(System.in);
        int i,dgr,m;
        double tolerance = 0.0000001,threshold=0.001,resolution = 0.01,Lft=0,Rht=0;
        double[] C;

        /*Enter degree*/
        System.out.print("Enter the degree: ");
        dgr = sc.nextInt();
        C = new double[dgr+1];


        /*Enter coefficients*/
        System.out.print("Enter "+(dgr+1)+" coefficients: ");
        for(i=0;i<C.length;i++){
            C[i]=sc.nextDouble();
        }


        /*Enter endpoints*/
        System.out.print("Enter the left and right end points: ");
        Lft=sc.nextDouble();
        Rht=sc.nextDouble();
        System.out.println();
        double root=0,a=0,b=Lft;

        double[] E ;
        E =diff(C);
        while(a<Rht){
            a=b;
            b = a+resolution;
            if(poly(C,a)*poly(C,b)<0){
                root = findRoot(C,a,b,tolerance);
                System.out.print("Root found at ");
                System.out.printf("%.5f%n",root);
            }
            else if(poly(E,a)*poly(E,b)<0){
                root = findRoot(E,a,b,tolerance);
                if(Math.abs(poly(C,root))<threshold){
                    System.out.print("Root found at ");
                    System.out.printf("%.5f%n",root);
                }
            }
        }
        if(Math.abs(poly(C,root))>=threshold){
            System.out.print("\nNo roots were found in the specified interval.");
            System.out.println();
        }
    } 
    /*static void Main close */
    static double poly(double[] C,double t){
        double sum =0,p=0,q=1;
        for(int i=1;i<C.length;i++){
            q = q*t;
            p = q*C[i];
            sum = sum+p;
        }
        sum = sum +1*C[0];
        return sum;
    }


    static double[] diff(double[] C){
        int n=C.length-1;
        double[]D=new double[n];
        for(int i=0;i<n;i++){
            D[i]=C[i+1]*(i+1);
        }
        return D;

    }

    static double findRoot(double[] C, double a, double b, double tolerance){
        double mid=a,width = b-a;
        if(poly(C,a)*poly(C,b)<=0) {


            while (width > tolerance) {
                mid = (a + b) / 2;
                if (poly(C, a) * poly(C, mid) <= 0) {
                    b = mid;
                } else {
                    a = mid;
		
                }
                width = b - a;
		
            }

        }
        return mid;
       
    }
}

