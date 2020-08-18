import java.util.Scanner;

class Sort4{

   public static void main( String[] args ){
      
      int a, b, c, d, temp;
      Scanner sc = new Scanner(System.in);

      // get a, b, c, d from user
      System.out.print("Enter four integers separated by spaces: ");
      a = sc.nextInt();
      b = sc.nextInt();
      c = sc.nextInt();
      d = sc.nextInt();
      
      // sort a, b, c, d in increasing order
      if( a>b ){ temp=a; a=b; b=temp; }  // swap a <--> b 
      if( b>c ){ temp=b; b=c; c=temp; }  // swap b <--> c
      if( c>d ){ temp=c; c=d; d=temp; }  // swap c <--> d
      
      if( a>b ){ temp=a; a=b; b=temp; }  // swap a <--> b
      if( b>c ){ temp=b; b=c; c=temp; }  // swap b <--> c
      if( a>b ){ temp=a; a=b; b=temp; }  // swap a <--> b
      // print numbers in increasing order
      System.out.println(a + " " + b + " " + c + " " + d);
      
                                                                                              }
                                                                                              }
